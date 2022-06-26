package com.example.demo.repository;

import com.example.demo.model.Spid;
import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpidRepo extends CrudRepository<Spid, Long> {
    Optional<Spid> findSpidByUserId(User users);

}