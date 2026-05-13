package cl.duoc.empleados_service.controller;

import cl.duoc.empleados_service.model.Empleado;
import cl.duoc.empleados_service.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<?>> listar() {
        List<Empleado> e = empleadoService.findAll();
        return ResponseEntity.ok(e);
    }
}
