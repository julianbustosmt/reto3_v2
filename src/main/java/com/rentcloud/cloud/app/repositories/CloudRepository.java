
package com.rentcloud.cloud.app.repositories;

import com.rentcloud.cloud.app.entities.Cloud;
import com.rentcloud.cloud.app.respositories.crud.CloudCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Julian Bustos
 */
@Repository
public class CloudRepository {
    @Autowired
    private CloudCrudRepository repository;
    
    /**
     * SELECT * FROM
     * @return List<Cloud> Retorna todos los registros de la tabla 
     */
    public List<Cloud> getAll(){
        return (List<Cloud>) repository.findAll();
    }
    
    /**
     * SELECT * FROM TABLE WERE ID=id
     * @param id
     * @return Optional<Cloud> Retorna un resgistro por ID
     */
    public Optional<Cloud> getCloud(int id){
        return repository.findById(id);
    }
    
    /**
     * INSERT & UPDATE
     * @param cloud
     * @return  Cloud Actualiza un registro ya existente o crea un nuevo registro
     */
    public Cloud save(Cloud cloud){
        return repository.save(cloud);
    }
    
    /**
     * DELETE FORM TABLE 
     * @param cloud 
     */
    public void delete(Cloud cloud){
        repository.delete(cloud);
    }
}
