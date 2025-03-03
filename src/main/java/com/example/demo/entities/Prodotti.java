package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prodotti")
@Getter @Setter
public class Prodotti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIZIONE", nullable = false)
    @NotBlank
    private String descrizione;

    @Column(name = "CATEGORIA", nullable = false)
    @NotBlank
    private String categoria;
}
