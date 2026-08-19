USE UniversityDB;
GO

-- ============================================
-- LAB 04 - CRUD OPERATIONS
-- ============================================

-- ============================================
-- 1. INSERT DATA
-- ============================================

-- Course
INSERT INTO Course (CID, Cname, C_Description, C_fee)
VALUES
('IT', 'Information Technology',
 'The programme is designed for technically focused students who capabilities in programming', 175000),

('SE', 'Software Engineering',
 'Software engineering is the discipline of designing, creating and maintaining', 185000),

('CSNE', 'Computer Systems And Network Engineering',
 'The programme aims to provide students with the knowledge, skills, planning, and designing', 155000),

('DS', 'Data Science',
 'The meticulous curriculum focuses on the fundamentals of computer science, statistics, and applied mathematics', 170000);
GO


-- Student
INSERT INTO Student
(SID, Sname, Address, dob, NIC, CID)
VALUES
('CN18384756', 'Kamal', 'No122, Rose street, matale',
 '1994-05-02', '946785467v', 'CSNE'),

('DS18234876', 'Pubudu', 'No678, 3rd new lane, Maharahgama',
 '1994-11-08', '948763759v', 'DS'),

('IT18234568', 'Ann', 'No12, Kings street, colombo',
 '1996-11-11', '961234587v', 'IT'),

('SE19238567', 'Malith', 'No08, st.thomas street, Kandy',
 '1992-12-20', '922356785v', 'SE');
GO


-- Module
INSERT INTO Module
(Mcode, Mname, M_Description, NoOfCredits)
VALUES
('SE3050', 'User Experience Engineering', 'subject under SE', 3),
('IT1010', 'Introduction to Programming', 'subject under IT', 4),
('IT2050', 'Computer Networks', 'subject under IT', 4),
('IT3051', 'Fundamentals of Data Mining', 'subject under DS', 4);
GO


-- Offers
INSERT INTO Offers
(CID, Mcode, Accadamic_year, Semester)
VALUES
('SE', 'SE3050', 'Y3', 2),
('IT', 'IT1010', 'Y1', 2),
('IT', 'IT2050', 'Y2', 1),
('DS', 'IT3051', 'Y3', 2);
GO


-- ============================================
-- 2. VERIFY INSERTED DATA
-- ============================================

SELECT * FROM Course;
SELECT * FROM Student;
SELECT * FROM Module;
SELECT * FROM Offers;
GO


-- ============================================
-- 3. UPDATE OPERATION
-- Update Ann's address
-- ============================================

UPDATE Student
SET Address = 'No50, New Road, Colombo'
WHERE SID = 'IT18234568';
GO

SELECT *
FROM Student
WHERE SID = 'IT18234568';
GO


-- ============================================
-- 4. DELETE OPERATION
-- Remove User Experience Engineering module
-- ============================================

-- First remove the related Offers record
-- because SE3050 is referenced by Offers

DELETE FROM Offers
WHERE Mcode = 'SE3050';
GO

-- Then delete the module

DELETE FROM Module
WHERE Mcode = 'SE3050';
GO


-- ============================================
-- 5. VERIFY DELETE
-- ============================================

SELECT *
FROM Module
WHERE Mcode = 'SE3050';

SELECT *
FROM Offers
WHERE Mcode = 'SE3050';
GO