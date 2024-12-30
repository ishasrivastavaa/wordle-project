# Research Report
## Hibernate ORM intro
### Summary of work
I researched the basics of how to use Hibernate ORM to interact with a database. I read through Hibernate's given documentation along with
watching a video walking through a code example of using Hibernate.
### Motivation
We need some way to interact with a database hosted on a docker container, and we needed it to be in java, and hibernate offers easier functionality with
its tagging system at the expense of a bit more setup compared to using JDBC
### Time Spent
~60 minutes reading through documentation and watching the video
~30 minutes reconfiguring the .cfg.xml file used in the video to be compatable with MySQL8
### Results
I gained a good beginner understanding of how to use Hibernate for our project, and reduced the complexity of it in my head.
I produced a config file that is tuned for MySQL8, and ready to be updated once our database is running.
What still needs to be done however is using the video's code repository to practice using it for myself and run the commands that
will be relevant for our needs, which are just querying the database to get a word in sprint 1, and then writing results from the player
in sprint 2 and sprint 3, and also talking with the TA to get help setting up a build manager to include the correct dependencies
## Links
Getting started with hibernate: https://docs.jboss.org/hibernate/orm/6.6/quickstart/html_single/#hibernate-gsg-tutorial-annotations-config
Hibernate tutorial video: https://www.youtube.com/watch?v=xHminZ9Dxm4&ab_channel=MarcoCodes
Hibernate tutorial repository: https://github.com/marcobehlerjetbrains/hibernate-tutorial
Hibernate javadocs: https://docs.jboss.org/hibernate/orm/6.6/javadocs/