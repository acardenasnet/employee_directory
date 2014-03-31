package net.acardenas.rest;

import net.acardenas.employee.directory.dataservice.EmployeeManager;
import net.acardenas.rest.domain.Employee;
import net.acardenas.rest.domain.Employees;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringWriter;

/**
 * Created by acardenas on 3/25/14.
 */
@Path("/service")
@Produces({MediaType.APPLICATION_JSON})

public class EmployeeService
{
    @Inject
    private EmployeeManager employeeManager;

    @GET
    @Path("/employees")
    public Response getEmployees()
    {
        Employees myEmployees = new Employees();
        myEmployees.setItems(employeeManager.getAllEmployeesDomain());
        return Response.status(Response.Status.OK).entity(myEmployees).build();
    }

    @GET
    @Path("/employee/{employeeId}")
    public Response getEmployee(@PathParam("employeeId") int aEmployeId)
    {
        Employee myEntity = new Employee();
        myEntity.setItem(employeeManager.getEmployeeDetails(aEmployeId));
        return Response.status(Response.Status.OK).entity(myEntity).build();
    }

    @GET
    @Path("/employee/create/dummy")
    public Response createDummy()
    {
        return Response.status(Response.Status.OK).entity(employeeManager.createDummyValues()).build();
    }
}
