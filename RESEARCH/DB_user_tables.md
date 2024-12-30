# Research Report

## Use DB to store username and password pairs safely (and evaluate doability)

### Summary of Work

I researched on how to store username and password in the database and executed the mysql commands to create these tables. I also did some research on if its relatively easy to hash passwords in java.

### Motivation

Our project might need to allow user to create their account and sign in, thus I decided to do some research on how to store these values in advance.

### Time Spent

~30 minutes how to write mysql statements for that

~60 minutes how to hash passwords in java

### Results

I first login to our team's CSL machine, the commands I executed in mysql are:
```shell
use UWwordle;
CREATE TABLE user (USER_NAME VARCHAR(255),
                    PASSWORD_ID INTEGER);
CREATE TABLE password (PASSWORD_ID INTEGER,
                    PASSWORD_HASH VARCHAR(255));
```

Then I run the following commands to check that I have created the tables correctly:
```shell
SHOW tables;
DESCRIBE user;
DESCRIBE password;
```

Then I did some research on how to hash passwords in java, and how are they compared, some links can be found below.

### Sources

- Commands for creating user and password table (look for N West's answer), as well as some insight on password hashing [^1]
- Intuition on using VARCHAR(255) instead of VARCHAR(256) [^2]
- Justification on why we are using two tables (suppose there are lots of superficial irrelevant redundant columns in the first table, having a second with just the password will be a scalable solution) [^3]
- How to get table schema [^4]
- Difference between SHA256 and bcrypt and why we should use bcrypt [^5]
- How to apply bcrypt in java [^6]
- How to apply SHA256 in java [^7]

[^1]: https://stackoverflow.com/questions/5329501/storing-user-passwords-data-in-database
[^2]: https://intellipaat.com/community/9222/why-historically-do-people-use-255-not-256-for-database-field-magnitudes#:~:text=With%20the%20most%20length%20of,knowledge%20(unless%20affected%20otherwise).
[^3]: https://stackoverflow.com/questions/20310503/most-efficient-way-to-store-user-profile-information
[^4]: https://stackoverflow.com/questions/898688/how-to-get-database-structure-in-mysql-via-query
[^5]: https://nordvpn.com/zh/blog/what-is-bcrypt/#:~:text=The%20main%20difference%20between%20bcrypt,designed%20to%20be%20computationally%20fast.
[^6]: https://dzone.com/articles/hashing-passwords-in-java-with-bcrypt
[^7]: https://medium.com/@AlexanderObregon/what-is-sha-256-hashing-in-java-0d46dfb83888