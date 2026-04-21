DROP TABLE IF EXISTS course CASCADE;
DROP TABLE IF EXISTS course_category CASCADE;

CREATE TABLE course_category (
    id   SERIAL PRIMARY KEY,
    name TEXT    NOT NULL UNIQUE        
);

CREATE TABLE course (
    id          SERIAL PRIMARY KEY,
    title       TEXT    NOT NULL,
    description TEXT    NOT NULL,
    category    TEXT    NOT NULL,
    image_url   TEXT,

    CONSTRAINT chk_category CHECK (category IN ('PROGRAMMING', 'DESIGN', 'CLOUD', 'TESTING'))
);


INSERT INTO course_category (name) VALUES
    ('PROGRAMMING'),
    ('DESIGN'),
    ('CLOUD'),
    ('TESTING');

INSERT INTO course (title, description, category, image_url) VALUES
    ('Learn Python',
        'Aprende programación con Python desde cero: variables, funciones, OOP y más.',
        'PROGRAMMING',
        'https://upload.wikimedia.org/wikipedia/commons/c/c3/Python-logo-notext.svg'),

    ('HTML/CSS',
        'Domina el maquetado web con HTML5 semántico y diseño responsivo con CSS3.',
        'DESIGN',
        'https://upload.wikimedia.org/wikipedia/commons/6/61/HTML5_logo_and_wordmark.svg'),

    ('JavaScript',
        'El lenguaje del navegador: ES6+, asincronía, DOM y fundamentos de Node.js.',
        'PROGRAMMING',
        'https://upload.wikimedia.org/wikipedia/commons/6/6a/JavaScript-logo.png'),

    ('Docker',
        'Contenerización de aplicaciones: imágenes, contenedores, Docker Compose y buenas prácticas.',
        'PROGRAMMING',
        'https://upload.wikimedia.org/wikipedia/commons/4/4e/Docker_%28container_engine%29_logo.svg'),

    ('AWS',
        'Amazon Web Services: EC2, S3, Lambda, RDS y arquitecturas cloud escalables.',
        'CLOUD',
        'https://upload.wikimedia.org/wikipedia/commons/9/93/Amazon_Web_Services_Logo.svg'),

    ('JUnit',
        'Testing en Java: assertions, mocks con Mockito, TDD y cobertura de código.',
        'TESTING',
        'https://junit.org/junit5/assets/img/junit5-logo.png');
