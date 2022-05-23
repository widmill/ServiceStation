package com.example.servicestation.entity;

import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "repaired_auto")
public class RepairedAuto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "date_of_repair")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateOfRepair;

    @Column(name = "owner_surname")
    private String ownerSurname;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "owner_phone_number")
    private String ownerPhoneNumber;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "price")
    private Integer price;

    @Column(name = "comment")
    private String comment;

//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;

    @Column(name = "department_id")
    private Long department_id;


    public RepairedAuto() {

}

    public RepairedAuto(String model, Date dateOfRepair, String ownerSurname, String ownerName, String ownerPhoneNumber, String plateNumber, Integer price, String comment, Long department_id) {
        this.model = model;
        this.dateOfRepair = dateOfRepair;
        this.ownerSurname = ownerSurname;
        this.ownerName = ownerName;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.plateNumber = plateNumber;
        this.price = price;
        this.comment = comment;
        this.department_id = department_id;
//        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getDateOfRepair() {
        return dateOfRepair;
    }

    public void setDateOfRepair(Date dateOfRepair) {
        this.dateOfRepair = dateOfRepair;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }
    //    public Department getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }
}
