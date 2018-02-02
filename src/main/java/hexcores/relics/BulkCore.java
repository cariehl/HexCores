package hexcores.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BulkCore extends CustomRelic {
    public static final String ID = "BulkCore";
    private static final String IMG = "images/relics/circlet.png";
    private static final String OUTLINE = "images/relics/outline/circlet.png";

    private static final int BLOCK_AMT = 10;
    private static final int STR_AMT = 1;

    public BulkCore() {
        super(ID, new Texture(Gdx.files.internal(IMG)), new Texture(Gdx.files.internal(OUTLINE)), RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.currentBlock >= 10) {
            flash();
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, STR_AMT), STR_AMT));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BLOCK_AMT + DESCRIPTIONS[1] + STR_AMT + DESCRIPTIONS[2];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BulkCore();
    }
}
