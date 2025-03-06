package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Prodotti;
import com.example.demo.services.ProdottiServices;


@RestController
@RequestMapping("/prodotti") 
public class ProductController {
		
		@Autowired
		private ProdottiServices prodottiServices;
		
		@GetMapping(value = "/message")
		public String getMessage() {
			return "Hello from the server! Spring Boot";
		}
		
		@GetMapping(value = "/getAll", produces = "application/json")
		public ResponseEntity<Iterable<Prodotti>> getProdotti() {
			Iterable<Prodotti> prodotti = prodottiServices.findAll();
			return new ResponseEntity<>(prodotti, HttpStatus.OK);
		}		
		
		@GetMapping(value = "getProdottiById/{id}", produces = "application/json") 
		public ResponseEntity<Prodotti> getProdottiById(@PathVariable("id") Integer id) {
			Prodotti prodotti = prodottiServices.findById(id);
			 
			    if (prodotti != null) {
		            return new ResponseEntity<>(prodotti, HttpStatus.OK);
		        } else {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		 }
		
		@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	    public ResponseEntity<Prodotti> createProdotto(@RequestBody Prodotti prodotto) {
			Prodotti nuovoProdotto = prodottiServices.saveProdotti(prodotto);
	        return new ResponseEntity<>(nuovoProdotto, HttpStatus.CREATED);
	    }
		
		@DeleteMapping(value = "/delete/{id}")
		public ResponseEntity<HttpStatus> deleteProdotto(@PathVariable("id") Integer id) {
			prodottiServices.deleteProdotti(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
		
		
		
	
}