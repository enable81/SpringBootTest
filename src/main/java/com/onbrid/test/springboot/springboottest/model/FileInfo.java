package com.onbrid.test.springboot.springboottest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class FileInfo {

    //private Long id;
    private String univNo;
//    private String campusNo;
//    private String categoryItem;
//    private String id;
//    private String category;
//    /** 업로드 파일명 */
//    private String name;
//    /** 서버에 저장한 파일명 */
//    private String namer;
//    private String ext;
//    private long fileSize;
//    private String path;
//    private String type;
//    private String bigo;
//    private Date regDate;
//    private String regEmp;
//    private Date editDate;
//    private String editEmp;


}
