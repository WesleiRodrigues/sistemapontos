package com.sistema.repository;

import org.springframework.data.repository.CrudRepository;

import com.sistema.models.CadastroPessoas;
import com.sistema.models.RegistroPontos;

public interface RegistroPontosRepository extends CrudRepository<RegistroPontos, String>{
	Iterable<RegistroPontos> findByPessoa(CadastroPessoas pessoa);
}
