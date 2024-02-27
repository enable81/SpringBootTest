package com.onbrid.test.springboot.springboottest.util;

import com.onbrid.test.springboot.springboottest.exception.OnBridException;
import com.onbrid.test.springboot.springboottest.model.FileInfo;
import com.onbrid.test.springboot.springboottest.properties.FilePathProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class FileUtil {

    final FilePathProperties filePathProperties;


    public List<FileInfo> saveFile(final List<MultipartFile> multipartFiles, ) {
        List<FileInfo> files = new ArrayList<>();
        for (MultipartFile multipartFile: multipartFiles) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            files.add(saveFile(multipartFile, paramMap));
        }
        return files;
    }

    public FileInfo saveFile(final MultipartFile multipartFile, Map paramMap) {
        if (multipartFile.isEmpty()) {
            return null;
        }
        //
        String namer = generateSaveFilename(multipartFile.getOriginalFilename());
        String savePath = getUploadPath( "./ONAMS_FILE");
        Path saveFile = Paths.get(savePath + File.separator + namer);
        log.debug("savePath: {}", saveFile.toAbsolutePath());

        try {
            multipartFile.transferTo(saveFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FileInfo.builder()
                .name(multipartFile.getOriginalFilename())
                .namer(namer)
                .fileSize(multipartFile.getSize())
                .build();
    }


    private String generateSaveFilename(final String filename) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = StringUtils.getFilenameExtension(filename);
        return uuid + "." + extension;
    }

    private String getUploadPath(final String addPath) {
        return makeDirectories(addPath);
    }

    /**
     * 업로드 폴더(디렉터리) 생성
     * @param path - 업로드 경로
     * @return 업로드 경로
     */
    private String makeDirectories(final String path) {
        File dir = new File(path);
        if (dir.exists() == false) {
            dir.mkdirs();
        }
        return dir.getPath();
    }



}
