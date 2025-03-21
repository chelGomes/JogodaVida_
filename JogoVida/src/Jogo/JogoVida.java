package Jogo;

public class JogoVida {
    private int[][] grid;
    private int tamanho;

    public JogoVida(int tamanho) {
        this.tamanho = tamanho;
        this.grid = new int[tamanho][tamanho];
    }
    
    

    public void setCelula(int x, int y, int estado) {
        grid[x][y] = estado;
    }

    public int getCelula(int x, int y) {
        return grid[x][y];
    }

  /*1*/  public void proximaGeracao() {
  /*2*/      int[][] novaGrid = new int[tamanho][tamanho];

  /*3*/      for (int i = 0; i < tamanho; i++) {
  /*4*/          for (int j = 0; j < tamanho; j++) {
  /*4*/              int vizinhosVivos = contarVizinhosVivos(i, j);

 /*5*/               if (grid[i][j] == 1) {
 /*6*/                   if (vizinhosVivos < 2 || vizinhosVivos > 3) {
 /*6*/                       novaGrid[i][j] = 0;
 /*7*/                   } else {
 /*7*/                       novaGrid[i][j] = 1;
 /*7*/                   }
 /*8*/               } else {
 /*9*/                   if (vizinhosVivos == 3) {
 /*9*/                       novaGrid[i][j] = 1;
 /*10*/                   } else {
 /*10*/                       novaGrid[i][j] = 0;
 /*10*/                   }
 /*11*/               }
 /*12*/           }
 /*13*/       }

 /*14*/       grid = novaGrid;
 /*15*/   }

 /*1*/   public int contarVizinhosVivos(int x, int y) {
 /*2*/       int count = 0;

 /*3*/       for (int i = -1; i <= 1; i++) {
 /*4*/           for (int j = -1; j <= 1; j++) {
 /*5*/               if (i == 0 && j == 0) continue;

 /*6*/               int novoX = x + i;
 /*7*/               int novoY = y + j;

 /*8*/               if (novoX >= 0 && novoX < tamanho && novoY >= 0 && novoY < tamanho) {
 /*8*/                   count += grid[novoX][novoY];
 /*8*/               }
 /*9*/           }
 /*10*/       }

 /*11*/       return count;
 /*12*/   }

   
   
/*1*/    public String getGridAsString() {
/*2*/        StringBuilder sb = new StringBuilder();
/*3*/        for (int i = 0; i < tamanho; i++) {
/*4*/            for (int j = 0; j < tamanho; j++) {
/*4*/                sb.append(grid[i][j]).append(" ");
/*4*/            }
/*5*/            sb.append("\n");
/*5*/        }
 /*6*/       return sb.toString();
 /*6*/   }

    
    
/*1*/    public void imprimirGrid() {
/*2*/        for (int i = 0; i < grid.length; i++) {
/*3*/            for (int j = 0; j < grid[i].length; j++) {
/*3*/                System.out.print(grid[i][j] == 1 ? "O" : "."); // Células vivas como 'O', mortas como '.'
/*3*/            }
/*4*/            System.out.println(); // Quebra de linha no final de cada linha do grid
 /*4*/       }
 /*5*/   }
    
  /* */ 
 // Função que evolui o tabuleiro do Jogo da Vida
/*1*/    public static int[][] evoluir(int[][] tabuleiro) {
/*2*/        int linhas = tabuleiro.length;
/*2*/        int colunas = tabuleiro[0].length;
/*2*/        int[][] novoTabuleiro = new int[linhas][colunas];

/*3*/        for (int i = 0; i < linhas; i++) {
/*4*/            for (int j = 0; j < colunas; j++) {
/*4*/                int vizinhos = contarVizinhos(tabuleiro, i, j);
/*5*/                if (tabuleiro[i][j] == 1) {
                    // Célula viva
/*5*/                    novoTabuleiro[i][j] = (vizinhos == 2 || vizinhos == 3) ? 1 : 0;
/*6*/                } else {
                    // Célula morta
/*6*/                    novoTabuleiro[i][j] = (vizinhos == 3) ? 1 : 0;
/*6*/                }
/*7*/            }
/*8*/        }
/*9*/        return novoTabuleiro;
/*9*/    }

    // Função que conta o número de vizinhos vivos
/*1*/    public static int contarVizinhos(int[][] tabuleiro, int i, int j) {
/*2*/        int vizinhos = 0;
/*3*/        for (int x = -1; x <= 1; x++) {
/*4*/            for (int y = -1; y <= 1; y++) {
/*4*/                if (x == 0 && y == 0) continue; // Ignora a célula central
/*4*/                int xi = i + x, yi = j + y;
/*5*/                if (xi >= 0 && xi < tabuleiro.length && yi >= 0 && yi < tabuleiro[0].length) {
/*5*/                    vizinhos += tabuleiro[xi][yi];
 /*5*/               }
 /*6*/           }
 /*6*/       }
 /*7*/       return vizinhos;
 /*7*/   }



public static int[][] proximaGeracao(int[][] tabuleiro) {
    // Implementação errada que não altera o tabuleiro
    return tabuleiro;
}


}





