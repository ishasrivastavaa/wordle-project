#!/bin/bash

# Navigate to React project directory
cd ./Frontend
npm start & # Run React app in background

# Navigate to Spring Boot project directory
cd ../spring-boot
./gradlew bootRun # Run Spring Boot app
