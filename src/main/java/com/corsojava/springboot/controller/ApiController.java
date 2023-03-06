package com.corsojava.springboot.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corsojava.springboot.model.Commento;
import com.corsojava.springboot.model.Foto;
import com.corsojava.springboot.repository.CommentoRepository;
import com.corsojava.springboot.repository.FotoRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@Autowired
	private CommentoRepository commentoRepository;	
	
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
	
	@GetMapping("{id}")
	public ResponseEntity<Foto> show(@PathVariable("id") Integer id) {
		Optional<Foto> result = fotoRepository.findById(id);
		if (result.isPresent()) {
			return new ResponseEntity<Foto>(result.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Foto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("{id}/create")
	public ResponseEntity<String> create(@PathVariable("id") Integer id, @RequestBody Commento commento) {
		Optional<Foto> res = fotoRepository.findById(id);
		if(!res.isPresent())
			return ResponseEntity.notFound().build();
		else {
			Foto foto = res.get();
			commento.setFoto(foto);
			commentoRepository.save(commento);
			return ResponseEntity.ok("OK invio commento");
		}
	}

}
