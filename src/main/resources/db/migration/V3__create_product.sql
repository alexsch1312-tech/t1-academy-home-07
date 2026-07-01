CREATE TABLE tbl_product (
                             id BIGSERIAL PRIMARY KEY,
                             account_number VARCHAR(50) NOT NULL UNIQUE,
                             balance NUMERIC(19, 2) NOT NULL,
                             product_type VARCHAR(20) NOT NULL,
                             user_id BIGINT NOT NULL,
                             CONSTRAINT fk_product_user FOREIGN KEY (user_id) REFERENCES tbl_user (id) ON DELETE CASCADE
);

CREATE INDEX idx_product_user_id ON tbl_product(user_id);