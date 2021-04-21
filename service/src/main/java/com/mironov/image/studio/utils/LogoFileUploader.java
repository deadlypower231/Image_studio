package com.mironov.image.studio.utils;

import com.mironov.image.studio.entities.Tournament;
import lombok.experimental.UtilityClass;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class LogoFileUploader {

    private static final String IMAGE_EXTENSION = ".jpg";
    private static final String LOGOS_FOLDER_PATH = "classpath:static/images/";
    private static final String LOGOS_FILE_NAME_TOURNAMENT = "tournament_default.jpg";

    public void defaultLogoTournament(Tournament tournament) throws IOException {
        String userName = tournament.getName();
        String filePath = new StringBuilder(LOGOS_FOLDER_PATH).append(userName).append(IMAGE_EXTENSION).toString();
        URL dFileUrl = ResourceUtils.getURL(LOGOS_FOLDER_PATH);
        File defaultFile = new File(new StringBuilder(dFileUrl.getPath()).append(LOGOS_FILE_NAME_TOURNAMENT).toString());
        File tournamentLogo;
        try {
            tournamentLogo = ResourceUtils.getFile(filePath);
        } catch (FileNotFoundException e) {
            URL fileUrl = ResourceUtils.getURL(LOGOS_FOLDER_PATH);
            tournamentLogo = new File(
                    new StringBuilder(fileUrl.getPath()).append(userName).append(IMAGE_EXTENSION).toString());
        }
        BufferedImage image = ImageIO.read(defaultFile);
        ImageIO.write(image, "jpg", tournamentLogo);
    }

    public void createLogoTournament(MultipartFile file, Tournament tournament) throws IOException {
        if (file != null && !file.isEmpty()) {
            String userName = tournament.getName();
            String filePath = new StringBuilder(LOGOS_FOLDER_PATH).append(userName).append(IMAGE_EXTENSION).toString();
            File tournamentLogo;
            try {
                tournamentLogo = ResourceUtils.getFile(filePath);
            } catch (FileNotFoundException e) {
                URL fileUrl = ResourceUtils.getURL(LOGOS_FOLDER_PATH);
                tournamentLogo = new File(
                        new StringBuilder(fileUrl.getPath()).append(userName).append(IMAGE_EXTENSION).toString());
            }
            Path path = Paths.get(tournamentLogo.getPath());
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);
        }
    }

}
