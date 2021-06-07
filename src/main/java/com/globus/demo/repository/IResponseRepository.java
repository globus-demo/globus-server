package com.globus.demo.repository;

import com.globus.demo.model.entites.ResponseToFriends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IResponseRepository extends JpaRepository<ResponseToFriends, Long> {
    ResponseToFriends findByEmailUserFromAndEmailUserTo(String EmailUserFrom, String EmailUserTo);
    List<ResponseToFriends> findAllByEmailUserFrom(String EmailUserFrom);
    List<ResponseToFriends> findAllByEmailUserTo(String EmailUserTo);

}
