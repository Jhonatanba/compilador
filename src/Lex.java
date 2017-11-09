
//import java.awt.Color;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextArea;

public class Lex {

	Semantico AnaSem = new Semantico();// instancia o objeto da classe
										// Semantico.

	public static enum Simbolo {
		// ESSES atributos estao em capslock para mostrar que sao como
		// constantes em java, pois pertencem a classe do tipo enum.
		// A vantagem de usar enum � que ele nao aceita nada alem do que foi
		// definido nos atributos
		// ele imprime o nome ex. DIGITO tal.
		//
		DIGITO("-?[0-9]+"), MODIFCADORDEACESSO("(private) | (protected) | (public)"), ESPACO("[ \t\f\r\n]+"), OPLOG(
				"(==)|(or)|(&&)|(>=)|(<=)|(>)|(<)|(!=)"), OPMAT("[*/+-=]"), OPBOLEANO("(true) | (false)"), SIMB(
						"[(/# )}{]"), PALAVRARESERVADA(
								"(if )|(else )|(for )|(while )|(case )|(Do ) | (break ) | (return ) | (switch )|(assert) | (catch) | (finally) | (throw) | (throws) | (try)"), TIPOVAR(
										"(int )|(float )|(String )|(double )|(Char )|(boolean )"), MODIFICADORDECLASSESEMETODOS(
												"(abstract ) | (class ) | (extends ) | (final )| (implements ) | (interface ) |"
														+ " (native ) | (new ) | (static ) | (strictfp ) | (synchronized ) | (transient ) | (volatile ) | (void )"), VARIAVELDEREFERENCIA(
																"(super ) | (this )"), VAR("-?[a-zA-Z0-9]+");

		public final String mod;

		private Simbolo(String modelo)// esse met. construtor � usado para
										// buscar os valores dos atributos da
										// classe enum.
		{
			this.mod = modelo; // a variavel mod. recebe a string digitada para
								// ser comparada no while.
		}
	}// fim da classe simbolo.

	public static class Simbol {
		public Simbolo t; // variavel t tipo
		public String d; // Variavel dado

		public Simbol(Simbolo pt, String pd)// essa fun��o recebe o tipo do
											// simb. da fun��o Simbolo
		{ // e a string que esta sendo digitada
			this.t = pt;
			this.d = pd;
		}

		public String toString() {
			return String.format("(%s %s)", t.name(), d);// da a representa��o
															// em string do
															// objeto.
		}

	}

