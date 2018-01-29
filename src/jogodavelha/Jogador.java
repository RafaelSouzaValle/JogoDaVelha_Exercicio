package jogodavelha;

import jogodavelha.exceptions.*;

/**
 * Representa um jogador
 * @author Rafael.Valle
 *
 */
public class Jogador {

	/**
	 * Nome do Jogador
	 */
	private String name;
	
	/**
	 * Símbolo do jogador
	 */
	private char simbolo;

	/**
	 * Construtor do jogador
	 * @param name
	 * @param simbolo
	 */
	public Jogador(String name, char simbolo) {
		this.name = name;
		this.simbolo = simbolo;
	}

	//Retorne o nome
	public String getName() {
		return name;
	}

	//Retorna o símbolo
	public char getSimbolo() {
		return simbolo;
	}
	
	/**
	 * Lê jogada inserida pelo teclado
	 * @return Jogada realizada
	 * @throws JogadaInvalidaException
	 */
	public Jogada obterJogada() throws JogadaInvalidaException {
		String entrada = jogodavelha.io.Console.leString();
		return new Jogada(entrada);
	}
}
