INSERT INTO rules(name) values ('admin');
INSERT INTO rules(name) values ('read');
INSERT INTO rules(name) values ('print');
INSERT INTO rules(name) values ('create');
INSERT INTO rules(name) values ('delete');

INSERT INTO role(name) values ('admin');
INSERT INTO role(name) values ('user');
INSERT INTO role(name) values ('glavbuh');
INSERT INTO role(name) values ('buh');
INSERT INTO role(name) values ('manager');

INSERT INTO role_rules(role_id, rules_id) values (1, 1);
INSERT INTO role_rules(role_id, rules_id) values (1, 2);
INSERT INTO role_rules(role_id, rules_id) values (1, 3);
INSERT INTO role_rules(role_id, rules_id) values (1, 4);
INSERT INTO role_rules(role_id, rules_id) values (1, 5);

INSERT INTO role_rules(role_id, rules_id) values (2, 2);

INSERT INTO role_rules(role_id, rules_id) values (3, 2);
INSERT INTO role_rules(role_id, rules_id) values (3, 3);
INSERT INTO role_rules(role_id, rules_id) values (3, 4);
INSERT INTO role_rules(role_id, rules_id) values (3, 5);

INSERT INTO role_rules(role_id, rules_id) values (4, 2);
INSERT INTO role_rules(role_id, rules_id) values (4, 3);
INSERT INTO role_rules(role_id, rules_id) values (4, 4);

INSERT INTO role_rules(role_id, rules_id) values (5, 2);
INSERT INTO role_rules(role_id, rules_id) values (5, 3);

INSERT INTO "user"(name, role_id) values ('admin', 1);
INSERT INTO "user"(name, role_id) values ('glavbuh', 3);
INSERT INTO "user"(name, role_id) values ('buh1', 4);
INSERT INTO "user"(name, role_id) values ('buh2', 4);
INSERT INTO "user"(name, role_id) values ('buh3', 4);
INSERT INTO "user"(name, role_id) values ('manager1', 5);