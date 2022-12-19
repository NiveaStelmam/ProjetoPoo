package visao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import dominio.Cliente;
import dominio.Endereco;
import dominio.Pedido;
import dominio.Tatuagem;
import persistencia.ClienteDAO;
import persistencia.Conexao;
import persistencia.EnderecoDAO;
import persistencia.PedidoDAO;
import persistencia.TatuagemDAO;

public class Principal {

	public static void main(String[] args) {
		
		
		inicializarBanco();
		Scanner teclado = new Scanner(System.in);
		int opM1, opM2, opM3, opM4 = 0, idTatoo,  pedido;
		String cpf = null, cpfAux;
		Cliente c = null;
		Tatuagem t = null;
		Pedido pd = null;
		Endereco e = null;

		ClienteDAO cDAO = new ClienteDAO();
		TatuagemDAO tDAO = new TatuagemDAO();
		PedidoDAO pdDAO = new PedidoDAO(cDAO,tDAO);
		EnderecoDAO eDAO = new EnderecoDAO(cDAO);

		do {
			System.out.println("\n******** Menu Principal ********");
			System.out.println("\n--------------------------------");
			System.out.println("1 - Cliente");
			System.out.println("2 - Tatuagem");
			System.out.println("3 - Pedido");
			System.out.println("4 - Sair");
			System.out.println("\nQual a opção desejada? ");
			System.out.println("\n--------------------------------");
			opM1 = teclado.nextInt();
			switch (opM1) {
			case 1:
				do {
					System.out.println("\n******** Menu Cliente ********");
					System.out.println("\n--------------------------------");
					System.out.println("1 - Cadastrar cliente");
					System.out.println("2 - Buscar");
					System.out.println("3 - Alterar");
					System.out.println("4 - excluir");
					System.out.println("5 - relatorio");
					System.out.println("6 - sair");
					System.out.println("\nQual a opção desejada? ");
					System.out.println("\n--------------------------------");
					opM2 = teclado.nextInt();

					switch (opM2) {
					case 1:
						System.out.println("Cadastrando cliente...");
						System.out.println("Digite o CPF do cliente que voce deseja cadastrar: ");
						teclado.nextLine();
						cpf = teclado.nextLine();

						c = new Cliente();
						c.setCpf(cpf);
						System.out.println("Digite o nome: ");
						c.setNome(teclado.nextLine());
						cDAO.cadastrar(c);
						e = new Endereco();
						System.out.println("Digite o bairro: ");
						e.setBairro(teclado.nextLine());
						System.out.println("Digite o rua: ");
						e.setRua(teclado.nextLine());
						System.out.println("Digite o numero: ");
						e.setNumero(teclado.nextInt());
						System.out.println("Digite o id do Endereço: ");
						e.setIdEndereco(teclado.nextInt()); 
						e.setCpfCliente(c);
						eDAO.cadastrar(e);
						System.out.println("Cliente cadastrado com sucesso!");

						break;

					case 2:
						System.out.println("Buscando Cliente...");
						System.out.println("Digite o CPF do cliente que voce deseja encontrar: ");
						teclado.nextLine();

						cpfAux = teclado.nextLine();

						// buscar o cliente no bd
						c = cDAO.buscar(cpfAux);
						if (c == null)
							System.out.println("Cliente não cadastrado!");
						else {
							System.out.println("Cliente localizado!");
							System.out.println("Nome: " + c.getNome());
							System.out.println("CPF: " + c.getCpf());
							e = eDAO.buscarPeloCpf(c.getCpf());
							if(e != null) {
							System.out.println("Rua: " + e.getRua());
							System.out.println("Bairro: " + e.getBairro());
							System.out.println("Numero: " + e.getNumero());
							}else {
								System.out.println("Endereço não cadastrado!");
							}
							
						}
						break;

					case 3:
						System.out.println("Alterando dados do cliente...");
						System.out.println("Digite o CPF do cliente que voce deseja alterar: ");
						teclado.nextLine();
						cpfAux = teclado.nextLine();

						c = cDAO.buscar(cpfAux);
						System.out.println("Cliente localizado!");
						System.out.println("CPF: " + c.getCpf());
						System.out.println("Nome: " + c.getNome());
						System.out.println("Digite o novo CPF: ");
						c.setCpf(teclado.nextLine());
						System.out.println("Digite o novo nome: ");
						c.setNome(teclado.nextLine());
						cDAO.alterar(c, cpfAux);
						System.out.println("Dados alterados com sucesso!");

						break;

					case 4:
						System.out.println("Deletando cliente...");
						System.out.println("Qual o CPF do cliente que voce deseja deletar? ");
						teclado.nextLine();
						cpfAux = teclado.nextLine();

						cDAO.exclusao(cpfAux);
						System.out.println("Cliente excluído com sucesso!");

						break;

					case 5:
						System.out.println("Cliente cadastrados:");

						ArrayList<Cliente> relatorio = cDAO.relatorio();

						for (int i = 0; i < relatorio.size(); i++) {
							eDAO.buscarPeloCpf(relatorio.get(i).getCpf());
							System.out.println("-----------------------------");
							System.out.println("CPF: " + relatorio.get(i).getCpf());
							System.out.println("Nome: " + relatorio.get(i).getNome());
							
							
						}
						System.out.println("Fim de relatório\n");
						break;

					default:

					}

				} while (opM2 != 6);
				break;
			case 2:
				do {
					System.out.println("\n******** Menu Tatuagem ********");
					System.out.println("\n--------------------------------");
					System.out.println("1 - Cadastrar tatuagem");
					System.out.println("2 - Buscar tataugem");
					System.out.println("3 - Alterar tatuagem");
					System.out.println("4 - excluir tatuagem");
					System.out.println("5 - relatorio");
					System.out.println("6 - sair");
					System.out.println("\nQual a opção desejada? ");
					System.out.println("\n--------------------------------");
					opM3 = teclado.nextInt();

					switch (opM3) {
					case 1:
						System.out.println("Cadastrando uma nova tatuagem...\n");
						System.out.println("Digite o ID da nova tatuagem : ");
						idTatoo = teclado.nextInt();

						t = new Tatuagem();
						t.setIdTatuagem(idTatoo);
						// System.out.println("Digite o ID da nova tatuagem: ");
						// t.setIdTatuagem(teclado.nextInt());
						System.out.println("Digite a cor da tatuagem: ");
						teclado.nextLine();
						t.setCor(teclado.nextLine());
						System.out.println("Digite o tamanho da tatuagem: ");
						t.setTamanho(teclado.nextInt());
						System.out.println("Uma nova tatuagem foi cadastrado!");
						tDAO.cadastrar(t);

						break;
					case 2:
						System.out.println("Buscando Tatuagem...");
						System.out.println("Digite o ID da tatuagem que voce deseja encontrar: ");

						idTatoo = teclado.nextInt();

						// buscar o cliente no bd
						t = tDAO.buscar(idTatoo);
						if (t == null)
							System.out.println("Esta tatuagem não está cadastrado no sistema!");
						else {
							System.out.println("Tatuagem encontrada!");
							System.out.println("ID da tatuagem: " + t.getIdTatuagem());
							System.out.println("Cor: " + t.getCor());
							System.out.println("Tamanho: " + t.getTamanho());

						}
						break;

					case 3:
						System.out.println("Alterando tatuagem...");
						System.out.println("Digite o ID da tatuagem que voce deseja encontrar: ");
						idTatoo = teclado.nextInt();

						t = tDAO.buscar(idTatoo);
						System.out.println("Tatuagem ecnontrada!");
						System.out.println("ID da tatuagem: " + t.getIdTatuagem());
						System.out.println("Cor: " + t.getCor());
						System.out.println("Tamanho: " + t.getTamanho());
						System.out.println("Digite o novo ID da tatuagem: ");
						t.setIdTatuagem(teclado.nextInt());
						System.out.println("Digite a novo cor da tatuagem: ");
						teclado.nextLine();
						t.setCor(teclado.nextLine());
						System.out.println("Digite o novo tamanho: ");
						t.setTamanho(teclado.nextInt());
						tDAO.alterar(t, idTatoo);
						System.out.println("Dados alterados com sucesso!");

						break;

					case 4:
						System.out.println("Deletando uma tatuagem...");
						System.out.println("Qual ID da tatuagem que voce deseja deletar? ");
						idTatoo = teclado.nextInt();

						tDAO.exclusao(idTatoo);
						System.out.println("Tatuagem excluída!");

						break;

					case 5:
						System.out.println("Tatuagens cadastrados:");
						ArrayList<Tatuagem> relatorio = tDAO.relatorio();

						for (int i = 0; i < relatorio.size(); i++) {
							System.out.println("-----------------------------");
							System.out.println("ID da tatuagem: " + relatorio.get(i).getIdTatuagem());
							System.out.println("Cor: " + relatorio.get(i).getCor());
							System.out.println("Tamanho: " + relatorio.get(i).getTamanho());

						}
						System.out.println("Fim de relatório\n");
						break;
					default:

					}

				} while (opM3 != 6);
				break;
			case 3:
				do {
					System.out.println("\n******** Menu Pedido ********");
					System.out.println("\n--------------------------------");
					System.out.println("1 - Cadastrar pedido");
					System.out.println("2 - Buscar pedido");
					System.out.println("3 - Alterar pedido");
					System.out.println("4 - excluir pedido");
					System.out.println("5 - relatorio");
					System.out.println("6 - sair");
					System.out.println("\nQual a opção desejada? ");
					System.out.println("\n--------------------------------");
					opM4 = teclado.nextInt();

					switch (opM4) {
					case 1:
						System.out.println("Cadastrando pedido...");
						System.out.println("Digite o ID do pedido: ");
						pedido = teclado.nextInt();

						pd = new Pedido();
						pd.setIdPedido(pedido);
						System.out.println("Qual é o CPF do cliente vinculado a este pedido? ");
						teclado.nextLine();
						String cpfDoCliente = teclado.nextLine();
						Cliente clientePedido = cDAO.buscar(cpfDoCliente);
						pd.setCpfCliente(clientePedido);
						System.out.println("Data de realização do pedido: ");
						pd.setData(LocalDate.parse(teclado.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
						System.out.println("Qual é o id da tatuagem vinculado a este pedido? ");
						int idTatuagem = teclado.nextInt();
						Tatuagem tatooPedido = tDAO.buscar(idTatuagem);
						pd.setIdTatuagem(tatooPedido);
						System.out.println("Preço do pedido: ");
						pd.setPreco(teclado.nextFloat());
						pdDAO.cadastrar(pd);
						System.out.println("O pedido foi cadastrado!");

						break;
					case 2:
						System.out.println("Buscando pedido...");
						System.out.println("Digite o ID do pedido que voce deseja encontrar: ");
						pedido = teclado.nextInt();

						// buscar o cliente no bd
						pd = pdDAO.buscar(pedido);
						if (pd == null)
							System.out.println("Pedido não cadastrado!");
						else {
							System.out.println("Cliente localizado!");
							System.out.println("Id do Pedido: " + pd.getIdPedido());
							System.out.println("CPF do cliente vinculado: " + pd.getCpfCliente());
							System.out.println("Data: " + pd.getData());
							System.out.println("Preço: " + pd.getPreco());

						}
						break;
					case 3:
						System.out.println("Alterando dados do pedido...");
						System.out.println("Digite o ID do pedido que voce deseja alterar: ");
						pedido = teclado.nextInt();

						pd = pdDAO.buscar(pedido);
						System.out.println("Pedido localizado!"); 
						System.out.println("ID do pedido: " + pd.getIdPedido());
						System.out.println("CPF do cliente vinculado: " + pd.getCpfCliente());
						System.out.println("Data: " + pd.getData());
						System.out.println("Preço: " + pd.getPreco());
						System.out.println("Digite o novo ID: ");
						pd.setIdPedido(teclado.nextInt());
						System.out.println("Digite o novo CPF do cliente que está vinculado ao pedido: ");
						teclado.nextLine();
						String cpfClient = teclado.nextLine();
						Cliente clientPedido = cDAO.buscar(cpfClient);
						pd.setCpfCliente(clientPedido);
						//pd.setCpfCliente("");
						System.out.println("Digite a novo data: ");
						pd.setData(LocalDate.parse(teclado.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
						System.out.println("Digite o novo preço: ");
						pd.setPreco(teclado.nextFloat());
						pdDAO.alterar(pd, pedido);
						System.out.println("Dados alterados com sucesso!");

						break;
					case 4:
						System.out.println("Deletando pedido...");
						System.out.println("Qual o ID do pedido que voce deseja deletar? ");
						pedido = teclado.nextInt();

						pdDAO.exclusao(pedido);
						System.out.println("Pedido excluído com sucesso!");

						break;
					case 5:
						System.out.println("Pedidos cadastrados:");

						ArrayList<Pedido> relatorio = pdDAO.relatorio();

						for (int i = 0; i < relatorio.size(); i++) {
							System.out.println("-----------------------------");
							System.out.println("ID do pedido: " + relatorio.get(i).getIdPedido());
							System.out.println("CPF do cliente vinculado: " + relatorio.get(i).getCpfCliente());
							System.out.println("Data: " + relatorio.get(i).getData());
							System.out.println("Preço: " + relatorio.get(i).getPreco());
						}
						System.out.println("Fim de relatório\n");
						break;
					default:

					}

				} while (opM4 != 6);

			}

		} while (opM1 != 4);
		teclado.close();
	}

	private static void inicializarBanco() {
		// TODO Auto-generated method stub
		String createBanco = "CREATE TABLE IF NOT EXISTS public.cliente (nome varchar NULL,cpf varchar NOT NULL,CONSTRAINT cliente_pk PRIMARY KEY (cpf)) ;\r\n"
				+ "\r\n"
				+ "CREATE TABLE IF NOT exists public.endereco (rua varchar NULL,bairro varchar NULL,numero int4 NULL,	idendereco int4 NOT NULL,cpfcliente varchar NULL,	CONSTRAINT endereco_pk PRIMARY KEY (idendereco));\r\n"
				+ "CREATE TABLE public.tatuagem (idtatuagem int4 NOT NULL,cor varchar NULL,tamanho int4 NULL,CONSTRAINT tatuagem_pk PRIMARY KEY (idtatuagem));\r\n"
				+ "ALTER table  public.endereco ADD CONSTRAINT endereco_fk FOREIGN KEY (cpfcliente) REFERENCES public.cliente(cpf) ON DELETE restrict ;\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "CREATE TABLE public.pedido (idpedido int4 NOT NULL,cpfcliente varchar NULL,\"data\" date NULL,preco float4 NULL,idtatoo int4 NOT NULL,CONSTRAINT pedido_pk PRIMARY KEY (idpedido));\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "ALTER TABLE public.pedido ADD CONSTRAINT pedido_fk FOREIGN KEY (cpfcliente) REFERENCES public.cliente(cpf) ON DELETE RESTRICT;\r\n"
				+ "ALTER TABLE public.pedido ADD CONSTRAINT pedido_tatoo_fk FOREIGN KEY (idtatoo) REFERENCES public.tatuagem(idtatuagem) ON DELETE CASCADE;\r\n"
				+ "\r\n"
				+ "\r\n";
				
				
		Conexao c = new Conexao("jdbc:postgresql://localhost:5434/pooteste","postgres","postgres");
		c.conectar();
		try {
			c.getConexao().prepareStatement(createBanco).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
