package cm.belrose.springBootGestionImage.services;

import cm.belrose.springBootGestionImage.entities.ImageModel;
import cm.belrose.springBootGestionImage.exceptions.SpringBootGestionImageException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


/**
 * @author Ngnawen Samuel
 */
public interface ImageService {
    public void saveImageInBD(MultipartFile file) throws SpringBootGestionImageException,IOException;

    public Optional<ImageModel> getImageInBD(String imageName) throws SpringBootGestionImageException;

    //public byte[] getImageInDirectory(Long id) throws Exception;
    public byte[] getImageInDirectory(String imageName) throws SpringBootGestionImageException,IOException;
}
