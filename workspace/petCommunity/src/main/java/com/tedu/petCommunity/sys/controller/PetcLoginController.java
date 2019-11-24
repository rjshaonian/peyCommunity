/**
 * 
 */
package com.tedu.petCommunity.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.petCommunity.common.vo.JsonResult;

/**
 * @author 张拂为 2019年11月23日 上午9:27:00
 */
@Controller
public class PetcLoginController {

	@RequestMapping("login")
	public String doLoginUI() {
		return "login";
	}

	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult doLogin(String username, String password, boolean isRememberMe) {
//		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//		if (isRememberMe)
//			token.setRememberMe(true);
//		Subject subject = SecurityUtils.getSubject();
//		subject.login(token);

		return new JsonResult("login ok");
	}

}
