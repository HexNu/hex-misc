package nu.hex.mediatype;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hln
 */
public class MediaTypeIdentifier {

    Map<String, Set<String>> fileTypeMediaTypeMap = new TreeMap<>();
    Map<String, String> suffixMap = new HashMap<>();
    Map<String, String> defaultMediaTypeMap = new HashMap<>();
    private final SourceProvider provider = new SourceProvider();

    public MediaTypeIdentifier() {
        parseMtd();
    }

    public Set<String> getMediaTypeByFileSuffix(String fileSuffix) {
        if (fileSuffix.indexOf(".") > 0) {
            fileSuffix = fileSuffix.substring(fileSuffix.lastIndexOf("."));
        }
        String suffix = fileSuffix.toLowerCase().replaceAll("\\.", "");
        try {
            Set<String> result = fileTypeMediaTypeMap.get(suffix);
            return result;
        } catch (NullPointerException ex) {
            Logger.getLogger(MediaTypeIdentifier.class.getName()).log(Level.WARNING, "Null provided as suffix");
        }
        return null;
    }

    public String getSuffix(String mediaType) {
        return defaultMediaTypeMap.get(mediaType);
    }

    public String[] getSuffixesByMediaType(String mediaType) {
        if (mediaType != null) {
            for (String suffix : fileTypeMediaTypeMap.keySet()) {
                if (fileTypeMediaTypeMap.get(suffix).contains(mediaType)) {
                    return suffix.split(",");
                }
            }
        }
        return null;
    }

    private void parseMtd() {
        BufferedReader reader = null;
        try {
            InputStream stream = provider.getCommonMediaTypesSource();
            reader = addToMap(reader, stream);
            stream.close();
            stream = provider.getHexMediaTypesSource();
            if (reader != null) {
                reader.close();
            }
            reader = addToMap(reader, stream);
            stream.close();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MediaTypeIdentifier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MediaTypeIdentifier.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MediaTypeIdentifier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private BufferedReader addToMap(BufferedReader reader, InputStream stream) throws UnsupportedEncodingException, IOException {
        reader = new BufferedReader(new InputStreamReader(stream, Main.MTD_ENCODING));
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.startsWith("MediaType")) {
                String[] lineParts = line.split(";");
                if (lineParts.length > 1) {
                    String[] fileSuffices = lineParts[1].split(",");
                    String mediaType = lineParts[0].trim().toLowerCase();
                    boolean defaultAdded = false;
                    for (String fileSuffix : fileSuffices) {
                        String suffix = fileSuffix.trim().toLowerCase();
                        if (!defaultAdded) {
                            defaultMediaTypeMap.put(mediaType, suffix);
                            defaultAdded = true;
                        }
                        suffixMap.put(suffix, mediaType);
                        if (!suffix.equals("")) {
                            if (fileTypeMediaTypeMap.containsKey(suffix)) {
                                fileTypeMediaTypeMap.get(suffix).add(mediaType);
                            } else {
                                Set<String> mediaTypeSet = new TreeSet<>();
                                mediaTypeSet.add(mediaType);
                                fileTypeMediaTypeMap.put(suffix, mediaTypeSet);
                            }
                        }
                    }
                }
            }
        }
        return reader;
    }

    private class SourceProvider {

        public InputStream getHexMediaTypesSource() {
            String resource = "HexMediaTypes.mtd";
            return getClass().getClassLoader().getResourceAsStream(resource);
        }

        public InputStream getCommonMediaTypesSource() {
            String resource = "CommonMediaTypes.mtd";
            return getClass().getClassLoader().getResourceAsStream(resource);
        }
    }
}
