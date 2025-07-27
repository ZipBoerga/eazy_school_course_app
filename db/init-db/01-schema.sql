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

CREATE TABLE IF NOT EXISTS holiday
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

CREATE TABLE IF NOT EXISTS role
(
    role_id    INT AUTO_INCREMENT PRIMARY KEY,
    role_name  VARCHAR(50) NOT NULL,
    created_at TIMESTAMP   NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP   NULL DEFAULT NULL,
    updated_by VARCHAR(50)      DEFAULT NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS address
(
    address_id int AUTO_INCREMENT PRIMARY KEY,
    address1   varchar(200) NOT NULL,
    address2   varchar(200) DEFAULT NULL,
    city       varchar(50)  NOT NULL,
    state      varchar(50)  NOT NULL,
    zip_code   varchar(10)  NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    created_by VARCHAR(50)  NOT NULL,
    updated_at TIMESTAMP    NULL DEFAULT NULL,
    updated_by VARCHAR(50)       DEFAULT NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS user
(
    user_id       int AUTO_INCREMENT PRIMARY KEY,
    name          varchar(100) NOT NULL,
    email         varchar(50)  NOT NULL,
    mobile_number varchar(20)  NOT NULL,
    password      varchar(200) NOT NULL,
    role_id       int          NOT NULL,
    address_id    int          NULL,
    created_at    TIMESTAMP    NOT NULL,
    created_by    VARCHAR(50)  NOT NULL,
    updated_at    TIMESTAMP    NULL DEFAULT NULL,
    updated_by    VARCHAR(50)       DEFAULT NULL,
    FOREIGN KEY (role_id) REFERENCES role (role_id),
    FOREIGN KEY (address_id) REFERENCES address (address_id)
) ENGINE = InnoDB;
