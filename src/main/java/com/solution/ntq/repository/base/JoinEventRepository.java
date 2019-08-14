package com.solution.ntq.repository.base;

import com.solution.ntq.repository.entities.JoinEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
/**
 * @author Ngoc Ngo Quy
 * @since  at 8/08/2019
 * @version 1.01
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author Ngoc Ngo Quy
 * @version 1.01
 * @since at 8/08/2019
 */
@org.springframework.stereotype.Repository
public interface JoinEventRepository extends Repository<JoinEvent, Integer> {
    void save(JoinEvent joinEvent);

    @Query(value = "SELECT * FROM join_event e WHERE e.event_id = ?1 and e.is_joined = true", nativeQuery = true)
    List<JoinEvent> getJoinEventsByEventIdAndJoinedTrue(int eventId);

    JoinEvent getJoinEventsById(int id);

    void save(JoinEvent joinEvent);
}