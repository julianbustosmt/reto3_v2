
package com.rentcloud.cloud.app.services;

import com.rentcloud.cloud.app.entities.Client;
import com.rentcloud.cloud.app.repositories.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Julian Bustos
 */

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repository;
    
    /**
     * GET
     * @return 
     */
    public List<Client> getall(){
        return repository.getAll();
    }
    
    /**
     * GET(id)
     * @param clientId
     * @return 
     */
    public Optional<Client> getClient(int clientId){
        return repository.getClient(clientId);
    }
    
    /**
     *POST
     * @param client
     * @return 
     */
    public Client save(Client client){
        if(client.getIdClient()==null){
            return repository.save(client);
        }else{
            Optional<Client> existClient = repository.getClient(client.getIdClient());
            if(existClient.isPresent()){
                return client;
            }else{
                return repository.save(client);
            }
        }
    }
    
    /**
     * PUT (UPDATE)
     * @param client
     * @return 
     */
    public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client> existClient = repository.getClient(client.getIdClient());
            if(existClient.isPresent()){
                if(client.getName()!=null){
                    existClient.get().setName(client.getName());
                }
                if(client.getEmail()!=null){
                    existClient.get().setEmail(client.getEmail());
                }
                if(client.getPassword()!=null){
                    existClient.get().setPassword(client.getPassword());
                }
                if(client.getAge()!=null){
                    existClient.get().setAge(client.getAge());
                }
                if(client.getMessages()!=null){
                    existClient.get().setMessages(client.getMessages());
                }
                if(client.getReservations()!=null){
                    existClient.get().setReservations(client.getReservations());
                }
                
                return repository.save(existClient.get());
            }else {
                return client;
            }
        }else{
            return client;
        }
    }
    
    /**
     * DELETE
     * @param clientId
     * @return 
     */
    public boolean delete(int clientId){
        Boolean respuesta = getClient(clientId).map(client->{
            repository.delete(client);
            return true;
        }).orElse(false);
        return respuesta;
    }
    
}
