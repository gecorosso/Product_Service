package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entities.Prodotti;
import com.example.demo.repository.ProdottiRepository;
import com.example.demo.services.ProdottiServices;
import com.example.demo.services.ProdottiServicesImpl;

	@ExtendWith(SpringExtension.class) // Per testare con JUnit 5
	@ExtendWith(MockitoExtension.class) // Per testare con mokito
	@SpringBootTest
	public class Product_ServiceTest {
		@Mock
		private ProdottiRepository prodottiRepository; // Crea un mock del repository
	
		@InjectMocks
		private ProdottiServicesImpl prodottiServices; // Inietta il mock nel service
	
		private Prodotti prodotto;
	
		@BeforeEach
		void setUp() {
			// Oggetto di test
			// id,descrizione,categoria
	
			prodotto = new Prodotti(1, "Pantaloni Uomo", "Pantaloni");
	
			// Mock
	
			Mockito.when(prodottiRepository.findById(1)).thenReturn(prodotto);
			Mockito.when(prodottiRepository.save(prodotto)).thenReturn(prodotto);
			Mockito.when(prodottiRepository.findAll()).thenReturn(Arrays.asList(prodotto));
			Mockito.doNothing().when(prodottiRepository).deleteById(1);
		}
		
		@Test 
		void testFindById() { 
			Prodotti prod = prodottiServices.findById(1);
			assertEquals("Pantaloni", prod.getCategoria());
			assertEquals("Pantaloni Uomo", prod.getDescrizione()); 
		}	 
	
		@Test
		void testSaveProdotti() {
			Prodotti prod = prodottiServices.saveProdotti(prodotto);
			assertEquals("Pantaloni", prod.getCategoria());
			assertEquals("Pantaloni Uomo", prod.getDescrizione());
		}
	
		@Test
		void testFindAll() {
			List<Prodotti> prod = (List<Prodotti>) prodottiServices.findAll();
			assertEquals(1, prod.size());
			assertEquals("Pantaloni", prod.get(0).getCategoria());
			assertEquals("Pantaloni Uomo", prod.get(0).getDescrizione());
		}
	
		@Test
		void testDeleteProdotti() {
			prodottiServices.deleteProdotti(1);
			Mockito.verify(prodottiRepository, Mockito.times(1)).deleteById(1);
		}
	}
