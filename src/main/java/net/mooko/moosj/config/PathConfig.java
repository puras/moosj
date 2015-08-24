package net.mooko.moosj.config;

import org.springframework.beans.factory.annotation.Value;

public class PathConfig {
    public static PathConfig INSTANCE = new PathConfig();

    @Value("${file.upload.tmp.dir}")
    private String uploadTmpDir;

    @Value("${file.upload.dir}")
    private String uploadDir;

    @Value("${base.resource.url}")
    private String baseResourceUrl;

    @Value("${picture.repository.dir}")
    private String pictureRepository;

    public String getUploadTmpDir() {
        return uploadTmpDir;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public String getBaseResourceUrl() {
        return baseResourceUrl;
    }

    public String getPictureRepository() {
        return pictureRepository;
    }
}
