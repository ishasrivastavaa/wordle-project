# MySQL commands used to create roles and assign them to users
## Commands to login to our DB
1. login to our team's CSL vm
2. mysql -h 127.0.0.1 -P 53306 -u username -p
3. when prompted, enter password

root account\
username: root\
password: UW_wordle_07

reader account\
username: readonly_user\
password: readonly

writer account\
username: writeonly_user\
password: writeonly


## Note that commands following mysql> are MySQL commands
This creates two roles, their names are self-explanatory\
mysql> CREATE ROLE read_role;\
mysql> CREATE ROLE write_role;

The following two commands assign read access to read_role and write/modify access to write_role\
mysql> GRANT SELECT ON UWwordle.* TO 'read_role';\
mysql> GRANT SELECT, UPDATE ON UWwordle.* TO 'write_role';

These creates user 'readonly_user'@'%' with password 'readonly' and 'writeonly_user'@'%' with password 'writeonly'\
mysql> CREATE USER 'readonly_user'@'%' IDENTIFIED BY 'readonly';\
mysql> CREATE USER 'writeonly_user'@'%' IDENTIFIED BY 'writeonly';

Then, assign temporary roles for current session for these two users\
mysql> GRANT read_role TO 'readonly_user'@'%';\
mysql> GRANT write_role TO 'writeonly_user'@'%';

Lastly, set current session roles into default roles, then any future login of these two users will default to corresponding roles\
mysql> SET DEFAULT ROLE read_role TO 'readonly_user'@'%';\
mysql> SET DEFAULT ROLE write_role TO 'writeonly_user'@'%';

Look at this cite for more info:\
https://dev.mysql.com/doc/refman/8.4/en/roles.html#roles-checking