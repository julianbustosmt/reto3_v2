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
 * Reservation representa el alquiler de la nube en un periodo de tiempo
 * determinado
 *
 * @author Julian Bustos
 */
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    /**
     * GET
     *
     * @return
     */
    public List<Reservation> getall() {
        return repository.getAll();
    }

    /**
     * GET(id)
     *
     * @param reservationId
     * @return
     */
    public Optional<Reservation> getReservation(int reservationId) {
        return repository.getReservation(reservationId);
    }

    /**
     * POST
     *
     * @param reservation
     * @return
     */
    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return repository.save(reservation);
        } else {
            Optional<Reservation> existReservation = repository.getReservation(reservation.getIdReservation());
            if (existReservation.isPresent()) {
                return reservation;
            } else {
                return repository.save(reservation);
            }
        }
    }

    /**
     * PUT (UPDATE)
     *
     * @param reservation
     * @return
     */
    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> existReservation = repository.getReservation(reservation.getIdReservation());
            if (existReservation.isPresent()) {
                if (reservation.getStartDate() != null) {
                    existReservation.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    existReservation.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getCloud() != null) {
                    existReservation.get().setCloud(reservation.getCloud());
                }
                if (reservation.getClient() != null) {
                    existReservation.get().setClient(reservation.getClient());
                }
                if (reservation.getStatus() != null) {
                    existReservation.get().setStatus(reservation.getStatus());
                }
                return repository.save(existReservation.get());
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }

    /**
     * DELETE
     *
     * @param reservationId
     * @return
     */
    public boolean delete(int reservationId) {
        Boolean respuesta = getReservation(reservationId).map(reservation -> {
            repository.delete(reservation);
            return true;
        }).orElse(false);
        return respuesta;
    }

    /**
     * Metodo que retorna una lista de objetos tipo Client ordenados de forma
     * desendente con respecto a numero de reservaciones asociadas a dicho
     * objeto
     *
     * @return
     */
    public List<CountClient> getTopClients() {
        return repository.getTopClients();
    }

    /**
     * Metodo que contabiliza el numero de reservaciones segun si su estatus es
     * cancelled o completed
     *
     * @return
     */
    public StatusAmount getStatusReport() {
        List<Reservation> completed = repository.getReservationsByStatus("completed");
        List<Reservation> cancelled = repository.getReservationsByStatus("cancelled");

        StatusAmount stAmt = new StatusAmount(completed.size(), cancelled.size());
        return stAmt;
    }

    /**
     * Metodo que nos permite saber cuántas reservas se han hecho en un
     * intervalo de tiempo
     *
     * @param d1
     * @param d2
     * @return
     */
    public List<Reservation> getPapeletiaPeriod(String d1, String d2) {

        SimpleDateFormat parser = new SimpleDateFormat("yyy-MM-dd");
        Date dateOne = new Date();
        Date dateTwo = new Date();

        try {
            dateOne = parser.parse(d1);
            dateTwo = parser.parse(d2);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dateOne.before(dateTwo)) {
            return repository.getReservationByDate(dateOne, dateTwo);
        } else {
            return new ArrayList<>();
        }

    }
}
