package com.example.servicestation.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long department_id;

    @Column(name = "owner")
    private String owner;

    @OneToMany
    @JoinColumn(name = "department_id")
    private List<RepairedAuto> repairedAuto;


    public Department() {}

    public Department(String owner) {
        this.owner = owner;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Отдел " + department_id + ", Владелец " + owner;
    }
}




