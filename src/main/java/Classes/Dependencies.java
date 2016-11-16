package Classes;

import navis.injection.BuildFunction;
import navis.injection.BuildFunction1Arg;
import navis.injection.InstanceFactory;
import navis.injection.InstanceFactory1Arg;

import java.util.Random;

/**
 * Created by mikehollibaugh on 11/11/16.
 */
public class Dependencies {

    public static final InstanceFactory1Arg<Random, String> random = new InstanceFactory1Arg<>(new BuildFunction1Arg<Random, String>() {
        private Random random = null;

        @Override
        public Random build(String A) {
            if (random == null)
                if (A.isEmpty()) {
                    random = new Random();
                } else {
                    random = new Random(Integer.parseInt(A));
                }
            return random;
        }
    });

    public static final InstanceFactory<ConsoleIO> console = new InstanceFactory<>(new BuildFunction<ConsoleIO>() {
        private ConsoleIO consoleIO = null;

        @Override
        public ConsoleIO build() {
            if (consoleIO == null)
                consoleIO = new ConsoleIO();
            return consoleIO;
        }
    });

    public static final InstanceFactory<Deck> deck = new InstanceFactory<>(new BuildFunction<Deck>() {

        @Override
        public Deck build() {
            return new Deck();
        }
    });

    public static final InstanceFactory<Hand> hand = new InstanceFactory<>(new BuildFunction<Hand>() {

        @Override
        public Hand build() {
            return new Hand();
        }
    });

    public static final InstanceFactory<HumanPlayer> humanPlayer = new InstanceFactory<>(new BuildFunction<HumanPlayer>() {
        private HumanPlayer humanPlayer = null;

        @Override
        public HumanPlayer build() {
            if (humanPlayer == null)
                humanPlayer = new HumanPlayer();
            return humanPlayer;
        }
    });

    public static final InstanceFactory<BotPlayer> botPlayer = new InstanceFactory<>(new BuildFunction<BotPlayer>() {
        private BotPlayer botPlayer = null;

        @Override
        public BotPlayer build() {
            if (botPlayer == null)
                botPlayer = new BotPlayer();
            return botPlayer;
        }
    });
}
