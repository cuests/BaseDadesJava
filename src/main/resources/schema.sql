CREATE TABLE IF NOT EXISTS events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    category VARCHAR(50),
    event_date DATE NOT NULL,
    city VARCHAR(100) NOT NULL
);

INSERT INTO events (title, description, category, event_date, city)
SELECT 'Partit de pàdel', 'Partit amistos dissabte', 'SPORTE', DATE '2026-01-01', 'Tarragona'
WHERE NOT EXISTS (
    SELECT 1 FROM events WHERE title = 'Partit de pàdel'
);

INSERT INTO events (title, description, category, event_date, city)
SELECT 'Gaming night', 'Torneig casual', 'GAming', DATE '2026-04-25', 'Reus'
WHERE NOT EXISTS (
    SELECT 1 FROM events WHERE title = 'Gaming night'
);