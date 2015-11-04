package com.medicalcms.patients.handlers;

import com.medicalcms.Validable;
import lombok.Data;

@Data
class CreatePatientPayload implements Validable {
    String name;
    int age;
    String email;
    String gender;
    String city;

    public boolean isValid() {
        return !name.isEmpty() && age != 0 && !email.isEmpty() && !gender.isEmpty() && !city.isEmpty();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
