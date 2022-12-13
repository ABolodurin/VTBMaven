SELECT name, subject_name, mark FROM progress
INNER JOIN students ON student_id = fk_student_id
INNER JOIN subjects ON subject_id = fk_subject_id
WHERE mark > 3 AND subject_id = 14;

DELETE FROM students WHERE student_id = 38;

SELECT AVG (mark) FROM progress
WHERE fk_subject_id = 13;

SELECT AVG (mark) FROM progress
WHERE fk_student_id = 34;

SELECT subject_name FROM subjects 
INNER JOIN progress ON subject_id = fk_subject_id
GROUP BY subject_name
ORDER BY COUNT(mark) DESC LIMIT 3;