package cl.duoc.empleados_service.controller;

import cl.duoc.empleados_service.model.Sucursal;
import cl.duoc.empleados_service.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<?>> listar() {
        List<Sucursal> s = sucursalService.findAll();
        return ResponseEntity.ok(s);
    }
}
