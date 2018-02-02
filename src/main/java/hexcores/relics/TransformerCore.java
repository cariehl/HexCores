package hexcores.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class TransformerCore extends CustomRelic {
    public static final String ID = "TransformerCore";
    private static final String IMG = "images/relics/circlet.png";
    private static final String OUTLINE = "images/relics/outline/circlet.png";

    private static final int BLOCK_AMT = 10;
    private static final int ENERGY_AMT = 1;

    public TransformerCore() {
        super(ID, new Texture(Gdx.files.internal(IMG)), new Texture(Gdx.files.internal(OUTLINE)), RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.currentBlock >= 10) {
            flash();
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(ENERGY_AMT));
        }
    }

    @Override
    public String getUpdatedDescription() {
        AbstractPlayer.PlayerClass c = (AbstractDungeon.player != null ? AbstractDungeon.player.chosenClass : null);
        if (c == null) {
            return DESCRIPTIONS[0] + BLOCK_AMT + DESCRIPTIONS[1] + DESCRIPTIONS[2];
        } else {
            switch(c) {
                case IRONCLAD:
                    return DESCRIPTIONS[0] + BLOCK_AMT + DESCRIPTIONS[1] + DESCRIPTIONS[2];
                case THE_SILENT:
                    return DESCRIPTIONS[0] + BLOCK_AMT + DESCRIPTIONS[1] + DESCRIPTIONS[3];
                case CROWBOT:
                    return DESCRIPTIONS[0] + BLOCK_AMT + DESCRIPTIONS[1] + DESCRIPTIONS[4];
                default:
                    return DESCRIPTIONS[0] + BLOCK_AMT + DESCRIPTIONS[1] + DESCRIPTIONS[2];
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new TransformerCore();
    }
}
