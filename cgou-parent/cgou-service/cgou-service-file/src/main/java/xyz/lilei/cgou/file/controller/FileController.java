package xyz.lilei.cgou.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.lilei.cgou.file.client.FastDFSClient;
import xyz.lilei.cgou.file.entity.FastDFSFile;

/**
 * @ClassName FileController
 * @Description TODO
 * @Author lilei
 * @Date 02/07/2020 21:32
 * @Version 1.0
 **/
@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {



    /***
     * 文件上传
     * @return
     */
    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        //封装一个FastDFSFile
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(), //文件名字
                file.getBytes(),            //文件字节数组
                StringUtils.getFilenameExtension(file.getOriginalFilename()));//文件扩展名

        //文件上传
        String[] uploads = FastDFSClient.upload(fastDFSFile);
        //组装文件上传地址
        return FastDFSClient.getTrackerUrl()+"/"+uploads[0]+"/"+uploads[1];
    }


}
