package myatt.abe.inspirations.listeners;

import myatt.abe.inspirations.service.FileReadWriteService;
import myatt.abe.inspirations.service.ImageModifierService;
import myatt.abe.inspirations.service.PexelsImageRetrievalService;
import myatt.abe.inspirations.service.ZenQuoteRetrievalService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static myatt.abe.inspirations.utility.CommandConstants.CURATED_IMAGE_COMMAND;
import static myatt.abe.inspirations.utility.CommandConstants.CURATED_IMAGE_DESCRIPTION;
import static myatt.abe.inspirations.utility.CommandConstants.IMAGE_SEARCH_COMMAND;
import static myatt.abe.inspirations.utility.CommandConstants.IMAGE_SEARCH_DESCRIPTION;
import static myatt.abe.inspirations.utility.CommandConstants.IMAGE_SEARCH_QUERY_DESCRIPTION;
import static myatt.abe.inspirations.utility.CommandConstants.CURATED_INSPIRATION_COMMAND;
import static myatt.abe.inspirations.utility.CommandConstants.CURATED_INSPIRATION_DESCRIPTION;
import static myatt.abe.inspirations.utility.CommandConstants.INSPIRATIONAL_IMAGE_SEARCH_COMMAND;
import static myatt.abe.inspirations.utility.CommandConstants.INSPIRATIONAL_IMAGE_SEARCH_DESCRIPTION;
import static myatt.abe.inspirations.utility.CommandConstants.RANDOM_IMAGE_COMMAND;
import static myatt.abe.inspirations.utility.CommandConstants.RANDOM_IMAGE_DESCRIPTION;
import static myatt.abe.inspirations.utility.CommandConstants.RANDOM_INSPIRATION_COMMAND;
import static myatt.abe.inspirations.utility.CommandConstants.RANDOM_INSPIRATION_DESCRIPTION;
import static myatt.abe.inspirations.utility.CommandConstants.RANDOM_QUOTE_COMMAND;
import static myatt.abe.inspirations.utility.CommandConstants.RANDOM_QUOTE_DESCRIPTION;
import static myatt.abe.inspirations.utility.CommandConstants.SEARCH_QUERY_OPTION;
import static myatt.abe.inspirations.utility.Constants.FILE_BASE_PATH;
import static myatt.abe.inspirations.utility.Constants.JPEG_FILE_EXTENSION;
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
                Commands.slash(CURATED_IMAGE_COMMAND, CURATED_IMAGE_DESCRIPTION)
        );

        commandListUpdateAction.addCommands(
                Commands.slash(IMAGE_SEARCH_COMMAND, IMAGE_SEARCH_DESCRIPTION)
                        .addOption(STRING, SEARCH_QUERY_OPTION, IMAGE_SEARCH_QUERY_DESCRIPTION, true)
        );

        commandListUpdateAction.addCommands(
                Commands.slash(CURATED_INSPIRATION_COMMAND, CURATED_INSPIRATION_DESCRIPTION)
        );

        commandListUpdateAction.addCommands(
                Commands.slash(RANDOM_INSPIRATION_COMMAND, RANDOM_INSPIRATION_DESCRIPTION)
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
        File file;
        String searchQuery;
        String timestamp;
        var imagesCreated = false;

        switch (event.getName()) {
            case RANDOM_QUOTE_COMMAND:
                var quote = "";
                var author = "";
                event.reply("Finding a quote...").queue();
                try {
                    var zenQuoteResponse = zenQuoteRetrievalService.getRandomQuote();
                    quote = zenQuoteResponse.getQuote();
                    author = zenQuoteResponse.getAuthorName();
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                event.getChannel().sendMessage(quote + " - " + author).queue();
                break;

            case RANDOM_IMAGE_COMMAND:
                event.reply("Finding a random image...").queue();
                try {
                    var pexelImage = pexelsImageRetrievalService.retrieveRandomPhoto();
                    var pexelImageData = pexelsImageRetrievalService.downloadImage(pexelImage);
                    timestamp = pexelImageData.getTimestamp();
                    var pathToFile = FILE_BASE_PATH + timestamp + JPEG_FILE_EXTENSION;
                    fileReadWriteService.savePexelImage(pexelImageData);
                    imagesCreated = true;
                    file = new File(pathToFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                event.getChannel().sendMessage("").addFiles(FileUpload.fromData(file)).queue();
                break;

            case CURATED_IMAGE_COMMAND:
                event.reply("Finding the current curated image...").queue();
                try {
                    var pexelImage = pexelsImageRetrievalService.retrieveCuratedPhoto();
                    var pexelImageData = pexelsImageRetrievalService.downloadImage(pexelImage);
                    timestamp = pexelImageData.getTimestamp();
                    var pathToFile = FILE_BASE_PATH + timestamp + JPEG_FILE_EXTENSION;
                    fileReadWriteService.savePexelImage(pexelImageData);
                    imagesCreated = true;
                    file = new File(pathToFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                event.getChannel().sendMessage("").addFiles(FileUpload.fromData(file)).queue();
                break;

            case IMAGE_SEARCH_COMMAND:
                event.reply("Finding an image...").queue();
                searchQuery = event.getOption(SEARCH_QUERY_OPTION).getAsString();
                try {
                    var pexelImage = pexelsImageRetrievalService.retrieveSearchPhotos(searchQuery);
                    var pexelImageData = pexelsImageRetrievalService.downloadImage(pexelImage);
                    timestamp = pexelImageData.getTimestamp();
                    var pathToFile = FILE_BASE_PATH + timestamp + JPEG_FILE_EXTENSION;
                    fileReadWriteService.savePexelImage(pexelImageData);
                    imagesCreated = true;
                    file = new File(pathToFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                event.getChannel().sendMessage("").addFiles(FileUpload.fromData(file)).queue();
                break;

            case CURATED_INSPIRATION_COMMAND:
                event.reply("Creating something inspirational...").queue();
                try {
                    var zenQuoteResponse = zenQuoteRetrievalService.getRandomQuote();
                    var pexelImage = pexelsImageRetrievalService.retrieveCuratedPhoto();
                    var pexelImageData = pexelsImageRetrievalService.downloadImage(pexelImage);
                    timestamp = pexelImageData.getTimestamp();
                    var pathToFile = FILE_BASE_PATH + timestamp + "-modified" + JPEG_FILE_EXTENSION;
                    fileReadWriteService.savePexelImage(pexelImageData);
                    var modifiedImage = imageModifierService.addTextToImage(timestamp, zenQuoteResponse);
                    fileReadWriteService.saveBufferedImage(timestamp, modifiedImage);
                    file = new File(pathToFile);
                    imagesCreated = true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                event.getChannel().sendMessage("").addFiles(FileUpload.fromData(file)).queue();

                break;

            case RANDOM_INSPIRATION_COMMAND:
                event.reply("Creating something random and inspirational...").queue();
                try {
                    var zenQuoteResponse = zenQuoteRetrievalService.getRandomQuote();
                    var pexelImage = pexelsImageRetrievalService.retrieveRandomPhoto();
                    var pexelImageData = pexelsImageRetrievalService.downloadImage(pexelImage);
                    timestamp = pexelImageData.getTimestamp();
                    var pathToFile = FILE_BASE_PATH + timestamp + "-modified" + JPEG_FILE_EXTENSION;
                    fileReadWriteService.savePexelImage(pexelImageData);
                    var modifiedImage = imageModifierService.addTextToImage(timestamp, zenQuoteResponse);
                    fileReadWriteService.saveBufferedImage(timestamp, modifiedImage);
                    file = new File(pathToFile);
                    imagesCreated = true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                event.getChannel().sendMessage("").addFiles(FileUpload.fromData(file)).queue();

                break;

            case INSPIRATIONAL_IMAGE_SEARCH_COMMAND:
                searchQuery = event.getOption(SEARCH_QUERY_OPTION).getAsString();
                event.reply("Creating something unique and inspirational...").queue();
                try {
                    var zenQuoteResponse = zenQuoteRetrievalService.getRandomQuote();
                    var pexelImage = pexelsImageRetrievalService.retrieveSearchPhotos(searchQuery);
                    var pexelImageData = pexelsImageRetrievalService.downloadImage(pexelImage);
                    timestamp = pexelImageData.getTimestamp();
                    var pathToFile = FILE_BASE_PATH + timestamp + "-modified" + JPEG_FILE_EXTENSION;
                    fileReadWriteService.savePexelImage(pexelImageData);
                    var modifiedImage = imageModifierService.addTextToImage(timestamp, zenQuoteResponse);
                    fileReadWriteService.saveBufferedImage(timestamp, modifiedImage);
                    imagesCreated = true;
                    file = new File(pathToFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                event.getChannel().sendMessage("").addFiles(FileUpload.fromData(file)).queue();
                break;

            default:
                event.reply("The definition of insanity is trying the same thing twice and expecting different results")
                        .queue();
        }

        if(imagesCreated) {
            try {
                Thread.sleep(5000); // wait for image to be sent before trying to delete
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                fileReadWriteService.deleteFilesInDirectory(new File(FILE_BASE_PATH));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
