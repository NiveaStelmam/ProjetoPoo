package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Tatuagem;

public class TatuagemDAO {

	private Conexao con; 
 	private final String CADASTRAR = "insert into public.tatuagem (idTatuagem, cor, tamanho) values (?, ?, ?)";
    private final String BUSCAR = "select * from public.tatuagem where (idTatuagem=?)";
    private final String ALTERAR = "update public.tatuagem set idTatuagem =?,  cor = ?, tamanho=? where idTatuagem=?";
    private final String EXCLUIR = "delete from Tatuagem where idTatuagem=?";
    private final String RELATORIO = "select * from Tatuagem";


    public TatuagemDAO(){
        con = new Conexao("jdbc:postgresql://localhost:5434/poo22","postgres","postgres");
        
    }
    
   // 
    public void cadastrar(Tatuagem t){
        try{
            con.conectar();
            PreparedStatement instrucao = 
                    con.getConexao().prepareStatement(CADASTRAR);
            instrucao.setInt(1, t.getIdTatuagem());
            instrucao.setString(2, t.getCor());
            instrucao.setInt(3, t.getTamanho());
            instrucao.execute();
            con.desconectar();
        }catch(Exception e){
            System.out.println("Erro no cadastro: "+e.getMessage());
        }
    }
    
    public 	Tatuagem buscar(int idTatuagem){
    	Tatuagem t = null;
        try{
            con.conectar();
            PreparedStatement instrucao = 
                    con.getConexao().prepareStatement(BUSCAR);
            instrucao.setInt(1, idTatuagem);
            ResultSet rs = instrucao.executeQuery();
            if(rs.next()){
                t = new Tatuagem(rs.getInt("idTatuagem"), rs.getString("cor"),
                        rs.getInt("tamanho"));
            }
            con.desconectar();
        }catch(SQLException e){
            System.out.println("Erro na busca: "+e.getMessage());
        }
        return t;
    }
   
    public void alterar(Tatuagem t, int idTatoo){
        try{
            con.conectar();
            PreparedStatement instrucao = 
                    con.getConexao().prepareStatement(ALTERAR);
            instrucao.setInt(1, t.getIdTatuagem());
            instrucao.setString(2, t.getCor());
            instrucao.setInt(3, t.getTamanho());
            instrucao.setInt(4, idTatoo);
            instrucao.execute();
            con.desconectar();
        }catch(Exception e){
            System.out.println("Erro na alteração: "+e.getMessage());
        }
    }
   
    public void exclusao(int idTatuagem){
        try{
            con.conectar();
            PreparedStatement instrucao = 
                    con.getConexao().prepareStatement(EXCLUIR);
            instrucao.setInt(1, idTatuagem);
            instrucao.execute();
            con.desconectar();
        }catch(Exception e){
            System.out.println("Erro na exclusão: "+e.getMessage());
        }
    }
     
    public ArrayList<Tatuagem> relatorio(){
        ArrayList<Tatuagem> listaTatoo = new ArrayList<>();
        try{
            con.conectar();
            Statement instrucao = con.getConexao().createStatement();
            ResultSet rs = instrucao.executeQuery(RELATORIO);
            while(rs.next()){
                Tatuagem t = new Tatuagem(rs.getInt("idTatuagem"), rs.getString("cor"),
                        rs.getInt("tamanho"));
                listaTatoo.add(t);
            }
            con.desconectar();
        }catch(SQLException e){
            System.out.println("Erro no relatório: "+e.getMessage());
        }
        return listaTatoo;
    }
    

}
