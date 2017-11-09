import java.awt.EventQueue;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;

public class AnaLex {

	private JFrame frmAnaLex;
	Lex AnalisadorLexico = new Lex();// instancia o objeto da classe Lex.
	GeradorDeCodigos gerador = new GeradorDeCodigos();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaLex janela = new AnaLex();
					janela.frmAnaLex.setVisible(true);// chama a janela do
														// programa
				} catch (Exception e) {
					e.printStackTrace();// procura dentro das exeptions e exibe
										// o erro.
				}
			}
		});
	}

	public AnaLex() {
		initialize();// inicia a aplicação
	}

	private void initialize() {
		// configuraões do Form.
		frmAnaLex = new JFrame();
		frmAnaLex.setFont(new Font("Dialog", Font.BOLD, 12));
		frmAnaLex.setAlwaysOnTop(true);
		frmAnaLex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnaLex.setResizable(false);
		frmAnaLex.getContentPane().setBackground(Color.GRAY);
		frmAnaLex.setTitle("Compilador");
		frmAnaLex.setBounds(100, 100, 621, 464);
		frmAnaLex.getContentPane().setLayout(null);

		JTabbedPane JTabbledPane = new JTabbedPane(JTabbedPane.TOP);
		JTabbledPane.setBounds(10, 241, 595, 183);
		frmAnaLex.getContentPane().add(JTabbledPane);

		JScrollPane scrollPane = new JScrollPane();
		JTabbledPane.addTab("Léxico", null, scrollPane, null);

		JTextArea txtResultado = new JTextArea();

		txtResultado.setFont(new Font("Monospaced", Font.BOLD, 13));
		scrollPane.setViewportView(txtResultado);

		JScrollPane scrollPane2 = new JScrollPane();
		JTabbledPane.addTab("Sintático", null, scrollPane2, null);

		JScrollPane scrollPane3 = new JScrollPane();
		JTabbledPane.addTab("Semântico", null, scrollPane3, null);

		JTextArea txtSemantico = new JTextArea();
		scrollPane3.setViewportView(txtSemantico);

		JTextArea txtSintatico = new JTextArea();
		txtSintatico.setFont(new Font("Monospaced", Font.BOLD, 13));
		scrollPane2.setViewportView(txtSintatico);

		JLabel lblRes = new JLabel();
		lblRes.setBounds(10, 271, 46, 14);
		lblRes.setForeground(Color.WHITE);
		frmAnaLex.getContentPane().add(lblRes);

		JLabel lblEntrada = new JLabel("Fonte:");
		lblEntrada.setBounds(10, 32, 72, 14);
		frmAnaLex.getContentPane().add(lblEntrada);

		JLabel lblAnlise = new JLabel("An\u00E1lises:");
		lblAnlise.setBounds(10, 226, 72, 14);

		frmAnaLex.getContentPane().add(lblAnlise);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 605, 21);
		frmAnaLex.getContentPane().add(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 53, 595, 162);
		frmAnaLex.getContentPane().add(tabbedPane);
		JScrollPane scrEntrada = new JScrollPane();

		tabbedPane.addTab("Entrada", null, scrEntrada, null);

		JTextArea txtEntrada = new JTextArea();
		scrEntrada.setViewportView(txtEntrada);
		txtEntrada.setFont(new Font("Monospaced", Font.BOLD, 12));
		txtEntrada.setBorder(new NumeredBorder());

		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// AnalisadorLexico2.salvar(txtEntrada, txtSintatico);
			}
		});
		mnArquivo.add(mntmSalvar);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);

		JMenu mnCompilar = new JMenu("Compilar");
		menuBar.add(mnCompilar);

		JMenuItem mntmNewMenuItem = new JMenuItem("Compilar");
		txtEntrada.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				AnalisadorLexico.verifica(txtEntrada, txtResultado, txtSintatico, txtSemantico);

			}
		});
		mnCompilar.add(mntmNewMenuItem);

		JMenuItem mntmDebugar = new JMenuItem("Depurar");
		mnCompilar.add(mntmDebugar);

		JButton btnSinttico = new JButton("Sint\u00E1tico");
		btnSinttico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gerador.GerarCodSintatico(txtEntrada);
			}
		});
		btnSinttico.setBounds(372, 28, 109, 23);
		frmAnaLex.getContentPane().add(btnSinttico);

		JButton btnSemntico = new JButton("Sem\u00E2ntico");
		btnSemntico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gerador.GerarCodSemantico(txtEntrada);
			}
		});
		btnSemntico.setBounds(491, 28, 114, 23);
		frmAnaLex.getContentPane().add(btnSemntico);

		JLabel lblGerarCdigosPara = new JLabel("Gerar c\u00F3digos para teste:");
		lblGerarCdigosPara.setBounds(77, 32, 183, 14);
		frmAnaLex.getContentPane().add(lblGerarCdigosPara);

		JButton btnComentario = new JButton("Comentario");
		btnComentario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gerador.GerarCodComentario(txtEntrada);
			}
		});
		btnComentario.setBounds(247, 28, 115, 23);
		frmAnaLex.getContentPane().add(btnComentario);

	}
}
