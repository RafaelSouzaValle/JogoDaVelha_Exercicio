package jogodavelha;

import jogodavelha.exceptions.*;

/**
 * 
 * @author Rafael.Valle
 *
 */

/**
 * Tabuleiro do jogo
 */
public class Tabuleiro {

	/**
	 * Matriz que representa o jogo
	 */
	private char[][] matriz;
	
	/**
	 * Construtor
	 */
	public Tabuleiro() {
		//Cria uma matriz de 3 x 3
		matriz = new char[3][3];
		
		//Insere um espaço em cada campo da matriz
		limpar();
	}
	
	/**
	 * Insere espaços em branco em todos os elementos da matriz
	 */
	public void limpar () {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = ' ';
			}
		}
	}
	
	/**
	 * Imprime os elementos no console
	 */
	public void imprimir () {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.print(matriz[i][j]);
				if (j < matriz[0].length - 1) {
					System.out.print(" | ");
				}				
			}
			System.out.println();
			
			if (i < matriz.length - 1) {
				System.out.println("---------");
			}
		}
		System.out.println();
	}
	
	/**
	 * Indica se ainda há elementos da matriz preenchidos com espaço.
	 * @return true se o tabuleiro foi todo preenchido. Caso contrário, retorna false.
	 */
	public boolean isCompleto() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				if (matriz[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Realiza a jogada inserida
	 * @param jogada
	 * @param simbolo
	 * @throws JogadaInvalidaException
	 */
	public boolean efetuarJogada (Jogada jogada, char simbolo) throws JogadaInvalidaException {
		int i = jogada.getI();
		int j = jogada.getJ();
		
		/**
		 * Verifica se a jogada não está além
		 * do limite da matriz
		 */
		if (i < 0 || i > matriz.length || j < 0 || j > matriz.length) {
			throw new JogadaInvalidaException("Jogada inválida");
		}
		
		/**
		 * Verifica se o campo onde a jogada está
		 * sendo efetuada já não foi preenchido
		 * com um símbolo
		 */
		if (matriz[i][j] != ' ') {
			throw new JogadaInvalidaException("Esta jogada já foi efetuada");
		}
		
		/**
		 * Insere o símbolo na posição digitada
		 */
		matriz[i][j] = simbolo;
		
		/**
		 * Verifica se a jogada inserida
		 * completa uma sequência que
		 * encerra o jogo.
		 */
		return isSequenciaCompleta();
	}
	
	/**
	 * Checa se há uma sequência de 3
	 * símbolos iguais.
	 * @return true se a sequência de 3 sómbolos encerra o jogo. Do contratário, false.
	 */
	private boolean isSequenciaCompleta () {
			
		if (matriz[0][0] == matriz[0][1] && matriz[0][1] == matriz[0][2] && matriz[0][0] != ' ') {
			return true;
		}
		
		if (matriz[1][0] == matriz[1][1] && matriz[1][1] == matriz[1][2] && matriz[1][0] != ' ') {
			return true;
		}
		
		if (matriz[2][0] == matriz[2][1] && matriz[2][1] == matriz[2][2] && matriz[2][0] != ' ') {
			return true;
		}
		
		if (matriz[0][0] == matriz[1][1] && matriz[1][1] == matriz[2][2] && matriz[0][0] != ' ') {
			return true;
		}
		
		if (matriz[0][2] == matriz[1][1] && matriz[1][1] == matriz[2][0] && matriz[0][2] != ' ') {
			return true;
		}
		
		if (matriz[0][1] == matriz[1][1] && matriz[1][1] == matriz[2][1] && matriz[0][1] != ' ') {
			return true;
		}
		
		if (matriz[0][2] == matriz[1][2] && matriz[1][2] == matriz[2][2] && matriz[0][2] != ' ') {
			return true;
		}
		
		return false;
	}

}
