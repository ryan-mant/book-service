CREATE TABLE book (
  id SERIAL PRIMARY KEY,
  author VARCHAR(100) NOT NULL,
  launch_date TIMESTAMP NOT NULL,
  price decimal(65,2) NOT NULL,
  title VARCHAR(100) NOT NULL
);
