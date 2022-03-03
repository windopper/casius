package GameFunctions;

public class Game implements IGame {

    private static Game instance;

    int duration = 0;

    public static Game getInstance() {
        if(instance == null) instance = new Game();
        return instance;
    }

    @Override
    public void Ready() {

    }

    @Override
    public void Start() {

    }

    @Override
    public void Stop() {

    }

    @Override
    public boolean vCondition() {
        return false;
    }

    @Override
    public void reset() {

    }
}
