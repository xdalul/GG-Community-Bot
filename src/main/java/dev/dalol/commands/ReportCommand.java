package dev.dalol.commands;

import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class ReportCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("report")) {
            String fehler = Objects.requireNonNull(event.getOption("fehler")).getAsString();
            event.reply("Danke für deinen Report!").setEphemeral(true).queue();
            event.getGuild().getTextChannelById("1151938627811688648").sendMessage("**FEHLER! @everyone**\n\n" + event.getMember().getAsMention() + " - **" + fehler + "**").queue();
        }
    }
}
