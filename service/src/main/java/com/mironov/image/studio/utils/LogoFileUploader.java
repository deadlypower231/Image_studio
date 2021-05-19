package com.mironov.image.studio.utils;

import com.mironov.image.studio.entities.Tournament;
import com.mironov.image.studio.entities.User;
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
        String tournamentName = tournament.getName();
        String filePath = LOGOS_FOLDER_PATH + tournamentName + IMAGE_EXTENSION;
        URL dFileUrl = ResourceUtils.getURL(LOGOS_FOLDER_PATH);
        File defaultFile = new File(dFileUrl.getPath() + LOGOS_FILE_NAME_TOURNAMENT);
        File tournamentLogo;
        try {
            tournamentLogo = ResourceUtils.getFile(filePath);
        } catch (FileNotFoundException e) {
            URL fileUrl = ResourceUtils.getURL(LOGOS_FOLDER_PATH);
            tournamentLogo = new File(
                    fileUrl.getPath() + tournamentName + IMAGE_EXTENSION);
        }
        BufferedImage image = ImageIO.read(defaultFile);
        ImageIO.write(image, "jpg", tournamentLogo);
    }

    public void createLogoTournament(MultipartFile file, Tournament tournament) throws IOException {
        if (file != null && !file.isEmpty()) {
            String tournamentName = tournament.getName();
            String filePath = LOGOS_FOLDER_PATH + tournamentName + IMAGE_EXTENSION;
            File tournamentLogo;
            try {
                tournamentLogo = ResourceUtils.getFile(filePath);
            } catch (FileNotFoundException e) {
                URL fileUrl = ResourceUtils.getURL(LOGOS_FOLDER_PATH);
                tournamentLogo = new File(
                        fileUrl.getPath() + tournamentName + IMAGE_EXTENSION);
            }
            Path path = Paths.get(tournamentLogo.getPath());
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);
        }
    }

    public void createImageUser(MultipartFile file, User user) throws IOException {
        if (file != null && !file.isEmpty()) {
            String userName = user.getUsername();
            String filePath = LOGOS_FOLDER_PATH + userName + IMAGE_EXTENSION;
            File tournamentLogo;
            try {
                tournamentLogo = ResourceUtils.getFile(filePath);
            } catch (FileNotFoundException e) {
                URL fileUrl = ResourceUtils.getURL(LOGOS_FOLDER_PATH);
                tournamentLogo = new File(
                        fileUrl.getPath() + userName + IMAGE_EXTENSION);
            }
            Path path = Paths.get(tournamentLogo.getPath());
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);
        }
    }

}