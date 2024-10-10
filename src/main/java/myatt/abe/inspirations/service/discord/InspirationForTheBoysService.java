package myatt.abe.inspirations.service.discord;

import myatt.abe.inspirations.listeners.CommandListener;
import myatt.abe.inspirations.listeners.ReadyListener;
import net.dv8tion.jda.api.JDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InspirationForTheBoysService {

    @Autowired
    private JDA jda;

    @Autowired
    private CommandListener commandListener;

    public void startBot() {
        commandListener.setupCommands(this.jda);
        this.jda.addEventListener(new ReadyListener(), commandListener);
    }

    public void shutdownBot() {
        this.jda.shutdown();
    }

}
