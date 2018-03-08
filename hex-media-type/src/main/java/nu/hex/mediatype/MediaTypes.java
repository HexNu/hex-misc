package nu.hex.mediatype;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created 2017-nov-07
 *
 * @author hl
 */
public class MediaTypes {

    public static final String APPLICATION = "application",
            AUDIO = "audio",
            IMAGE = "image",
            MESSAGE = "message",
            MODEL = "model",
            TEXT = "text",
            VIDEO = "video";
    private final String baseType;

    public MediaTypes() {
        this(null);
    }

    public MediaTypes(String baseType) {
        this.baseType = baseType;
    }

    public List<MediaType> getMedaTypes() {
        List<MediaType> result = new ArrayList<>();
        result.addAll(read("CommonMediaTypes"));
        result.addAll(read("HexMediaTypes"));
        Collections.sort(result);
        if (baseType == null || baseType.isEmpty()) {
            return result;
        }
        return result.stream().filter((s) -> s.getType().startsWith(baseType)).collect(Collectors.toList());
    }

    private List<MediaType> read(String file) {
        List<MediaType> result = new ArrayList<>();
        InputStream resource = getClass().getResourceAsStream("/" + file + ".mtd");
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(resource))) {
            buffer.lines().filter((line) -> !line.startsWith("MediaType")).forEach((s) -> {
                result.add(new MediaType(s));
            });
        } catch (IOException e) {
        }
        return result;
    }

    public static class MediaType implements Comparable<MediaType> {

        private final String source;
        private String type;
        private String suffix;

        public MediaType(String source) {
            this.source = source;
            setup();
        }

        private void setup() {
            String[] parts = source.split(";");
            type = parts[0];
            suffix = parts[1];
            if (suffix.contains(",")) {
                suffix = suffix.split(",")[0];
            }
        }

        public String getType() {
            return type;
        }

        public String getSuffix() {
            return suffix;
        }

        @Override
        public String toString() {
            return getType();
        }

        @Override
        public int compareTo(MediaType o) {
            return this.getType().compareTo(o.getType());
        }
    }

    public static void main(String[] args) {
        new MediaTypes(MediaTypes.IMAGE).getMedaTypes().forEach((mt) -> {
            System.out.println(mt.getType());
            System.out.println(mt.getSuffix());
        });
    }
}
