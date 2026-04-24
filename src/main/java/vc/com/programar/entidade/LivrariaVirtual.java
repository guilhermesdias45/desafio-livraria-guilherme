package vc.com.programar.entidade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LivrariaVirtual {
    private final Scanner scan = new Scanner(System.in);
    private final int max_impressos = 10;
    private final int max_eletronicos = 20;
    private final int max_vendas = 50;
    private static List<Impresso> impressos = new ArrayList<>();
    private static List<Eletronico> eletronicos = new ArrayList<>();
    private static List<Venda> vendas = new ArrayList<>();
    private int numImpressos = 0;
    private int numEletronicos = 0;
    private int numVendas = 0;

    public void cadastrarLivro(){

        System.out.println("""
                Qual tipo de livro será cadastrado?
                1 - Impresso
                2 - Eletrônico
                3 - Ambos
                """);
    }

    public void realizarVenda(){

        System.out.println("Informe o nome do cliente:");
        String nomeCliente = capturarTexto();
        System.out.println("Informe quantos livros deseja comprar:");
        int qtdLivros = capturarNumero();

        for (int i = 0; i < qtdLivros; i++){
            System.out.println("Qual é o tipo do livro?");
        }
    }

    public String capturarTexto(){
        return scan.nextLine();
    }

    public Integer capturarNumero(){
        return scan.nextInt();
    }

    public Double capturarDecimal(){
        return scan.nextDouble();
    }

    public void listarLivrosImpressos(){
        System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |%n",
                "Impressos",
                "Autores",
                "Editora",
                "Preço",
                "Frete",
                "Estoque");
        for (Impresso i : impressos) {
            System.out.println(i.toString());
        }
    }

    public void listarLivrosEletronicos(){
        System.out.printf("| %20s | %20s | %20s | %20s | %20s |%n",
                "Eletronicos",
                "Autores",
                "Editora",
                "Preço",
                "Tamanho");
        for (Eletronico e : eletronicos) {
            System.out.println(e.toString());
        }
    }

    public void listarLivros(){
        System.out.printf("| %-20s | %-20s | %-20s | %-20s |%n",
                "Eletronicos",
                "Autores",
                "Editora",
                "Preço");
        for (Eletronico e : eletronicos) {
            System.out.println(e.toString());
        }
        for (Impresso i : impressos) {
            System.out.println(i.toString());
        }
    }

    public void listarVendas(){
        System.out.println("Vendas Realizadas:");
        for (Venda v : vendas) {
            v.listarLivros();
        }
    }
}
