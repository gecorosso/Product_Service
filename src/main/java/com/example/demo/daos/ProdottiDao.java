package com.example.demo.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Prodotti;


public interface ProdottiDao extends JpaRepository<Prodotti, Integer> {
	//custom 
	Optional<Prodotti> findById(Integer id);
	
	Optional<Prodotti> findByDescrizione(String descrizione);
	Optional<Prodotti> findByCategoria(String categoria);
	
	void save(Optional<Prodotti> prodotto);
	void deleteById(Integer id);
	
}
