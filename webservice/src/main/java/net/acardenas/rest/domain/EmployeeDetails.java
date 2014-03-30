/*
 * ---------------------------------------------------------------------------
 *  COPYRIGHT Alejandro Cardenas, acardenas.net, Saltillo,Coah, MX %year.
 *  All rights reserved.
 *
 *  The Copyright to the computer program(s) herein is the property of
 *  Alejandro Raul Cardenas
 *  The program(s) may be used and/or copied only with the written
 *  permission from Alejandro Cardenas, or in
 *  accordance with the terms and conditions stipulated in the
 *  agreement/contract under which the program(s) have been supplied.
 *  ---------------------------------------------------------------------------
 */

package net.acardenas.rest.domain;

/**
 * Created by acardenas on 3/28/14.
 */
public class EmployeeDetails
{
    private int id;
    private String firstName;
    private String lastName;
    private int managerId;
    private String title;
    private String department;
    private String city;
    private String officePhone;
    private String cellPhone;
    private String email;
    private String picture;
    private String managerFirstName;
    private String managerLastName;
    private Long reportCount;

    public EmployeeDetails(int id, String firstName, String lastName,
                           int managerId, String title, String department, String city,
                           String officePhone, String cellPhone, String email, String picture,
                           String managerFirstName, String managerLastName, Long reportCount)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.managerId = managerId;
        this.title = title;
        this.department = department;
        this.city = city;
        this.officePhone = officePhone;
        this.cellPhone = cellPhone;
        this.email = email;
        this.picture = picture;
        this.managerFirstName = managerFirstName;
        this.managerLastName = managerLastName;
        this.reportCount = reportCount;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getManagerId()
    {
        return managerId;
    }

    public void setManagerId(int managerId)
    {
        this.managerId = managerId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getOfficePhone()
    {
        return officePhone;
    }

    public void setOfficePhone(String officePhone)
    {
        this.officePhone = officePhone;
    }

    public String getCellPhone()
    {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone)
    {
        this.cellPhone = cellPhone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPicture()
    {
        return picture;
    }

    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    public String getManagerFirstName()
    {
        return managerFirstName;
    }

    public void setManagerFirstName(String managerFirstName)
    {
        this.managerFirstName = managerFirstName;
    }

    public String getManagerLastName()
    {
        return managerLastName;
    }

    public void setManagerLastName(String managerLastName)
    {
        this.managerLastName = managerLastName;
    }

    public Long getReportCount()
    {
        return reportCount;
    }

    public void setReportCount(Long reportCount)
    {
        this.reportCount = reportCount;
    }
}
