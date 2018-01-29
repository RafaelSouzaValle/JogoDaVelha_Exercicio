package jogodavelha;

import java.util.ArrayList;
import java.util.List;

import jogodavelha.exceptions.JogadaInvalidaException;
import jogodavelha.exceptions.JogoDaVelhaException;
import jogodavelha.io.Console;
import jogodavelha.pontuacao.GerenciadorPontuacao;
import jogodavelha.pontuacao.GerenciadorPontuacaoArquivo;

/**
 * Possui a l�gica do jogo
 * @author Rafael.Valle
 *
 */
public class Jogo {

	/**
	 * Cria uma inst�ncia de Tabuleiro
	 */
	Tabuleiro tabuleiro = new Tabuleiro();
	
	/**
	 * Lista de jogadores
	 */
	private List<Jogador> jogadores = new ArrayList<>();
	
	/**
	 * Gerenciador da pontua��o
	 */
	private GerenciadorPontuacao gerenciadorPontuacao;
	
	/**
	 * Inicia jogo
	 * @throws JogoDaVelhaException para o caso de problemas durante a execu��o do jogo
	 */
	public void jogar() throws JogoDaVelhaException {
		System.out.println("+++++++++++++++++");
		System.out.println("| JOGO DA VELHA |");
		System.out.println("+++++++++++++++++");
		System.out.println();
		
		/**
		 *  Carrega o gerenciador de pontua��o
		 */
		gerenciadorPontuacao = new GerenciadorPontuacaoArquivo();
		
		/**
		 * Solicita entrada com nome do primeiro jogador
		 */
		System.out.print("Nome do jogador 1: ");
		String nome = Console.leString();
		jogadores.add(new Jogador(nome, 'X'));
		
		/**
		 * Verifica se j� existe pontua��o
		 * gravada para um jogador com nome
		 * igual ao inserido e insere
		 * o n�mero de vit�rias
		 */
		Integer pontuacao = gerenciadorPontuacao.getPontuacao(nome);
		
		String mensagem = "O jogador %s j� possui %d %s.\n";
		
		if (pontuacao != null) {
			System.out.format(mensagem, nome, pontuacao, pontuacao == 1 ? "vit�ria" : "vit�rias");
		}
		
		/**
		 * Solicita entrada com nome do segundo jogador
		 */
		System.out.print("Nome do jogador 2: ");
		nome = Console.leString();
		jogadores.add(new Jogador(nome, 'O'));
		
		pontuacao = gerenciadorPontuacao.getPontuacao(nome);
		
		if (pontuacao != null) {
			System.out.format(mensagem, nome, pontuacao, pontuacao == 1 ? "vit�ria" : "vit�rias");
		}
		
		// Indica se o jogo est� finalizado
		boolean terminado = false;
		
		// �ndice do jogador: 0 para Jogador 1; 1 para Jogador 2
		int indiceJogadorAtual = 0;
		
		Jogador vencedor = null;
		
		/**
		 * Loop do jogo
		 */
		while (!terminado) {
			/**
			 * Imprime o tabuleiro
			 */
			tabuleiro.imprimir();
			
			/**
			 * Retorna o jogador atual
			 * com base em seu �ndice na lista
			 * de jogadores
			 */
			Jogador jogador = jogadores.get(indiceJogadorAtual);
			
			System.out.print("Jogador " + jogador.getName() + ": ");
			
			boolean sequenciaEncontrada;
			try {
				// Solicita a jogada
				Jogada jogada = jogador.obterJogada();
				
				// Faz a jogada do jogador atual
				sequenciaEncontrada = tabuleiro.efetuarJogada(jogada, jogador.getSimbolo());
				
			} catch (JogadaInvalidaException e) {
				System.out.println("Erro: " + e.getMessage());
				continue;
			}
			
			if (sequenciaEncontrada) {
				/**
				 * Se a sequ�ncia de pontua��o for encontrada,
				 * o jogador atual � declarado vencedor
				 */
				vencedor = jogador;
				terminado = true;
			} else if (tabuleiro.isCompleto()) {
				/**
				 * Se o tabuleiro for preenchido,
				 * mas n�o houver sequ�ncia com pontua��o
				 * o jogo � declarado empatado
				 */
				terminado = true;
			}
			
			indiceJogadorAtual = (indiceJogadorAtual + 1) % jogadores.size();
		}
		
		System.out.println();
		
		if (vencedor == null) {
			System.out.println("O jogo terminou empatado!");
		} else {
			System.out.println("Jogo encerrado! Vencedor: " + vencedor.getName());
			
			/**
			 * Grava��o da pontua��o do
			 * vencedor no arquivo de pontua��o
			 */
			gerenciadorPontuacao.gravarPontuacao(vencedor.getName());
		}
		
		tabuleiro.imprimir();
	}
}
