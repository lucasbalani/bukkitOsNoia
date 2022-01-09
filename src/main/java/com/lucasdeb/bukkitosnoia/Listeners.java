package com.lucasdeb.bukkitosnoia;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

@Getter
@Setter
public class Listeners implements Listener {

    Location deadLocation;

    @EventHandler
    public void onKick(PlayerKickEvent event){
        Player player = event.getPlayer();
        event.setLeaveMessage("§7[§5OsNoia§7] §cO player [" + player.getName() + "] Foi kikado, que ele respeite as regras da proxima vez §7[§5!§7]");
        event.setReason("§7§l[§5§lServidor OsNoiasBR§7§l \n\n" +
                        "§f Voce foi kikado do servidor, favor respeitar as regras");
    }


    //Seta o local de morte do player - Testar
    @EventHandler
    public void onDead(PlayerDeathEvent event){
        Player player = event.getEntity();
        Location localdeadLocation = player.getLocation();
        setDeadLocation(localdeadLocation);

        event.setDeathMessage("§7[§5OsNoia§7] §fVocê está morto, com certeza a culpa é do actiontuk §7[§5!§7]" );
        player.sendMessage("§7[§5OsNoia§7] §fVoce morreu, para voltar digite /back §7[§5!§7]");

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        player.sendMessage("§7[§5OsNoia§7] §fVai toma no cu action Foda-se §7[§5!§7]");
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ArrayList<Block> troncoArvore = new ArrayList<>();
        Block bloco = event.getClickedBlock();
        Location locationBlock = bloco.getLocation();
        ItemStack itemNaMao = player.getInventory().getItemInMainHand();

        //Testa se o item na mao é um machado
        if (itemNaMao.getType() == Material.IRON_AXE || itemNaMao.getType() == Material.WOODEN_AXE || itemNaMao.getType() == Material.DIAMOND_AXE || itemNaMao.getType() == Material.GOLDEN_AXE){
            //Testa se o bloco é o material Oak_Log
            if (bloco.getType() == Material.OAK_LOG || bloco.getType() == Material.DARK_OAK_LOG || bloco.getType() == Material.ACACIA_LOG || bloco.getType() == Material.BIRCH_LOG || bloco.getType() == Material.JUNGLE_LOG || bloco.getType() == Material.SPRUCE_LOG){

                //Montando o pilar que será destruido
                while (bloco.getType() == Material.OAK_LOG || bloco.getType() == Material.DARK_OAK_LOG || bloco.getType() == Material.ACACIA_LOG || bloco.getType() == Material.BIRCH_LOG || bloco.getType() == Material.JUNGLE_LOG || bloco.getType() == Material.SPRUCE_LOG){
                    troncoArvore.add(bloco); //Adiciona bloco na arrayList
                    locationBlock.setY(bloco.getY()+1); // Seta a localização do bloco pra y+1
                    bloco = locationBlock.getBlock(); // Seta nova posiçao do bloco

                    //Checa se o bloco é folha - Possivelmente esta estrutura será excluida mais a frente.
                    if (bloco.getType() == Material.OAK_LEAVES || bloco.getType() == Material.DARK_OAK_LEAVES || bloco.getType() == Material.ACACIA_LEAVES || bloco.getType() == Material.BIRCH_LEAVES || bloco.getType() == Material.JUNGLE_LEAVES || bloco.getType() == Material.SPRUCE_LEAVES){
                        break;
                    }
                }

                //Quebra o bloco presente na lista
                for(Block blocoEach : troncoArvore){
                    blocoEach.breakNaturally();
                    blocoEach.setType(Material.AIR);
                }

                player.sendMessage("§7[§5!§7] Árvore derrubada §7[§5!§7]");
            }
        }

    }
}
