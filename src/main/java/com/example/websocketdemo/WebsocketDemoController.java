package com.example.websocketdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/socketdemo")
public class WebsocketDemoController {

	@RequestMapping("/webpage")
	public String webPage() {
		return "websocketdemo";
	}

	@RequestMapping("/error")
	public String errorPage() {
		return "error";
	}
}
