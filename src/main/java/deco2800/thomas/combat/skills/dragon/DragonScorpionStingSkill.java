package deco2800.thomas.combat.skills.dragon;

import deco2800.thomas.combat.skills.AbstractSkill;
import deco2800.thomas.entities.agent.Peon;
import deco2800.thomas.tasks.AbstractTask;
import deco2800.thomas.tasks.combat.ScorpionStingAttackTask;

/**
 * Launches a sting projectile that deals damage when it hits a target, and
 * then applies damage over time.
 */
public class DragonScorpionStingSkill extends AbstractSkill {
    /* Maximum time of cooldown in ticks */
    private static int cooldown = 300;
    /* Damage multiplier to apply to the ice tile.
    Multiplies the peon base damage value. */
    private static float damageMultiplier = 0.4f;
    /* Speed of projectile */
    private static final float SPEED = 0.5f;
    /* Lifetime of projectile */
    private static final int LIFETIME = 40;

    /* Reference to parent entity */
    private final Peon entity;

    /**
     * Creates a new ScorpionStingSkill and binds it to the Entity.
     * @param parent Parent entity of skill.
     * @throws NullPointerException when parent is null
     */
    public DragonScorpionStingSkill(Peon parent) {
        if (parent == null) {
            throw new NullPointerException();
        }
        this.entity = parent;
    }

    /**
     * Returns (in ticks) how long the full cooldown of this skill is.
     *
     * @return Maximum cooldown of skill.
     */
    @Override
    public int getCooldownMax() {
        return cooldown;
    }

    @Override
    public void setCooldownMax(int cooldownMax) {
        cooldown = cooldownMax;
    }

    /***
     * Sets coooldown of skill
     * @param cooldown cooldown of skill
     */
    public static void setCooldown(int cooldown){
        DragonScorpionStingSkill.cooldown = cooldown;
    }

    /**
     * Returns multiplier of skill
     *
     * @return Multiplier of skill.
     */
    public float getDamageMultiplier(){
        return damageMultiplier;
    }

    /***
     * Set multiplier of skill
     *
     * @param damageMultiplier multiplier of skill
     */
    public static void setDamageMultiplier(float damageMultiplier){
        DragonScorpionStingSkill.damageMultiplier = damageMultiplier;
    }

    public static void reduceCooldownMax(float percent){
        if (cooldown > 25) {
            cooldown = Math.round(cooldown * (1.0f - percent));
        }
    }


    /**
     * Returns a string containing the name of the texture that is used to represent
     * this skill on the skill bar.
     *
     * @return
     */
    @Override
    public String getTexture() {
        return "stingIcon";
    }

    /**
     * Creates and returns a new skill task for this skill that is executed by the
     * entity executing the skill.
     * @param targetX X position of target in ColRow coordinates
     * @param targetY Y position of target in ColRow coordinates
     * @return New AbstractTask to execute.
     */
    @Override
    protected AbstractTask getTask(float targetX, float targetY) {
        int damage = (int) (entity.getDamage() * damageMultiplier);
        return new ScorpionStingAttackTask(entity, targetX, targetY, damage, SPEED, LIFETIME);
    }
}
