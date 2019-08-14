package com.solution.ntq.service.impl;

import com.solution.ntq.common.exception.InvalidRequestException;
import com.solution.ntq.common.utils.ConvertObject;
import com.solution.ntq.common.validator.Validator;

import com.solution.ntq.controller.response.EventResponse;
import com.solution.ntq.repository.base.EventRepository;
import com.solution.ntq.repository.entities.Event;
import com.solution.ntq.controller.request.JoinEventRequest;
import com.solution.ntq.repository.base.JoinEventRepository;
import com.solution.ntq.repository.entities.JoinEvent;
import com.solution.ntq.repository.entities.User;
import com.solution.ntq.service.base.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Ngoc Ngo Quy
 * @since  at 7/08/2019
 * @version 1.01
 */

@AllArgsConstructor
@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;


    @Override
    public List<EventResponse> getGroupEvent(int classId, long startDate, long endDate) {
        if (Validator.isScopeOutOfOneMonth(startDate, endDate)) {
            throw new InvalidRequestException("Invalid data request");
        }
        java.util.Date date = new java.util.Date();
        endDate = startDate == 0 ? date.getTime() : endDate;
        List<Event> groupEvent = eventRepository.getEventByClazzIdAndStartDate(classId, new Date(startDate), new Date(endDate));
        return convertEventToEventResponse(groupEvent);
    }

    private List<EventResponse> convertEventToEventResponse(List<Event> events) {
        List<EventResponse> eventResponses = new ArrayList<>();
        events.forEach(event -> eventResponses.add(eventMapper(event)));
        return eventResponses;
    }

    private EventResponse eventMapper(Event event) {
        return ConvertObject.mapper().convertValue(event, EventResponse.class);
    }
    private JoinEventRepository joinEventRepository;
    @Transactional
    @Override
    public void saveJoinForUser(JoinEventRequest joinEventRequest) {
        try {
            JoinEvent joinEvent = joinEventMapper(joinEventRequest);
            User user = new User();
            user.setId(joinEventRequest.getUserId());
            Event event = new Event();
            event.setId(joinEventRequest.getEventId());
            joinEvent.setUser(user);
            joinEvent.setEvent(event);
            save(joinEvent);
        } catch (InvalidRequestException ex) {
            throw new InvalidRequestException(ex.getMessage());
        }
    }

    private void save(JoinEvent joinEvent) {
        joinEventRepository.save(joinEvent);
    }

    private JoinEvent joinEventMapper(JoinEventRequest joinEventRequest) {
        return ConvertObject.mapper().convertValue(joinEventRequest, JoinEvent.class);
    }
}
