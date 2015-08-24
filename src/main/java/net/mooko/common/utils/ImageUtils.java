package net.mooko.common.utils;

import java.io.IOException;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class ImageUtils {
    public static void copyAndScale(String sourceFile, String targetFile,
            int expectWidth, int expectHeight) throws IOException {
        ScaleImage si = new ScaleImage();
        si.saveImageAsJpg(sourceFile, targetFile, expectWidth, expectHeight);
    }
}
