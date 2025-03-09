package com.pagos.pagos_service.service;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

@Service
public class FileWriterService {

    private static final String FILE_PATH = "notifications.log";

    public void writeToFile(String content) {
        try {
            Files.write(Paths.get(FILE_PATH),
                    (LocalDateTime.now() + " - " + content + "\n").getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
