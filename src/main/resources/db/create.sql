SET MODE Postgresql;

CREATE DATABASE quiz-app-portal;

CREATE TABLE IF NOT EXISTS user_table(
    userId serial PRIMARY KEY,
    firstName VARCHAR,
    lastName VARCHAR,
    email VARCHAR,
    password VARCHAR,
);
