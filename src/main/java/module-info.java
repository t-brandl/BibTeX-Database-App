module tb130.relational.bibtex.database.relational_bibtex_db {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires org.postgresql.jdbc;

    requires org.apache.logging.log4j;


    opens tb130.relational.bibtex.database to javafx.fxml;
    exports tb130.relational.bibtex.database;
}