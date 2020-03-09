package com.biometry.app.web;

import com.biometry.app.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

}
