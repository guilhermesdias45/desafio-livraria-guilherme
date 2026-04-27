package vc.com.programar.entidade;

import jakarta.persistence.Entity;

@Entity
public class Impresso extends Livro{
    private double frete;
    private int estoque;

    public Impresso() {
    }

    public Impresso(String titulo, String autores, String editora, double preco, double frete, int estoque) {
        super(titulo, autores, editora, preco);
        this.frete = frete;
        this.estoque = estoque;
    }

    public void atualizarEstoque(){
        this.estoque--;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return String.format("%s R$: %04.2f | %07d |",
                super.toString(),
                this.frete,
                this.estoque);
    }
}
