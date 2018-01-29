package jogodavelha.pontuacao;

import jogodavelha.exceptions.PontucacaoException;

/**
 * Interface usada no gerenciamento dos pontos.
 * A l�gica de leitura e grava��o dos pontos
 * deve ser introduzida nas classes que implementam esta interface
 * @author Rafael.Valle
 *
 */
public interface GerenciadorPontuacao {

	/**
	 * Retorna pontua��o de um jogador
	 * @param nome refere-se ao nome do jogador
	 * @return Pontua��o do jogador inserido como par�metro
	 */
	public Integer getPontuacao(String nome);
	
	/**
	 * Incrementa pontua��o do jogador
	 * @param nome � o nome do jogador que recebeu o ponto
	 */
	public void gravarPontuacao(String nome) throws PontucacaoException;
}
