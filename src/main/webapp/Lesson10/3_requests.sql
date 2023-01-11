SELECT stu.student_name AS name, pro.mark
FROM progress AS pro
INNER JOIN students AS stu
ON stu.student_id = pro.student_id
INNER JOIN subjects AS subj
ON subj.subject_id = pro.subject_id
WHERE mark > 3 AND subj.subject_name = 'English';

DELETE FROM students WHERE student_id = 7;

SELECT AVG (mark) FROM progress
WHERE subject_id = 3;

SELECT AVG (mark) FROM progress
WHERE student_id = 3;

SELECT subj.subject_name AS subject
FROM subjects AS subj
INNER JOIN progress AS pro
ON subj.subject_id = pro.subject_id
GROUP BY subject
ORDER BY COUNT(DISTINCT (student_id, pro.subject_id)) DESC LIMIT 3;