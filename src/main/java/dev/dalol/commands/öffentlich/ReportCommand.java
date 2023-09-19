package dev.dalol.commands.öffentlich;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;
import java.util.Objects;

public class ReportCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("report")) {
            String fehler = Objects.requireNonNull(event.getOption("fehler")).getAsString();
            event.reply("Danke für deinen Report!").setEphemeral(true).queue();
            EmbedBuilder builder = new EmbedBuilder();

            builder.setTitle("Fehler!");
            builder.setDescription(event.getMember().getEffectiveName() + " - **" + fehler + "**");
            builder.setColor(0xf55142);
            builder.setFooter("GG-Community");
            builder.setTimestamp(Instant.now());


        }
    }
}
