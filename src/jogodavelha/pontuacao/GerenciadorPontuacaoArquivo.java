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
 * Gerencia a pontuação e a armazena em um arquivo. 
 * @author Rafael.Valle
 *
 */
public class GerenciadorPontuacaoArquivo implements GerenciadorPontuacao {

	/**
	 * Constante contendo String com nome do arquivo
	 * onde os pontos serão armazenados
	 */
	private static final String ARQUIVO_PONTUACAO = "pontuacao.txt";
	
	/**
	 * Coleção Map contendo nomes dos jogadores (String)
	 * como chave para o valor de sua pontuação (Integer)
	 */
	private Map<String, Integer> pontuacaoMap = new HashMap<>();
	
	public GerenciadorPontuacaoArquivo() throws PontucacaoException{
		inicializar();
	}
	
	/**
	 * Inicializa gerenciador de pontuação
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
		 * Lê o arquivo para verificar a pontuação e nome atual(is) do(s) jogador(es)
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
	 * Retorna um Integer referente a pontuação de um jogador
	 * @param nome : Nome do jogador que terá a pontuação retornada
	 * @return : Integer referente a pontuação
	 */
	public Integer getPontuacao(String nome) {
		return pontuacaoMap.get(nome.toUpperCase());
	}

	/**
	 * Assimila a pontuação atual, lida pelo método getPontuacao
	 * a carrega e incrementa a pontuação atual
	 * @param nome
	 * @throws PontucacaoException
	 */
	public void gravarPontuacao (String nome) throws PontucacaoException {
		/**
		 * Lê pontuação do jogador
		 */
		Integer pontuacao = getPontuacao(nome);
		
		if (pontuacao == null) {
			pontuacao = 0;
		}
		
		/**
		 * Incrementa pontuação
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
