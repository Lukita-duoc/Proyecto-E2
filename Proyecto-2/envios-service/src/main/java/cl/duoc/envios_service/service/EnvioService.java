package cl.duoc.envios_service.service;

import cl.duoc.envios_service.dto.ClienteDTO;
import cl.duoc.envios_service.dto.EnvioDTO;
import cl.duoc.envios_service.dto.OrdenDTO;
import cl.duoc.envios_service.feign.ClienteFeign;
import cl.duoc.envios_service.feign.OrdenFeign;
import cl.duoc.envios_service.mapper.EnvioMapper;
import cl.duoc.envios_service.model.Envio;
import cl.duoc.envios_service.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private EnvioMapper mapper;

    @Autowired
    private ClienteFeign clienteFeign;

    @Autowired
    private OrdenFeign ordenFeign;

    public List<Envio> findAll() {
        return envioRepository.findAll();
    }

    public Envio findById(Long id) {
        return envioRepository.findById(id).orElse(null);
    }

    public Envio save(Envio e) {
        OrdenDTO orden = ordenFeign.buscarPorIdDTO(e.getOrdenId());
        if(orden == null) return null;
        return envioRepository.save(e);
    }

    public void deleteById(Long id) {
        envioRepository.deleteById(id);
    }

    public Envio update(Long id, Envio e) {
        Envio envio = envioRepository.findById(id).orElse(null);
        if(envio == null) return null;

        envio.setTransportista(e.getTransportista());
        envio.setOrigen(e.getOrigen());
        envio.setDestino(e.getDestino());
        envio.setEstadoEnvio(e.getEstadoEnvio());
        envio.setFechaSalida(e.getFechaSalida());
        envio.setOrdenId(e.getOrdenId());

        return envioRepository.save(envio);
    }

    public EnvioDTO buscarDTO(Long id) {
        Envio envio = envioRepository.findById(id).orElse(null);
        if(envio == null) return null;

        EnvioDTO dto = mapper.toDTO(envio);
        OrdenDTO orden = ordenFeign.buscarPorIdDTO(envio.getOrdenId());

        if(orden != null) {
            dto.setTotal(orden.getTotal());
            dto.setNombreCliente(orden.getNombreCompleto());
            dto.setCorreoCliente(orden.getCorreo());
        }

        return dto;
    }

}
