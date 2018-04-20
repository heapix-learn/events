package com.heapix.events.persistence.model;

import lombok.Data;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author mgergalov
 */
@Entity
@Data
public class News {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Long userId;
    private String title;
    private String body;
    private Date createdDate;
    private Date lastModifiedDate;

}
