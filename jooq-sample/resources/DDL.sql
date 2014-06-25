CREATE TABLE IF NOT EXISTS language (
  id              INT     NOT NULL PRIMARY KEY,
  cd              CHAR(2)       NOT NULL,
  description     VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS author (
  id              INT     NOT NULL PRIMARY KEY,
  first_name      VARCHAR(50),
  last_name       VARCHAR(50)  NOT NULL,
  date_of_birth   DATE,
  year_of_birth   INT,
  distinguished   INT
);

CREATE TABLE IF NOT EXISTS book (
  id              INT     NOT NULL PRIMARY KEY,
  author_id       INT     NOT NULL,
  title           VARCHAR(200) NOT NULL,
  published_in    INT     NOT NULL,
  language_id     INT     NOT NULL,

  CONSTRAINT fk_book_author     FOREIGN KEY (author_id)   REFERENCES author(id),
  CONSTRAINT fk_book_language   FOREIGN KEY (language_id) REFERENCES language(id)
);

CREATE TABLE IF NOT EXISTS book_store (
  name            VARCHAR(200) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS book_to_book_store (
  name            VARCHAR(200) NOT NULL,
  book_id         INT       NOT NULL,
  stock           INT,

  PRIMARY KEY(name, book_id),
  CONSTRAINT fk_b2bs_book_store FOREIGN KEY (name)        REFERENCES book_store (name) ON DELETE CASCADE,
  CONSTRAINT fk_b2bs_book       FOREIGN KEY (book_id)     REFERENCES book (id)         ON DELETE CASCADE
);