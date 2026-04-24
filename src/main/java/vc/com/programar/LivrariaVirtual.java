package vc.com.programar;

import vc.com.programar.entidade.Eletronico;
import vc.com.programar.entidade.Impresso;
import vc.com.programar.entidade.Venda;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LivrariaVirtual {
    private static final Scanner scan = new Scanner(System.in);
    private final int max_impressos = 10;
    private final int max_eletronicos = 20;
    private final int max_vendas = 50;
    private static List<Impresso> impressos = new ArrayList<>();
    private static List<Eletronico> eletronicos = new ArrayList<>();
    private static List<Venda> vendas = new ArrayList<>();
    private int numImpressos = 0;
    private int numEletronicos = 0;
    private int numVendas = 0;

    public static void main(String[] args) {
        impressos.add(new Impresso(
            "Padrões de Projeto",
            "Erich Gamma et al.",
            "Addison-Wesley",
            189.90,
            22.00,
            10
            ));

        impressos.add(new Impresso(
            "Spring Boot em Ação",
            "Craig Walls",
            "Manning",
            129.90,
            15.00,
            20
        ));


        int opcao = 0;
        do {
            System.out.println("""
                    Escolha uma opção:
                    1 - Cadastrar Livro
                    2 - Realizar Venda
                    3 - Listar Livros
                    4 - Listar Vendas
                    5 - Sair do Programa
                    """);
            opcao = capturarNumero();
            if (opcao == 3){
                listarLivros();
            }
        } while (opcao > 0 && opcao < 5);
    }

    public void cadastrarLivro(){

        System.out.println("""
                Qual tipo de livro será cadastrado?
                1 - Impresso
                2 - Eletrônico
                3 - Ambos
                """);
    }

    public static void realizarVenda(){

        System.out.println("Informe o nome do cliente:");
        String nomeCliente = capturarTexto();
        System.out.println("Informe quantos livros deseja comprar:");
        int qtdLivros = capturarNumero();

        for (int i = 0; i < qtdLivros; i++){
            System.out.println("Qual é o tipo do livro?");
        }
    }

    public static String capturarTexto(){
        return scan.nextLine();
    }

    public static Integer capturarNumero(){
        return scan.nextInt();
    }

    public static Double capturarDecimal(){
        return scan.nextDouble();
    }

    public static void listarLivrosImpressos(){
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

    public static void listarLivrosEletronicos(){
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

    public static void listarLivros(){
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

    public static void listarVendas(){

    }
}