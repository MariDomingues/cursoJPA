package entity;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "categoria")
public class CategoriaEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;

    public CategoriaEntity() {
    }

    public CategoriaEntity(int id) {
        this.id = id;
    }

    public CategoriaEntity(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
