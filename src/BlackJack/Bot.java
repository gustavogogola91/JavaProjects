package BlackJack;

import java.security.SecureRandom;

public class Bot extends Player {

    public Bot(String name) {
        super(name);
    }

    public boolean decision() {
        SecureRandom generator = new SecureRandom();

        if(sumCards() <= 11) {
            addCard();
        } else if(sumCards() <= 15) {
            if(generator.nextInt(2) == 0) {
                addCard();
            }
            else {
                System.out.println("Computador não quer mais cartas!");
                return false;
            }
        } else if (sumCards() <= 19) {
            if(generator.nextInt(5) == 2) {
                addCard();
            } else {
                System.out.println("Computador não quer mais cartas!");
                return false;
            }
        } else {
            System.out.println("Computador não quer mais cartas!");
            return false;
        }

        return true;
    }
}
