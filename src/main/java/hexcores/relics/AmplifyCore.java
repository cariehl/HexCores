package hexcores.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hexcores.HexCores;

public class AmplifyCore extends CustomRelic
{
    public static final String ID = "hexcores:AmplifyCore";
    public static final String IMG = HexCores.assetPath("images/relics/AmplifyCore.png");
    public static final String OUTLINE = HexCores.assetPath("images/relics/outline/HexCore.png");

    public static final int BLOCK_AMT = 10;
    public static final int DMG_AMT = 10;


    public AmplifyCore()
    {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.UNCOMMON, LandingSound.HEAVY);
    }


    @Override
    public void atTurnStart()
    {
        if (AbstractDungeon.player.currentBlock >= 10) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(DMG_AMT, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
        }
    }


    @Override
    public String getUpdatedDescription()
    {
        return DESCRIPTIONS[0] + BLOCK_AMT + DESCRIPTIONS[1] + DMG_AMT + DESCRIPTIONS[2];
    }
}
