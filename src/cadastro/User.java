package cadastro;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String nome;
    private String email;
    private String idade;
    private String altura;

    @Override
    public String toString() {
        return this.nome + "\n" + this.email + "\n" + this.idade + "\n" + this.altura;
    }

    public String getNome() {
        return nome;
    }

    public String setNome(String nome) {
        this.nome = nome;
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }

    public String getIdade() {
        return idade;
    }

    public String setIdade(String idade) {
        this.idade = idade;
        return idade;
    }

    public String getAltura() {
        return altura;
    }

    public String setAltura(String altura) {
        this.altura = altura;
        return altura;
    }
}
