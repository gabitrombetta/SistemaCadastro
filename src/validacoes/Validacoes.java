package validacoes;

import exceptions.*;

import java.util.regex.Pattern;

public class Validacoes {
    public static void validarNome(String nome) throws NomeInvalidoException {
        if (nome.length() < 10) {
            throw new NomeInvalidoException("O nome deve ter no mínimo 10 caracteres.");
        }
    }

    public static void validarEmail(String email) throws EmailInvalidoException {
        if (!email.contains("@")) {
            throw new EmailInvalidoException("Email deve conter caractere \"@\"");
        }
    }

    public static void validarIdade(String idade) throws IdadelInvalidaException {
        if (Integer.parseInt(idade) < 18) {
            throw new IdadelInvalidaException("Usuário deve ter 18 anos ou mais.");
        }
    }

    public static void validarAltura(String altura) throws AlturalInvalidaException {
        Pattern alturaPattern = Pattern.compile("^\\d+,\\d+$");
        if (!altura.contains(",") || !alturaPattern.matcher(altura).matches()) {
            throw new AlturalInvalidaException("Altura deve ser um número válido com vírgula.");
        }

    }
}
