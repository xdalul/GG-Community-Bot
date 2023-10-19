package dev.dalol.commands.öffentlich;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ChatGPT extends ListenerAdapter {

    //----> Discontinued
    private static String apiKey = "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";
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String question = event.getOption("frage").getAsString();

        if (event.getName().equals("ask-gpt")) {
            try {
                String response = askChatGPT(question);
                event.reply(response).queue();
            } catch (IOException e) {
                e.printStackTrace();
                event.reply("Fehler bei der Anfrage an ChatGPT.").setEphemeral(true).queue();
            }
        }
    }
    private String askChatGPT(String question) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        String apiKeyHeader = "Bearer " + apiKey;

        RequestBody body = RequestBody.create(mediaType, "{\"prompt\": \"" + question + "\"}");
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/engines/davinci-codex/completions")
                .post(body)
                .addHeader("Authorization", apiKeyHeader)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return extractResponseText(responseBody);
            } else {
                throw new IOException("Ungültige API-Antwort: " + response.code());
            }
        }
    }

    private String extractResponseText(String responseBody) {
        try {
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray choicesArray = jsonResponse.getJSONArray("choices");

            if (choicesArray.length() > 0) {
                JSONObject choice = choicesArray.getJSONObject(0); // Wir nehmen die erste Wahl
                return choice.getString("text");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Ungültige Antwort von ChatGPT.";
    }
}