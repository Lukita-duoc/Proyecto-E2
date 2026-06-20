package cl.duoc.empleados_service.service;

import cl.duoc.empleados_service.dto.SucursalDTO;
import cl.duoc.empleados_service.mapper.SucursalMapper;
import cl.duoc.empleados_service.model.Sucursal;
import cl.duoc.empleados_service.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private SucursalMapper mapper;

    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    public Sucursal findById(Long id) {
        return sucursalRepository.findById(id).orElse(null);
    }

    public Sucursal save(Sucursal s) {
        return sucursalRepository.save(s);
    }

    public void deleteById(Long id) {
        sucursalRepository.deleteById(id);
    }

    public Sucursal update(Long id, Sucursal s){
        Sucursal sucursal = sucursalRepository.findById(id).orElse(null);
        if(sucursal == null) return null;

        sucursal.setNombre(s.getNombre());
        sucursal.setCapacidad(s.getCapacidad());
        sucursal.setCiudad(s.getCiudad());

        return sucursalRepository.save(sucursal);
    }

    public SucursalDTO buscarDTO(Long id) {
        Sucursal sucursal = sucursalRepository.findById(id).orElse(null);
        if(sucursal == null) return null;
        return mapper.toDTO(sucursal);
    }

}
