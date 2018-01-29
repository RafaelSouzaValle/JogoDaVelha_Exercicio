package jogodavelha.pontuacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jogodavelha.exceptions.PontucacaoException;

/**
 * Gerencia a pontua��o e a armazena em um arquivo. 
 * @author Rafael.Valle
 *
 */
public class GerenciadorPontuacaoArquivo implements GerenciadorPontuacao {

	/**
	 * Constante contendo String com nome do arquivo
	 * onde os pontos ser�o armazenados
	 */
	private static final String ARQUIVO_PONTUACAO = "pontuacao.txt";
	
	/**
	 * Cole��o Map contendo nomes dos jogadores (String)
	 * como chave para o valor de sua pontua��o (Integer)
	 */
	private Map<String, Integer> pontuacaoMap = new HashMap<>();
	
	public GerenciadorPontuacaoArquivo() throws PontucacaoException{
		inicializar();
	}
	
	/**
	 * Inicializa gerenciador de pontua��o
	 * @throws PontucacaoException
	 */
	private void inicializar() throws PontucacaoException {
		/**
		 * Verifica se o arquivo existe
		 */
		File pontuacaoFile = new File(ARQUIVO_PONTUACAO);
		if (!pontuacaoFile.exists()) {
			try {
				pontuacaoFile.createNewFile();
			} catch (IOException e) {
				throw new PontucacaoException(e.getMessage());
			}
		}
		
		BufferedReader bReader = null;
		
		/**
		 * L� o arquivo para verificar a pontua��o e nome atual(is) do(s) jogador(es)
		 */
		try {
			bReader = new BufferedReader(new FileReader(pontuacaoFile));
			
			String line;
			while ((line = bReader.readLine()) != null) {
				String[] tokens = line.split("\\|");
				
				pontuacaoMap.put(tokens[0].toUpperCase(), Integer.parseInt(tokens[1]));
			}
		} catch (IOException e) {
			throw new PontucacaoException(e.getMessage());
		} finally {
			if (bReader != null) {
				try {
					bReader.close();
				} catch (IOException e) {
					throw new PontucacaoException(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Retorna um Integer referente a pontua��o de um jogador
	 * @param nome : Nome do jogador que ter� a pontua��o retornada
	 * @return : Integer referente a pontua��o
	 */
	public Integer getPontuacao(String nome) {
		return pontuacaoMap.get(nome.toUpperCase());
	}

	/**
	 * Assimila a pontua��o atual, lida pelo m�todo getPontuacao
	 * a carrega e incrementa a pontua��o atual
	 * @param nome
	 * @throws PontucacaoException
	 */
	public void gravarPontuacao (String nome) throws PontucacaoException {
		/**
		 * L� pontua��o do jogador
		 */
		Integer pontuacao = getPontuacao(nome);
		
		if (pontuacao == null) {
			pontuacao = 0;
		}
		
		/**
		 * Incrementa pontua��o
		 */
		pontuacaoMap.put(nome.toUpperCase(), ++pontuacao);
		
		/**
		 * Armazena entradas no Map no arquivo
		 */
		try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(ARQUIVO_PONTUACAO))){
			for (Map.Entry<String, Integer> entry : pontuacaoMap.entrySet()) {
				bWriter.write(entry.getKey() + "|" + entry.getValue());
				
				bWriter.newLine();
			}
		} catch (IOException e) {
			throw new PontucacaoException(e.getMessage());
		}
	}
	
}
