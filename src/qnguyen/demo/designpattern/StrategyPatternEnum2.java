package qnguyen.demo.designpattern;

public class StrategyPatternEnum2 {
    public static void main(String[] args) {
        new Player(Strategy.T20);
        new Player(Strategy.TEST);
        new Player(Strategy.ONE_DAY);
    }

    private static class Player {
        private Strategy strategy;

        public Player(Strategy strategy) {
            this.strategy = strategy;
        }

        public void play() {
            this.strategy.play();
        }
    }

    enum Strategy  {
        /* Make sure to score quickly on T20 games */
        T20 {

            @Override
            public void play() {
                System.out.printf("In %s, If it's in the V, make sure it goes to tree %n", name());
            }
        },

        /* Make a balance between attach and defence in One day */
        ONE_DAY {

            @Override
            public void play() {
                System.out.printf("In %s, Push it for Single %n", name());
            }
        },

        /* Test match is all about occupying the crease and grinding opposition */
        TEST {
            @Override
            public void play() {
                System.out.println("Test");
            }
        };

        public abstract void play();
    }
}

