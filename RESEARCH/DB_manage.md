# Research Report
## MySQL server
### Summary of Work
Requesting MySQL server from CSL and learning how to access/run insert and load commands in MySQL.
### Motivation
Our team wants 
### Time Spent
3 hours. The first one was spent trying to figure out the difference between a MySQL database and a MySQL server. The second one was dealt trying to figure out why I can't connect to a MySQL server. Then I found that I needed to request one from the CSL so I had to submit a form and called them to inquiry on the form.
### Results
I learnt how to run queries on a MySQL, these are transferrable skills once I am able to setup our MySQL server within our docker container.
### Sources

The following are some useful links on how to setup our mysql server. I am using a public db supported by CSL, there will be one  db_accessadmin, one db_datawriter, and one db_datareader. db_accessadmin will grant read only and write only access to other roles in our uwwordle database. Consider emailing Haochen via hhe92@wisc.edu for any concerns or questions.

Clarification on what hibernate is, and how do we use it to interact with mysql
https://stackoverflow.com/questions/58934013/difference-between-h2-vs-hibernate-and-mysql-in-general-sense

Can't connect to MySQL server
https://stackoverflow.com/questions/11657829/error-2002-hy000-cant-connect-to-local-mysql-server-through-socket-var-run

MySQL tutorial on how to connect to MySQL server
https://dev.mysql.com/doc/mysql-getting-started/en/

MySQL tutorial on how to create databases/tables/running queries
https://dev.mysql.com/doc/refman/8.4/en/tutorial.html

How to request MySQL database on CSL
https://csl.cs.wisc.edu/docs/csl/2012-08-16-mysql-database-service/

Set of Character sets and collations for MySQL server
https://dev.mysql.com/doc/refman/8.4/en/charset-charsets.html

Contact the CSL on DB issues
https://csl.cs.wisc.edu/docs/csl/2012-08-16-mysql-database-service/

How are db roles managed
https://www.linkedin.com/advice/0/how-do-you-handle-database-user-permissions
