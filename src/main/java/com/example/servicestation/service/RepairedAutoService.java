package com.example.servicestation.service;

import com.example.servicestation.entity.RepairedAuto;
import com.example.servicestation.exception.AutoNotFoundException;
import com.example.servicestation.exception.DepartmentDoesNotExist;
import com.example.servicestation.exception.NonValidPhoneNumberException;
import com.example.servicestation.exception.NonValidPlateNumberException;
import com.example.servicestation.repository.RepairedAutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("repairedAutoService")
@Transactional
public class RepairedAutoService {
    @Autowired
    RepairedAutoRepo repairedAutoRepo;

    public RepairedAuto getRepairedAuto(Long id) throws AutoNotFoundException {
        if (id == 0) {
            throw new NullPointerException();
        }
        if (repairedAutoRepo.findById(id).isEmpty()) {
            throw new AutoNotFoundException();
        }

        return repairedAutoRepo.findById(id).get();
    }

    public List<RepairedAuto> getAllAutos() {
        return (List<RepairedAuto>) repairedAutoRepo.findAll();
    }

    public RepairedAuto addAuto(RepairedAuto repairedAuto) throws NonValidPhoneNumberException, NonValidPlateNumberException, DepartmentDoesNotExist{
        if (!isPhoneNumberValid(repairedAuto)) {
            throw new NonValidPhoneNumberException("Телефон должен быть формата +7**********");
        }

        if (!isPlateNumberValid(repairedAuto)) {
            throw new NonValidPlateNumberException("Номер машины должен быть формата А000АА00");
        }

        if (!ifDepartmentExists(repairedAuto)) {
            throw new DepartmentDoesNotExist("Номер отдела должен быть 1 или 2");
        }

        return repairedAutoRepo.save(repairedAuto);
    }

    private boolean isPhoneNumberValid(RepairedAuto repairedAuto) {
        String phoneNumber = repairedAuto.getOwnerPhoneNumber();
        Pattern pattern = Pattern.compile("\\+[7]\\d{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();

    }

    private boolean isPlateNumberValid(RepairedAuto repairedAuto) {
        String plateNumber = repairedAuto.getPlateNumber();
        Pattern pattern = Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$");
        Matcher matcher = pattern.matcher(plateNumber);
        return matcher.matches();
    }

    private boolean ifDepartmentExists(RepairedAuto repairedAuto) {
        return repairedAuto.getDepartment_id() == 1 || repairedAuto.getDepartment_id() == 2;
    }

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


//    private void parseDate(RepairedAuto repairedAuto) throws ParseException {
//        final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
//        final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
//
//        Date date1 = repairedAuto.getDateOfRepair();
//        String date2 = String.valueOf(date1);
//
//        Date date3 = DATE_FORMAT.parse(String.valueOf(date1));
//        repairedAuto.setDateOfRepair(date3);
//
//    }
}