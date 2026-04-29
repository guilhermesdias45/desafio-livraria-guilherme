package vc.com.programar.entidade.data;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.ParameterDeclarations;

import java.util.stream.Stream;

public class LivroNuloTest implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ParameterDeclarations parameters, ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(
                        3,
                        "",
                        "Dalmatan",
                        "101 Dal Editora",
                        47.50,
                        20.00,
                        5,
                        120),
                Arguments.of(
                        3,
                        null,
                        "Dalmatan",
                        "101 Dal Editora",
                        47.50,
                        20.00,
                        5,
                        120),
                Arguments.of(
                        3,
                        "101 Dalmatas",
                        "     ",
                        "101 Dal Editora",
                        47.50,
                        20.00,
                        5,
                        120),
                Arguments.of(
                        3,
                        "101 Dalmatas",
                        null,
                        "101 Dal Editora",
                        47.50,
                        20.00,
                        5,
                        120),
                Arguments.of(
                        3,
                        "101 Dalmatas",
                        "Dalmatan",
                        " ",
                        47.50,
                        20.00,
                        5,
                        120),
                Arguments.of(
                        3,
                        "101 Dalmatas",
                        "Dalmatan",
                        null,
                        47.50,
                        20.00,
                        5,
                        120),

                Arguments.of(
                        3,
                        "101 Dalmatas",
                        "Dalmatan",
                        "101 Dal Editora",
                        null,
                        20.00,
                        5,
                        120),

                Arguments.of(
                        3,
                        "101 Dalmatas",
                        "Dalmatan",
                        "101 Dal Editora",
                        -47.50,
                        20.00,
                        5,
                        120)
        );
    }
}
