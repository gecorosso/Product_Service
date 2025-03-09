package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Prodotti;
import java.util.Optional;

public interface ProdottiRepository extends CrudRepository<Prodotti, Integer> {
	public Optional<Prodotti> findById(int id);
}
