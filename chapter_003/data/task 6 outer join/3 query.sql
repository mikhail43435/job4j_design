-- ��������� ������� � left, rigth, full, cross ������������
SELECT * FROM emploees e LEFT OUTER JOIN departments d ON e.departments_id=d.id;
SELECT * FROM departments d RIGHT OUTER JOIN emploees e ON e.departments_id=d.id;
SELECT * FROM emploees e FULL JOIN departments d ON e.departments_id=d.id;
SELECT * FROM emploees e CROSS JOIN departments d;

-- ��������� left join ����� ����������, ������� �� ��������� �� � ���� �� �������������
SELECT * FROM emploees e LEFT OUTER JOIN departments d ON e.departments_id=d.id WHERE d IS NULL;

-- ��������� left � right join �������� �������, ������� ������ �� ���������� ���������. 
SELECT * FROM emploees e LEFT OUTER JOIN departments d ON e.departments_id=d.id;
SELECT e.id, e.name, e.departments_id, d.id, d.name FROM departments d RIGHT OUTER JOIN emploees e ON e.departments_id=d.id;

-- ������� ������� teens � ���������� name, gender � ��������� ��. ��������� cross join ��������� ��� ��������� ���������� ����
create table teens (
    id serial primary key,
    name varchar(255),
    gender char(3)
);
INSERT INTO teens(name, gender) VALUES('F', 'f');
INSERT INTO teens(name, gender) VALUES('Z', 'f');
INSERT INTO teens(name, gender) VALUES('Q', 'f');
INSERT INTO teens(name, gender) VALUES('P', 'f');
INSERT INTO teens(name, gender) VALUES('D', 'm');
INSERT INTO teens(name, gender) VALUES('R', 'm');
INSERT INTO teens(name, gender) VALUES('E', 'm');
INSERT INTO teens(name, gender) VALUES('H', 'bs');
INSERT INTO teens(name, gender) VALUES('R', 'bs');

SELECT t.name, n.name, (t.gender||'-'||n.gender) AS "g-g" 
FROM teens t 
CROSS JOIN teens n 
WHERE t.gender<>n.gender;