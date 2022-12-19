package dominio;

public class Telefone {
 // Telefone (PK_IdTelefone, FK_IdCliente, Número, DDD)
	private String idTelefone;
	private Cliente cpfCliente;
	private String numero;
	private String ddd;
	
	public Telefone() {
		
	}
	
	public Telefone(String idTelefone, Cliente cpfCliente, String numero, String ddd) {
		this.idTelefone =  idTelefone;
		this.cpfCliente =  cpfCliente;
		this.numero = numero;
		this.ddd = ddd;	
	}

	public String getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(String idTelefone) {
		this.idTelefone = idTelefone;
	}

	public Cliente getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(Cliente cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	
	
}
