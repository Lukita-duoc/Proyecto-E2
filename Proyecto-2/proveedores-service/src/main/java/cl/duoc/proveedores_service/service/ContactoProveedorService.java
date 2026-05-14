package cl.duoc.proveedores_service.service;

import cl.duoc.proveedores_service.model.Proveedor;
import cl.duoc.proveedores_service.repository.ContactoProveedorRepository;
import cl.duoc.proveedores_service.dto.ContactoProveedorDTO;
import cl.duoc.proveedores_service.mapper.ContactoProveedorMapper;
import cl.duoc.proveedores_service.model.ContactoProveedor;
import cl.duoc.proveedores_service.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactoProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ContactoProveedorRepository conProRepository;

    @Autowired
    private ContactoProveedorMapper mapper;

    public List<ContactoProveedor> findAll(){
        return conProRepository.findAll();
    }

    public ContactoProveedor findById(Long id){
        return conProRepository.findById(id).orElse(null);
    }

    public ContactoProveedor save(ContactoProveedor cop){
        Proveedor p = proveedorRepository.findById(cop.getProveedor().getId_proveedor()).orElse(null);
        cop.setProveedor(p);
        return conProRepository.save(cop);
    }

    public void deleteById(Long id) {
        conProRepository.deleteById(id);
    }

    public ContactoProveedor update (ContactoProveedor cop, Long id){
        ContactoProveedor actualizar = conProRepository.findById(id).orElse(null);
        if(actualizar == null) return null;

        actualizar.setNombre_contacto(cop.getNombre_contacto());
        actualizar.setCargo(cop.getCargo());
        actualizar.setTelefono(cop.getTelefono());
        actualizar.setProveedor(cop.getProveedor());

        return conProRepository.save(actualizar);

    }

    public List<ContactoProveedorDTO> listaDetallada(){
        List<ContactoProveedor> listado = conProRepository.findAll();
        List<ContactoProveedorDTO> listaDTO = new ArrayList<>();

        for (ContactoProveedor cop : listado){
            ContactoProveedorDTO dto = mapper.toDTO(cop);
            listaDTO.add(dto);
        }

        return listaDTO;
    }


}
