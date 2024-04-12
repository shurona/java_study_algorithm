package problem;

public class BindBondage {
    public void solution() {
//        int[] bandage = {5, 1, 5};
//        int health = 30;
//        int[][] attacks = {{2, 10}, {9, 15}, {10, 5}, {11, 5}};

//        int[] bandage = {1, 1, 1};
//        int health = 5;
//        int[][] attacks = {{1, 2}, {3, 2}};

        int[] bandage = {3, 2, 7};
        int health = 20;
        int[][] attacks = {{1, 15}, {5, 16}, {8, 6}};

        int startHealTime = 0;
        int currentHealth = health;
        for (int i = 0; i < attacks.length; i++) {
            int[] attack = attacks[i];
            int attackTime = attack[0];

            int healTime = attackTime - startHealTime;

            currentHealth += bandage[1] * healTime;
            if (healTime >= bandage[0]) {
                int bonusHeal = healTime/bandage[0];
                currentHealth += bonusHeal * bandage[2];
            }

            // 회복된 체력을 확인한다.
            currentHealth = Math.min(health, currentHealth);

            int attackDamage = attack[1];
            currentHealth -= attackDamage;


            if (currentHealth <= 0) {
                currentHealth = -1;
                break;
            }

            startHealTime = attackTime + 1;
        }

        System.out.println("currentHealth = " + currentHealth);
        System.out.println("BindBondage.solution");
    }
}
