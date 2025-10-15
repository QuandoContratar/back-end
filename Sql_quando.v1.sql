drop database quando_contratar;
CREATE database quando_contratar;
use quando_contratar;

create table user(
`id_user`int auto_increment primary key,
`name` varchar(100),
`email` varchar(100),
`password` varchar(30),
`area` varchar(20),
`levelAccess` ENUM('1', '2', '3')
);

create table candidate(
`id_candidate` int auto_increment primary key,
`name`varchar(100),
`birth` date,
`phone_number` char(14),
`email`varchar(100),
`state` char(2),
`profile_picture` BLOB,
`education`varchar(500),
`skills`varchar(500),
`resume` MEDIUMBLOB
);

-- CREATE TABLE file_metadata(
-- `file_type` int auto_increment primary key,
-- `file_name` varchar(100),
-- `bucket` enum(),
-- `status`enum(),
-- `action`enum(),
-- `key`varchar(100),
-- `id_user_upload` int,
-- `created_at` date('dd/mm/yyyy')
-- ); 

CREATE TABLE vacancies(
`id_vacancy` int auto_increment primary key,
`position_job` varchar(30),
`period` varchar(10),
`work_model` enum(
    'presencial',
    'remoto',
    'híbrido'),
`requirements` varchar(150),
`contract_type` enum(
'CLT',
'PJ',
'Temporário',
'Estágio',
'Autônomo'),
`salary` decimal(7, 2),
`location` varchar(30),
`opening_justification` varchar(100),
`area` varchar(100),
`fk_manager` int,
FOREIGN KEY (`fk_manager`) REFERENCES user(`id_user`)
);

CREATE TABLE selection_process (
  `id` int auto_increment primary key,
  `progress` decimal(5,2),
  `current_stage` enum (
    'aguardando_triagem', 
    'triagem_inicial', 
    'avaliacao_fit_cultural',
    'teste_tecnico', 
    'entrevista_tecnica', 
    'entrevista_final', 
    'proposta_fechamento',
    'contratacao'
  ),
  `outcome` enum ('aprovado', 'reprovado'),
  `created` datetime, 
  `fk_candidate` int,
  `fk_recruiter` int,
  `fk_vacancy` int,
  FOREIGN KEY (fk_candidate) REFERENCES candidate(id_candidate),
  FOREIGN KEY (fk_recruiter) REFERENCES user(id_user),
  FOREIGN KEY (fk_vacancy) REFERENCES vacancies(id_vacancy)
);


select * from candidate;

INSERT INTO candidate (name, birth, phone_number, email, state, education, skills)
VALUES
('João da Silva', '1998-06-15', '(11)91234-5678', 'joao.silva@example.com', 'SP', 'Bacharel em Ciência da Computação', 'Java, Spring, MySQL'),

('Maria Oliveira', '1995-04-22', '(21)92345-6789', 'maria.oliveira@example.com', 'RJ', 'Engenharia de Software', 'Python, Django, PostgreSQL'),

('Carlos Souza', '2000-10-10', '(31)93456-7890', 'carlos.souza@example.com', 'MG', 'Sistemas de Informação', 'JavaScript, React, Node.js'),

('Ana Lima', '1992-12-05', '(47)94567-8901', 'ana.lima@example.com', 'SC', 'Análise e Desenvolvimento de Sistemas', 'C#, .NET, SQL Server'),

('Lucas Pereira', '1999-08-30', '(41)95678-9012', 'lucas.pereira@example.com', 'PR', 'Ciência da Computação', 'Kotlin, Android, Firebase');

INSERT INTO user (name, email, password, area, levelAccess)
VALUES
('Carlos Manager', 'cmanager@example.com', 'pass123', 'TI', '3'),
('Ana Recruiter', 'arecruiter@example.com', 'pass456', 'RH', '2'),
('Paulo Admin', 'admin@example.com', 'adminpass', 'TI', '1');

-- Inserts para vacancies
INSERT INTO vacancies (position_job, period, work_model, requirements, contract_type, salary, location, opening_justification, area, fk_manager)
VALUES
('Desenvolvedor Java', 'Full-time', 'remoto', 'Experiência com Java e Spring', 'CLT', 8000.00, 'São Paulo', 'Nova vaga para projeto X', 'Tecnologia', 1),
('Analista de Dados', 'Part-time', 'presencial', 'Conhecimento em Python e SQL', 'PJ', 5000.00, 'Rio de Janeiro', 'Crescimento da área', 'Tecnologia', 1);

-- Inserts para selection_process
INSERT INTO selection_process (progress, current_stage, outcome, created, fk_candidate, fk_recruiter, fk_vacancy)
VALUES
(30.00, 'triagem_inicial', 'aprovado', '2025-10-10 09:30:00', 1, 2, 1),
(70.00, 'entrevista_tecnica', 'aprovado', '2025-10-12 14:00:00', 2, 2, 2),
(90.00, 'proposta_fechamento', 'aprovado', '2025-10-13 10:00:00', 3, 2, 1),
(50.00, 'teste_tecnico', 'reprovado', '2025-10-11 11:00:00', 4, 2, 2),
(100.00, 'contratação', 'aprovado', '2025-10-14 15:00:00', 5, 2, 1);


select * from candidate;
select * from selection_process;




