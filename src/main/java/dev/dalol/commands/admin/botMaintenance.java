package dev.dalol.commands.admin;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class botMaintenance extends ListenerAdapter {

    public static boolean maintenance;

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        boolean maintenanceop = event.getOption("boolean").getAsBoolean();
        Member m = event.getMember();

        if (m.hasPermission(Permission.ADMINISTRATOR)) {
            if (maintenanceop == true) {
                if (maintenanceop == true) {
                    event.reply("Die Bot-Maintenance ist bereits gesetzt!").setEphemeral(true).queue();
                } else {
                    maintenance = true;
                    event.reply("Du hast die Maintenance für den Bot gestartet!").setEphemeral(true).queue();
                }
            } if (maintenanceop == false) {
                if (maintenance == false) {
                    event.reply("Die Bot-Maintenance ist bereits aus!").setEphemeral(true).queue();
                } else {
                    maintenance = false;
                    event.reply("Du hast die Maintenance für den Bot nun ausgeschalten!").setEphemeral(true).queue();
                }
            }
        } else {
            event.reply("Du hast keine Berchtigung hierfür!").setEphemeral(true).queue();
        }
    }
}
