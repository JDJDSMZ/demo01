package com.example.oa.web;

import com.example.oa.pojo.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    //从配置文件中获取路径
    @Value("${oa.empphoto}")
    private String empPhoto;

    //员工相片上传
    @RequestMapping("/empphoto")
    public ResponseEntity<String> uploadEmpPhoto(HttpServletRequest request, MultipartFile file){
        //判断是否有文件上传
        //UUID
        String code = UUID.randomUUID().toString().replace("-", "");
        //图片上传名字
        String photoName = file.getOriginalFilename();
        System.out.println("file: " +file.toString());
        System.out.println("photoName: "+ photoName);
        //获取后缀
        String lastName = photoName.substring(photoName.lastIndexOf("."));
        //获取图片名 不包含后缀
        String pn = photoName.substring(0, photoName.lastIndexOf("."));

        //判断是否上传成功

        try {
            file.transferTo(new File(empPhoto + "\\" + pn + code + lastName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.debug(empPhoto + "\\" + photoName + code + lastName);
        return new ResponseEntity<String>("200", empPhoto + "\\" + pn + code + lastName);
    }

    //异常处理
    @ExceptionHandler({StringIndexOutOfBoundsException.class})
    public ResponseEntity<?> handlerException(StringIndexOutOfBoundsException s){
        s.printStackTrace();
        return new ResponseEntity<>("404", "存入的图片为空");
    }
}
