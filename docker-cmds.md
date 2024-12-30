# Commands for setting up docker container
<! -- Make sure docker-compose.yml is on the csl machine, then run following command to start docker container -->
docker compose -f docker-compose.yml -p team-7-sql up -d

<! -- Replace hhe with your cs login -->
ssh -f -N -L localhost:50080:localhost:50080 hhe@cs506x07.cs.wisc.edu
ssh -f -N -L 3306:localhost:53306 hhe@cs506x07.cs.wisc.edu
