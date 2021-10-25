
package com.rentcloud.cloud.app.repositories;

import com.rentcloud.cloud.app.entities.Category;
import com.rentcloud.cloud.app.respositories.crud.CategoryCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Julian Bustos
 */
@Repository
public class CategoryRepository {
    @Autowired
    private CategoryCrudRepository repository;
    
    /**
     * SELECT * FROM
     * @return List<Category> Retorna todos los registros de la tabla 
     */
    public List<Category> getAll(){
        return (List<Category>) repository.findAll();
    }
    
    /**
     * SELECT * FROM TABLE WERE ID=id
     * @param id
     * @return Optional<Category> Retorna un resgistro por ID
     */
    public Optional<Category> getCategory(int id){
        return repository.findById(id);
    }
    
    /**
     * INSERT & UPDATE
     * @param category
     * @return  Category Actualiza un registro ya existente o crea un nuevo registro
     */
    public Category save(Category category){
        return repository.save(category);
    }
    
    /**
     * DELETE FORM TABLE 
     * @param category 
     */
    public void delete(Category category){
        repository.delete(category);
    }
}
