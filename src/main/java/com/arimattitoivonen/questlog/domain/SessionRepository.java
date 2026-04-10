package com.arimattitoivonen.questlog.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session, Long> {
    List<Session> findByUser(AppUser user);
}
