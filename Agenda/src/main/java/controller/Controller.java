package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Dao;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	Dao dao = new Dao();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			Listarcontatos(request, response);
		} else if (action.equals("/insert")) {
			Novocontato(request, response);
		} else if (action.equals("/select")) {
			Selecionarcontato(request, response);
		} else if (action.equals("/update")) {
			Editarcontato(request, response);
		} else if (action.equals("/delete")) {
			Removercontato(request, response);
		} else if (action.equals("/report")) {
			Relatoriocontato(request, response);

		} else {
			response.sendRedirect("index.html");
		}

		// Teste de conexao
		// dao.testeConexao();
	}

	/**
	 * Listarcontatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar contatos
	protected void Listarcontatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> listaContatos = dao.listarContato();
		// encaminhar a lista ao documento "agenda.jsp"
		request.setAttribute("contatos", listaContatos);
		RequestDispatcher rd = request.getRequestDispatcher("Agenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * Novocontato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo contato
	protected void Novocontato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste de recebimento dos dados do formulário
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("fone"));
		// System.out.println(request.getParameter("email"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		dao.inserirContato(contato);
		response.sendRedirect("main");
	}

	/**
	 * Selecionarcontato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Selecionar contato
	protected void Selecionarcontato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setar a variável javabeans
		contato.setIdcon(request.getParameter("idcon"));
		// executar o método para selecionar o contato
		dao.selecionarContato(contato);
		// teste de recebimento System.out.println(contato.getNome());
		// Setar os campos do formulario com o conteudo da variavel javabeans contato
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		// encaminhar ao documento Editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("Editar.jsp");
		rd.forward(request, response);

	}

	/**
	 * Editarcontato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Editar contato
	protected void Editarcontato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste de recebimento dos dados do formulário
		/*
		 * System.out.println(request.getParameter("nome"));
		 * System.out.println(request.getParameter("fone"));
		 * System.out.println(request.getParameter("email"));
		 * System.out.println(request.getParameter("idcon"));
		 */
		// setar os valores do Javabeans contato
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		// alterar o contato no banco
		dao.alterarContato(contato);
		// voltar para a página agenda.jsp
		response.sendRedirect("main");
	}

	/**
	 * Removercontato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Remover contato
	protected void Removercontato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contato.setIdcon(request.getParameter("idcon"));
		// remover o contato no banco
		dao.removerContato(contato);
		// voltar para a página agenda.jsp
		response.sendRedirect("main");
	}

	/**
	 * Relatoriocontato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Relatorio de contatos
	protected void Relatoriocontato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste de recebimento dos dados do formulário
		Document documento = new Document();
		try {
			response.setContentType("aplication/pdf"); // tipo do documento
			response.addHeader("Content-Disposition", "inline;filename=" + "contatos.pdf"); // nome do documento
			PdfWriter.getInstance(documento, response.getOutputStream()); // cria o documento
			documento.open(); // abre o documento
			documento.add(new Paragraph("Lista de Contatos"));
			documento.add(new Paragraph(" "));
			PdfPTable tabela = new PdfPTable(3); // cria uma tabela com 3 colunas
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			// Popular a tabela
			ArrayList<JavaBeans> listaContatos = dao.listarContato();
			for (int i = 0; i < listaContatos.size(); i++) {
				tabela.addCell(listaContatos.get(i).getNome());
				tabela.addCell(listaContatos.get(i).getFone());
				tabela.addCell(listaContatos.get(i).getEmail());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
