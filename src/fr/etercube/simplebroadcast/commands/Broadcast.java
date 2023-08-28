package fr.etercube.simplebroadcast.commands;

import fr.etercube.simplebroadcast.utils.Strings;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

// Importations nécessaires pour les fonctionnalités du plugin BungeeCord

public class Broadcast extends Command {

    // Crée une nouvelle commande "broadcast"
    public Broadcast() {
        super("broadcast");
    }

    // Fonction exécutée lorsque la commande est utilisée
    public void execute(CommandSender sender, String[] args) {
        // Vérifie si l'émetteur de la commande est un joueur
        if ((sender instanceof ProxiedPlayer)) {
            ProxiedPlayer p = (ProxiedPlayer) sender;

            // Vérifie si au moins 2 arguments sont fournis
            if (args.length >= 2) {
                // Récupère le type de message spécifié (titre ou chat)
                String messageType = args[1];

                // Construit le message à partir des arguments restants
                StringBuilder message = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    message.append(args[i]).append(" ");
                }

                // Effectue différentes actions en fonction du type de message
                switch (messageType) {
                    case "title":
                        // Diffuse un titre avec le message
                        broadcastTitle(message.toString());
                        break;
                    case "chat":
                        // Diffuse un message dans le chat
                        broadcastChatMessage(message.toString());
                        break;
                    default:
                        // Affiche un message d'erreur pour une utilisation correcte
                        TextComponent chatMessage = new TextComponent(Strings.color("&cUsage: /broadcast <title:chat> <message>"));
                        p.sendMessage(ChatMessageType.CHAT, chatMessage);
                        break;
                }
            }
        }
    }

    // Diffuse un titre à tous les joueurs en ligne
    private void broadcastTitle(String message) {
        Title title = ProxyServer.getInstance().createTitle()
                .title(TextComponent.fromLegacyText(Strings.color("&7&l≫ &c&lANNONCE &7&l≪")))
                .subTitle(TextComponent.fromLegacyText(message))
                .fadeIn(10)
                .stay(40)
                .fadeOut(10);

        // Envoie le titre à tous les joueurs
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            title.send(player);
        }
    }

    // Diffuse un message dans le chat à tous les joueurs en ligne
    private void broadcastChatMessage(String message) {
        TextComponent chatMessage = new TextComponent(Strings.color("&c&n&lAnnonce :&r\n" + message));

        // Envoie le message dans le chat à tous les joueurs
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            player.sendMessage(ChatMessageType.CHAT, chatMessage);
        }
    }
}
