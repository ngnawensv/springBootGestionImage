package cm.belrose.springBootGestionImage.services;

import cm.belrose.springBootGestionImage.entities.ImageModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ImageService {
    public void saveImageInBD(MultipartFile file) throws IOException;
    public Optional<ImageModel> getImageInBD(String imageName) throws IOException;
}
