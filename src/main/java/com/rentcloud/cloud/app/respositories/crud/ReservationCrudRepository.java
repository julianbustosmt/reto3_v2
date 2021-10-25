package com.rentcloud.cloud.app.respositories.crud;

import com.rentcloud.cloud.app.entities.Reservation;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Julian Bustos
 */
public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer> {
    
}