	// essa fun��o lex � do tipo arralist
	// arraylist � um vetor redimensionavel. que recebe como tamanho o token
	// escolhido pelo usuario.
	// a classe lex recebe o parametro entr.
	public static ArrayList<Simbol> lex(String entr) {
		// lex recebe o valor digitado.
		ArrayList<Simbol> vet = new ArrayList<Simbol>();
		// cria��o da var. vet do tipo arraylist.
		StringBuffer buffer = new StringBuffer();
		// stringBuffer concatena strings de maneira sincronizada.
		for (Simbolo simb : Simbolo.values())
			// esse tipo de 'for' faz com que ele passe em todos.

			// coloca a string digitada na sequencia de caracteres.
			buffer.append(String.format("|(?<%s>%s)", simb.name(), simb.mod));
		// a var. pat recebe a string digitada iniciando pela primeira letra.
		Pattern pat = Pattern.compile(new String(buffer.substring(1)));
		// o tipo pattern serve para criar matchers, � uma expressao regular
		// string.
		// o matcher � uma especie de mecanismo que interpreta o pattern.
		// Pattern � como se fosse um modelo e o matcher compara o que �
		// digitado com esse modelo.
		Matcher mat = pat.matcher(entr);
		// logo acima o matcher l� a entrada.

		while (mat.find())// a funcao find tenta encontrar a proxima
							// subsequencia atribuida ao pattern.
		{// o .group retorna a sequencia capturada durante o funcionamento do
			// matcher

			if (mat.group(Simbolo.DIGITO.name()) != null) {// se a palavra foi
															// digitada
															// exatamente como
															// foi atribuida ao
															// DIGITO ele nao
															// retorna nulo.
				vet.add(new Simbol(Simbolo.DIGITO, mat.group(Simbolo.DIGITO.name())));
				// ele adiciona no vet que eh um vetor, o digito e chama a
				// fun��o Simbol.
				continue;
			} else if (mat.group(Simbolo.MODIFICADORDECLASSESEMETODOS.name()) != null) {
				vet.add(new Simbol(Simbolo.MODIFICADORDECLASSESEMETODOS,
						mat.group(Simbolo.MODIFICADORDECLASSESEMETODOS.name())));
				continue;
			} else if (mat.group(Simbolo.VARIAVELDEREFERENCIA.name()) != null) {
				vet.add(new Simbol(Simbolo.VARIAVELDEREFERENCIA, mat.group(Simbolo.VARIAVELDEREFERENCIA.name())));
				continue;
			}

			// faz o mesmo para os demais.
			else if (mat.group(Simbolo.OPMAT.name()) != null) {
				vet.add(new Simbol(Simbolo.OPMAT, mat.group(Simbolo.OPMAT.name())));
				continue;
			} else if (mat.group(Simbolo.MODIFCADORDEACESSO.name()) != null) {
				vet.add(new Simbol(Simbolo.MODIFCADORDEACESSO, mat.group(Simbolo.MODIFCADORDEACESSO.name())));
				continue;
			}

			else if (mat.group(Simbolo.OPLOG.name()) != null) {
				vet.add(new Simbol(Simbolo.OPLOG, mat.group(Simbolo.OPLOG.name())));
				continue;
			} else if (mat.group(Simbolo.OPBOLEANO.name()) != null) {
				vet.add(new Simbol(Simbolo.OPBOLEANO, mat.group(Simbolo.OPBOLEANO.name())));
				continue;
			}

			else if (mat.group(Simbolo.SIMB.name()) != null) {
				vet.add(new Simbol(Simbolo.SIMB, mat.group(Simbolo.SIMB.name())));
				continue;
			}

			else if (mat.group(Simbolo.VAR.name()) != null) {
				vet.add(new Simbol(Simbolo.VAR, mat.group(Simbolo.VAR.name())));

				continue;
			}

			else if (mat.group(Simbolo.TIPOVAR.name()) != null) {
				vet.add(new Simbol(Simbolo.TIPOVAR, mat.group(Simbolo.TIPOVAR.name())));
				continue;
			}

			else if (mat.group(Simbolo.PALAVRARESERVADA.name()) != null) {
				vet.add(new Simbol(Simbolo.PALAVRARESERVADA, mat.group(Simbolo.PALAVRARESERVADA.name())));
				continue;
			}

			else if (mat.group(Simbolo.ESPACO.name()) != null)
				continue;

			// else if(mat.group(Simbolo.OPLOG.name()) == "="){
			// System.out.println("aeeee");
			// continue;
			// }

		}
		return vet;
	}

