-- Script completo para configurar o banco de dados quando_contratar
-- Execute este script no MySQL Workbench ou linha de comando MySQL

-- Cria o banco de dados se não existir
CREATE DATABASE IF NOT EXISTS quando_contratar;

USE quando_contratar;

-- Remove a tabela candidate se existir (cuidado: isso apaga todos os dados!)
-- DROP TABLE IF EXISTS candidate;

-- Cria a tabela candidate com a estrutura correta
CREATE TABLE IF NOT EXISTS candidate(
    `id_candidate` int auto_increment primary key,
    `name` varchar(100),
    `birth` date,
    `phone_number` char(14),
    `email` varchar(100),
    `state` char(2),
    `profile_picture` LONGBLOB,
    `education` varchar(500),
    `skills` varchar(500),
    `experience` TEXT,
    `resume` LONGBLOB
);

-- Verifica se a tabela foi criada corretamente
DESCRIBE candidate;

-- Verifica se há dados na tabela
SELECT COUNT(*) as total_candidates FROM candidate;

