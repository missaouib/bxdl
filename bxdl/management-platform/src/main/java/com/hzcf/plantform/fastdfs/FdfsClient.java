package com.hzcf.plantform.fastdfs;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;


/**
*<dl>
*<dt>类名：FdfsClient.java</dt>
*<dd>描述: fast工具类</dd>
*<dd>创建时间： 2018年11月21日 下午3:24:44</dd>
*<dd>创建人：tie</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2018年11月21日 下午3:24:44 tie 1.0 1.0 Version
* </pre>
*</dl>
*/
@Configuration
public class FdfsClient {

	@Autowired
	private FastFileStorageClient storageClient;

	/**
	 * 上传文件 适用于所有文件
	 * 
	 * @param file
	 *            文件对象
	 * @return 文件访问地址
	 * @throws IOException
	 */
	public StorePath uploadFile(MultipartFile file) throws IOException {
		StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
				FilenameUtils.getExtension(file.getOriginalFilename()), null);
		return storePath;
	}

	/**
	 * 将一段字符串生成一个文件上传
	 * 
	 * @param content
	 *            文件路径
	 * @param extName
	 *            文件扩展名
	 * @return文件访问地址
	 * @throws FileNotFoundException
	 */
	public StorePath uploadFile(String content, String extName) throws FileNotFoundException {
		File file = new File(content);
		FileInputStream inputStream = new FileInputStream(file);
		StorePath storePath = storageClient.uploadFile(inputStream, file.length(), extName, null);
		return storePath;
	}

	/**
	 * 传图片并同时生成一个缩略图 "JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"
	 * 
	 * @param file
	 *            文件对象
	 * @return 文件访问地址
	 * @throws IOException
	 */
	public StorePath uploadImageAndCrtThumbImage(MultipartFile file) throws IOException {
		StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
				FilenameUtils.getExtension(file.getOriginalFilename()), null);
		return storePath;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileUrl
	 *            文件访问地址
	 * @return
	 * @throws IOException
	 */
	public void deleteFile(String storePath) {
		if (!StringUtils.hasText(storePath)) {
			throw new NullPointerException();
		}
		storageClient.deleteFile(storePath);
	}

    /**
     * 文件下载 返回byte[]
     * @param groupName
     * @param path
     * @return
     */
	public byte[] downloadByteArray(String groupName, String path){
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = storageClient.downloadFile(groupName, path, downloadByteArray);
        return bytes;
    }


    /**
     * 下载文件
     *
     * @param response
     * @param filePath 文件路径
     * @param fileName 设置文件名
     * @param fileType 设置格式
     */
    public void downLoadFile(String filePath, HttpServletResponse response, String fileName) throws IOException {
		OutputStream toClient = null;
        try {
            URL url = new URL(filePath);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            byte[] buffer = new byte[inStream.available()];
            // 下载网络文件
            int bytesum = 0;
            int byteread = 0;
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("content-disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("multipart/form-data");
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                toClient.write(buffer, 0, byteread);
            }
            inStream.close();
            toClient.flush();
            toClient.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if (null != toClient) {
				toClient.close();
			}
		}
    }

    public void downLoadFile(String filePath, HttpServletResponse response, String fileName, String fileType) throws IOException {
		OutputStream toClient = null;
        try {
            URL url = new URL(filePath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(30*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            //得到输入流
            InputStream inStream = conn.getInputStream();
            byte[] buffer = new byte[inStream.available()];
            int bytesum = 0;
            int byteread = 0;

            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("content-disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName + "." + fileType, "UTF-8"));
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("multipart/form-data");
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                toClient.write(buffer, 0, byteread);
            }
            inStream.close();
            toClient.flush();
            toClient.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if(null != toClient) {
				toClient.close();
			}
		}
    }
}
