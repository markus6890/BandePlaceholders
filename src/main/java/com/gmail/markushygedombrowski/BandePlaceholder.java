package com.gmail.markushygedombrowski;

import com.staxitech.bande.Bande;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

import static org.bukkit.Bukkit.getServer;

public class BandePlaceholder extends PlaceholderExpansion {
    private Bande bande;
    @Override
    public @NotNull String getIdentifier() {
        return "BandePlaceholder";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Markus Hyge Dombrowski";
    }

    @Override
    public @NotNull String getVersion() {
        return "1";
    }

    @Override
    public String getRequiredPlugin() {
        return "Bande"; //
    }
    @Override
    public boolean canRegister() { //
        return (bande = (Bande) Bukkit.getPluginManager().getPlugin(getRequiredPlugin())) != null;
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        String bande_name = bande.bandeAPI().playerGetBande(getServer().getPlayer(player.getName()));
        String bande_level = bande.bandeAPI().getBandeValue(bande_name, "Level");
        String bande_penge = bande.bandeAPI().getBandeValue(bande_name, "Penge");
        if (identifier.equalsIgnoreCase("name")) {
            if(bande_name == null) {
                return "Ingen bande";
            }
            return bande_name;

        }
        if (identifier.equalsIgnoreCase("level")) {
            if(bande_level == null) {
                return "0";
            }
            return bande_level;
        }
        if (identifier.equalsIgnoreCase("penge")) {
            if(bande_penge == null) {
                return "0";
            }
            String pattern = "###,###.##";
            DecimalFormat df = new DecimalFormat(pattern);
            return df.format(Integer.parseInt(bande_penge));
        }
        return null;
    }
}
