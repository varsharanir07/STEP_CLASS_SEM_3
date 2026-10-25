package week7ap;

/**
 * Problem 1: The Health Bar
 * 
 * Scenario:
 * A game character has health that changes during battle.
 * 
 * Problem Statement:
 * Design a Character class where health can never drop below 0 or rise above its maximum,
 * and can't be set directly from outside.
 * 
 * Requirements:
 * - Health must be private, changed only through takeDamage(int amount) and heal(int amount).
 * - Health must never go below 0 (extra damage is just wasted) or above the maximum (extra healing is just wasted).
 * - The maximum health must be final, fixed when the character is created.
 * - Provide a read-only way to check current health — no setter for it.
 */
class Character {
    private final int maxHealth;
    private int health;

    public Character(int maxHealth) {
        if (maxHealth <= 0) {
            this.maxHealth = 100;
        } else {
            this.maxHealth = maxHealth;
        }
        this.health = this.maxHealth;
    }

    public void takeDamage(int amount) {
        if (amount > 0) {
            health = Math.max(0, health - amount);
        }
    }

    public void heal(int amount) {
        if (amount > 0) {
            health = Math.min(maxHealth, health + amount);
        }
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}

public class TheHealthBar {
    public static void main(String[] args) {
        Character c = new Character(100);
        System.out.println("Character c = new Character(100);");

        c.takeDamage(30);
        System.out.println("c.takeDamage(30) -> health = " + c.getHealth());

        c.heal(50);
        System.out.println("c.heal(50) -> health = " + c.getHealth() + " (capped)");

        c.takeDamage(150);
        System.out.println("c.takeDamage(150) -> health = " + c.getHealth() + " (floored)");
    }
}
