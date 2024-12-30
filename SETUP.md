## System Requirements

- **Java 21**
- **Node.js**  
  Install via the [official package manager](https://nodejs.org/en/download/package-manager)
- **Node package manager**
  Install with the following command (this needs to be installed in the Frontend folder):
  npm install
- **React**  
  Install with the following command:
  npm install react

## Tunneling into the database:
Before running the app, make sure to tunnel into the databse from another terminal window. This window needs to be running before and during the app running:

`ssh -f -N -L localhost:53306:localhost:53306 username@cs506x07.cs.wisc.edu`

(make sure to replace `username` with you CSL username)
You will then be prompted to enter your CSL password and authentication.
Once logged in you can continue to run the app

## Running on windows:
Open file explorer
Run start.bat

## Running on Mac/Linux:
Open project directory in the command line
First time setup: chmod +x start.sh
Run Project: ./start.sh