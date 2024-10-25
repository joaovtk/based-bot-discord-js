package app.tk.beccatk;

import java.util.EnumSet;

import app.tk.beccatk.Commands.Ping;
import dev.arbjerg.lavalink.client.LavalinkClient;
import dev.arbjerg.lavalink.libraries.jda.JDAVoiceUpdateListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        
        String token = dotenv.get("TOKEN");
        LavalinkClient client = new Lavalink(token).getClient();

        JDA jda = JDABuilder.createDefault(token, EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.DIRECT_MESSAGES))
            .addEventListeners(new Ping())
            .setVoiceDispatchInterceptor(new JDAVoiceUpdateListener(client))
            .build();

        
        
    }

    public void deployCommands(JDA jda){
        jda.upsertCommand("ping", "Comando universal dos bots")
            .queue();
        
        SubcommandGroupData dustG = new SubcommandGroupData("dust", "Comandos da economia do comando de coleção");
        SubcommandData atm = new SubcommandData("atm", "Conta quantas dust ou pó de estrelas você tem").addOption(OptionType.USER, "user", "Usuario que você quer ver a quantidade de estrelas", false);
        SubcommandData pray = new SubcommandData("pray", "Te dá pó de estrelas diários, os valores de benção ou daily podem ser afetados pelo comando offer");
        SubcommandData offer = new SubcommandData("offer", "Faz uma oferenda para um astro que dará um buff diferente").addOption(OptionType.STRING, "dust", "Quantidade de dust para oferenda (Obrigatorio)", true).addOption(OptionType.STRING, "char", "Personagem que você quer sacrificar para os astros (Opcional)", false);
        dustG.addSubcommands(atm);
        dustG.addSubcommands(pray);

        jda.upsertCommand("star", "Comando de coleção").addSubcommandGroups(dustG);
    }
}