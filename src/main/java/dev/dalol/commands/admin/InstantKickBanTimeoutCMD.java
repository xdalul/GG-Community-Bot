package dev.dalol.commands.admin;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class InstantKickBanTimeoutCMD extends ListenerAdapter {

    private static String[] dauer = {"1m","5m","10m","1h","1d","2w"};


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Role owner = event.getGuild().getRoleById("897123045469204520");

        if (event.getName().equals("instantban")) {
            Member member = event.getOption("nutzer").getAsMember();
            Integer deldays = event.getOption("deldays").getAsInt();
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                if (member.getRoles().contains(owner)) {
                    event.reply("Diesen User darfst du nicht bannen!").setEphemeral(true).queue();
                } else {
                    member.ban(deldays, TimeUnit.DAYS).reason("Gebannt von " + event.getMember().getEffectiveName()).queue();
                    event.reply("**Du hast erfolgreich " + member.getEffectiveName() + " gebannt.**").setEphemeral(true).queue();
                }
           } else {
                event.reply("**Du hast keine Berechtigung dafür!**").setEphemeral(true).queue();
            }
        }
        if (event.getName().equals("instantkick")) {
            Member member = event.getOption("nutzer").getAsMember();

            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                if (member.getRoles().contains(owner)) {
                    event.reply("Diesen User darfst du nicht kicken!").setEphemeral(true).queue();
                } else {
                    member.kick().reason("Gekickt von " + event.getMember().getEffectiveName()).queue();
                    event.reply("**Du hast erfolgreich " + member.getEffectiveName() + " gekickt.**").setEphemeral(true).queue();
                }
            } else {
                event.reply("**Du hast keine Berechtigung dafür!**").setEphemeral(true).queue();
            }
        }
        String länge = event.getOption("dauer").getAsString();
        Member member = event.getOption("nutzer").getAsMember();
        if (event.getName().equals("instanttimeout")) {
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                if (member.getRoles().contains(owner)) {
                    event.reply("Diesen User darfst du nicht Timeouten!").setEphemeral(true).queue();
                } else {
                    if (länge.equals("1m")) {
                        member.timeoutFor(1, TimeUnit.MINUTES).reason("Timed out von " + event.getMember().getEffectiveName()).queue();
                        event.reply("Du hast den Nutzer " + member.getAsMention() + " timed out für 5 Minuten.").setEphemeral(true).queue();
                    } else if (länge.equals("5m")) {
                        member.timeoutFor(5, TimeUnit.MINUTES).reason("Timed out von " + event.getMember().getEffectiveName()).queue();
                        event.reply("Du hast den Nutzer " + member.getAsMention() + " timed out für 5 Minuten.").setEphemeral(true).queue();
                    } else if (länge.equals("10m")) {
                        member.timeoutFor(10, TimeUnit.MINUTES).reason("Timed out von " + event.getMember().getEffectiveName()).queue();
                        event.reply("Du hast den Nutzer " + member.getAsMention() + " timed out für 10 Minuten.").setEphemeral(true).queue();
                    } else if (länge.equals("1h")) {
                        member.timeoutFor(1, TimeUnit.HOURS).reason("Timed out von " + event.getMember().getEffectiveName()).queue();
                        event.reply("Du hast den Nutzer " + member.getAsMention() + " timed out für 1 Stunde.").setEphemeral(true).queue();
                    } else if (länge.equals("1d")) {
                        member.timeoutFor(1, TimeUnit.DAYS).reason("Timed out von " + event.getMember().getEffectiveName()).queue();
                        event.reply("Du hast den Nutzer " + member.getAsMention() + " timed out für 1 Tag.").setEphemeral(true).queue();
                    } else if (länge.equals("2w")) {
                        member.timeoutFor(14, TimeUnit.DAYS).reason("Timed out von " + event.getMember().getEffectiveName()).queue();
                        event.reply("Du hast den Nutzer " + member.getAsMention() + " timed out für 2 Wochen.").setEphemeral(true).queue();
                    }
                }
            }
        }
    }
    @Override
    public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event) {
        if (event.getName().equals("instanttimeout") && event.getFocusedOption().getName().equals("dauer")) {
            List<Command.Choice> options = Stream.of(dauer)
                    .filter(dauer -> dauer.startsWith(event.getFocusedOption().getValue())) // only display words that start with the user's current input
                    .map(dauer -> new Command.Choice(dauer, dauer)) // map the words to choices
                    .collect(Collectors.toList());
            event.replyChoices(options).queue();
        }
    }
}
