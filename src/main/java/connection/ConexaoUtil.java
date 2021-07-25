package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConexaoUtil {  //n�o permite instancia��o da classe
	
	private static Connection conn;
	
	
	
	/*Os m�todos static ou m�todos da classe s�o fun��es que n�o dependem de nenhuma vari�vel de inst�ncia, 
	 * quando invocados executam uma fun��o sem a depend�ncia do conte�do de um objeto ou a execu��o da inst�ncia de uma classe*/
	public synchronized static Connection getConnection() { 
		
		if(conn == null) {
			
			
			String url = "jdbc:mysql://localhost:3306/dbinfox";
			String driver = "com.mysql.cj.jdbc.Driver";
			String user = "root";
			String senha = "root";
			
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, senha);
				conn.setAutoCommit(false);
				System.out.println("a conex�o �:" + conn);
				
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		
		return conn;
	}
	
	
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
