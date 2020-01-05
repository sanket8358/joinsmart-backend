package com.startup.joinsmart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "UserDetail")
public class UserDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserDetailId")
    private Long userDetailId;

    @Column(name = "userRegistrationId")
    private Long userRegistrationId;
    @Column(name = "isFresher")
    private Boolean isFresher;
    @Column(name = "totalExperience")
    private Integer totalExperience;
    @Column(name = "currentCompany")
    private String currentCompany;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "UserCompanyMapping", joinColumns = @JoinColumn(name = "UserDetailId"), inverseJoinColumns =  @JoinColumn(name = "CompanyId"))
    private List<Company> companies;

    public Long getUserRegistrationId() {
        return userRegistrationId;
    }

    public void setUserRegistrationId(Long userRegistrationId) {
        this.userRegistrationId = userRegistrationId;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public Long getUserDetailId() {
        return userDetailId;
    }

    public void setUserDetailId(Long userDetailId) {
        this.userDetailId = userDetailId;
    }

    public Boolean getFresher() {
        return isFresher;
    }

    public void setFresher(Boolean fresher) {
        isFresher = fresher;
    }

    public Integer getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(Integer totalExperience) {
        this.totalExperience = totalExperience;
    }

    public String getCurrentCompany() {
        return currentCompany;
    }

    public void setCurrentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
    }
}
