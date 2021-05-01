INSERT INTO level(created_at, name)
VALUES
	(NOW(), 'error'),
	(NOW(), 'warning'),
	(NOW(), 'info');

INSERT INTO origin(created_at, name)
VALUES
	(NOW(), 'system'),
	(NOW(), 'service');
