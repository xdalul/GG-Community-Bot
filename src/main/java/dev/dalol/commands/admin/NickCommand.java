package dev.dalol.commands.admin;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class NickCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("nick")) {
            Member p = event.getOption("nutzer").getAsMember();
            String nickName = event.getOption("nickname").getAsString();
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                p.modifyNickname(nickName).queue();
                event.reply("**Du hast den Nickname für " + p.getAsMention() + " auf \"" + nickName + "\" gesetzt.**").setEphemeral(true).queue();
            } else {
                event.reply("Du hast dafür keine Berechtigung!").setEphemeral(true).queue();
            }
        }
    }
}