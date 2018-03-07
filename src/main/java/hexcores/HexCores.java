package hexcores;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.RelicStrings;

import hexcores.relics.AmplifyCore;
import hexcores.relics.BulkCore;
import hexcores.relics.TransformerCore;

import java.nio.charset.StandardCharsets;

public class HexCores implements PostInitializeSubscriber,EditStringsSubscriber,EditRelicsSubscriber {
    private static final String MODNAME = "Hex Cores";
    private static final String AUTHOR = "cariehl";
    private static final String DESCRIPTION = "v1.0.0 NL Adds three new relics inspired by /u/Sweet_Azu.";

    public HexCores() {
        BaseMod.subscribeToPostInitialize(this);
        BaseMod.subscribeToEditRelics(this);
        BaseMod.subscribeToEditStrings(this);
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
    }

    @Override
    public void receiveEditRelics() {
        // Add relics
        RelicLibrary.add(new AmplifyCore());
        RelicLibrary.add(new BulkCore());
        RelicLibrary.add(new TransformerCore());
    }

    @Override
    public void receiveEditStrings() {
        // RelicStrings
        String jsonString = Gdx.files.internal("localization/HexCores-relics.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, jsonString);
    }
}
