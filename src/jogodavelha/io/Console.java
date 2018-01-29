package jogodavelha.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * Respons�vel pela leitura de dados do console
 * @author Rafael.Valle
 *
 */
public class Console {

	/**
	 * L� String inserida via console
	 * @return String inserida no console
	 */
	public static String leString () {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (Exception e) {
			throw new RuntimeException("Erro de leitura do teclado");
		}
	}
	
}
