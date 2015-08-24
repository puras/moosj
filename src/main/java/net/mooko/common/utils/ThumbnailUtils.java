package net.mooko.common.utils;

import java.io.File;
import java.io.IOException;

/**
 * @author puras <he@puras.me>
 * @since 15/6/25  下午3:41
 */
public class ThumbnailUtils {
    public static final String THUMBNAIL_LARGE = "large";
    public static final String THUMBNAIL_MEDIUM= "medium";
    public static final String THUMBNAIL_SMALL = "small";
    public static final int THUMBNAIL_LARGE_WIDTH = 400;
    public static final int THUMBNAIL_LARGE_HEIGHT = 400;
    public static final int THUMBNAIL_MEDIUM_WIDTH = 300;
    public static final int THUMBNAIL_MEDIUM_HEIGHT = 300;
    public static final int THUMBNAIL_SMALL_WIDTH = 200;
    public static final int THUMBNAIL_SMALL_HEIGHT = 200;

    public static void saveThumbnail(String repositoryPath, String fileName, int width, int height) throws IOException {
//        String prefix = PathConfig.INSTANCE.getPictureRepository();
        String prefix = repositoryPath;
        prefix = prefix == null ? "" : prefix;
        String source = prefix + File.separator + fileName;
        String thumbnailName = thumbnailFileName(fileName, width, height);
        String target = prefix + File.separator + thumbnailName;
        ImageUtils.copyAndScale(source, target, width, height);
    }

    public static void saveAllThumbnail(String repositoryPath, String fileName) throws IOException {
        saveLargeThumbnail(repositoryPath, fileName);
        saveMediumThumbnail(repositoryPath, fileName);
        saveSmallThumbnail(repositoryPath, fileName);
    }

    public static void saveLargeThumbnail(String repositoryPath, String fileName) throws IOException {
        saveThumbnail(repositoryPath, fileName, THUMBNAIL_LARGE_WIDTH, THUMBNAIL_LARGE_HEIGHT);
    }

    public static void saveMediumThumbnail(String repositoryPath, String fileName) throws IOException {
        saveThumbnail(repositoryPath, fileName, THUMBNAIL_MEDIUM_WIDTH, THUMBNAIL_MEDIUM_HEIGHT);
    }

    public static void saveSmallThumbnail(String repositoryPath, String fileName) throws IOException {
        saveThumbnail(repositoryPath, fileName, THUMBNAIL_SMALL_WIDTH, THUMBNAIL_SMALL_HEIGHT);
    }

    public static String largeThumbnailFileName(String fileName) {
        return thumbnailFileName(fileName, THUMBNAIL_LARGE_WIDTH, THUMBNAIL_LARGE_HEIGHT);
    }

    public static String mediumThumbnailFileName(String fileName) {
        return thumbnailFileName(fileName, THUMBNAIL_MEDIUM_WIDTH, THUMBNAIL_MEDIUM_HEIGHT);
    }

    public static String smallThumbnailFileName(String fileName) {
        return thumbnailFileName(fileName, THUMBNAIL_SMALL_WIDTH, THUMBNAIL_SMALL_HEIGHT);
    }

    public static String thumbnailFileName(String fileName, int width, int height) {
        int lastPoint = fileName.lastIndexOf(".");
        String prefix = lastPoint == -1 ? fileName : fileName.substring(0, lastPoint);
        String suffix = lastPoint == -1 ? "" : fileName.substring(lastPoint);
        return prefix + "_" + width + "x" + height + suffix;
    }

    public static void main(String[] args) throws IOException {
        String large = largeThumbnailFileName("bfdff302-2e34-4e1d-a7e6-0d0924a3994d.png");
        String medium = mediumThumbnailFileName("bfdff302-2e34-4e1d-a7e6-0d0924a3994d.png");
        String small = smallThumbnailFileName("bfdff302-2e34-4e1d-a7e6-0d0924a3994d.png");
        System.out.println(large);
        System.out.println(medium);
        System.out.println(small);

        String fileName = "/tmp/respo/picture/bfdff302-2e34-4e1d-a7e6-0d0924a3994d.png";
        saveAllThumbnail("", fileName);
    }
}
