package dev.dalol.commands.öffentlich;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Taschenrechner extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        String rechnung = event.getOption("rechnung").getAsString();

        if (event.getName().equals("taschenrechner")) {
            try {
                double result = evaluateExpression(rechnung);
                MessageChannel channel = event.getChannel();
                channel.sendMessage("Ergebnis: " + result).queue();
            } catch (Exception e) {
                MessageChannel channel = event.getChannel();
                channel.sendMessage("Ungültiger Ausdruck.").queue();
            }
        }
    }

    private double evaluateExpression(String expression) {
        // Hier können Sie Code hinzufügen, um den Ausdruck auszuwerten.
        // Zum Beispiel können Sie eine externe Mathematikbibliothek verwenden oder
        // Ihren eigenen Code schreiben, um die Berechnungen durchzuführen.
        // Für dieses Beispiel verwenden wir die eval-Methode in der JavaScript-Engine.

        javax.script.ScriptEngineManager mgr = new javax.script.ScriptEngineManager();
        javax.script.ScriptEngine engine = mgr.getEngineByName("JavaScript");

        try {
            Object result = engine.eval(expression);
            if (result instanceof Number) {
                return ((Number) result).doubleValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("Ungültiger Ausdruck");
    }
}
