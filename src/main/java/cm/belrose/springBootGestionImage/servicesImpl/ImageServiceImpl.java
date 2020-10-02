package cm.belrose.springBootGestionImage.servicesImpl;

import cm.belrose.springBootGestionImage.dao.ImageDao;
import cm.belrose.springBootGestionImage.entities.ImageModel;
import cm.belrose.springBootGestionImage.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;


    @Override
    public void saveImageInBD(MultipartFile file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
        imageDao.save(img);
    }
    @Override
    public Optional<ImageModel> getImageInBD(String imageName) throws IOException {
        ImageModel retrievedImage = imageDao.findByName(imageName).get();
        ImageModel img = new ImageModel(retrievedImage.getName(), retrievedImage.getType(), decompressBytes(retrievedImage.getPicByte()));
        return Optional.of(img);
    }


    /**
     * With this method we get the image which is storing in directory
     * @param imageName
     * @return
     * @throws Exception
     */
    @Override
    public byte[] getImageInDirectory(String imageName) throws Exception {
        //ImageModel imageModel= imageDao.findById(id).get();
        //String imageName=imageModel.getName();
        System.out.println("imageName "+imageName);
        File file=new File(System.getProperty("user.home")+"/Pictures/"+imageName);
        Path path= Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }



    /**
     * With this method, we compress the image bytes before storing it in the database
     * @param data
     * @return
     */
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    /**
     * This method help us to uncompress the image bytes before returning it to the angular application
     * @param data
     * @return
     */
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        System.out.println("Decompressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public ImageDao getImageDao() {
        return imageDao;
    }

    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }
}
