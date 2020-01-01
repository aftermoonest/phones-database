package com.aftermoonest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

@Entity
@DiscriminatorValue("A")
public class SmartPhone extends PrimaryPhone {

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    @Column
    private Integer memory;

    public SmartPhone() {

    }

    public SmartPhone(Date dateOfProduction, String manufacturer, String model, Color color, Integer memory) {
        super(dateOfProduction, manufacturer, model, color);
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "SmartPhone{" +
                "id=" + id +
                ", dateOfProduction=" + dateOfProduction +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", color=" + color +
                ", memory=" + memory +
                '}';
    }

}
