package cl.duoc.clientes_service.service;

import cl.duoc.clientes_service.dto.EmpresaDTO;
import cl.duoc.clientes_service.mapper.EmpresaMapper;
import cl.duoc.clientes_service.model.Cliente;
import cl.duoc.clientes_service.model.Empresa;
import cl.duoc.clientes_service.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Empresa findById(Long id) {
        return empresaRepository.findById(id).orElse(null);
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

    public List<EmpresaDTO> listaDetallada() {
        List<Empresa> listado = empresaRepository.findAll();
        List<EmpresaDTO> listaDTO = new ArrayList<>();

        for (Empresa e : listado) {
            EmpresaDTO dto = mapper.toDTO(e);
            listaDTO.add(dto);
        }
        return listaDTO;
    }



}
