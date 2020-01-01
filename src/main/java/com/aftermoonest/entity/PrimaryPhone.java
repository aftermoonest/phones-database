package com.aftermoonest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "BD_TYPE")
@Table(name = "Phones")
@MappedSuperclass
public abstract class PrimaryPhone implements Serializable {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(Date dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column
    Date dateOfProduction;

    @Column
    String manufacturer;

    @Column
    String model;

    @Column
    Color color;

    PrimaryPhone(Date dateOfProduction, String manufacturer, String model, Color color) {
        this.dateOfProduction = dateOfProduction;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
    }

    PrimaryPhone() {
    }

    public int compareById(PrimaryPhone o1, PrimaryPhone o2) {
        return Integer.compare(o1.id, o2.id);
    }

    public int compareByDateOfProduction(PrimaryPhone o1, PrimaryPhone o2) {
        return o1.dateOfProduction.compareTo(o2.dateOfProduction);
    }

    public int compareByManufacturer(PrimaryPhone o1, PrimaryPhone o2) {
        return o1.getManufacturer().compareTo(o2.getManufacturer());
    }

    public int compareByModel(PrimaryPhone o1, PrimaryPhone o2) {
        return o1.getModel().compareTo(o2.getModel());
    }
}
