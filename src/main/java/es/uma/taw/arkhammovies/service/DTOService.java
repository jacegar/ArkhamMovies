package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dto.DTO;

import java.util.ArrayList;
import java.util.List;

public abstract class DTOService <DTOClass,EntityClass>{

    protected List<DTOClass> entity2DTO (List<EntityClass> entidades) {
        List<DTOClass> lista = new ArrayList<>();
        for (EntityClass entidad : entidades) {
            DTO<DTOClass> clase = (DTO<DTOClass>) entidad;
            lista.add(clase.toDTO());
        }
        return lista;
    }

    protected DTOClass toDTO(EntityClass entidad) {
        DTO<DTOClass> clase = (DTO<DTOClass>) entidad;

        return clase.toDTO();
    }
}
