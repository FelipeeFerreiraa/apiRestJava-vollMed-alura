ALTER TABLE consultas ADD ativo TINYINT;

UPDATE consultas SET ativo = 1;

