package dev.dalol.commands.admin;

import dev.dalol.util.Embeds;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;

public class EnableMaintenance extends ListenerAdapter {
    public static Boolean maintenance;

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Boolean maintenancecmd = event.getOption("boolean").getAsBoolean();
        Channel channel = event.getOption("anounce-kanal").getAsChannel();
        String grund = event.getOption("grund").getAsString();
        if (event.getName().equals("maintenance")) {
            if (maintenancecmd.equals(true)) {
                if (maintenance.equals(true)) {
                    event.replyEmbeds(Embeds.ErrorEmbed("Wartungsarbeiten sind bereits an!").build()).setEphemeral(true).queue();
                } else {
                    maintenance = true;
                    EmbedBuilder builder = new EmbedBuilder();

                    builder.setTitle("**Wartungsarbeiten gestartet**");
                    builder.setDescription("Die Wartungsarbeiten wurden nun erfolgreich gestartet. Oneword und andere Sachen wurden nun \"geschlossen\"");
                    builder.setFooter("GG-Community");
                    builder.setTimestamp(Instant.now());
                    builder.setColor(0x339cff);

                    event.replyEmbeds(builder.build()).setEphemeral(true).queue();
                }
            } else {
                if (maintenance.equals(false)) {
                    event.replyEmbeds(Embeds.ErrorEmbed("Wartungsarbeiten sind bereits an!").build()).setEphemeral(true).queue();
                } else {
                    maintenance = false;
                    EmbedBuilder builder = new EmbedBuilder();

                    builder.setTitle("**Bot Wartungsarbeiten**");
                    builder.setDescription("Der Bot ist aktuell in Wartungsarbeiten. Der Grund ist \"**" + grund + "**\"" + "\nWir bitten um Geduld, es gibt keine genaue Zeit, bis die Wartungsarbeiten vorrüber sind.");
                    builder.setFooter("GG-Community");
                    builder.setTimestamp(Instant.now());
                    builder.setColor(0x339cff);

                    event.replyEmbeds(builder.build()).setEphemeral(true).queue();
                }
            }
        }
    }
}
