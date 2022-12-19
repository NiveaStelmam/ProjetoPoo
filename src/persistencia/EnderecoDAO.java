package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Cliente;
import dominio.Endereco;

public class EnderecoDAO {

	private Conexao con;
	private final String CADASTRAR = "insert into public.endereco (rua,bairro,numero,idendereco,cpfcliente) values (?, ?, ?, ?,?)";
	private final String BUSCAR = "select * from public.endereco where (idendereco=?)";
	private final String BUSCAR_CPF = "select * from public.endereco where (cpfcliente=?)";
	private final String ALTERAR = "update public.endereco set idendereco =?, rua =?, bairro = ?, numero=?,cpfcliente=? where idendereco=?";
	private final String EXCLUIR = "delete from public.endereco where idendereco=?";
	private final String EXCLUIR_CPF = "delete from public.endereco where cpfcliente=?";
	private final String RELATORIO = "select * from public.endereco";
	private ClienteDAO clienteDao;
	
	public EnderecoDAO(ClienteDAO clienteDAO) {
		con = new Conexao("jdbc:postgresql://localhost:5434/poo22", "postgres", "postgres");
		this.clienteDao = clienteDAO;
	}

	public void cadastrar(Endereco e) {
		try {
			con.conectar();
			PreparedStatement instrucao = con.getConexao().prepareStatement(CADASTRAR);
			instrucao.setString(1, e.getRua());
			instrucao.setString(2, e.getBairro());
			instrucao.setInt(3, e.getNumero());
			instrucao.setInt(4, e.getIdEndereco());
			instrucao.setString(5, e.getCpfCliente().getCpf());
			instrucao.execute();
			con.desconectar();
		} catch (Exception ex) {
			System.out.println("Erro no cadastro: " + ex.getMessage());
		}
	}

	public Endereco buscar(int idEndereco) {
		Endereco e = null;
		try {
			con.conectar();
			PreparedStatement instrucao = con.getConexao().prepareStatement(BUSCAR);
			instrucao.setInt(1, idEndereco);
			ResultSet rs = instrucao.executeQuery();
			if (rs.next()) {
				Cliente c = clienteDao.buscar(rs.getString("cpfCliente"));
				e = new Endereco(c, rs.getString("rua"),rs.getString("bairro"),rs.getInt("idendereco"), rs.getInt("numero"));
			}
			con.desconectar();
		} catch (SQLException ex) {
			System.out.println("Erro na busca: " + ex.getMessage());
		}
		return e;
	}

	public void alterar(Endereco e, int idAntigo) {
		try {
			con.conectar();
			PreparedStatement instrucao = con.getConexao().prepareStatement(ALTERAR);
			instrucao.setInt(1, e.getIdEndereco());
			instrucao.setString(2, e.getRua());
			instrucao.setString(3, e.getBairro());
			instrucao.setInt(4, e.getNumero());
			instrucao.setString(5, e.getCpfCliente().getCpf());
			instrucao.setInt(6, idAntigo);
			instrucao.execute();
			con.desconectar();
		} catch (Exception ex) {
			System.out.println("Erro na alteração: " + ex.getMessage());
		}
	}

	public void exclusao(int idEndereco) {
		try {
			con.conectar();
			PreparedStatement instrucao = con.getConexao().prepareStatement(EXCLUIR);
			instrucao.setInt(1, idEndereco);
			instrucao.execute();
			con.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na exclusão: " + e.getMessage());
		}
	}

	public ArrayList<Endereco> relatorio() {
		ArrayList<Endereco> listaE = new ArrayList<>();
		try {
			con.conectar();
			Statement instrucao = con.getConexao().createStatement();
			ResultSet rs = instrucao.executeQuery(RELATORIO);
			while (rs.next()) {
				Cliente c = clienteDao.buscar(rs.getString("cpfCliente"));
				Endereco e = new Endereco(c, rs.getString("rua"),rs.getString("bairro"),rs.getInt("numero"),
						rs.getInt("idendereco"));
				listaE.add(e);
			}
			con.desconectar();
		} catch (SQLException e) {
			System.out.println("Erro no relatório: " + e.getMessage());
		}
		return listaE;
	}

	public Endereco buscarPeloCpf(String cpf) {
		Endereco e = null;
		try {
			con.conectar();
			PreparedStatement instrucao = con.getConexao().prepareStatement(BUSCAR_CPF);
			instrucao.setString(1, cpf);
			ResultSet rs = instrucao.executeQuery();
			if (rs.next()) {
				Cliente c = clienteDao.buscar(rs.getString("cpfCliente"));
				e = new Endereco(c, rs.getString("rua"),rs.getString("bairro"),rs.getInt("idendereco"), rs.getInt("numero"));
			}
			con.desconectar();
		} catch (SQLException ex) {
			System.out.println("Erro na busca: " + ex.getMessage());
		}
		return e;		
	}

	public void exclusao(String cpf) {
		try {
			con.conectar();
			PreparedStatement instrucao = con.getConexao().prepareStatement(EXCLUIR_CPF);
			instrucao.setString(1, cpf);
			instrucao.execute();
			con.desconectar();
		} catch (Exception e) {
			System.out.println("Erro na exclusão: " + e.getMessage());
		}
	}

}
