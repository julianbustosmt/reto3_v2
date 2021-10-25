
package com.rentcloud.cloud.app.repositories;

import com.rentcloud.cloud.app.entities.Score;
import com.rentcloud.cloud.app.respositories.crud.ScoreCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Julian Bustos
 */
@Repository
public class ScoreRepository {
    @Autowired
    private ScoreCrudRepository repository;
    
    /**
     * SELECT * FROM
     * @return List<Score> Retorna todos los registros de la tabla 
     */
    public List<Score> getAll(){
        return (List<Score>) repository.findAll();
    }
    
    /**
     * SELECT * FROM TABLE WERE ID=id
     * @param id
     * @return Optional<Score> Retorna un resgistro por ID
     */
    public Optional<Score> getScore(int id){
        return repository.findById(id);
    }
    
    /**
     * INSERT & UPDATE
     * @param score
     * @return  Score Actualiza un registro ya existente o crea un nuevo registro
     */
    public Score save(Score score){
        return repository.save(score);
    }
    
    /**
     * DELETE FORM TABLE 
     * @param score 
     */
    public void delete(Score score){
        repository.delete(score);
    }
}
