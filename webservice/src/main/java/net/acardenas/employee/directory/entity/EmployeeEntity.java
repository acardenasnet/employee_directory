/*
 * ---------------------------------------------------------------------------
 *  COPYRIGHT Alejandro Cardenas, acardenas.net, Saltillo,Coah, MX 2014.
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

package net.acardenas.employee.directory.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by acardenas on 3/26/14.
 */
@NamedQueries({
        @NamedQuery(name = EmployeeEntity.ALL_EMPLOYEES_DOMAIN_QUERY,
                query = "SELECT NEW net.acardenas.rest.domain.EmployeeDomain(" +
                        "e.id, e.firstName, e.lastName, e.title, e.picture, COUNT(r.id) )" +
                        "FROM EmployeeEntity e " +
                        "LEFT OUTER JOIN EmployeeEntity r ON r.managerId = e " +
                        "GROUP BY e.id ORDER BY e.lastName, e.firstName"),
        @NamedQuery(name = EmployeeEntity.DETAILS_EMPLOYEES_QUERY,
                query = "SELECT NEW net.acardenas.rest.domain.EmployeeDetails(" +
                        "e.id, e.firstName, e.lastName, m.id , e.title, e.department, e.city, " +
                        "e.officePhone, e.cellPhone, e.email, e.picture, m.firstName, m.lastName, COUNT(r.id) ) " +
                        "FROM EmployeeEntity e " +
                        "LEFT JOIN FETCH e.managerId m " +
                        "LEFT OUTER JOIN EmployeeEntity r ON r.managerId = e " +
                        "WHERE e.id = :" + EmployeeEntity.ID_PARAMETER +
                        " GROUP BY e.lastName ORDER BY e.lastName, e.firstName"),
        @NamedQuery(name = EmployeeEntity.REPORTS_EMPLOYEES_QUERY,
                query = "SELECT NEW net.acardenas.rest.domain.EmployeeReport(" +
                        "e.id, e.firstName, e.lastName, e.title, COUNT(r.id) ) " +
                        "FROM EmployeeEntity e " +
                        "LEFT OUTER JOIN EmployeeEntity r ON r.managerId = e " +
                        "WHERE e.managerId.id = :" + EmployeeEntity.ID_PARAMETER +
                        " GROUP BY e.id ORDER BY e.lastName, e.firstName")
})
@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
@Table(name = "employee")
public class EmployeeEntity
    implements Serializable
{
    public static final String ALL_EMPLOYEES_DOMAIN_QUERY = "employeeEntity.allDomain";
    public static final String DETAILS_EMPLOYEES_QUERY = "employeeEntity.employeeDetails";
    public static final String REPORTS_EMPLOYEES_QUERY = "employeeEntity.employeeReport";

    public static final String ID_PARAMETER = "id";


    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "firstName")

    private String firstName;
    @Column(name = "lastName")
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "managerId", nullable = true, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private EmployeeEntity managerId;

    @Column(name = "title")
    private String title;

    @Column(name = "department")
    private String department;

    @Column(name = "officePhone")
    private String officePhone;

    @Column(name = "cellPhone")
    private String cellPhone;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "picture")
    private String picture;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getPicture()
    {
        return picture;
    }

    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    public EmployeeEntity getManagerId()
    {
        return managerId;
    }

    public void setManagerId(EmployeeEntity managerId)
    {
        this.managerId = managerId;
    }
}
