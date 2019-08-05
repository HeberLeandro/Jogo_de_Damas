package jogo;

public class Regras {
	private Peca[][] peca = new Peca[8][8];
	
	public Regras(Peca[][] peca) {
		this.peca = peca;
	}

	//Valida��o do movimendo da pe�a
		public boolean isValidMove(int destinoColum, int destinoLinha, int localColum, int localLinha) {
			
			int distanciaLinha, distanciaColum;
			//Variavei para Movimenta��o das damas
			boolean inimigoCapturado = false;
			int temInimigo = 0,inimigoLinha = 0, inimigoColum = 0;
			
			distanciaLinha = Math.abs(localLinha - destinoLinha);
			distanciaColum = Math.abs(localColum - destinoColum);
			
			/* ******************** *
			 * COME�O PE�AS BRANCAS *
			 * ******************** */
			//CORCHORO BRANCO = tipoPe�a 1;
			//COROA BRANCA = tipoPe�a 2;
			
			//Verifica��o para pe�a branca (N�O COROA) mover uma casa (n�o pode pra cima)
			if((distanciaColum == 1 && distanciaLinha == 1) && (peca[localLinha][localColum].getTipoPeca() == 1)) {
				
				if (destinoLinha > localLinha) {
					
					if (destinoColum < localColum || destinoColum > localColum) return true;
				}
			
			} //FINAL DA MOVIMENTA��O DE UMA CASA PE�A BRANCA
			
			
			//Verifica��o Para pe�a branca (N�O COROA) "COMER" uma preta
			if((distanciaColum == 2 && distanciaLinha == 2) && (peca[localLinha][localColum].getTipoPeca() == 1)) {
				
				//verifica a pe�a entre o Destino e o local atual, sentido pra baixo
				if(destinoLinha > localLinha) {
					
					//Pe�as pretas a esqueda da pe�a
					if (destinoColum < localColum) {
						
						if (peca[localLinha + 1][localColum - 1].getTipoPeca() == 3 || peca[localLinha + 1][localColum - 1].getTipoPeca() == 4) {
							
							peca[localLinha + 1][localColum - 1].setIcon(null);
							peca[localLinha + 1][localColum - 1].setTipoPeca(0);
							return true;
						}	
					}
					//pe�as pretas a direita da pe�a
					else if(destinoColum > localColum) {
						
						if (peca[localLinha + 1][localColum + 1].getTipoPeca() == 3 || peca[localLinha + 1][localColum + 1].getTipoPeca() == 4) {
							
							peca[localLinha + 1][localColum + 1].setIcon(null);
							peca[localLinha + 1][localColum + 1].setTipoPeca(0);
							return true;
						}
					}
				}	
			} //FINAL DA MOVIMENTA��O DE COMER. CACHORRO BRANCO
			
			//Movimenta��o da COROA BRANCA
			if (((distanciaColum < 8 && distanciaColum > 0 ) && (distanciaLinha < 8 && distanciaLinha > 0) && distanciaLinha == distanciaColum) && (peca[localLinha][localColum].getTipoPeca() == 2)) {
				
				//Movimenta��o pra BAIXO
				if (destinoLinha > localLinha) {
					
					//Movimenta��o pra Esquerda (baixo)
					if (destinoColum < localColum) {
						//For para verificar se o caminho da Coroa n�o tem obstaculos
						for (int i = 1; i < distanciaColum; i++) {
							//VERIFICA��O DE PE�A INIMIGA NO CAMINHO
							if(peca[localLinha + i][localColum - i].getTipoPeca() == 3 || peca[localLinha + i][localColum - i].getTipoPeca() == 4) {
								temInimigo++;
								if (temInimigo == 1 && inimigoCapturado == false) {
									inimigoLinha = localLinha + i;
									inimigoColum = localColum - i;
									inimigoCapturado = true;
								}
							}
							//VERIFICA��O DE PE�A ALIADA NO CAMINHO
							else if(peca[localLinha + i][localColum - i].getTipoPeca() == 1 || peca[localLinha + i][localColum - i].getTipoPeca() == 2) {
								return false;
							}
						}
						
						if(temInimigo == 1) {
							peca[inimigoLinha][inimigoColum].setIcon(null);
							peca[inimigoLinha][inimigoColum].setTipoPeca(0);
							
							return true;
						}
						else if(temInimigo == 0) {
							return true;
						}
					}
					//Movimenta��o pra Direita (baixo)
					else if (destinoColum > localColum) {
						//For para verificar se o caminho da Coroa n�o tem obstaculos
						for (int i = 1; i < distanciaColum; i++) {
							//VERIFICA��O DE PE�A INIMIGA NO CAMINHO
							if(peca[localLinha + i][localColum + i].getTipoPeca() == 3 || peca[localLinha + i][localColum + i].getTipoPeca() == 4) {
								temInimigo++;
								if (temInimigo == 1 && inimigoCapturado == false) {
									inimigoLinha = localLinha + i;
									inimigoColum = localColum + i;
									inimigoCapturado = true;
								}
							}
							//VERIFICA��O DE PE�A ALIADA NO CAMINHO
							else if(peca[localLinha + i][localColum + i].getTipoPeca() == 1 || peca[localLinha + i][localColum + i].getTipoPeca() == 2) {
								return false;
							}
						}
						
						if(temInimigo == 1) {
							peca[inimigoLinha][inimigoColum].setIcon(null);
							peca[inimigoLinha][inimigoColum].setTipoPeca(0);
							
							return true;
						}
						else if(temInimigo == 0) {
							return true;
						}
					}
				}//FIM DO MOVIMENTO PRA BAIXO
				
				//Movimento pra CIMA
				else if (destinoLinha < localLinha) {
					
					//Movimenta��o pra Esquerda (cima)
					if (destinoColum < localColum) {
						//For para verificar se o caminho da Coroa n�o tem obstaculos
						for (int i = 1; i < distanciaColum; i++) {
							//VERIFICA��O DE PE�A INIMIGA NO CAMINHO
							if(peca[localLinha - i][localColum - i].getTipoPeca() == 3 || peca[localLinha - i][localColum - i].getTipoPeca() == 4) {
								temInimigo++;
								if (temInimigo == 1 && inimigoCapturado == false) {
									inimigoLinha = localLinha - i;
									inimigoColum = localColum - i;
									inimigoCapturado = true;
								}
							}
							//VERIFICA��O DE PE�A ALIADA NO CAMINHO
							else if(peca[localLinha - i][localColum - i].getTipoPeca() == 1 || peca[localLinha - i][localColum - i].getTipoPeca() == 2) {
								return false;
							}
						}
						
						if(temInimigo == 1) {
							peca[inimigoLinha][inimigoColum].setIcon(null);
							peca[inimigoLinha][inimigoColum].setTipoPeca(0);
							
							return true;
						}
						else if(temInimigo == 0) {
							return true;
						}
					}
					//Movimenta��o pra Direita (cima)
					else if (destinoColum > localColum) {
						//For para verificar se o caminho da Coroa n�o tem obstaculos
						for (int i = 1; i < distanciaColum; i++) {
							//VERIFICA��O DE PE�A INIMIGA NO CAMINHO
							if(peca[localLinha - i][localColum + i].getTipoPeca() == 3 || peca[localLinha - i][localColum + i].getTipoPeca() == 4) {
								temInimigo++;
								if (temInimigo == 1 && inimigoCapturado == false) {
									inimigoLinha = localLinha - i;
									inimigoColum = localColum + i;
									inimigoCapturado = true;
								}
							}
							//VERIFICA��O DE PE�A ALIADA NO CAMINHO
							else if(peca[localLinha - i][localColum + i].getTipoPeca() == 1 || peca[localLinha - i][localColum + i].getTipoPeca() == 2) {
								return false;
								
							}
						}
						if(temInimigo == 1) {
							peca[inimigoLinha][inimigoColum].setIcon(null);
							peca[inimigoLinha][inimigoColum].setTipoPeca(0);
							
							return true;
						}
						else if(temInimigo == 0) {
							return true;
						}
					}
				
				}//FIM DA MIVIMENTA��O PRA CIMA
				
			}//FiM DA MOVIMENTA��O DA COROA BRANCA
			
			/* ********************* *
			 * FIM DAS PE�AS BRANCAS *
			 * ********************* */
			
			
			/* ******************* *
			 * COME�O PE�AS PRETAS *
			 * ******************* */
			//CACHORRO PRETO = tipoPe�a 3;
			//COROA PRETA = tipoPe�a 4;
			
			//Verifica��o para pe�a preta (N�O COROA) mover uma casa (n�o pode para baixo)
			if ((distanciaColum == 1 && distanciaLinha == 1) && (peca[localLinha][localColum].getTipoPeca() == 3)) {
					
				if (destinoLinha < localLinha) {
						
					if (destinoColum < localColum || destinoColum > localColum) return true;
						
					//else if (destinoColum > localColum) return true;
				}
			}
			//FINAL DA MIVIMENTA��O DE UMA CASA
		
			
			//Verifica��o Para pe�a preta (N�O COROA) "COMER" uma branca
			if((distanciaColum == 2 && distanciaLinha == 2) && (peca[localLinha][localColum].getTipoPeca() == 3)) {
				
				//verifica a pe�a entre o Destino e o local atual, sentido pra cima
				if(destinoLinha < localLinha) {
					
					//Pe�as brancas a esqueda da pe�a
					if (destinoColum < localColum) {
						
						if (peca[localLinha - 1][localColum -1].getTipoPeca() == 1 || peca[localLinha - 1][localColum - 1].getTipoPeca() == 2) {
							
							peca[localLinha - 1][localColum -1].setIcon(null);
							peca[localLinha - 1][localColum -1].setTipoPeca(0);
							return true;
						}	
					}
					//pe�as bracas a direita da pe�a
					else if(destinoColum > localColum) {
						
						if (peca[localLinha - 1][localColum + 1].getTipoPeca() == 1 || peca[localLinha - 1][localColum + 1].getTipoPeca() == 2) {
							
							peca[localLinha - 1][localColum + 1].setIcon(null);
							peca[localLinha - 1][localColum + 1].setTipoPeca(0);
							return true;
						}
					}
				}
			} //FINAL DA MOVIMENTA��O DE COMER. CACHORRO PRETO
			
			//Movimenta��o da COROA PRETA
			if (((distanciaColum < 8 && distanciaColum > 0 ) && (distanciaLinha < 8 && distanciaLinha > 0) && distanciaLinha == distanciaColum) && (peca[localLinha][localColum].getTipoPeca() == 4)) {
				
				//Movimenta��o pra BAIXO
				if (destinoLinha > localLinha) {
					
					//Movimenta��o pra Esquerda (baixo)
					if (destinoColum < localColum) {
						//For para verificar se o caminho da Coroa n�o tem obstaculos
						for (int i = 1; i < distanciaColum; i++) {
							//VERIFICA��O DE PE�A INIMIGA NO CAMINHO
							if(peca[localLinha + i][localColum - i].getTipoPeca() == 1 || peca[localLinha + i][localColum - i].getTipoPeca() == 2) {
								temInimigo++;
								if (temInimigo == 1 && inimigoCapturado == false) {
									inimigoLinha = localLinha + i;
									inimigoColum = localColum - i;
									inimigoCapturado = true;
								}
							}
							//VERIFICA��O DE PE�A ALIADA NO CAMINHO
							else if(peca[localLinha + i][localColum - i].getTipoPeca() == 3 || peca[localLinha + i][localColum - i].getTipoPeca() == 4) {
								return false;
							}
						}
						
						if(temInimigo == 1) {
							peca[inimigoLinha][inimigoColum].setIcon(null);
							peca[inimigoLinha][inimigoColum].setTipoPeca(0);
							
							return true;
						}
						else if(temInimigo == 0) {
							return true;
						}
					}
					//Movimenta��o pra Direita (baixo)
					else if (destinoColum > localColum) {
						//For para verificar se o caminho da Coroa n�o tem obstaculos
						for (int i = 1; i < distanciaColum; i++) {
							//VERIFICA��O DE PE�A INIMIGA NO CAMINHO
							if(peca[localLinha + i][localColum + i].getTipoPeca() == 1 || peca[localLinha + i][localColum + i].getTipoPeca() == 2) {
								temInimigo++;
								if (temInimigo == 1 && inimigoCapturado == false) {
									inimigoLinha = localLinha + i;
									inimigoColum = localColum + i;
									inimigoCapturado = true;
								}
							}
							//VERIFICA��O DE PE�A ALIADA NO CAMINHO
							else if(peca[localLinha + i][localColum + i].getTipoPeca() == 3 || peca[localLinha + i][localColum + i].getTipoPeca() == 4) {
								return false;
							}
						}
						
						if(temInimigo == 1) {
							peca[inimigoLinha][inimigoColum].setIcon(null);
							peca[inimigoLinha][inimigoColum].setTipoPeca(0);
							
							return true;
						}
						else if(temInimigo == 0) {
							return true;
						}
					}
				}//FIM DO MOVIMENTO PRA BAIXO
				
				//Movimento pra CIMA
				else if (destinoLinha < localLinha) {
					
					//Movimenta��o pra Esquerda (cima)
					if (destinoColum < localColum) {
						//For para verificar se o caminho da Coroa n�o tem obstaculos
						for (int i = 1; i < distanciaColum; i++) {
							//VERIFICA��O DE PE�A INIMIGA NO CAMINHO
							if(peca[localLinha - i][localColum - i].getTipoPeca() == 1 || peca[localLinha - i][localColum - i].getTipoPeca() == 2) {
								temInimigo++;
								if (temInimigo == 1 && inimigoCapturado == false) {
									inimigoLinha = localLinha - i;
									inimigoColum = localColum - i;
									inimigoCapturado = true;
								}
							}
							//VERIFICA��O DE PE�A ALIADA NO CAMINHO
							else if(peca[localLinha - i][localColum - i].getTipoPeca() == 3 || peca[localLinha - i][localColum - i].getTipoPeca() == 4) {
								return false;
							}
						}
						
						if(temInimigo == 1) {
							peca[inimigoLinha][inimigoColum].setIcon(null);
							peca[inimigoLinha][inimigoColum].setTipoPeca(0);
							
							return true;
						}
						else if(temInimigo == 0) {
							return true;
						}
					}
					//Movimenta��o pra Direita (cima)
					else if (destinoColum > localColum) {
						//For para verificar se o caminho da Coroa n�o tem obstaculos
						for (int i = 1; i < distanciaColum; i++) {
							//VERIFICA��O DE PE�A INIMIGA NO CAMINHO
							if(peca[localLinha - i][localColum + i].getTipoPeca() == 1 || peca[localLinha - i][localColum + i].getTipoPeca() == 2) {
								temInimigo++;
								if (temInimigo == 1 && inimigoCapturado == false) {
									inimigoLinha = localLinha - i;
									inimigoColum = localColum + i;
									inimigoCapturado = true;
								}
							}
							//VERIFICA��O DE PE�A ALIADA NO CAMINHO
							else if(peca[localLinha - i][localColum + i].getTipoPeca() == 3 || peca[localLinha - i][localColum + i].getTipoPeca() == 4) {
								return false;
								
							}
						}
						if(temInimigo == 1) {
							peca[inimigoLinha][inimigoColum].setIcon(null);
							peca[inimigoLinha][inimigoColum].setTipoPeca(0);
							
							return true;
						}
						else if(temInimigo == 0) {
							return true;
						}
					}
				
				}//FIM DA MIVIMENTA��O PRA CIMA
				
			}//FiM DA MOVIMENTA��O DA COROA PRETA
			
			/* ******************** *
			 *  FIM DAS PE�AS PRETAS *
			 * ******************** */		
			
			 return false;
		}
}
