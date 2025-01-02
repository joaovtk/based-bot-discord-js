package app.tk.beccatk;

import java.util.EnumSet;

import app.tk.beccatk.Commands.Dust;
import app.tk.beccatk.Commands.Ping;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        
        String token = dotenv.get("TOKEN");

        JDA jda = JDABuilder.createDefault(token, EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.DIRECT_MESSAGES))
            .addEventListeners(new Ping())
            .addEventListeners(new Dust())
            .build();
        deployCommands(jda);
    }

    public static void deployCommands(JDA jda){
        /*
         dailycards and collection are firts implements commands to test 
        */
        SubcommandData dcards = new SubcommandData("dailycards", "Daily Anime Cards Commands, is offer 3 special bundle when granted one 4 star each 5 spawns");
        SubcommandData collection = new SubcommandData("collection", "Show your Anime Cards, sort by rarity and name");


        CommandData dust = Commands.slash("dust", "Dust Commands").addSubcommands(dcards).addSubcommands(collection);
        
        jda.upsertCommand(dust).queue();
        jda.updateCommands().addCommands(dust).queue();

                
    }
}