package com.sp.web;

import com.sp.entity.SysArea;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @version 1.0.0
 * @ClassName SysUserController
 * @Description 区域管理控制器
 * @author:Gaoxueyong http://blog.csdn.net/fenfenguai
 * @Date 2016年11月14日 下午5:42:03
 */
@Controller
@RequestMapping(value = "/sys/file/")
public class SysFileController {


    /**
     * @param request
     * @param response
     * @return
     * @Description
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年11月15日 下午2:21:27
     */
    @RequestMapping(value = "manage")
    public String manage(HttpServletRequest request, HttpServletResponse response, Model model, SysArea sysArea) {


        return "sys/file/sysFileManage";
    }

    @RequestMapping(value = "upload")
    @ResponseBody
    public void upload(HttpServletRequest req) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        MultipartHttpServletRequest request = multipartResolver.resolveMultipart(req);
        String guid = request.getParameter("guid");
        MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();

        MultipartFile multipartFile = multiFileMap.getFirst("file");
        try {
            InputStream in = multipartFile.getInputStream();

            String chunk = request.getParameter("chunk") == null ? "0" : request.getParameter("chunk");
            String path = "D:\\bigFile" + File.separator + request.getParameter("id") + File.separator + guid;
            File exist = new File(path);
            if (!exist.exists()) {
                exist.mkdirs();
            }
            OutputStream out = new FileOutputStream(path + File.separator + chunk + "_" + request.getParameter("name"));
            byte[] bytes = new byte[1024];
            int len = -1;

            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            out.close();
            in.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件合并
     *
     * @param guid
     * @param realFileName
     * @param chunks
     * @param folder
     */
    @RequestMapping(value = "merge", method = RequestMethod.POST)
    @ResponseBody
    public void merge(String guid, String realFileName, int chunks, String folder) {


        String path = "D:\\bigFile" + File.separator + folder + File.separator + guid;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path + File.separator + realFileName);
            byte[] buf = new byte[1024];
            for (int i = 0; i < chunks; i++) {
                File tempFile = new File(path + File.separator + i + "_" + realFileName);
                InputStream inputStream = new FileInputStream(tempFile);
                int len = 0;
                while ((len = inputStream.read(buf)) != -1) {
                    fileOutputStream.write(buf, 0, len);
                }
                inputStream.close();
                //删除临时文件
                tempFile.delete();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
