## TABLA CLIENTE
    DROP TABLE cliente;

    CREATE TABLE cliente
    (
      cliente_rut bigint NOT NULL,
      cliente_apellido_m character varying(255),
      cliente_apellido_p character varying(255),
      cliente_nombres character varying(255),
      CONSTRAINT cliente_pkey PRIMARY KEY (cliente_rut)
    )
    WITH (
      OIDS=FALSE
    );
    ALTER TABLE cliente
      OWNER TO docker;

## TABLA SALDO
    DROP TABLE saldo;

    CREATE TABLE saldo
    (
      saldo_cliente_rut bigint NOT NULL,
      saldo_tipo_saldo_id bigint NOT NULL,
      CONSTRAINT fkbs2jxm1l6p2lxtbm3s5o9je7g FOREIGN KEY (saldo_tipo_saldo_id)
          REFERENCES tipo_saldo (id) MATCH SIMPLE
          ON UPDATE NO ACTION ON DELETE NO ACTION,
      CONSTRAINT fkgboo12w4utyocq63aej58op5a FOREIGN KEY (saldo_cliente_rut)
          REFERENCES cliente (cliente_rut) MATCH SIMPLE
          ON UPDATE NO ACTION ON DELETE NO ACTION,
      CONSTRAINT uk_fv7j027qdgsb0tjj6qjlpii41 UNIQUE (saldo_tipo_saldo_id)
    )
    WITH (
      OIDS=FALSE
    );
    ALTER TABLE saldo
      OWNER TO docker;

## TABLA TIPO_SALDO
    DROP TABLE tipo_saldo;

    CREATE TABLE tipo_saldo
    (
      id bigserial NOT NULL,
      fecha_transaccion timestamp without time zone NOT NULL,
      monto_transaccion bigint NOT NULL,
      tipo_transaccion character varying(255) NOT NULL,
      CONSTRAINT tipo_saldo_pkey PRIMARY KEY (id)
    )
    WITH (
      OIDS=FALSE
    );
    ALTER TABLE tipo_saldo
      OWNER TO docker;
