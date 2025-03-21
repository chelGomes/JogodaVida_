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
	     * Configuração inicial antes de cada teste.
	     * Cria um novo jogo da vida com grid 5x5.
	     */
	    @Before
	    public void setUp() {
	        jogo = new JogoVida(5);
	    }

	    /**
	     * Testa se uma célula isolada morre por solidão na próxima geração.
	     * Apenas uma célula é ativada e espera-se que ela morra.
	     */
	    @Test
	    public void testCelulaMorrePorSolidão() {
	        jogo.setCelula(2, 2, 1); // Define uma célula viva isolada
	        jogo.proximaGeracao();   // Calcula a próxima geração
	        assertEquals(0, jogo.getCelula(2, 2)); // A célula deve morrer
	    }

	    /**
	     * Testa se uma célula morre por superpopulação.
	     * Células ao redor fazem com que a célula central morra na próxima geração.
	     */
	    @Test
	    public void testCelulaMorrePorSuperpopulacao() {
	        // Configuração de células para simular superpopulação
	        jogo.setCelula(1, 1, 1);
	        jogo.setCelula(1, 2, 1);
	        jogo.setCelula(1, 3, 1);
	        jogo.setCelula(2, 1, 1);
	        jogo.setCelula(2, 2, 1); // Célula central que deve morrer

	        jogo.proximaGeracao(); // Calcula a próxima geração

	        assertEquals(0, jogo.getCelula(2, 2)); // A célula central deve morrer
	    }

	    /**
	     * Testa se uma célula continua viva na próxima geração.
	     * A célula deve permanecer viva se tiver 2 ou 3 vizinhos vivos.
	     */
	    @Test
	    public void testCelulaContinuaViva() {
	        // Configuração de um padrão onde a célula deve continuar viva
	        jogo.setCelula(1, 1, 1);
	        jogo.setCelula(1, 2, 1);
	        jogo.setCelula(2, 1, 1);

	        jogo.proximaGeracao(); // Calcula a próxima geração

	        assertEquals(1, jogo.getCelula(2, 2)); // A célula deve permanecer viva
	    }

	    /**
	     * Testa se uma célula nasce por reprodução.
	     * Se uma célula morta tiver exatamente 3 vizinhos vivos, ela nasce na próxima geração.
	     */
	    @Test
	    public void testCelulaNascePorReproducao() {
	        // Configuração de células vizinhas para permitir reprodução
	        jogo.setCelula(1, 1, 1);
	        jogo.setCelula(1, 2, 1);
	        jogo.setCelula(2, 1, 1);

	        jogo.proximaGeracao(); // Calcula a próxima geração

	        assertEquals(1, jogo.getCelula(2, 2)); // A célula deve nascer
	    }
	    
	    /**
	     * Testa se o método getGridAsString() retorna a representação correta do grid.
	     * O grid é inicializado com um padrão específico e comparado com a saída esperada.
	     */
	    @Test
	    public void testGetGridAsString() {
	        JogoVida jogo = new JogoVida(3);
	        jogo.setCelula(0, 0, 1);
	        jogo.setCelula(1, 1, 1);
	        jogo.setCelula(2, 2, 1);

	        // Representação esperada do grid
	        String expected = "1 0 0 \n0 1 0 \n0 0 1 \n";
	        
	        assertEquals(expected, jogo.getGridAsString()); // Verifica se a saída é a esperada
	    }
	    
	    /**
	     * Testa se o método imprimirGrid() exibe corretamente a representação do grid no console.
	     * A saída do console é capturada e verificada.
	     */
	    @Test
	    public void testImprimirGrid() {
	        // Redireciona a saída do console para capturar a impressão
	        ByteArrayOutputStream saidaConsole = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(saidaConsole));

	        JogoVida jogo = new JogoVida(5);
	        jogo.setCelula(1, 2, 1);
	        jogo.setCelula(2, 2, 1);
	        jogo.setCelula(3, 2, 1);
	        jogo.imprimirGrid(); // Imprime o grid no console

	        // Restaura a saída original
	        System.setOut(System.out);

	        // Verifica se a saída contém células vivas na posição esperada
	        String saida = saidaConsole.toString();
	        assertTrue(saida.contains("O")); // Verifica se o caractere 'O' foi impresso
	    }
	    
	    
	    /// melhorias no teste de cobertura /////
	    
	    @Test
	    public void testCelulasNasBordas() {
	        // Configuração inicial: define células vivas nas bordas da grade
	        jogo.setCelula(0, 0, 1); // Célula viva na posição (0, 0)
	        jogo.setCelula(0, 1, 1); // Célula viva na posição (0, 1)
	        jogo.setCelula(1, 0, 1); // Célula viva na posição (1, 0)

	        // Executa a próxima geração do Jogo da Vida
	        jogo.proximaGeracao();

	        // Verifica se a célula na posição (0, 0) continua viva após a evolução
	        // De acordo com as regras do Jogo da Vida, uma célula com 2 ou 3 vizinhos vivos continua viva
	        assertEquals(1, jogo.getCelula(0, 0));
	    }

	    @Test
	    public void testPadraoOscilador() {
	        // Configuração inicial: define um padrão oscilador (blinker) na vertical
	        jogo.setCelula(1, 2, 1); // Célula viva na posição (1, 2)
	        jogo.setCelula(2, 2, 1); // Célula viva na posição (2, 2)
	        jogo.setCelula(3, 2, 1); // Célula viva na posição (3, 2)

	        // Executa a próxima geração do Jogo da Vida
	        jogo.proximaGeracao();

	        // Verifica o estado das células após a evolução
	        // O padrão oscilador deve girar para a horizontal, então:
	        assertEquals(0, jogo.getCelula(1, 2)); // Célula (1, 2) deve morrer
	        assertEquals(1, jogo.getCelula(2, 2)); // Célula (2, 2) deve continuar viva
	        assertEquals(0, jogo.getCelula(3, 2)); // Célula (3, 2) deve morrer
	    }
	    
	    /* */
	    


	    @Test
	    public void testCenarioVivoCom2Vizinhos() {
	        int[][] tabuleiro = {
	            {1, 1},
	            {0, 1}
	        };

	        // O estado esperado após evoluir
	        int[][] tabuleiroEsperado = {
	            {1, 1},
	            {1, 1}
	        };

	        // Chama a função evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado é igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioVivoCom3Vizinhos() {
	        int[][] tabuleiro = {
	            {1, 1},
	            {1, 1}
	        };

	        // O estado esperado após evoluir
	        int[][] tabuleiroEsperado = {
	            {1, 1},
	            {1, 1}
	        };

	        // Chama a função evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado é igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioVivoCom1Vizinhos() {
	        int[][] tabuleiro = {
	            {1, 0},
	            {0, 1}
	        };

	        // O estado esperado após evoluir
	        int[][] tabuleiroEsperado = {
	            {0, 0},
	            {0, 0}
	        };

	        // Chama a função evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado é igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioVivoCom4Vizinhos() {
	        int[][] tabuleiro = {
	            {1, 1, 0},
	            {1, 0, 1},
	            {0, 1, 1}
	        };

	        // O estado esperado após evoluir
	        int[][] tabuleiroEsperado = {
	            {1, 1, 1},
	            {1, 0, 1},
	            {1, 1, 1}
	        };

	        // Chama a função evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado é igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioMortoCom3Vizinhos() {
	        int[][] tabuleiro = {
	            {0, 1},
	            {1, 1}
	        };

	        // O estado esperado após evoluir
	        int[][] tabuleiroEsperado = {
	            {1, 1},
	            {1, 1}
	        };

	        // Chama a função evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado é igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioMortoComMenosDe3Vizinhos() {
	        int[][] tabuleiro = {
	            {0, 1},
	            {0, 1}
	        };

	        // O estado esperado após evoluir
	        int[][] tabuleiroEsperado = {
	            {0, 0},
	            {0, 0}
	        };

	        // Chama a função evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado é igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioMortoComMaisDe3Vizinhos() {
	        int[][] tabuleiro = {
	            {1, 1, 1},
	            {1, 0, 1},
	            {1, 1, 1}
	        };

	        // O estado esperado após evoluir
	        int[][] tabuleiroEsperado = {
	            {1, 1, 1},
	            {1, 0, 1},
	            {1, 1, 1}
	        };

	        // Chama a função evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado é igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioCélulaVivaComZeroVizinhos() {
	        int[][] tabuleiro = {
	            {0, 0},
	            {0, 0}
	        };

	        // O estado esperado após evoluir
	        int[][] tabuleiroEsperado = {
	            {0, 0},
	            {0, 0}
	        };

	        // Chama a função evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado é igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioCélulaVivaCom8Vizinhos() {
	        int[][] tabuleiro = {
	            {1, 1, 1},
	            {1, 1, 1},
	            {1, 1, 1}
	        };

	        // O estado esperado após evoluir
	        int[][] tabuleiroEsperado = {
	            {1, 1, 1},
	            {1, 1, 1},
	            {1, 1, 1}
	        };

	        // Chama a função evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado é igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }

	    @Test
	    public void testCenarioTabuleiroVazio() {
	        int[][] tabuleiro = {};

	        // O estado esperado após evoluir
	        int[][] tabuleiroEsperado = {};

	        // Chama a função evoluir
	        int[][] tabuleiroResultado = JogoVida.evoluir(tabuleiro);

	        // Verifica se o resultado é igual ao esperado
	        assertArrayEquals(tabuleiroEsperado, tabuleiroResultado);
	    }
	    
	    /*Casos de Teste arcos, nós e caminho */
	    
	  @Test
	    public void testTodosNos() {
	        // Tabuleiro de teste
	        int[][] tabuleiro = {
	            {0, 1, 0},
	            {0, 1, 0},
	            {0, 1, 0}
	        };

	        // Próxima geração esperada
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

	        // Próxima geração esperada
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

	        // Próxima geração esperada
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
