package banco.ui;

import java.util.List;

import banco.dao.AutorDao;
import banco.dao.LivroDao;
import banco.modelo.Autor;
import banco.modelo.Livro;

public class InterfaceLivroTexto extends InterfaceModeloTexto {

	private LivroDao dao;
	private AutorDao autorDao;
	
	public InterfaceLivroTexto() {
		super();
		dao = new LivroDao();
		autorDao = new AutorDao();
	}
	
	@Override
	public void adicionar() {
		System.out.println("Adicionar livro");
		System.out.println();
		
		Livro novoLivro = obtemDadosLivro(null);	
		dao.insert(novoLivro);
	}

	private Livro obtemDadosLivro(Livro livro) {
		System.out.print("Insira o titulo do livro: ");
		String titulo = entrada.nextLine();
		
		System.out.print("Insira a editora do livro: ");
		String editora = entrada.nextLine();
		
		System.out.print("Insira o ano de publica��o do livro (apenas numero): ");
		int anoPublicacao = entrada.nextInt();
		
		System.out.print("Insira o ID do autor: ");
		int idAutor = entrada.nextInt();
		
		Autor autor = autorDao.getByKey(idAutor);
		
		return new Livro(0, titulo, anoPublicacao, editora, autor);
	}

	@Override
	public void listarTodos() {
		List<Livro> livros = dao.getAll();
		
		System.out.println("Lista de livros");
		System.out.println();
		
		System.out.println("id\tAgência\tNúmero\tSaldo\tID do Autor\tNome do Autor");
		
		for (Livro livro : livros) {
			imprimeItem(livro);
		}
	}

	@Override
	public void editar() {
		listarTodos();
		
		System.out.println("Editar livro");
		System.out.println();
		
		System.out.print("Entre o id do livro: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		Livro livroAModificar = dao.getByKey(id);
		
		Livro novoLivro = obtemDadosLivro(livroAModificar);
		
		novoLivro.setId(id);
		
		dao.update(novoLivro);
	}

	@Override
	public void excluir() {
		listarTodos();
		
		System.out.println("Excluir livro");
		System.out.println();
		
		System.out.print("Entre o id da livro: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		dao.delete(id);
	}

}
