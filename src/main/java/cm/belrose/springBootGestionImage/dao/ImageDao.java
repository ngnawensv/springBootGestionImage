package cm.belrose.springBootGestionImage.dao;

import cm.belrose.springBootGestionImage.entities.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageDao extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
}
