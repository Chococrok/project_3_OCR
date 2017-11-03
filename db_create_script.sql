DROP TABLE IF EXISTS comment, longueur, owner, secteur, site, topo, topo_owner, voie CASCADE;

CREATE SEQUENCE public.site_id_seq;

CREATE TABLE public.site (
                id INTEGER NOT NULL DEFAULT nextval('public.site_id_seq'),
                name VARCHAR NOT NULL,
                lat NUMERIC,
                long NUMERIC,
                how_to_find VARCHAR,
                description VARCHAR,
                CONSTRAINT site_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.site_id_seq OWNED BY public.site.id;

CREATE SEQUENCE public.topo_id_seq;

CREATE TABLE public.topo (
                id INTEGER NOT NULL DEFAULT nextval('public.topo_id_seq'),
                site_id INTEGER NOT NULL,
                name VARCHAR NOT NULL,
                CONSTRAINT topo_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.topo_id_seq OWNED BY public.topo.id;

CREATE SEQUENCE public.secteur_id_seq;

CREATE TABLE public.secteur (
                id INTEGER NOT NULL DEFAULT nextval('public.secteur_id_seq'),
                site_id INTEGER NOT NULL,
                name VARCHAR NOT NULL,
                description VARCHAR,
                CONSTRAINT secteur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.secteur_id_seq OWNED BY public.secteur.id;

CREATE SEQUENCE public.voie_id_seq;

CREATE TABLE public.voie (
                id INTEGER NOT NULL DEFAULT nextval('public.voie_id_seq'),
                secteur_id INTEGER NOT NULL,
                length INTEGER,
                name VARCHAR NOT NULL,
                point_number INTEGER,
                cotation VARCHAR,
                description VARCHAR,
                CONSTRAINT voie_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.voie_id_seq OWNED BY public.voie.id;

CREATE SEQUENCE public.owner_id_seq;

CREATE TABLE public.owner (
                id INTEGER NOT NULL DEFAULT nextval('public.owner_id_seq'),
                first_name VARCHAR NOT NULL,
                last_name VARCHAR NOT NULL,
                email VARCHAR NOT NULL,
                password VARCHAR NOT NULL,
                phone_number VARCHAR NOT NULL,
                CONSTRAINT owner_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.owner_id_seq OWNED BY public.owner.id;

CREATE TABLE public.topo_owner (
                topo_id INTEGER NOT NULL,
                owner_id INTEGER NOT NULL,
                available BOOLEAN DEFAULT true NOT NULL,
                CONSTRAINT topo_owner_pk PRIMARY KEY (topo_id, owner_id)
);


CREATE SEQUENCE public.longueur_id_seq;

CREATE TABLE public.longueur (
                id INTEGER NOT NULL DEFAULT nextval('public.longueur_id_seq'),
                voie_id INTEGER NOT NULL,
                name VARCHAR NOT NULL,
                length INTEGER,
                cotation VARCHAR,
                CONSTRAINT longueur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.longueur_id_seq OWNED BY public.longueur.id;

CREATE SEQUENCE public.comment_id_seq;

CREATE TABLE public.comment (
                id INTEGER NOT NULL DEFAULT nextval('public.comment_id_seq'),
                topo_id INTEGER,
                site_id INTEGER,
                time_stamp TIMESTAMP NOT NULL,
                content VARCHAR NOT NULL,
                secteur_id INTEGER,
                voie_id INTEGER,
                longueure_id INTEGER,
                CONSTRAINT comment_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.comment_id_seq OWNED BY public.comment.id;

ALTER TABLE public.comment ADD CONSTRAINT site_comment
FOREIGN KEY (site_id)
REFERENCES public.site (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.secteur ADD CONSTRAINT site_secteur_fk
FOREIGN KEY (site_id)
REFERENCES public.site (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo ADD CONSTRAINT site_topo_fk
FOREIGN KEY (site_id)
REFERENCES public.site (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.comment ADD CONSTRAINT topo_comment
FOREIGN KEY (topo_id)
REFERENCES public.topo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo_owner ADD CONSTRAINT topo_topo_owner_fk
FOREIGN KEY (topo_id)
REFERENCES public.topo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.comment ADD CONSTRAINT secteur_comment
FOREIGN KEY (secteur_id)
REFERENCES public.secteur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.voie ADD CONSTRAINT secteur_voie_fk
FOREIGN KEY (secteur_id)
REFERENCES public.secteur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.comment ADD CONSTRAINT voie_comment
FOREIGN KEY (voie_id)
REFERENCES public.voie (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.longueur ADD CONSTRAINT voie_longueur_fk
FOREIGN KEY (voie_id)
REFERENCES public.voie (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo_owner ADD CONSTRAINT owner_topo_owner_fk
FOREIGN KEY (owner_id)
REFERENCES public.owner (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.comment ADD CONSTRAINT longueur_comment
FOREIGN KEY (longueure_id)
REFERENCES public.longueur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
