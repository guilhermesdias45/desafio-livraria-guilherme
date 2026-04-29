package vc.com.programar.entidade.data;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.ParameterDeclarations;

import java.util.stream.Stream;

public class DataLivrosTest implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ParameterDeclarations parameters, ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(
                        1,
                        "Scooby Doo",
                        "Scooby",
                        "Scooby Editora",
                        35.99,
                        12.99,
                        9,
                        null
                ),
                Arguments.of(
                        2,
                        "Thomas e seus amigos",
                        "Thomas",
                        "Amigos Editora",
                        99.00,
                        null,
                        null,
                        55
                ),
                Arguments.of(
                        3,
                        "101 Dalmatas",
                        "Dalmatan",
                        "101 Dal Editora",
                        47.50,
                        20.00,
                        5,
                        120
                )
        );
    }
}