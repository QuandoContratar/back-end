-- ========================================
-- SCRIPT DE CRIAÇÃO DO BANCO DE DADOS
-- Sistema: Quando Contratar
-- ========================================

-- DROP DATABASE IF EXISTS quando_contratar;
-- CREATE DATABASE quando_contratar;
USE quando_contratar;

-- ========================================
-- TABELA: user (Usuários do sistema)
-- ========================================

CREATE TABLE IF NOT EXISTS user (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    area VARCHAR(50),
    level_access ENUM('ADMIN','HR','MANAGER') DEFAULT 'MANAGER' COMMENT 'Níveis de acesso do usuário'
);


-- ========================================
-- TABELA: candidate (Candidatos)
-- ========================================
CREATE TABLE IF NOT EXISTS candidate (
    id_candidate INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birth DATE,
    phone_number CHAR(14),
    email VARCHAR(100) NOT NULL UNIQUE,
    state CHAR(2),
    profile_picture BLOB,
    education VARCHAR(500),
    skills VARCHAR(500),
    experience TEXT,
    resume MEDIUMBLOB,
    path_resume VARCHAR(100),
	current_stage VARCHAR(50) DEFAULT 'aguardando_triagem',
    status VARCHAR(20) DEFAULT 'ativo',
    rejection_reason VARCHAR(500),
    vacancy_id BIGINT
);

-- ========================================
-- TABELA: vacancies (Vagas)
-- ========================================
CREATE TABLE IF NOT EXISTS vacancies (
    id_vacancy INT AUTO_INCREMENT PRIMARY KEY,
    position_job VARCHAR(100) NOT NULL,
    period VARCHAR(20),
    work_model ENUM('presencial', 'remoto', 'híbrido') DEFAULT 'presencial',
    requirements TEXT,
    contract_type ENUM('CLT', 'PJ', 'Temporário', 'Estágio', 'Autônomo') DEFAULT 'CLT',
    salary DECIMAL(10, 2),
    location VARCHAR(100),
    opening_justification VARCHAR(255),
    area VARCHAR(100),
    status_vacancy VARCHAR(50) DEFAULT 'pendente aprovação',
    fk_manager INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_manager) REFERENCES user(id_user) ON DELETE SET NULL
);

-- ========================================
-- TABELA: opening_requests (Solicitações de Abertura de Vaga)
-- IMPORTANTE: Esta tabela é usada pelo frontend para criar solicitações
-- ========================================
CREATE TABLE IF NOT EXISTS opening_requests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cargo VARCHAR(100) NOT NULL,
    periodo VARCHAR(50) NOT NULL,
    modelo_trabalho VARCHAR(50) NOT NULL,
    regime_contratacao VARCHAR(50) NOT NULL,
    salario DECIMAL(10, 2) NOT NULL,
    localidade VARCHAR(100) NOT NULL,
    requisitos TEXT,
    justificativa_path VARCHAR(255),
    gestor_id INT NOT NULL,
    area VARCHAR(100) NOT NULL,
    status ENUM('ENTRADA', 'ABERTA', 'APROVADA', 'REJEITADA', 'CANCELADA') DEFAULT 'ENTRADA',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (gestor_id) REFERENCES user(id_user) ON DELETE CASCADE,
    INDEX idx_gestor_id (gestor_id),
    INDEX idx_status (status)
);

CREATE TABLE IF NOT EXISTS candidate_profile (
    id_profile INT AUTO_INCREMENT PRIMARY KEY,
    fk_candidate INT NOT NULL,
    raw_json JSON NOT NULL,

    total_experience_years DECIMAL(4,1),
    main_seniority ENUM('JUNIOR','PLENO','SENIOR','LEAD'),
    main_stack VARCHAR(100),
    main_role VARCHAR(100),

    city VARCHAR(100),
    state CHAR(2),
    remote_preference VARCHAR(20),

    hard_skills TEXT,
    soft_skills TEXT,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (fk_candidate) REFERENCES candidate(id_candidate) ON DELETE CASCADE
);


-- ========================================
-- KANBAN – ESTÁGIOS
-- ========================================

CREATE TABLE IF NOT EXISTS kanban_stage (
    id_stage INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    position_order INT NOT NULL
);

INSERT INTO kanban_stage (name, position_order) VALUES
('aguardando_triagem',1),
('triagem', 2),
('entrevista_rh',3),
('avaliacao_fit_cultural', 4),
('teste_tecnico', 5),
('entrevista_tecnica', 6),
('entrevista_final', 7),
('proposta_fechamento', 8),
('contratacao', 9),
('rejeitados', 10);


