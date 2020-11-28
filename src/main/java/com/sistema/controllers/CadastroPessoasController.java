package com.sistema.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.models.CadastroPessoas;
import com.sistema.models.RegistroPontos;
import com.sistema.repository.CadastroPessoasRepository;
import com.sistema.repository.RegistroPontosRepository;

@Controller
@ComponentScan()
public class CadastroPessoasController {

	@Autowired
	private CadastroPessoasRepository cp;
	
	@Autowired
	private RegistroPontosRepository rp;
	
	@RequestMapping(value="/cadastroPessoas", method=RequestMethod.GET)
	public ModelAndView listaPessoasNoCadastro() {
		ModelAndView mv = new ModelAndView("views/formCadastroPessoas");
		Iterable<CadastroPessoas> registro = cp.findAll();
		mv.addObject("registro", registro);
		return mv;
	}
 	
	@RequestMapping(value="/cadastroPessoas", method=RequestMethod.POST)
	public String formEnviaCadastroPessoa(CadastroPessoas cadastro) {
		cp.save(cadastro);
		return "redirect:/pessoas";
	}
	
	@RequestMapping("/pessoas")
	public ModelAndView listaPessoas() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<CadastroPessoas> consulta = cp.findAll();
		mv.addObject("consulta", consulta);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesPontos(@PathVariable("codigo") long codigo) {
		
		//consulta dos dados da pessoa que irá registrar o ponto
		
		//declaração da variável iddapessoapesquisada que irá receber o código de registro da pessoa envolvida
		CadastroPessoas iddapessoapesquisada = cp.findByCodigo(codigo);
		
		//set do objeto ModelAndView através da variável mv, pois, ela que irá ativar e receber os dados do formulário
		ModelAndView mv = new ModelAndView("views/formRegistroPontos");
		mv.addObject("registro",iddapessoapesquisada);

		Iterable<RegistroPontos> pontos = rp.findByPessoa(iddapessoapesquisada);
		mv.addObject("pontos", pontos);
		
		return mv;
	}
	
	//insere registros dentro da tabela
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesRegistroPontos(@PathVariable("codigo") long codigo, RegistroPontos pontos) {
		CadastroPessoas pessoa = cp.findByCodigo(codigo);
		pontos.setPessoa(pessoa);
		pontos.setDtiniciojornada(LocalDateTime.now());
		rp.save(pontos);
		return "redirect:/{codigo}";
	}

/*	//edita registros dentro da tabela
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEditarPontos(@PathVariable("codigo") long codigo, RegistroPontos pontos) {
		CadastroPessoas pessoa = cp.findByCodigo(codigo);
		pontos.setPessoa(pessoa);
		pontos.setDtiniciojornada(LocalDateTime.now());
		rp.save(pontos);
		return "redirect:/{codigo}";
	}*/

}
