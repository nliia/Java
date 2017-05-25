package ru.kpfu.itis.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liia
 */
@Entity
@Table(name = "cottage")
public class Cottage {

//    @GeneratedValue(strategy = G)
    @Id
    private Long id;

    @Column(name = "place_amount")
    private Integer placeAmount;

    private Boolean parking;

    @Column(name = "kid_zone")
    private Boolean playground;

    private Boolean pavilion;

    @Column(name = "animals_permission")
    private Boolean animalsPermission;

    private Integer cost;

    @OneToMany(mappedBy = "cottage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    public Cottage(){}

    public Cottage(Long id, Integer placeAmount, Boolean parking, Boolean playground, Boolean pavilion, Boolean animalsPermission, Integer cost) {
        this.id = id;
        this.placeAmount = placeAmount;
        this.parking = parking;
        this.playground = playground;
        this.pavilion = pavilion;
        this.animalsPermission = animalsPermission;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlaceAmount() {
        return placeAmount;
    }

    public void setPlaceAmount(Integer placeAmount) {
        this.placeAmount = placeAmount;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean getPlayground() {
        return playground;
    }

    public void setPlayground(Boolean playground) {
        this.playground = playground;
    }

    public Boolean getPavilion() {
        return pavilion;
    }

    public void setPavilion(Boolean pavilion) {
        this.pavilion = pavilion;
    }

    public Boolean getAnimalsPermission() {
        return animalsPermission;
    }

    public void setAnimalsPermission(Boolean animalsPermission) {
        this.animalsPermission = animalsPermission;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
