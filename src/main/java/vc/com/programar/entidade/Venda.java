package vc.com.programar.entidade;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Venda {
    private static int numVendas = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;
    private String cliente;
    private double valor;
    @OneToMany
    private List<Livro> livros;

    public Venda() {
    }

    public Venda(String cliente, double valor) {
        numVendas++;

        this.cliente = cliente;
        this.valor = valor;
        this.livros = new ArrayList<>();
    }

    public void listarLivros(){
        System.out.printf("| %12s | %12s | %12s | %12s |%n",
        "Livro",
        "Autores",
        "Editora",
        "Preço");

        for (Livro l : livros){
            System.out.println(l.toString());
        }

    }

    public void addLivro(Livro livro, int index){
        livros.add(livro);
    }

    public int getNumVendas() {
        return numVendas;
    }

    public void setNumVendas(int numVendas) {
        this.numVendas = numVendas;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
