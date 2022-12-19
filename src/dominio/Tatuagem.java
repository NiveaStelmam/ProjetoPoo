package dominio;

public class Tatuagem {
	//Tatuagem (PK_IdTatuagem, FK_IDPedido, cor)
	private int idTatuagem;
	private String cor;
	private int tamanho;
	
	public Tatuagem() {
		
	}
	
	public Tatuagem(int idTatuagem, String cor, int tamanho) {
		this.idTatuagem = idTatuagem;
		this.cor = cor;
		this.tamanho = tamanho;
		
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public int getIdTatuagem() {
		return idTatuagem;
	}

	public void setIdTatuagem(int idTatuagem) {
		this.idTatuagem = idTatuagem;
	}


	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
	

}
