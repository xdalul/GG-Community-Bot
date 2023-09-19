package dev.dalol.commands.öffentlich;

import dev.dalol.util.Embeds;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChangeLog extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("changelog")) {
            if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)){
                event.replyEmbeds(Embeds.changeLogEmbed().build()).setEphemeral(true).queue();
                return;
            } else {
                event.replyEmbeds(Embeds.changeLogAdminEmbed().build()).setEphemeral(true).queue();
                return;
            }
        }
    }
}
