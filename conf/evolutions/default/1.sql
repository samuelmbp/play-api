# --- !Ups
CREATE TABLE employees (
  id INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  mobile_number VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  contract_type VARCHAR(255) NOT NULL,
  start_date VARCHAR(255) NOT NULL,
  full_time_part_time VARCHAR(255) NOT NULL,
  end_date VARCHAR(255) NOT NULL,
  hours_per_week VARCHAR(255) NOT NULL
);

# --- !Downs
DROP TABLE employees;