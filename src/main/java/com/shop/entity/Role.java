package com.shop.entity;

import com.shop.content.anotation.Column;
import com.shop.content.anotation.Entity;
import com.shop.content.anotation.Id;

@Entity
public class Role {
    @Id
    private Long id;

    @Column(value = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
