package myatt.abe.inspirations.listeners;

import myatt.abe.inspirations.service.FileReadWriteService;
import myatt.abe.inspirations.service.ImageModifierService;
import myatt.abe.inspirations.service.PexelsImageRetrievalService;
import myatt.abe.inspirations.service.ZenQuoteRetrievalService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

import static myatt.abe.inspirations.utility.CommandConstants.IMAGE_SEARCH_COMMAND;
import static myatt.abe.inspirations.utility.CommandConstants.IMAGE_SEARCH_DESCRIPTION;
import static myatt.abe.inspirations.utility.CommandConstants.IMAGE_SEARCH_QUERY_DESCRIPTION;
import static myatt.abe.inspirations.utility.CommandConstants.INSPIRATIONAL_IMAGE_COMMAND;
import static myatt.abe.inspirations.utility.CommandConstants.INSPIRATIONAL_IMAGE_DESCRIPTION;
import static myatt.abe.inspirations.utility.CommandConstants.INSPIRATIONAL_IMAGE_SEARCH_COMMAND;
import static myatt.abe.inspirations.utility.CommandConstants.INSPIRATIONAL_IMAGE_SEARCH_DESCRIPTION;
import static myatt.abe.inspirations.utility.CommandConstants.RANDOM_IMAGE_COMMAND;
import static myatt.abe.inspirations.utility.CommandConstants.RANDOM_IMAGE_DESCRIPTION;
import static myatt.abe.inspirations.utility.CommandConstants.RANDOM_QUOTE_COMMAND;
import static myatt.abe.inspirations.utility.CommandConstants.RANDOM_QUOTE_DESCRIPTION;
import static myatt.abe.inspirations.utility.CommandConstants.SEARCH_QUERY_OPTION;
import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

@Component
public class CommandListener extends ListenerAdapter {

    private final ZenQuoteRetrievalService zenQuoteRetrievalService;

    private final PexelsImageRetrievalService pexelsImageRetrievalService;

    private final ImageModifierService imageModifierService;

    private final FileReadWriteService fileReadWriteService;

    @Autowired
    public CommandListener(ZenQuoteRetrievalService zenQuoteRetrievalService, PexelsImageRetrievalService pexelsImageRetrievalService, ImageModifierService imageModifierService, FileReadWriteService fileReadWriteService) {
        this.zenQuoteRetrievalService = zenQuoteRetrievalService;
        this.pexelsImageRetrievalService = pexelsImageRetrievalService;
        this.imageModifierService = imageModifierService;
        this.fileReadWriteService = fileReadWriteService;
    }

    public void setupCommands(JDA jda) {

        var commandListUpdateAction = jda.updateCommands();

        commandListUpdateAction.addCommands(
                Commands.slash(RANDOM_QUOTE_COMMAND, RANDOM_QUOTE_DESCRIPTION)
        );

        commandListUpdateAction.addCommands(
                Commands.slash(RANDOM_IMAGE_COMMAND, RANDOM_IMAGE_DESCRIPTION)
        );

        commandListUpdateAction.addCommands(
                Commands.slash(INSPIRATIONAL_IMAGE_COMMAND, INSPIRATIONAL_IMAGE_DESCRIPTION)
        );

        commandListUpdateAction.addCommands(
                Commands.slash(IMAGE_SEARCH_COMMAND, IMAGE_SEARCH_DESCRIPTION)
                        .addOption(STRING, SEARCH_QUERY_OPTION, IMAGE_SEARCH_QUERY_DESCRIPTION, true)
        );

        commandListUpdateAction.addCommands(
                Commands.slash(INSPIRATIONAL_IMAGE_SEARCH_COMMAND, INSPIRATIONAL_IMAGE_SEARCH_DESCRIPTION)
                        .addOption(STRING, SEARCH_QUERY_OPTION, IMAGE_SEARCH_QUERY_DESCRIPTION, true)
        );


        commandListUpdateAction.queue();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getGuild() == null) return;

        switch (event.getName()) {
            case RANDOM_QUOTE_COMMAND:
                try {
                    event.reply(zenQuoteRetrievalService.getRandomQuote().getQuote()).queue();
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            case RANDOM_IMAGE_COMMAND:
                // event.reply("").addFiles(null).queue();
                break;
            case INSPIRATIONAL_IMAGE_COMMAND:
                // event.reply("").addFiles(null).queue();
                break;

            case IMAGE_SEARCH_COMMAND:
            case INSPIRATIONAL_IMAGE_SEARCH_COMMAND:
                var query = event.getOption(SEARCH_QUERY_OPTION);
                // event.reply("").addFiles(null).queue();
                break;
            default:
                event.reply("The definition of insanity is trying the same thing twice and expecting different results")
                        .queue();
        }
    }
}
