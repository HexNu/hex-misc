package nu.hex.mediatype.file;

import java.io.File;

/**
 *
 * @author hln
 */
public interface FileWriter {

    String DEFAULT_ENCODING = "UTF-8";

    File write();
}
