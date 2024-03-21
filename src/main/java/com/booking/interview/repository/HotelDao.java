package com.booking.interview.repository;

import com.booking.interview.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelDao extends JpaRepository<Hotel, Integer> {

    List<Hotel> findAllByActive(Boolean isActive);

    @Modifying
    @Query("UPDATE Hotel h SET h.active = :status WHERE h.id = :id")
    void updateHotelStatus(@Param("status") Boolean status, @Param("id") Integer id);
}
