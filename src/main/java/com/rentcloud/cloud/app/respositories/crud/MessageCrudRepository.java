
package com.rentcloud.cloud.app.respositories.crud;

import com.rentcloud.cloud.app.entities.Message;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Julian Bustos
 */
public interface MessageCrudRepository extends CrudRepository<Message,Integer> {
    
}
