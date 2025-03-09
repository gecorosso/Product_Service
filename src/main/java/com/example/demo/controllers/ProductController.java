package com.example.demo.controllers;

import java.util.Optional;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/prodotti")
@Tag(name = "Prodotti Controller", description = "Gestione dei prodotti") // Titolo del controller
public class ProductController {
		
		@Autowired
		private ProdottiServices prodottiServices;
				
		@GetMapping(value = "/getAll", produces = "application/json")
		@Operation(summary = "Recupera tutti i prodotti", description = "Restituisce un elenco di tutti i prodotti disponibili.")
		public ResponseEntity<Iterable<Prodotti>> getProdotti() {
			Iterable<Prodotti> prodotti = prodottiServices.findAll();
			return new ResponseEntity<>(prodotti, HttpStatus.OK);
		}		
		
		@GetMapping(value = "getProdottiById/{id}", produces = "application/json") 
		@Operation(summary = "Recupera un prodotto", description = "Restituisce un prodotto in base all'id.")
		public ResponseEntity <Prodotti> getProdottiById(@PathVariable("id") Integer id) {
			
			Optional<Prodotti> prodotti = prodottiServices.findById(id);
			 
			    if (prodotti != null) {
		             return new ResponseEntity<>(prodotti.get(), HttpStatus.OK); 
			    } else {
		             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		 }
		
		@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	    @Operation(summary = "Crea un nuovo prodotto", description = "Crea un nuovo prodotto.")
		public ResponseEntity<Prodotti> createProdotto(@RequestBody Prodotti prodotto) {
			Prodotti nuovoProdotto = prodottiServices.saveProdotti(prodotto);
	        return new ResponseEntity<>(nuovoProdotto, HttpStatus.CREATED);
	    }
		
		@DeleteMapping(value = "/delete/{id}")
		@Operation(summary = "Elimina un prodotto", description = "Elimina un prodotto in base all'id.")
		public ResponseEntity<HttpStatus> deleteProdotto(@PathVariable("id") int id) {
			//Verifica se il prodotto esiste
		System.out.println("id: " +id);			
		System.out.println("prodottiServices.existsById(id)" + prodottiServices.existsById(id));	
		
		    if (!prodottiServices.existsById(id)) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			prodottiServices.deleteProdotti(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	
}