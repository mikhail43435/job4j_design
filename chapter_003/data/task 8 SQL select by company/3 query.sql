-- � ����� ������� ��������
-- ����� ���� person, ������� �� ������� � �������� � id = 5;
-- �������� �������� ��� ������� ��������.
SELECT 
p.name AS "��� ����������", c.name AS "�������� ��������", c.id AS "ID ��������"
FROM person p
JOIN company c 
ON p.company_id=c.id
WHERE p.company_id<>5;

-- ���������� ������� �������� �������� 
-- � ������������ ����������� �������
-- + ���������� ������� � ���� ��������.

-- ���������� ������� �������� ��������
-- � ������������ ����������� �������
-- + ���������� ������� � ���� ��������.
SELECT c.name, COUNT(c.name)
FROM company c
JOIN person p  ON c.id=p.company_id
GROUP BY c.id
HAVING (COUNT(c.name)) = (
    SELECT MAX(count)
    FROM
	    (SELECT c.name, COUNT(c.name)
	    FROM company c JOIN person p ON c.id=p.company_id
	    GROUP BY c.id) AS second_table);