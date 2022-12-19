package dominio;

import java.time.LocalDate;

public class Pedido {
 
	private Cliente cpfCliente;
	private int idPedido;
	private Tatuagem idTatuagem;
	private LocalDate data;
	private float preco;
	
	
	public Pedido() {
		// relação 1 para n entre cliente e pedido, um cliente pode fazer varios pedidos
		// relação 1 para 1 entre endereço e cliente
	}
	
	public Pedido(Cliente cpfCliente, int idPedido, Tatuagem idTatuagem, LocalDate data, float preco) {
		super();
		this.cpfCliente = cpfCliente;
		this.idPedido = idPedido;
		this.idTatuagem = idTatuagem;
		this.data = data;
		this.preco = preco;
	}

	public Cliente getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(Cliente cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public Tatuagem getIdTatuagem() {
		return idTatuagem;
	}

	public void setIdTatuagem(Tatuagem idTatuagem) {
		this.idTatuagem = idTatuagem;
	}
}
