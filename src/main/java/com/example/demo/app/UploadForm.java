package com.example.demo.app;

import org.springframework.web.multipart.MultipartFile;

public class UploadForm {
	private MultipartFile multipartFile;

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
}
