package com.corsojava.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.springboot.model.Foto;

public interface FotoRepository extends JpaRepository<Foto, Integer> {
	
	public List<Foto> findByTitoloLike(String keyword);
	public List<Foto> findByTagLike(String keyword);

}