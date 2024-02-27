package com.onbrid.test.springboot.springboottest.service;

import com.onbrid.test.springboot.springboottest.model.FileInfo;
import com.onbrid.test.springboot.springboottest.model.OnBridOnamsData;
import com.onbrid.test.springboot.springboottest.properties.FilePathProperties;
import com.onbrid.test.springboot.springboottest.util.FileUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService extends OnamsService {

    final FileUtil fileUtil;

    public OnBridOnamsData writeFile(HttpServletRequest request, List<MultipartFile> files, Map paramMap) {
        log.debug("ParamMap: {}", paramMap);

        List<FileInfo> fileInfos = fileUtil.saveFile(files, paramMap);


        return null;
    }

}
