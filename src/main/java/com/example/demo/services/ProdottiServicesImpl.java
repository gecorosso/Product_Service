package com.example.demo.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Prodotti;
import com.example.demo.repository.ProdottiRepository;

@Service
public class ProdottiServicesImpl implements ProdottiServices {
	@Autowired
	ProdottiRepository prodottiRepository;
	
	@Override
    public Optional<Prodotti> findById(int id) {
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

	@Override
	public void deleteProdotti(int id) {
		prodottiRepository.deleteById(id);
	}

	@Override
	public boolean existsById(int id) {
		return prodottiRepository.existsById(id);
	}

	
		

}
