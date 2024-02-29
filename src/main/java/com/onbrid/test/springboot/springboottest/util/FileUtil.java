package com.onbrid.test.springboot.springboottest.util;

import com.onbrid.test.springboot.springboottest.exception.OnBridException;
import com.onbrid.test.springboot.springboottest.model.FileInfo;
import com.onbrid.test.springboot.springboottest.properties.FilePathProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class FileUtil {

    private final String LOCAL_PATH = "./ONAMS_FILE";

    final FilePathProperties filePathProperties;


    public List<FileInfo> saveFile(final List<MultipartFile> multipartFiles, String uploadPath) {
        List<FileInfo> files = new ArrayList<>();
        for (MultipartFile multipartFile: multipartFiles) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            files.add(saveFile(multipartFile, uploadPath));
        }
        return files;
    }

    public FileInfo saveFile(final MultipartFile multipartFile, String uploadPath) {
        if (multipartFile.isEmpty()) {
            return null;
        }
        //
        String namer = generateSaveFilename(multipartFile.getOriginalFilename());
        String systemPath = getUploadPath(LOCAL_PATH);
        Path saveFile = Paths.get(systemPath + File.separator + uploadPath + File.separator + namer);
        log.debug("savePath: {}", saveFile.toAbsolutePath());

        try {
            multipartFile.transferTo(saveFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FileInfo.builder()
//                .name(multipartFile.getOriginalFilename())
//                .namer(namer)
//                .fileSize(multipartFile.getSize())
                .build();
    }

    /**
     * 다운로드할 첨부파일(리소스) 조회 (as Resource)
     * @param uploadPath 업로드 경로
     * @param fileName 파일명
     * @return 첨부파일(리소스)
     */
    public Resource readFileAsResource(String uploadPath, String fileName) {
        Path filePath = Paths.get(filePathProperties.getFilePath(LOCAL_PATH), uploadPath, fileName);
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() == false || resource.isFile() == false) {
                throw new RuntimeException("file not found : " + filePath.toString());
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException("file not found : " + filePath.toString());
        }
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
