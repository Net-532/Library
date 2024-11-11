-- Створення бази даних
CREATE DATABASE IF NOT EXISTS library_db;

-- Використання бази даних
USE library_db;

-- Таблиця для збереження інформації про книги
CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- Унікальний ідентифікатор для кожної книги
    title VARCHAR(255) NOT NULL,        -- Назва книги
    author VARCHAR(255) NOT NULL,       -- Автор книги
    isbn VARCHAR(13) NOT NULL UNIQUE    -- ISBN книги (унікальний)
);

-- Таблиця для збереження інформації про людей (спільна для читачів та бібліотекарів)
CREATE TABLE IF NOT EXISTS persons (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- Унікальний ідентифікатор
    name VARCHAR(255) NOT NULL          -- Ім'я людини
);

-- Таблиця для читачів (розширює базову таблицю persons)
CREATE TABLE IF NOT EXISTS readers (
    person_id INT,                         -- Ідентифікатор читача (ідентичний id в таблиці persons)
    PRIMARY KEY (person_id),               -- Читачі мають унікальні person_id
    FOREIGN KEY (person_id) REFERENCES persons(id) ON DELETE CASCADE  -- Зв'язок з таблицею persons
);

-- Таблиця для бібліотекарів (також розширює базову таблицю persons)
CREATE TABLE IF NOT EXISTS librarians (
    person_id INT,                         -- Ідентифікатор бібліотекаря (ідентичний id в таблиці persons)
    PRIMARY KEY (person_id),               -- Бібліотекарі мають унікальні person_id
    FOREIGN KEY (person_id) REFERENCES persons(id) ON DELETE CASCADE  -- Зв'язок з таблицею persons
);

-- Таблиця для збереження інформації про книги, які взяли читачі
CREATE TABLE IF NOT EXISTS borrowed_books (
    reader_id INT,                          -- Ідентифікатор читача (посилання на таблицю readers)
    book_id INT,                            -- Ідентифікатор книги (посилання на таблицю books)
    borrow_date DATE DEFAULT CURRENT_DATE,  -- Дата позики книги
    PRIMARY KEY (reader_id, book_id),       -- Первинний ключ, що складається з двох полів
    FOREIGN KEY (reader_id) REFERENCES readers(person_id) ON DELETE CASCADE,  -- Зв'язок з читачами
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE               -- Зв'язок з книгами
);
