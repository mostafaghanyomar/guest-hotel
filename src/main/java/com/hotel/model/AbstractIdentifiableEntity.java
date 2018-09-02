package com.hotel.model;

import javax.persistence.*;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@MappedSuperclass
public abstract class AbstractIdentifiableEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

}
