package com.app.model;

/**
 * @author jhon
 * @version 1.0
 **/
public class Student {

    /** Empty **/
    private long studentId;
    /** Empty **/
    private String studentAllName;
    /** Empty **/
    private long studentCode;
    /** Empty **/
    private String studentEmail;
    /** Empty **/
    private String studentSchool;

    /** Empty **/
    public Student(long studentId, String studentAllName, long studentCode, String studentEmail, String studentSchool) {
        this.studentId = studentId;
        this.studentAllName = studentAllName;
        this.studentCode = studentCode;
        this.studentEmail = studentEmail;
        this.studentSchool = studentSchool;
    }

    /** Empty **/
    public Student(String studentAllName, long studentCode, String studentEmail, String studentSchool) {
        this.studentAllName = studentAllName;
        this.studentCode = studentCode;
        this.studentEmail = studentEmail;
        this.studentSchool = studentSchool;
    }

    /** Empty **/
    public long getStudentId() {
        return this.studentId;
    }

    /** Empty **/
    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    /** Empty **/
    public String getStudentAllName() {
        return this.studentAllName;
    }

    /** Empty **/
    public void setStudentAllName(String studentAllName) {
        this.studentAllName = studentAllName;
    }

    /** Empty **/
    public long getStudentCode() {
        return this.studentCode;
    }

    /** Empty **/
    public void setStudentCode(long studentCode) {
        this.studentCode = studentCode;
    }

    /** Empty **/
    public String getStudentEmail() {
        return this.studentEmail;
    }

    /** Empty **/
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    /** Empty **/
    public String getStudentSchool() {
        return this.studentSchool;
    }

    /** Empty **/
    public void setStudentSchool(String studentSchool) {
        this.studentSchool = studentSchool;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentAllName='" + studentAllName + '\'' +
                ", studentCode=" + studentCode +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentSchool='" + studentSchool + '\'' +
                '}';
    }
}
