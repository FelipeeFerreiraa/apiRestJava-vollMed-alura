CREATE TABLE consultas (
    id BIGINT NOT NULL AUTO_INCREMENT,

    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,

    horario DATETIME NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_consultas_paciente
        FOREIGN KEY (paciente_id)
        REFERENCES pacientes(id),

    CONSTRAINT fk_consultas_medico
        FOREIGN KEY (medico_id)
        REFERENCES medicos(id)
);
