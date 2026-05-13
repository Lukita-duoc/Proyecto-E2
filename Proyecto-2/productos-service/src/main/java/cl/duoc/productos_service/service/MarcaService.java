package cl.duoc.productos_service.service;

import cl.duoc.productos_service.dto.MarcaDTO;
import cl.duoc.productos_service.mapper.MarcaMapper;
import cl.duoc.productos_service.model.Marca;
import cl.duoc.productos_service.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private MarcaMapper mapper;

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Marca findById(Long id) {
        return marcaRepository.findById(id).orElse(null);
    }

    public Marca save(Marca marca) {
        return marcaRepository.save(marca);
    }

    public void deleteById(Long id) {
        marcaRepository.deleteById(id);
    }

    public Marca update(Long id, Marca m) {
        Marca marca = marcaRepository.findById(id).orElse(null);
        if(marca == null) return null;

        marca.setNombre(m.getNombre());
        marca.setPaisOrigen(m.getPaisOrigen());

        return marcaRepository.save(marca);
    }

    public List<MarcaDTO> listaDetallada() {
        List<Marca> listado = marcaRepository.findAll();
        List<MarcaDTO> listadoDTO = new ArrayList<>();

        for (Marca m : listado) {
            MarcaDTO dto = mapper.toDTO(m);
            listadoDTO.add(dto);
        }
        return listadoDTO;
    }

}
