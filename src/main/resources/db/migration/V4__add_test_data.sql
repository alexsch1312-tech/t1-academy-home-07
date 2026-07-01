TRUNCATE TABLE tbl_product, tbl_user RESTART IDENTITY CASCADE;

INSERT INTO tbl_user (username) VALUES
                                    ('alex_smith'),
                                    ('maria_ivanova'),
                                    ('john_doe'),
                                    ('elena_petrova'),
                                    ('kurt_cobain');


INSERT INTO tbl_product (account_number, balance, product_type, user_id) VALUES
-- alex_smith (id: 1) — 3 продукта
('ACC-40802001', 150000.00, 'ACCOUNT', 1),
('CRD-51062111', 2500.50, 'CARD', 1),
('ACC-40802002', 0.00, 'ACCOUNT', 1),

-- maria_ivanova (id: 2) — 5 продуктов
('ACC-40802003', 850000.75, 'ACCOUNT', 2),
('CRD-51062112', 12000.00, 'CARD', 2),
('CRD-51062113', 450.00, 'CARD', 2),
('ACC-40802004', 50.00, 'ACCOUNT', 2),
('CRD-51062114', 999999.99, 'CARD', 2),

-- john_doe (id: 3) — 1 продукт
('CRD-51062115', 7300.25, 'CARD', 3),

-- elena_petrova (id: 4) — 0 продуктов

-- kurt_cobain (id: 5) — 2 продукта
('ACC-40802005', 27000.00, 'ACCOUNT', 5),
('CRD-51062116', 150.00, 'CARD', 5);
