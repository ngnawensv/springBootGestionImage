package cm.belrose.springBootGestionImage.Controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import cm.belrose.springBootGestionImage.dao.ImageDao;
import cm.belrose.springBootGestionImage.entities.ImageModel;
import cm.belrose.springBootGestionImage.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "image")
public class ImageUploadController {

    @Autowired
    private ImageService imageService;

    /**
     * This method is use to save image in BD
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public BodyBuilder saveImageInBD(@RequestParam("imageFile") MultipartFile file) throws IOException {
        imageService.saveImageInBD(file);
        return ResponseEntity.status(HttpStatus.OK);
    }

    /**
     * This method is use to retreive image in BD
     * @param imageName
     * @return
     * @throws IOException
     */
    @GetMapping(path = {"/get/{imageName}"})
    public ImageModel getImageInBD(@PathVariable("imageName") String imageName) throws IOException {
        return imageService.getImageInBD(imageName).get();
    }

    /**
     *
     * @param imageName
     * @return
     * @throws Exception
     */
    @GetMapping(path = {"/get1/{imageName}"},produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageInDiractory(@PathVariable("imageName") String imageName) throws Exception {
        return imageService.getImageInDirectory(imageName);
    }
}
