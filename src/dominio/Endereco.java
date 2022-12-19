package dominio;

public class Endereco {
	// Endereço (FK_CPFCliente, Rua, bairro, Número)
	private Cliente cpfCliente;
	private String rua;
	private String bairro;
	private int numero;
	private int idEndereco;

	public Endereco() {

	}

	public Endereco(Cliente cpfCliente, String rua, String bairro, int numero, int idEndereco) {
		super();
		this.cpfCliente = cpfCliente;
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.idEndereco = idEndereco;
	}

	public Cliente getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(Cliente cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

}
