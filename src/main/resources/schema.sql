CREATE TABLE users(
    name VARCHAR(255),
    surname VARCHAR(255),
    phone_number VARCHAR(255) PRIMARY KEY
);

CREATE TABLE cards(
    number VARCHAR(16) PRIMARY KEY ,
    balance DOUBLE
);

CREATE TABLE transfers(
    sender_phone_number VARCHAR(32),
    recipient_phone_number VARCHAR(32),
    money DOUBLE,
    date DATE
);