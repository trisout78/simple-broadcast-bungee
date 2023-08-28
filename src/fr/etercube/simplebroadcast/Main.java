package fr.etercube.simplebroadcast;
import fr.etercube.simplebroadcast.commands.Broadcast;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import fr.etercube.simplebroadcast.utils.Strings;

public class Main extends Plugin{
    @Override
    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Broadcast());


    }
}
