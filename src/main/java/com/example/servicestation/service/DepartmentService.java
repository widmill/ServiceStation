package com.example.servicestation.service;


import com.example.servicestation.entity.Department;
import com.example.servicestation.entity.RepairedAuto;
import com.example.servicestation.exception.DepartmentDoesNotExist;
import com.example.servicestation.repository.DepartmentRepo;
import com.example.servicestation.repository.RepairedAutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("departmentService")
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    RepairedAutoRepo repairedAutoRepo;

    public Department getDepartment(Long id) throws DepartmentDoesNotExist {
        if (id == 0) {
            throw new NullPointerException();
        }
        if (!(id == 1 || id == 2)) {
            throw new DepartmentDoesNotExist("Отдел с таким id не существует");
        }

        return departmentRepo.findById(id).get();
    }

    public List<Department> getAllDepartments() {
        return (List<Department>) departmentRepo.findAll();
    }

    public Map<Department, List<RepairedAuto>> getDepartmentWithRepairedAutos(Long id) throws DepartmentDoesNotExist {
        Department department = getDepartment(id);
        Map<Department, List<RepairedAuto>> departmentListMap = new HashMap<>();
        departmentListMap.put(department, sortByDepartments(id));
        return departmentListMap;

    }
    public List<RepairedAuto> sortByDepartments(Long id) {
        List<RepairedAuto> repairedAutos = (ArrayList<RepairedAuto>) repairedAutoRepo.findAll();
        List<RepairedAuto> sortedRepairedAutos = new ArrayList<>();
        for (RepairedAuto repairedAuto : repairedAutos) {
            if (repairedAuto.getDepartment_id() == id) {
                sortedRepairedAutos.add(repairedAuto);
            }
        }

        return sortedRepairedAutos;
    }

}
