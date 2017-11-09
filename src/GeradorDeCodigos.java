import javax.swing.JTextArea;

public class GeradorDeCodigos {

	public void GerarCodComentario(JTextArea txtEntrada) {

		txtEntrada.setText(
				"boolean condicao;\nif (SaoPaulo == MelhorTime)\n{\n   condicao=true;\n}\nelse\n{\n   condicao=false;\n}//SP sempre ser� o melhor time.");
	}

	public void GerarCodSintatico(JTextArea txtEntrada) {

		txtEntrada.setText(
				"while (Goku == true) {\n    goku.teletransportar();\n\nAbra a aba sit�tico para visualizar o erro.");
	}

	public void GerarCodSemantico(JTextArea txtEntrada) {

		txtEntrada.setText("String velocidadeDoFlash=5;\n\nAbra a aba sem�ntico para visualizar o erro.");
	}

}
