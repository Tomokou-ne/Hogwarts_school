package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    @Value("${application.avatars.folder}")
    private String avatarDirectory;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }

    public void uploadAvatar(long studentId, MultipartFile avatarFile) throws IOException {
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        Student student = studentService.toStudent(studentDTO);

        Path avatarPath = Path.of(avatarDirectory, studentId + "." + getExtension(avatarFile.getOriginalFilename()));
        Files.createDirectories(avatarPath.getParent());
        Files.deleteIfExists(avatarPath);

        try (InputStream is = avatarFile.getInputStream();
             OutputStream os = Files.newOutputStream(avatarPath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }

        Avatar avatar = findAvatar(studentId);

        avatar.setStudent(student);
        avatar.setFilePath(avatarPath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(generateAvatarPreview(avatarPath));

        avatarRepository.save(avatar);
    }

    private byte[] generateAvatarPreview(Path avatarPath) throws IOException {
        try (InputStream is = Files.newInputStream(avatarPath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(avatarPath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }

    public Avatar findAvatar(long studentId) {
        return avatarRepository.findAvatarByStudentId(studentId).orElse(new Avatar());
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}