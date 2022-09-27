package controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.javaBeans;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO();
	javaBeans contato = new javaBeans();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// dao.testeConexao(); // teste de conexao
		String action = request.getServletPath();
		System.out.println(action); // teste de chamada da açãp

		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);

		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else {

			response.sendRedirect("index.html");
		}

	}

	// listar contatos

	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp");
		// criando um objeto que ira receber os dados do javaBeans para passar como
		// retorno
		ArrayList<javaBeans> lista = dao.listarContatos();
		// Encaminha a lista para o documento agenda

		request.setAttribute("contato", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response); // configure o documento jsp

	}

	// Novo contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		//teste de recebimento de dados
		  
		  System.out.println(request.getParameter("nome"));
		  System.out.println(request.getParameter("fone"));
		  System.out.println(request.getParameter("email"));
		 

		// setar os dados para camada beans

		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		// invocar o metodo inserirContato da classe DAO passando o objeto contato
		dao.inserirContato(contato);
		// redirecionar para o documento agenda
		response.sendRedirect("main");

	}

	// ListarContato
		protected void listarContato(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			//System.out.println(idcon); verificando se o sistema esta buscando o id que vai ser editado
			
			
			//Recebimento do id do contato que será editado
			String idcon = request.getParameter("idcon");
			
			//Setando a variavel javabeans
			 contato.setIdcon(idcon);
			//Execultar o metodo selecionarcontato (DAO
			 
			 dao.selecionarContato(contato);
			 //setar os atributos do formulrio com o conteudo javabeans
			 request.setAttribute("idcon", contato.getIdcon());
			 request.setAttribute("nome", contato.getNome());
			 request.setAttribute("fone", contato.getFone());
			 request.setAttribute("email", contato.getEmail());
			 //encaminhar para o documento editar.jsp
			 RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
			 rd.forward(request, response);
			
		

		}
       
}
