-- Если date_planned_implementation == 23:59, то задача до конца дня
-- Если date_planned_implementation == null, то задача без даты выполнения
INSERT INTO tasks (task_id, comment, creation_date, date_planned_implementation, execution_date, header, owner, task_status)
    VALUES (1, 'Английский язык, биология, химия', '2024-08-07 14:05', '2024-08-08 18:00', null, 'Сделать ДЗ', 1, 'IN_PROGRESS');

INSERT INTO tasks (task_id, comment, creation_date, date_planned_implementation, execution_date, header, owner, task_status)
    VALUES (2, null, '2024-08-07 14:10', '2024-08-08 23:59', '2024-08-08 15:00', 'Помыть пол', 1, 'COMPLETED');

INSERT INTO tasks (task_id, comment, creation_date, date_planned_implementation, execution_date, header, owner, task_status)
    VALUES (3, 'День рук', '2024-08-07 14:15', '2024-08-08 23:59', '2024-08-08 13:00', 'Сходить в зал', 1, 'COMPLETED');

INSERT INTO tasks (task_id, comment, creation_date, date_planned_implementation, execution_date, header, owner, task_status)
    VALUES (4, null, '2024-08-07 14:20', null, null, 'Сходить к врачу', 1, 'IN_PROGRESS');


INSERT INTO tasks (task_id, comment, creation_date, date_planned_implementation, execution_date, header, owner, task_status)
    VALUES (5, null, '2024-08-07 14:25', '2024-08-07 11:15', null, 'Сходить в душ', 1, 'IN_PROGRESS');

INSERT INTO tasks (task_id, comment, creation_date, date_planned_implementation, execution_date, header, owner, task_status)
VALUES (6, null, '2024-08-07 14:30', '2024-08-07 11:10', null, 'Сходить в туалет', 1, 'IN_PROGRESS');
