package service;

import repository.Conexao;
import entity.ClienteEntity;

import java.util.List;

public class ClienteService {

    public void criarCliente() throws Exception {

        ClienteEntity cliente = new ClienteEntity("Mariane", "123.485.452.52");
        insert(cliente);
    }

    public void insert(ClienteEntity pCliente) throws Exception {

        try {
            Conexao.begin();

            Conexao.insert(pCliente);

            Conexao.commit();

        } catch (Exception ex) {
            Conexao.rollback();
            throw new Exception(ex);
        }
    }

    public List<ClienteEntity> consult() throws Exception {

        return Conexao.consult(ClienteEntity.class, "SELECT c FROM ClienteEntity AS c").getResultList();
    }
}
