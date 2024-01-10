package dev.dalol.commands.admin;

import dev.dalol.util.Rollen;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class EnableOneword extends ListenerAdapter {

    public static boolean oneword;

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {


        if (event.getName().equals("enable-oneword")) {
            Member p = event.getMember();
            boolean einwort = event.getOption("boolean").getAsBoolean();
            if (event.getMember().getRoles().contains(Rollen.getModerationsRoles(event.getGuild()))) {
                if (einwort == true) {
                    if (oneword == true) {
                        event.reply("OneWord läuft schon bereits!").setEphemeral(true).queue();
                        return;
                    } else {
                        oneword = true;
                        event.reply("OneWord wurde nun gestartet.").setEphemeral(true).queue();
                        return;
                    }
                }
                if (einwort == false) {
                    if (oneword == false) {
                        event.reply("OneWord ist bereits aus!").setEphemeral(true).queue();
                        return;
                    } else {
                        oneword = false;
                        event.reply("OneWord wurde nun beendet.").setEphemeral(true).queue();
                        return;
                    }
                }
            } else {
                event.reply(p.getAsMention() + "\nDu hast keine Berechtigung dafür.").setEphemeral(true).queue();
            }
        }
    }
}
