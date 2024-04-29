package org.beccatk;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;

public class Main {
    public static void main(String[] args) {
        JDABuilder Builder = JDABuilder.createLight(System.getenv("TOKEN"), EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_EMOJIS_AND_STICKERS, GatewayIntent.MESSAGE_CONTENT));
        Builder.addEventListeners(new MessageCreate());
        Builder.build();
    }
}

