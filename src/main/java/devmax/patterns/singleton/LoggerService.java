package devmax.patterns.singleton;

public final class LoggerService {
    private static LoggerService instance;

    private LoggerService() {}

    public static synchronized LoggerService getInstance() {
        if (instance == null) instance = new LoggerService();
        return instance;
    }

    public void info(String msg) {
        System.out.println("[INFO] " + msg);
    }
}
