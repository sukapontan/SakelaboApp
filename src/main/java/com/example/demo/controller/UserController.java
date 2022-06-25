package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.app.PasswordResetForm;
import com.example.demo.app.SignupForm;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserDetailServiceImpl;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	/**
	 * ユーザー情報 Service
	 */
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
    private UserDetailServiceImpl userDetailsServiceImpl;

	/**
	 * ユーザー情報一覧画面を表示
	 * 
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@GetMapping(value = "/user/list")
	public String displayList(Model model) {
		List<User> userlist = userService.searchAll();
		model.addAttribute("userlist", userlist);
		return "user/list";
	}
	
	/**
	 * 新規ユーザー登録画面を表示
	 * 
	 * @return 新規ユーザー登録画面
	 */
	@GetMapping("/user/signup")
    public String newSignup(SignupForm signupForm) {
        return "user/signup";
    }
    
    /**
     * ユーザー登録フォーム画面から受け取った値から画面遷移を分岐する
     * @return エラーだった場合、登録画面へのパス
     * 			
     */
    @PostMapping("/user/signup")
    public String signup(@Validated SignupForm signupForm, BindingResult result, Model model, HttpServletRequest request) {
    	
    	if (result.hasErrors()) {
            return "user/signup";
        }
    	
    	if (userDetailsServiceImpl.isExistUser(signupForm.getUsername())) {
            model.addAttribute("signupError", "ユーザー名 " + signupForm.getUsername() + "は既に登録されています");
            return "user/signup";
        }
    	
        try {
            userDetailsServiceImpl.register(signupForm.getUsername(), signupForm.getPassword(), "ROLE_USER");
        } catch (DataAccessException e) {
            model.addAttribute("signupError", "ユーザー登録に失敗しました");
            return "user/signup";
        }
        
        /*
        // ユーザー登録後、一度ログアウト
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken == false) {
            SecurityContextHolder.clearContext();
        }

		// 再度ログイン
        try {
            request.login(signupForm.getUsername(), signupForm.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }
        */
        
        model.addAttribute("registerUser", signupForm.getUsername());
        return "user/signupSuccess";
//        return "redirect:/";
    }

	/**
	 * 新規ユーザー登録画面を表示
	 * 
	 * @param model Model
	 * @return 新規ユーザー登録画面
	 */
	@GetMapping(value = "/user/add")
	public String displayAdd(Model model) {
		return "user/add";
	}

	/**
	 * ユーザー情報詳細画面を表示
	 * 
	 * @param id    表示するユーザーID
	 * @param model Model
	 * @return ユーザー情報詳細画面
	*/
	@GetMapping("/user/{id}")
	public String displayView(@PathVariable Long id, Model model) {
		return "user/view";
	}

	/**
	 * ユーザー情報編集画面を表示
	 * @param model Model
	 * @return ユーザー情報編集画面
	*/
	@GetMapping(value = "/user/edit/{id}")
	public String userEdit(Model model,@PathVariable Long id) {
		// 受け取ったIDのユーザー情報を取得
		Optional<User> user = repository.findById(id);
		model.addAttribute("user", user);
	    return "user/edit";
	}
	
	/**
	 * ユーザー情報編集画面を表示
	 * @param model Model
	 * @return ユーザー情報編集画面
	*/
	@PostMapping(value = "/user/update")
	public String userUpdate(Model model, User user) {
		// 受け取ったIDをもとにユーザー情報を更新
		User updateUser = userService.updateUser(user);
		model.addAttribute("updateUser", updateUser);
		return "user/updateSuccess";
	}
	
	/**
	 * ユーザー情報編集画面を表示
	 * @param model Model
	 * @return ユーザー情報編集画面
	*/
	@GetMapping(value = "/user/delete/{id}")
	public String userDelete(@PathVariable Long id) {
		// 受け取ったIDをもとにユーザー情報を削除
		userService.deleteUser(id);
		return "user/deleteSuccess";
	}
	
	/**
	 * パスワード再設定画面を表示
	 * @return パスワード変更画面
	*/
	@GetMapping("/passwordReset")
    public String forgotPassword(PasswordResetForm passwordResetForm) {
        return "user/passwordReset";
    }
	
	/**
	 * パスワード変更
	 * @param model Model
	 * @return パスワード変更完了画面（変更失敗時は入力画面に遷移）
	*/
	@PostMapping("/passwordReset")
    public String passwordReset(@Validated PasswordResetForm passwordResetForm, BindingResult result, Model model, HttpServletRequest request) {
		
		System.out.println("パスワード：" + passwordResetForm.getPassword());
		System.out.println("確認用パスワード：" + passwordResetForm.getPasswordConf());
		System.out.println("ユーザー名：" + passwordResetForm.getUserName());
		
		// ユーザ名からIDを取得する処理が必要
		
		if (result.hasErrors()) {
            return "user/passwordReset";
        }
		// 入力したパスワードと確認用パスワードが一致しているかチェック
		if (!(passwordResetForm.getPassword().equals(passwordResetForm.getPasswordConf()))) {
			model.addAttribute("passwordResetError", "入力したパスワードが一致しません。");
			return "user/passwordReset";
		}
		
		userService.updateUser(passwordResetForm);
		return "user/passwordResetSuccess";
	}
}
