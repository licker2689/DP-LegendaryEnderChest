package com.darksoldier1404.dlec;

import com.darksoldier1404.dlec.commands.DLECCommand;
import com.darksoldier1404.dlec.events.DLECEvent;
import com.darksoldier1404.dlec.functions.DLECFunction;
import com.darksoldier1404.dppc.utils.ColorUtils;
import com.darksoldier1404.dppc.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class LegendaryEnderChest extends JavaPlugin {
    private static LegendaryEnderChest plugin;
    public YamlConfiguration config;
    public String prefix;
    public int defaultSlot;
    public int maxPages;
    public static HashSet<UUID> opened = new HashSet<>();
    public static final Map<UUID, YamlConfiguration> udata = new HashMap<>();


    public static LegendaryEnderChest getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        config = ConfigUtils.loadDefaultPluginConfig(plugin);
        prefix = ColorUtils.applyColor(Objects.requireNonNull(config.getString("Settings.prefix")));
        defaultSlot = config.getInt("Settings.DefaultSlot");
        maxPages = config.getInt("Settings.MaxPages");
        plugin.getServer().getPluginManager().registerEvents(new DLECEvent(), plugin);
        Objects.requireNonNull(getCommand("엔더창고")).setExecutor(new DLECCommand());
    }


    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(DLECFunction::saveAndLeave);
        ConfigUtils.savePluginConfig(plugin, config);
    }
}
