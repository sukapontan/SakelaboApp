package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Information;
import com.example.demo.repository.InformationRepository;


/**
 * インフォメーション Service
 */
@Service
public class InformationService {
	
	/**
	 * インフォメーション Repository
	 */
	@Autowired
	private InformationRepository informationRepository;
	
	/**
	 * インフォメーション 全検索
	 * @return 検索結果一覧
	 */
	public List<Information> searchAll() {
		return (List<Information>) informationRepository.informationListALL();
	}
	
	/**
	 * インフォメーション 件数制限検索
	 * @return 検索結果一覧(5件)
	 */
	public List<Information> searchTopInfo() {
		return (List<Information>) informationRepository.informationListTop();
	}
	
	/**
	 * インフォメーション 新規登録
	 * @return なし
	 */
	public void register(Information information, UserDetails userDetails) {
		
		// 必要なパラメータをインフォメーションEntityから設定
		String title = information.getTitle();
		String cont = information.getCont();
		String ins_user = userDetails.getUsername();
		
		// リポジトリにて、独自のSQLを実行
		informationRepository.registerInformation(title, cont, ins_user);
		return;
	}
	
	/**
	 * インフォメーション更新 
	 * @return インフォメーションオブジェクト
	 */
	public Information updateInformation(Information information, UserDetails userDetails) {
		
		Long information_id = information.getInformation_id();
		String title = information.getTitle();
		String cont = information.getCont();
		String upd_user = userDetails.getUsername();
		
		informationRepository.updateInformation(information_id, title, cont, upd_user);
		return information;
	}
	
	
	/**
	 * インフォメーション削除
	 * @return インフォメーションオブジェクト 
	 */
	public Optional<Information> deleteInformation(Long id) {
		Optional<Information> information = informationRepository.findById(id);
		informationRepository.deleteById(id);
		return information;
	}

}
