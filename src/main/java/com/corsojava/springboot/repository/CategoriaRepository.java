package com.corsojava.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.springboot.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}