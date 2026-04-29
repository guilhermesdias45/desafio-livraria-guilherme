package vc.com.programar;

import vc.com.programar.entidade.LivrariaVirtual;

public class Main {

    public static void main(String[] args) {
        LivrariaVirtual lv = new LivrariaVirtual();

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
            opcao = lv.capturarNumero();
            if (opcao == 1){
                lv.insertsCadastrarLivro();
            }
            if (opcao == 2){
                lv.realizarVenda();
            }
            if (opcao == 3){
                lv.listarLivros();
            }
            if (opcao == 4){
                lv.listarVendas();
            }
        } while (opcao > 0 && opcao < 5);
    }

}