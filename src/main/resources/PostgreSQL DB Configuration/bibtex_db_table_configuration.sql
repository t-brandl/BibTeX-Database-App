CREATE TABLE bibtex_db
(
    cite_key character varying COLLATE pg_catalog."default" NOT NULL,
    cite_type character varying COLLATE pg_catalog."default",
    author character varying COLLATE pg_catalog."default",
    editor character varying COLLATE pg_catalog."default",
    address character varying COLLATE pg_catalog."default",
    title character varying COLLATE pg_catalog."default",
    booktitle character varying COLLATE pg_catalog."default",
    journal character varying COLLATE pg_catalog."default",
    publisher character varying COLLATE pg_catalog."default",
    publication_year character varying COLLATE pg_catalog."default",
    howpublished character varying COLLATE pg_catalog."default",
    organization character varying COLLATE pg_catalog."default",
    institution character varying COLLATE pg_catalog."default",
    school character varying COLLATE pg_catalog."default",
    volume character varying COLLATE pg_catalog."default",
    series character varying COLLATE pg_catalog."default",
    type character varying COLLATE pg_catalog."default",
    edition character varying COLLATE pg_catalog."default",
    chapter character varying COLLATE pg_catalog."default",
    month character varying COLLATE pg_catalog."default",
    "number" character varying COLLATE pg_catalog."default",
    pages character varying COLLATE pg_catalog."default",
    note character varying COLLATE pg_catalog."default",
    created_at timestamp with time zone DEFAULT now(),
    CONSTRAINT bibtex_db_pkey PRIMARY KEY (cite_key),
    CONSTRAINT check_types CHECK (cite_type::text = ANY (ARRAY['article'::character varying, 'book'::character varying, 'booklet'::character varying,
    'inbook'::character varying, 'inproceeding'::character varying, 'conference'::character varying, 'incollection'::character varying,
    'manual'::character varying, 'phdthesis'::character varying, 'mastersthesis'::character varying, 'misc'::character varying,
    'proceedings'::character varying, 'techreport'::character varying, 'unpublished'::character varying]::text[])),
    CONSTRAINT is_valide_value CHECK ((cite_type::text = 'article'::text AND (author IS NOT NULL AND title IS NOT NULL AND journal IS NOT NULL AND publication_year IS NOT NULL)) OR
    (cite_type::text = 'book'::text AND ((author IS NOT NULL OR editor IS NOT NULL) AND title IS NOT NULL AND publisher IS NOT NULL AND publication_year IS NOT NULL)) OR
    (cite_type::text = 'booklet'::text AND (title IS NOT NULL)) OR
    (cite_type::text = 'conference'::text AND (author IS NOT NULL AND title IS NOT NULL AND booktitle IS NOT NULL AND publication_year IS NOT NULL)) OR
    (cite_type::text = 'inbook'::text AND ((author IS NOT NULL OR editor IS NOT NULL) AND title IS NOT NULL AND (chapter IS NOT NULL OR pages IS NOT NULL) AND publisher IS NOT NULL AND publication_year IS NOT NULL)) OR
    (cite_type::text = 'incollection'::text AND (author IS NOT NULL AND title IS NOT NULL AND booktitle IS NOT NULL AND publisher IS NOT NULL AND publication_year IS NOT NULL)) OR
    (cite_type::text = 'inproceeding'::text AND (author IS NOT NULL AND title IS NOT NULL AND booktitle IS NOT NULL AND publication_year IS NOT NULL)) OR
    (cite_type::text = 'manual'::text AND (title IS NOT NULL)) OR
    (cite_type::text = 'mastersthesis'::text AND (author IS NOT NULL AND title IS NOT NULL AND school IS NOT NULL AND publication_year IS NOT NULL)) OR
    (cite_type::text = 'phdthesis'::text AND (author IS NOT NULL AND title IS NOT NULL AND school IS NOT NULL AND publication_year IS NOT NULL)) OR
    (cite_type::text = 'proceedings'::text AND (title IS NOT NULL AND publication_year IS NOT NULL)) OR
    (cite_type::text = 'techreport'::text AND (author IS NOT NULL AND title IS NOT NULL AND institution IS NOT NULL AND publication_year IS NOT NULL)) OR
    (cite_type::text = 'unpublished'::text AND (author IS NOT NULL AND title IS NOT NULL AND note IS NOT NULL)) OR
    (cite_type::text = 'misc'::text AND (cite_key IS NOT NULL)))
)

TABLESPACE pg_default;

ALTER TABLE bibtex_db
    OWNER to postgres;

GRANT ALL ON TABLE bibtex_db TO postgres;

GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE bibtex_db TO relational_bibtex_user;