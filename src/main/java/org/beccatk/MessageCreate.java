package org.beccatk;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
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
            }
        }

    }
}