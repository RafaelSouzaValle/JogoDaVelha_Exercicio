package jogodavelha.pontuacao;

import jogodavelha.exceptions.PontucacaoException;

/**
 * Interface usada no gerenciamento dos pontos.
 * A lógica de leitura e gravação dos pontos
 * deve ser introduzida nas classes que implementam esta interface
 * @author Rafael.Valle
 *
 */
public interface GerenciadorPontuacao {

	/**
	 * Retorna pontuação de um jogador
	 * @param nome refere-se ao nome do jogador
	 * @return Pontuação do jogador inserido como parâmetro
	 */
	public Integer getPontuacao(String nome);
	
	/**
	 * Incrementa pontuação do jogador
	 * @param nome é o nome do jogador que recebeu o ponto
	 */
	public void gravarPontuacao(String nome) throws PontucacaoException;
}
