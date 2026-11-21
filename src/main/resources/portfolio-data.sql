INSERT INTO student (id, name, roll_number, email, school_name, dob, gender)
VALUES ('portfolio-1', 'Alex Demo', 'DEMO-101', 'alex@example.invalid', 'Portfolio Demo School', DATE '2005-01-15', 'Not specified');

INSERT INTO mark (subject, marks, max_marks, remark, student_id)
VALUES ('Mathematics', 91, 100, 'Excellent', 'portfolio-1'),
       ('Computer Science', 96, 100, 'Excellent', 'portfolio-1'),
       ('English', 85, 100, 'Very good', 'portfolio-1');
