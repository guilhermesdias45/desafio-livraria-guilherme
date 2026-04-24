package vc.com.programar;

public class Main {

    public static void main(String[] args) {
//        impressos.add(new Impresso(
//            "Padrões de Projeto",
//            "Erich Gamma et al.",
//            "Addison-Wesley",
//            189.90,
//            22.00,
//            10
//            ));
//
//        impressos.add(new Impresso(
//            "Spring Boot em Ação",
//            "Craig Walls",
//            "Manning",
//            129.90,
//            15.00,
//            20
//        ));


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
//            opcao = capturarNumero();
            if (opcao == 3){
//                listarLivros();
            }
        } while (opcao > 0 && opcao < 5);
    }

}