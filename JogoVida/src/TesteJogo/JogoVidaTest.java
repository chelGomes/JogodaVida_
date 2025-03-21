package TesteJogo;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import Jogo.JogoVida;

public class JogoVidaTest {

	 private JogoVida jogo;

	    /**
	     * Configura��o inicial antes de cada teste.
	     * Cria um novo jogo da vida com grid 5x5.
	     */
	    @Before
	    public void setUp() {
	        jogo = new JogoVida(5);
	    }

	    /**
	     * Testa se uma c�lula isolada morre por solid�o na pr�xima gera��o.
	     * Apenas uma c�lula � ativada e espera-se que ela morra.
	     */
	    @Test
	    public void testCelulaMorrePorSolid�o() {
	        jogo.setCelula(2, 2, 1); // Define uma c�lula viva isolada
	        jogo.proximaGeracao();   // Calcula a pr�xima gera��o
	        assertEquals(0, jogo.getCelula(2, 2)); // A c�lula deve morrer
	    }

	    /**
	     * Testa se uma c�lula morre por superpopula��o.
	     * C�lulas ao redor fazem com que a c�lula central morra na pr�xima gera��o.
	     */
	    @Test
	    public void testCelulaMorrePorSuperpopulacao() {
	        // Configura��o de c�lulas para simular superpopula��o
	        jogo.setCelula(1, 1, 1);
	        jogo.setCelula(1, 2, 1);
	        jogo.setCelula(1, 3, 1);
	        jogo.setCelula(2, 1, 1);
	        jogo.setCelula(2, 2, 1); // C�lula central que deve morrer

	        jogo.proximaGeracao(); // Calcula a pr�xima gera��o

	        assertEquals(0, jogo.getCelula(2, 2)); // A c�lula central deve morrer
	    }

	    /**
	     * Testa se uma c�lula continua viva na pr�xima gera��o.
	     * A c�lula deve permanecer viva se tiver 2 ou 3 vizinhos vivos.
	     */
	    @Test
	    public void testCelulaContinuaViva() {
	        // Configura��o de um padr�o onde a c�lula deve continuar viva
	        jogo.setCelula(1, 1, 1);
	        jogo.setCelula(1, 2, 1);
	        jogo.setCelula(2, 1, 1);

	        jogo.proximaGeracao(); // Calcula a pr�xima gera��o

	        assertEquals(1, jogo.getCelula(2, 2)); // A c�lula deve permanecer viva
	    }

	    /**
	     * Testa se uma c�lula nasce por reprodu��o.
	     * Se uma c�lula morta tiver exatamente 3 vizinhos vivos, ela nasce na pr�xima gera��o.
	     */
	    @Test
	    public void testCelulaNascePorReproducao() {
	        // Configura��o de c�lulas vizinhas para permitir reprodu��o
	        jogo.setCelula(1, 1, 1);
	        jogo.setCelula(1, 2, 1);
	        jogo.setCelula(2, 1, 1);

	        jogo.proximaGeracao(); // Calcula a pr�xima gera��o

	        assertEquals(1, jogo.getCelula(2, 2)); // A c�lula deve nascer
	    }
	    
	    /**
	     * Testa se o m�todo getGridAsString() retorna a representa��o correta do grid.
	     * O grid � inicializado com um padr�o espec�fico e comparado com a sa�da esperada.
	     */
	    @Test
	    public void testGetGridAsString() {
	        JogoVida jogo = new JogoVida(3);
	        jogo.setCelula(0, 0, 1);
	        jogo.setCelula(1, 1, 1);
	        jogo.setCelula(2, 2, 1);

	        // Representa��o esperada do grid
	        String expected = "1 0 0 \n0 1 0 \n0 0 1 \n";
	        
	        assertEquals(expected, jogo.getGridAsString()); // Verifica se a sa�da � a esperada
	    }
	    
	    /**
	     * Testa se o m�todo imprimirGrid() exibe corretamente a representa��o do grid no console.
	     * A sa�da do console � capturada e verificada.
	     */
	    @Test
	    public void testImprimirGrid() {
	        // Redireciona a sa�da do console para capturar a impress�o
	        ByteArrayOutputStream saidaConsole = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(saidaConsole));

	        JogoVida jogo = new JogoVida(5);
	        jogo.setCelula(1, 2, 1);
	        jogo.setCelula(2, 2, 1);
	        jogo.setCelula(3, 2, 1);
	        jogo.imprimirGrid(); // Imprime o grid no console

	        // Restaura a sa�da original
	        System.setOut(System.out);

