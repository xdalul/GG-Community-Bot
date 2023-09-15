package dev.dalol.commands;

import dev.dalol.listener.MessageEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RemoveLastMessageCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("removelastmessage")) {
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                MessageEvent.words.remove(MessageEvent.lastMessage);
                MessageEvent.lastMessage = "";
                event.getGuild().getTextChannelById("1151867308789211166").deleteMessageById(MessageEvent.getId).queue();
                event.reply("**Erfolgreich!**").setEphemeral(true).queue();
            }
        }
    }
}
