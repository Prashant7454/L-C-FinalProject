package Application.command;

public interface MenuAction {
    String getName();
    void execute(int userId);
}