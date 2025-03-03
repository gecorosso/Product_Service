package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Prodotti;

public interface ProdottiRepository extends JpaRepository<Prodotti, Integer> {
	public Prodotti findById(int id);
	
	
}
