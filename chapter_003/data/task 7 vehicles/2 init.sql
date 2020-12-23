INSERT INTO body(name) VALUES('body 1'), ('body 2'), ('body 3'), ('body 4'), ('body 5'), ('body 6');
INSERT INTO gear(name) VALUES('gear 1'), ('gear 2'), ('gear 3'), ('gear 4'), ('gear 5'), ('gear 6');
INSERT INTO engine(name) VALUES('engine 1'), ('engine 2'), ('engine 3'), ('engine 4'), ('engine 5'), ('engine 6');
INSERT INTO car(name, body_id, gear_id, engine_id) VALUES 
('car 1', 1, 24, 19),
('car 1', 1, 19, 21),
('car 2', 1, 21, 20),
('car 3', 4, 22, 24),
('car 4', 1, 20, 19),
('car 5', 1, 19, 24);