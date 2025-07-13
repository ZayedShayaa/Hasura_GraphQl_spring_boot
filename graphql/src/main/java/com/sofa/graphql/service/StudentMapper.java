package com.sofa.graphql.service;
import com.sofa.generated.insertnewstudent.student;
import com.sofa.graphql.service.model.Student;

public class StudentMapper {

    public static Student fromInsertStudent(student s) {
        if (s == null) return null;
        return new Student(s.getId() != null ? Long.valueOf(s.getId()) : null, s.getName(),s.getEmail(),s.getAge());
    }

    public static Student fromGetStudent(com.sofa.generated.getstudentbyid.student s) {
        if (s == null) return null;
        return new Student(
                s.getId() != null ? Long.valueOf(s.getId()) : null,
                s.getName(),
                s.getEmail(),
                s.getAge()
        );
    }

    public static Student fromUpdateStudent(com.sofa.generated.updatestudentbyid.student s) {
        if (s == null) return null;
        return new Student(
                s.getId() != null ? Long.valueOf(s.getId()) : null,
                s.getName(),
                s.getEmail(),
                s.getAge()
        );
    }

    public static Student fromDeleteStudent(com.sofa.generated.deletestudentbyid.student s) {
        if (s == null) return null;
        return new Student(
                s.getId() != null ? Long.valueOf(s.getId()) : null,
                s.getName(),
                s.getEmail(),
                s.getAge()
        );
    }

    public static Student from(com.sofa.generated.getallstudents.student s) {
        if (s == null) return null;
        return new Student(
                s.getId() != null ? Long.valueOf(s.getId()) : null,
                s.getName(),
                s.getEmail(),
                s.getAge()
        );
    }
}
