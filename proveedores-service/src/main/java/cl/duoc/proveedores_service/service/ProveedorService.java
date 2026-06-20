package cl.duoc.proveedores_service.service;

import cl.duoc.proveedores_service.repository.ProveedorRepository;
import cl.duoc.proveedores_service.dto.ProveedorDTO;
import cl.duoc.proveedores_service.mapper.ProveedorMapper;
import cl.duoc.proveedores_service.model.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    ProveedorMapper mapper;

    public List<Proveedor> findAll(){
        return proveedorRepository.findAll();
    }

    public Proveedor findById(Long id){
        return proveedorRepository.findById(id).orElse(null);
    }

    public Proveedor save(Proveedor proveedor){
        return proveedorRepository.save(proveedor);
    }

    public void deleteById(Long id) {
        proveedorRepository.deleteById(id);
    }

    public Proveedor update (Proveedor p, Long id){
        Proveedor actualizar = proveedorRepository.findById(id).orElse(null);
        if(actualizar == null) return null;

        actualizar.setRazonSocial(p.getRazonSocial());
        actualizar.setCorreo(p.getCorreo());
        actualizar.setTelefono(p.getTelefono());
        actualizar.setPais(p.getPais());
        actualizar.setTipoProveedor(p.getTipoProveedor());

        return proveedorRepository.save(actualizar);
    }

    public List<ProveedorDTO> listaDetallada() {
        List<Proveedor> listado = proveedorRepository.findAll();
        List<ProveedorDTO> listaDTO = new ArrayList<>();

        for (Proveedor p : listado) {
            ProveedorDTO dto = mapper.toDTO(p);
            listaDTO.add(dto);
        }
        return listaDTO;
    }

    //Listar por pais del proveedor
    public List<Proveedor> findByPais(String pais) {
        return proveedorRepository.findByPais(pais);
    }
}
