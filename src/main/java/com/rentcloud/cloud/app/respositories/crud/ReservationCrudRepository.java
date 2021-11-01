package com.rentcloud.cloud.app.respositories.crud;

import com.rentcloud.cloud.app.entities.Reservation;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Julian Bustos
 */
public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer> {
     
    //JPQL
    @Query("SELECT c.client, COUNT(c.client) FROM Reservation AS c group by c.client order by COUNT(c.client) desc")
    public List<Object[]> countTotalReservationByClient();
    
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne,Date dateTwo );
    
    public List<Reservation>findAllByStatus(String status);
    
}