	        // Verifica se a sa�da cont�m c�lulas vivas na posi��o esperada
	        String saida = saidaConsole.toString();
	        assertTrue(saida.contains("O")); // Verifica se o caractere 'O' foi impresso
	    }
	    
	    
	    /// melhorias no teste de cobertura /////
	    
	    @Test
	    public void testCelulasNasBordas() {
	        // Configura��o inicial: define c�lulas vivas nas bordas da grade
	        jogo.setCelula(0, 0, 1); // C�lula viva na posi��o (0, 0)
	        jogo.setCelula(0, 1, 1); // C�lula viva na posi��o (0, 1)
	        jogo.setCelula(1, 0, 1); // C�lula viva na posi��o (1, 0)

	        // Executa a pr�xima gera��o do Jogo da Vida
	        jogo.proximaGeracao();

	        // Verifica se a c�lula na posi��o (0, 0) continua viva ap�s a evolu��o
	        // De acordo com as regras do Jogo da Vida, uma c�lula com 2 ou 3 vizinhos vivos continua viva
	        assertEquals(1, jogo.getCelula(0, 0));
	    }

	    @Test
	    public void testPadraoOscilador() {
	        // Configura��o inicial: define um padr�o oscilador (blinker) na vertical
	        jogo.setCelula(1, 2, 1); // C�lula viva na posi��o (1, 2)
	        jogo.setCelula(2, 2, 1); // C�lula viva na posi��o (2, 2)
	        jogo.setCelula(3, 2, 1); // C�lula viva na posi��o (3, 2)

	        // Executa a pr�xima gera��o do Jogo da Vida
	        jogo.proximaGeracao();

	        // Verifica o estado das c�lulas ap�s a evolu��o
	        // O padr�o oscilador deve girar para a horizontal, ent�o:
	        assertEquals(0, jogo.getCelula(1, 2)); // C�lula (1, 2) deve morrer
	        assertEquals(1, jogo.getCelula(2, 2)); // C�lula (2, 2) deve continuar viva
	        assertEquals(0, jogo.getCelula(3, 2)); // C�lula (3, 2) deve morrer
	    }
	    
	    /* */
	    


	    @Test
	    public void testCenarioVivoCom2Vizinhos() {
	        int[][] tabuleiro = {
	            {1, 1},
	            {0, 1}
	        };

	        // O estado esperado ap�s evoluir
	        int[][] tabuleiroEsperado = {
	            {1, 1},
	            {1, 1}
	        };

	        // Chama a fun��o evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado � igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioVivoCom3Vizinhos() {
	        int[][] tabuleiro = {
	            {1, 1},
	            {1, 1}
	        };

	        // O estado esperado ap�s evoluir
	        int[][] tabuleiroEsperado = {
	            {1, 1},
	            {1, 1}
	        };

	        // Chama a fun��o evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado � igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioVivoCom1Vizinhos() {
	        int[][] tabuleiro = {
	            {1, 0},
	            {0, 1}
	        };

	        // O estado esperado ap�s evoluir
	        int[][] tabuleiroEsperado = {
	            {0, 0},
	            {0, 0}
	        };

	        // Chama a fun��o evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado � igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioVivoCom4Vizinhos() {
	        int[][] tabuleiro = {
	            {1, 1, 0},
	            {1, 0, 1},
	            {0, 1, 1}
	        };

	        // O estado esperado ap�s evoluir
	        int[][] tabuleiroEsperado = {
	            {1, 1, 1},
	            {1, 0, 1},
	            {1, 1, 1}
	        };

	        // Chama a fun��o evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado � igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioMortoCom3Vizinhos() {
	        int[][] tabuleiro = {
	            {0, 1},
	            {1, 1}
	        };

	        // O estado esperado ap�s evoluir
	        int[][] tabuleiroEsperado = {
	            {1, 1},
	            {1, 1}
	        };

	        // Chama a fun��o evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado � igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioMortoComMenosDe3Vizinhos() {
	        int[][] tabuleiro = {
	            {0, 1},
	            {0, 1}
	        };

	        // O estado esperado ap�s evoluir
	        int[][] tabuleiroEsperado = {
	            {0, 0},
	            {0, 0}
	        };

	        // Chama a fun��o evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado � igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioMortoComMaisDe3Vizinhos() {
	        int[][] tabuleiro = {
	            {1, 1, 1},
	            {1, 0, 1},
	            {1, 1, 1}
	        };

	        // O estado esperado ap�s evoluir
	        int[][] tabuleiroEsperado = {
	            {1, 1, 1},
	            {1, 0, 1},
	            {1, 1, 1}
	        };

	        // Chama a fun��o evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado � igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioC�lulaVivaComZeroVizinhos() {
	        int[][] tabuleiro = {
	            {0, 0},
	            {0, 0}
	        };

	        // O estado esperado ap�s evoluir
	        int[][] tabuleiroEsperado = {
	            {0, 0},
	            {0, 0}
	        };

	        // Chama a fun��o evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado � igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioC�lulaVivaCom8Vizinhos() {
	        int[][] tabuleiro = {
	            {1, 1, 1},
	            {1, 1, 1},
	            {1, 1, 1}
	        };

	        // O estado esperado ap�s evoluir
	        int[][] tabuleiroEsperado = {
	            {1, 1, 1},
	            {1, 1, 1},
	            {1, 1, 1}
	        };

	        // Chama a fun��o evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado � igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioTabuleiroVazio() {
	        int[][] tabuleiro = {};

	        // O estado esperado ap�s evoluir
	        int[][] tabuleiroEsperado = {};

	        // Chama a fun��o evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado � igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }
	    
	    /*Casos de Teste arcos, n�s e caminho */
	    
	  @Test
	    public void testTodosNos() {
	        // Tabuleiro de teste
	        int[][] tabuleiro = {
	            {0, 1, 0},
	            {0, 1, 0},
	            {0, 1, 0}
	        };

	        // Pr�xima gera��o esperada
	        int[][] esperado = {
	            {0, 0, 0},
	            {1, 1, 1},
	            {0, 0, 0}
	        };

	        int[][] resultado = JogoVida.proximaGeracao(tabuleiro);
	        assertArrayEquals(esperado, resultado);
	    }
	    
	    @Test
	    public void testTodosArcos() {
	        // Tabuleiro de teste
	        int[][] tabuleiro = {
	            {1, 0, 1},
	            {0, 1, 0},
	            {1, 0, 1}
	        };

	        // Pr�xima gera��o esperada
	        int[][] esperado = {
	            {0, 1, 0},
	            {1, 0, 1},
	            {0, 1, 0}
	        };

	        int[][] resultado = JogoVida.proximaGeracao(tabuleiro);
	        assertArrayEquals(esperado, resultado);
	    }
	    
	    @Test
	    public void testTodosCaminhos() {
	        // Tabuleiro de teste
	        int[][] tabuleiro = {
	            {1, 1, 0},
	            {1, 0, 0},
	            {0, 0, 0}
	        };

	        // Pr�xima gera��o esperada
	        int[][] esperado = {
	            {1, 1, 0},
	            {1, 1, 0},
	            {0, 0, 0}
	        };

	        int[][] resultado = JogoVida.proximaGeracao(tabuleiro);
	        assertArrayEquals(esperado, resultado);
	    }
	    
	//// Caso de Teste de Mutante Vivo ///////
	    
	    
	    @Test
	    public void testCT1() {
	        int[][] tabuleiro = {
	            {0, 0, 0},
	            {1, 1, 1},
	            {0, 0, 0}
	        };
	        int[][] esperado = {
	            {0, 1, 0},
	            {0, 1, 0},
	            {0, 1, 0}
	        };
	        int[][] resultado = JogoVida.proximaGeracao(tabuleiro);
	        assertArrayEquals(esperado, resultado);
	    }

	    @Test
	    public void testCT2() {
	        int[][] tabuleiro = {
	            {0, 1, 0},
	            {0, 0, 1},
	            {0, 1, 0}
	        };
	        int[][] esperado = {
	            {0, 0, 0},
	            {0, 1, 1},
	            {0, 0, 0}
	        };
	        int[][] resultado = JogoVida.proximaGeracao(tabuleiro);
	        assertArrayEquals(esperado, resultado);
	    }

	    @Test
	    public void testCT3() {
	        int[][] tabuleiro = {
	            {1, 1, 1},
	            {1, 1, 1},
	            {1, 1, 1}
	        };
	        int[][] esperado = {
	            {1, 0, 1},
	            {0, 0, 0},
	            {1, 0, 1}
	        };
	        int[][] resultado = JogoVida.proximaGeracao(tabuleiro);
	        assertArrayEquals(esperado, resultado);
	    }

	    @Test
	    public void testCT4() {
	        int[][] tabuleiro = {
	            {0, 0, 0},
	            {0, 1, 0},
	            {0, 0, 0}
	        };
	        int[][] esperado = {
	            {0, 0, 0},
	            {0, 0, 0},
	            {0, 0, 0}
	        };
	        int[][] resultado = JogoVida.proximaGeracao(tabuleiro);
	        assertArrayEquals(esperado, resultado);
	    }
	    
	   
}
