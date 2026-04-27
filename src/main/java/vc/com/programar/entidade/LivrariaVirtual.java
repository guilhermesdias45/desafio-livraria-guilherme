package vc.com.programar.entidade;

import vc.com.programar.repositorio.LivroRepositorio;
import vc.com.programar.repositorio.VendaRepositorio;

import java.util.List;
import java.util.Scanner;

public class LivrariaVirtual {
    private final Scanner scan = new Scanner(System.in);
    private final Scanner apenasTexto = new Scanner(System.in);
    private final int max_impressos = 10;
    private final int max_eletronicos = 20;
    private final int max_vendas = 50;
    private int numImpressos = 0;
    private int numEletronicos = 0;
    private int numVendas = 0;

    private final LivroRepositorio livro;
    private final VendaRepositorio venda;

    public LivrariaVirtual() {
        this.numImpressos = 0;
        this.numEletronicos = 0;
        this.numVendas = 0;

        this.livro = new LivroRepositorio();
        this.venda = new VendaRepositorio();
    }

    public void cadastrarLivro(){

        System.out.println("""
                Qual tipo de livro será cadastrado?
                1 - Impresso
                2 - Eletrônico
                3 - Ambos
                """);

        int opcao = capturarNumero();

        System.out.println("Informe o nome do livro:");
        String nome = capturarTexto();
        System.out.println("Informe o(s) autore(s) do livro:");
        String autor = capturarTexto();
        System.out.println("Informe a editora do livro:");
        String editora = capturarTexto();
        System.out.println("Informe o preço do livro:");
        Double preco = capturarDecimal();

        Double frete;
        Integer estoque;
        Integer tamanho;

        if (opcao == 1 || opcao == 3){
            System.out.println("Informe o frete do livro:");
            frete = capturarDecimal();
            System.out.println("Informe o estoque do livro:");
            estoque = capturarNumero();

            Impresso impresso = new Impresso(nome, autor, editora, preco, frete, estoque);

            livro.salvar(impresso);
        }
        if (opcao == 2 || opcao == 3){
            System.out.println("Informe o tamanho do livro:");
            tamanho = capturarNumero();

            Eletronico eletronico = new Eletronico(nome, autor, editora, preco, tamanho);

            livro.salvar(eletronico);
        }
    }

    public void realizarVenda(){
        System.out.println("Informe o nome do cliente:");
        String nomeCliente = capturarTexto();
        System.out.println("Informe quantos livros deseja comprar:");
        int qtdLivros = capturarNumero();

        Venda novaVenda = new Venda(nomeCliente,
                0.0);

        for (int i = 0; i < qtdLivros; i++){
            int opcao;
            do {
                System.out.printf("""
                    Qual é o tipo do livro %d?
                    1 - Impresso
                    2 - Eletrônico
                    """, (i+1));

                opcao = capturarNumero();
                if (opcao == 1){
                    listarLivrosImpressos();
                } else if (opcao == 2) {
                    listarLivrosEletronicos();
                }
                if (opcao < 1 || opcao > 2){
                    System.out.print("\nOpção Inválida!\n");
                }
            } while (opcao < 1 || opcao > 2);

            System.out.println("Qual é o ID do livro desejado?");
            opcao = capturarNumero();

            Livro livroEscolhido = livro.encontrarPorNumero(opcao);

            novaVenda.addLivro(livroEscolhido, 0);
            novaVenda.setValor(novaVenda.getValor() + livroEscolhido.getPreco());
        }

        venda.salvar(novaVenda);
    }

    public String capturarTexto(){
        return apenasTexto.nextLine();
    }

    public Integer capturarNumero(){
        return scan.nextInt();
    }

    public Double capturarDecimal(){
        return scan.nextDouble();
    }

    public void listarLivrosImpressos(){
        List<Impresso> impressosLista = livro.listarTodosImpressos();
        System.out.printf("| %-3s | %-20s | %-20s | %-20s | %-11s | %-8s | %-3s |%n",
                "Id",
                "Impressos",
                "Autores",
                "Editora",
                "Preço",
                "Frete",
                "Estoque");
        for (Impresso i : impressosLista) {
            System.out.println(i.toString());
        }
    }

    public void listarLivrosEletronicos(){
        List<Eletronico> eletronicosLista = livro.listarTodosEletronicos();
        System.out.printf("| %-3s | %-20s | %-20s | %-20s | %-11s | %-7s |%n",
                "Id",
                "Eletronicos",
                "Autores",
                "Editora",
                "Preço",
                "Tamanho");
        for (Eletronico e : eletronicosLista) {
            System.out.println(e.toString());
        }
    }

    public void listarLivros(){
        listarLivrosEletronicos();
        listarLivrosImpressos();
    }

    public void listarVendas(){
        System.out.println("Vendas Realizadas:");
        List<Venda> vendasLista = venda.listarTodos();

        for (int i = 0; i < vendasLista.size(); i++) {
            System.out.printf("Venda nº%d:\n", (i+1));
            vendasLista.get(i).listarLivros();
        }
    }
}