-- ========================================
-- KANBAN – CARDS
-- ========================================

CREATE TABLE IF NOT EXISTS kanban_card (
    id_card INT AUTO_INCREMENT PRIMARY KEY,
    fk_candidate INT NOT NULL,
    fk_vacancy INT NOT NULL,
    fk_stage INT NOT NULL,
    match_level ENUM('BAIXO','MEDIO','ALTO','DESTAQUE') DEFAULT 'MEDIO',
    rejection_reason VARCHAR(500),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_candidate) REFERENCES candidate(id_candidate) ON DELETE CASCADE,
    FOREIGN KEY (fk_vacancy) REFERENCES vacancies(id_vacancy) ON DELETE CASCADE,
    FOREIGN KEY (fk_stage) REFERENCES kanban_stage(id_stage) ON DELETE CASCADE
);

-- ========================================
-- TABELA: selection_process (Processo Seletivo)
-- ========================================
CREATE TABLE IF NOT EXISTS selection_process (
    `id_selection` INT AUTO_INCREMENT PRIMARY KEY,
    `progress` DECIMAL(5, 2) DEFAULT 0.00,
    `current_stage` ENUM (
        'aguardando_triagem',
        'triagem',
		'entrevista_rh',
        'avaliacao_fit_cultural',
        'teste_tecnico',
        'entrevista_tecnica',
        'entrevista_final',
        'proposta_fechamento',
        'contratacao'
    ) DEFAULT 'aguardando_triagem',
    `outcome` ENUM('aprovado', 'reprovado', 'pendente') DEFAULT 'pendente',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `fk_candidate` INT NOT NULL,
    `fk_recruiter` INT,
    `fk_vacancy` INT NOT NULL,
    FOREIGN KEY (`fk_candidate`) REFERENCES candidate(`id_candidate`) ON DELETE CASCADE,
    FOREIGN KEY (`fk_recruiter`) REFERENCES user(`id_user`) ON DELETE SET NULL,
    FOREIGN KEY (`fk_vacancy`) REFERENCES vacancies(`id_vacancy`) ON DELETE CASCADE,
    INDEX idx_candidate (`fk_candidate`),
    INDEX idx_vacancy (`fk_vacancy`)
);

-- ========================================
-- TABELA: candidate_match (Match de Candidatos com Vagas)
-- ========================================
CREATE TABLE IF NOT EXISTS candidate_match (
    id_match INT AUTO_INCREMENT PRIMARY KEY,
    fk_candidate INT NOT NULL,
    fk_vacancy INT NOT NULL,
    score DECIMAL(5,2) NOT NULL,
    match_level ENUM('BAIXO','MEDIO','ALTO','DESTAQUE') NOT NULL,
    status ENUM('PENDING','ACCEPTED','REJECTED') DEFAULT 'PENDING',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_candidate) REFERENCES candidate(id_candidate) ON DELETE CASCADE,
    FOREIGN KEY (fk_vacancy) REFERENCES vacancies(id_vacancy) ON DELETE CASCADE,
    UNIQUE KEY unique_match (fk_candidate, fk_vacancy)
);

INSERT INTO user (name, email, password, area, level_access) VALUES
('Carlos Manager', 'carlos@empresa.com', '123', 'TI', 'MANAGER'),
('Ana Gestora', 'ana@empresa.com', '123', 'Marketing', 'MANAGER'),
('João RH', 'joao.rh@empresa.com', '123', 'RH', 'HR'),
('Admin Master', 'admin@empresa.com', '123', 'Diretoria', 'ADMIN');


INSERT INTO vacancies (position_job, period, work_model, requirements, contract_type, salary, location, opening_justification, area, status_vacancy, fk_manager)
VALUES
('Desenvolvedor Java JR', 'Diurno', 'remoto', 'Java, Spring Boot, MySQL', 'CLT', 4500.00, 'São Paulo - SP', 'Aumento de time', 'TI', 'aberta', 1),
('Analista de Dados JR', 'Diurno', 'presencial', 'SQL, Python, Power BI', 'CLT', 4000.00, 'São Paulo - SP', 'Nova demanda BI', 'TI', 'aberta', 1),
('Designer UX', 'Integral', 'híbrido', 'Figma, UX Research', 'CLT', 5000.00, 'São Paulo - SP', 'Reposição', 'Marketing', 'pendente aprovação', 2),
('Analista de Marketing', 'Diurno', 'híbrido', 'SEO, Social Media', 'CLT', 3800.00, 'São Paulo - SP', 'Expansão', 'Marketing', 'aberta', 2),
('Assistente RH JR', 'Diurno', 'presencial', 'Pacote Office, Comunicação', 'CLT', 2500.00, 'São Paulo - SP', 'Suporte ao RH', 'RH', 'aberta', 3);


