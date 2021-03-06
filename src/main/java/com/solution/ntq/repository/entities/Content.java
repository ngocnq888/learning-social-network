package com.solution.ntq.repository.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
/**
 * @author Duc Anh
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @JsonProperty(value = "class")
    @ManyToOne
    @JoinColumn(name = "clazz_id")
    Clazz clazz;
    Date startDate;
    Date endDate;
    String authorId;
    String content;
    String title;
    boolean isApprove;
    boolean isDone;
    String level;
    String thumbnail;
    Date timePost;
    String avatar;

    @JsonIgnore
    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    List<Attendance> attendances;
}
