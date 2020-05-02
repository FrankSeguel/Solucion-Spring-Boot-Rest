package cl.multicaja.services;

import cl.multicaja.models.Cliente;
import cl.multicaja.repository.ClientesRepository;
import cl.multicaja.utils.exception.BusinessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fseguel
 */
@Service
public class ClientesService {

    private static final Logger logger = LoggerFactory.getLogger(ClientesService.class);

    @Autowired
    ClientesRepository repository;

    @Transactional
    public Cliente createOrUpdateCliente(Cliente entity) throws BusinessException {
        Optional<Cliente> cliente = repository.findById(entity.getRut());
        logger.debug(entity.toString());

        if (cliente.isPresent()) {
            Cliente newEntity = cliente.get();
            newEntity = repository.save(newEntity);
            repository.flush();
            return newEntity;
        } else {
            logger.debug("Grabando : " + entity.toString());
            entity = repository.save(entity);
            repository.flush();
            return entity;
        }
    }

    public List<Cliente> getClientes() {
        List<Cliente> clientes = repository.findAll();

        if (clientes.size() > 0) {
            return clientes;
        } else {
            return new ArrayList<>();
        }
    }

    public Cliente getClienteByRutId(Long id) throws BusinessException {
        Optional<Cliente> cliente = repository.findById(id);

        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new BusinessException("No existe un registro del id " + id + " Cliente");
        }

    }

    public void deleteClienteByRutId(Long id) throws BusinessException {
        Optional<Cliente> cliente = repository.findById(id);
        if (cliente.isPresent()) {
            repository.deleteById(id);
            repository.flush();
        } else {
            throw new BusinessException("No existe un registro del id " + id + " Cliente");
        }
    }

}
