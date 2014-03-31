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
import net.acardenas.rest.domain.Employee;
import net.acardenas.rest.domain.EmployeeDetails;
import net.acardenas.rest.domain.EmployeeDomain;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
        String myQueryString = "SELECT NEW net.acardenas.rest.domain.EmployeeDomain(" +
                "e.id, e.firstName, e.lastName, e.title, e.picture, COUNT(r.id) )" +
                "FROM EmployeeEntity e " +
                "LEFT OUTER JOIN EmployeeEntity r ON r.managerId = e " +
                "GROUP BY e.id ORDER BY e.lastName, e.firstName";
        TypedQuery<EmployeeDomain> typedQuery = entityManager.createQuery(myQueryString, EmployeeDomain.class);
        return typedQuery.getResultList();
    }

    public List<EmployeeDomain> getAllEmployeesDomainNative()
    {
        String myQueryString = "SELECT " +
                "e.id, e.firstName, e.lastName, e.title, e.picture, COUNT(r.id)  " +
                "FROM employee e " +
                "LEFT OUTER JOIN employee r ON r.managerId = e.id " +
                "GROUP BY e.id ORDER BY e.lastName, e.firstName";
        Query typedQuery = entityManager.createNativeQuery(myQueryString);

        List<Object[]> myEmployeeDomains = typedQuery.getResultList();
        List<EmployeeDomain> myResult = new ArrayList<EmployeeDomain>();

        for (Object[] myObject : myEmployeeDomains)
        {
            EmployeeDomain myEmployeeDomain = new EmployeeDomain();
            myEmployeeDomain.setId((Integer) myObject[0]);
            myEmployeeDomain.setFirstName((String) myObject[1]);
            myEmployeeDomain.setLastName((String) myObject[2]);
            myEmployeeDomain.setTitle((String) myObject[3]);
            myEmployeeDomain.setPicture((String) myObject[4]);
            myEmployeeDomain.setReportCount((Long) myObject[5]);
            myResult.add(myEmployeeDomain);
        }

        return myResult;
    }

    public EmployeeEntity find(int aKey)
    {
        return entityManager.find(EmployeeEntity.class, aKey);
    }

    public EmployeeDetails getEmployeeDetails(int aKey)
    {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String myQueryString = "SELECT NEW net.acardenas.rest.domain.EmployeeDetails(" +
                "e.id, e.firstName, e.lastName, m.id , e.title, e.department, e.city, " +
                "e.officePhone, e.cellPhone, e.email, e.picture, m.firstName, m.lastName, COUNT(r.id) ) " +
                "FROM EmployeeEntity e " +
                "LEFT JOIN FETCH e.managerId m " +
                "LEFT OUTER JOIN EmployeeEntity r ON r.managerId = e " +
                "WHERE e.id = :id ";
        TypedQuery<EmployeeDetails> typedQuery = entityManager.createQuery(myQueryString, EmployeeDetails.class);
        typedQuery.setParameter("id", aKey);
        EmployeeDetails myResult = typedQuery.getSingleResult();
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
            e.printStackTrace();
            transaction.rollback();
        }

        return myReturn;

    }

}
