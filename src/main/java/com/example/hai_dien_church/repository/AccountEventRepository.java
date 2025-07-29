package com.example.hai_dien_church.repository;

import com.example.hai_dien_church.entity.AccountEvent;
import com.example.hai_dien_church.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountEventRepository extends JpaRepository<AccountEvent,String> {

    List<AccountEvent> findByEvent(Event event);

}
