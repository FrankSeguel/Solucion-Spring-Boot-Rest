INSERT INTO PUBLIC.CLIENTE (CLIENTE_RUT, CLIENTE_NOMBRES, CLIENTE_APELLIDO_P, CLIENTE_APELLIDO_m) 
    VALUES (111, 'Francisco', 'Seguel', 'Arriagada');


INSERT INTO PUBLIC.TIPO_SALDO
    ("id","fecha_transaccion","monto_transaccion","tipo_transaccion")
    VALUES(1,  '01-05-2020 11:34:24'::timestamp ,1000, 'ABONOS');
INSERT INTO PUBLIC.TIPO_SALDO
    ("id","fecha_transaccion","monto_transaccion","tipo_transaccion")
    VALUES(2,  '01-05-2020 12:34:24'::timestamp ,3000, 'ABONOS');
INSERT INTO PUBLIC.TIPO_SALDO
    ("id","fecha_transaccion","monto_transaccion","tipo_transaccion")
    VALUES(3,  '01-05-2020 13:34:24'::timestamp ,4000, 'ABONOS');
INSERT INTO PUBLIC.TIPO_SALDO
    ("id","fecha_transaccion","monto_transaccion","tipo_transaccion")
    VALUES(4,  '01-05-2020 14:34:24'::timestamp ,5000, 'ABONOS');
INSERT INTO PUBLIC.TIPO_SALDO
    ("id","fecha_transaccion","monto_transaccion","tipo_transaccion")
    VALUES(5,  '01-05-2020 15:34:24'::timestamp ,2000, 'RETIROS');

INSERT INTO PUBLIC.SALDO ("saldo_cliente_rut","saldo_tipo_saldo_id")
VALUES(111,  1);
INSERT INTO PUBLIC.SALDO ("saldo_cliente_rut","saldo_tipo_saldo_id")
VALUES(111,  2);
INSERT INTO PUBLIC.SALDO ("saldo_cliente_rut","saldo_tipo_saldo_id")
VALUES(111,  3);
INSERT INTO PUBLIC.SALDO ("saldo_cliente_rut","saldo_tipo_saldo_id")
VALUES(111,  4);
INSERT INTO PUBLIC.SALDO ("saldo_cliente_rut","saldo_tipo_saldo_id")
VALUES(111,  5);
