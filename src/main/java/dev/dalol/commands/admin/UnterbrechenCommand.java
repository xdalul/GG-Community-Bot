package dev.dalol.commands.admin;

import dev.dalol.listener.MessageEvent;
import dev.dalol.util.Embeds;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;

public class UnterbrechenCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("unterbrechen")) {
            String getGrund = event.getOption("grund").getAsString();
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                event.reply("**Erfolgreich!**").setEphemeral(true).queue();
                event.getChannel().sendMessageEmbeds(Embeds.unterbrechenEmbed(event.getMember(), getGrund).build()).queue();
                MessageEvent.words.clear();
                MessageEvent.currentIndex = 0;
                MessageEvent.lastWord = "";
            } else {
                event.reply("Du hast keine Berechtigung dafür!").setEphemeral(true).queue();
            }
        }
    }
}