	// fun��o verifica, recebe como param. dois texfields.
	public void verifica(JTextArea txtEntrada, JTextArea txtResultado, JTextArea txtSintatico, JTextArea txtSemantico) {
		// Declara��o de vari�veis
		String entrada = txtEntrada.getText();
		ArrayList<Simbol> vet = lex(entrada);
		String s = "";
		String com = "";
		int achouCom = 0;
		int linhaAtual = 1;
		int erroChave = 0;
		int linhaAtualChave = 0;
		int erroColchete = 0;
		int erroParenteses = 0;
		int linhaAtualColchete = 0;
		int linhaAtualParenteses = 0;

		// AN�LISE SINT�TICA: VERIFICA��O DE ESTRUTURAS COM CHAVES, COLCHETES E
		// PARENTESES.
		for (int i = 0; i < entrada.length(); i++) {
			// verifica se deu enter.
			if (entrada.charAt(i) == '\n') {
				linhaAtual++;
			}
			// verificar se abriu e fechou chave.
			if (entrada.charAt(i) == '{') {
				erroChave = 1;// Seta a flag. erroChave
				linhaAtualChave = linhaAtual;
			}
			if (entrada.charAt(i) == '}') {
				erroChave = 0;
				txtSintatico.setText("");
			}
			// verifica se abriu e fechou colchete.
			if (entrada.charAt(i) == '[') {
				erroColchete = 1;
				linhaAtualColchete = linhaAtual;
			}
			if (entrada.charAt(i) == ']') {
				erroColchete = 0;
				txtSintatico.setText("");
			}
			// verificar se abriu e fechou parenteses.
			if (entrada.charAt(i) == '(') {
				erroParenteses = 1;
				linhaAtualParenteses = linhaAtual;
			}
			if (entrada.charAt(i) == ')') {
				txtSintatico.setText("");
				erroParenteses = 0;
			}

			// verifica se encontrou coment�rio
			if ((entrada.charAt(i) == '/') && (entrada.charAt(i + 1) == '/')) {
				achouCom = 1;
				for (int j = 0; j < i; j++) {
					s = s + entrada.charAt(j);
				}
				for (int c = i + 2; c < entrada.length(); c++) {
					com = com + entrada.charAt(c);
				}
			}
		}
		ArrayList<Simbol> vet2 = lex(s);
		if (achouCom == 1) {
			for (Simbol vet1 : vet2) {
				s += vet1.toString() + "\n";
			}
			txtResultado.setText(s + "Coment�rio: " + com + "\n" + "Linha: " + Integer.toString(linhaAtual));
		} else {
			for (Simbol vet1 : vet) {
				entrada += vet1.toString() + "\n";
			}
			txtResultado.setText(entrada);
		}
		// SE ALGUM ERRO FOI ENCONTRADO, O TXTSINTATICO RECEBE A NOTIFICA��O.
		if (erroChave == 1) {
			txtSintatico.setText("Linha: " + Integer.toString(linhaAtualChave)
					+ "-> Erro de Sintaxe: Insira '}' para completar o corpo da classe.");
		}

		if (erroColchete == 1) {
			txtSintatico.setText("Linha: " + Integer.toString(linhaAtualColchete)
					+ "-> Erro de Sintaxe: Insira ']' para completar o corpo da classe.");
		}

		if (erroParenteses == 1) {
			txtSintatico.setText("Linha: " + Integer.toString(linhaAtualParenteses)
					+ "-> Erro de Sintaxe: Insira ')' para completar o corpo da classe.");
		}

		// ******************************VERIFICA��O DE DECLARA��O DE
		// VARI�VEIS*******************************************

		StringBuffer buffer = new StringBuffer();

		for (Simbolo simb : Simbolo.values())
			buffer.append(String.format("|(?<%s>%s)", simb.name(), simb.mod));// preenche
																				// o
																				// buffer
																				// com
																				// o
																				// conteudo
																				// da
																				// classe
																				// simbolo
		String text = txtResultado.getText();// conteudo do jtextArea

		Pattern p = Pattern.compile(new String(buffer.substring(1)));// compila
																		// o
																		// buffer

		Matcher m = p.matcher(text);// percorre o jtextArea em busca do conteudo
									// do pattern.

		// int flag=0;

		String variavelDeclarada = null;

		String tipo = null;
		while (m.find()) {// enquanto encontrar
			if (m.group(Simbolo.VAR.name()) != null) {

				variavelDeclarada = m.group(Simbolo.VAR.name());

				break;
			}
			continue;
		}
		while (m.find()) {
			if (m.group(Simbolo.TIPOVAR.name()) != null) {

				tipo = m.group(Simbolo.TIPOVAR.name());
				break;
			}

			continue;
		}
		String text2 = txtEntrada.getText();

		AnaSem.verificaSem(tipo, variavelDeclarada, txtSemantico, text2);
	}

	public void salvar(JTextArea txtEntrada, JTextArea txtResultado) {
		FileWriter arquivo;
		try {
			arquivo = new FileWriter(new File("C:/Novapasta/teste.txt"));
			arquivo.write(txtEntrada.getText());
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gravarSint(JTextArea txtEntrada, JTextArea txtSintatico) {
		String entr = txtEntrada.getText();
		txtSintatico.setText(entr);
	}
}
