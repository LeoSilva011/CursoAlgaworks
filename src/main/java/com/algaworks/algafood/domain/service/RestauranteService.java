package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvarRestaurante(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		if(cozinha == null) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com codigo %d", cozinhaId));
		}
		restaurante.setCozinha(cozinha);
		return restauranteRepository.salvar(restaurante);
		
	}
	
	public Restaurante alterarRestaurante(Long restauranteId, Restaurante restaurante) {
		
		Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId); 
		if(restauranteAtual == null) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de restaurante com codigo %d", restauranteId));
		}
		restaurante.setId(restauranteId);
		return salvarRestaurante(restaurante);
		
	}
	
	
}
