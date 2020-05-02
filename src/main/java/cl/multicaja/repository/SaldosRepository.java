/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.multicaja.repository;

import cl.multicaja.models.TipoSaldo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author fseguel
 */
public interface SaldosRepository extends JpaRepository<TipoSaldo, Long> {

}
