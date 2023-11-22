package dev.dalol.commands.admin;

import dev.dalol.util.Embeds;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.w3c.dom.Text;

import java.time.Instant;

public class EnableMaintenance extends ListenerAdapter {
    public static Boolean maintenance;
    public static String reason;

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Boolean maintenancecmd = event.getOption("boolean").getAsBoolean();
        TextChannel channel = event.getOption("anounce-kanal").getAsChannel().asTextChannel();
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

                    EmbedBuilder builder1 = new EmbedBuilder();
                    reason = grund;

                    builder1.setTitle("**Bot Wartungsarbeiten**");
                    builder1.setDescription("Der Bot ist nun nicht mehr in Wartungsarbeiten. Der Grund ist \"**" + grund + "**\"" + "\nWir bitten um Geduld, es gibt keine genaue Zeit, bis die Wartungsarbeiten vorrüber sind.");
                    builder1.setFooter("GG-Community");
                    builder1.setTimestamp(Instant.now());
                    builder1.setColor(0x339cff);

                    channel.sendMessageEmbeds(builder1.build()).queue();
                }
            } else {
                if (maintenance.equals(false)) {
                    event.replyEmbeds(Embeds.ErrorEmbed("Wartungsarbeiten sind bereits an!").build()).setEphemeral(true).queue();
                } else {
                    maintenance = false;

                    EmbedBuilder builder = new EmbedBuilder();
                    reason = grund;

                    builder.setTitle("**Ende der Wartungsarbeiten.**");
                    builder.setDescription("> Der Bot ist nun nicht mehr in Wartungsarbeiten.\n > Der Changelog wird wahrscheinlich bald reingeposted.");
                    builder.setFooter("GG-Community");
                    builder.setTimestamp(Instant.now());
                    builder.setColor(0x339cff);

                    channel.sendMessageEmbeds(builder.build()).queue();
                }
            }
        }
    }
}
