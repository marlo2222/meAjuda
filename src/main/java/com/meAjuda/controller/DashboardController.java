package com.meAjuda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
	
	
	@GetMapping("/home")
	public ModelAndView homeDashboard() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dashboard/index");
		return mv;
	}

}
