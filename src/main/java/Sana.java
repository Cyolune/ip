import java.util.*; // Import java utils

/**
 * Sana is a BIG program!
 *
 * @author  Jan Alfenson Tan
 * @version 1.2
 */
public class Sana {
    // The border for Sana's replies
    private static final String border = "_____________________________________________";

    /**
     * userCommands stores the commands given to Sana from the user
     */
    private LinkedList<Task> userTasks;

    /**
     * Constructor for the Sana class
     */
    public Sana() {
        this.userTasks = new LinkedList<>();
    }

    /**
     * This method prints to system IO Sana's greeting
     */
    public void greet() {
        border();
        System.out.println("Hi! I'm BEEEEEEEG\nWhats up?");
        border();
    }

    /**
     * This method takes in the user's command and calls the respective Sana commands
     *
     * @param userCommand   the user command
     */
    public void commandParser(String userCommand) {
        border();
        if (userCommand.equals("bye")) {
            bye();
        } else if (userCommand.equals("list")) {
            list();
        } else if (userCommand.startsWith("mark")) {
            int taskIndex = Integer.parseInt(userCommand.split(" ", 2)[1]) - 1;
            mark(taskIndex, true);
        } else if (userCommand.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(userCommand.split(" ", 2)[1]) - 1;
            mark(taskIndex, false);
        } else if (userCommand.startsWith("todo")) {
            String taskName = userCommand.replaceFirst("todo ", "");
            addToDo(taskName);
        } else {
            addTask(userCommand);
        }
        border();
    }

    /**
     * This method adds a ToDo to userTasks
     *
     * @param taskName  the name of the todo
     */
    private void addToDo(String taskName) {
        addTaskText();
        ToDo newTodo = new ToDo(taskName);
        userTasks.add(newTodo);
        System.out.println(newTodo);
        taskNumberText();
    }

    /**
     * This method marks the task located at the index as done or not done
     *
     * @param taskIndex     the index of the task to be marked done
     * @param completion    the completion of the task
     */
    private void mark(int taskIndex, boolean completion) {
        userTasks.get(taskIndex).setDone(completion);
        if (completion) {
            System.out.println("You've done it! Well done!");
        } else {
            System.out.println("Oopsies! I'll change it back!");
        }
        System.out.println(userTasks.get(taskIndex));
    }

    /**
     * Adds given task to the list of tasks given to Sana.
     *
     * @param taskName  name of task
     */
    private void addTask(String taskName) {
        userTasks.add(new Task(taskName));
        System.out.println("added: " + taskName);
    }

    /**
     * This method lists the history of user inputs to Sana
     */
    private void list() {
        int index = 1;
        System.out.println("Here's your tasks!");
        for (Task task : userTasks) {
            String header = Integer.valueOf(index).toString() + ".";
            System.out.println(header + task);
            index++;
        }
    }

    /**
     * This method prints to system IO how many tasks in user tasks
     */
    private void taskNumberText() {
        String taskAmt = Integer.valueOf(userTasks.size()).toString();
        System.out.println("You have " + taskAmt + " things to do. Time to get working!");
    }

    /**
     * This method prints to system IO Sana's add task text
     */
    private void addTaskText() {
        System.out.println("I've added your task!");
    }

    /**
     * This method prints to system IO Sana's bye
     */
    private void bye() {
        System.out.println("See you next time!");
    }

    /**
     * This method prints the border
     */
    private void border() {
        System.out.println(border);
    }

    public static void main(String[] args) {
        Sana sana = new Sana();
        sana.greet();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            String input = userInput.nextLine();
            sana.commandParser(input);
            if (input.equals("bye")) {
                break;
            }
        }
    }
}
