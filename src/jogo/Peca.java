package jogo;

import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Peca extends JButton {
	private estadoPeca estado;
	private int tipoPeca; // 1 = Branca; 2 = Coroa Branca; 3 = Preta; 4 = Coroa Preta;
	
	public estadoPeca getEstado() {
		return estado;
	}

	public void setEstado(estadoPeca estado) {
		this.estado = estado;
	}

	public int getTipoPeca() {
		return tipoPeca;
	}

	public void setTipoPeca(int tipoPeca) {
		this.tipoPeca = tipoPeca;
	}
		
	//imagens da pe�a
	ImageIcon cachorroBranco = new ImageIcon(getClass().getResource("/icones/Cachorro Branco.png"));
	ImageIcon cachorroPreto = new ImageIcon(getClass().getResource("/icones/Cachorro Preto.png"));
	ImageIcon coroaBranca = new ImageIcon(getClass().getResource("/icones/Coroa Branca.png"));
	ImageIcon coroaPreta = new ImageIcon(getClass().getResource("/icones/Coroa Preta.png"));
	
	//M�todos para adicionar uma imagen � pe�a
	// Tipo de Pe�a 1
	public void addCachorroBranco() {
		this.setIcon(cachorroBranco);
	}
	// Tipo de Pe�a 2
	public void addCoroaBranca() {
		this.setIcon(coroaBranca);
	}
	// Tipo de Pe�a 3
	public void addCachorroPreto() {
		this.setIcon(cachorroPreto);
	}
	// Tipo de Pe�a 1
	public void addCoroaPreta() {
		this.setIcon(coroaPreta);
	}
	
	public enum estadoPeca {
		NORMAL, SELECIONADO;
	}
	
}
