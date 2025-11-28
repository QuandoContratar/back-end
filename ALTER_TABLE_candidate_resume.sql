-- Script para alterar a coluna resume de MEDIUMBLOB para LONGBLOB
-- Execute este script no MySQL para suportar arquivos maiores

USE quando_contratar;

-- Alterar coluna resume para LONGBLOB (suporta até 4GB)
ALTER TABLE candidate 
MODIFY COLUMN resume LONGBLOB;

-- Opcional: também alterar profile_picture se necessário
ALTER TABLE candidate 
MODIFY COLUMN profile_picture LONGBLOB;

-- Verificar a alteração
DESCRIBE candidate;

