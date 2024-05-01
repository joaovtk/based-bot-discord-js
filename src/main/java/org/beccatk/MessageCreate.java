package org.beccatk;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateAvatarEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.CacheRestAction;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

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
                EmbedBuilder embed = new EmbedBuilder();
                String avatar = "";
                embed.setColor(0x00ff00);
                String[] listmsg = {"Você está olhando para o animal mais perigoso do mundo. Só ele de todos os animais que já viveram pode exterminar (e tem) espécies inteiras de animais. Agora tem o poder de exterminar toda a vida na terra.", "Isso daqui na minha mão é um membro em conserva de um deus anciião mesotãmico...", "Os olhos conseguem nos ver ramon e eu consigo ver os olhos", "Da minha amiga lorrita vai uma frase ai \"apesar de tudo, ainda é você.\""};
                Random rand = new Random();
                embed.setDescription(listmsg[rand.nextInt(listmsg.length)]);

                if(args.length == 0){
                    embed.setTitle(String.format("Rosto do %s", message.getAuthor().getName()));
                    avatar = message.getAuthor().getAvatar().getUrl(1024);
                }else {
                    if(!message.getMentions().getUsers().isEmpty()){
                        embed.setTitle(String.format("Icon do %s", message.getMentions().getUsers().getFirst().getName()));
                        avatar = message.getMentions().getUsers().getFirst().getAvatar().getUrl(1024);
                    }else {
                        try {
                            JDA jda = message.getJDA();
                            User userget = jda.retrieveUserById(args[0]).complete();
                            System.out.println(userget);
                            avatar = userget.getAvatar().getUrl(1024);
                            embed.setTitle(String.format("Icon do %s", userget.getName()));
                        }catch (Exception err){
                            System.out.println(err);
                            message.getChannel().sendMessage("Formato de id invalido").queue();
                        }
                    }
                }
                if(!avatar.isEmpty()) {
                    embed.setImage(avatar);
                    message.getChannel().sendMessageEmbeds(embed.build()).queue();
                }
            }
        }

    }
}