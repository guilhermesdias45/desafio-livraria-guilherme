package vc.com.programar.entidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import vc.com.programar.entidade.data.DataLivrosTest;
import vc.com.programar.entidade.data.LivroNuloTest;
import vc.com.programar.excecao.ObjetoInvalidoException;

import static org.junit.jupiter.api.Assertions.*;

class LivrariaVirtualTest {

    @ParameterizedTest(name = "Cadastro bem sucedido informando {0} como opção, {1} como nome, {2} como autor," +
            "{3} como editora, {4} como preço, {5} como frete, {6} como estoque e {7} como tamanho.")
    @ArgumentsSource(DataLivrosTest.class)
    void verificarSeImpressoFoiCadastradoComSucessoTest(
            int opcao,
            String nome,
            String autor,
            String editora,
            Double preco,
            Double frete,
            Integer estoque,
            Integer tamanho
    ) {
        LivrariaVirtual lv = new LivrariaVirtual();

        lv.cadastrarLivro(
            opcao,
            nome,
            autor,
            editora,
            preco,
            frete,
            estoque,
            tamanho
        );
    }

    @Test
    @DisplayName("Validar se objeto InvalidoException é arremessado quando impressos atinge o limite.")
    void validarSeObjetoInvalidoExceptionEArremessadoQuandoImpressosAtingeLimite(){
        LivrariaVirtual lv = new LivrariaVirtual();
        lv.setMax_impressos(0);

        ObjetoInvalidoException obj = assertThrows(
                ObjetoInvalidoException.class, () -> lv.cadastrarLivro(
                        1,
                        "Joãozinho",
                        "João Autor",
                        "João Editora",
                        37.99,
                        23.50,
                        5,
                        null
                )
        );

        Assertions.assertEquals("Já atingiu o número de impressos máximo.", obj.getMessage());
    }

    @Test
    @DisplayName("Validar se objeto InvalidoException é arremessado quando eletrônicos atinge o limite.")
    void validarSeObjetoInvalidoExceptionEArremessadoQuandoEletronicosAtingeLimite(){
        LivrariaVirtual lv = new LivrariaVirtual();
        lv.setMax_eletronicos(0);

        ObjetoInvalidoException obj = assertThrows(
                ObjetoInvalidoException.class, () -> lv.cadastrarLivro(
                        2,
                        "Mariazinha",
                        "Maria Autor",
                        "Maria Editora",
                        7.77,
                        null,
                        null,
                        200
                )
        );

        Assertions.assertEquals("Já atingiu o número de eletrônicos máximo.", obj.getMessage());
    }

    @ParameterizedTest(name = "Cadastro bem sucedido informando {0} como opção, {1} como nome, {2} como autor," +
            "{3} como editora, {4} como preço, {5} como frete, {6} como estoque e {7} como tamanho.")
    @ArgumentsSource(LivroNuloTest.class)
    void validarSePossivelCriacaoDeObjetoLivroComDadosObrigatoriosNulos(
            int opcao,
            String nome,
            String autor,
            String editora,
            Double preco,
            Double frete,
            Integer estoque,
            Integer tamanho
    ){
        LivrariaVirtual lv = new LivrariaVirtual();

        ObjetoInvalidoException obj = assertThrows(
                ObjetoInvalidoException.class, () -> lv.cadastrarLivro(
                        opcao,
                        nome,
                        autor,
                        editora,
                        preco,
                        frete,
                        estoque,
                        tamanho
                )
        );

        Assertions.assertEquals("O livro deve conter nome, autor(es), editora e preço para ser cadastrado com sucesso.", obj.getMessage());
    }

//    @Test
//    @DisplayName("Validar se objeto InvalidoException é arremessado quando vendas atinge o limite.")
//    void validarSeObjetoInvalidoExceptionEArremessadoQuandoVendasAtingeLimite(){
//        LivrariaVirtual lv = new LivrariaVirtual();
//        lv.setMax_vendas(0);
//
//        ObjetoInvalidoException obj = assertThrows(
//                ObjetoInvalidoException.class, () -> lv.cadastrarLivro(
//                        1,
//                        "Joãozinho",
//                        "João Autor",
//                        "João Editora",
//                        37.99,
//                        23.50,
//                        5,
//                        null
//                )
//        );
//
//        Assertions.assertEquals("Já atingiu o número de impressos máximo.", obj.getMessage());
//    }
}