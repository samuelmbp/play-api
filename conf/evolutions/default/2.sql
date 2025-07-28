# --- !Ups

CREATE TABLE employees (
  id INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  mobile_number VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL
);

CREATE TABLE contracts (
  id INT PRIMARY KEY AUTO_INCREMENT,
  employee_id INT,
  contract_type VARCHAR(255) NOT NULL,
  start_date VARCHAR(255) NOT NULL,
  full_time_part_time VARCHAR(255) NOT NULL,
  end_date VARCHAR(255),
  hours_per_week VARCHAR(255) NOT NULL,
  CONSTRAINT fk_employee
    FOREIGN KEY (employee_id)
    REFERENCES employees(id)
    ON DELETE SET NULL
);

# --- !Downs

DROP TABLE contracts;
DROP TABLE employees;
