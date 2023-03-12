CREATE TABLE IF NOT EXISTS student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    score DOUBLE,
    graduate BOOLEAN,
    create_date TIMESTAMP
);
-- 加上 IF NOT EXISTS 是為惹不要重複建 STUDENT TABLE，因為spring boot內部機制，可能會去運行schema.sql這個檔案多次