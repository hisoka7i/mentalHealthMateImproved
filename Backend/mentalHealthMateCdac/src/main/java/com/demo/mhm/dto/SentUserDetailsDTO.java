package com.demo.mhm.dto;

public class SentUserDetailsDTO {
    private String jwtToken;
    private String userName;
    private Integer age;
    private String phoneNumber;
    private Character gender;

    public SentUserDetailsDTO(){
        
    }
    public SentUserDetailsDTO(String jwtToken, String userName, Integer age, String phoneNumber, Character gender) {
        this.jwtToken = jwtToken;
        this.userName = userName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }
}
