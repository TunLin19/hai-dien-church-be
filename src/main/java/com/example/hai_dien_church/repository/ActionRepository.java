package com.example.hai_dien_church.repository;

import com.example.hai_dien_church.entity.Action;
import com.example.hai_dien_church.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action,String> {

    @Query("SELECT a FROM Action a WHERE a.event.id = :eventId")
    List<Action> findByEventId(@Param("eventId") String eventId);


}
