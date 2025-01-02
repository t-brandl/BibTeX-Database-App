# Overview
The Application was part of my project for the course `Technical Documentation`. It allows 

# UML Class Diagram

# UML Sequence Diagram


# Requirements  

**Java**: JDK 23 or higher  
**Maven**: 3.9.8 or higher  
**PostgreSQL**: 17.2 or higher  
**Optional: pgAdmin 4**: To manage the Postgre database

## Key Dependencies
The project uses the following libraries:  

- **JavaFX** (17.0.6)  
  - `javafx-controls`  
  - `javafx-fxml`  
  - `javafx-graphics`  
  - `javafx-media`  
  - `javafx-base`  
- **JUnit** (4.13.1): For unit testing.  
- **Log4j** (2.17.1): For logging and application diagnostics.  
- **postgresql** (42.7.2): PostgreSQL Driver.  
- **jackson-databind** (2.13.4.2): To load the PostgreSQL DB credentials.

All dependencies are managed through Maven. For the full list, see the `pom.xml` file.

## Font

`The IBM Plex Sans` Font was used in this project. You can find it here:  
https://github.com/IBM/plex  

Simply download the zip and extract the folder `ibm-plex-sans/fonts/complete` to [src/main/resources/IBM Plex Sans Fonts](src/main/resources/IBM%20Plex%20Sans%20Fonts/)

# Usage

1. Set up your PostgreSQL Database, for example using the provided Configuration in [src/main/resources](src/main/resources)
2. Create a `DBconfig.json`, using the provided [DBconfig_template.json](DBconfig_template.json)
3. Run using your preferred IDE or Maven. Entry is the `Driver.java` file

# Additional Ressources
The UML Class Diagrams