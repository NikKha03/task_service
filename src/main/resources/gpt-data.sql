-- 1. Проект по разработке веб-приложения
INSERT INTO projects (name, project_owner) VALUES ('Разработка веб-приложения', 'user_123');
-- 2. Проект по созданию мобильного приложения
INSERT INTO projects (name, project_owner) VALUES ('Мобильное приложение для доставки еды', 'user_456');
-- 3. Проект по разработке системы управления клиентами (CRM)
INSERT INTO projects (name, project_owner) VALUES ('CRM-система для малого бизнеса', 'user_789');
-- 4. Проект по разработке AI-чат-бота
INSERT INTO projects (name, project_owner) VALUES ('Чат-бот с искусственным интеллектом', 'user_101');
-- 5. Проект по кибербезопасности
INSERT INTO projects (name, project_owner) VALUES ('Защита данных и кибербезопасность', 'user_202');
-- 6. Исследовательский проект в области машинного обучения
INSERT INTO projects (name, project_owner) VALUES ('Исследование машинного обучения', 'user_303');
-- 7. Проект по созданию игровой платформы
INSERT INTO projects (name, project_owner) VALUES ('Платформа для инди-игр', 'user_404');
-- 8. Проект по облачным вычислениям
INSERT INTO projects (name, project_owner) VALUES ('Система облачного хранения данных', 'user_505');
-- 9. Внутренний корпоративный проект
INSERT INTO projects (name, project_owner) VALUES ('Автоматизация бизнес-процессов компании', 'user_606');


-- 1. Владелец проекта "Разработка веб-приложения"
INSERT INTO users_projects (project, username, role) VALUES (1, 'user_123', 'Participant');
-- 2. Владелец проекта "Мобильное приложение для доставки еды"
INSERT INTO users_projects (project, username, role) VALUES (2, 'user_456', 'Participant');
-- 3. Владелец проекта "CRM-система для малого бизнеса"
INSERT INTO users_projects (project, username, role) VALUES (3, 'user_789', 'Participant');
-- 4. Владелец проекта "Чат-бот с искусственным интеллектом"
INSERT INTO users_projects (project, username, role) VALUES (4, 'user_101', 'Participant');
-- 5. Владелец проекта "Защита данных и кибербезопасность"
INSERT INTO users_projects (project, username, role) VALUES (5, 'user_202', 'Participant');
-- 6. Владелец проекта "Исследование машинного обучения"
INSERT INTO users_projects (project, username, role) VALUES (6, 'user_303', 'Participant');
-- 7. Владелец проекта "Платформа для инди-игр"
INSERT INTO users_projects (project, username, role) VALUES (7, 'user_404', 'Participant');
-- 8. Владелец проекта "Система облачного хранения данных"
INSERT INTO users_projects (project, username, role) VALUES (8, 'user_505', 'Participant');
-- 9. Владелец проекта "Автоматизация бизнес-процессов компании"
INSERT INTO users_projects (project, username, role) VALUES (9, 'user_606', 'Participant');


-- 1. Добавляем категории к проекту с ID = 1
INSERT INTO categories (name, project) VALUES ('Frontend', 1);
INSERT INTO categories (name, project) VALUES ('Backend', 1);
INSERT INTO categories (name, project) VALUES ('Database', 1);
-- 2. Добавляем категории к проекту с ID = 2
INSERT INTO categories (name, project) VALUES ('Design', 2);
INSERT INTO categories (name, project) VALUES ('Testing', 2);
-- 3. Добавляем категории к проекту с ID = 3
INSERT INTO categories (name, project) VALUES ('Infrastructure', 3);
INSERT INTO categories (name, project) VALUES ('Security', 3);
-- 4. Добавляем категории без задач (например, для будущих проектов)
INSERT INTO categories (name, project) VALUES ('AI & ML', 4);
INSERT INTO categories (name, project) VALUES ('DevOps', 4);


-- Проект 1: Разработка веб-приложения
INSERT INTO tasks (header, comment, creator, implementer, category, creation_date, execution_date, planned_impl_date, task_status)
VALUES
    ('Создать макет UI', 'Проработать главную страницу', 'user_123', NULL, 1, '2025-02-10 10:00', NULL, '2025-02-20 18:00', 'AWAITING_COMPLETION'),
    ('Разработать backend API', 'CRUD-операции для пользователей', 'user_123', 'user_456', 1, '2025-02-11 12:30', '2025-02-18 16:45', '2025-02-17 12:00', 'COMPLETED'),
    ('Настроить CI/CD', 'Добавить Github Actions', 'user_123', NULL, 1, '2025-02-12 14:00', NULL, NULL, 'WITHOUT_DATE_IMPL'),
    ('Оптимизировать базу данных', 'Добавить индексы', 'user_456', NULL, 1, '2025-02-14 09:15', NULL, '2025-02-25 20:00', 'IN_PROGRESS');
