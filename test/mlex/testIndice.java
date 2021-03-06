package mlex;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

import mlex.Indice;
import mlex.Jogo;

public class testIndice {
	Indice indiceTeste;
	Indice indiceTemp;
	Jogo jogoTeste1;
	Jogo jogoTeste2;
	Jogo jogoTeste3;
	String pathObjetoIndice = "./etc/objeto_indice";
	String pathMapaJogoCategorias = "./etc/mapa_jogo_categorias";
	String pathListaCategorias = "./etc/lista_categorias";
	File arquivoIndice;
	File arquivoObjetoIndice;
	File arquivoMapaJogoCategorias;
	File arquivoListaCategorias;
	FileOutputStream saidaArquivoEscrita;
	ObjectOutputStream saidaObjetoEscrita;
	
	@Before
	public void setUp()
	{
		arquivoIndice = new File("./etc/indice.txt");
        arquivoIndice.delete();
        arquivoObjetoIndice = new File(pathObjetoIndice);
        arquivoObjetoIndice.delete();
        arquivoMapaJogoCategorias = new File(pathMapaJogoCategorias);
        arquivoMapaJogoCategorias.delete();
        arquivoListaCategorias = new File(pathListaCategorias);
        arquivoListaCategorias.delete();
        indiceTeste = new Indice();
		jogoTeste1 = new Jogo(0, "Jogo Legal", "01/09/94", "Klei");
		indiceTeste.novoJogoSendoAdicionado(0);
		jogoTeste2 = new Jogo(666, "Jogo Chato", "01/09/94", "Despacito");
		indiceTeste.novoJogoSendoAdicionado(666);
		jogoTeste3 = new Jogo(3, "Aladdin2", "16/08/94", "Despacito");
		indiceTeste.novoJogoSendoAdicionado(3);
		
	}

