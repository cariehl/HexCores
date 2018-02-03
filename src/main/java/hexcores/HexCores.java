package hexcores;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import hexcores.relics.AmplifyCore;
import hexcores.relics.BulkCore;
import hexcores.relics.TransformerCore;

import java.nio.charset.StandardCharsets;

public class HexCores implements PostInitializeSubscriber {
    private static final String MODNAME = "Hex Cores";
    private static final String AUTHOR = "cariehl";
    private static final String DESCRIPTION = "v1.0.0 NL Adds three new relics inspired by /u/Sweet_Azu.";

    public HexCores() {
        BaseMod.subscribeToPostInitialize(this);
    }

    public static void initialize() {
        HexCores hexCores = new HexCores();
    }

    public void receivePostInitialize() {
        // Mod badge
        Texture badgeTexture = new Texture(Gdx.files.internal("images/HexCoresBadge.png"));
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addLabel("This mod does not have any settings", 400.0f, 700.0f, (me) -> {});
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        // RelicStrings
        String jsonString = Gdx.files.internal("localization/HexCores-relics.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomRelicStrings(jsonString);

        // Add relics
        RelicLibrary.add(new AmplifyCore());
        RelicLibrary.add(new BulkCore());
        RelicLibrary.add(new TransformerCore());
    }
}
