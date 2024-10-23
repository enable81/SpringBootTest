package com.onbrid.test.springboot.springboottest.view;

import com.onbrid.test.springboot.springboottest.properties.OnBridProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

@Slf4j
@Component
public class OnamsFileDownView extends AbstractView {


    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        File file = new File(String.valueOf(model.get(OnBridProperties.PARAM.FILE_PATH)));
        String FILE_NM = String.valueOf(model.get(OnBridProperties.PARAM.FILE_NAME));
        log.debug("OnamsFileDownView --> file :: {}/{}", file.getPath(), FILE_NM);

        response.setContentType("application/download; charset=utf-8");
        response.setContentLength((int) file.length());

        String userAgent = request.getHeader("User-Agent");
        boolean ie = userAgent.indexOf("MSIE") > -1;
        String fileName = null;

        if(ie) {
            fileName = URLEncoder.encode(FILE_NM, "utf-8");
        }
        else {
            fileName = new String(FILE_NM.getBytes("utf-8"), "iso-8859-1");
        }

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
            out.flush();
        }
        catch(Exception e) {
            //파일 다운로드 대화창 뜬 후 사용자가 취소를 누르면 ClientAbortException 발생한다.
            if( ! e.getClass().getSimpleName().equals("ClientAbortException")) {
                e.printStackTrace();
            }
        }
        finally {
            if(fis != null) {
                try {
                    fis.close();
                }
                catch(IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            if(out != null) {
                try {
                    out.close();
                }
                catch(IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
