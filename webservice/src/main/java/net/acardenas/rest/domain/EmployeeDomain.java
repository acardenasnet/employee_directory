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
public class EmployeeDomain
{
    private int id;
    private String firstName;
    private String lastName;
    private String title;
    private String picture;
    private Long reportCount;

    public EmployeeDomain()
    {

    }

    public EmployeeDomain(int anId, String aFirstName, String aLastName, String aTitle, String aPicture, Long aReportCount)
    {
        id = anId;
        firstName =aFirstName;
        lastName = aLastName;
        title = aTitle;
        picture = aPicture;
        reportCount = aReportCount;
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

    public String getPicture()
    {
        return picture;
    }

    public void setPicture(String picture)
    {
        this.picture = picture;
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
