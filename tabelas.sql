-- ========================================
-- SCRIPT DE CRIAÇÃO DO BANCO DE DADOS
-- Sistema: Quando Contratar
-- ========================================
-- Desativa checagem de foreign keys
-- SET FOREIGN_KEY_CHECKS = 0;

-- Limpa todas as tabelas
-- TRUNCATE TABLE selection_process;
-- TRUNCATE TABLE opening_requests;
-- TRUNCATE TABLE kanban_card;
-- TRUNCATE TABLE candidate_match;
-- TRUNCATE TABLE candidate_profile;
-- TRUNCATE TABLE candidate;
-- TRUNCATE TABLE vacancies;
-- TRUNCATE TABLE user;

-- Reativa checagem de foreign keys
-- SET FOREIGN_KEY_CHECKS = 1;


DROP DATABASE IF EXISTS quando_contratar;
CREATE DATABASE quando_contratar;
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
ALTER TABLE candidate_match
ADD COLUMN candidate_status ENUM('PENDING','ACCEPTED','REJECTED') DEFAULT 'PENDING';

ALTER TABLE vacancies
ADD COLUMN institution VARCHAR(50);