INSERT INTO candidate (name, birth, phone_number, email, state, education, skills, experience)
VALUES
('Lucas Ribeiro', '1999-05-12', '(11)90000-0001', 'lucas@gmail.com', 'SP', 'Bacharel em SI', 'Java, Spring', '1 ano como dev'),
('Mariana Santos', '1998-08-22', '(11)90000-0002', 'mariana@gmail.com', 'SP', 'ADS', 'Python, SQL', 'Projetos acadêmicos'),
('Pedro Lima', '2000-02-10', '(11)90000-0003', 'pedro@gmail.com', 'RJ', 'Design Digital', 'Figma, UX', 'Freelancer'),
('Aline Souza', '1997-07-30', '(11)90000-0004', 'aline@gmail.com', 'SP', 'Marketing', 'SEO, Social Media', '2 anos exp'),
('João Martins', '2001-03-15', '(11)90000-0005', 'joaomartins@gmail.com', 'SP', 'Administração', 'Excel, Comunicação', 'Estágio RH'),
('Carla Ferreira', '1999-11-05', '(11)90000-0006', 'carla@gmail.com', 'SP', 'Eng. Computação', 'Java, SQL, AWS', 'Projetos pessoais');


INSERT INTO candidate_profile (fk_candidate, raw_json, total_experience_years, main_seniority, main_stack, main_role, city, state, remote_preference, hard_skills, soft_skills)
VALUES
(1, '{}', 1.0, 'JUNIOR', 'Java', 'Backend', 'São Paulo', 'SP', 'remoto', 'Java, Spring', 'Comunicação'),
(2, '{}', 0.5, 'JUNIOR', 'Python', 'Dados', 'São Paulo', 'SP', 'presencial', 'Python, SQL', 'Organização'),
(3, '{}', 1.0, 'JUNIOR', 'Figma', 'UX', 'Rio', 'RJ', 'híbrido', 'Figma, UX Research', 'Criatividade'),
(4, '{}', 2.0, 'PLENO', 'Marketing', 'Analista', 'São Paulo', 'SP', 'híbrido', 'SEO, Social Media', 'Comunicação'),
(5, '{}', 0.5, 'JUNIOR', 'RH', 'Assistente', 'São Paulo', 'SP', 'presencial', 'Excel, Atendimento', 'Proatividade'),
(6, '{}', 1.0, 'JUNIOR', 'Java', 'Backend', 'São Paulo', 'SP', 'remoto', 'Java, SQL, AWS', 'Liderança');


INSERT INTO candidate_match (fk_candidate, fk_vacancy, score, match_level)
VALUES
(1, 1, 82.5, 'ALTO'),
(6, 1, 90.0, 'DESTAQUE'),

(2, 2, 75.0, 'MEDIO'),
(4, 2, 40.0, 'BAIXO'),

(3, 3, 88.0, 'ALTO'),

(4, 4, 70.0, 'MEDIO'),

(5, 5, 80.0, 'ALTO'),
(2, 5, 50.0, 'MEDIO'),

(1, 2, 30.0, 'BAIXO'),
(6, 2, 85.0, 'ALTO');

INSERT INTO kanban_card (fk_candidate, fk_vacancy, fk_stage, match_level)
VALUES
(1, 1, 2, 'ALTO'),
(6, 1, 4, 'DESTAQUE'),

(2, 2, 1, 'MEDIO'),
(4, 2, 3, 'BAIXO'),

(3, 3, 2, 'ALTO'),

(4, 4, 2, 'MEDIO'),

(5, 5, 3, 'ALTO'),
(2, 5, 1, 'MEDIO'),

(1, 2, 10, 'BAIXO'),
(6, 2, 5, 'ALTO');


INSERT INTO opening_requests (cargo, periodo, modelo_trabalho, regime_contratacao, salario, localidade, requisitos, justificativa_path, gestor_id, area, status)
VALUES
('Desenvolvedor Java JR', 'Diurno', 'remoto', 'CLT', 4500, 'São Paulo', 'Spring Boot', NULL, 1, 'TI', 'APROVADA'),

('Designer UX', 'Integral', 'híbrido', 'CLT', 5000, 'São Paulo', 'Figma, UX Research', NULL, 2, 'Marketing', 'APROVADA'),

('Assistente RH JR', 'Diurno', 'presencial', 'CLT', 2500, 'São Paulo', 'Pacote Office', NULL, 3, 'RH', 'REJEITADA');
