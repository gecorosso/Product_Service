package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Prodotti;
import com.example.demo.services.ProdottiServices;

@RestController
public class ProductController {
		
		@Autowired
		private ProdottiServices prodottiServices;
	
		@RequestMapping("/cercaProdotto")		
		public ResponseEntity<Prodotti> cercaProdotto(@RequestParam(value="jwt") String jwt) {
	        
			//prodottiServices.cercaProdotto(jwt);
			return null;
	
		}
}