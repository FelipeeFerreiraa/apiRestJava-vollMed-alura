create table medicos (

    id BIGINT NOT NULL AUTO_INCREMENT,

    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    crm VARCHAR(20) NOT NULL UNIQUE,

    especialidade VARCHAR(50) NOT NULL,

    logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(60) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    cidade VARCHAR(60) NOT NULL,
    uf CHAR(2) NOT NULL,
    
    numero VARCHAR(20),
    complemento VARCHAR(100),
    
    PRIMARY KEY(id)
);