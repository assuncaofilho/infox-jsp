package servlets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ClienteDao;
import dao.DaoFactory;
import dao.OsDao;
import dao.UsuarioDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Os;
import model.Usuario;


@WebServlet(urlPatterns = {"/ServletOsController"})
public class ServletOsController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	private OsDao osDao = DaoFactory.createOsDao();
	private ClienteDao clienteDao = DaoFactory.createClienteDao();
	private UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

    public ServletOsController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {	
		
		 String acao  = request.getParameter("acao");
		 
		 if (acao != null && !acao.isEmpty() && acao.equals("deletar")) {
			 
			 String id = request.getParameter("id");
			 
			 osDao.excluir(id);
			 
			 request.setAttribute("msg", "OS excluída com sucesso!");
			 request.getRequestDispatcher("principal/os.jsp").forward(request, response);
		 }
		 else if (acao != null && !acao.isEmpty() && acao.equals("deletarajax")) {
				 
				 String id = request.getParameter("id");
				 
				 osDao.excluir(id);
				 
				 response.getWriter().write(" Os excluída com sucesso!");
				 
		 }
		 
		 else if (acao != null && !acao.isEmpty() && acao.equals("buscarajax")) {
			 
			 String id = request.getParameter("idBuscaOS");
			 
			 Os jsonOs =  osDao.pesquisar(id);
			 
			 ObjectMapper mapper = new ObjectMapper();
			 
			 String json = mapper.writeValueAsString(jsonOs);
			 
			 response.getWriter().write(json);
			 
		 }
		 
		 else if (acao != null && !acao.isEmpty() && acao.equals("buscareditar")) {
			
			    String id = request.getParameter("id");
			 
			     Os os = osDao.pesquisar(id);
			     
			     Cliente cliente = clienteDao.consultaID(os.getIdcli().toString());
			 
			 
			    request.setAttribute("msg", "OS em edição");
				request.setAttribute("os", os);
				request.setAttribute("cboTipo", os.getTipo());
				request.setAttribute("cboSituacao", os.getSituacao());
				request.setAttribute("nomeCli", cliente.getNome());
				request.setAttribute("idCli", cliente.getId());
				request.getRequestDispatcher("principal/os.jsp").forward(request, response);
		 }
		 else if (acao != null && !acao.isEmpty() && acao.equals("buscareditarclios")) {
				
			    String id = request.getParameter("id");
			  
			    Cliente cliente = clienteDao.consultaID(id);
			    
				ObjectMapper mapper = new ObjectMapper();
				 
				String json = mapper.writeValueAsString(cliente);
				 
				response.getWriter().write(json);
			 
			 
			  /* request.setAttribute("msg", "Cadastrando nova OS");
				request.setAttribute("nomeCli", cliente.getNome());
				request.setAttribute("idCli", cliente.getId());
				request.getRequestDispatcher("principal/os.jsp").forward(request, response);*/
		 }
		 
		 else if (acao != null && !acao.isEmpty() && acao.equals("buscareditartecos")) {
				
			    String id = request.getParameter("id");
			  
			    Usuario tecnico = usuarioDao.consultaUsuarioID(id);
			 
				request.setAttribute("nomeTec", tecnico.getNome());
				request.getRequestDispatcher("principal/os.jsp").forward(request, response);
		 }
		 
		 else if (acao != null && !acao.isEmpty() && acao.equals("buscartecnicoajax")) {
				
				 String nomeTec = request.getParameter("nomeTec");
				 
				 List<Usuario> listaUsuario =  usuarioDao.consultaUsuarioList(nomeTec);
				 
				 ObjectMapper mapper = new ObjectMapper();
				 
				 String json = mapper.writeValueAsString(listaUsuario);
				 
				 response.getWriter().write(json);
		 }
		 
		 
		 
		 else if (acao != null && !acao.isEmpty() && acao.equals("buscarclienteajax")) {
				
			    String nomeCli = request.getParameter("nomeCli");
			     
			     List<Cliente> clienteJSon = clienteDao.consultaListar(nomeCli);
				 
				 ObjectMapper mapper = new ObjectMapper();
				 
				 String json = mapper.writeValueAsString(clienteJSon);
				 
				 response.getWriter().write(json);
		 }
		 
		 else {
			 request.getRequestDispatcher("principal/os.jsp").forward(request, response);
		 }
		 
		
		 
		 
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String id = request.getParameter("id");
		String data = request.getParameter("data");
		String tipo = request.getParameter("tipo");
		String situacao = request.getParameter("situacao");
		String equipamento = request.getParameter("equipamento");
		String defeito = request.getParameter("defeito");
		String servico = request.getParameter("servico");
		String tecnico = request.getParameter("tecnico");
		String valor = request.getParameter("valor");
		String idcli = request.getParameter("idcli");
		
	
		
		Os os = new Os();
		
		try {
		
		os.setId(id != null && !id.isEmpty() ? Integer.parseInt(id) : null); // condicao ternária
		os.setData(data != null && !data.isEmpty() ? data : null);
		os.setTipo(tipo);
		os.setSituacao(situacao);
		os.setEquipamento(equipamento);
		os.setDefeito(defeito);
		os.setServico(servico);
		os.setTecnico(tecnico);
		os.setValor(Double.parseDouble(valor));
		os.setIdcli(Integer.parseInt(idcli));
		
		
		if (os.getId() == null && os.getIdcli() != null) { // nova os e cliente carregado na tela
		//insert
		os = osDao.cadastrar(os); // grava e retorna o mesmo objeto vindo de uma consulta;
		
		request.setAttribute("msg", "OS cadastrada com sucesso!");
		
		
		}else {
			
			if(os.getId() != null && os.getIdcli() != null) {
			//update
			os = osDao.cadastrar(os);
			request.setAttribute("msg", "OS atualizada com sucesso!");
	
				
			}else {
				if(os.getIdcli() == null) {
			
			request.setAttribute("msg", "Vincule um cliente para cadastrar a OS!");
			
				} 
			
			}
		}
		
		request.setAttribute("os", os);
		RequestDispatcher redirecionar = request.getRequestDispatcher("principal/os.jsp");
		redirecionar.forward(request, response);
		
				
			}
			catch(Exception e) {
				
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/os.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

		}
	}

}