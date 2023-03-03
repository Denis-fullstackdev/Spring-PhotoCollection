package com.corsojava.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corsojava.springboot.model.Categoria;
import com.corsojava.springboot.repository.CategoriaRepository;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping()
	public String index(Model model) {
		List<Categoria> elencoCategorie = categoriaRepository.findAll();
		model.addAttribute("elencoCategorie", elencoCategorie);
		return "/categorias/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Categoria categoria=new Categoria();	
		model.addAttribute("categoria", categoria);
		return "/categorias/create";
	}
	
	@PostMapping("/create")
	public String store(@ModelAttribute("categoria") Categoria formCategoria, Model model) {	
		categoriaRepository.save(formCategoria);
		return "redirect:/categorias";	
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Optional<Categoria> categoria= categoriaRepository.findById(id);
		if(categoria.isEmpty()) {
			return "redirect:/categorias";
		}
		model.addAttribute("categoria",categoria.get());
		return "categorias/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String update(@ModelAttribute("categoria") Categoria formCategoria, Model model) {
		categoriaRepository.save(formCategoria);
		return "redirect:/categorias";	
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		categoriaRepository.deleteById(id);
		return "redirect:/categorias";
	}

}
