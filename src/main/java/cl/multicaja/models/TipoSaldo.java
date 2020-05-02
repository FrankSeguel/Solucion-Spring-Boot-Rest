/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.multicaja.models;

import cl.multicaja.utils.reflection.BaseBean;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;

/**
 *
 * @author fseguel
 */
//@Entity
@Data
@Entity(name = "ForeignKeyAssoEntity")
@Table(name = "TIPO_SALDO", uniqueConstraints = {
    @UniqueConstraint(columnNames = "ID")})
public class TipoSaldo extends BaseBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "TIPO_TRANSACCION", nullable = false)
    private String tipo;

    @Column(name = "MONTO_TRANSACCION", nullable = false)
    private Long monto;

    @Column(name = "FECHA_TRANSACCION", nullable = false)
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date fecha;

}
