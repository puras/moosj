package net.mooko.moosj.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author puras <he@puras.me>
 * @since 15/7/13  下午4:51
 */
public interface MultipartService {
    FileInfo saveFile(MultipartFile multipartFile) throws Exception;
    List<FileInfo> saveFiles(List<MultipartFile> multipartFiles) throws Exception;
    Map<String, List<FileInfo>> saveFiles(MultipartRequest multipartRequest) throws Exception;

    //文件被保存后相关信息
    class FileInfo {
        private String fileName;
        private String url;
        public FileInfo(String fileName, String url) {
            this.fileName = fileName;
            this.url = url;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    class MultipartEditor extends PropertyEditorSupport {
        private MultipartService multipartService;
        public MultipartEditor(MultipartService multipartService) {
            this.multipartService = multipartService;
        }
        @Override
        public Object getValue() {
            Object value = super.getValue();
            List<MultipartFile> multipartFiles = new ArrayList<>();
            if (value instanceof MultipartFile) {
                multipartFiles.add((MultipartFile)value);
            } else if (value instanceof List) {
                multipartFiles.addAll((List<MultipartFile>)value);
            }
            List<FileInfo> result = null;
            try {
                result = multipartService.saveFiles(multipartFiles);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (result == null || result.isEmpty()) {
                return null;
            } else if (result.size() == 1) {
                return result.get(0).getFileName();
            } else {
                List<String> fileNames = new ArrayList<>();
                for (FileInfo info : result) {
                    fileNames.add(info.getFileName());
                }
                return fileNames;
            }
        }
    }
}
