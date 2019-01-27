package hexcores.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import hexcores.HexCores;

public class BulkCore extends CustomRelic
{
    public static final String ID = "hexcores:BulkCore";
    public static final String IMG = HexCores.assetPath("images/relics/BulkCore.png");
    public static final String OUTLINE = HexCores.assetPath("images/relics/outline/HexCore.png");

    public static final int BLOCK_AMT = 10;
    public static final int STR_AMT = 2;


    public BulkCore()
    {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.UNCOMMON, LandingSound.HEAVY);
    }


    @Override
    public void atTurnStart()
    {
        if (AbstractDungeon.player.currentBlock >= 10) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, STR_AMT), STR_AMT));
        }
    }

    @Override
    public String getUpdatedDescription()
    {
        return DESCRIPTIONS[0] + BLOCK_AMT + DESCRIPTIONS[1] + STR_AMT + DESCRIPTIONS[2];
    }
}
