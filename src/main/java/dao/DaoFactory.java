package dao;

public abstract class DaoFactory { // classe modelo; n�o pode ser instanciada diretamente;
	
	public static UsuarioDao createUsuarioDao() { // n�o depende de vari�vel de inst�ncia;
		return new UsuarioDaoJdbc();
	}

}
