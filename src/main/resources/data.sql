INSERT INTO level(created_at, name)
VALUES
	(NOW(), 'error'),
	(NOW(), 'warning'),
	(NOW(), 'info');

INSERT INTO origin(created_at, name)
VALUES
	(NOW(), 'system'),
	(NOW(), 'service');

INSERT INTO user_app(login, password)
VALUES ('teste@teste.com', '123456');
