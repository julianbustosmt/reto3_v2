package com.rentcloud.cloud.app.repositories;

import com.rentcloud.cloud.app.entities.Client;
import com.rentcloud.cloud.app.entities.Reservation;
import com.rentcloud.cloud.app.entities.custom.CountClient;
import com.rentcloud.cloud.app.respositories.crud.ReservationCrudRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Julian Bustos
 */
@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository repository;

    /**
     * SELECT * FROM
     *
     * @return List<Reservation> Retorna todos los registros de la tabla
     */
    public List<Reservation> getAll() {
        return (List<Reservation>) repository.findAll();
    }

    /**
     * SELECT * FROM TABLE WERE ID=id
     *
     * @param id
     * @return Optional<Reservation> Retorna un resgistro por ID
     */
    public Optional<Reservation> getReservation(int id) {
        return repository.findById(id);
    }

    /**
     * INSERT & UPDATE
     *
     * @param reservation
     * @return Reservation Actualiza un registro ya existente o crea un nuevo
     * registro
     */
    public Reservation save(Reservation reservation) {
        return repository.save(reservation);
    }

    /**
     * DELETE FORM TABLE
     *
     * @param reservation
     */
    public void delete(Reservation reservation) {
        repository.delete(reservation);
    }

    public List<Reservation> getReservationsByStatus(String status) {
        return repository.findAllByStatus(status);
    }

    public List<Reservation> getReservationByDate(Date dateOne, Date dateTwo) {
        return repository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }

    public List<CountClient> getTopClients(){
        List<CountClient> res= new ArrayList<>();
        
        List<Object[]> report =  repository.countTotalReservationByClient();
        for(int i=0; i<report.size(); i++){
            res.add(new CountClient((Long) report.get(i)[1],(Client) report.get(i)[0]));
        }
        
        
        return res;
    }

}
