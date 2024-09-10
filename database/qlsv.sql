create database universitymanagement;

use universitymanagement;

create table class(
	class_id varchar(20) not null,
    primary key(class_id)
);

create table student(
	student_id varchar(20) not null,
    first_name nvarchar(50) not null,
    last_name nvarchar(50) not null,
    dob date not null,
    gender nvarchar(10) not null,
    major nvarchar(50) not null,
    email varchar(50) not null,
    address nvarchar(50) not null,
    phone nvarchar(20) not null,
    class_id varchar(20), -- Added class_id field
    primary key(student_id),
    foreign key(class_id) references class(class_id)
);

create table teacher(
	teacher_id varchar(20) not null,
    first_name nvarchar(50) not null,
    last_name nvarchar(50) not null,
    dob date not null,
    gender nvarchar(10) not null,
    department nvarchar(50),
    degree nvarchar(50),
    email varchar(50) not null,
    address nvarchar(50) not null,
    phone nvarchar(20) not null,
    primary key(teacher_id)
);

create table subject(
	subject_id varchar(20) not null,
    subject_name nvarchar(50) not null,
    credits int not null,
    semester nvarchar(30),
    academic_year varchar(30),
    teacher_id varchar(20), -- Added teacher_id field
    primary key(subject_id),
    foreign key(teacher_id) references teacher(teacher_id)
);

create table score(
	score double,
    student_id varchar(20), -- Added student_id field
    subject_id varchar(20), -- Corrected reference to subject table
    foreign key(student_id) references student(student_id),
    foreign key(subject_id) references subject(subject_id)
);

create table register(
	register_code varchar(20) not null,
    student_id varchar(20), -- Added student_id field
    subject_id varchar(20), -- Corrected reference to subject table
    foreign key(student_id) references student(student_id),
    foreign key(subject_id) references subject(subject_id)
);

INSERT INTO class (class_id) VALUES
('AT17A'), ('AT17B'), ('AT17C'), ('AT17D'), ('AT17E'),
('CT5A'), ('CT5B'), ('CT5C'),
('DT4A'), ('DT4B'), ('DT4C');
INSERT INTO student (student_id, first_name, last_name, dob, gender, major, email, address, phone, class_id) VALUES
-- Công nghệ thông tin (CT05****)
('CT050001', 'Nguyễn', 'Văn A', '2000-05-12', 'Nam', 'Công nghệ thông tin', 'vana@example.com', '123 Nguyễn Trãi, Hà Nội', '0987654321', 'CT5A'),
('CT050002', 'Trần', 'Thị B', '1999-09-21', 'Nữ', 'Công nghệ thông tin', 'thib@example.com', '456 Lê Lợi, TP. Hồ Chí Minh', '0976543210', 'CT5B'),
('CT050003', 'Lê', 'Văn C', '2001-01-05', 'Nam', 'Công nghệ thông tin', 'vanc@example.com', '789 Phạm Ngũ Lão, Đà Nẵng', '0987654323', 'CT5C'),

-- An toàn thông tin (AT17****)
('AT170001', 'Phạm', 'Thị D', '2000-11-23', 'Nữ', 'An toàn thông tin', 'thid@example.com', '12 Trần Hưng Đạo, Hải Phòng', '0987654324', 'AT17A'),
('AT170002', 'Hoàng', 'Văn E', '1999-06-15', 'Nam', 'An toàn thông tin', 'vane@example.com', '345 Hùng Vương, Đà Nẵng', '0987654325', 'AT17B'),

