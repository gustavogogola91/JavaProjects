package BlackJack;

import java.util.Scanner;


public class Game {

    public static void main(String[] args) throws InterruptedException {

        boolean flag = true;
        boolean userBlow;
        boolean botBlow;
        boolean userRound;
        boolean continueGame;
        boolean botDecision;
        boolean userWin;
        boolean botWin;
        boolean draw;

        int maxPoint;
        int roundPoints = 1;

        Scanner input = new Scanner(System.in);

        // criação dos jogadores
        Player user = new Player("jogador");
        Bot bot = new Bot("computador");
        System.out.println("Iniciando jogo.");


        System.out.println("Qual a pontuação para definir o ganhador do jogo? ");
        maxPoint = input.nextInt();

        do {

            System.out.println("\nIniciando partida.");

            continueGame = true;


            do{
                userWin = false;
                botWin = false;
                userBlow = false;
                botBlow = false;
                userRound = true;
                draw = false;

                System.out.println("Iniciando nova rodada.");
                System.out.printf("Jogador %d X %d computador%n", user.getPoints(), bot.getPoints());
                waitGame();
                user.addCard();
                waitGame();
                user.addCard();
                do {
                    if(user.sumCards() == 21) {
                        playerWinRound(roundPoints, user);
                        userWin = true;
                        break;

                    } else if( user.sumCards() < 21){
                        System.out.print("Deseja pegar mais uma carta? [s/n]");
                        char more = input.next().charAt(0);
                        more = Character.toLowerCase(more);
                        if(more == 's') {
                            user.addCard();
                        } else {
                            userRound = false;
                        }
                    } else {
                        System.out.println("Você estourou.");
                        userBlow = true;
                        userRound = false;
                    }
                    waitGame();
                } while(userRound);



                if(!userWin) {
                    do {
                        botDecision = bot.decision();
                        waitGame();
                    } while(botDecision);
                    if(bot.sumCards() == 21) {
                        botWinRound(roundPoints, bot);
                        botWin = true;
                    } else if(bot.sumCards() > 21) {
                        System.out.println("Computador estourou.");
                        waitGame();
                        botBlow = true;
                    }

                    if(!botWin) {
                        if(botBlow && !userBlow) {
                            playerWinRound(roundPoints, user);
                        } else if (!botBlow && userBlow) {
                            botWinRound(roundPoints, bot);
                        } else if ((botBlow && userBlow)) {
                            System.out.println("Rodada terminou em empate, adiconando 1 ponto para a próxima rodada.");
                            roundPoints++;
                            draw = true;
                        } else if (user.sumCards() == bot.sumCards()) {
                            System.out.println("Computador e jogador ficaram com o mesmo valor. Empate! Adicionado um ponto para a próxima rodada.");
                            roundPoints++;
                            draw = true;
                        } else if (user.sumCards() > bot.sumCards()) {
                            playerWinRound(roundPoints, user);
                        } else {
                            botWinRound(roundPoints, bot);
                        }
                    }

                }
                if (!draw) {
                    roundPoints = 1;
                }
                waitGame();
                // verifica se o jogador ou o computador ganharam o jogo
                if(user.getPoints() >= maxPoint || bot.getPoints() >= maxPoint){
                    if(user.getPoints() >= maxPoint) {
                        System.out.println("Você ganhou!");
                    } else {
                        System.out.println("Você perdeu");
                    }
                    continueGame = false;
                }

                waitGame();
                newRound(user, bot);
            } while(continueGame);
            // verifica se o usuario quer jogar novamente, se sim reinicia a pontuação como zero.
            System.out.print("Deseja jogar novamente? [s/n]");
            char verify = input.next().charAt(0);
            verify = Character.toLowerCase(verify);
            waitGame();
            if (verify == 's') {
                newGame(user, bot);

            } else {
                flag = false;
            }
        } while(flag);
    }

    private static void waitGame() throws InterruptedException {
        Thread.sleep(1000);
    }

    private static void playerWinRound(int roundPoints, Player user) {
        System.out.printf("O jogador venceu a rodada, adicionando %d pontos.%n", roundPoints);
        user.addPoint(roundPoints);
    }

    private static void botWinRound(int roundPoints, Bot bot) {
        System.out.printf("O computador venceu a rodada, adicionando %d pontos.%n", roundPoints);
        bot.addPoint(roundPoints);
    }

    public static void newGame(Player user, Bot bot) {
        user.newGame();
        bot.newGame();
    }

    public static void newRound(Player user, Bot bot) {
        user.newRound();
        bot.newRound();
    }
}
