package com.aftermoonest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

@Entity
@DiscriminatorValue("B")
public class Phone extends PrimaryPhone{

    public Integer getCountOfSim() {
        return countOfSim;
    }

    public void setCountOfSim(Integer countOfSim) {
        this.countOfSim = countOfSim;
    }

    @Column(name = "countOfSim")
    private Integer countOfSim;

    public Phone() {

    }

    public Phone(Date dateOfProduction, String manufacturer, String model, Color color, Integer countOfSim) {
        this.id = id;
        this.dateOfProduction = dateOfProduction;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.countOfSim = countOfSim;
    }

    public Phone(String manufacturer, String model, Color color) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", dateOfProduction=" + dateOfProduction +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", color=" + color +
                ", countOfSim=" + countOfSim +
                '}';
    }

}
