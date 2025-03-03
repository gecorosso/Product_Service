package com.example.demo.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Prodotti;


public interface ProdottiDao extends JpaRepository<Prodotti, Integer> {
	//custom 
	Optional<Prodotti> findById(Integer id);
	
	
	
}
