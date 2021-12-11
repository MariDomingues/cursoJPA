package entity;

import classe.DadosPessoais;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "cliente")
public class ClienteEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded //separa as colunas sem criar uma nova tabela
    private DadosPessoais dadosPessoais;

    public ClienteEntity() {
    }

    public ClienteEntity(String nome, String cpf) {
        this.dadosPessoais = new DadosPessoais(nome, cpf);
    }

    public int getId() {
        return id;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public String getNome() {
        return this.dadosPessoais.getNome();
    }

    public String getCpf() {
        return this.dadosPessoais.getCpf();
    }
}
