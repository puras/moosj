package net.mooko.moosj.service.impl;

import net.mooko.common.utils.MultipartUtils;
import net.mooko.common.utils.ThumbnailUtils;
import net.mooko.moosj.config.PathConfig;
import net.mooko.moosj.service.MultipartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.util.*;

/**
 * @author puras <he@puras.me>
 * @since 15/7/13  下午5:06
 */
@Service
@Transactional
public class MultipartServiceImpl implements MultipartService {
    private static final Logger logger = LoggerFactory.getLogger(MultipartServiceImpl.class);

    @Autowired
    private PathConfig pathConfig;

    private MultipartUtils.SavingStrategy savingStrategy;

    @Autowired
    public MultipartServiceImpl(PathConfig pathConfig) {
        this.savingStrategy = new MultipartUtils.UUIDStrategy(pathConfig.getUploadDir());
    }

    @Override
    public FileInfo saveFile(MultipartFile multipartFile) throws Exception {
        if (multipartFile.isEmpty()) {
            return new FileInfo(null, null);
        }
        File savedFile = MultipartUtils.saveFile(multipartFile, savingStrategy);
        String fileName = savedFile.getName();
        logger.debug("save file to {}", savedFile.getAbsoluteFile());
        return new FileInfo(fileName, pathConfig.getBaseResourceUrl() + "/" + fileName);
    }

    @Override
    public List<FileInfo> saveFiles(List<MultipartFile> multipartFiles) throws Exception {
        List<FileInfo> resultList = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            resultList.add(saveFile(file));
        }
        return resultList;
    }

    @Override
    public Map<String, List<FileInfo>> saveFiles(MultipartRequest multipartRequest) throws Exception {
        Map<String, List<FileInfo>> resultMap = new HashMap<>();
        for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
            String name = it.next();
            List<FileInfo> resultList = saveFiles(multipartRequest.getFiles(name));
            resultMap.put(name, resultList);
        }
        return resultMap;
    }
}
