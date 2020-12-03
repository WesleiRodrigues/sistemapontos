package com.sistema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.models.CadastroPessoas;
import com.sistema.repository.CadastroPessoasRepository;

@Controller
@ComponentScan
public class EnvioMensagensController {
	@Autowired
	private CadastroPessoasRepository cp;
	
	@RequestMapping("/enviomensagens")
	public ModelAndView carregaMensagens() {
		
		ModelAndView mv = new ModelAndView("views/formEnviaMensagem");
		Iterable<CadastroPessoas> pessoas = cp.findAll();
				
		mv.addObject("registroPessoas", pessoas);
		return mv;
	}
}
