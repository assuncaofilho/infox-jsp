package servlets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


import dao.DaoFactory;
import dao.UsuarioDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

@WebServlet(urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	private UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

    public ServletUsuarioController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {	
		
		 String acao  = request.getParameter("acao");
		 
		 if (acao != null && !acao.isEmpty() && acao.equals("deletar")) {
			 
			 String idUser = request.getParameter("id");
			 
			 usuarioDao.deletarUser(idUser);
			 
			 request.setAttribute("msg", "Excluido com sucesso!");
			 request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		 }
		 else if (acao != null && !acao.isEmpty() && acao.equals("deletarajax")) {
				 
				 String idUser = request.getParameter("id");
				 
				 usuarioDao.deletarUser(idUser);
				 
				 response.getWriter().write("Excluido com sucesso!");
				 
		 }
		 
		 else if (acao != null && !acao.isEmpty() && acao.equals("buscarajax")) {
			 
			 String nomeBusca = request.getParameter("nomeBusca");
			 
			 List<Usuario> dadosJsonUser =  usuarioDao.consultaUsuarioList(nomeBusca);
			 
			 ObjectMapper mapper = new ObjectMapper();
			 
			 String json = mapper.writeValueAsString(dadosJsonUser);
			 
			 response.getWriter().write(json);
			 
		 }
		 
		 else if (acao != null && !acao.isEmpty() && acao.equals("buscareditar")) {
			
			    String id = request.getParameter("id");
			 
			     Usuario usuario = usuarioDao.consultaUsuarioID(id);
			 
			 
			    request.setAttribute("msg", "Usuário em edição");
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		 }
		 
		 else {
			 request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		 }
		 
		
		 
		 
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String perfil = request.getParameter("perfil");
	
		
		Usuario usuario = new Usuario();
		
		
		usuario.setId(id != null && !id.isEmpty() ? Integer.parseInt(id) : null); // condicao ternária
		usuario.setNome(nome);
		usuario.setFone(telefone);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setPerfil(perfil);
		
		
		if (!usuarioDao.loginExiste(usuario.getLogin()) && usuario.getId() == null) { // só grava novos usuarios;
		//insert
		usuario = usuarioDao.gravarUsuario(usuario); // grava e retorna o mesmo objeto vindo de uma consulta;
		
		request.setAttribute("msg", "Usuário cadastrado com sucesso!");
		
		
		}else {
			
			if(usuarioDao.loginExiste(usuario.getLogin()) && usuario.getId() != null ) {
			//update
			usuario = usuarioDao.gravarUsuario(usuario);
			request.setAttribute("msg", "Usuário atualizado com sucesso!");
	
				
			}else {
				if(usuarioDao.loginExiste(login)) {
			
			request.setAttribute("msg", "O login '" + usuario.getLogin() +"' já está em uso! Por favor tente novamente!");
				}
			
			}
		}
		
		request.setAttribute("usuario", usuario);
		RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
		redirecionar.forward(request, response);
		
			}catch(Exception e) {
				
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

		}
	}


}