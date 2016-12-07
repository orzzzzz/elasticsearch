package com.icinfo.springmvc.file.controller;

import com.icinfo.springmvc.common.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.Map;


/**
 * 描述：文件上传
 *
 * @author zhangmengwen
 * @date 2016/10/19
 */
@Controller
@RequestMapping("/file")
public class UploadController {
    private Logger logger = LoggerFactory.getLogger(UploadController.class);
    /**
     * 跳转到上传文件页面
     *
     * @return
     */
    @RequestMapping(value = "/toupload", method = RequestMethod.GET)
    public String toUpload() {
        logger.info("跳转成功！");
        return "page/file/upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "fileName", required = false) MultipartFile file,
                         @RequestParam(value = "fileName", required = false) CommonsMultipartFile commonsfile,
                         HttpServletRequest request,
                         Model model) {
        Map<String, Object> map = HttpUtils.getAllParam(request);

        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();

        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
            logger.info("文件上传成功！");
        } catch (Exception e) {
            logger.error("文件上传失败！");
            e.printStackTrace();
        }
        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName);
        return "page/file/result";
    }

}
