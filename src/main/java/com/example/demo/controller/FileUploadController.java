package com.example.demo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.form.UploadForm;

/***
 * ファイルアップロードのコントローラークラス
 */
@Controller
public class FileUploadController {

    /**
     * 日本酒メニューアップロード画面遷移処理
     * @return
     */
	@RequestMapping(method = RequestMethod.GET, path="/admin/sake/upload")
    String sakeIndex(Model model) {
        //アップロードファイルをモデルにセット
        model.addAttribute("uploadForm", new UploadForm());

        //アップロード画面に遷移
        return "/sake/upload";
    }

    /**
     * 日本酒メニューファイルアップロード処理
     * @param uploadForm
     * @return
     */
	@RequestMapping(method = RequestMethod.POST, path="/admin/sake/upload")
    String sakeUpload(UploadForm uploadForm, Model model) {
        //フォームで渡されてきたアップロードファイルを取得
        MultipartFile multipartFile = uploadForm.getMultipartFile();

        //アップロード実行処理メソッド呼び出し
        boolean result = uploadAction(multipartFile);
        
        if(result) {
        	//アップロード成功メッセージ設定
        	model.addAttribute("result", "OK");
            model.addAttribute("uploadSuccess", "画像のアップロードに成功しました。");
        } else {
        	model.addAttribute("result", "NG");
        	model.addAttribute("message", "画像のアップロードに失敗しました。");
        }
        

        //リダイレクト
//        return "redirect:/admin/sake/upload";
        
        return "/sake/upload";
    }
	
	/**
     * マニュアルアップロード画面遷移処理
     * @return
     */
	@RequestMapping(method = RequestMethod.GET, path="/admin/workManual/upload")
    String workManualIndex(Model model) {
        //アップロードファイルをモデルにセット
        model.addAttribute("uploadForm", new UploadForm());

        //アップロード画面に遷移
        return "/manual/upload";
    }
	
    /**
     * 業務マニュアルアップロード処理
     * @param uploadForm
     * @return /manual/upload.html
     */
	@RequestMapping(method = RequestMethod.POST, path="/admin/workManual/upload")
    String workManualUpload(UploadForm uploadForm, Model model) {
        //フォームで渡されてきたアップロードファイルを取得
        MultipartFile multipartFile = uploadForm.getMultipartFile();
        
        /* ここでファイルの存在チェックとファイル名のチェックをした方がよいのでは？
        if(multipartFile != null) {
        	// ファイル名取得
            String fileName = multipartFile.getOriginalFilename();
            System.out.println("対象ファイル名：" + fileName);
            if(fileName.equals("workManual.pdf")) {
            	uploadAction(multipartFile);
            }
        } else {
        	model.addAttribute("result", "NG");
        	model.addAttribute("message", "ファイルをアップロードしてください。");
        }
        */

        //アップロード実行処理メソッド呼び出し
        boolean result = uploadAction(multipartFile);
        
        if(result) {
        	model.addAttribute("result", "OK");
            model.addAttribute("message", "画像のアップロードに成功しました。");
        } else {
        	model.addAttribute("result", "NG");
        	model.addAttribute("message", "画像のアップロードに失敗しました。");
        }
        
        //リダイレクト
//        return "redirect:/admin/sake/upload";
        
        return "/manual/upload";
    }
	
	/**
     * 電話対応マニュアルアップロード処理
     * @param uploadForm
     * @return /manual/upload.html
     */
	@RequestMapping(method = RequestMethod.POST, path="/admin/telephoneManual/upload")
    String telephoneManualUpload(UploadForm uploadForm, Model model) {
        //フォームで渡されてきたアップロードファイルを取得
        MultipartFile multipartFile = uploadForm.getMultipartFile();

        //アップロード実行処理メソッド呼び出し
        boolean result = uploadAction(multipartFile);
        
        if(result) {
        	model.addAttribute("result", "OK");
        	//アップロード成功メッセージ設定
            model.addAttribute("message", "画像のアップロードに成功しました。");
        } else {
        	model.addAttribute("result", "NG");
        	model.addAttribute("message", "画像のアップロードに失敗しました。");
        }
        
        //リダイレクト
//        return "redirect:/admin/sake/upload";
        
        return "/manual/upload";
    }
	
	/**
     * 電話対応マニュアルアップロード処理
     * @param uploadForm
     * @return /manual/upload.html
     */
	@RequestMapping(method = RequestMethod.POST, path="/admin/billManual/upload")
    String billManualUpload(UploadForm uploadForm, Model model) {
        //フォームで渡されてきたアップロードファイルを取得
        MultipartFile multipartFile = uploadForm.getMultipartFile();

        //アップロード実行処理メソッド呼び出し
        boolean result = uploadAction(multipartFile);
        
        if(result) {
        	model.addAttribute("result", "OK");
        	//アップロード成功メッセージ設定
            model.addAttribute("message", "画像のアップロードに成功しました。");
        } else {
        	model.addAttribute("result", "NG");
        	model.addAttribute("message", "画像のアップロードに失敗しました。");
        }
        
        //リダイレクト
//        return "redirect:/admin/sake/upload";
        
        return "/manual/upload";
    }
	

    /**
     * アップロード実行処理
     * @param multipartFile
     */
    private boolean uploadAction(MultipartFile multipartFile) {
    	
    	boolean result = true;
    	
        // ファイル名取得
        String fileName = multipartFile.getOriginalFilename();
        Path filePath = null;
        
        // ファイル名によってファイルパスを設定
        if(fileName.equals("sakeMenu.jpg")) {
        	filePath = Paths.get("src/main/resources/static/images/sake/" + fileName);
        } else if(fileName.equals("workManual.pdf")) {
        	filePath = Paths.get("src/main/resources/static/images/manual/" + fileName);
        } else if(fileName.equals("telephoneManual.pdf")) {
        	filePath = Paths.get("src/main/resources/static/images/manual/" + fileName);
        } else if(fileName.equals("billManual.pdf")) {
        	filePath = Paths.get("src/main/resources/static/images/manual/" + fileName);
        } else {	
        	// ファイルが間違えている旨を返却
        	System.out.println("アップロード対象ファイルを確認してください。");
        	result = false;
        	return result;
        }

        try {
            // アップロードファイルをバイト値に変換
            byte[] bytes  = multipartFile.getBytes();

            // バイト値を書き込む為のファイルを作成して指定したパスに格納
            OutputStream stream = Files.newOutputStream(filePath);

            // ファイルに書き込み
            stream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}