package cl.duoc.clientes_service.service;

import cl.duoc.clientes_service.dto.ClienteDTO;
import cl.duoc.clientes_service.mapper.ClienteMapper;
import cl.duoc.clientes_service.model.Cliente;
import cl.duoc.clientes_service.model.Empresa;
import cl.duoc.clientes_service.repository.ClienteRepository;
import cl.duoc.clientes_service.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ClienteMapper mapper;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);

    }

    public Cliente save(Cliente c){
        Empresa e = empresaRepository.findById(c.getEmpresa().getEmpresaId()).orElse(null);
        c.setEmpresa(e);
        return clienteRepository.save(c);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente update(Cliente c, Long id) {
        Cliente actualizar = clienteRepository.findById(id).orElse(null);
        if(actualizar == null) return null;

        actualizar.setRut(c.getRut());
        actualizar.setNombre(c.getNombre());
        actualizar.setApellido(c.getApellido());
        actualizar.setCorreo(c.getCorreo());
        actualizar.setTelefono(c.getTelefono());
        actualizar.setEmpresa(c.getEmpresa());

        return clienteRepository.save(actualizar);
    }

    public ClienteDTO buscarDTO(Long id) {
        Cliente c = clienteRepository.findById(id).orElse(null);
        if(c == null) return null;
        ClienteDTO dto = mapper.toDTO(c);
        return dto;
    }

    //Encontrar al cliente por el rut
    public Cliente findByRut(int rut) {
        return clienteRepository.findByRut(rut).orElse(null);
    }

    //Listar por id de empresa
    public List<Cliente> findByIdempresa(Long idEmpresa) {

        List<Cliente> cliente = new ArrayList<>();

        List<Cliente> allClient = clienteRepository.findAll();

        for (Cliente c : allClient) {
            if(c.getEmpresa() != null && c.getEmpresa().getEmpresaId().equals(idEmpresa)){
                cliente.add(c);
            }
        }

        return cliente;
    }

}
