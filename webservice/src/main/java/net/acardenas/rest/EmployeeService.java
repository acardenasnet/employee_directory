package net.acardenas.rest;

import net.acardenas.employee.directory.dataservice.EmployeeManager;
import net.acardenas.rest.domain.EmployeeDetails;
import net.acardenas.rest.domain.EmployeeDomain;
import net.acardenas.rest.domain.EmployeeReport;
import net.acardenas.rest.domain.ItemWrapper;
import net.acardenas.rest.domain.ItemsWrapper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        ItemsWrapper<EmployeeDomain> myEntity = new ItemsWrapper<EmployeeDomain>();
        myEntity.setItems(employeeManager.getAllEmployeesDomain());
        return Response.status(Response.Status.OK).entity(myEntity).build();
    }

    @GET
    @Path("/employee/{employeeId}")
    public Response getEmployee(@PathParam("employeeId") int aEmployeId)
    {
        ItemWrapper<EmployeeDetails> myEntity = new ItemWrapper<EmployeeDetails>();
        myEntity.setItem(employeeManager.getEmployeeDetails(aEmployeId));
        return Response.status(Response.Status.OK).entity(myEntity).build();
    }

    @GET
    @Path("/employee/{employeeId}/report")
    public Response getEmployeeReport(@PathParam("employeeId") int aEmployeId)
    {
        ItemsWrapper<EmployeeReport> myEntity = new ItemsWrapper<EmployeeReport>();
        myEntity.setItems(employeeManager.getEmployeeReport(aEmployeId));
        return Response.status(Response.Status.OK).entity(myEntity).build();
    }

    @GET
    @Path("/employee/create/dummy")
    public Response createDummy()
    {
        return Response.status(Response.Status.OK).entity(employeeManager.createDummyValues()).build();
    }
}
