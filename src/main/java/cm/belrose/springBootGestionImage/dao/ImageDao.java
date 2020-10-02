package cm.belrose.springBootGestionImage.dao;

import cm.belrose.springBootGestionImage.entities.ImageModel;
import cm.belrose.springBootGestionImage.exceptions.ExceptionHandleControllerAdvice;
import cm.belrose.springBootGestionImage.exceptions.SpringBootGestionImageException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ngnawen Samuel
 */
@Repository
public interface ImageDao extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name) throws SpringBootGestionImageException;
}
