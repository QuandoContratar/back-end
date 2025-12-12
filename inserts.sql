-- ===========================================
    -- INSERT INTO user
    -- ===========================================
    INSERT INTO user (name, email, password, area, level_access) VALUES
    ('Carlos Manager', 'carlos@empresa.com', '123', 'TI', 'MANAGER'),
    ('Ana Cabral', 'ana@empresa.com', '123', 'Marketing',   'MANAGER'),
    ('João RH', 'joao.rh@empresa.com', '123', 'RH', 'HR'),
    ('Admin Master', 'admin@empresa.com', '123', 'Diretoria', 'ADMIN');


    -- ===========================================
    -- INSERT INTO vacancies
    -- ===========================================
    INSERT INTO vacancies (position_job, period, work_model, requirements, contract_type, salary, location, opening_justification, area, status_vacancy, fk_manager, institution)
    VALUES
    ('Desenvolvedor Java JR', 'Diurno', 'remoto', 'Java, Spring Boot, MySQL', 'CLT', 4500.00, 'São Paulo - SP', 'Aumento de time', 'TI', 'aberta', 1, 'SPTECH'),
    ('Analista de Dados JR', 'Diurno', 'presencial', 'SQL, Python, Power BI', 'CLT', 4000.00, 'São Paulo - SP', 'Nova demanda BI', 'TI', 'aberta', 1, 'SPTECH'),
    ('Designer UX', 'Integral', 'híbrido', 'Figma, UX Research', 'CLT', 5000.00, 'São Paulo - SP', 'Reposição', 'Marketing', 'pendente aprovação', 2, 'SPTECH'),
    ('Analista de Marketing', 'Diurno', 'híbrido', 'SEO, Social Media', 'CLT', 3800.00, 'São Paulo - SP', 'Expansão', 'Marketing', 'aberta', 2, 'USP'),
    ('Assistente RH JR', 'Diurno', 'presencial', 'Pacote Office, Comunicação', 'CLT', 2500.00, 'São Paulo - SP', 'Suporte ao RH', 'RH', 'aberta', 3, 'USP');


    -- ===========================================
    -- INSERT INTO candidate
    -- ===========================================
    INSERT INTO candidate (name, birth, phone_number, email, state, education, skills, experience)
    VALUES
    ('Lucas Ribeiro', '1999-05-12', '(11)90000-0001', 'lucas@gmail.com', 'SP', 'Bacharel em SI', 'Java, Spring', '1 ano como dev'),
    ('Mariana Santos', '1998-08-22', '(11)90000-0002', 'mariana@gmail.com', 'SP', 'ADS', 'Python, SQL', 'Projetos acadêmicos'),
    ('Pedro Lima', '2000-02-10', '(11)90000-0003', 'pedro@gmail.com', 'RJ', 'Design Digital', 'Figma, UX', 'Freelancer'),
    ('Aline Souza', '1997-07-30', '(11)90000-0004', 'aline@gmail.com', 'SP', 'Marketing', 'SEO, Social Media', '2 anos exp'),
    ('João Martins', '2001-03-15', '(11)90000-0005', 'joaomartins@gmail.com', 'SP', 'Administração', 'Excel, Comunicação', 'Estágio RH'),
    ('Carla Ferreira', '1999-11-05', '(11)90000-0006', 'carla@gmail.com', 'SP', 'Eng. Computação', 'Java, SQL, AWS', 'Projetos pessoais');


    -- ===========================================
    -- INSERT INTO candidate_profile
    -- ===========================================
    INSERT INTO candidate_profile (fk_candidate, raw_json, total_experience_years, main_seniority, main_stack, main_role, city, state, remote_preference, hard_skills, soft_skills)
    VALUES
    (1, '{}', 1.0, 'JUNIOR', 'Java', 'Backend', 'São Paulo', 'SP', 'remoto', 'Java, Spring', 'Comunicação'),
    (2, '{}', 0.5, 'JUNIOR', 'Python', 'Dados', 'São Paulo', 'SP', 'presencial', 'Python, SQL', 'Organização'),
    (3, '{}', 1.0, 'JUNIOR', 'Figma', 'UX', 'Rio', 'RJ', 'híbrido', 'Figma, UX Research', 'Criatividade'),
    (4, '{}', 2.0, 'PLENO', 'Marketing', 'Analista', 'São Paulo', 'SP', 'híbrido', 'SEO, Social Media', 'Comunicação'),
    (5, '{}', 0.5, 'JUNIOR', 'RH', 'Assistente', 'São Paulo', 'SP', 'presencial', 'Excel, Atendimento', 'Proatividade'),
    (6, '{}', 1.0, 'JUNIOR', 'Java', 'Backend', 'São Paulo', 'SP', 'remoto', 'Java, SQL, AWS', 'Liderança');

    -- ===========================================
    -- INSERT INTO kanban_card
    -- ===========================================
    -- ⚠️ fk_stage = 10 POSSIVELMENTE INEXISTENTE
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

    (1, 2, 5, 'BAIXO'),  
    (6, 2, 5, 'ALTO');


    -- ===========================================
    -- INSERT INTO opening_requests
    -- ===========================================
    INSERT INTO opening_requests (cargo, periodo, modelo_trabalho, regime_contratacao, salario, localidade, requisitos, justificativa_path, gestor_id, area, status)
    VALUES
    ('Desenvolvedor Java JR', 'Diurno', 'remoto', 'CLT', 4500, 'São Paulo', 'Spring Boot', NULL, 1, 'TI', 'APROVADA'),

    ('Designer UX', 'Integral', 'híbrido', 'CLT', 5000, 'São Paulo', 'Figma, UX Research', NULL, 2, 'Marketing', 'APROVADA'),

    ('Assistente RH JR', 'Diurno', 'presencial', 'CLT', 2500, 'São Paulo', 'Pacote Office', NULL, 3, 'RH', 'REJEITADA');


    -- ===========================================
    -- INSERT INTO user (adicionais para total 10)
    -- ===========================================
    INSERT INTO user (name, email, password, area, level_access) VALUES
    ('Fernanda Lima', 'fernanda@empresa.com', '123', 'TI', 'HR'),
    ('Gustavo Almeida', 'gustavo@empresa.com', '123', 'TI', 'MANAGER'),
    ('Helena Costa', 'helena@empresa.com', '123', 'Marketing', 'HR'),
    ('Igor Ferreira', 'igor@empresa.com', '123', 'Comercial', 'MANAGER'),
    ('Julia Santos', 'julia@empresa.com', '123', 'RH', 'HR'),
    ('Leonardo Rocha', 'leo@empresa.com', '123', 'TI', 'ADMIN');

    -- ===========================================
    -- INSERT INTO vacancies (adicionais para total 10)
    -- ===========================================
    INSERT INTO vacancies (position_job, period, work_model, requirements, contract_type, salary, location, opening_justification, area, status_vacancy, fk_manager, institution)
    VALUES
    ('QA Tester', 'Integral', 'remoto', 'Testes manuais', 'CLT', 5500, 'São Paulo', 'Garantia de qualidade', 'TI', 'aberta', 2, 'USP'),
    ('Data Scientist', 'Integral', 'remoto', 'Python, SQL, ML', 'CLT', 10000, 'São Paulo', 'Time de dados', 'TI', 'aberta', 1, 'USP'),
    ('UX Designer Pleno', 'Integral', 'híbrido', 'Figma, Pesquisa UX', 'CLT', 6500, 'Rio de Janeiro', 'Reforço equipe design', 'Marketing', 'aberta', 3, 'FIAP'),
    ('Product Owner', 'Integral', 'presencial', 'Scrum, Jira', 'CLT', 9000, 'São Paulo', 'Nova squad', 'TI', 'aberta', 1, 'FIAP'),
    ('Estagiário de RH', 'Diurno', 'presencial', 'Excel, Atendimento', 'CLT', 1800, 'São Paulo', 'Suporte RH', 'RH', 'aberta', 4, 'FIAP');

    -- ===========================================
    -- INSERT INTO candidate (adicionais para total 10)
    -- ===========================================
    INSERT INTO candidate (name, birth, phone_number, email, state, education, skills, experience)
    VALUES
    ('Marcelo Pinto', '1995-11-23', '(11)90000-0007', 'marcelo@gmail.com', 'SP', 'Eng. Software', 'Java, Spring, SQL', '2 anos backend'),
    ('Larissa Melo', '1996-04-15', '(11)90000-0008', 'larissa@gmail.com', 'RJ', 'Marketing', 'SEO, Google Ads', '2 anos marketing digital'),
    ('Vitor Souza', '1993-12-10', '(11)90000-0009', 'vitor@gmail.com', 'SP', 'TI', 'AWS, Docker', '4 anos DevOps'),
    ('Patrícia Gomes', '1997-08-22', '(11)90000-0010', 'patricia@gmail.com', 'MG', 'Design', 'Figma, UX', '3 anos UX'),
    ('Rafael Martins', '1992-03-03', '(11)90000-0011', 'rafael.m@gmail.com', 'SP', 'Administração', 'Excel, Power BI', '5 anos financeiro');

    -- ===========================================
    -- INSERT INTO candidate_profile (adicionais para total 10)
    -- ===========================================
    INSERT INTO candidate_profile (fk_candidate, raw_json, total_experience_years, main_seniority, main_stack, main_role, city, state, remote_preference, hard_skills, soft_skills)
    VALUES
    (7, '{}', 2.0, 'PLENO', 'Java', 'Backend', 'São Paulo', 'SP', 'remoto', 'Java, Spring, SQL', 'Organização'),
    (8, '{}', 2.0, 'PLENO', 'Marketing', 'Analista', 'Rio de Janeiro', 'RJ', 'presencial', 'SEO, Ads', 'Criatividade'),
    (9, '{}', 4.0, 'SENIOR', 'AWS', 'DevOps', 'São Paulo', 'SP', 'remoto', 'AWS, Docker', 'Liderança'),
    (10, '{}', 3.0, 'PLENO', 'Figma', 'UX', 'Belo Horizonte', 'MG', 'híbrido', 'Figma, UX Research', 'Comunicação'),
    (11, '{}', 5.0, 'SENIOR', 'Excel, Power BI', 'Financeiro', 'São Paulo', 'SP', 'presencial', 'Excel, Power BI', 'Proatividade');

    -- ===========================================
    -- INSERT INTO kanban_card (adicionais para total 10)
    -- ===========================================
    INSERT INTO kanban_card (fk_candidate, fk_vacancy, fk_stage, match_level)
    VALUES
    (7, 1, 3, 'ALTO'),
    (8, 3, 2, 'ALTO'),
    (9, 2, 4, 'DESTAQUE'),
    (10, 4, 5, 'MEDIO'),
    (11, 5, 3, 'MEDIO');

    -- ===========================================
    -- INSERT INTO opening_requests (adicionais para total 10)
    -- ===========================================
    INSERT INTO opening_requests (cargo, periodo, modelo_trabalho, regime_contratacao, salario, localidade, requisitos, justificativa_path, gestor_id, area, status)
    VALUES
    ('Desenvolvedor Frontend', 'Diurno', 'remoto', 'CLT', 4200, 'São Paulo', 'React, JS', NULL, 2, 'TI', 'APROVADA'),
    ('Analista Financeiro', 'Diurno', 'presencial', 'CLT', 5000, 'São Paulo', 'Excel, ERP', NULL, 4, 'Financeiro', 'ABERTA'),
    ('UX Researcher', 'Integral', 'híbrido', 'CLT', 4800, 'Rio de Janeiro', 'Figma, Pesquisa UX', NULL, 3, 'Marketing', 'APROVADA'),
    ('QA Analyst', 'Integral', 'remoto', 'CLT', 5200, 'São Paulo', 'Testes manuais', NULL, 2, 'TI', 'APROVADA'),
    ('Estagiário Comercial', 'Diurno', 'presencial', 'CLT', 2000, 'São Paulo', 'Suporte vendas', NULL, 5, 'Comercial', 'ABERTA');

    INSERT INTO selection_process (progress, current_stage, outcome, fk_candidate, fk_recruiter, fk_vacancy) VALUES
    (10.00, 'triagem', 'pendente', 1, 2, 1),
    (20.00, 'triagem', 'pendente', 2, 2, 2),
    (30.00, 'teste_tecnico', 'pendente', 3, 1, 3),
    (40.00, 'teste_tecnico', 'pendente', 4, 1, 4),
    (50.00, 'entrevista_tecnica', 'pendente', 5, 3, 5),
    (60.00, 'entrevista_tecnica', 'pendente', 6, 3, 1),
    (70.00, 'contratacao', 'pendente', 1, 4, 2),
    (80.00, 'contratacao', 'pendente', 2, 4, 3),
    (90.00, 'contratacao', 'pendente', 3, 1, 4),
    (100.00, 'contratacao', 'pendente', 4, 2, 5);

    INSERT INTO user (name, email, password, area, level_access) VALUES
    ('Lucas Almeida', 'lucas@empresa.com', '123', 'Marketing', 'HR'),
    ('Bruna Silva', 'bruna@empresa.com', '123', 'RH', 'HR'),
    ('Fernando Souza', 'fernando@empresa.com', '123', 'Financeiro', 'MANAGER'),
    ('Mariana Lima', 'mariana.lima@empresa.com', '123', 'TI', 'MANAGER'),
    ('Ricardo Santos', 'ricardo@empresa.com', '123', 'Marketing', 'MANAGER'),
    ('Patrícia Oliveira', 'patricia@empresa.com', '123', 'RH', 'HR'),
    ('Gabriel Costa', 'gabriel@empresa.com', '123', 'Financeiro', 'ADMIN'),
    ('Aline Ribeiro', 'aline@empresa.com', '123', 'Marketing', 'HR'),
    ('Carlos Lima', 'carlos.lima@empresa.com', '123', 'TI', 'HR'),
    ('Juliana Fernandes', 'juliana@empresa.com', '123', 'RH', 'MANAGER'),
    ('Rafael Almeida', 'rafael.almeida@empresa.com', '123', 'Financeiro', 'MANAGER'),
    ('Vanessa Souza', 'vanessa@empresa.com', '123', 'Marketing', 'HR'),
    ('Daniel Martins', 'daniel@empresa.com', '123', 'TI', 'MANAGER'),
    ('Fernanda Rocha', 'fernanda.rocha@empresa.com', '123', 'RH', 'HR'),
    ('Bruno Gomes', 'bruno@empresa.com', '123', 'Financeiro', 'ADMIN'),
    ('Camila Santos', 'camila@empresa.com', '123', 'Marketing', 'HR'),
    ('Thiago Lima', 'thiago@empresa.com', '123', 'TI', 'MANAGER'),
    ('Letícia Oliveira', 'leticia@empresa.com', '123', 'RH', 'HR'),
    ('Victor Costa', 'victor@empresa.com', '123', 'Financeiro', 'MANAGER'),
    ('Natália Almeida', 'natalia@empresa.com', '123', 'Marketing', 'HR');

    INSERT INTO vacancies (position_job, period, work_model, requirements, contract_type, salary, location, opening_justification, area, status_vacancy, fk_manager, institution)
    VALUES
    ('Analista de Marketing Pleno', 'Diurno', 'híbrido', 'SEO, Ads', 'CLT', 4500, 'São Paulo', 'Nova demanda', 'Marketing', 'aberta', 2, 'USP'),
    ('Coordenador RH', 'Diurno', 'presencial', 'Gestão de pessoas', 'CLT', 7000, 'São Paulo', 'Reposição', 'RH', 'aberta', 3, 'FIAP'),
    ('Analista Financeiro JR', 'Diurno', 'remoto', 'Excel, Power BI', 'CLT', 4000, 'São Paulo', 'Expansão', 'Financeiro', 'aberta', 7, 'USP'),
    ('Assistente Marketing', 'Integral', 'híbrido', 'Social Media, Copywriting', 'CLT', 3500, 'São Paulo', 'Suporte equipe', 'Marketing', 'pendente aprovação', 2, 'FIAP'),
    ('Estagiário Financeiro', 'Diurno', 'presencial', 'Excel, ERP', 'CLT', 1800, 'São Paulo', 'Suporte financeiro', 'Financeiro', 'aberta', 11, 'USP'),
    ('Analista RH Pleno', 'Diurno', 'presencial', 'Recrutamento e seleção', 'CLT', 5000, 'São Paulo', 'Nova contratação', 'RH', 'aberta', 3, 'FIAP'),
    ('Marketing Digital JR', 'Integral', 'remoto', 'Google Ads, SEO', 'CLT', 4000, 'Rio de Janeiro', 'Nova demanda', 'Marketing', 'aberta', 2, 'FIAP'),
    ('Financeiro Pleno', 'Diurno', 'híbrido', 'Contabilidade, ERP', 'CLT', 6000, 'São Paulo', 'Reposição', 'Financeiro', 'aberta', 7, 'USP'),
    ('RH Assistente JR', 'Diurno', 'presencial', 'Atendimento, Excel', 'CLT', 2800, 'São Paulo', 'Suporte', 'RH', 'aberta', 3, 'USP'),
    ('Coordenador Marketing', 'Integral', 'híbrido', 'Gestão de campanhas', 'CLT', 7500, 'Rio de Janeiro', 'Nova squad', 'Marketing', 'pendente aprovação', 2, 'FIAP'),
    ('Analista Financeiro Pleno', 'Diurno', 'presencial', 'ERP, Excel', 'CLT', 5500, 'São Paulo', 'Expansão', 'Financeiro', 'aberta', 7, 'USP'),
    ('RH Pleno', 'Diurno', 'presencial', 'Treinamento, Seleção', 'CLT', 5200, 'São Paulo', 'Reposição', 'RH', 'aberta', 3, 'FIAP'),
    ('Marketing Estratégico', 'Integral', 'híbrido', 'Planejamento, SEO', 'CLT', 6800, 'São Paulo', 'Nova demanda', 'Marketing', 'aberta', 2, 'USP'),
    ('Financeiro Senior', 'Diurno', 'remoto', 'ERP, Contabilidade', 'CLT', 9000, 'São Paulo', 'Expansão', 'Financeiro', 'aberta', 7, 'FIAP'),
    ('Assistente RH Pleno', 'Diurno', 'presencial', 'Excel, Atendimento', 'CLT', 3500, 'São Paulo', 'Suporte equipe', 'RH', 'aberta', 3, 'USP'),
    ('Marketing Analyst', 'Integral', 'remoto', 'SEO, Ads', 'CLT', 4200, 'Rio de Janeiro', 'Reposição', 'Marketing', 'aberta', 2, 'FIAP'),
    ('Financeiro Junior', 'Diurno', 'híbrido', 'ERP, Excel', 'CLT', 3700, 'São Paulo', 'Nova demanda', 'Financeiro', 'aberta', 7, 'USP'),
    ('RH Junior', 'Diurno', 'presencial', 'Atendimento, Excel', 'CLT', 2800, 'São Paulo', 'Reposição', 'RH', 'aberta', 3, 'FIAP'),
    ('Marketing Pleno', 'Integral', 'híbrido', 'Google Ads, Social Media', 'CLT', 5000, 'São Paulo', 'Expansão', 'Marketing', 'aberta', 2, 'USP'),
    ('Financeiro Analyst', 'Diurno', 'remoto', 'ERP, Power BI', 'CLT', 4500, 'São Paulo', 'Nova demanda', 'Financeiro', 'aberta', 7, 'FIAP');

    INSERT INTO candidate (name, birth, phone_number, email, state, education, skills, experience)
    VALUES
    ('Eduardo Lima', '1990-01-20', '(11)90000-0012', 'eduardo@gmail.com', 'SP', 'Administração', 'Excel, ERP', '3 anos financeiro'),
    ('Bianca Santos', '1994-05-17', '(11)90000-0013', 'bianca@gmail.com', 'RJ', 'Marketing', 'SEO, Ads', '2 anos marketing digital'),
    ('Fernando Rocha', '1992-08-21', '(11)90000-0014', 'fernando.r@gmail.com', 'SP', 'RH', 'Recrutamento, Excel', '4 anos RH'),
    ('Carolina Almeida', '1995-03-12', '(11)90000-0015', 'carolina@gmail.com', 'MG', 'Marketing', 'Social Media, Figma', '3 anos UX'),
    ('Gustavo Lima', '1993-12-30', '(11)90000-0016', 'gustavo@gmail.com', 'SP', 'TI', 'Java, Spring', '5 anos Dev'),
    ('Priscila Costa', '1997-06-18', '(11)90000-0017', 'priscila@gmail.com', 'SP', 'Financeiro', 'ERP, Excel', '2 anos financeiro'),
    ('Rodrigo Souza', '1991-11-02', '(11)90000-0018', 'rodrigo@gmail.com', 'SP', 'RH', 'Atendimento, Excel', '5 anos RH'),
    ('Camila Oliveira', '1998-09-10', '(11)90000-0019', 'camila@gmail.com', 'RJ', 'Marketing', 'SEO, Ads', '1 ano marketing'),
    ('Vinicius Martins', '1992-07-22', '(11)90000-0020', 'vinicius@gmail.com', 'SP', 'Financeiro', 'ERP, Power BI', '4 anos financeiro'),
    ('Aline Santos', '1996-02-14', '(11)90000-0021', 'aline.s@gmail.com', 'SP', 'RH', 'Excel, Atendimento', '3 anos RH'),
    ('Diego Almeida', '1993-04-11', '(11)90000-0022', 'diego@gmail.com', 'SP', 'Marketing', 'Figma, Social Media', '2 anos UX'),
    ('Jessica Lima', '1995-08-25', '(11)90000-0023', 'jessica@gmail.com', 'SP', 'Financeiro', 'ERP, Excel', '3 anos financeiro'),
    ('Matheus Souza', '1990-10-30', '(11)90000-0024', 'matheus@gmail.com', 'RJ', 'TI', 'Python, SQL', '5 anos Dev'),
    ('Larissa Almeida', '1997-12-05', '(11)90000-0025', 'larissa.a@gmail.com', 'SP', 'Marketing', 'SEO, Ads', '2 anos marketing'),
    ('Felipe Martins', '1991-03-19', '(11)90000-0026', 'felipe@gmail.com', 'SP', 'Financeiro', 'Excel, ERP', '4 anos financeiro'),
    ('Renata Costa', '1996-06-21', '(11)90000-0027', 'renata@gmail.com', 'SP', 'RH', 'Recrutamento, Atendimento', '3 anos RH'),
    ('Leandro Oliveira', '1992-11-13', '(11)90000-0028', 'leandro@gmail.com', 'RJ', 'Marketing', 'Google Ads, SEO', '4 anos marketing'),
    ('Patrícia Lima', '1994-01-09', '(11)90000-0029', 'patricia.l@gmail.com', 'SP', 'Financeiro', 'ERP, Power BI', '5 anos financeiro'),
    ('Rafael Costa', '1995-05-30', '(11)90000-0030', 'rafael.c@gmail.com', 'SP', 'RH', 'Excel, Atendimento', '2 anos RH'),
    ('Bruna Martins', '1998-09-12', '(11)90000-0031', 'bruna.m@gmail.com', 'RJ', 'Marketing', 'Figma, UX', '1 ano UX');

    INSERT INTO candidate_profile (fk_candidate, raw_json, total_experience_years, main_seniority, main_stack, main_role, city, state, remote_preference, hard_skills, soft_skills)
    VALUES
    (12, '{}', 3.0, 'PLENO', 'Excel, ERP', 'Financeiro', 'São Paulo', 'SP', 'presencial', 'Excel, ERP', 'Organização'),
    (13, '{}', 2.0, 'JUNIOR', 'Marketing', 'Analista', 'Rio de Janeiro', 'RJ', 'remoto', 'SEO, Ads', 'Criatividade'),
    (14, '{}', 4.0, 'PLENO', 'RH', 'Analista', 'São Paulo', 'SP', 'presencial', 'Recrutamento, Excel', 'Comunicação'),
    (15, '{}', 3.0, 'PLENO', 'Marketing', 'UX', 'Belo Horizonte', 'MG', 'híbrido', 'Social Media, Figma', 'Criatividade'),
    (16, '{}', 5.0, 'SENIOR', 'Java', 'Backend', 'São Paulo', 'SP', 'remoto', 'Java, Spring', 'Liderança'),
    (17, '{}', 2.0, 'JUNIOR', 'Financeiro', 'Analista', 'São Paulo', 'SP', 'presencial', 'ERP, Excel', 'Organização'),
    (18, '{}', 5.0, 'SENIOR', 'RH', 'Coordenador', 'São Paulo', 'SP', 'presencial', 'Atendimento, Excel', 'Liderança'),
    (19, '{}', 1.0, 'JUNIOR', 'Marketing', 'Analista', 'Rio de Janeiro', 'RJ', 'remoto', 'SEO, Ads', 'Criatividade'),
    (20, '{}', 4.0, 'PLENO', 'Financeiro', 'Analista', 'São Paulo', 'SP', 'remoto', 'ERP, Power BI', 'Organização'),
    (21, '{}', 3.0, 'PLENO', 'RH', 'Assistente', 'São Paulo', 'SP', 'presencial', 'Excel, Atendimento', 'Comunicação'),
    (22, '{}', 2.0, 'PLENO', 'Marketing', 'UX', 'São Paulo', 'SP', 'híbrido', 'Figma, Social Media', 'Criatividade'),
    (23, '{}', 3.0, 'PLENO', 'Financeiro', 'Analista', 'São Paulo', 'SP', 'presencial', 'ERP, Excel', 'Organização'),
    (24, '{}', 5.0, 'SENIOR', 'Python, SQL', 'Dev', 'Rio de Janeiro', 'RJ', 'remoto', 'Python, SQL', 'Liderança'),
    (25, '{}', 2.0, 'JUNIOR', 'Marketing', 'Analista', 'São Paulo', 'SP', 'remoto', 'SEO, Ads', 'Criatividade'),
    (26, '{}', 4.0, 'PLENO', 'Financeiro', 'Analista', 'São Paulo', 'SP', 'presencial', 'Excel, ERP', 'Organização'),
    (27, '{}', 3.0, 'PLENO', 'RH', 'Analista', 'São Paulo', 'SP', 'presencial', 'Recrutamento, Atendimento', 'Comunicação'),
    (28, '{}', 4.0, 'PLENO', 'Marketing', 'Analista', 'Rio de Janeiro', 'RJ', 'remoto', 'Google Ads, SEO', 'Criatividade'),
    (29, '{}', 5.0, 'SENIOR', 'Financeiro', 'Analista', 'São Paulo', 'SP', 'presencial', 'ERP, Power BI', 'Organização'),
    (30, '{}', 2.0, 'JUNIOR', 'RH', 'Assistente', 'São Paulo', 'SP', 'presencial', 'Excel, Atendimento', 'Comunicação'),
    (31, '{}', 1.0, 'JUNIOR', 'Marketing', 'UX', 'Rio de Janeiro', 'RJ', 'híbrido', 'Figma, UX', 'Criatividade');

    INSERT INTO selection_process (progress, current_stage, outcome, fk_candidate, fk_recruiter, fk_vacancy) VALUES
    (10.00, 'triagem', 'pendente', 12, 2, 6),
    (15.00, 'triagem', 'pendente', 13, 2, 7),
    (20.00, 'triagem', 'pendente', 14, 3, 8),
    (25.00, 'triagem', 'pendente', 15, 3, 9),
    (30.00, 'triagem', 'pendente', 16, 1, 1),
    (35.00, 'triagem', 'pendente', 17, 1, 10),
    (40.00, 'triagem', 'pendente', 18, 2, 2),
    (45.00, 'triagem', 'pendente', 19, 2, 3),
    (50.00, 'triagem', 'pendente', 20, 3, 4),
    (55.00, 'triagem', 'pendente', 21, 3, 5),
    (60.00, 'teste_tecnico', 'pendente', 22, 1, 6),
    (65.00, 'teste_tecnico', 'pendente', 23, 1, 7),
    (70.00, 'teste_tecnico', 'pendente', 24, 2, 8),
    (75.00, 'teste_tecnico', 'pendente', 25, 2, 9),
    (80.00, 'teste_tecnico', 'pendente', 26, 3, 10),
    (85.00, 'teste_tecnico', 'pendente', 27, 3, 1),
    (90.00, 'entrevista_tecnica', 'pendente', 28, 4, 2),
    (92.00, 'entrevista_tecnica', 'pendente', 29, 4, 3),
    (95.00, 'entrevista_tecnica', 'pendente', 30, 1, 4),
    (100.00, 'contratacao', 'pendente', 31, 2, 5);

    INSERT INTO kanban_card (fk_candidate, fk_vacancy, fk_stage, match_level)
    VALUES
    (12, 6, 1, 'BAIXO'),
    (13, 7, 1, 'BAIXO'),
    (14, 8, 2, 'BAIXO'),
    (15, 9, 2, 'BAIXO'),
    (16, 1, 3, 'BAIXO'),
    (17, 10, 3, 'BAIXO'),
    (18, 2, 4, 'BAIXO'),
    (19, 3, 4, 'BAIXO'),
    (20, 4, 5, 'BAIXO'),
    (21, 5, 5, 'BAIXO'),
    (22, 6, 1, 'BAIXO'),
    (23, 7, 2, 'BAIXO'),
    (24, 8, 2, 'BAIXO'),
    (25, 9, 3, 'BAIXO'),
    (26, 10, 3, 'BAIXO'),
    (27, 1, 4, 'BAIXO'),
    (28, 2, 4, 'BAIXO'),
    (29, 3, 5, 'BAIXO'),
    (30, 4, 5, 'BAIXO'),
    (31, 5, 5, 'BAIXO');

    INSERT INTO opening_requests (cargo, periodo, modelo_trabalho, regime_contratacao, salario, localidade, requisitos, justificativa_path, gestor_id, area, status)
    VALUES
    ('Analista Marketing JR', 'Diurno', 'híbrido', 'CLT', 4000, 'São Paulo', 'SEO, Ads', NULL, 2, 'Marketing', 'ABERTA'),
    ('Coordenador RH', 'Diurno', 'presencial', 'CLT', 7000, 'São Paulo', 'Gestão de pessoas', NULL, 3, 'RH', 'APROVADA'),
    ('Analista Financeiro JR', 'Diurno', 'remoto', 'CLT', 4000, 'São Paulo', 'Excel, Power BI', NULL, 7, 'Financeiro', 'ABERTA'),
    ('Assistente Marketing', 'Integral', 'híbrido', 'CLT', 3500, 'São Paulo', 'Social Media, Copywriting', NULL, 2, 'Marketing', 'ABERTA'),
    ('Estagiário Financeiro', 'Diurno', 'presencial', 'CLT', 1800, 'São Paulo', 'ERP, Excel', NULL, 11, 'Financeiro', 'ABERTA'),
    ('Analista RH Pleno', 'Diurno', 'presencial', 'CLT', 5000, 'São Paulo', 'Recrutamento e Seleção', NULL, 3, 'RH', 'ABERTA'),
    ('Marketing Digital JR', 'Integral', 'remoto', 'CLT', 4000, 'Rio de Janeiro', 'SEO, Ads', NULL, 2, 'Marketing', 'ABERTA'),
    ('Financeiro Pleno', 'Diurno', 'híbrido', 'CLT', 6000, 'São Paulo', 'ERP, Contabilidade', NULL, 7, 'Financeiro', 'ABERTA'),
    ('RH Assistente JR', 'Diurno', 'presencial', 'CLT', 2800, 'São Paulo', 'Excel, Atendimento', NULL, 3, 'RH', 'ABERTA'),
    ('Coordenador Marketing', 'Integral', 'híbrido', 'CLT', 7500, 'Rio de Janeiro', 'Gestão de campanhas', NULL, 2, 'Marketing', 'ABERTA'),
    ('Analista Financeiro Pleno', 'Diurno', 'presencial', 'CLT', 5500, 'São Paulo', 'ERP, Excel', NULL, 7, 'Financeiro', 'ABERTA'),
    ('RH Pleno', 'Diurno', 'presencial', 'CLT', 5200, 'São Paulo', 'Treinamento, Seleção', NULL, 3, 'RH', 'ABERTA'),
    ('Marketing Estratégico', 'Integral', 'híbrido', 'CLT', 6800, 'São Paulo', 'Planejamento, SEO', NULL, 2, 'Marketing', 'ABERTA'),
    ('Financeiro Senior', 'Diurno', 'remoto', 'CLT', 9000, 'São Paulo', 'ERP, Contabilidade', NULL, 7, 'Financeiro', 'ABERTA'),
    ('Assistente RH Pleno', 'Diurno', 'presencial', 'CLT', 3500, 'São Paulo', 'Excel, Atendimento', NULL, 3, 'RH', 'ABERTA'),
    ('Marketing Analyst', 'Integral', 'remoto', 'CLT', 4200, 'Rio de Janeiro', 'SEO, Ads', NULL, 2, 'Marketing', 'ABERTA'),
    ('Financeiro Junior', 'Diurno', 'híbrido', 'CLT', 3700, 'São Paulo', 'ERP, Excel', NULL, 7, 'Financeiro', 'ABERTA'),
    ('RH Junior', 'Diurno', 'presencial', 'CLT', 2800, 'São Paulo', 'Atendimento, Excel', NULL, 3, 'RH', 'ABERTA'),
    ('Marketing Pleno', 'Integral', 'híbrido', 'CLT', 5000, 'São Paulo', 'Google Ads, Social Media', NULL, 2, 'Marketing', 'ABERTA'),
    ('Financeiro Analyst', 'Diurno', 'remoto', 'CLT', 4500, 'São Paulo', 'ERP, Power BI', NULL, 7, 'Financeiro', 'ABERTA');


    INSERT INTO candidate (id_candidate, name, birth, phone_number, email, state, education, skills, experience) VALUES
    (32, 'Candidato 32', '1995-01-10', '(11)90000-0032', 'cand32@gmail.com', 'SP', 'Bacharel em TI', 'Java, SQL', '1 ano'),
    (33, 'Candidato 33', '1996-02-15', '(11)90000-0033', 'cand33@gmail.com', 'RJ', 'ADS', 'Python, Excel', '2 anos'),
    (34, 'Candidato 34', '1994-03-20', '(11)90000-0034', 'cand34@gmail.com', 'SP', 'Administração', 'Excel, Power BI', '3 anos'),
    (35, 'Candidato 35', '1997-04-25', '(11)90000-0035', 'cand35@gmail.com', 'MG', 'Marketing', 'SEO, Social Media', '1 ano'),
    (36, 'Candidato 36', '1995-05-30', '(11)90000-0036', 'cand36@gmail.com', 'SP', 'Engenharia', 'Java, Spring', '2 anos'),
    (37, 'Candidato 37', '1996-06-10', '(11)90000-0037', 'cand37@gmail.com', 'RJ', 'Financeiro', 'Excel, ERP', '2 anos'),
    (38, 'Candidato 38', '1995-07-15', '(11)90000-0038', 'cand38@gmail.com', 'SP', 'TI', 'Python, SQL', '1 ano'),
    (39, 'Candidato 39', '1997-08-20', '(11)90000-0039', 'cand39@gmail.com', 'MG', 'Marketing', 'Social Media, Ads', '3 anos'),
    (40, 'Candidato 40', '1994-09-25', '(11)90000-0040', 'cand40@gmail.com', 'SP', 'RH', 'Recrutamento, Excel', '1 ano'),
    (41, 'Candidato 41', '1995-10-30', '(11)90000-0041', 'cand41@gmail.com', 'RJ', 'TI', 'AWS, Docker', '2 anos'),
    (42, 'Candidato 42', '1996-11-05', '(11)90000-0042', 'cand42@gmail.com', 'SP', 'Financeiro', 'ERP, Contabilidade', '3 anos'),
    (43, 'Candidato 43', '1995-12-10', '(11)90000-0043', 'cand43@gmail.com', 'MG', 'RH', 'Excel, Atendimento', '1 ano'),
    (44, 'Candidato 44', '1994-01-15', '(11)90000-0044', 'cand44@gmail.com', 'SP', 'Marketing', 'SEO, Google Ads', '2 anos'),
    (45, 'Candidato 45', '1996-02-20', '(11)90000-0045', 'cand45@gmail.com', 'RJ', 'TI', 'Java, Spring Boot', '3 anos'),
    (46, 'Candidato 46', '1995-03-25', '(11)90000-0046', 'cand46@gmail.com', 'SP', 'Financeiro', 'Excel, Power BI', '2 anos'),
    (47, 'Candidato 47', '1997-04-30', '(11)90000-0047', 'cand47@gmail.com', 'MG', 'RH', 'Recrutamento, Excel', '1 ano'),
    (48, 'Candidato 48', '1996-05-05', '(11)90000-0048', 'cand48@gmail.com', 'SP', 'Marketing', 'Social Media, SEO', '2 anos'),
    (49, 'Candidato 49', '1995-06-10', '(11)90000-0049', 'cand49@gmail.com', 'RJ', 'TI', 'Python, SQL', '3 anos'),
    (50, 'Candidato 50', '1997-07-15', '(11)90000-0050', 'cand50@gmail.com', 'SP', 'Financeiro', 'ERP, Contabilidade', '1 ano'),
    (51, 'Candidato 51', '1995-08-20', '(11)90000-0051', 'cand51@gmail.com', 'MG', 'RH', 'Excel, Atendimento', '2 anos'),
    (52, 'Candidato 52', '1994-09-25', '(11)90000-0052', 'cand52@gmail.com', 'SP', 'Marketing', 'SEO, Social Media', '2 anos'),
    (53, 'Candidato 53', '1996-10-30', '(11)90000-0053', 'cand53@gmail.com', 'RJ', 'TI', 'Java, Spring', '3 anos'),
    (54, 'Candidato 54', '1995-11-05', '(11)90000-0054', 'cand54@gmail.com', 'SP', 'Financeiro', 'Excel, ERP', '1 ano'),
    (55, 'Candidato 55', '1997-12-10', '(11)90000-0055', 'cand55@gmail.com', 'MG', 'RH', 'Recrutamento, Comunicação', '2 anos'),
    (56, 'Candidato 56', '1996-01-15', '(11)90000-0056', 'cand56@gmail.com', 'SP', 'Marketing', 'Social Media, Figma', '2 anos'),
    (57, 'Candidato 57', '1995-02-20', '(11)90000-0057', 'cand57@gmail.com', 'RJ', 'TI', 'Python, AWS', '3 anos'),
    (58, 'Candidato 58', '1997-03-25', '(11)90000-0058', 'cand58@gmail.com', 'SP', 'Financeiro', 'ERP, Excel', '1 ano'),
    (59, 'Candidato 59', '1996-04-30', '(11)90000-0059', 'cand59@gmail.com', 'MG', 'RH', 'Excel, Atendimento', '2 anos'),
    (60, 'Candidato 60', '1995-05-05', '(11)90000-0060', 'cand60@gmail.com', 'SP', 'Marketing', 'SEO, Ads', '2 anos'),
    (61, 'Candidato 61', '1997-06-10', '(11)90000-0061', 'cand61@gmail.com', 'RJ', 'TI', 'Java, SQL', '3 anos'),
    (62, 'Candidato 62', '1995-07-15', '(11)90000-0062', 'cand62@gmail.com', 'SP', 'Financeiro', 'ERP, Power BI', '1 ano'),
    (63, 'Candidato 63', '1996-08-20', '(11)90000-0063', 'cand63@gmail.com', 'MG', 'RH', 'Recrutamento, Excel', '2 anos'),
    (64, 'Candidato 64', '1997-09-25', '(11)90000-0064', 'cand64@gmail.com', 'SP', 'Marketing', 'Social Media, SEO', '2 anos'),
    (65, 'Candidato 65', '1995-10-30', '(11)90000-0065', 'cand65@gmail.com', 'RJ', 'TI', 'Python, SQL', '3 anos'),
    (66, 'Candidato 66', '1996-11-05', '(11)90000-0066', 'cand66@gmail.com', 'SP', 'Financeiro', 'ERP, Contabilidade', '1 ano'),
    (67, 'Candidato 67', '1997-12-10', '(11)90000-0067', 'cand67@gmail.com', 'MG', 'RH', 'Excel, Atendimento', '2 anos'),
    (68, 'Candidato 68', '1995-01-15', '(11)90000-0068', 'cand68@gmail.com', 'SP', 'Marketing', 'SEO, Ads', '2 anos'),
    (69, 'Candidato 69', '1996-02-20', '(11)90000-0069', 'cand69@gmail.com', 'RJ', 'TI', 'Java, Spring Boot', '3 anos'),
    (70, 'Candidato 70', '1997-03-25', '(11)90000-0070', 'cand70@gmail.com', 'SP', 'Financeiro', 'Excel, Power BI', '1 ano'),
    (71, 'Candidato 71', '1995-04-30', '(11)90000-0071', 'cand71@gmail.com', 'MG', 'RH', 'Recrutamento, Excel', '2 anos'),
    (72, 'Candidato 72', '1996-05-05', '(11)90000-0072', 'cand72@gmail.com', 'SP', 'Marketing', 'Social Media, Figma', '2 anos'),
    (73, 'Candidato 73', '1997-06-10', '(11)90000-0073', 'cand73@gmail.com', 'RJ', 'TI', 'Python, AWS', '3 anos'),
    (74, 'Candidato 74', '1995-07-15', '(11)90000-0074', 'cand74@gmail.com', 'SP', 'Financeiro', 'ERP, Excel', '1 ano'),
    (75, 'Candidato 75', '1996-08-20', '(11)90000-0075', 'cand75@gmail.com', 'MG', 'RH', 'Excel, Atendimento', '2 anos'),
    (76, 'Candidato 76', '1997-09-25', '(11)90000-0076', 'cand76@gmail.com', 'SP', 'Marketing', 'SEO, Ads', '2 anos'),
    (77, 'Candidato 77', '1995-10-30', '(11)90000-0077', 'cand77@gmail.com', 'RJ', 'TI', 'Java, SQL', '3 anos'),
    (78, 'Candidato 78', '1996-11-05', '(11)90000-0078', 'cand78@gmail.com', 'SP', 'Financeiro', 'ERP, Power BI', '1 ano'),
    (79, 'Candidato 79', '1997-12-10', '(11)90000-0079', 'cand79@gmail.com', 'MG', 'RH', 'Recrutamento, Excel', '2 anos'),
    (80, 'Candidato 80', '1995-01-15', '(11)90000-0080', 'cand80@gmail.com', 'SP', 'Marketing', 'Social Media, SEO', '2 anos'),
    (81, 'Candidato 81', '1996-02-20', '(11)90000-0081', 'cand81@gmail.com', 'RJ', 'TI', 'Python, SQL', '3 anos');

    INSERT INTO candidate_match (fk_candidate, fk_vacancy, score, match_level, candidate_status) VALUES
    (1,1,42.0,'BAIXO','PENDING'),
    (2,2,45.0,'BAIXO','PENDING'),
    (3,3,48.0,'BAIXO','REJECTED'),
    (4,4,50.0,'BAIXO','PENDING'),
    (5,5,44.0,'BAIXO','PENDING'),
    (6,6,47.0,'BAIXO','PENDING'),
    (7,7,49.0,'BAIXO','ACCEPTED'),
    (8,8,46.0,'BAIXO','PENDING'),
    (9,9,52.0,'MEDIO','PENDING'),
    (10,10,70.0,'MEDIO','ACCEPTED'),
    (11,11,43.0,'BAIXO','PENDING'),
    (12,12,39.0,'BAIXO','REJECTED'),
    (13,13,48.0,'BAIXO','PENDING'),
    (14,14,45.0,'BAIXO','PENDING'),
    (15,15,41.0,'BAIXO','PENDING'),
    (16,16,46.0,'BAIXO','PENDING'),
    (17,17,44.0,'BAIXO','PENDING'),
    (18,18,47.0,'BAIXO','PENDING'),
    (19,19,79.0,'ALTO','ACCEPTED'),
    (20,20,85.0,'DESTAQUE','ACCEPTED'),
    (21,21,43.0,'BAIXO','PENDING'),
    (22,22,46.0,'BAIXO','PENDING'),
    (23,23,48.0,'BAIXO','PENDING'),
    (24,24,45.0,'BAIXO','PENDING'),
    (25,25,44.0,'BAIXO','PENDING'),
    (26,1,46.0,'BAIXO','PENDING'),
    (27,2,49.0,'BAIXO','PENDING'),
    (28,3,50.0,'BAIXO','REJECTED'),
    (29,4,42.0,'BAIXO','PENDING'),
    (30,5,47.0,'BAIXO','PENDING'),
    (31,6,48.0,'BAIXO','PENDING'),
    (32,7,45.0,'BAIXO','ACCEPTED'),
    (33,8,70.0,'MEDIO','PENDING'),
    (34,9,72.0,'MEDIO','ACCEPTED'),
    (35,10,43.0,'BAIXO','PENDING'),
    (36,11,41.0,'BAIXO','REJECTED'),
    (37,12,47.0,'BAIXO','PENDING'),
    (38,13,49.0,'BAIXO','PENDING'),
    (39,14,46.0,'BAIXO','PENDING'),
    (40,15,44.0,'BAIXO','PENDING'),
    (41,16,48.0,'BAIXO','PENDING'),
    (42,17,45.0,'BAIXO','PENDING'),
    (43,18,47.0,'BAIXO','PENDING'),
    (44,19,79.0,'ALTO','ACCEPTED'),
    (45,20,86.0,'DESTAQUE','ACCEPTED'),
    (46,21,43.0,'BAIXO','PENDING'),
    (47,22,45.0,'BAIXO','PENDING'),
    (48,23,46.0,'BAIXO','PENDING'),
    (49,24,42.0,'BAIXO','PENDING'),
    (50,25,44.0,'BAIXO','PENDING'),
    (51,1,48.0,'BAIXO','PENDING'),
    (52,2,46.0,'BAIXO','PENDING'),
    (53,3,41.0,'BAIXO','PENDING'),
    (54,4,47.0,'BAIXO','PENDING'),
    (55,5,49.0,'BAIXO','PENDING'),
    (56,6,50.0,'BAIXO','PENDING'),
    (57,7,55.0,'MEDIO','PENDING'),
    (58,8,62.0,'MEDIO','PENDING'),
    (59,9,48.0,'BAIXO','PENDING'),
    (60,10,44.0,'BAIXO','REJECTED'),
    (61,11,46.0,'BAIXO','PENDING'),
    (62,12,47.0,'BAIXO','PENDING'),
    (63,13,43.0,'BAIXO','PENDING'),
    (64,14,41.0,'BAIXO','PENDING'),
    (65,15,50.0,'BAIXO','PENDING'),
    (66,16,69.0,'MEDIO','ACCEPTED'),
    (67,17,72.0,'MEDIO','ACCEPTED'),
    (68,18,48.0,'BAIXO','PENDING'),
    (69,19,46.0,'BAIXO','PENDING'),
    (70,20,47.0,'BAIXO','PENDING'),
    (71,21,42.0,'BAIXO','PENDING'),
    (72,22,49.0,'BAIXO','PENDING'),
    (73,23,50.0,'BAIXO','PENDING'),
    (74,24,45.0,'BAIXO','PENDING'),
    (75,25,44.0,'BAIXO','PENDING'),
    (76,1,46.0,'BAIXO','PENDING'),
    (77,2,48.0,'BAIXO','PENDING'),
    (78,3,52.0,'MEDIO','PENDING'),
    (79,4,70.0,'MEDIO','ACCEPTED'),
    (80,5,71.0,'MEDIO','ACCEPTED'),
    (81,6,43.0,'BAIXO','PENDING'),
    (1,7,41.0,'BAIXO','PENDING'),
    (2,8,44.0,'BAIXO','PENDING'),
    (3,9,47.0,'BAIXO','PENDING'),
    (4,10,48.0,'BAIXO','PENDING'),
    (5,11,46.0,'BAIXO','PENDING'),
    (6,12,49.0,'BAIXO','PENDING'),
    (7,13,50.0,'BAIXO','PENDING'),
    (8,14,45.0,'BAIXO','PENDING'),
    (9,15,46.0,'BAIXO','PENDING'),
    (10,16,52.0,'MEDIO','PENDING'),
    (11,17,60.0,'MEDIO','PENDING'),
    (12,18,62.0,'MEDIO','ACCEPTED'),
    (13,19,48.0,'BAIXO','PENDING'),
    (14,20,46.0,'BAIXO','PENDING'),
    (15,21,47.0,'BAIXO','PENDING'),
    (16,22,42.0,'BAIXO','PENDING'),
    (17,23,49.0,'BAIXO','PENDING'),
    (18,24,50.0,'BAIXO','PENDING'),
    (19,25,70.0,'MEDIO','PENDING'),
    (20,1,72.0,'MEDIO','ACCEPTED'),
    (21,2,44.0,'BAIXO','PENDING'),
    (22,3,46.0,'BAIXO','PENDING'),
    (23,4,47.0,'BAIXO','PENDING'),
    (24,5,43.0,'BAIXO','PENDING'),
    (25,6,41.0,'BAIXO','PENDING'),
    (26,7,48.0,'BAIXO','PENDING'),
    (27,8,50.0,'BAIXO','PENDING'),
    (28,9,52.0,'MEDIO','PENDING'),
    (29,10,68.0,'MEDIO','ACCEPTED'),
    (30,11,69.0,'MEDIO','ACCEPTED'),
    (31,12,43.0,'BAIXO','PENDING'),
    (32,13,46.0,'BAIXO','PENDING'),
    (33,14,47.0,'BAIXO','PENDING'),
    (34,15,42.0,'BAIXO','PENDING'),
    (35,16,49.0,'BAIXO','PENDING'),
    (36,17,50.0,'BAIXO','PENDING'),
    (37,18,45.0,'BAIXO','PENDING'),
    (38,19,46.0,'BAIXO','PENDING'),
    (39,20,52.0,'MEDIO','PENDING'),
    (40,21,60.0,'MEDIO','ACCEPTED'),
    (41,22,62.0,'MEDIO','ACCEPTED'),
    (42,23,43.0,'BAIXO','PENDING'),
    (43,24,46.0,'BAIXO','PENDING'),
    (44,25,47.0,'BAIXO','PENDING'),
    (45,1,79.0,'ALTO','ACCEPTED'),
    (46,2,85.0,'DESTAQUE','ACCEPTED'),
    (47,3,44.0,'BAIXO','PENDING'),
    (48,4,46.0,'BAIXO','PENDING'),
    (49,5,47.0,'BAIXO','PENDING'),
    (50,6,42.0,'BAIXO','PENDING'),
    (51,7,43.0,'BAIXO','PENDING'),
    (52,8,48.0,'BAIXO','PENDING'),
    (53,9,50.0,'BAIXO','PENDING'),
    (54,10,55.0,'MEDIO','PENDING'),
    (55,11,70.0,'MEDIO','ACCEPTED'),
    (56,12,72.0,'MEDIO','ACCEPTED'),
    (57,13,43.0,'BAIXO','PENDING'),
    (58,14,46.0,'BAIXO','PENDING'),
    (59,15,47.0,'BAIXO','PENDING'),
    (60,16,42.0,'BAIXO','PENDING'),
    (61,17,49.0,'BAIXO','PENDING'),
    (62,18,50.0,'BAIXO','PENDING'),
    (63,19,45.0,'BAIXO','PENDING'),
    (64,20,46.0,'BAIXO','PENDING'),
    (65,21,52.0,'MEDIO','PENDING'),
    (66,22,60.0,'MEDIO','ACCEPTED'),
    (67,23,62.0,'MEDIO','ACCEPTED'),
    (68,24,43.0,'BAIXO','PENDING'),
    (69,25,46.0,'BAIXO','PENDING'),
    (70,1,47.0,'BAIXO','PENDING'),
    (71,2,42.0,'BAIXO','PENDING'),
    (72,3,49.0,'BAIXO','PENDING'),
    (73,4,50.0,'BAIXO','PENDING'),
    (74,5,70.0,'MEDIO','PENDING'),
    (75,6,72.0,'MEDIO','ACCEPTED'),
    (76,7,43.0,'BAIXO','PENDING'),
    (77,8,46.0,'BAIXO','PENDING'),
    (78,9,47.0,'BAIXO','PENDING'),
    (79,10,42.0,'BAIXO','PENDING'),
    (80,11,49.0,'BAIXO','PENDING'),
    (81,12,50.0,'BAIXO','PENDING'),
    (1,13,52.0,'MEDIO','PENDING'),
    (2,14,60.0,'MEDIO','ACCEPTED'),
    (3,15,62.0,'MEDIO','ACCEPTED'),
    (4,16,43.0,'BAIXO','PENDING'),
    (5,17,46.0,'BAIXO','PENDING'),
    (6,18,47.0,'BAIXO','PENDING'),
    (7,19,42.0,'BAIXO','PENDING'),
    (8,20,49.0,'BAIXO','PENDING'),
    (9,21,50.0,'BAIXO','PENDING'),
    (10,22,45.0,'BAIXO','PENDING'),
    (11,23,46.0,'BAIXO','PENDING'),
    (12,24,52.0,'MEDIO','PENDING'),
    (13,25,60.0,'MEDIO','ACCEPTED'),
    (14,1,62.0,'MEDIO','ACCEPTED'),
    (15,2,43.0,'BAIXO','PENDING'),
    (16,3,46.0,'BAIXO','PENDING'),
    (17,4,47.0,'BAIXO','PENDING'),
    (18,5,42.0,'BAIXO','PENDING'),
    (19,6,49.0,'BAIXO','PENDING'),
    (20,7,50.0,'BAIXO','PENDING'),
    (21,8,52.0,'MEDIO','PENDING'),
    (22,9,60.0,'MEDIO','ACCEPTED'),
    (23,10,62.0,'MEDIO','ACCEPTED'),
    (24,11,43.0,'BAIXO','PENDING'),
    (25,12,46.0,'BAIXO','PENDING'),
    (26,13,47.0,'BAIXO','PENDING'),
    (27,14,42.0,'BAIXO','PENDING'),
    (28,15,49.0,'BAIXO','PENDING'),
    (29,16,50.0,'BAIXO','PENDING'),
    (30,17,70.0,'MEDIO','PENDING'),
    (31,18,72.0,'MEDIO','ACCEPTED'),
    (32,19,43.0,'BAIXO','PENDING'),
    (33,20,46.0,'BAIXO','PENDING'),
    (34,21,47.0,'BAIXO','PENDING'),
    (35,22,42.0,'BAIXO','PENDING'),
    (36,23,49.0,'BAIXO','PENDING'),
    (37,24,50.0,'BAIXO','PENDING'),
    (38,25,52.0,'MEDIO','PENDING'),
    (39,1,60.0,'MEDIO','ACCEPTED'),
    (40,2,62.0,'MEDIO','ACCEPTED'),
    (41,3,43.0,'BAIXO','PENDING'),
    (42,4,46.0,'BAIXO','PENDING'),
    (43,5,47.0,'BAIXO','PENDING'),
    (44,6,42.0,'BAIXO','PENDING'),
    (45,7,49.0,'BAIXO','PENDING'),
    (46,8,50.0,'BAIXO','PENDING'),
    (47,9,52.0,'MEDIO','PENDING'),
    (48,10,60.0,'MEDIO','ACCEPTED'),
    (49,11,62.0,'MEDIO','ACCEPTED'),
    (50,12,43.0,'BAIXO','PENDING');

    INSERT INTO candidate_match (fk_candidate, fk_vacancy, score, match_level, candidate_status) VALUES
    (82, 21, 78.0, 'ALTO', 'PENDING'),
    (83, 22, 80.0, 'ALTO', 'PENDING'),
    (84, 23, 77.0, 'ALTO', 'PENDING'),
    (85, 24, 79.0, 'ALTO', 'PENDING'),
    (86, 25, 81.0, 'ALTO', 'PENDING'),
    (87, 21, 76.0, 'ALTO', 'PENDING'),
    (88, 22, 78.0, 'ALTO', 'PENDING'),
    (89, 23, 80.0, 'ALTO', 'PENDING'),
    (90, 24, 77.0, 'ALTO', 'PENDING'),
    (91, 25, 79.0, 'ALTO', 'PENDING'),
    (92, 21, 81.0, 'ALTO', 'PENDING'),
    (93, 22, 76.0, 'ALTO', 'PENDING'),
    (94, 23, 78.0, 'ALTO', 'PENDING'),
    (95, 24, 80.0, 'ALTO', 'PENDING'),
    (96, 25, 77.0, 'ALTO', 'PENDING'),
    (97, 21, 79.0, 'ALTO', 'PENDING'),
    (98, 22, 81.0, 'ALTO', 'PENDING'),
    (99, 23, 76.0, 'ALTO', 'PENDING'),
    (100, 24, 78.0, 'ALTO', 'PENDING'),
    (101, 25, 80.0, 'ALTO', 'PENDING'),
    (102, 21, 77.0, 'ALTO', 'PENDING'),
    (103, 22, 79.0, 'ALTO', 'PENDING'),
    (104, 23, 81.0, 'ALTO', 'PENDING'),
    (105, 24, 76.0, 'ALTO', 'PENDING'),
    (106, 25, 78.0, 'ALTO', 'PENDING'),
    (107, 21, 80.0, 'ALTO', 'PENDING'),
    (108, 22, 77.0, 'ALTO', 'PENDING'),
    (109, 23, 79.0, 'ALTO', 'PENDING'),
    (110, 24, 81.0, 'ALTO', 'PENDING'),
    (111, 25, 76.0, 'ALTO', 'PENDING');

    -- ===========================
    -- Inserir candidatos novos
    -- ===========================
    INSERT INTO candidate (name, birth, phone_number, email, state, education, skills, experience) VALUES
    ('Candidato 82', '1990-01-01', '(11)90000-0082', 'candidato82@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '2 anos'),
    ('Candidato 83', '1991-02-02', '(11)90000-0083', 'candidato83@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '3 anos'),
    ('Candidato 84', '1992-03-03', '(11)90000-0084', 'candidato84@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '1 ano'),
    ('Candidato 85', '1993-04-04', '(11)90000-0085', 'candidato85@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '2 anos'),
    ('Candidato 86', '1994-05-05', '(11)90000-0086', 'candidato86@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '3 anos'),
    ('Candidato 87', '1995-06-06', '(11)90000-0087', 'candidato87@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '2 anos'),
    ('Candidato 88', '1996-07-07', '(11)90000-0088', 'candidato88@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '4 anos'),
    ('Candidato 89', '1990-08-08', '(11)90000-0089', 'candidato89@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '1 ano'),
    ('Candidato 90', '1991-09-09', '(11)90000-0090', 'candidato90@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '3 anos'),
    ('Candidato 91', '1992-10-10', '(11)90000-0091', 'candidato91@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '2 anos'),
    ('Candidato 92', '1993-11-11', '(11)90000-0092', 'candidato92@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '1 ano'),
    ('Candidato 93', '1994-12-12', '(11)90000-0093', 'candidato93@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '2 anos'),
    ('Candidato 94', '1995-01-13', '(11)90000-0094', 'candidato94@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '3 anos'),
    ('Candidato 95', '1996-02-14', '(11)90000-0095', 'candidato95@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '1 ano'),
    ('Candidato 96', '1990-03-15', '(11)90000-0096', 'candidato96@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '2 anos'),
    ('Candidato 97', '1991-04-16', '(11)90000-0097', 'candidato97@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '3 anos'),
    ('Candidato 98', '1992-05-17', '(11)90000-0098', 'candidato98@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '2 anos'),
    ('Candidato 99', '1993-06-18', '(11)90000-0099', 'candidato99@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '1 ano'),
    ('Candidato 100', '1994-07-19', '(11)90000-0100', 'candidato100@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '2 anos'),
    ('Candidato 101', '1995-08-20', '(11)90000-0101', 'candidato101@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '3 anos'),
    ('Candidato 102', '1996-09-21', '(11)90000-0102', 'candidato102@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '2 anos'),
    ('Candidato 103', '1990-10-22', '(11)90000-0103', 'candidato103@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '1 ano'),
    ('Candidato 104', '1991-11-23', '(11)90000-0104', 'candidato104@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '2 anos'),
    ('Candidato 105', '1992-12-24', '(11)90000-0105', 'candidato105@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '3 anos'),
    ('Candidato 106', '1993-01-25', '(11)90000-0106', 'candidato106@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '2 anos'),
    ('Candidato 107', '1994-02-26', '(11)90000-0107', 'candidato107@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '1 ano'),
    ('Candidato 108', '1995-03-27', '(11)90000-0108', 'candidato108@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '2 anos'),
    ('Candidato 109', '1996-04-28', '(11)90000-0109', 'candidato109@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '3 anos'),
    ('Candidato 110', '1990-05-29', '(11)90000-0110', 'candidato110@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '2 anos'),
    ('Candidato 111', '1991-06-30', '(11)90000-0111', 'candidato111@gmail.com', 'SP', 'Administração', 'Excel, Financeiro', '1 ano');

    -- ===========================
    -- Inserir perfis dos candidatos
    -- ===========================
    INSERT INTO candidate_profile (fk_candidate, raw_json, total_experience_years, main_seniority, main_stack, main_role, city, state, remote_preference, hard_skills, soft_skills) VALUES
    (82,'{}',2.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Power BI','Comunicação'),
    (83,'{}',3.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, ERP','Organização'),
    (84,'{}',1.0,'JUNIOR','Financeiro','Assistente','São Paulo','SP','presencial','Excel, Contabilidade','Proatividade'),
    (85,'{}',2.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Power BI','Comunicação'),
    (86,'{}',3.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, ERP','Organização'),
    (87,'{}',2.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Contabilidade','Proatividade'),
    (88,'{}',4.0,'SENIOR','Financeiro','Analista','São Paulo','SP','presencial','Excel, Power BI','Liderança'),
    (89,'{}',1.0,'JUNIOR','Financeiro','Assistente','São Paulo','SP','presencial','Excel, ERP','Comunicação'),
    (90,'{}',3.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Contabilidade','Organização'),
    (91,'{}',2.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Power BI','Comunicação'),
    (92,'{}',1.0,'JUNIOR','Financeiro','Assistente','São Paulo','SP','presencial','Excel, ERP','Proatividade'),
    (93,'{}',2.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Contabilidade','Comunicação'),
    (94,'{}',3.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Power BI','Organização'),
    (95,'{}',1.0,'JUNIOR','Financeiro','Assistente','São Paulo','SP','presencial','Excel, ERP','Proatividade'),
    (96,'{}',2.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Contabilidade','Comunicação'),
    (97,'{}',3.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Power BI','Organização'),
    (98,'{}',2.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, ERP','Proatividade'),
    (99,'{}',1.0,'JUNIOR','Financeiro','Assistente','São Paulo','SP','presencial','Excel, Contabilidade','Comunicação'),
    (100,'{}',2.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Power BI','Organização'),
    (101,'{}',3.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, ERP','Proatividade'),
    (102,'{}',2.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Contabilidade','Comunicação'),
    (103,'{}',1.0,'JUNIOR','Financeiro','Assistente','São Paulo','SP','presencial','Excel, Power BI','Organização'),
    (104,'{}',2.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, ERP','Proatividade'),
    (105,'{}',3.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Contabilidade','Comunicação'),
    (106,'{}',2.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Power BI','Organização'),
    (107,'{}',1.0,'JUNIOR','Financeiro','Assistente','São Paulo','SP','presencial','Excel, ERP','Proatividade'),
    (108,'{}',2.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Contabilidade','Comunicação'),
    (109,'{}',3.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, Power BI','Organização'),
    (110,'{}',2.0,'PLENO','Financeiro','Analista','São Paulo','SP','presencial','Excel, ERP','Proatividade'),
    (111,'{}',1.0,'JUNIOR','Financeiro','Assistente','São Paulo','SP','presencial','Excel, Contabilidade','Comunicação');

    -- ===========================
    -- Inserir candidate_match
    -- ===========================
    INSERT INTO candidate_match (fk_candidate, fk_vacancy, score, match_level, candidate_status) VALUES
    (82, 21, 78.0, 'ALTO', 'PENDING'),
    (83, 22, 80.0, 'ALTO', 'PENDING'),
    (84, 23, 77.0, 'ALTO', 'PENDING'),
    (85, 24, 79.0, 'ALTO', 'PENDING'),
    (86, 25, 81.0, 'ALTO', 'PENDING'),
    (87, 21, 76.0, 'ALTO', 'PENDING'),
    (88, 22, 78.0, 'ALTO', 'PENDING'),
    (89, 23, 80.0, 'ALTO', 'PENDING'),
    (90, 24, 77.0, 'ALTO', 'PENDING'),
    (91, 25, 79.0, 'ALTO', 'PENDING'),
    (92, 21, 81.0, 'ALTO', 'PENDING'),
    (93, 22, 76.0, 'ALTO', 'PENDING'),
    (94, 23, 78.0, 'ALTO', 'PENDING'),
    (95, 24, 80.0, 'ALTO', 'PENDING'),
    (96, 25, 77.0, 'ALTO', 'PENDING'),
    (97, 21, 79.0, 'ALTO', 'PENDING'),
    (98, 22, 81.0, 'ALTO', 'PENDING'),
    (99, 23, 76.0, 'ALTO', 'PENDING'),
    (100, 24, 78.0, 'ALTO', 'PENDING'),
    (101, 25, 80.0, 'ALTO', 'PENDING'),
    (102, 21, 77.0, 'ALTO', 'PENDING'),
    (103, 22, 79.0, 'ALTO', 'PENDING'),
    (104, 23, 81.0, 'ALTO', 'PENDING'),
    (105, 24, 76.0, 'ALTO', 'PENDING'),
    (106, 25, 78.0, 'ALTO', 'PENDING'),
    (107, 21, 80.0, 'ALTO', 'PENDING'),
    (108, 22, 77.0, 'ALTO', 'PENDING'),
    (109, 23, 79.0, 'ALTO', 'PENDING'),
    (110, 24, 81.0, 'ALTO', 'PENDING'),
    (111, 25, 76.0, 'ALTO', 'PENDING');


    -- ===========================
    -- Inserir 20 registros ALTO para TI
    -- ===========================
    INSERT INTO candidate_match (fk_candidate, fk_vacancy, score, match_level, candidate_status) VALUES
    (112, 1, 85.0, 'ALTO', 'PENDING'),
    (113, 2, 88.0, 'ALTO', 'PENDING'),
    (114, 3, 87.0, 'ALTO', 'PENDING'),
    (115, 4, 90.0, 'ALTO', 'PENDING'),
    (116, 5, 86.0, 'ALTO', 'PENDING'),
    (117, 1, 89.0, 'ALTO', 'PENDING'),
    (118, 2, 87.5, 'ALTO', 'PENDING'),
    (119, 3, 88.0, 'ALTO', 'PENDING'),
    (120, 4, 85.5, 'ALTO', 'PENDING'),
    (121, 5, 90.0, 'ALTO', 'PENDING'),
    (122, 1, 86.0, 'ALTO', 'PENDING'),
    (123, 2, 89.0, 'ALTO', 'PENDING'),
    (124, 3, 87.0, 'ALTO', 'PENDING'),
    (125, 4, 88.5, 'ALTO', 'PENDING'),
    (126, 5, 86.0, 'ALTO', 'PENDING'),
    (127, 1, 90.0, 'ALTO', 'PENDING'),
    (128, 2, 85.5, 'ALTO', 'PENDING'),
    (129, 3, 87.0, 'ALTO', 'PENDING'),
    (130, 4, 88.0, 'ALTO', 'PENDING'),
    (131, 5, 89.5, 'ALTO', 'PENDING');

    -- ===========================================
    -- Criar candidatos TI (112 a 131)
    -- ===========================================
    INSERT INTO candidate (name, birth, phone_number, email, state, education, skills, experience) VALUES
    ('TI Candidato 112', '1990-01-01', '(11)90000-0112', 'ti112@gmail.com', 'SP', 'Engenharia de Software', 'Java, Spring', '2 anos'),
    ('TI Candidato 113', '1991-02-02', '(11)90000-0113', 'ti113@gmail.com', 'SP', 'Engenharia de Software', 'Python, SQL', '3 anos'),
    ('TI Candidato 114', '1992-03-03', '(11)90000-0114', 'ti114@gmail.com', 'SP', 'Engenharia de Software', 'AWS, Docker', '1 ano'),
    ('TI Candidato 115', '1993-04-04', '(11)90000-0115', 'ti115@gmail.com', 'SP', 'Engenharia de Software', 'Java, Spring', '2 anos'),
    ('TI Candidato 116', '1994-05-05', '(11)90000-0116', 'ti116@gmail.com', 'SP', 'Engenharia de Software', 'Python, SQL', '3 anos'),
    ('TI Candidato 117', '1990-06-06', '(11)90000-0117', 'ti117@gmail.com', 'SP', 'Engenharia de Software', 'Java, Spring', '2 anos'),
    ('TI Candidato 118', '1991-07-07', '(11)90000-0118', 'ti118@gmail.com', 'SP', 'Engenharia de Software', 'Python, SQL', '3 anos'),
    ('TI Candidato 119', '1992-08-08', '(11)90000-0119', 'ti119@gmail.com', 'SP', 'Engenharia de Software', 'AWS, Docker', '1 ano'),
    ('TI Candidato 120', '1993-09-09', '(11)90000-0120', 'ti120@gmail.com', 'SP', 'Engenharia de Software', 'Java, Spring', '2 anos'),
    ('TI Candidato 121', '1994-10-10', '(11)90000-0121', 'ti121@gmail.com', 'SP', 'Engenharia de Software', 'Python, SQL', '3 anos'),
    ('TI Candidato 122', '1990-11-11', '(11)90000-0122', 'ti122@gmail.com', 'SP', 'Engenharia de Software', 'Java, Spring', '2 anos'),
    ('TI Candidato 123', '1991-12-12', '(11)90000-0123', 'ti123@gmail.com', 'SP', 'Engenharia de Software', 'Python, SQL', '3 anos'),
    ('TI Candidato 124', '1992-01-13', '(11)90000-0124', 'ti124@gmail.com', 'SP', 'Engenharia de Software', 'AWS, Docker', '1 ano'),
    ('TI Candidato 125', '1993-02-14', '(11)90000-0125', 'ti125@gmail.com', 'SP', 'Engenharia de Software', 'Java, Spring', '2 anos'),
    ('TI Candidato 126', '1994-03-15', '(11)90000-0126', 'ti126@gmail.com', 'SP', 'Engenharia de Software', 'Python, SQL', '3 anos'),
    ('TI Candidato 127', '1990-04-16', '(11)90000-0127', 'ti127@gmail.com', 'SP', 'Engenharia de Software', 'AWS, Docker', '1 ano'),
    ('TI Candidato 128', '1991-05-17', '(11)90000-0128', 'ti128@gmail.com', 'SP', 'Engenharia de Software', 'Java, Spring', '2 anos'),
    ('TI Candidato 129', '1992-06-18', '(11)90000-0129', 'ti129@gmail.com', 'SP', 'Engenharia de Software', 'Python, SQL', '3 anos'),
    ('TI Candidato 130', '1993-07-19', '(11)90000-0130', 'ti130@gmail.com', 'SP', 'Engenharia de Software', 'AWS, Docker', '1 ano'),
    ('TI Candidato 131', '1994-08-20', '(11)90000-0131', 'ti131@gmail.com', 'SP', 'Engenharia de Software', 'Java, Spring', '2 anos');

    -- ===========================================
    -- Criar candidate_profile para cada candidato
    -- ===========================================
    INSERT INTO candidate_profile (fk_candidate, raw_json, total_experience_years, main_seniority, main_stack, main_role, city, state, remote_preference, hard_skills, soft_skills) VALUES
    (112,'{}',2.0,'PLENO','Java','Backend','São Paulo','SP','remoto','Java, Spring','Comunicação'),
    (113,'{}',3.0,'PLENO','Python','Dados','São Paulo','SP','remoto','Python, SQL','Organização'),
    (114,'{}',1.0,'JUNIOR','AWS','DevOps','São Paulo','SP','remoto','AWS, Docker','Proatividade'),
    (115,'{}',2.0,'PLENO','Java','Backend','São Paulo','SP','remoto','Java, Spring','Comunicação'),
    (116,'{}',3.0,'PLENO','Python','Dados','São Paulo','SP','remoto','Python, SQL','Organização'),
    (117,'{}',2.0,'PLENO','Java','Backend','São Paulo','SP','remoto','Java, Spring','Comunicação'),
    (118,'{}',3.0,'PLENO','Python','Dados','São Paulo','SP','remoto','Python, SQL','Organização'),
    (119,'{}',1.0,'JUNIOR','AWS','DevOps','São Paulo','SP','remoto','AWS, Docker','Proatividade'),
    (120,'{}',2.0,'PLENO','Java','Backend','São Paulo','SP','remoto','Java, Spring','Comunicação'),
    (121,'{}',3.0,'PLENO','Python','Dados','São Paulo','SP','remoto','Python, SQL','Organização'),
    (122,'{}',2.0,'PLENO','Java','Backend','São Paulo','SP','remoto','Java, Spring','Comunicação'),
    (123,'{}',3.0,'PLENO','Python','Dados','São Paulo','SP','remoto','Python, SQL','Organização'),
    (124,'{}',1.0,'JUNIOR','AWS','DevOps','São Paulo','SP','remoto','AWS, Docker','Proatividade'),
    (125,'{}',2.0,'PLENO','Java','Backend','São Paulo','SP','remoto','Java, Spring','Comunicação'),
    (126,'{}',3.0,'PLENO','Python','Dados','São Paulo','SP','remoto','Python, SQL','Organização'),
    (127,'{}',1.0,'JUNIOR','AWS','DevOps','São Paulo','SP','remoto','AWS, Docker','Proatividade'),
    (128,'{}',2.0,'PLENO','Java','Backend','São Paulo','SP','remoto','Java, Spring','Comunicação'),
    (129,'{}',3.0,'PLENO','Python','Dados','São Paulo','SP','remoto','Python, SQL','Organização'),
    (130,'{}',1.0,'JUNIOR','AWS','DevOps','São Paulo','SP','remoto','AWS, Docker','Proatividade'),
    (131,'{}',2.0,'PLENO','Java','Backend','São Paulo','SP','remoto','Java, Spring','Comunicação');

    -- ===========================================
    -- Criar 20 registros ALTO em candidate_match
    -- ===========================================
    INSERT INTO candidate_match (fk_candidate, fk_vacancy, score, match_level, candidate_status) VALUES
    (112, 1, 85.0, 'ALTO', 'ACCEPTED'),
    (113, 2, 88.0, 'ALTO', 'ACCEPTED'),
    (114, 3, 87.0, 'ALTO', 'ACCEPTED'),
    (115, 4, 90.0, 'ALTO', 'ACCEPTED'),
    (116, 5, 86.0, 'ALTO', 'ACCEPTED'),
    (117, 1, 89.0, 'ALTO', 'ACCEPTED'),
    (118, 2, 87.5, 'ALTO', 'ACCEPTED'),
    (119, 3, 88.0, 'ALTO', 'ACCEPTED'),
    (120, 4, 85.5, 'ALTO', 'ACCEPTED'),
    (121, 5, 90.0, 'ALTO', 'ACCEPTED'),
    (122, 1, 86.0, 'ALTO', 'ACCEPTED'),
    (123, 2, 89.0, 'ALTO', 'ACCEPTED'),
    (124, 3, 87.0, 'ALTO', 'ACCEPTED'),
    (125, 4, 88.5, 'ALTO', 'ACCEPTED'),
    (126, 5, 86.0, 'ALTO', 'ACCEPTED'),
    (127, 1, 90.0, 'ALTO', 'ACCEPTED'),
    (128, 2, 85.5, 'ALTO', 'PENDING'),
    (129, 3, 87.0, 'ALTO', 'ACCEPTED'),
    (130, 4, 88.0, 'ALTO', 'ACCEPTED'),
    (131, 5, 89.5, 'ALTO', 'ACCEPTED');