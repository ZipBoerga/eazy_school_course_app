CREATE TABLE IF NOT EXISTS contact_message
(
    contact_id INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    mobile_num VARCHAR(10)  NOT NULL,
    email      VARCHAR(100) NOT NULL,
    subject    VARCHAR(100) NOT NULL,
    message    VARCHAR(500) NOT NULL,
    status     VARCHAR(10)  NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    created_by VARCHAR(50)  NOT NULL,
    updated_at TIMESTAMP    NULL DEFAULT NULL,
    updated_by VARCHAR(50)       DEFAULT NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS holidays
(
    holiday_id INT AUTO_INCREMENT PRIMARY KEY,
    `day`      VARCHAR(20)  NOT NULL,
    reason     VARCHAR(100) NOT NULL,
    `type`     VARCHAR(20)  NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    created_by VARCHAR(50)  NOT NULL,
    updated_at TIMESTAMP    NULL DEFAULT NULL,
    updated_by VARCHAR(50)       DEFAULT NULL
) ENGINE = InnoDB;
