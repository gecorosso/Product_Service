package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Prodotti;
import com.example.demo.repository.ProdottiRepository;

@Service
public class ProdottiServicesImpl implements ProdottiServices {
	@Autowired
	ProdottiRepository prodottiRepository;
	@Override
	public Prodotti findById(int id) {
		return prodottiRepository.findById(id);
	}
	
	@Override
	public Prodotti saveProdotti(Prodotti prodotti) {
		return prodottiRepository.save(prodotti);
	}

	@Override
	public Iterable<Prodotti> findAll() {
		return prodottiRepository.findAll();
	}	

}
