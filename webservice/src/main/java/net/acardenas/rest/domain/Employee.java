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

import net.acardenas.employee.directory.entity.EmployeeEntity;

/**
 * Created by acardenas on 3/26/14.
 */
public class Employee
{
    private EmployeeDetails item;

    public EmployeeDetails getItem()
    {
        return item;
    }

    public void setItem(EmployeeDetails anItem)
    {
        item = anItem;
    }
}
