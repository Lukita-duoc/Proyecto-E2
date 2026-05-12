package cl.duoc.clientes_service.service;

import cl.duoc.clientes_service.dto.EmpresaDTO;
import cl.duoc.clientes_service.mapper.EmpresaMapper;
import cl.duoc.clientes_service.model.Cliente;
import cl.duoc.clientes_service.model.Empresa;
import cl.duoc.clientes_service.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaMapper mapper;

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public EmpresaDTO findById(Long id) {
        Empresa e = empresaRepository.findById(id).orElse(null);
        return mapper.toDTO(e);
    }

    public Empresa save(Empresa e) {
        return empresaRepository.save(e);
    }

    public void delete(Long id) {
        empresaRepository.deleteById(id);
    }

    public Empresa update(Empresa e, Long id) {
        Empresa actualizar = empresaRepository.findById(id).orElse(null);
        if(actualizar == null) return null;

        actualizar.setNombreEmpresa(e.getNombreEmpresa());
        actualizar.setTipoEmpresa(e.getTipoEmpresa());
        actualizar.setRegion(e.getRegion());

        return empresaRepository.save(actualizar);

    }



}
