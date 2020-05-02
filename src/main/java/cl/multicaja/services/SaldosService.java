package cl.multicaja.services;

import cl.multicaja.models.TipoSaldo;
import cl.multicaja.repository.SaldosRepository;
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
public class SaldosService {

    private static final Logger logger = LoggerFactory.getLogger(SaldosService.class);

    @Autowired
    SaldosRepository repository;

    @Transactional
    public TipoSaldo createOrUpdateTipoSaldo(TipoSaldo entity) throws BusinessException {
        Optional<TipoSaldo> tipoSaldo = repository.findById(entity.getId());
        if (tipoSaldo.isPresent()) {
            TipoSaldo newEntity = tipoSaldo.get();
            newEntity = repository.save(newEntity);
            repository.flush();
            return newEntity;
        } else {
            entity = repository.save(entity);
            repository.flush();
            return entity;
        }
    }

    public List<TipoSaldo> getTipoSaldos() {
        List<TipoSaldo> tipoSaldo = repository.findAll();

        if (tipoSaldo.size() > 0) {
            return tipoSaldo;
        } else {
            return new ArrayList<>();
        }
    }

    public TipoSaldo getTipoSaldoById(Long id) throws BusinessException {
        Optional<TipoSaldo> tipoSaldo = repository.findById(id);

        if (tipoSaldo.isPresent()) {
            return tipoSaldo.get();
        } else {
            throw new BusinessException("No existe un registro del id " + id + " Tipo Saldo");
        }

    }

    public void deleteTipoSaldoById(Long id) throws BusinessException {
        Optional<TipoSaldo> tipoSaldo = repository.findById(id);
        if (tipoSaldo.isPresent()) {
            repository.deleteById(id);
            repository.flush();
        } else {
            throw new BusinessException("No existe un registro del id " + id + " Tipo Saldo");
        }
    }
}
