
package com.rentcloud.cloud.app.services;

import com.rentcloud.cloud.app.entities.Reservation;
import com.rentcloud.cloud.app.entities.custom.CountClient;
import com.rentcloud.cloud.app.entities.custom.StatusAmount;
import com.rentcloud.cloud.app.repositories.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Julian Bustos
 */

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repository;
    
    /**
     * GET
     * @return 
     */
    public List<Reservation> getall(){
        return repository.getAll();
    }
    
    /**
     * GET(id)
     * @param reservationId
     * @return 
     */
    public Optional<Reservation> getReservation(int reservationId){
        return repository.getReservation(reservationId);
    }
    
    /**
     *POST
     * @param reservation
     * @return 
     */
    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation()==null){
            return repository.save(reservation);
        }else{
            Optional<Reservation> existReservation = repository.getReservation(reservation.getIdReservation());
            if(existReservation.isPresent()){
                return reservation;
            }else{
                return repository.save(reservation);
            }
        }
    }
    
    /**
     * PUT (UPDATE)
     * @param reservation
     * @return 
     */
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> existReservation = repository.getReservation(reservation.getIdReservation());
            if(existReservation.isPresent()){
                if(reservation.getStartDate()!=null){
                    existReservation.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    existReservation.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    existReservation.get().setStatus(reservation.getStatus());
                }
                return repository.save(existReservation.get());
            }else {
                return reservation;
            }
        }else{
            return reservation;
        }
    }
    
    /**
     * DELETE
     * @param reservationId
     * @return 
     */
    public boolean delete(int reservationId){
        Boolean respuesta = getReservation(reservationId).map(reservation->{
            repository.delete(reservation);
            return true;
        }).orElse(false);
        return respuesta;
    }
        
    public List<CountClient> getTopClients(){
        return repository.getTopClients();
    }
    
    public StatusAmount getStatusReport(){
        List<Reservation> completed = repository.getReservationsByStatus("completed");
        List<Reservation> cancelled = repository.getReservationsByStatus("cancelled");
        
        StatusAmount stAmt = new StatusAmount(completed.size(),cancelled.size());
        return stAmt;
    }
    
    public List<Reservation> getPapeletiaPeriod(String d1, String d2){
        
        SimpleDateFormat parser = new SimpleDateFormat("yyy-MM-dd");
        Date dateOne = new Date();
        Date dateTwo = new Date();
        
        try{
            dateOne = parser.parse(d1);
            dateTwo = parser.parse(d2);
            
        }catch(ParseException e){
            e.printStackTrace();
        }
        
        if(dateOne.before(dateTwo)){
            return repository.getReservationByDate(dateOne, dateTwo);
        }else{
            return new ArrayList<>();
        }
        
        
    }
}
