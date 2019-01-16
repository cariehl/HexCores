package hexcores;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import hexcores.relics.AmplifyCore;
import hexcores.relics.BulkCore;
import hexcores.relics.TransformCore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class HexCores implements PostInitializeSubscriber, EditStringsSubscriber, EditRelicsSubscriber
{
    public static final String MODNAME = "Hex Cores";
    public static final String AUTHOR = "Kazel";
    public static final String DESCRIPTION = "Adds three new relics with a common theme.";

    public static final Logger logger = LogManager.getLogger(HexCores.class.getSimpleName());


    public HexCores()
    {
        BaseMod.subscribe(this);
    }


    @SuppressWarnings("unused")
    public static void initialize()
    {
        logger.info("Initializing HexCores...");

        new HexCores();

        logger.info("HexCores initialized.");
    }


    public static String assetPath(String path)
    {
        return "hexcores/" + path;
    }


    public void receivePostInitialize()
    {
        logger.trace("Begin HexCores.receivePostInitialize");

        registerModBadge();

        logger.trace("End HexCores.receivePostInitialize");
    }

    private void registerModBadge()
    {
        logger.trace("Begin HexCores.registerModBadge");

        Texture badgeTexture = new Texture(assetPath("images/HexCoresBadge.png"));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, new ModPanel());

        logger.trace("End HexCores.registerModBadge");
    }


    @Override
    public void receiveEditRelics()
    {
        logger.trace("Begin HexCores.receiveEditRelics");

        BaseMod.addRelic(new AmplifyCore(), RelicType.SHARED);
        BaseMod.addRelic(new BulkCore(), RelicType.SHARED);
        BaseMod.addRelic(new TransformCore(), RelicType.SHARED);

        logger.trace("End HexCores.receiveEditRelics");
    }


    @Override
    public void receiveEditStrings()
    {
        logger.trace("Begin HexCores.receiveEditStrings");

        if (Settings.language != Settings.GameLanguage.ENG)
        {
            logger.warn("Localization file not found for language: '" + Settings.language.name() + "'; defaulting to English...");
        }

        BaseMod.loadCustomStringsFile(RelicStrings.class, assetPath("localization/eng/HexCores-RelicStrings.json"));

        logger.trace("End HexCores.receiveEditStrings");
    }
}
