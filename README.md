Employee Directory
==================

Employee Directory, It started as scholar project.

This take as base the code from http://coenraets.org/blog/2011/10/sample-application-with-jquery-mobile-and-phonegap/, 
But create the webservice with Java.

The goal of this poject was scholar to understand the Phonegap, jquery mobile, and JSON.

To start up the webservice, you need move into webservice folder and run:

<code>
mvn jetty:run
</code>

Once you started the server this shouold been binded the por 9090, hence you should be able to access to te following urls:

<pre>To retrieve all the employees.
<code>
http://localhost:9090/employee-server/service/employees
</code>
</pre>

<pre>To retrieve a employee
<code>
http://localhost:9090/employee-server/service/employee/{employee_id}
</code>
</pre>

<pre>To retrieve a employee's reportees.
<code>
http://localhost:9090/employee-server/service/employee/{employee_id}/report
</code>
</pre>

To compilie the phnonegap application, you need move into device folder, and the execute the following command:

<code>
phonegap run android
</code>