-- Проект 2: Мобильное приложение для доставки еды
INSERT INTO tasks (header, comment, creator, implementer, category, creation_date, execution_date, planned_impl_date, task_status)
VALUES
    ('Создать дизайн главного экрана', 'UI/UX для заказов', 'user_456', NULL, 2, '2025-02-10 08:45', NULL, '2025-02-15 14:00', 'AWAITING_COMPLETION'),
    ('Реализовать карту курьеров', 'Интеграция с Google Maps', 'user_456', 'user_789', 2, '2025-02-11 15:20', '2025-02-17 19:30', '2025-02-16 12:00', 'COMPLETED');
-- Проект 3: CRM-система для малого бизнеса
INSERT INTO tasks (header, comment, creator, implementer, category, creation_date, execution_date, planned_impl_date, task_status)
VALUES
    ('Настроить систему ролей', 'Разграничение доступа по правам', 'user_789', NULL, 3, '2025-02-12 13:40', NULL, '2025-02-22 10:00', 'IN_PROGRESS'),
    ('Добавить функционал отчетов', 'Выгрузка CSV и PDF', 'user_789', NULL, 3, '2025-02-13 10:50', NULL, NULL, 'WITHOUT_DATE_IMPL');
-- Проект 4: Чат-бот с искусственным интеллектом
INSERT INTO tasks (header, comment, creator, implementer, category, creation_date, execution_date, planned_impl_date, task_status)
VALUES
    ('Настроить NLP модель', 'Добавить поддержку диалогов', 'user_101', 'user_202', 4, '2025-02-14 14:20', '2025-02-16 11:30', '2025-02-15 16:00', 'COMPLETED'),
    ('Разработать API бота', 'Взаимодействие с Telegram', 'user_101', NULL, 4, '2025-02-15 09:30', NULL, '2025-02-25 14:00', 'IN_PROGRESS');
-- Проект 5: Защита данных и кибербезопасность
INSERT INTO tasks (header, comment, creator, implementer, category, creation_date, execution_date, planned_impl_date, task_status)
VALUES
    ('Настроить шифрование данных', 'Использовать AES-256', 'user_202', NULL, 5, '2025-02-16 08:30', NULL, '2025-02-28 18:00', 'AWAITING_COMPLETION'),
    ('Проверить уязвимости', 'Запуск тестов на SQL-инъекции', 'user_202', 'user_303', 5, '2025-02-17 15:40', '2025-02-19 17:50', '2025-02-18 12:00', 'COMPLETED');
-- Проект 6: Исследование машинного обучения
INSERT INTO tasks (header, comment, creator, implementer, category, creation_date, execution_date, planned_impl_date, task_status)
VALUES
    ('Обучить модель классификации', 'Dataset MNIST', 'user_303', NULL, 6, '2025-02-18 10:00', NULL, '2025-03-01 09:00', 'IN_PROGRESS'),
    ('Подготовить отчет по модели', 'Графики и метрики', 'user_303', NULL, 6, '2025-02-19 13:20', NULL, NULL, 'WITHOUT_DATE_IMPL');
-- Проект 7: Платформа для инди-игр
INSERT INTO tasks (header, comment, creator, implementer, category, creation_date, execution_date, planned_impl_date, task_status)
VALUES
    ('Создать систему уровней', 'Добавить генерацию карт', 'user_404', NULL, 7, '2025-02-20 09:00', NULL, '2025-03-05 12:00', 'AWAITING_COMPLETION'),
    ('Оптимизировать производительность', 'Использование GPU', 'user_404', 'user_505', 7, '2025-02-21 15:30', '2025-02-24 16:40', '2025-02-23 12:00', 'COMPLETED');
-- Проект 8: Система облачного хранения данных
INSERT INTO tasks (header, comment, creator, implementer, category, creation_date, execution_date, planned_impl_date, task_status)
VALUES
    ('Развернуть кластер серверов', 'Использование Docker', 'user_505', NULL, 8, '2025-02-22 08:15', NULL, '2025-03-10 15:00', 'AWAITING_COMPLETION'),
    ('Добавить резервное копирование', 'Скрипты ежедневного бэкапа', 'user_505', NULL, 8, '2025-02-23 12:50', NULL, NULL, 'WITHOUT_DATE_IMPL');
-- Проект 9: Автоматизация бизнес-процессов компании
INSERT INTO tasks (header, comment, creator, implementer, category, creation_date, execution_date, planned_impl_date, task_status)
VALUES
    ('Настроить интеграцию с 1С', 'Обмен данными по API', 'user_606', 'user_101', 9, '2025-02-24 14:30', '2025-02-27 11:45', '2025-02-26 10:00', 'COMPLETED'),
    ('Создать систему уведомлений', 'E-mail и SMS рассылки', 'user_606', NULL, 9, '2025-02-25 10:10', NULL, '2025-03-03 14:00', 'IN_PROGRESS');

