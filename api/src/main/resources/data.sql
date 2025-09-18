INSERT INTO users (name, email, password, area, level_access)
VALUES
    ('Admin User', 'admin@email.com', '123', 'TI', 'ADMIN'),
    ('RH User', 'rh@email.com', '123', 'RH', 'HR'),
    ('Manager User', 'manager@email.com', '123', 'Gestão', 'MANAGER');

INSERT INTO vacancies (
    position, period, work_model, requirements, contract_type, salary, location, opening_justification, area, fk_manager
) VALUES
      ('Desenvolvedor Backend', 'Integral', 'ON_SITE', 'Java, Spring Boot', 'CLT', 7000.00, 'São Paulo', 'Aumento de demanda', 'TI', 1),
      ('Analista de RH', 'Parcial', 'REMOTE', 'Experiência em recrutamento', 'PJ', 4000.00, 'Remoto', 'Substituição', 'RH', 2),
      ('Designer UX', 'Integral', 'HYBRID', 'Figma, UX Research', 'Estágio', 2000.00, 'Belo Horizonte', 'Novo projeto', 'UX', 3);