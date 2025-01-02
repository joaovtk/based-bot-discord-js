package app.tk.beccatk.Commands;

import java.awt.Color;

import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Dust extends ListenerAdapter{
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        
        String commandName = event.getFullCommandName();
        
        if(commandName.isEmpty()){
            event.reply("Ops i have any error in api, try later...").queue();;
        }else {
            if(commandName.equals("dust dailycards")){
                event.reply("Command Dust Daily Cards is unavailable...").queue();
            }else if(commandName.equals("dust collection")){   
                kong.unirest.HttpResponse<JsonNode> response = Unirest.get("https://meme-api.com/gimme")
                .asJson();

                JsonNode body = response.getBody();
                String url = body.getObject().getString("url");

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Fun this is image")
                .setImage(url)
                .setColor(Color.GREEN);
                
                
                event.reply("Command Dust Daily Cards is unavailable...")
                .addEmbeds(embed.build()).queue();
            }
        }
    }
}
