package ru.kpfu.itis.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author Liia
 */
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @JoinColumn(name = "cottage_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Cottage cottage;

    @Column(name = "arrive_date")
    private Date arriveDate;

    @Column(name = "departure_date")
    private Date departureDate;

    public Booking(String name, String phoneNumber, Cottage cottage, Date arriveDate, Date departureDate) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.cottage = cottage;
        this.arriveDate = arriveDate;
        this.departureDate = departureDate;
    }

    public Booking() {

    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Cottage getCottage() {
        return cottage;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}
