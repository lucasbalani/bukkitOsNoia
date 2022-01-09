package com.lucasdeb.bukkitosnoia;

import com.sun.tools.javac.Main;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Getter
@Setter
public class bukkitOsNoia extends JavaPlugin {
    public Location localSpawn;
    public Location spawnNether;
    public Location spawnEnd;
    public Listeners classeListener = new Listeners();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(classeListener, this);

        getLogger().info("=========================================");
        getLogger().info("BukkitOsNoia em ação !");
        getLogger().info("Creditos - iTzLuquinhaRX_");
        getLogger().info("=========================================");

        saveDefaultConfig();
        saveConfig();
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        World mundo = player.getWorld(); // Variavel usada para pegar o mundo que o player está
        World mapaWorld = Bukkit.getWorld(getConfig().getString("nameworld"));
        World mapaNether = Bukkit.getWorld(getConfig().getString("worldnether"));
        World mapaEnd = Bukkit.getWorld(getConfig().getString("worldtheend"));

        //Trata comando /back
        if (command.getName().equalsIgnoreCase("back")){
            Location getDeadLocation = classeListener.getDeadLocation();

            player.teleport(getDeadLocation);
            player.sendMessage("§7[§5OsNoia§7] §fVoce foi teleportado ao local onde morreu. §7[§5§5!§7]");
        }

        //Trata comando /save
        if (command.getName().equalsIgnoreCase("save")){
            mapaWorld.save();
            mapaNether.save();
            mapaEnd.save();

            player.sendMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fSalvando mundo " + mapaWorld.getName() + " §7[§5!§7]");
            player.sendMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fSalvando mundo " + mapaNether.getName() + " §7[§5!§7]");
            player.sendMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fSalvando mundo " + mapaEnd.getName() + " §7[§5!§7]");
        }

        //Trata comando /spawn
        if (command.getName().equalsIgnoreCase("spawn")){

            if (mundo.getName().equalsIgnoreCase(getConfig().getString("worldnether"))){
                player.teleport(spawnNether);
                player.sendMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fVoce foi ao spawn no mundo [" + getConfig().getString("worldnether") + "] §7[§5!§7]");
            } else if (mundo.getName().equalsIgnoreCase(getConfig().getString("worldtheend"))) {
                player.teleport(spawnEnd);
                player.sendMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fVoce foi ao spawn no mundo [" + getConfig().getString("worldtheend") + "] §7[§5!§7]");
            } else if (mundo.getName().equalsIgnoreCase(getConfig().getString("nameworld"))){
                player.teleport(localSpawn);
                player.sendMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fVoce foi ao spawn no mundo [" + getConfig().getString("nameworld") + "] §7[§5!§7]");
            }else{
                player.sendMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fVoce nao está em um mundo válido, cheque a config.yml §7[§5!§7]");
            }
        }

        //Trata comando /setspawn
        if (command.getName().equalsIgnoreCase("setspawn")){

            if (mundo.getName().equalsIgnoreCase(getConfig().getString("worldnether"))){
                this.setSpawnNether(player.getLocation());
                player.sendMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fVoce setou o spawn no mundo [" + getConfig().getString("worldnether") + "] §7[§5!§7]");
            } else if (mundo.getName().equalsIgnoreCase(getConfig().getString("worldtheend"))) {
                this.setSpawnEnd(player.getLocation());
                player.sendMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fVoce setou o spawn no mundo [" + getConfig().getString("worldtheend") + "] §7[§5!§7]");
            } else if (mundo.getName().equalsIgnoreCase(getConfig().getString("nameworld"))){
                this.setLocalSpawn(player.getLocation());
                player.sendMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fVoce setou o spawn no mundo [" + getConfig().getString("nameworld") + "] §7[§5!§7]");
            }else{
                player.sendMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fVoce nao está em um mundo válido, cheque a config.yml §7[§5!§7]");
            }
        }

        //Trata comando /ofender
        if (command.getName().equalsIgnoreCase("ofender")){
                Bukkit.broadcastMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fVai toma no cu " + args[0] + ". By - " + player.getName() + " §7[§5!§7]");
        }

        //Trata comando /tempo
        if (command.getName().equalsIgnoreCase("tempo")){
            World world = Bukkit.getWorld(getConfig().getString("nameworld"));

            if (args[0].equalsIgnoreCase("day")){
                world.setTime(0);
                Bukkit.broadcastMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fAlterado tempo para dia. By - " + player.getName() + " §7[§5!§7]");
            } else if(args[0].equalsIgnoreCase("night")){
                world.setTime(19000);
                Bukkit.broadcastMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fAlterado tempo para noite. By - " + player.getName() + " §7[§5!§7]");
            } else {
                player.sendMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fTempo nao reconhecido. §7[§5!§7]");
            }
        }

        //Trata comando /reparar
        if (command.getName().equalsIgnoreCase("reparar")){
            ItemStack item = player.getInventory().getItemInMainHand();
            item.setDurability((short) 1);
            player.sendMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fVoce restaurou o item §7[§5!§7]");
        }

        //Trata comando /luz
        if (command.getName().equalsIgnoreCase("luz")){
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9000, 5));
            player.sendMessage("§7[§5" + getConfig().getString("prefixmessages") + "§7] §fVoce ligou a luz §7[§5!§7]");
        }

        return false;
    }

    public static bukkitOsNoia getPlugin(){
        return bukkitOsNoia.getPlugin(bukkitOsNoia.class);
    }
}