-- Điện tử Viễn Thông (DT04****)
('DT040001', 'Đặng', 'Thị F', '1998-12-30', 'Nữ', 'Điện tử Viễn Thông', 'thif@example.com', '67 Bà Triệu, Hà Nội', '0987654326', 'DT4A'),
('DT040002', 'Vũ', 'Văn G', '1997-08-14', 'Nam', 'Điện tử Viễn Thông', 'vang@example.com', '89 Trần Phú, TP. Hồ Chí Minh', '0987654327', 'DT4B');
INSERT INTO teacher (teacher_id, first_name, last_name, dob, gender, department, degree, email, address, phone) VALUES
-- Công nghệ thông tin
('GV001', 'Nguyễn', 'Thị Hoa', '1980-03-10', 'Nữ', 'Information Technology', 'Doctor', 'thie@example.com', '10 Lý Thường Kiệt, Hà Nội', '0988123456'),
-- An toàn thông tin
('GV002', 'Trần', 'Văn Chiến', '1975-07-19', 'Nam', 'Information Securityn', 'Master', 'vanf@example.com', '15 Nguyễn Huệ, TP. Hồ Chí Minh', '0987234567'),
-- Điện tử Viễn Thông
('GV003', 'Lê', 'Thị Giang', '1982-12-02', 'Nữ', 'Electronics and Telecommunication', 'Bachelor', 'thig@example.com', '30 Bạch Đằng, Đà Nẵng', '0987345678');
INSERT INTO teacher (teacher_id, first_name, last_name, dob, gender, department, degree, email, address, phone) VALUES
('GV004', 'Phạm', 'Văn Hoàng', '1981-05-14', 'Male', 'Information Technology', 'Master', 'hoangp@example.com', '12 Trần Phú, Hà Nội', '0987456789'),
('GV005', 'Đỗ', 'Thị Mai', '1983-08-21', 'Female', 'Information Technology', 'Doctor', 'maid@example.com', '23 Trần Hưng Đạo, Hà Nội', '0987567890'),
('GV006', 'Nguyễn', 'Anh Dũng', '1985-11-11', 'Male', 'Information Technology', 'Doctor', 'dungn@example.com', '45 Lý Nam Đế, Hà Nội', '0987678901'),
('GV007', 'Trần', 'Minh Quân', '1979-04-04', 'Male', 'Information Security', 'Master', 'quanm@example.com', '18 Võ Văn Tần, TP. Hồ Chí Minh', '0987789012'),
('GV008', 'Ngô', 'Thị Hạnh', '1987-06-16', 'Female', 'Information Security', 'Master', 'hanhn@example.com', '28 Phạm Ngọc Thạch, TP. Hồ Chí Minh', '0987890123'),
('GV009', 'Lê', 'Thanh Hải', '1982-09-23', 'Male', 'Information Security', 'Bachelor', 'hail@example.com', '14 Hoàng Diệu, Đà Nẵng', '0987901234'),
('GV010', 'Bùi', 'Tiến Mạnh', '1980-02-14', 'Female', 'Electronics and Telecommunication', 'Doctor', 'manhb@example.com', '22 Nguyễn Tri Phương, Đà Nẵng', '0987012345'),
('GV011', 'Vũ', 'Hữu Lợi', '1986-07-07', 'Male', 'Electronics and Telecommunication', 'Master', 'loiv@example.com', '33 Lê Lợi, Huế', '0987123456'),
('GV012', 'Hoàng', 'Thị Lan', '1984-10-20', 'Female', 'Electronics and Telecommunication', 'Bachelor', 'lanh@example.com', '17 Hai Bà Trưng, Huế', '0987234567'),
('GV013', 'Lê', 'Quang Hùng', '1978-12-18', 'Male', 'Information Technology', 'Master', 'hungl@example.com', '20 Võ Thị Sáu, Hà Nội', '0987345678'),
('GV014', 'Trần', 'Thị Ngọc', '1983-09-25', 'Female', 'Information Technology', 'Bachelor', 'ngoct@example.com', '35 Nguyễn Đình Chiểu, Hà Nội', '0987456789'),
('GV015', 'Đinh', 'Văn Phong', '1980-11-29', 'Male', 'Information Technology', 'Doctor', 'phongd@example.com', '5 Hùng Vương, Đà Nẵng', '0987567890'),
('GV016', 'Phan', 'Văn Khôi', '1985-01-10', 'Male', 'Information Security', 'Doctor', 'khoip@example.com', '10 Trường Chinh, TP. Hồ Chí Minh', '0987678901'),
('GV017', 'Nguyễn', 'Thị Quỳnh', '1982-05-05', 'Female', 'Information Security', 'Master', 'quynhn@example.com', '7 Pasteur, Đà Nẵng', '0987789012'),
('GV018', 'Phạm', 'Minh Tuấn', '1979-07-22', 'Male', 'Electronics and Telecommunication', 'Master', 'tuanp@example.com', '21 Nguyễn Văn Linh, TP. Hồ Chí Minh', '0987890123');


