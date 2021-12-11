package entity;

import entity.ProdutoEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "livro")
public class LivroEntity extends ProdutoEntity {

    private String autor;
    private int numeroPagina;

    public LivroEntity() {
    }

    public LivroEntity(String autor, int numeroPagina) {
        this.autor = autor;
        this.numeroPagina = numeroPagina;
    }

    public String getAutor() {
        return autor;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }
}
