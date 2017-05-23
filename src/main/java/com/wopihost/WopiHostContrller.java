package com.wopihost;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wopihost.entity.FileInfo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * WOPI HOST
 * Created by admin on 2017/4/15.
 */
@Controller
@RequestMapping(value="/wopi")
public class WopiHostContrller {

    @Value("${file.path}")
    private String filePath;

    /**
     * 获取文件流
     * @param name
     * @param response
     */
    @RequestMapping(value="/files/{name}/contents", method= RequestMethod.GET)
    public void getFile(@PathVariable(name = "name") String name, HttpServletResponse response) {
        InputStream fis = null;
        OutputStream toClient = null;
        try {
            // 文件的路径
            String path = filePath + name;
            File file = new File(path);
            // 取得文件名
            String filename = file.getName();
            // 以流的形式下载文件
            fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // 清空response
            response.reset();

            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("utf-8"), "ISO-8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fis.close();
                toClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存更新文件
     * @param name
     * @param content
     */
    @RequestMapping(value="/files/{name}/contents", method= RequestMethod.POST)
    public void postFile(@PathVariable(name = "name") String name, @RequestBody byte[] content) {
        // 文件的路径
        String path = filePath + name;
        File file = new File(path);
        FileOutputStream fop = null;
        try {
            if (!file.exists()) {
                file.createNewFile();//构建文件
            }
            fop = new FileOutputStream(file);
            fop.write(content);
            fop.flush();
            System.out.println("------------ save file ------------ ");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fop.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取文件信息
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/files/{name}")
    @ResponseBody
    public void getFileInfo(@PathVariable(name = "name") String name, HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        FileInfo info = new FileInfo();
        try {
            // 获取文件名
            String fileName = URLDecoder.decode(uri.substring(uri.indexOf("wopi/files/") + 11, uri.length()), "UTF-8");
            if (fileName != null && fileName.length() > 0) {
                String path = filePath + fileName;
                File file = new File(path);
                if (file.exists()) {
                    // 取得文件名
                    info.setBaseFileName(file.getName());
                    info.setSize(file.length());
                    info.setOwnerId("admin");
                    info.setVersion(file.lastModified());
                    info.setSha256(getHash256(file));
                    info.setAllowExternalMarketplace(true);
                    info.setUserCanWrite(true);
                    info.setSupportsUpdate(true);
                    info.setSupportsLocks(true);
                }
            }

            ObjectMapper mapper = new ObjectMapper();
            String Json =  mapper.writeValueAsString(info);
            response.getWriter().write(Json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件的SHA-256值
     * @param file
     * @return
     */
    public static String getHash256(File file) {
        String value = "";
        // 获取hash值
        try {
            byte[] buffer = new byte[1024];
            int numRead;
            InputStream fis = new FileInputStream(file);
            //如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
            MessageDigest complete = MessageDigest.getInstance("SHA-256");
            do {
                //从文件读到buffer
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    //用读到的字节进行MD5的计算，第二个参数是偏移量
                    complete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);

            fis.close();
            value = new String(Base64.encodeBase64(complete.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

}
