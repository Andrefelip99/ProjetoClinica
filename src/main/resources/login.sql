-- Roles usadas pela aplicacao
INSERT INTO tb_role (authority) VALUES
    ('ROLE_ADMIN'),
    ('ROLE_MEDICO'),
    ('ROLE_ENFERMEIRO'),
    ('ROLE_TECNICO_ENFERMAGEM'),
    ('ROLE_ADMINISTRACAO')
ON CONFLICT (authority) DO NOTHING;

-- Funcionarios de exemplo. Todos usam o mesmo hash BCrypt de exemplo.
-- Troque o hash por um de uma senha conhecida antes de usar.
INSERT INTO funcionarios (nome, cpf, email, senha, ativo) VALUES
    ('Administrador', '00000000001', 'admin@exemplo.local', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', TRUE),
    ('Medico', '00000000002', 'medico@exemplo.local', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', TRUE),
    ('Enfermeiro', '00000000003', 'enfermeiro@exemplo.local', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', TRUE),
    ('Tecnico de Enfermagem', '00000000004', 'tecnico@exemplo.local', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', TRUE),
    ('Administracao', '00000000005', 'administracao@exemplo.local', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', TRUE)
ON CONFLICT DO NOTHING;

-- Associacoes entre cada funcionario e sua role
INSERT INTO funcionario_roles (funcionario_id, role_id)
SELECT f.id, r.id FROM funcionarios f, tb_role r
WHERE f.email = 'admin@exemplo.local' AND r.authority = 'ROLE_ADMIN'
ON CONFLICT DO NOTHING;

INSERT INTO funcionario_roles (funcionario_id, role_id)
SELECT f.id, r.id FROM funcionarios f, tb_role r
WHERE f.email = 'medico@exemplo.local' AND r.authority = 'ROLE_MEDICO'
ON CONFLICT DO NOTHING;

INSERT INTO funcionario_roles (funcionario_id, role_id)
SELECT f.id, r.id FROM funcionarios f, tb_role r
WHERE f.email = 'enfermeiro@exemplo.local' AND r.authority = 'ROLE_ENFERMEIRO'
ON CONFLICT DO NOTHING;

INSERT INTO funcionario_roles (funcionario_id, role_id)
SELECT f.id, r.id FROM funcionarios f, tb_role r
WHERE f.email = 'tecnico@exemplo.local' AND r.authority = 'ROLE_TECNICO_ENFERMAGEM'
ON CONFLICT DO NOTHING;

INSERT INTO funcionario_roles (funcionario_id, role_id)
SELECT f.id, r.id FROM funcionarios f, tb_role r
WHERE f.email = 'administracao@exemplo.local' AND r.authority = 'ROLE_ADMINISTRACAO'
ON CONFLICT DO NOTHING;
