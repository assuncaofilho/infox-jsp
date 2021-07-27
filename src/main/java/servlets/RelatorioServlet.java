package servlets;

import java.io.IOException;
import java.util.HashMap;

import connection.ConexaoUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class RelatorioServlet
 */
@WebServlet("/RelatorioServlet")
public class RelatorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RelatorioServlet() {
        super();
   
    }


	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		
		geraRelatorio(request, response);
		
	
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			
		geraRelatorio(request, response);
		
	}
	
	protected void geraRelatorio(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			String acao = request.getParameter("acao");
		
			String erro = "";
			/*Caso o report necessite de parâmetros*/
			//String empresa = request.getParameter("empresa"); // vêm do name (post) ou da URL (get);
			
			/*Caminho relativo do arquivo jasper*/
			String caminhoJasper = "WEB-INF/report/"+acao+".jasper";
			 
			//HashMap<String, Object> param = new HashMap<String, Object>();
			//param.put("EMPRESA", empresa);
			
			byte[] bytes = null;
			
			ServletContext context = getServletContext();
			
			try {
				JasperReport relatorio = (JasperReport) JRLoader.loadObjectFromFile(
						context.getRealPath(caminhoJasper));
				
		
				
				bytes = JasperRunManager.runReportToPdf(relatorio, null, ConexaoUtil.getConnection());
				
				
				
				
			} catch (JRException e) {
				erro = e.getMessage();
			} finally {
				
				if(bytes != null) {
					response.setContentType("application/pdf");
					response.setContentLength(bytes.length);
					
					ServletOutputStream sos = response.getOutputStream();
					sos.write(bytes);
					sos.flush();
					sos.close();
				} else {
					RequestDispatcher redireciona = request.getRequestDispatcher("principal/relatorios.jsp");
					request.setAttribute("msg", erro);
					redireciona.forward(request, response);
				}
			}
			
		
		
	}
			

}
