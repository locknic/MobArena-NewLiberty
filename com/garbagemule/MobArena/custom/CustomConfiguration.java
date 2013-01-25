package com.garbagemule.MobArena.custom;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.garbagemule.MobArena.MobArena;

public class CustomConfiguration {
    private final YamlFile file;

    public CustomConfiguration(MobArena plugin) {
        this.file = new YamlFile(new File(plugin.getDataFolder(), "customconfig.yml"));

        file.load();
    }

    public List<ItemStack> getBoosts(Player player) {
        String group = "";
        for (String key : file.getKeys("boosts")) {
            if (PermissionsEx.getUser(player).has("mobarena.boosts." + key)) {
                group = key;
                break;
            }
        }

        if (group.isEmpty()) {
            return null;
        }

        List<ItemStack> boosts = new ArrayList<ItemStack>();
        for (String entry : file.getStringList("boosts." + group)) {
            String[] split = entry.split(":");
            boosts.add(new ItemStack(Material.getMaterial(Integer.parseInt(split[0])), Integer.parseInt(split[2]), Short.parseShort(split[1])));
        }

        return boosts;
    }
}
