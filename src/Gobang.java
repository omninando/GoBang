import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Gobang extends JFrame{
	
	private JLabel[][] tabuleiro;
	private int[][] tabuleiroLogico;
	static final int DIMENSAO_TABULEIRO = 13;
	static final int TAMANHO_UNI = 35;
	private int numeroJogador;
	private boolean fimJogo;
	
	public Gobang(){
		tabuleiro = new JLabel[DIMENSAO_TABULEIRO][DIMENSAO_TABULEIRO];
		tabuleiroLogico = new int[DIMENSAO_TABULEIRO][DIMENSAO_TABULEIRO];
		numeroJogador = 1;
		fimJogo = false;
		setLayout(null);
		setSize(700,500);
		setResizable(false);
		for(int lin = 0; lin < DIMENSAO_TABULEIRO;lin++){
			for(int col = 0; col < DIMENSAO_TABULEIRO;col++){
				tabuleiroLogico[lin][col] = 0;
				tabuleiro[lin][col] = new JLabel();
				limpaCampo(lin, col);
				add(tabuleiro[lin][col]);
				tabuleiro[lin][col].setBounds((col*TAMANHO_UNI), (lin*TAMANHO_UNI), TAMANHO_UNI, TAMANHO_UNI);
				tabuleiro[lin][col].setVisible(true);
			}
		}
		
		JButton btlimpar = new JButton("Novo Jogo");
		btlimpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for(int c1 = 0; c1 < DIMENSAO_TABULEIRO;c1++){
					for(int c2 = 0; c2 < DIMENSAO_TABULEIRO;c2++){
						tabuleiroLogico[c1][c2] = 0;
						limpaCampo(c1,c2);
					}
				}
			}
		});
		add(btlimpar);
		btlimpar.setBounds(470, 100,100,40);
		btlimpar.setVisible(true);
		
		JPanel paineltab = new JPanel();
		paineltab.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getY() < (TAMANHO_UNI+1)*DIMENSAO_TABULEIRO && e.getX() < TAMANHO_UNI*DIMENSAO_TABULEIRO){
					colocarPeca((e.getY()/TAMANHO_UNI),e.getX()/TAMANHO_UNI );
					validate();
					consultaGanhador();
				}
			}
		});
		add(paineltab);
		paineltab.setBounds(0, 0, 455, 455);
		paineltab.setVisible(true);
	}
	
	
	private void limpaCampo(int linha, int coluna){
		if(linha == 0 && coluna == 0){
			tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_superior_esquerda.gif")));
		}else if(linha == 0 && coluna == 12){
			tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_superior_direita.gif")));
		}else if(linha == 12 && coluna == 0){
			tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_inferior_esquerda.gif")));
		}else if(linha == 12 && coluna == 12){
			tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_inferior_direita.gif")));
		}else if(linha == 0){
			tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_superior.gif")));
		}else if(coluna == 0){
			tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_esquerda.gif")));
		}else if(linha == 12){
			tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_inferior.gif")));
		}else if(coluna == 12){
			tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_direita.gif")));
		}else{
			tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_central.gif")));
		}
	}
	
	private boolean consultaCampo(int linha, int coluna) {
		if(tabuleiroLogico[linha][coluna]==0 && !fimJogo){
			return true;
		}else{
			return false;
		}
	}
	
	public void colocarPeca(int linha, int coluna) {
		if(consultaCampo(linha, coluna)){
			if(numeroJogador == 1){
				if(linha == 0 && coluna == 0){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_superior_esquerda_prt.gif")));
				}else if(linha == 0 && coluna == 12){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_superior_direita_prt.gif")));
				}else if(linha == 12 && coluna == 0){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_inferior_esquerda_prt.gif")));
				}else if(linha == 12 && coluna == 12){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_inferior_direita_prt.gif")));
				}else if(linha == 0){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_superior_prt.gif")));
				}else if(coluna == 0){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_esquerda_prt.gif")));
				}else if(linha == 12){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_inferior_prt.gif")));
				}else if(coluna == 12){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_direita_prt.gif")));
				}else{
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_central_prt.gif")));
				}
				tabuleiroLogico[linha][coluna] = numeroJogador;
				numeroJogador = 2;
			}else if(numeroJogador == 2){
				if(linha == 0 && coluna == 0){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_superior_esquerda_brc.gif")));
				}else if(linha == 0 && coluna == 12){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_superior_direita_brc.gif")));
				}else if(linha == 12 && coluna == 0){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_inferior_esquerda_brc.gif")));
				}else if(linha == 12 && coluna == 12){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_inferior_direita_brc.gif")));
				}else if(linha == 0){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_superior_brc.gif")));
				}else if(coluna == 0){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_esquerda_brc.gif")));
				}else if(linha == 12){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_inferior_brc.gif")));
				}else if(coluna == 12){
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_borda_direita_brc.gif")));
				}else{
					tabuleiro[linha][coluna].setIcon(new ImageIcon(getClass().getResource("/icones/icone_central_brc.gif")));
				}
				tabuleiroLogico[linha][coluna] = numeroJogador;
				numeroJogador = 1;
			}
		}
	}
	
	
	private void consultaGanhador(){
		int ganhador = 0;
		int preto = 1;
		int branco = 2;
		for(int linha = 0; linha < DIMENSAO_TABULEIRO-1;linha++){
			for(int coluna = 0; coluna < DIMENSAO_TABULEIRO-1;coluna++){
				if(tabuleiroLogico[linha][coluna] != 0){
					if(tabuleiroLogico[linha][coluna]== preto){
						if(linha < DIMENSAO_TABULEIRO-5 && coluna < DIMENSAO_TABULEIRO-5){
							if(tabuleiroLogico[linha+1][coluna+1]==preto && tabuleiroLogico[linha+2][coluna+2]==preto && tabuleiroLogico[linha+3][coluna+3]==preto && tabuleiroLogico[linha+4][coluna+4]==preto){
								ganhador = preto;
							}
						}
						if(linha < DIMENSAO_TABULEIRO-5){
							if(tabuleiroLogico[linha+1][coluna]==preto && tabuleiroLogico[linha+2][coluna]==preto && tabuleiroLogico[linha+3][coluna]==preto && tabuleiroLogico[linha+4][coluna]==preto){
								ganhador = preto;
							}
						}
						if(coluna < DIMENSAO_TABULEIRO-5){
							if(tabuleiroLogico[linha][coluna+1]==preto && tabuleiroLogico[linha][coluna+2]==preto && tabuleiroLogico[linha][coluna+3]==preto && tabuleiroLogico[linha][coluna+4]==preto){
								ganhador = preto;
							}
						}
						if(linha < DIMENSAO_TABULEIRO-5 && coluna > 4){
							if(tabuleiroLogico[linha+1][coluna-1]==preto && tabuleiroLogico[linha+2][coluna-2]==preto && tabuleiroLogico[linha+3][coluna-3]==preto && tabuleiroLogico[linha+4][coluna-4]==preto){
								ganhador = preto;
							}
						}
					}else if(tabuleiroLogico[linha][coluna]==branco){
						if(linha < DIMENSAO_TABULEIRO-5 && coluna < DIMENSAO_TABULEIRO-5){
							if(tabuleiroLogico[linha+1][coluna+1]==branco && tabuleiroLogico[linha+2][coluna+2]==branco && tabuleiroLogico[linha+3][coluna+3]==branco && tabuleiroLogico[linha+4][coluna+4]==branco){
								ganhador = branco;
							}
						}
						if(linha < DIMENSAO_TABULEIRO-5){
							if(tabuleiroLogico[linha+1][coluna]==preto && tabuleiroLogico[linha+2][coluna]==branco && tabuleiroLogico[linha+3][coluna]==branco && tabuleiroLogico[linha+4][coluna]==branco){
								ganhador = branco;
							}
						}
						if(coluna < DIMENSAO_TABULEIRO-5){
							if(tabuleiroLogico[linha][coluna+1]==branco && tabuleiroLogico[linha][coluna+2]==branco && tabuleiroLogico[linha][coluna+3]==branco && tabuleiroLogico[linha][coluna+4]==branco){
								ganhador = branco;
							}
						}
						if(linha < DIMENSAO_TABULEIRO-5 && coluna > 4){
							if(tabuleiroLogico[linha+1][coluna-1]==branco && tabuleiroLogico[linha+2][coluna-2]==branco && tabuleiroLogico[linha+3][coluna-3]==branco && tabuleiroLogico[linha+4][coluna-4]==branco){
								ganhador = branco;
							}
						}
					}
				}
			}
		}
		if(ganhador == 1 && !fimJogo){
			fimJogo = true;
			JOptionPane.showConfirmDialog(null,"O Jogador 1(Preto) ganhou!","Fim de partida",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
		}else if(ganhador == 2 && !fimJogo){
			fimJogo = true;
			JOptionPane.showConfirmDialog(null,"O Jogador 2(Branco) ganhou!","Fim de partida",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	//criando e instanciando o objeto mesa de jogo Gobang
	public static void main(String[] args) throws IOException {
		Gobang mesa = new Gobang();
		mesa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mesa.setVisible(true);
	}
}
