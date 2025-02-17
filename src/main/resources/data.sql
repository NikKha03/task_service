INSERT INTO projects (project_id, name, project_owner)
VALUES (1, 'SB chat', 'kolya_kha');

INSERT INTO users (project, username, role) VALUES (1, 'kolya_kha', 'Participant');

INSERT INTO projects (project_id, name, project_owner)
VALUES (2, 'SB website', 'nik.kh.03');

INSERT INTO users (project, username, role) VALUES (2, 'nik.kh.03', 'Participant');

INSERT INTO projects (project_id, name, project_owner)
VALUES (3, 'Хакатон', 'victoria');

INSERT INTO users (project, username, role) VALUES (3, 'victoria', 'Participant');


INSERT INTO projects (project_id, name, project_owner)
VALUES (4, 'OZON', 'yunya');

INSERT INTO users (project, username, role) VALUES (4, 'yunya', 'Participant');

INSERT INTO categories (category_id, project, name)
VALUES (1, 1, 'Category 1');
INSERT INTO categories (category_id, project, name)
VALUES (2, 1, 'Category 2');
INSERT INTO categories (category_id, project, name)
VALUES (3, 1, 'Category 3');

INSERT INTO tasks (category, creator, task_id, header)
VALUES (1, 'kolya_kha', 1, 'Task 1');
INSERT INTO tasks (category, creator, task_id, header)
VALUES (2, 'kolya_kha', 2, 'Task 2');
INSERT INTO tasks (category, creator, task_id, header)
VALUES (2, 'kolya_kha', 3, 'Task 3');
INSERT INTO tasks (category, creator, task_id, header)
VALUES (3,'yunya', 4, 'Task 4');