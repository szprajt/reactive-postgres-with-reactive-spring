CREATE TABLE Phone (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    os VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);
INSERT INTO Phone (brand, model, os, price) VALUES
('Samsung', 'Galaxy S21', 'ANDROID', 999.99),
('Apple', 'iPhone 13', 'IOS', 1099.99),
('Google', 'Pixel 6', 'ANDROID', 799.99),
('OnePlus', '9 Pro', 'ANDROID', 1069.99);