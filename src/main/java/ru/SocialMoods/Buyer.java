package ru.SocialMoods;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import ru.SocialMoods.autobuyer.AutoBuyerAPI;
import ru.SocialMoods.autobuyer.AutoBuyerListener;
import ru.SocialMoods.command.BuyerCommand;
import ru.SocialMoods.model.ShopItem;
import ru.SocialMoods.util.TimerTask;

public class Buyer extends PluginBase {

    private final Map<Integer, ShopItem> shopItems = new HashMap<>();
    private Config config;
    private ScheduledExecutorService scheduler;
    private AutoBuyerAPI autoBuyerAPI;

    public Buyer() {
    }

    @Override
    public void onEnable() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            
            File configFile = new File(getDataFolder(), "config.yml");
            if (!configFile.exists()) {
                saveResource("config.yml", false);
            }
            
            config = new Config(configFile, Config.YAML);
            
            autoBuyerAPI = new AutoBuyerAPI(this);
            
            loadShopItems();
            
            getServer().getPluginManager().registerEvents(new AutoBuyerListener(autoBuyerAPI, this), this);
            this.getServer().getCommandMap().register("buyer", new BuyerCommand(this));
            
            startTimerTask();
            
            this.getLogger().info(TextFormat.GREEN + "NeoBuyer " + TextFormat.YELLOW + "v" + this.getDescription().getVersion() + TextFormat.GREEN + " ¡Activado correctamente!");
            this.getLogger().info(TextFormat.AQUA + "-----------------------------------");
            this.getLogger().info(TextFormat.YELLOW + "Sigueme en:");
            this.getLogger().info(TextFormat.BLUE + "• Telegram: " + TextFormat.WHITE + "proximamente");
            this.getLogger().info(TextFormat.BLUE + "• VKontakte: " + TextFormat.WHITE + "proximamente");
            this.getLogger().info(TextFormat.BLUE + "• GitHub: " + TextFormat.WHITE + "https://github.com/BrunoJaegerK");
            this.getLogger().info(TextFormat.AQUA + "-----------------------------------");
        } catch (Exception e) {
            getLogger().error("Error al activar el plugin", e);
        }
    }

    private void startTimerTask() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new TimerTask(this), 0, getConfig().getInt("update-period", 60), TimeUnit.MINUTES);
    }

    public Map<Integer, ShopItem> getShopItems() {
        return shopItems;
    }

    public void loadShopItems() {
        shopItems.clear();
        for (String key : config.getKeys()) {
            if (isItemSection(key)) {
                try {
                    int slot = Integer.parseInt(key);
                    int itemId = config.getInt(key + ".itemId");
                    int count = config.getInt(key + ".count");
                    int price = config.getInt(key + ".price");
                    String url = config.getString(key + ".url", "");

                    ShopItem shopItem = new ShopItem(itemId, count, price, url);
                    shopItems.put(slot, shopItem);
                    getLogger().debug("Artículo cargado: " + itemId + " en la ranura " + slot);
                } catch (NumberFormatException e) {
                    getLogger().warning("Formato de clave no válido para el elemento: " + key);
                }
            }
        }
        getLogger().info("Cargado " + shopItems.size() + " artículos para la tienda");
    }
    
    private boolean isItemSection(String key) {
        try {
            Integer.parseInt(key);
            return config.exists(key + ".itemId") && config.exists(key + ".count") && config.exists(key + ".price");
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public AutoBuyerAPI getAutoBuyerAPI() {
        return autoBuyerAPI;
    }

    @Override
    public void onDisable() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }
}