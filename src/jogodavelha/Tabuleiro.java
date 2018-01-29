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
		
		//Insere um espa�o em cada campo da matriz
		limpar();
	}
	
	/**
	 * Insere espa�os em branco em todos os elementos da matriz
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
	 * Indica se ainda h� elementos da matriz preenchidos com espa�o.
	 * @return true se o tabuleiro foi todo preenchido. Caso contr�rio, retorna false.
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
		 * Verifica se a jogada n�o est� al�m
		 * do limite da matriz
		 */
		if (i < 0 || i > matriz.length || j < 0 || j > matriz.length) {
			throw new JogadaInvalidaException("Jogada inv�lida");
		}
		
		/**
		 * Verifica se o campo onde a jogada est�
		 * sendo efetuada j� n�o foi preenchido
		 * com um s�mbolo
		 */
		if (matriz[i][j] != ' ') {
			throw new JogadaInvalidaException("Esta jogada j� foi efetuada");
		}
		
		/**
		 * Insere o s�mbolo na posi��o digitada
		 */
		matriz[i][j] = simbolo;
		
		/**
		 * Verifica se a jogada inserida
		 * completa uma sequ�ncia que
		 * encerra o jogo.
		 */
		return isSequenciaCompleta();
	}
	
	/**
	 * Checa se h� uma sequ�ncia de 3
	 * s�mbolos iguais.
	 * @return true se a sequ�ncia de 3 s�mbolos encerra o jogo. Do contrat�rio, false.
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
