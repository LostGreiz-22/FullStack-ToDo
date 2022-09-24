DROP SCHEMA IF EXISTS edgar cascade;
CREATE SCHEMA edgar;

CREATE TABLE edgar.todolist (
    todo_id uuid,
    todo varchar(300),
    created_date TIMESTAMP WITH TIME ZONE,
    modified_date TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (todo_id)
);
