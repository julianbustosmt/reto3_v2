
package com.rentcloud.cloud.app.repositories;

import com.rentcloud.cloud.app.entities.Client;
import com.rentcloud.cloud.app.respositories.crud.ClientCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Julian Bustos
 */
@Repository
public class ClientRepository {
    @Autowired
    private ClientCrudRepository repository;
    
/**
     * SELECT * FROM
     * @return List<Client> Retorna todos los registros de la tabla 
     */
    public List<Client> getAll(){
        return (List<Client>) repository.findAll();
    }
    
    /**
     * SELECT * FROM TABLE WERE ID=id
     * @param id
     * @return Optional<Client> Retorna un resgistro por ID
     */
    public Optional<Client> getClient(int id){
        return repository.findById(id);
    }
    
    /**
     * INSERT & UPDATE
     * @param client
     * @return  Client Actualiza un registro ya existente o crea un nuevo registro
     */
    public Client save(Client client){
        return repository.save(client);
    }
    
    /**
     * DELETE FORM TABLE 
     * @param client 
     */
    public void delete(Client client){
        repository.delete(client);
    }
}
