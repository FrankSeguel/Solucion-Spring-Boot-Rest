/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.multicaja.models;

import cl.multicaja.utils.reflection.BaseBean;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;

/**
 *
 * @author fseguel
 */
//@Entity
@Data
@Entity
@Table(name = "CLIENTE", uniqueConstraints = {
    @UniqueConstraint(columnNames = "CLIENTE_RUT")})
public class Cliente extends BaseBean {

    @Id
    @Column(name = "CLIENTE_RUT")
    private Long rut; // Del Rut solo grabo el numero sin diguito verificador. Ya que este se calcula

    @Column(name = "CLIENTE_NOMBRES")
    private String nombres;

    @Column(name = "CLIENTE_APELLIDO_P")
    private String apellidoP;

    @Column(name = "CLIENTE_APELLIDO_m")
    private String apellidoM;

    @OneToMany
    @JoinTable(name = "SALDO", joinColumns = {
        @JoinColumn(name = "SALDO_CLIENTE_RUT", referencedColumnName = "CLIENTE_RUT")},
             inverseJoinColumns = {
                @JoinColumn(name = "SALDO_TIPO_SALDO_ID", referencedColumnName = "ID")})
    private List<TipoSaldo> saldo;

    public Cliente() {
        this.saldo = new ArrayList<>();
    }
    
    

}
