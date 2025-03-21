package TesteJogo;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import Jogo.JogoVida;

public class PiTest {
	private JogoVida jogo;

    /**
     * Configuração inicial antes de cada teste.
     * Cria um novo jogo da vida com grid 5x5.
     */
    @Before
    public void setUp() {
        jogo = new JogoVida(5);
    }
	
	@Test
	public void testCelulasNaBorda() {
	    jogo.setCelula(0, 0, 1);
	    jogo.setCelula(0, 1, 1);
	    jogo.setCelula(1, 0, 1);
	    jogo.proximaGeracao();
	    assertEquals(1, jogo.getCelula(0, 0));
	}
	
	@Test
	public void testGridTodoMorto() {
	    jogo.proximaGeracao();
	    for (int i = 0; i < 5; i++) {
	        for (int j = 0; j < 5; j++) {
	            assertEquals(0, jogo.getCelula(i, j));
	        }
	    }
	}
	
	@Test
	public void testGridTodoVivo() {
	    for (int i = 0; i < 5; i++) {
	        for (int j = 0; j < 5; j++) {
	            jogo.setCelula(i, j, 1);
	        }
	    }
	    jogo.proximaGeracao();
	    // Verifique se as células centrais morrem por superpopulação
	    assertEquals(0, jogo.getCelula(2, 2));
	}
	
	@Test
	public void testPadraoGlider() {
	    jogo.setCelula(1, 2, 1);
	    jogo.setCelula(2, 3, 1);
	    jogo.setCelula(3, 1, 1);
	    jogo.setCelula(3, 2, 1);
	    jogo.setCelula(3, 3, 1);
	    jogo.proximaGeracao();
	    // Verifique se o padrão se move corretamente
	    assertEquals(1, jogo.getCelula(2, 1));
	}
	
	/////// Teste com 100% ///
	
	@Test
	public void testInicializacaoGrid() {
	    for (int i = 0; i < 5; i++) {
	        for (int j = 0; j < 5; j++) {
	            assertEquals(0, jogo.getCelula(i, j));
	        }
	    }
	}
	
	@Test
	public void testCelulaMorreComMenosDe2Vizinhos() {
	    jogo.setCelula(1, 1, 1);
	    jogo.setCelula(1, 2, 1);
	    jogo.proximaGeracao();
	    assertEquals(0, jogo.getCelula(1, 1));
	}
	
	@Test
	public void testCelulaMorreComMaisDe3Vizinhos() {
	    jogo.setCelula(1, 1, 1);
	    jogo.setCelula(1, 2, 1);
	    jogo.setCelula(2, 1, 1);
	    jogo.setCelula(2, 2, 1);
	    jogo.setCelula(3, 1, 1);
	    jogo.proximaGeracao();
	    assertEquals(0, jogo.getCelula(2, 2));
	}
	
	@Test
	public void testCelulaNasceCom3Vizinhos() {
	    jogo.setCelula(1, 1, 1);
	    jogo.setCelula(1, 2, 1);
	    jogo.setCelula(2, 1, 1);
	    jogo.proximaGeracao();
	    assertEquals(1, jogo.getCelula(2, 2));
	}
	
	@Test
	public void testCelulaSobreviveCom2ou3Vizinhos() {
	    jogo.setCelula(1, 1, 1);
	    jogo.setCelula(1, 2, 1);
	    jogo.setCelula(2, 1, 1);
	    jogo.proximaGeracao();
	    assertEquals(1, jogo.getCelula(1, 1));
	}
	
	////// ////
	@Test
	public void testCelulaSobreviveCom2Vizinhos() {
	    jogo.setCelula(1, 1, 1);
	    jogo.setCelula(1, 2, 1);
	    jogo.setCelula(2, 1, 1);
	    jogo.proximaGeracao();
	    assertEquals(1, jogo.getCelula(1, 1));
	}
	
	@Test
	public void testCelulaNoCanto() {
	    jogo.setCelula(0, 0, 1);
	    jogo.setCelula(0, 1, 1);
	    jogo.setCelula(1, 0, 1);
	    jogo.proximaGeracao();
	    assertEquals(1, jogo.getCelula(0, 0));
	}
	
	@Test
	public void testCelulaMorreCom0Vizinhos() {
	    jogo.setCelula(2, 2, 1);
	    jogo.proximaGeracao();
	    assertEquals(0, jogo.getCelula(2, 2));
	}
}
	

	
	

