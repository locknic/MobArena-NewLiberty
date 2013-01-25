package com.garbagemule.MobArena.custom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.garbagemule.MobArena.Messenger;

public class YamlFile {
    private final File file;
    private final FileConfiguration config;

    public YamlFile(File file) {
        this.file = file;
        this.config = new YamlConfiguration();

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
                Messenger.info("Created new file '" + file.getName() + "'.");
            } catch (IOException e) {
                Messenger.severe("Could not create file '" + file.getName() + "'!");
                e.printStackTrace();
            }
        }

        load();
    }

    public Set<String> getKeys(String key) {
        return config.getConfigurationSection(key).getKeys(false);
    }

    public String getString(String key) {
        return config.getString(key);
    }

    public List<String> getStringList(String key) {
        return config.getStringList(key);
    }

    public void load() {
        try {
            config.load(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
