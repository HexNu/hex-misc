package nu.hex.mediatype;

import java.io.IOException;
import nu.hex.mediatype.gui.MediaTypeEditorDialog;

/**
 * <h3>Run this class to autogenerate the HexMediaType interface.</h3>
 * <ol>
 * <li>The first column contains the mediatype string.</li>
 * <li>The second column contains file suffix for the type, if any.</li>
 * <li>Each following column is used for description. In the interface they will
 * appear on separate rows in the javadoc.</li>
 * <li>Run the project(-s Main class).</li>
 * </ol>
 * <p>
 * <b>DO NOT EDIT THE INTERFACE!</b></p>
 *
 * @author hln
 */
public class Main {

    /**
     * <h3>Encoding for the MTD-file.</h3>
     * <p>
     * The encoding for the file is UTF-8
     * </p>
     *
     */
    public static final String MTD_ENCODING = "UTF-8";

    /**
     * Runs the MediaTypeEditor.
     *
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        MediaTypeEditorDialog.main(args);
    }

}
