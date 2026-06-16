package de.gruppe_D.features.dbkonfigurieren.infrastructure;

import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenModel;
import de.gruppe_D.features.dbkonfigurieren.interfaces.LokalDbKonfigurierenDAO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DateiSpeichernDbKonfigurierenDaoFileImpl implements LokalDbKonfigurierenDAO {
    private final Path configFile;

    public DateiSpeichernDbKonfigurierenDaoFileImpl() {
        this.configFile = resolveConfigPath();
    }

    private Path resolveConfigPath() {

        String os = System.getProperty("os.name").toLowerCase();
        String home = System.getProperty("user.home");

        Path configDir;

        if (os.contains("win")) {

            String appData = System.getenv("APPDATA");
            configDir = Paths.get(appData, "jobtracer");

        } else if (os.contains("mac")) {

            configDir = Paths.get(home, "Library", "Application Support", "jobtracer");

        } else {

            configDir = Paths.get(home, ".config", "jobtracer");
        }

        try {
            Files.createDirectories(configDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return configDir.resolve("jobtracer.properties");
    }

    @Override
    public void saveDatei(DbKonfigurierenModel config) {

        Properties props = new Properties();

        props.setProperty("username", config.getUsername());
        props.setProperty("password", config.getPassword());
        props.setProperty("port", config.getPort());
        props.setProperty("hostname", config.getHostname());

        try (OutputStream out = Files.newOutputStream(configFile)) {

            props.store(out, "Database Config");

        } catch (IOException e) {
            throw new RuntimeException("Konnte Config nicht speichern", e);
        }
    }

    @Override
    public DbKonfigurierenModel loadDatei() {

        if (!Files.exists(configFile)) {
            return null;
        }

        Properties props = new Properties();

        try (InputStream in = Files.newInputStream(configFile)) {

            props.load(in);

            return new DbKonfigurierenModel(props.getProperty("username"), props.getProperty("password"), props.getProperty("port"), props.getProperty("hostname"));

        } catch (IOException e) {
            throw new RuntimeException("Konnte Config nicht laden", e);
        }
    }
}