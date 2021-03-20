INSERT INTO file (id, name, date) VALUES (1, 'file 1', now());
INSERT INTO file (id, name, date) VALUES (2, 'file 2', now());

INSERT INTO field (file_id, name, value_txt) VALUES (1, 'name', 'Foonyor');
INSERT INTO field (file_id, name, value_bool) VALUES (1, 'valid', true);
INSERT INTO field (file_id, name, value_int) VALUES (1, 'count', 1);