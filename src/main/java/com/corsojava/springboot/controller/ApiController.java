package com.corsojava.springboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corsojava.springboot.model.Foto;
import com.corsojava.springboot.repository.FotoRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	
	@GetMapping
	public ResponseEntity<List<Foto>> index(@RequestParam Map<String,String> requestParams, Model model) {
		List<Foto> filtro;
		
		String keyword=requestParams.get("keyword");
		String fieldFilter=requestParams.get("fieldFilter");
		
		if ( keyword == null || keyword.isEmpty() )
			filtro = fotoRepository.findAll();
		else
			if (fieldFilter.equals("titolo"))
				filtro = fotoRepository.findByTitoloLike("%" + keyword + "%");
			else
				filtro = fotoRepository.findByTagLike("%" + keyword + "%");
		
		if (filtro.size() > 0)
			return new ResponseEntity<List<Foto>>(filtro, HttpStatus.OK);
		else
			return new ResponseEntity<List<Foto>>(HttpStatus.NO_CONTENT);
		
	}

}
