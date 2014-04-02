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

package net.acardenas.employee.directory.dataservice;

import net.acardenas.employee.directory.entity.EmployeeEntity;
import net.acardenas.rest.domain.EmployeeDetails;
import net.acardenas.rest.domain.EmployeeDomain;
import net.acardenas.rest.domain.EmployeeReport;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by acardenas on 3/26/14.
 */
public class EmployeeManager
{
    private EntityManager entityManager;

    @Inject
    public EmployeeManager(EntityManager anEntityManager) {
        entityManager = anEntityManager;
    }

    public List<EmployeeEntity> getAllEmployees()
    {
        TypedQuery<EmployeeEntity> typedQuery = entityManager.createQuery("SELECT e FROM EmployeeEntity e", EmployeeEntity.class);
        return typedQuery.getResultList();
    }

    public List<EmployeeDomain> getAllEmployeesDomain()
    {
        TypedQuery<EmployeeDomain> typedQuery = entityManager.createNamedQuery(
                EmployeeEntity.ALL_EMPLOYEES_DOMAIN_QUERY, EmployeeDomain.class);
        return typedQuery.getResultList();
    }

    public EmployeeEntity find(int aKey)
    {
        return entityManager.find(EmployeeEntity.class, aKey);
    }

    public EmployeeDetails getEmployeeDetails(int aKey)
    {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<EmployeeDetails> typedQuery = entityManager.createNamedQuery(
                EmployeeEntity.DETAILS_EMPLOYEES_QUERY, EmployeeDetails.class);
        typedQuery.setParameter(EmployeeEntity.ID_PARAMETER, aKey);
        transaction.commit();
        try
        {
            return typedQuery.getSingleResult();
        }
        catch (NoResultException | NonUniqueResultException e)
        {
            return null;
        }
    }

    public List<EmployeeReport> getEmployeeReport(int aKey)
    {
        TypedQuery<EmployeeReport> typedQuery = entityManager.createNamedQuery(
                EmployeeEntity.REPORTS_EMPLOYEES_QUERY, EmployeeReport.class);
        typedQuery.setParameter(EmployeeEntity.ID_PARAMETER, aKey);
        return typedQuery.getResultList();
    }

    public int createDummyValues()
    {
        EntityTransaction transaction = entityManager.getTransaction();
        int myReturn = 0;
        try
        {
            transaction.begin();
            String myNativeQueryInsert = "INSERT INTO employee " +
                    "(id, firstName, lastName, managerId, title, department, officePhone, cellPhone, email, city, picture) " +
                    "VALUES (12,'Steven','Wells',4,'Software Architect','Engineering','617-000-0012','781-000-0012','swells@fakemail.com','Boston, MA','steven_wells.jpg')," +
                    "(1,'James','King',0,'President and CEO','Corporate','617-000-0001','781-000-0001','jking@fakemail.com','Boston, MA','james_king.jpg')," +
                    "(2,'Julie','Taylor',1,'VP of Marketing','Marketing','617-000-0002','781-000-0002','jtaylor@fakemail.com','Boston, MA','julie_taylor.jpg')," +
                    "(3,'Eugene','Lee',1,'CFO','Accounting','617-000-0003','781-000-0003','elee@fakemail.com','Boston, MA','eugene_lee.jpg')," +
                    "(4,'John','Williams',1,'VP of Engineering','Engineering','617-000-0004','781-000-0004','jwilliams@fakemail.com','Boston, MA','john_williams.jpg')," +
                    "(5,'Ray','Moore',1,'VP of Sales','Sales','617-000-0005','781-000-0005','rmoore@fakemail.com','Boston, MA','ray_moore.jpg')," +
                    "(11,'Amy','Jones',5,'Sales Representative','Sales','617-000-0011','781-000-0011','ajones@fakemail.com','Boston, MA','amy_jones.jpg')," +
                    "(10,'Kathleen','Byrne',5,'Sales Representative','Sales','617-000-0010','781-000-0010','kbyrne@fakemail.com','Boston, MA','kathleen_byrne.jpg')," +
                    "(9,'Gary','Donovan',2,'Marketing','Marketing','617-000-0009','781-000-0009','gdonovan@fakemail.com','Boston, MA','gary_donovan.jpg')," +
                    "(8,'Lisa','Wong',2,'Marketing Manager','Marketing','617-000-0008','781-000-0008','lwong@fakemail.com','Boston, MA','lisa_wong.jpg')," +
                    "(7,'Paula','Gates',4,'Software Architect','Engineering','617-000-0007','781-000-0007','pgates@fakemail.com','Boston, MA','paula_gates.jpg')," +
                    "(6,'Paul','Jones',4,'QA Manager','Engineering','617-000-0006','781-000-0006','pjones@fakemail.com','Boston, MA','paul_jones.jpg')"
                    ;
            Query myQuery = entityManager.createNativeQuery(myNativeQueryInsert);
            myReturn = myQuery.executeUpdate();
            transaction.commit();
        }
        catch(PersistenceException e)
        {
            transaction.rollback();
        }

        return myReturn;
    }
}
