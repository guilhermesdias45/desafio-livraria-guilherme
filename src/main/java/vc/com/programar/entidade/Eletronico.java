package vc.com.programar.entidade;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ELETRONICO")
public class Eletronico extends Livro{
    private int tamanho;

    public Eletronico() {
    }

    public Eletronico(String titulo, String autores, String editora, double preco, int tamanho) {
        super(titulo, autores, editora, preco);
        this.tamanho = tamanho;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return String.format("%s %7dKB |",
                super.toString(),
                this.tamanho);
    }
}
