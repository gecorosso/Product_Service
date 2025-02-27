package com.example.demo.entities;



//JPA
//JSR 303

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.*;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor 
@Table(name="prodotti")
public class Prodotti {
	@Id
	@Column(name="ID_PRODOTTI")
	@NotEmpty
	@NotNull
	@NotBlank
	@Getter @Setter 
	private Integer id_prodotti;
	
	@Column(name="DESCRIZIONE")
	@NotEmpty
	@NotNull
	@NotBlank
	@Getter @Setter 
	private String descrizione;
	
	@Column(name="CATEGORIA")
	@NotEmpty
	@NotNull
	@NotBlank
	@Getter @Setter 
	private String categoria;
	
}
