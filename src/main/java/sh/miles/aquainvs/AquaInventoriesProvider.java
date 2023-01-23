package sh.miles.aquainvs;

import java.io.File;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

public class AquaInventoriesProvider extends JavaPlugin {

    public AquaInventoriesProvider() {
        super();
    }

    protected AquaInventoriesProvider(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder,
            File file) {
        super(loader, description, dataFolder, file);
    }

}
