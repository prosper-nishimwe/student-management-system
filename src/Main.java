import service.StudentService;
import ui.Menu;

public class Main {

    public static void main(String[] args) {

        StudentService studentService = new StudentService();

        Menu menu = new Menu(studentService);

        menu.start();
    }
}