INSERT INTO subject (subject_id, subject_name, credits, semester, academic_year, teacher_id) VALUES
('MH001', 'Lập trình Java', 3, 'Học kỳ 1', '2023-2024', 'GV001'),
('MH002', 'Cấu trúc dữ liệu và giải thuật', 4, 'Học kỳ 1', '2023-2024', 'GV001'),
('MH003', 'Mạng máy tính', 3, 'Học kỳ 2', '2023-2024', 'GV002'),
('MH004', 'Điện tử cơ bản', 3, 'Học kỳ 2', '2023-2024', 'GV003');

INSERT INTO subject (subject_id, subject_name, credits, semester, academic_year, teacher_id) VALUES
('MH005', 'Lập trình C++', 3, 'Học kỳ 1', '2022-2023', 'GV004'),
('MH006', 'Thiết kế Web', 4, 'Học kỳ 1', '2022-2023', 'GV005'),
('MH007', 'Cơ sở dữ liệu', 3, 'Học kỳ 2', '2022-2023', 'GV006'),
('MH008', 'Phân tích thiết kế hệ thống', 4, 'Học kỳ 2', '2023-2024', 'GV007'),
('MH009', 'An ninh mạng', 3, 'Học kỳ 1', '2021-2022', 'GV008'),
('MH010', 'Lập trình di động', 4, 'Học kỳ 1', '2021-2022', 'GV009'),
('MH011', 'Kỹ thuật số', 3, 'Học kỳ 2', '2022-2023', 'GV010'),
('MH012', 'Vi xử lý', 4, 'Học kỳ 2', '2021-2022', 'GV011'),
('MH013', 'Hệ điều hành', 3, 'Học kỳ 1', '2023-2024', 'GV012'),
('MH014', 'Lập trình Python', 4, 'Học kỳ 1', '2023-2024', 'GV013'),
('MH015', 'Trí tuệ nhân tạo', 3, 'Học kỳ 2', '2022-2023', 'GV014'),
('MH016', 'Phát triển phần mềm', 4, 'Học kỳ 2', '2022-2023', 'GV015'),
('MH017', 'Học máy', 4, 'Học kỳ 1', '2023-2024', 'GV016'),
('MH018', 'Điện tử số', 3, 'Học kỳ 1', '2021-2022', 'GV017'),
('MH019', 'Lập trình Android', 4, 'Học kỳ 2', '2021-2022', 'GV018');

INSERT INTO score (score, student_id, subject_id) VALUES
(8.5, 'CT050001', 'MH001'),
(7.0, 'CT050002', 'MH002'),
(9.0, 'AT170001', 'MH003'),
(6.5, 'DT040001', 'MH004');
INSERT INTO score (score, student_id, subject_id) VALUES
(7.5, 'CT050003', 'MH005'),
(8.0, 'AT170002', 'MH006'),
(7.2, 'CT050001', 'MH007'),
(6.8, 'CT050002', 'MH008'),
(9.5, 'DT040002', 'MH009'),
(7.3, 'DT040001', 'MH010'),
(8.1, 'CT050003', 'MH011'),
(6.9, 'AT170001', 'MH012'),
(8.6, 'AT170002', 'MH013'),
(7.7, 'DT040001', 'MH014'),
(9.0, 'CT050001', 'MH015'),
(6.7, 'CT050002', 'MH016'),
(8.8, 'CT050003', 'MH017'),
(7.4, 'AT170001', 'MH018'),
(6.6, 'DT040002', 'MH019'),
(9.1, 'CT050003', 'MH001'),
(7.8, 'CT050001', 'MH002'),
(8.0, 'AT170002', 'MH003'),
(6.9, 'DT040001', 'MH004'),
(8.7, 'CT050002', 'MH005'),
(7.1, 'CT050003', 'MH006'),
(7.9, 'AT170001', 'MH007'),
(8.2, 'CT050001', 'MH008'),
(6.7, 'DT040002', 'MH009'),
(9.2, 'DT040001', 'MH010');

INSERT INTO register (register_code, student_id, subject_id) VALUES
('DK001', 'CT050001', 'MH001'),
('DK002', 'CT050002', 'MH002'),
('DK003', 'AT170001', 'MH003'),
('DK004', 'DT040001', 'MH004');

create table login(
	username varchar(30),
    password varchar(30)
);

select * from student;
select * from class;
select * from teacher;
select * from subject;
select * from login;
select * from score;

insert into login(username, password) value ("admin", "123456")




