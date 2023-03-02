package com.corsojava.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.springboot.model.Foto;
import com.corsojava.springboot.repository.FotoRepository;

@Controller
@RequestMapping("/fotos")
public class FotoController {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@GetMapping
	public String index(@RequestParam(name="keyword", required=false) String keyword, Model model) {
		List<Foto> filtro;
		
		if ( keyword == null || keyword.isEmpty() )
			filtro = fotoRepository.findAll();
		else
			filtro = fotoRepository.findByTitoloLike("%" + keyword + "%");
		
		model.addAttribute("fotos", filtro);
		
		return "/fotos/index";
	}

}
