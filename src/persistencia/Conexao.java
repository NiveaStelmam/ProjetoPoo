package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

	public class Conexao {
		private String usuario;
		private String senha;
		private String caminho;
		private Connection con;
	
	
	
	public Conexao(String caminho, String usuario, String senha) {
		this.caminho = caminho;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public void conectar() {
		try {
			Class.forName("org.postgresql.Driver"); //carrega o driver do postgres
			con = DriverManager.getConnection(caminho, usuario, senha); //abrir a conex�o
		}catch(Exception e) {
			System.out.println("Erro na conex�o");
		}
	}
	
	public void desconectar() {
		try {
			con.close();
		}catch(Exception e) {
			System.out.println("Erro na desconex�o...");
		}
	}

	public Connection getConexao() {
		return con;
	}
}


