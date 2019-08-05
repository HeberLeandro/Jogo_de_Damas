package jogo;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import jogo.Peca.estadoPeca;


public class Tabuleiro extends JFrame implements ActionListener{
	
	//Menu Bar
	JMenuBar menuBar;
	JMenu opcoesMenu;
	JMenuItem restartAction, sairAction;
	private Peca[][] peca = new Peca[8][8];
	//Flag para o Action Performed
	boolean temSelecionada = false;
	Regras regras = new Regras(peca);
	//Variaveis para p Action Performed
	int colunaSelecionda = 0, linhaSeleciona = 0;
	
	public Tabuleiro(LayoutManager GridLayout) {
		
		//JFrame
		this.setSize(725,725);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setName("teste");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(GridLayout);
		this.setResizable(false);
		
		
		//Add Menu Bar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.setBackground(Color.GRAY);
		//menu drop down
		opcoesMenu = new JMenu("Menu"); 
		menuBar.add(opcoesMenu);
		//items do menu
		restartAction = new JMenuItem("Restart");
		restartAction.addActionListener(this);
		sairAction = new JMenuItem("Sair");
		sairAction.addActionListener(this);
		opcoesMenu.add(restartAction);
		opcoesMenu.add(sairAction);
		
		
		//Cria��o do tabuleiro
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				peca[i][j] = new Peca();
				if((i+j) % 2 != 0) {
					peca[i][j].setBackground(Color.GRAY);
				}else {
					peca[i][j].setBackground(Color.WHITE);
				}
				peca[i][j].addActionListener(this);
				this.add(peca[i][j]);
			}
		}
		//Adiciona as pe�as
		adicionarPecas();
	}
	//esse método inicial/reinicia o jogou destribuindo as peças
	public void adicionarPecas() {
		int filaFinal = 8;
		for (int i = 0; i < 3; i++) {
			filaFinal--;
			for (int j = 0; j < 8; j++) {	
				if((i+j) % 2 != 0) {
						peca[i][j].addCachorroBranco();
						peca[i][j].setTipoPeca(1);
						peca[i][j].setEstado(estadoPeca.NORMAL);
						peca[i][j].setBackground(Color.GRAY);
						//quadrados sem pedras
						peca[i+2][j].setIcon(null);
						peca[i+2][j].setTipoPeca(0);
						peca[i+2][j].setEstado(estadoPeca.NORMAL);
						peca[i+2][j].setBackground(Color.GRAY);
				}else {
						//peças pretas
						peca[filaFinal][j].addCachorroPreto();
						peca[filaFinal][j].setTipoPeca(3);
						peca[filaFinal][j].setEstado(estadoPeca.NORMAL);
						peca[filaFinal][j].setBackground(Color.GRAY);
				}
			}
		}
	}
	
	
	//realiza o movimento
	public void moverPeca(int destinoColum, int destinoLinha, int localColum, int localLinha) {
		
		int guardaTipo = 0;
		
		guardaTipo = peca[localLinha][localColum].getTipoPeca();
		peca[localLinha][localColum].setIcon(null);
		peca[localLinha][localColum].setEstado(estadoPeca.NORMAL);
		peca[localLinha][localColum].setBackground(Color.GRAY);
		peca[localLinha][localColum].setTipoPeca(0);
			
			//novo local da pe�a
		switch (guardaTipo) {
			case 1:
				//Cachorro branco
				if(destinoLinha == 7) {
					peca[destinoLinha][destinoColum].addCoroaBranca();
					peca[destinoLinha][destinoColum].setTipoPeca(2);
					peca[destinoLinha][destinoColum].setEstado(estadoPeca.NORMAL);
					temSelecionada = false;
					break;
				}
				peca[destinoLinha][destinoColum].addCachorroBranco();
				peca[destinoLinha][destinoColum].setTipoPeca(guardaTipo);
				peca[destinoLinha][destinoColum].setEstado(estadoPeca.NORMAL);
				temSelecionada = false;
			
				break;
				
			case 2:
			//Coroa branca
				peca[destinoLinha][destinoColum].addCoroaBranca();
				peca[destinoLinha][destinoColum].setTipoPeca(guardaTipo);
				peca[destinoLinha][destinoColum].setEstado(estadoPeca.NORMAL);
				temSelecionada = false;
				break;
				
			case 3:
			//Cachorro preto
				if(destinoLinha == 0) {
					peca[destinoLinha][destinoColum].addCoroaPreta();
					peca[destinoLinha][destinoColum].setTipoPeca(4);
					peca[destinoLinha][destinoColum].setEstado(estadoPeca.NORMAL);
					temSelecionada = false;
					break;
				}
				peca[destinoLinha][destinoColum].addCachorroPreto();
				peca[destinoLinha][destinoColum].setTipoPeca(guardaTipo);
				peca[destinoLinha][destinoColum].setEstado(estadoPeca.NORMAL);
				temSelecionada = false;
				break;
				
			case 4:
			//Coroa preta
				peca[destinoLinha][destinoColum].addCoroaPreta();
				peca[destinoLinha][destinoColum].setTipoPeca(guardaTipo);
				peca[destinoLinha][destinoColum].setEstado(estadoPeca.NORMAL);
				temSelecionada = false;
				break;
		default:
			return;
		}

	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
    	int pecasBrancas = 0, pecasPretas = 0;
    	
    	int linhaDestino = 0, colunaDestino = 0;
    	boolean temSelecionada = false;
    	
    	// loop para verificar pe�� selecionada
    	for (int i = 0; i < 8; i++) {
			
    		for (int j = 0; j < 8; j++) {
				
				if(peca[i][j].getEstado() == estadoPeca.SELECIONADO) {
					temSelecionada = true;
					colunaSelecionda = j;
					linhaSeleciona = i;
				}
				if ((peca[i][j].getTipoPeca() == 1 || peca[i][j].getTipoPeca() == 2)) {
					pecasBrancas++;
				}
				else if ((peca[i][j].getTipoPeca() == 3 || peca[i][j].getTipoPeca() == 4)) {
					pecasPretas++;
				}
			}
		}
    	
    	//la�o para faz movimento, ou selecionar um pe�a para mover
    	for (int i = 0; i < 8; i++) {
    		
			for (int j = 0; j < 8; j++) {
				
				//Mudar bot�o selecionado
				if ((temSelecionada == true) && (e.getSource() == peca[i][j]) && peca[i][j].getEstado() == estadoPeca.SELECIONADO) {
					peca[i][j].setEstado(estadoPeca.NORMAL);
					peca[i][j].setBackground(Color.GRAY);
					temSelecionada = false;
					return;
				}
				//Se o but�o selecionado tiver uma pe�a, ele munda seu estado para selecionado, se n�o tiver outro bot�o selecionadao
				if (e.getSource() == peca[i][j] && (peca[i][j].getTipoPeca() > 0 && peca[i][j].getTipoPeca() <= 4) && temSelecionada == false) {
					
					peca[i][j].setEstado(estadoPeca.SELECIONADO);
					peca[i][j].setBackground(Color.blue);
				}
				//Se ja estiver algum bot�o selecionado o proximo but�o clicado (que n�o tiver uma pe�a), sera enviada a sua posi��o
				//para verifica��o, para poder mover a pe�a
				else if(e.getSource() == peca[i][j] && !(peca[i][j].getTipoPeca() > 0 && peca[i][j].getTipoPeca() <= 4) && temSelecionada == true) {
						
					linhaDestino = i;
					colunaDestino = j;
						
					if(regras.isValidMove(colunaDestino, linhaDestino, colunaSelecionda, linhaSeleciona) == true) {
							
						moverPeca(colunaDestino, linhaDestino, colunaSelecionda, linhaSeleciona);
						return;
					}	
				}
			}
		}
    	
    	//Lado Vitorioso
    	if (pecasBrancas == 0) {
    		int opcao = JOptionPane.showConfirmDialog(null, "As pe�as pretas ganharam! Deseja jogar novamente?", "t/Vit�ria!" ,JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
    		if (opcao == 0) {
				adicionarPecas();
			}
    		else {
    			System.exit(0);
    		}
    	}
    	else if (pecasPretas == 0) {
    		int opcao = JOptionPane.showConfirmDialog(null, "As pe�as brancas ganharam! Deseja jogar novamente?", "t/Vit�ria!" ,JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
    		if (opcao == 0) {
				adicionarPecas();
			}
    		else {
    			System.exit(0);
    		}	
		}
			
    	//Menu Items
    	if (e.getSource() == sairAction) {
			System.exit(0);
		}
    	
    	if(e.getSource() == restartAction) {
    		adicionarPecas();
    	}
    }
	   
}
	



