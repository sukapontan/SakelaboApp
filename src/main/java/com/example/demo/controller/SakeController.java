package com.example.demo.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sake/list")
public class SakeController {
	
	/**
	 * 日本酒メニュー編集画面を表示
	 * 
	 * @return 日本酒メニュー一覧画面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String sakeList() {
		return "sake/list";
	}
	
    /**
     * アップロードファイルを格納するディレクトリを作成する
     *
     * @param filePath
     * @return
     */
    private File mkdirs(StringBuffer filePath){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        File uploadDir = new File(filePath.toString(), sdf.format(now));
        // 既に存在する場合はプレフィックスをつける
        int prefix = 0;
        while(uploadDir.exists()){
            prefix++;
            uploadDir =
                    new File(filePath.toString() + sdf.format(now) + "-" + String.valueOf(prefix));
        }

        // フォルダ作成
        uploadDir.mkdirs();

        return uploadDir;
    }

}
