package com.hzcf.plantform.config;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;


@Component
public class FastDFSClientWrapper {
    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private FdfsClientConfig appConfig;   // 项目参数配置
    /**
     * 普通的文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        StorePath path = storageClient.uploadFile(inputStream, file.length(),FilenameUtils.getExtension(file.getName()), null);
        return getResAccessUrl(path);
    }

//*************************************************************************************
    /**
     * 上传文件
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
        return storePath.getFullPath();
    }

    /**
     * 将一段字符串生成一个文件上传
     * @param content 文件内容
     * @param fileExtension 文件后缀
     * @return
     */
    public String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = storageClient.uploadFile(stream,buff.length, fileExtension,null);
        return storePath.getFullPath();
    }

    // 封装图片完整URL地址
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = "http://" + appConfig.getResHost()
                + ":" + appConfig.getStoragePort() + "/" + storePath.getFullPath();
        return fileUrl;
    }
	
	
	  /**
      * 删除文件
      * @param fileUrl 文件访问地址
      * @return
      */
 /*    public void deleteFile(String fileUrl) {
         if (StringUtils.isEmpty(fileUrl)) {
             return;
         }
         try {
             StorePath storePath = StorePath.parseFromUrl(fileUrl);
             storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
         } catch (FdfsUnsupportStorePathException ignored) {
         }
     }*/
 
     /**
      * 下载文件
      * @param fileUrl 文件url
      * @return
      */
     public byte[]  download(String fileUrl) {
         String group = fileUrl.substring(0, fileUrl.indexOf("/"));
         String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
         byte[] bytes = storageClient.downloadFile(group, path, new DownloadByteArray());
         return bytes;
     }
 
 
}