package com.solution.ntq.repository.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/**
 * @author Ngoc Ngo Quy
 * created at 7/08/2019
 * @version 1.01
 */

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JoinEvent {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;
    @JsonProperty("isJoined")
    boolean isJoined;
    @JsonProperty(value = "isAttendance")
    boolean isAttendance;
    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    boolean confirm;
    String note;
}
