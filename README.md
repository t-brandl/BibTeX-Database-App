# Overview
The Application was part of my project for the course `Technical Documentation` in 2020.  
The most popular way to write TeX documents today is with LaTeX, which allows you to adjust document styles with a single line and easily reference literature using the `\cite{citation_key}` command. This references an entry from a bibliography database. The most commonly used tool for this is `BibTeX`, which generates `.bib` files that can be included in documents with `\bibliography{bib-name}`. While effective, `BibTeX` has a major limitation: the bibliography database is local. This is inconvenient when working across multiple devices or in academic settings where individuals must maintain separate, potentially redundant databases.

This project addresses the issue by introducing a centralized relational database accessible via a desktop client. The client supports multi-device and multi-user workflows, enabling users to select `TeX` documents, scan them for references, and query the keys from the centralized database. The references are saved in a `.bib` file with the same name in the document's directory. The program can also identify and scan linked `TeX` documents for references, if desired.

Through the client’s GUI, users can manage the database by adding or removing references. Future updates will allow importing `.bib` files to automatically populate the database, streamlining bibliography management across devices and teams.

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

# BibTeX Items and keys

Following Bibtex items with the given respective key are supported by the application:

| Index         | 0   | 1    | 2      | 3      | 4       | 5     | 6         | 7       | 8         | 9    | 10           | 11           | 12          | 13     | 14     | 15     | 16   | 17      | 18      | 19    | 20     | 21    | 22   |
|---------------|-----|------|--------|--------|---------|-------|-----------|---------|-----------|------|--------------|--------------|-------------|--------|--------|--------|------|---------|---------|-------|--------|-------|------|
| Value         | key | type | author | editor | address | title | booktitle | journal | publisher | year | howpublished | organization | institution | school | volume | series | type | edition | chapter | month | number | pages | note |
| Required Keys |     |      |        |        |         |       |           |         |           |      |              |              |             |        |        |        |      |         |         |       |        |       |      |
| ARTICLE       | x   | x    | x      |        |         | x     |           | x       |           | x    |              |              |             |        | o      |        |      |         |         | o     | o      | o     | o    |
| BOOK          | x   | x    | a      | a      | o       | x     |           |         | x         | x    |              |              |             |        | o      | o      |      | o       |         | o     | o      |       | o    |
| BOOKLET       | x   | x    | o      |        | o       | x     |           |         |           | o    | o            |              |             |        |        |        |      |         |         | o     |        |       | o    |
| INBOOK        | x   | x    | a      | a      | o       | x     |           |         | x         | x    |              |              |             |        | o      | o      | o    | o       | b       | o     | o      | b     | o    |
| INPROCEEDING  | x   | x    | x      | o      | o       | x     | x         |         | o         | x    |              | o            |             |        | o      | o      |      |         |         | o     | o      | o     | o    |
| CONFERENCE    | x   | x    | x      | o      | o       | x     | x         |         | o         | x    |              | o            |             |        | o      | o      |      |         |         | o     | o      | o     | o    |
| INCOLLECTION  | x   | x    | x      | o      | o       | x     | x         |         | x         | x    |              |              |             |        | o      | o      | o    | o       | o       | o     | o      | o     | o    |
| MANUAL        | x   | x    | o      |        | o       | x     |           |         |           | o    |              | o            |             |        |        |        |      | o       |         | o     |        |       | o    |
| PHDTHESIS     | x   | x    | x      |        | o       | x     |           |         |           | x    |              |              |             | x      |        |        |      |         |         | o     |        |       | o    |
| MASTERSTHESIS | x   | x    | x      |        | o       | x     |           |         |           | x    |              |              |             | x      |        |        | o    |         |         | o     |        |       | o    |
| MISC          | x   | x    | o      |        |         | o     |           |         |           | o    | o            |              |             |        |        |        |      |         |         | o     |        |       | o    |
| PROCEEDINGS   | x   | x    |        | o      | o       | x     |           |         | o         | x    |              | o            |             |        | o      | o      |      |         |         | o     | o      |       | o    |
| TECHREPORT    | x   | x    | x      |        | o       | x     |           |         |           | x    |              |              | x           |        |        |        | o    |         |         | o     | o      |       | o    |
| UNPUBLISHED   | x   | x    | x      |        |         | x     |           |         |           | o    |              |              |             |        |        |        |      |         |         | o     |        |       | x    |

  
  
a: both or either  
b: both or either

# UML Class Diagram
The following uML Class Diagram describes the core functions of the architecture:  
![UML Class Diagram](documentation/UML_relBib.png)

`Bibitem` is the most important class in the document. Every item has an array containing an entry for each of the 23 bibtex keys. The `getItems()` method returns the array, the `toString()` method overrides the default `toString` function to look like a classic entry in a bibtex file, e.g.:
```BibTeX
@conference{Xconference,   
author    = "",
title     = "",
booktitle = "",
editor    = "",
volume    = "",
number    = "",
series    = "",
}
``` 

`outputFiles` manages all the file outout. `writeToBib()` writes the bibliography to a `.bib` file. `updateBibRef()` is not yet implemented.  
`LoadConfigurations` has the task to load configuration files for the database connection. `loadDBconfig()` reads the `DBconfig.json` in the same directory and returns the JDBC URL, username and password.  
`DBAccess` controls all task related to the database. Adding entries to the database, removing entries from them and querying the database for entries.  
`CrawlFiles` has the task of crawling and parsing given documents (`.tex` files) for `cite` and `nocite` commands, as well as `input` and `include`.  
`RelationalBibtexDB` is the core of the application, containing the GUI and event handlers. The GUI was created using `JavaFX` and `.fxml`-files with a `css` stylesheet to ensure consistent styling across all UI windows.

# UML Sequence Diagram