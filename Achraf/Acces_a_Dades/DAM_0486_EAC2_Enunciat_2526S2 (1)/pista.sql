-- 1. Eliminem si ja existeix
DROP TABLE IF EXISTS pista;
DROP TYPE IF EXISTS estat_neu;
DROP TYPE IF EXISTS dades_tecniques;

-- 2. Dades físiques de la pista (fixes)
CREATE TYPE dades_tecniques AS (
    longitud_m INTEGER,
    desnivell_m INTEGER,
    pendent_max_pct INTEGER
);

ALTER TYPE dades_tecniques
    OWNER TO ioc;

-- 3. Condicions de la neu (variables)
CREATE TYPE estat_neu AS (
    gruix_cm INTEGER,
    qualitat VARCHAR(50)
);

ALTER TYPE estat_neu
    OWNER TO ioc;

-- 4. Taula de pistes
CREATE TABLE pista (
    id VARCHAR(10) PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    color VARCHAR(20),               -- Verda, Blava, Vermella, Negra
    oberta BOOLEAN,         -- Oberta, Tancada

    -- Aplicació dels tipus compostos
    especificacions dades_tecniques,
    condicions estat_neu,

    -- Únic camp array: Remuntadors que donen accés a la pista
    remuntadors_acces VARCHAR[]      -- Ex: {"Telecabina Sud", "Cadira del Bosc"}
);

ALTER TABLE pista
    OWNER to ioc;
