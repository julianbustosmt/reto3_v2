
package com.rentcloud.cloud.app.respositories.crud;

import com.rentcloud.cloud.app.entities.Score;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Julian Bustos
 */
public interface ScoreCrudRepository extends CrudRepository<Score,Integer> {
    
}
