package com.sistema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sistema.models.CadastroPessoas;

@Controller
@ComponentScan()
public class DepartamentoFuncaoController {
	
	@Autowired
	private DepartamentoFuncaoController dp;
	
	@RequestMapping(value="/departamentofuncao", method=RequestMethod.GET)
	public String departamentoFuncao() {
		return "views/formCadastroDepartamentoFuncao";
	}
	
	@RequestMapping(value="/departamento", method=RequestMethod.POST,params="action=departamento")
	public String formSalvarDepartamento(Departamento departamento) {
		
	}
	public String formEnviaCadastroPessoa(CadastroPessoas cadastro) {
		cp.save(cadastro);
		return "redirect:/pessoas";
	}

	
	@RequestMapping(value="/departamento", method=RequestMethod.POST,params="action=funcao")
	public ModelAndView salvarFuncao() {
		
	}
}

