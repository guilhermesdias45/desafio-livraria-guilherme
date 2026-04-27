package vc.com.programar.entidade;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String autores;
    private String editora;
    private double preco;

    public Livro() {
    }

    public Livro(String titulo, String autores, String editora, double preco) {
        this.titulo = titulo;
        this.autores = autores;
        this.editora = editora;
        this.preco = preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("| %03d | %-20s | %-20s | %-20s | R$: %07.2f |",
                this.id,
                this.titulo,
                this.autores,
                this.editora,
                this.preco);
    }
}
