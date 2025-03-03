package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entities.Prodotti;

@Service
@Transactional
public interface ProdottiServices  {
	
	public Prodotti findById(int id);
	public Prodotti saveProdotti(Prodotti prodotti);
}	
