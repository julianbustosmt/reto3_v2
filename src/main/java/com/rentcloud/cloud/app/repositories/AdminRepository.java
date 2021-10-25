package com.rentcloud.cloud.app.repositories;

import com.rentcloud.cloud.app.entities.Admin;
import com.rentcloud.cloud.app.respositories.crud.AdminCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Julian Bustos
 */
@Repository
public class AdminRepository {
    
    @Autowired
    private AdminCrudRepository repository;
    
    /**
     * SELECT * FROM
     * @return List<Admin> Retorna todos los registros de la tabla 
     */
    public List<Admin> getAll(){
        return (List<Admin>) repository.findAll();
    }
    
    /**
     * SELECT * FROM TABLE WERE ID=id
     * @param id
     * @return Optional<Admin> Retorna un resgistro por ID
     */
    public Optional<Admin> getAdmin(int id){
        return repository.findById(id);
    }
    
    /**
     * INSERT & UPDATE
     * @param admin
     * @return  Admin Actualiza un registro ya existente o crea un nuevo registro
     */
    public Admin save(Admin admin){
        return repository.save(admin);
    }
    
    /**
     * DELETE FORM TABLE 
     * @param admin 
     */
    public void delete(Admin admin){
        repository.delete(admin);
    }
}
