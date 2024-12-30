@echo off

:: Navigate to React project directory
cd /d ./Frontend
start npm start

:: Run Spring Boot app
cd /d ../spring-boot
gradlew.bat bootRun