	@Test
	public void testAdicionaJogoIndiceVazio()
	{
		System.out.println(indiceTeste.getNumeroJogos());
		assertTrue(indiceTeste.getNumeroJogos() == 0);
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("jogo1 ja existe");
		}
		assertTrue(indiceTeste.getNumeroJogos() == 1);

	}

	@Test
	public void testAdicionaJogoQueJaEstaNoIndice()
	{
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("primeira adicao de jogo ja existe");
		}
		assertTrue(indiceTeste.getNumeroJogos() == 1);
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("segunda adicao de jogo ja existe");
		}
		assertTrue(indiceTeste.getNumeroJogos() == 1);
	}
	
	@Test
	public void testAdicionaJogoComUmJogo()
	{
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("jogo1 ja existe");
		}
		assertTrue(indiceTeste.getNumeroJogos() == 1);
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste2);
		}
		catch (Exception e)
		{
			System.out.println("jogo2 ja existe");
		}
		assertTrue(indiceTeste.getNumeroJogos() == 2);	
	}

	@Test
	public void testAdicionaCategoriaAoIndiceComNenhumaCategoria()
	{
		assertTrue(indiceTeste.getNumeroCategorias() == 0);
		assertTrue(indiceTeste.getMapaCategorias().size() == 3);
		assertTrue(indiceTeste.getMapaCategorias().get(jogoTeste1.getIdJogo()).length() == 1);
		indiceTeste.adicionaCategoriaAoIndice("Jogos do verao passado");		
		assertTrue(indiceTeste.getNumeroCategorias() == 1);
		assertTrue(indiceTeste.getMapaCategorias().size() == 3);
		assertTrue(indiceTeste.getMapaCategorias().get(jogoTeste1.getIdJogo()).length() == 1);
		indiceTeste.adicionaCategoriaAoIndice("Jogos do inverno passado");
		assertTrue(indiceTeste.getNumeroCategorias() == 2);
		assertTrue(indiceTeste.getMapaCategorias().size() == 3);
		assertTrue(indiceTeste.getMapaCategorias().get(jogoTeste1.getIdJogo()).length() == 2);
		indiceTeste.adicionaCategoriaAoIndice("Jogos do equinocio passado");
		assertTrue(indiceTeste.getNumeroCategorias() == 3);
		assertTrue(indiceTeste.getMapaCategorias().size() == 3);
		assertTrue(indiceTeste.getMapaCategorias().get(jogoTeste1.getIdJogo()).length() == 3);
//		for (Integer key : indiceTeste.getMapaCategorias().keySet())
//		{
//			System.out.println(indiceTeste.getCategorias(key));
//		}
	}
	
	@Test
	public void testAdicionaCategoriaAoJogo()
	{	
		indiceTeste.adicionaCategoriaAoIndice("Jogos do verao passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do inverno passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do equinocio passado");
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste1.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria1 nao existe");
		}
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do equinocio passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria2 nao existe");
		}
		assertTrue(indiceTeste.testaCategoria(jogoTeste1.getIdJogo(), "Jogos do verao passado") == true);
		assertTrue(indiceTeste.testaCategoria(jogoTeste2.getIdJogo(), "Jogos do equinocio passado") == true);
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{
		
			System.out.println("categoria2 nao existe");
		}
		assertTrue(indiceTeste.testaCategoria(jogoTeste2.getIdJogo(), "Jogos do verao passado") == true);
	}
	
	@Test
	public void testRemoveCategoriaDoJogo()
	{	
		indiceTeste.adicionaCategoriaAoIndice("Jogos do verao passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do inverno passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do equinocio passado");
		
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste1.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria1 nao existe");
		}
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do equinocio passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria2 nao existe");
		}
		
		indiceTeste.removeCategoriaDoJogo(jogoTeste1.getIdJogo(), "Jogos do verao passado");
		indiceTeste.removeCategoriaDoJogo(jogoTeste2.getIdJogo(), "Jogos do equinocio passado");
		
		assertTrue(indiceTeste.testaCategoria(jogoTeste1.getIdJogo(), "Jogos do verao passado") == false);
		assertTrue(indiceTeste.testaCategoria(jogoTeste2.getIdJogo(), "Jogos do equinocio passado") == false);
		
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{
		
			System.out.println("categoria2 nao existe");
		}
		
		indiceTeste.removeCategoriaDoJogo(jogoTeste2.getIdJogo(), "Jogos do verao passado");
		
		assertTrue(indiceTeste.testaCategoria(jogoTeste2.getIdJogo(), "Jogos do verao passado") == false);
	}
	
	@Test
	public void testmodificaJogoNoIndice()
	{
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("primeira adicao de jogo ja existe");
		}
		
		System.out.println(indiceTeste.getInformacoesJogoNoIndice(jogoTeste1.getIdJogo()));
		assertTrue(indiceTeste.getInformacoesJogoNoIndice(jogoTeste1.getIdJogo()).get(3) == "Klei");
		Jogo temp = new Jogo(0, "Jogo Legal", "01/09/94", "Cacatua");
		
		try
		{
			indiceTeste.modificaJogoNoIndice(temp);
		}
		catch (Exception e)
		{
			System.out.println("Jogo nao existe no indice");
		}
	
		assertTrue(indiceTeste.getInformacoesJogoNoIndice(jogoTeste1.getIdJogo()).get(3) == "Cacatua");
		System.out.println(indiceTeste.getInformacoesJogoNoIndice(temp.getIdJogo()));
		
	}
	
	@Test
	public void testGetIdPorNomeDeJogoNoIndiceVazio()
	{
		assertTrue(indiceTeste.getIdComNome(jogoTeste1.getNomeJogo()) == -1);
		assertTrue(indiceTeste.getIdComNome(jogoTeste2.getNomeJogo()) == -1);
		assertTrue(indiceTeste.getIdComNome(jogoTeste3.getNomeJogo()) == -1);
	}
	
	@Test
	public void testGetIdPorNomeDeJogoNoIndiceComJogos()
	{
		try {
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
			indiceTeste.adicionaJogoNoIndice(jogoTeste2);
			indiceTeste.adicionaJogoNoIndice(jogoTeste3);
		}
		catch (Exception e)
		{
			System.out.println("falha em adicionar jogo no indice");
		}
		assertTrue(indiceTeste.getIdComNome(jogoTeste1.getNomeJogo()) == jogoTeste1.getIdJogo());
		assertTrue(indiceTeste.getIdComNome(jogoTeste2.getNomeJogo()) == jogoTeste2.getIdJogo());
		assertTrue(indiceTeste.getIdComNome(jogoTeste3.getNomeJogo()) == jogoTeste3.getIdJogo());
	}
	
	@Test
	public void testFiltroPorAtributo()
	{
		try {
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
			indiceTeste.adicionaJogoNoIndice(jogoTeste2);
			indiceTeste.adicionaJogoNoIndice(jogoTeste3);
		}
		catch (Exception e)
		{
			System.out.println("falha em adicionar jogo no indice");
		}		
		//System.out.println("filtro por nome:\n");
		assertTrue(indiceTeste.filtroPorAtributos("Jogo Chato", 1).size() == 1);
		assertTrue(indiceTeste.filtroPorAtributos("Jogo Legal", 1).size() == 1);
		assertTrue(indiceTeste.filtroPorAtributos("Aladdin", 1).size() == 0);
		//System.out.println("filtro por lanc:\n");
		assertTrue(indiceTeste.filtroPorAtributos("Jogo Chato", 2).size() == 0);
		assertTrue(indiceTeste.filtroPorAtributos("16/08/94", 2).size() == 1);
		assertTrue(indiceTeste.filtroPorAtributos("01/09/94", 2).size() == 2);
		//System.out.println("filtro por dev:\n");
		assertTrue(indiceTeste.filtroPorAtributos("Jogo Chato", 3).size() == 0);
		assertTrue(indiceTeste.filtroPorAtributos("Klei", 3).size() == 1);
		assertTrue(indiceTeste.filtroPorAtributos("Despacito", 3).size() == 2);
	}
		
	@Test
	public void testGetJogosPorCategoriaQueNaoExiste()
	{
		try {
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
			indiceTeste.adicionaJogoNoIndice(jogoTeste2);
			indiceTeste.adicionaJogoNoIndice(jogoTeste3);
		}
		catch (Exception e)
		{
			System.out.println("erro ao adicionar jogo no indice");
		}
		indiceTeste.adicionaCategoriaAoIndice("Jogos do verao passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do inverno passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do equinocio passado");
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste1.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria1 nao existe");
		}
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do equinocio passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria2 nao existe");
		}
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{		
			System.out.println("categoria2 nao existe");
		}
		assertTrue(indiceTeste.filtroPorCategoria("Jogo Chato",indiceTeste.getIdsDoIndice()) == -1);
	}
	
	@Test
	public void testGetJogosPorCategoriaQueNaoTemJogos()
	{
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
			indiceTeste.adicionaJogoNoIndice(jogoTeste2);
			indiceTeste.adicionaJogoNoIndice(jogoTeste3);
		}
		catch (Exception e)
		{
			System.out.println("erro ao adicionar jogos no indice");
			
		}
		indiceTeste.adicionaCategoriaAoIndice("Jogos do verao passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do inverno passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do equinocio passado");
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste1.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria1 nao existe");
		}
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do equinocio passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria2 nao existe");
		}
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{		
			System.out.println("categoria2 nao existe");
		}
		assertTrue(indiceTeste.filtroPorCategoria("Jogos do inverno passado",indiceTeste.getIdsDoIndice()) == 0);
	}
	
	@Test
	public void testGetJogosPorCategoriaQueSoTemUmJogo()
	{
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
			indiceTeste.adicionaJogoNoIndice(jogoTeste2);
			indiceTeste.adicionaJogoNoIndice(jogoTeste3);
		}
		catch (Exception e)
		{
			System.out.println("erro ao adicionar jogos no indice");	
		}
		indiceTeste.adicionaCategoriaAoIndice("Jogos do verao passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do inverno passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do equinocio passado");
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste1.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria1 nao existe");
		}
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do equinocio passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria2 nao existe");
		}
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{		
			System.out.println("categoria2 nao existe");
		}
		assertTrue(indiceTeste.filtroPorCategoria("Jogos do equinocio passado",indiceTeste.getIdsDoIndice()) == 1);
	}
	
	@Test
	public void testGetJogosPorCategoriaComMaisDeUmJogo()
	{
		try {
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
			indiceTeste.adicionaJogoNoIndice(jogoTeste2);
			indiceTeste.adicionaJogoNoIndice(jogoTeste3);
		}
		catch (Exception e)
		{
			System.out.println("falha em adicionar jogo no indice");			
		}
		
		indiceTeste.adicionaCategoriaAoIndice("Jogos do verao passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do inverno passado");
		indiceTeste.adicionaCategoriaAoIndice("Jogos do equinocio passado");
		
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste1.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria1 nao existe");
		}
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do equinocio passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria2 nao existe");
		}
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste2.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{		
			System.out.println("categoria2 nao existe");
		}
		assertTrue(indiceTeste.filtroPorCategoria("Jogos do verao passado",indiceTeste.getIdsDoIndice()) == 2);
	}
	
	@Test
	public void testRemoveJogoQueNaoEstaNoIndice()
	{	
		assertTrue(indiceTeste.getNumeroJogos() == 0);
		try
		{
			indiceTeste.removeJogoDoIndice(jogoTeste1.getIdJogo());
		}
		catch (Exception e)
		{
			
			System.out.println("jogo nao existe no indice");
		}
		assertTrue(indiceTeste.getNumeroJogos() == 0);
	}
	
	@Test
	public void testRemoveJogoQueEstaNoIndice()
	{	
		assertTrue(indiceTeste.getNumeroJogos() == 0);
		
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("primeira adicao de jogo ja existe");
		}
		
		assertTrue(indiceTeste.getNumeroJogos() == 1);
		
		try
		{
			indiceTeste.removeJogoDoIndice(jogoTeste1.getIdJogo());
		}
		catch (Exception e)
		{
			
			System.out.println("jogo nao existe no indice");
		}
		
		assertTrue(indiceTeste.getNumeroJogos() == 0);
	}
	
	@Test
	public void testRemoveMultiplosJogosDoIndice()
	{	
		assertTrue(indiceTeste.getNumeroJogos() == 0);
		
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("primeira adicao de jogo ja existe");
		}
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste2);
		}
		catch (Exception e)
		{
			System.out.println("primeira adicao de jogo ja existe");
		}
		
		assertTrue(indiceTeste.getNumeroJogos() == 2);
		
		try
		{
			indiceTeste.removeJogoDoIndice(jogoTeste1.getIdJogo());
		}
		catch (Exception e)
		{
			
			System.out.println("jogo nao existe no indice");
		}
		try
		{
			indiceTeste.removeJogoDoIndice(jogoTeste2.getIdJogo());
		}
		catch (Exception e)
		{
			
			System.out.println("jogo nao existe no indice");
		}
		
		assertTrue(indiceTeste.getNumeroJogos() == 0);
	}
	
	@Test
	public void testSalvaObjetoIndiceEmArquivoQueNaoExiste()
	{
		arquivoObjetoIndice = new File(pathObjetoIndice);
		
		if (arquivoObjetoIndice.exists() == true)
		{
			arquivoObjetoIndice.delete();
		}
		assertFalse(arquivoObjetoIndice.exists());
		indiceTeste.salvaObjetoIndice();
		assertTrue(arquivoObjetoIndice .exists());
	}
	
	@Test
	public void testSalvaObjetoIndiceEmArquivoQueExisteVazio()
	{
		arquivoObjetoIndice = new File(pathObjetoIndice);
		
		if (arquivoObjetoIndice.exists() == true)
		{
			arquivoObjetoIndice.delete();
		}
		try
		{
			arquivoObjetoIndice.createNewFile();
		}
		catch (IOException e)
		{
			System.out.println("falha em criar arquivo indice vazio");
		}
		assertTrue(arquivoObjetoIndice.exists());
		indiceTeste.salvaObjetoIndice();
		assertTrue(arquivoObjetoIndice.exists());
	}
	
	@Test
	public void testSalvaObjetoIndiceEmArquivoQueExisteComConteudoAleatorio()
	{
		arquivoObjetoIndice = new File(pathObjetoIndice);
		
		if (arquivoObjetoIndice.exists() == false)
		{
			try
			{
				arquivoObjetoIndice.createNewFile();
				saidaArquivoEscrita = new FileOutputStream(pathObjetoIndice);
				saidaObjetoEscrita = new ObjectOutputStream(saidaArquivoEscrita);
				saidaObjetoEscrita.writeObject(jogoTeste1);
			}
			catch (IOException e)
			{
				System.out.println("falha em criar arquivo indice");
			}
		}
		
		assertTrue(arquivoObjetoIndice.exists());
		indiceTeste.salvaObjetoIndice();
		assertTrue(arquivoObjetoIndice.exists());
	}
	
	@Test
	public void testSalvaMapaJogoCategoriasEmArquivoQueNaoExiste()
	{
		arquivoMapaJogoCategorias = new File(pathMapaJogoCategorias);
		
		if (arquivoMapaJogoCategorias.exists() == true)
		{
			arquivoMapaJogoCategorias.delete();
		}
		assertFalse(arquivoMapaJogoCategorias.exists());
		indiceTeste.salvaMapaJogoCategorias();
		assertTrue(arquivoMapaJogoCategorias .exists());
	}
		
	@Test
	public void testSalvaMapaJogoCategoriasEmArquivoQueExisteVazio()
	{
		arquivoMapaJogoCategorias = new File(pathMapaJogoCategorias);
		
		if (arquivoMapaJogoCategorias.exists() == true)
		{
			arquivoMapaJogoCategorias.delete();
		}
		try
		{
			arquivoMapaJogoCategorias.createNewFile();
		}
		catch (IOException e)
		{
			System.out.println("falha em criar arquivo indice vazio");
		}
		assertTrue(arquivoMapaJogoCategorias.exists());
		indiceTeste.salvaMapaJogoCategorias();
		assertTrue(arquivoMapaJogoCategorias.exists());
	}
		
	@Test
	public void testSalvaMapaJogoCategoriasEmArquivoQueExisteComConteudoAleatorio()
	{
		arquivoMapaJogoCategorias = new File(pathMapaJogoCategorias);
		
		if (arquivoMapaJogoCategorias.exists() == false)
		{
			try
			{
				arquivoMapaJogoCategorias.createNewFile();
				saidaArquivoEscrita = new FileOutputStream(pathObjetoIndice);
				saidaObjetoEscrita = new ObjectOutputStream(saidaArquivoEscrita);
				saidaObjetoEscrita.writeObject(jogoTeste1);
			}
			catch (IOException e)
			{
				System.out.println("falha em criar arquivo indice");
			}
		}
		
		assertTrue(arquivoMapaJogoCategorias.exists());
		indiceTeste.salvaMapaJogoCategorias();
		assertTrue(arquivoMapaJogoCategorias.exists());
	}
	
	@Test
	public void testSalvaListaCategoriasEmArquivoQueNaoExiste()
	{
		arquivoListaCategorias = new File(pathListaCategorias);
		
		if (arquivoListaCategorias.exists() == true)
		{
			arquivoListaCategorias.delete();
		}
		assertFalse(arquivoListaCategorias.exists());
		indiceTeste.salvaListaCategorias();
		assertTrue(arquivoListaCategorias.exists());
	}
	
	@Test
	public void testSalvaListaCategoriasEmArquivoQueExisteVazio()
	{
		arquivoListaCategorias = new File(pathListaCategorias);
		
		if (arquivoListaCategorias.exists() == true)
		{
			arquivoListaCategorias.delete();
		}
		try
		{
			arquivoListaCategorias.createNewFile();
		}
		catch (IOException e)
		{
			System.out.println("falha em criar arquivo indice vazio");
		}
		assertTrue(arquivoListaCategorias.exists());
		indiceTeste.salvaListaCategorias();
		assertTrue(arquivoListaCategorias.exists());
	}
	
	@Test
	public void testSalvaListaCategoriasEmArquivoQueExisteComConteudoAleatorio()
	{
		arquivoListaCategorias = new File(pathListaCategorias);
		
		if (arquivoListaCategorias.exists() == false)
		{
			try
			{
				arquivoListaCategorias.createNewFile();
				saidaArquivoEscrita = new FileOutputStream(pathObjetoIndice);
				saidaObjetoEscrita = new ObjectOutputStream(saidaArquivoEscrita);
				saidaObjetoEscrita.writeObject(jogoTeste1);
			}
			catch (IOException e)
			{
				System.out.println("falha em criar arquivo indice");
			}
		}
		
		assertTrue(arquivoListaCategorias.exists());
		indiceTeste.salvaListaCategorias();
		assertTrue(arquivoListaCategorias.exists());
	}
	
	
	
	
	@Test
	public void testRestauraObjetoIndiceDoArquivoQueNaoExiste()
	{
		indiceTemp = new Indice();

		arquivoObjetoIndice = new File(pathObjetoIndice);
		
		if (arquivoObjetoIndice.exists() == true)
		{
			arquivoObjetoIndice.delete();
		}
		
		indiceTemp.restauraObjetoIndice();
		
		assertFalse(indiceTeste.getIndiceLocal() == indiceTemp.getIndiceLocal());
	}
	
	@Test
	public void testRestauraObjetoIndiceDoArquivoQueExiste()
	{
		indiceTemp = new Indice();
		
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("jogo1 ja existe");
		}
		
		indiceTeste.salvaObjetoIndice();
		
		assertFalse(indiceTemp.getNumeroJogos() == indiceTeste.getNumeroJogos());
		
		indiceTemp.restauraObjetoIndice();
		
		assertTrue(indiceTeste.getNumeroJogos() == indiceTemp.getNumeroJogos());
	}
	
	@Test
	public void testRestauraMapaJogoCategoriasDoArquivoQueNaoExiste()
	{
		indiceTemp = new Indice();

		arquivoMapaJogoCategorias = new File(pathMapaJogoCategorias);
		
		if (arquivoMapaJogoCategorias.exists() == true)
		{
			arquivoMapaJogoCategorias.delete();
		}
		
		indiceTemp.restauraMapaJogoCategorias();
		
		assertFalse(indiceTeste.getIndiceLocal() == indiceTemp.getIndiceLocal());
	}
	
	@Test
	public void testRestauraMapaJogoCategoriasDoArquivoQueExiste()
	{
		indiceTemp = new Indice();
		
		try
		{
			indiceTeste.adicionaJogoNoIndice(jogoTeste1);
			indiceTemp.adicionaJogoNoIndice(jogoTeste1);
		}
		catch (Exception e)
		{
			System.out.println("jogo1 ja existe");
		}
		
		indiceTeste.adicionaCategoriaAoIndice("Jogos do verao passado");
		indiceTemp.adicionaCategoriaAoIndice("Jogos do verao passado");
		
		try
		{
			indiceTeste.adicionaCategoriaAoJogo(jogoTeste1.getIdJogo(), "Jogos do verao passado");
		}
		catch (Exception e)
		{
			System.out.println("categoria1 nao existe");
		}
		
		indiceTeste.salvaMapaJogoCategorias();
		
		assertFalse(indiceTemp.testaCategoria(jogoTeste1.getIdJogo(), "Jogos do verao passado"));
		assertTrue(indiceTeste.testaCategoria(jogoTeste1.getIdJogo(), "Jogos do verao passado"));
		
		indiceTemp.restauraMapaJogoCategorias();
		
		assertTrue(indiceTemp.testaCategoria(jogoTeste1.getIdJogo(), "Jogos do verao passado"));
	}
	
	@Test
	public void testRestauraListaCategoriasDoArquivoQueNaoExiste()
	{
		indiceTemp = new Indice();
		
		arquivoListaCategorias = new File(pathListaCategorias);
				
		indiceTeste.adicionaCategoriaAoIndice("Jogos do verao passado");
		
		if (arquivoListaCategorias.exists() == true)
		{
			arquivoListaCategorias.delete();
		}
		
		indiceTemp.restauraListaCategorias();
		
		System.out.println(indiceTemp.getNumeroCategorias());
		
		assertFalse(indiceTeste.getNumeroCategorias() == indiceTemp.getNumeroCategorias());
	}
	
	@Test
	public void testRestauraListaCategoriasDoArquivoQueExiste()
	{
		indiceTemp = new Indice();

		indiceTeste.adicionaCategoriaAoIndice("Jogos do verao passado");
		indiceTeste.salvaListaCategorias();
		
		assertFalse(indiceTemp.getNumeroCategorias() == indiceTeste.getNumeroCategorias());
		
		indiceTemp.restauraListaCategorias();
	
		assertTrue(indiceTemp.getNumeroCategorias() == indiceTeste.getNumeroCategorias());
	}
	
}
