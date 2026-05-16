package cl.duoc.empleados_service.service;

import cl.duoc.empleados_service.dto.EmpleadoDTO;
import cl.duoc.empleados_service.mapper.EmpleadoMapper;
import cl.duoc.empleados_service.model.Empleado;
import cl.duoc.empleados_service.model.Sucursal;
import cl.duoc.empleados_service.repository.EmpleadoRepository;
import cl.duoc.empleados_service.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private EmpleadoMapper mapper;

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public Empleado findById(Long id){
        return empleadoRepository.findById(id).orElse(null);
    }

    public Empleado save(Empleado e){
        Sucursal s = sucursalRepository.findById(e.getSucursal().getIdSucursal()).orElse(null);
        e.setSucursal(s);
        return empleadoRepository.save(e);
    }

    public void deleteById(Long id) {
        empleadoRepository.deleteById(id);
    }

    public Empleado update(Long id, Empleado empleado){
        Empleado e = empleadoRepository.findById(id).orElse(null);
        if(e == null) return null;
        Sucursal s = sucursalRepository.findById(empleado.getSucursal().getIdSucursal()).orElse(null);
        if(s == null) return null;
        e.setNombre(empleado.getNombre());
        e.setApellido(empleado.getApellido());
        e.setCorreo(empleado.getCorreo());
        e.setCargo(empleado.getCargo());
        e.setFechaContrato(empleado.getFechaContrato());
        e.setSucursal(empleado.getSucursal());

        return empleadoRepository.save(e);
    }

    public List<EmpleadoDTO> listaDetallada() {
        List<Empleado> listado = empleadoRepository.findAll();
        List<EmpleadoDTO> listaDTO = new ArrayList<>();

        for (Empleado e : listado) {
            EmpleadoDTO dto = mapper.toDTO(e);
            listaDTO.add(dto);
        }

        return listaDTO;
    }
}
