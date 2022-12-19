package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Cliente;
//import dominio.Endereco;

public class ClienteDAO {

	private Conexao con;
	private final String CADASTRAR = "insert into public.cliente (cpf, nome) values (?, ?)";
	private final String BUSCAR = "select * from public.cliente where (cpf=?)";
	private final String ALTERAR = "update public.cliente set cpf =?, nome=? where cpf=?";
	private final String EXCLUIR = "delete from Cliente where cpf=?";
	private final String RELATORIO = "select * from Cliente";
	private EnderecoDAO dao;

	public ClienteDAO() {
		con = new Conexao("jdbc:postgresql://localhost:5434/poo22", "postgres", "postgres");
		 dao = new EnderecoDAO(this);	
	}

	// relação 1 para n entre cliente e pedido, um cliente pode fazer varios pedidos
	// relação 1 para 1 entre endereço e cliente
	// só pode excluir um cliente depois que excluir todos os pedidos vinculados a ele
	// se excluir a tatuagem tem que excluir o pedido antes
	
	public void cadastrar(Cliente c) {
		try {// executar esse bloco de codigo
			con.conectar();
			PreparedStatement instrucao = con.getConexao().prepareStatement(CADASTRAR); // prepara a query CADASTRAR para ser executada
			instrucao.setString(1, c.getCpf()); // guarda o cpf no parametro 1
			instrucao.setString(2, c.getNome()); // guarda o cpf no parametro 1
			instrucao.execute(); // realiza uma ação dentro do banco
			con.desconectar();
		} catch (Exception e) { // se houver um sql exception, vai printar o erro // se tiver outra linha ele vai executa
			System.out.println("Erro no cadastro: " + e.getMessage());
		}
	}

	public Cliente buscar(String cpf) {
		Cliente c = null;
		try { // executar esse bloco de codigo
			con.conectar();
			PreparedStatement instrucao = con.getConexao().prepareStatement(BUSCAR); // preparedStatement: para instruções SQL que podem ser finalizadas após terem sido criadas.
			instrucao.setString(1, cpf);		// resultSet: contém o conjunto de dado retornado por uma consulta SQL
			ResultSet rs = instrucao.executeQuery(); // executequery:  retorna um resultSet que contem todos os dados da seleção
			if (rs.next()) { // se tiver outra linha ele vai executar 
				c = new Cliente(rs.getString("cpf"), rs.getString("nome"));
			}
			con.desconectar();
		} catch (SQLException e) { // se houver um sql exception, vai printar o erro
			System.out.println("Erro na busca: " + e.getMessage());
		}
		return c;
	}

	public void alterar(Cliente c, String cpfAntigo) {
		try {
			con.conectar();
			PreparedStatement instrucao = con.getConexao().prepareStatement(ALTERAR);
			instrucao.setString(1, c.getCpf());
			instrucao.setString(2, c.getNome());
			instrucao.setString(3, cpfAntigo);
			instrucao.execute();
			con.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na alteração: " + e.getMessage());
		}
	}

	public void exclusao(String cpf) {
		try {
			dao.exclusao(cpf);
			con.conectar();
			PreparedStatement instrucao = con.getConexao().prepareStatement(EXCLUIR);
			
			instrucao.setString(1, cpf);
			instrucao.execute();
			con.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na exclusão: " + e.getMessage());
		}
	}

	public ArrayList<Cliente> relatorio() { //
		ArrayList<Cliente> lista = new ArrayList<>(); // instancia um arraylist de clientes
		try {
			con.conectar();
			Statement instrucao = con.getConexao().createStatement(); // statement para instruções sql finalizadas
			ResultSet rs = instrucao.executeQuery(RELATORIO); // retornar um resultset todos os dados da seleção
			while (rs.next()) { //  usa while pq retorna todos os clientees e não apenas uma linha
				Cliente c = new Cliente(rs.getString("cpf"), rs.getString("nome"));
				lista.add(c);
			}
			con.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro no relatório: " + e.getMessage());
		}
		return lista;
	}


}
