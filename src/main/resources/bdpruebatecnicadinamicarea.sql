CREATE TABLE employees (
    emp_no      BIGINT             NOT NULL AUTO_INCREMENT,
    birth_date  DATE            NOT NULL,
    first_name  VARCHAR(14)     NOT NULL,
    last_name   VARCHAR(16)     NOT NULL,
    gender      ENUM ('M','F')  NOT NULL,
    hire_date   DATE            NOT NULL,
    PRIMARY KEY (emp_no)
);

CREATE TABLE departments (
    dept_no     BIGINT         NOT NULL AUTO_INCREMENT,
    dept_name   VARCHAR(40)     NOT NULL,
    PRIMARY KEY (dept_no),
    UNIQUE  KEY (dept_name)
);

CREATE TABLE dept_emp (
    emp_no      BIGINT         NOT NULL,
    dept_no     BIGINT     NOT NULL,
    from_date   DATE        NOT NULL,
    to_date     DATE        NOT NULL,
    KEY         (emp_no),
    KEY         (dept_no),
    FOREIGN KEY (emp_no) REFERENCES employees (emp_no) ON DELETE CASCADE,
    FOREIGN KEY (dept_no) REFERENCES departments (dept_no) ON DELETE CASCADE,
    PRIMARY KEY (emp_no, dept_no)
);

CREATE TABLE dept_manager (
   dept_no      BIGINT  NOT NULL,
   emp_no       BIGINT      NOT NULL,
   from_date    DATE     NOT NULL,
   to_date      DATE     NOT NULL,
   KEY         (emp_no),
   KEY         (dept_no),
   FOREIGN KEY (emp_no)  REFERENCES employees (emp_no)    ON DELETE CASCADE,
   FOREIGN KEY (dept_no) REFERENCES departments (dept_no) ON DELETE CASCADE,
   PRIMARY KEY (emp_no, dept_no)  
);

CREATE TABLE titles (
    emp_no      BIGINT          NOT NULL,
    title       VARCHAR(50)  NOT NULL,
    from_date   DATE         NOT NULL,
    to_date     DATE,
    KEY         (emp_no),
    FOREIGN KEY (emp_no) REFERENCES employees (emp_no) ON DELETE CASCADE,
    PRIMARY KEY (emp_no, title, from_date)
);

CREATE TABLE salaries (
    emp_no      BIGINT    NOT NULL,
    salary      INT    NOT NULL,
    from_date   DATE   NOT NULL,
    to_date     DATE   NOT NULL,
    KEY         (emp_no),
    FOREIGN KEY (emp_no) REFERENCES employees (emp_no) ON DELETE CASCADE,
    PRIMARY KEY (emp_no, from_date)
);


-- Insertar empleados
INSERT INTO employees (birth_date, first_name, last_name, gender, hire_date)
VALUES
('1985-01-12', 'John', 'Doe', 'M', '2010-01-01'),
('1990-03-25', 'Jane', 'Smith', 'F', '2015-07-01'),
('1982-09-15', 'Alice', 'Brown', 'F', '2012-06-15');

-- Insertar departamentos
INSERT INTO departments (dept_name)
VALUES
('HR'),
('Engineering'),
('Sales');

-- Asignar empleados a departamentos (dept_emp)
INSERT INTO dept_emp (emp_no, dept_no, from_date, to_date)
VALUES
(1, 1, '2010-01-01', '2025-01-01'),
(2, 2, '2015-07-01', '2025-01-01'),
(3, 3, '2012-06-15', '2025-01-01');

-- Asignar gerentes a departamentos (dept_manager)
INSERT INTO dept_manager (dept_no, emp_no, from_date, to_date)
VALUES
(1, 1, '2010-01-01', '2025-01-01'),
(2, 2, '2015-07-01', '2025-01-01'),
(3, 3, '2012-06-15', '2025-01-01');

-- Insertar títulos de los empleados
INSERT INTO titles (emp_no, title, from_date, to_date)
VALUES
(1, 'Software Engineer', '2010-01-01', '2025-01-01'),
(2, 'HR Manager', '2015-07-01', '2025-01-01'),
(3, 'Sales Executive', '2012-06-15', '2025-01-01');

-- Insertar salarios de los empleados
INSERT INTO salaries (emp_no, salary, from_date, to_date)
VALUES
(1, 50000, '2010-01-01', '2025-01-01'),
(2, 60000, '2015-07-01', '2025-01-01'),
(3, 55000, '2012-06-15', '2025-01-01');