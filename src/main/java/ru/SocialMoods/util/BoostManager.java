package ru.SocialMoods.util;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import ru.SocialMoods.Buyer;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Gestiona los multiplicadores económicos asociados a permisos de rango.
 * Los multiplicadores no se acumulan: se utiliza el mayor permiso que tenga el jugador.
 */
public class BoostManager {

    private static final double MIN_MULTIPLIER = 1.0;
    private static final double MAX_MULTIPLIER = 5.0;

    private final Buyer plugin;
    private final Map<String, RankBoost> rankBoosts = new LinkedHashMap<>();

    public BoostManager(Buyer plugin) {
        this.plugin = plugin;
        load();
    }

    /** Carga los rangos y limita cualquier multiplicador al intervalo x1.0-x5.0. */
    public final void load() {
        rankBoosts.clear();

        File file = new File(plugin.getDataFolder(), "boost.yml");
        if (!file.exists()) {
            plugin.saveResource("boost.yml", false);
        }

        Config config = new Config(file, Config.YAML);
        Map<String, Object> entries = config.getSection("boosts").getAllMap();

        for (Map.Entry<String, Object> entry : entries.entrySet()) {
            if (!(entry.getValue() instanceof Map<?, ?> values)) {
                plugin.getLogger().warning("La configuración del boost '" + entry.getKey() + "' no es válida.");
                continue;
            }

            Object permissionValue = values.get("permission");
            Object multiplierValue = values.get("multiplier");

            if (permissionValue == null || multiplierValue == null) {
                plugin.getLogger().warning("El boost '" + entry.getKey() + "' necesita permission y multiplier.");
                continue;
            }

            String permission = String.valueOf(permissionValue).trim();
            double multiplier;

            try {
                multiplier = Double.parseDouble(String.valueOf(multiplierValue));
            } catch (NumberFormatException exception) {
                plugin.getLogger().warning("Multiplicador no válido para el rango '" + entry.getKey() + "'.");
                continue;
            }

            if (permission.isEmpty() || !Double.isFinite(multiplier)) {
                plugin.getLogger().warning("Configuración no válida para el rango '" + entry.getKey() + "'.");
                continue;
            }

            double boundedMultiplier = Math.max(MIN_MULTIPLIER, Math.min(MAX_MULTIPLIER, multiplier));
            if (boundedMultiplier != multiplier) {
                plugin.getLogger().warning("El multiplicador de '" + entry.getKey()
                        + "' fue limitado a x" + boundedMultiplier + " (rango permitido: x1.0-x5.0).");
            }

            rankBoosts.put(entry.getKey(), new RankBoost(permission, boundedMultiplier));
        }

        plugin.getLogger().info("Se cargaron " + rankBoosts.size() + " boosts de rango.");
    }

    /** Devuelve el mayor boost permitido por los permisos que tenga el jugador. */
    public double getMultiplier(Player player) {
        double multiplier = MIN_MULTIPLIER;

        for (RankBoost boost : rankBoosts.values()) {
            if (player.hasPermission(boost.permission()) && boost.multiplier() > multiplier) {
                multiplier = boost.multiplier();
            }
        }

        return multiplier;
    }

    public String formatMultiplier(Player player) {
        return String.format(java.util.Locale.US, "x%.2f", getMultiplier(player));
    }

    private record RankBoost(String permission, double multiplier) {
    }
}
