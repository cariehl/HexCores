package hexcores.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hexcores.HexCores;

public class TransformCore extends CustomRelic
{
    public static final String ID = "hexcores:TransformCore";
    public static final String IMG = HexCores.assetPath("images/relics/TransformCore.png");
    public static final String OUTLINE = HexCores.assetPath("images/relics/outline/HexCore.png");

    public static final int BLOCK_AMT = 10;
    public static final int ENERGY_AMT = 2;


    public TransformCore()
    {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.UNCOMMON, LandingSound.HEAVY);
    }


    @Override
    public void atTurnStart()
    {
        if (AbstractDungeon.player.currentBlock >= 10) {
            flash();
            // TODO: Is this the right order to stack things?
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(ENERGY_AMT));
        }
    }


    @Override
    public String getUpdatedDescription()
    {
        return DESCRIPTIONS[0] + BLOCK_AMT + DESCRIPTIONS[1];
    }
}
