package app.tk.beccatk;

import dev.arbjerg.lavalink.client.Helpers;
import dev.arbjerg.lavalink.client.LavalinkClient;
import dev.arbjerg.lavalink.client.NodeOptions;

public class Lavalink {

    private LavalinkClient client;

    public Lavalink(String token){
        this.client = new LavalinkClient(Helpers.getUserIdFromToken(token));
        this.client.addNode(new NodeOptions.Builder().setServerUri("ws://localhost:2333").setPassword("developer").setName("beccatk").build());
    
    }
    public LavalinkClient getClient(){
        return this.client;
    }
}
