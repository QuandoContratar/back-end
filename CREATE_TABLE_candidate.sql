-- Script para criar a tabela candidate caso não exista
-- Execute este script no MySQL se a tabela não existir

USE quando_contratar;

-- Verifica se a tabela existe e cria se não existir
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

-- Verifica se a tabela foi criada
DESCRIBE candidate;

