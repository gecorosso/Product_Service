 package com.example.demo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entities.Prodotti;

@Service
@Transactional
public interface ProdottiServices  {
	  
	public Optional<Prodotti> findById(int id);
	
	public Prodotti saveProdotti(Prodotti prodotti);	
	public Iterable<Prodotti> findAll();
	public void deleteProdotti(int id);
	public boolean existsById(int id);
}	
