package com.hzcf.plantform.fastdfs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.tobato.fastdfs.domain.StorePath;

@RestController
public class FastController {

	@Autowired
	private FdfsClient dfsClient;

	// 上传图片
	@RequestMapping(value = "/upload")
	public void upload() {
		try {
			// 省略业务逻辑代码。。。
			 StorePath uploadFile = dfsClient.uploadFile("D:\\1.jpg", "jpg");
			System.out.println(uploadFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
