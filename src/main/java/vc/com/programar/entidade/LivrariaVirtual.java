package vc.com.programar.entidade;

import vc.com.programar.excecao.ObjetoInvalidoException;
import vc.com.programar.repositorio.LivroRepositorio;
import vc.com.programar.repositorio.VendaRepositorio;

import java.util.List;
import java.util.Scanner;

public class LivrariaVirtual {
    private final Scanner scan = new Scanner(System.in);
    private final Scanner apenasTexto = new Scanner(System.in);
    private int max_impressos = 10;
    private int max_eletronicos = 20;
    private int max_vendas =50;
    private int numImpressos;
    private int numEletronicos;
    private int numVendas;

    private final LivroRepositorio livro;
    private final VendaRepositorio venda;

    public LivrariaVirtual() {
        this.livro = new LivroRepositorio();
        this.venda = new VendaRepositorio();

        atualizarEletronico();
        atualizarImpresso();
        atualizarVenda();
    }

    public void atualizarImpresso(){
        this.numImpressos = (int) livro.qtdImpressos();
    }

    public void atualizarEletronico() {
        this.numEletronicos = (int) livro.qtdEletronicos();
    }

    public void atualizarVenda() {
        this.numVendas = (int) venda.qtdVendas();
    }

    public void cadastrarLivro(
            int opcao,
            String nome,
            String autor,
            String editora,
            Double preco,
            Double frete,
            Integer estoque,
            Integer tamanho
            ){

        if ((opcao == 1 || opcao == 3) && this.numImpressos >= max_impressos){
            throw new ObjetoInvalidoException("Já atingiu o número de impressos máximo.");
        }
        if ((opcao == 2 || opcao == 3) && this.numEletronicos >= max_eletronicos){
            throw new ObjetoInvalidoException("Já atingiu o número de eletrônicos máximo.");
        }
        if (nome == null || nome.isEmpty() ||
        autor == null || autor.isEmpty() ||
        editora == null || editora.isEmpty() ||
        preco == null || preco <= 0){
            throw new ObjetoInvalidoException("O livro deve conter nome, autor(es), editora e preço para ser cadastrado com sucesso.");
        }

        if (opcao == 1 || opcao == 3){
            if (frete == null || frete <= 0 ||
            estoque == null || estoque <= 0){
                throw new ObjetoInvalidoException("O impresso deve conter frete e estoque válidos para ser cadastrado com sucesso.");
            }

            Impresso impresso = new Impresso(nome, autor, editora, preco, frete, estoque);

            livro.salvar(impresso);
        }
        if (opcao == 2 || opcao == 3){
            if (tamanho == null || tamanho <= 0){
                throw new ObjetoInvalidoException("O eletrônico deve conter um tamanho válido para ser cadastrado com sucesso.");
            }

            Eletronico eletronico = new Eletronico(nome, autor, editora, preco, tamanho);

            livro.salvar(eletronico);
        }
    }

    public void insertsCadastrarLivro(){
        System.out.println("""
                Qual tipo de livro será cadastrado?
                1 - Impresso
                2 - Eletrônico
                3 - Ambos
                """);

        int opcao = capturarNumero();

        if (opcao < 1 || opcao > 3){
            System.out.println("Opção inválida.");
            return;
        }

        System.out.println("Informe o nome do livro:");
        String nome = capturarTexto();
        System.out.println("Informe o(s) autore(s) do livro:");
        String autor = capturarTexto();
        System.out.println("Informe a editora do livro:");
        String editora = capturarTexto();
        System.out.println("Informe o preço do livro:");
        Double preco = capturarDecimal();

        Double frete = null;
        Integer estoque = null;
        Integer tamanho = null;

        if (opcao == 1 || opcao == 3){
            System.out.println("Informe o frete do livro:");
            frete = capturarDecimal();
            System.out.println("Informe o estoque do livro:");
            estoque = capturarNumero();
        }
        if (opcao == 2 || opcao == 3){
            System.out.println("Informe o tamanho do livro:");
            tamanho = capturarNumero();
        }

        try{
            cadastrarLivro(opcao, nome, autor, editora, preco, frete, estoque, tamanho);
        } catch (ObjetoInvalidoException e){
            System.out.println(e.getMessage());
        }
    }

    public void realizarVenda(){
        if (this.numVendas < max_vendas){
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

                if ((livroEscolhido instanceof Impresso &&
                        ((Impresso) livroEscolhido).getEstoque() > 0) ||
                        livroEscolhido instanceof Eletronico){
                    novaVenda.addLivro(livroEscolhido, novaVenda.getLivros().size());
                    novaVenda.setValor(novaVenda.getValor() + livroEscolhido.getPreco());
                    livro.atualizarLivro(livroEscolhido);
                } else {
                    System.out.println("O livro escolhido não está mais disponível! Escolha outro livro ou outro formato.");
                    i--;
                }
            }

            if (!novaVenda.getLivros().isEmpty()){
                venda.salvar(novaVenda);
            } else {
                System.out.println("Não foi possível salvar a venda, pois não foi selecionado nenhum livro!");
            }

            atualizarVenda();
        } else {
            System.out.println("Já atingiu o número de vendas máximo.");
        }
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
        System.out.printf("| %-3s | %-20s | %-20s | %-20s | %-11s | %-9s |%n",
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
        if (numVendas <= 0){
            System.out.println("Não há Vendas para serem listadas.");
        } else {
            System.out.println("Vendas Realizadas:");
            List<Venda> vendasLista = venda.listarTodos();

            for (int i = 0; i < vendasLista.size(); i++) {
                System.out.printf("Venda nº%d:\n", vendasLista.get(i).getNumero());
                vendasLista.get(i).listarLivros();
            }
        }
    }

    public void setMax_vendas(int max_vendas) {
        this.max_vendas = max_vendas;
    }

    public void setMax_eletronicos(int max_eletronicos) {
        this.max_eletronicos = max_eletronicos;
    }

    public void setMax_impressos(int max_impressos) {
        this.max_impressos = max_impressos;
    }
}
