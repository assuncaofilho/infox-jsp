package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConexaoUtil;
import model.Os;

public class OsDaoJdbc implements OsDao {

	private Connection connection;
	
	public OsDaoJdbc() {
		connection = ConexaoUtil.getConnection();
	}
	
	public Os cadastrar(Os o) throws Exception{
		
		Os retorno = null;
		
		if (o.isNovo()) {/*Grava um novo*/
			
			String cadastrar = "insert into tbos (os, data_os, tipo, situacao, equipamento, defeito, servico, tecnico, valor, idcli) "
	                + "values (default, default, ?, ?, ?, ?, ?, ?, ?, ?)";

	        
	               PreparedStatement pst = connection.prepareStatement(cadastrar);

	                pst.setString(1, o.getTipo());
	                pst.setString(2, o.getSituacao());
	                pst.setString(3, o.getEquipamento());
	                pst.setString(4, o.getDefeito());
	                pst.setString(5, o.getServico());
	                pst.setString(6, o.getTecnico());
	                pst.setString(7, Double.toString(o.getValor()));
	                pst.setString(8, Integer.toString(o.getIdcli()));

	                ResultSet rs = pst.executeQuery();
	                rs.next();
	                retorno = this.pesquisar(rs.getString("os"));
	                
	            
			connection.commit();
			
			
			}else {
				
				String editar = "update tbos set tipo=?, situacao=?, equipamento=?, defeito=?, servico=?, tecnico=?, valor=? where os=?";
		     
						PreparedStatement pst;
		                pst = connection.prepareStatement(editar);
		                pst.setString(1, o.getTipo());
		                pst.setString(2, o.getSituacao());
		                pst.setString(3, o.getEquipamento());
		                pst.setString(4, o.getDefeito());
		                pst.setString(5, o.getServico());
		                pst.setString(6, o.getTecnico());
		                pst.setString(7, Double.toString(o.getValor()));
		                pst.setString(8, Integer.toString(o.getId()));
		                
		                ResultSet rs = pst.executeQuery();
		                rs.next();
		                retorno = this.pesquisar(rs.getString("os"));
				
				connection.commit();
				
			
			}
			
			
			return retorno;

		
	}

	
	public Os pesquisar(String id) throws Exception {
	
        
        String pesquisar = "select * from tbos where os=?";
        
            PreparedStatement pst = connection.prepareStatement(pesquisar);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            
            Os encontrada = new Os();
            
                if(rs.next()){
                	
                  
                  encontrada.setId(rs.getInt("os")); 
                  encontrada.setData(rs.getString("data_os"));
                  encontrada.setTipo(rs.getString("tipo"));
                  encontrada.setSituacao(rs.getString("situacao")); 
                  encontrada.setEquipamento(rs.getString("equipamento")); 
                  encontrada.setDefeito(rs.getString("defeito"));
                  encontrada.setServico(rs.getString("servico")); 
                  encontrada.setTecnico(rs.getString("tecnico")); 
                  encontrada.setValor(rs.getDouble("valor"));
                  encontrada.setIdcli(rs.getInt("idcli"));
                    
                 }
            return encontrada;
	}

	
	public void excluir(String id) throws Exception {
		
		String sql = "DELETE FROM tbos WHERE os = ?;";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		
		prepareSql.setInt(1, Integer.parseInt(id));
		
		prepareSql.executeUpdate();
		
		connection.commit();
		
		
	}

}
