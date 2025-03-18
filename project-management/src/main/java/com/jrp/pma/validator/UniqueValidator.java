package com.jrp.pma.validator;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<IUniqueValue, String> {

    @Autowired
    IEmployeeRepository empRepo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      System.out.println("Entered Validation method ...");

       Employee emp = empRepo.findByEmail(value);

       if(emp != null)
           return false;
       else
        return true;
    }
}
