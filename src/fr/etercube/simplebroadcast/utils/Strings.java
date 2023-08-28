package fr.etercube.simplebroadcast.utils;


import net.md_5.bungee.api.ChatColor;

public class Strings {
    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}