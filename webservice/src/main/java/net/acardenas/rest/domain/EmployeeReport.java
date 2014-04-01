package net.acardenas.rest.domain;

/**
 * Created by acardenas on 3/31/14.
 */
public class EmployeeReport
{
    private int id;
    private String firstName;
    private String lastName;
    private String title;
    private Long reportCount;

    public EmployeeReport(int id, String firstName, String lastName, String title, Long reportCount)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
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

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
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
