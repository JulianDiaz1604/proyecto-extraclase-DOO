package edu.uco.artdly.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.artdly.domain.ArtworkDTO;


public interface ArtworkDAO {

    void create(ArtworkDTO artwork);

    List<ArtworkDTO> find(ArtworkDTO artwork);
    
    List<ArtworkDTO> findAll();

    void update(ArtworkDTO artwork);

    void delete(UUID id);
    
}

