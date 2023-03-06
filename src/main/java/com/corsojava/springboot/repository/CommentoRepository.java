package com.corsojava.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.springboot.model.Commento;

public interface CommentoRepository extends JpaRepository<Commento, Integer> {

}
