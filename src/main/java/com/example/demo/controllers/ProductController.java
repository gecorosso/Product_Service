package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {
		@GetMapping("/hello")
		@ResponseBody
		public String SalutaHello() {
	        return "Saluta tua sorella! quella zoccola";
	
		}
}