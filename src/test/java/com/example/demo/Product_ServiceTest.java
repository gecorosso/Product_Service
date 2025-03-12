package com.example.demo;

import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entities.Prodotti;
import com.example.demo.repository.ProdottiRepository;
import com.example.demo.services.ProdottiServicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

@ExtendWith(SpringExtension.class) // For testing with JUnit 5
@ExtendWith(MockitoExtension.class) // For testing with Mockito
@SpringBootTest
@MockitoSettings(strictness = Strictness.LENIENT) //meno rigido nel segnalare problemi 
public class Product_ServiceTest {
    @Mock
    private ProdottiRepository prodottiRepository; // Create a mock of the repository

    @InjectMocks
    private ProdottiServicesImpl prodottiServices; // Inject the mock into the service

    private Optional<Prodotti> prodotto;

    @BeforeEach
    void setUp() {
        // Test object
        // id, description, category
        prodotto = Optional.of(new Prodotti(1, "Pantaloni Uomo", "Pantaloni"));

        // Mock
        Mockito.when(prodottiRepository.findByCategoria("Pantaloni")).thenReturn(Arrays.asList(prodotto.get()));
        Mockito.when(prodottiRepository.findById(1)).thenReturn(prodotto);
        Mockito.when(prodottiRepository.save(prodotto.get())).thenReturn(prodotto.get());
        Mockito.when(prodottiRepository.findAll()).thenReturn(Arrays.asList(prodotto.get()));
        Mockito.doNothing().when(prodottiRepository).deleteById(1);
    }

    @Test
    void testFindById() {
        Optional<Prodotti> prod = prodottiServices.findById(1);
        assertTrue(prod.isPresent());
        assertEquals("Pantaloni", prod.get().getCategoria());
        assertEquals("Pantaloni Uomo", prod.get().getDescrizione());
    }

    @Test
    void testSaveProdotti() {
        Prodotti prod = prodottiServices.saveProdotti(prodotto.get());
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
    
    @Test
	void testSearchProdotti() {
		List<Prodotti> prod = prodottiServices.findByCategoria("Pantaloni");
		assertEquals(1, prod.size());
		assertEquals("Pantaloni", prod.get(0).getCategoria());
		assertEquals("Pantaloni Uomo", prod.get(0).getDescrizione());
	}
}