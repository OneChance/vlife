package com.vlife.gm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class GameController {

	@RequestMapping("")
	public String main(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return "main";
	}

}