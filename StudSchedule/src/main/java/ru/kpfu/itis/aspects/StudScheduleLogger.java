package ru.kpfu.itis.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudScheduleLogger {
    private static Logger log = Logger.getLogger(StudScheduleLogger.class);

    @After("execution(* ru.kpfu.itis.controller.AuthController.signUp())")
    public void logAfterSignUp() {
        log.info("User signed up");
    }

    @After("execution(* ru.kpfu.itis.service.SubjectService.add())")
    public void logAfterAddingSchedule() {
        log.info("Schedule added");
    }

    @After("execution(* ru.kpfu.itis.service.DepartmentService.add())")
    public void logAfterAddingDepartment() {
        log.info("Department added");
    }

    @After("execution(* ru.kpfu.itis.service.GroupService.add())")
    public void logAfterAddingGroup() {
        log.info("Group added");
    }

    @After("execution(* ru.kpfu.itis.service.HighSchoolService.add())")
    public void logAfterAddingSchool() {
        log.info("High school added");
    }

    @Before("execution(* ru.kpfu.itis.service.*.*(..))")
    public void before() {
        log.info("Service method is being executed..");
    }
}
