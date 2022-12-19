package persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Cliente;
import dominio.Pedido;
import dominio.Tatuagem;

public class PedidoDAO {
	

	 
 	private Conexao con; 
 	private final String CADASTRAR = "insert into public.pedido (cpfCliente, idPedido, data, preco,idtatoo) values (?, ?, ?, ?,?)";
    private final String BUSCAR = "select * from public.pedido where (idPedido=?)";
    private final String ALTERAR = "update public.pedido set cpfCliente =?, idPedido=?, data=?, preco=? where idPedido=?";
    private final String EXCLUIR = "delete from Pedido where idPedido=?";
    private final String RELATORIO = "select * from Pedido";
    
    
    
    private ClienteDAO clienteDao; // quando salvar um pedido, busca um cliente pelo cpf
    private TatuagemDAO tatuagemDao; // quando salvar um pedido, busca um cliente pela tatuagem
    
    
    public PedidoDAO(){
        con = new Conexao("jdbc:postgresql://localhost:5434/poo22","postgres","postgres"); // conexao com bd
        
    }
    
    
    
   public PedidoDAO(ClienteDAO clienteDao, TatuagemDAO tatuagemDao) {
        con = new Conexao("jdbc:postgresql://localhost:5434/poo22","postgres","postgres");
		this.clienteDao = clienteDao;
		this.tatuagemDao = tatuagemDao;
	}




    public void cadastrar(Pedido p){
        try{
            con.conectar();
            PreparedStatement instrucao = 
                    con.getConexao().prepareStatement(CADASTRAR);
            instrucao.setString(1,p.getCpfCliente().getCpf());
            instrucao.setInt(2, p.getIdPedido());
            instrucao.setDate(3, Date.valueOf(p.getData())); // converter localdate para date
            instrucao.setFloat(4, p.getPreco());
            instrucao.setFloat(5, p.getIdTatuagem().getIdTatuagem());
            instrucao.execute();
            con.desconectar();
        }catch(Exception e){
            System.out.println("Erro no cadastro: "+e.getMessage());
        }
    }
    
    public 	Pedido buscar(int idPedido){
    	Pedido p = null;
        try{
            con.conectar();
            PreparedStatement instrucao = 
                    con.getConexao().prepareStatement(BUSCAR);
            instrucao.setInt(1, idPedido);
            ResultSet rs = instrucao.executeQuery();
            if(rs.next()){
            	Cliente c = clienteDao.buscar(rs.getString("cpfCliente"));
            	Tatuagem t = tatuagemDao.buscar(rs.getInt("idtatoo"));
                p = new Pedido(c,rs.getInt("idPedido"),t, rs.getDate("data").toLocalDate(), rs.getFloat("preco"));// tolocaldate para converter date pra localdate
            }
            con.desconectar();
        }catch(SQLException e){
            System.out.println("Erro na busca: "+e.getMessage());
        }
        return p;
    }
   
    public void alterar(Pedido p, int idpedidoantigo){
        try{
            con.conectar();
            PreparedStatement instrucao = 
                    con.getConexao().prepareStatement(ALTERAR);
            instrucao.setString(1,p.getCpfCliente().getCpf());
            instrucao.setInt(2, p.getIdPedido());
            instrucao.setDate(3, Date.valueOf(p.getData()));
            instrucao.setFloat(4, p.getPreco());
            instrucao.setInt(5, idpedidoantigo);
            instrucao.execute();
            con.desconectar();
        }catch(Exception e){
            System.out.println("Erro na alteração: "+e.getMessage());
        }
    }
   
    public void exclusao(int idPedido){
        try{
            con.conectar();
            PreparedStatement instrucao = 
                    con.getConexao().prepareStatement(EXCLUIR);
            instrucao.setInt(1, idPedido);
            instrucao.execute();
            con.desconectar();
        }catch(Exception e){
            System.out.println("Erro na exclusão: "+e.getMessage());
        }
    }
     
    public ArrayList<Pedido> relatorio(){
        ArrayList<Pedido> listaPedido = new ArrayList<>();
        try{
            con.conectar();
            Statement instrucao = con.getConexao().createStatement();
            ResultSet rs = instrucao.executeQuery(RELATORIO);
            while(rs.next()){
            	Cliente c = clienteDao.buscar(rs.getString("cpfCliente"));
            	Tatuagem tatoo = tatuagemDao.buscar(rs.getInt("idPedido"));
                Pedido p = new Pedido(c, rs.getInt("idPedido"),tatoo, rs.getDate("data").toLocalDate(), rs.getFloat("preco"));
                
                listaPedido.add(p);
            }
            con.desconectar();
        }catch(SQLException e){
            System.out.println("Erro no relatório: "+e.getMessage());
        }
        return listaPedido;
    }
    

	
	
	
	
	
	
	
	
}
