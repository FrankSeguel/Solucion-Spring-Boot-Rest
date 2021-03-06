/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.multicaja.controller;

import cl.multicaja.models.Cliente;
import cl.multicaja.models.TipoSaldo;
import cl.multicaja.services.ClientesService;
import cl.multicaja.services.SaldosService;
import cl.multicaja.utils.exception.BusinessException;
import java.util.Date;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fseguel
 */
@CrossOrigin
@Transactional
@RestController
@RequestMapping(value = "/clientes")
public class ClientesController {

    private static final Logger logger = LoggerFactory.getLogger(ClientesController.class);

    @Autowired
    ClientesService clienteService;

    @Autowired
    SaldosService saldosService;

    private static final String ABONOS = "ABONOS";
    private static final String RETIROS = "RETIROS";

    /**
     *
     * Operaciones Basicas de Crear, Modificar y Eliminar Clientes
     *
     */
    @PutMapping( consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente entity) throws BusinessException {
        Cliente updated = clienteService.createOrUpdateCliente(entity);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente entity) throws BusinessException {
        Cliente updated = clienteService.createOrUpdateCliente(entity);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteClienteById(@PathVariable("id") Long id) throws BusinessException {
        clienteService.deleteClienteByRutId(id);
        return HttpStatus.OK;
    }

    /**
     * Operaciones Basicas de Listar Todos y Filtro
     *
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> list = clienteService.getClientes();
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id) throws BusinessException {
        Cliente entity = clienteService.getClienteByRutId(id);
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Operaciones del negocio . En este servicio podemos consultar el saldo de
     * un cliente
     *
     * @param id
     * @return JSON
     * @throws cl.multicaja.utils.exception.BusinessException
     */
    @GetMapping(path = "/saldo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSaldoCliente(@PathVariable("id") Long id) throws BusinessException {
        Cliente cliente = clienteService.getClienteByRutId(id);
        int saldo = 0;
        for (TipoSaldo ts : cliente.getSaldo()) {
            if (ABONOS.equals(ts.getTipo())) {
                saldo = saldo + ts.getMonto().intValue();
            } else {
                saldo = saldo - ts.getMonto().intValue();
            }
        }

        logger.info("saldo " + saldo);
        String saldojson = "{ \"saldo\" : " + saldo + "}";
        return new ResponseEntity<>(saldojson, HttpStatus.OK);
    }

    /**
     * Operaciones del negocio . En este servicio podemos consultar todos los
     * movimientos de un cliente
     *
     * @param id
     * @return List< TipoSaldo >
     * @throws cl.multicaja.utils.exception.BusinessException
     */
    @RequestMapping(value = "/movimientos", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @GetMapping("/movimientos/{id}")
    public ResponseEntity<List<TipoSaldo>> getMovimientos(@PathVariable("id") Long id) throws BusinessException {
        Cliente cliente = clienteService.getClienteByRutId(id);
        return new ResponseEntity<>(cliente.getSaldo(), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Operaciones del negocio . En este servicio podemos ingresar abonos a un
     * cliente
     *
     * @param id
     * @param monto
     * @return HttpStatus
     * @throws cl.multicaja.utils.exception.BusinessException
     */
    @PostMapping("/abonos/{id}/{monto}")
    public HttpStatus ingresarAbonos(@PathVariable("id") Long id, @PathVariable("monto") Long monto) throws BusinessException {

        Cliente cl = clienteService.getClienteByRutId(id);

        TipoSaldo ts = new TipoSaldo();
        ts.setFecha(new Date());
        ts.setId(new Long(cl.getSaldo().size() + 1));
        ts.setMonto(monto);
        ts.setTipo(ABONOS);

        cl.getSaldo().add(ts);

        saldosService.createOrUpdateTipoSaldo(ts);
        return HttpStatus.OK;
    }

    /**
     * Operaciones del negocio . En este servicio podemos ingresar retiros a un
     * cliente
     *
     * @param id
     * @param monto
     * @return HttpStatus
     * @throws cl.multicaja.utils.exception.BusinessException
     */
    @PostMapping("/retiros/{id}/{monto}")
    public HttpStatus ingresarRetiros(@PathVariable("id") Long id, @PathVariable("monto") Long monto) throws BusinessException {

        Cliente cl = clienteService.getClienteByRutId(id);

        TipoSaldo ts = new TipoSaldo();
        ts.setFecha(new Date());
        ts.setId(new Long(cl.getSaldo().size() + 1));
        ts.setMonto(monto);
        ts.setTipo(RETIROS);

        cl.getSaldo().add(ts);

        saldosService.createOrUpdateTipoSaldo(ts);
        return HttpStatus.OK;
    }
}
