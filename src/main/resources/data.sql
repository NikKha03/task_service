INSERT INTO projects (project_id, name, project_owner)
VALUES (4, 'OZON', 'yunya');

INSERT INTO categories (category_id, project, name)
VALUES (1, 1, 'Category 1');
INSERT INTO categories (category_id, project, name)
VALUES (2, 1, 'Category 2');
INSERT INTO categories (category_id, project, name)
VALUES (3, 1, 'Category 3');

INSERT INTO tasks (category, owner, task_id, header)
VALUES (1, 'kolya_kha', 1, 'Task 1');
INSERT INTO tasks (category, owner, task_id, header)
VALUES (2, 'kolya_kha', 2, 'Task 2');
INSERT INTO tasks (category, owner, task_id, header)
VALUES (2, 'kolya_kha', 3, 'Task 3');
INSERT INTO tasks (category, owner, task_id, header)
VALUES (3,'yunya', 4, 'Task 4');