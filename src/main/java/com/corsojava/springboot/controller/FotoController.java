package com.corsojava.springboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String index(@RequestParam Map<String,String> requestParams, Model model) {
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
		
		model.addAttribute("fotos", filtro);
		
		return "/fotos/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("foto", fotoRepository.getReferenceById(id));
		return "/fotos/show";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Foto foto = new Foto();	
		model.addAttribute("foto", foto);
		return "/fotos/create";
	}
	
	@PostMapping("/create")
	public String store(@ModelAttribute("foto") Foto formFoto, Model model) {
		fotoRepository.save(formFoto);
		return "redirect:/fotos";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Foto foto = fotoRepository.getReferenceById(id);
		model.addAttribute("foto", foto);
		return "/fotos/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String update(@ModelAttribute("foto") Foto formFoto, Model model) {
		fotoRepository.save(formFoto);
		return "redirect:/fotos";
	}

}
