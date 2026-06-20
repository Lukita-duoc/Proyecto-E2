package cl.duoc.clientes_service.mapper;

import cl.duoc.clientes_service.dto.EmpresaDTO;
import cl.duoc.clientes_service.model.Empresa;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {

    public EmpresaDTO toDTO(Empresa e) {
        if(e == null) return null;
        EmpresaDTO dto = new EmpresaDTO();

        dto.setEmpresaId(e.getEmpresaId());
        dto.setNombreEmpresa(e.getNombreEmpresa());
        dto.setTipoEmpresa(e.getTipoEmpresa());
        dto.setRegion(e.getRegion());

        return dto;
    }
}
