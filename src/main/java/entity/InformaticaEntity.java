package entity;

import entity.ProdutoEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "informatica")
public class InformaticaEntity extends ProdutoEntity {

    private String marca;
    private int modelo;

    public InformaticaEntity() {
    }

    public InformaticaEntity(String marca, int modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public int getModelo() {
        return modelo;
    }
}
