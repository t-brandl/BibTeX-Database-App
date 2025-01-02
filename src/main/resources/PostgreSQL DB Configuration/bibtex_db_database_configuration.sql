-- Database: relational_bibtex_db

-- DROP DATABASE relational_bibtex_db;

CREATE DATABASE relational_bibtex_db
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'German_Germany.1252'
    LC_CTYPE = 'German_Germany.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

GRANT ALL ON DATABASE relational_bibtex_db TO postgres;

GRANT CONNECT ON DATABASE relational_bibtex_db TO relational_bibtex_user;