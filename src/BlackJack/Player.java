package BlackJack;
import java.security.SecureRandom;
import java.util.Arrays;

public class Player {
    SecureRandom ramdomNumber = new SecureRandom();
    private String name;
    private int cards[] = new int[6];

    private int points;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCard() {
        int card = 1 + ramdomNumber.nextInt(10);
        System.out.printf("Adicionando carta %d ao %s.", card, getName());
        for(int i = 0; i < cards.length; i++) {
            if(cards[i] == 0) {
                this.cards[i] = card;
                break;
            }
        }
        showCards();
    }

    public void showCards() {
        System.out.printf("%nCartas do %s: ", this.name);
        for(int card : cards) {
            if (card != 0) {
                System.out.printf("%d ", card);
            }
        }
        System.out.printf("%nSoma = %d%n", sumCards());
    }

    public int sumCards() {
        int sum = 0;
        for (int card : cards) {
            sum += card;
        }
        return sum;
    }

    public void addPoint(int points) {
        this.points += points;
    }

    public int getPoints() {
        return points;
    }

    public void newGame () {
        this.points = 0;
    }

    public void newRound() {
        Arrays.fill(cards, 0);
    }

}
