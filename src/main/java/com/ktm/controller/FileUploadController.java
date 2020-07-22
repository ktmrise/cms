package com.ktm.controller;


import com.ktm.common.Result;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/manager")
public class FileUploadController {

    @Value("${photo.path}")
    private String path;


    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/file/upload")
    public Result fileUpload(MultipartFile file) throws IOException {

        String oldName = file.getOriginalFilename();

        String newName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(oldName);

        String realPath = path + newName;

        file.transferTo(new File(realPath));

        return Result.ok("success", "http://localhost:8099/" + newName, 200);
    }
}
