package dominio;

public class Cliente {

	//Cliente (PK_CPF, nome, endereço)
	private String cpf;
	private String nome;
	
	public Cliente() {
		
	}
	
	public Cliente(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome; 
		
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "CPF do cliente:" + cpf + ", nome:" + nome + "]";
	}

	
	
}
