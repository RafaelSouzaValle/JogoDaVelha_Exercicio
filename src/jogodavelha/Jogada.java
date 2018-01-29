package jogodavelha;

import jogodavelha.exceptions.*;

/**
 * Representa um par de lances (Jogada)
 * @author Rafael.Valle
 *
 */
public class Jogada {

	/**
	 * Posi��o da linha na matriz do tabuleiro (eixo i)
	 */
	int i;
	
	/**
	 * Posi��o da coluna na matriz do tabuleiro (eixo j)
	 */
	int j;

	public Jogada(String jogada) throws JogadaInvalidaException {
		parseString(jogada);
	}
	
	/**
	 * @param jogada recebe string do construtor da jogada 
	 * e o converte em dois inteiros.
	 * @throws JogadaInvalidaException Se a jogada n�o for v�lida.
	 */
	private void parseString (String jogada) throws JogadaInvalidaException {
		try {
			String[] tokens = jogada.split(",");
			this.i = Integer.parseInt(tokens[0].trim());
			this.j = Integer.parseInt(tokens[1].trim());
		} catch (Exception e) {
			throw new JogadaInvalidaException("Jogada Inv�lida");
		}
	}
	
	/**
	 * @return o valor do eixo i
	 */
	public int getI() {
		return i;
	}
	
	/** 
	 * @return o valor do eixo j
	 */
	public int getJ() {
		return j;
	}
}
