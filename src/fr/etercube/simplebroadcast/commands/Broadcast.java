package fr.etercube.simplebroadcast.commands;

import fr.etercube.simplebroadcast.utils.Strings;
import net.md_5.bungee.api.*;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class Broadcast extends Command {

    public Broadcast() {
        super("broadcast");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(Strings.color("&cUsage: /broadcast <title|chat> <message>"));
            return;
        }

        String messageType = args[0];
        String message = String.join(" ", args).substring(messageType.length() + 1);

        if (messageType.equalsIgnoreCase("title")) {
            broadcastTitle(message);
        } else if (messageType.equalsIgnoreCase("chat")) {
            broadcastChatMessage(message);
        } else {
            sender.sendMessage(Strings.color("&cInvalid message type. Use 'title' or 'chat'."));
        }
    }

    // Diffuse un titre à tous les joueurs en ligne
    private void broadcastTitle(String message) {
        Title title = ProxyServer.getInstance().createTitle()
                .title(TextComponent.fromLegacyText(Strings.color("&7&l≫ &c&lANNONCE &7&l≪")))
                .subTitle(TextComponent.fromLegacyText(message))
                .fadeIn(10)
                .stay(80)
                .fadeOut(10);

        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            title.send(player);
        }
    }

    // Diffuse un message dans le chat à tous les joueurs en ligne
    private void broadcastChatMessage(String message) {
        TextComponent chatMessage = new TextComponent(Strings.color("&c&n&lAnnonce :&r\n" + message));

        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            player.sendMessage(ChatMessageType.CHAT, chatMessage);
        }
    }
}
