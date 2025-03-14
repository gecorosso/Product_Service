package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Prodotti;

import java.util.List;
import java.util.Optional;

public interface ProdottiRepository extends CrudRepository<Prodotti, Integer> {
	public Optional<Prodotti> findById(int id);
	@Query("SELECT p FROM Prodotti p WHERE LOWER (p.categoria) LIKE %:keyword% OR "
			+ "LOWER (p.descrizione) LIKE %:keyword%")
	List<Prodotti> findByCategoriaOrDescrizioneIgnoreCase(@Param("keyword") String keyword);	
	
}
