import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextArea;

public class Semantico {

	private String tipo;
	private String variavel;

	public void verificaSem(String stipo, String svariavel, JTextArea txtSemantico, String text) {

		this.tipo = stipo;
		this.variavel = svariavel;
		if (tipo.equals("String ")) {
			Pattern acharVar = Pattern.compile(new String(variavel));
			Pattern acharPV = Pattern.compile(";");

			Matcher m = acharVar.matcher(text);
			Matcher m1 = acharPV.matcher(text);

			while (m.find()) {
				int comecoVar = m.start();

				// System.out.println(comecoVar);
				// System.out.println(fimVar);
				int pvPos = 0;
				String trechoVarPv = "";// trecho da variavel ao ponto evirgula
				String trechoVarErrada = "";// trecho entre o igual e o ponto e
											// virgula
				int igualPos = 0;
				while (m1.find()) {

					pvPos = m1.start();// posicao do ;
					int k = 0;
					for (k = comecoVar; k < pvPos; k++) {
						trechoVarPv = trechoVarPv + text.charAt(k);
					}

					Pattern acharAtr = Pattern.compile("=");
					Matcher m2 = acharAtr.matcher(trechoVarPv);

					while (m2.find()) {
						igualPos = m2.start();

						for (int j = igualPos + 1; j < trechoVarPv.length(); j++) {
							trechoVarErrada = trechoVarErrada + trechoVarPv.charAt(j);
						}

						if (trechoVarErrada.charAt(0) != '"') {

							txtSemantico.setText("Erro: Tipo incorreto!" + trechoVarErrada);

						} else {
							txtSemantico.setText("");
						}

					} // m2
				} // m1

			} // m

		} // if

	}

}
