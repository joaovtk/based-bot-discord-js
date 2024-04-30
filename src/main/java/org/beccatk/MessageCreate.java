package org.beccatk;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateAvatarEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;

public class MessageCreate extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        Message message = event.getMessage();
        String content = message.getContentDisplay();
        String prefix = System.getenv("PREFIX");
        if(content.startsWith(prefix) && !message.getAuthor().isBot()){
            String[] args = content.trim().replace(prefix, "").split(" ");
            String command = args[0];
            args = Arrays.copyOfRange(args, 1, args.length);
            System.out.println(command);
            if(command.equals("ping")){
                message.getChannel().sendMessage("Pong").queue();
            }else if(command.equals("avatar")){
                if(args.length == 0){
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle(String.format("Icon do %s", message.getAuthor().getName()));
                    embed.setImage(message.getAuthor().getAvatarUrl());
                    message.getChannel().sendMessageEmbeds(embed.build()).queue();
                }else {
                    message.getChannel().sendMessage("Sem Suporte").queue();
                }
            }
        }

    }
}