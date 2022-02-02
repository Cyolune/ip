package sana;

import java.time.LocalDate;
import java.util.LinkedList;

import sana.command.AddTask;
import sana.command.ByeCommand;
import sana.command.DeleteCommand;
import sana.command.FindCommand;
import sana.command.ListCommand;
import sana.command.MarkCommand;
import sana.command.UnmarkCommand;
import sana.exception.IncompleteCommandException;
import sana.exception.OutOfBoundsTaskException;
import sana.exception.UnknownCommandException;
import sana.task.Deadline;
import sana.task.Event;
import sana.task.Task;
import sana.task.ToDo;

/**
 * Sana is a BIG program!
 *
 * @author  Jan Alfenson Tan
 * @version 8.0
 */
public class Sana {

    /** userTasks stores the commands given to Sana from the user */
    private TaskList userTasks;

    /** taskMem stores the tasks given to Sana in a txt file */
    private Memory taskMem;

    /** parser parses the user command */
    private Parser parser;

    /**
     * Constructor for the Sana class
     */
    public Sana() {
        this.taskMem = new Memory();
        this.userTasks = new TaskList(taskMem.memToList());
        this.parser = new Parser();
    }

    /**
     * This method controls the flow of logic of Sana depending on the user command
     *
     * @param userCommand   the user command
     */
    public String doCommand(String userCommand) {
        String[] parsedCmd = parser.parseCommand(userCommand);
        if (parsedCmd.length == 0) {
            return new UnknownCommandException().getMessage();
        }
        String command = parsedCmd[0];

        try {
            switch (command) {
                case "bye":
                    return new ByeCommand().executeCommand(parsedCmd, userTasks);
                case "list":
                    return new ListCommand().executeCommand(parsedCmd, userTasks);
                case "mark":
                    return new MarkCommand().executeCommand(parsedCmd, userTasks);
                case "unmark":
                    return new UnmarkCommand().executeCommand(parsedCmd, userTasks);
                case "todo":
                case "event":
                case "deadline":
                    return new AddTask().executeCommand(parsedCmd, userTasks);
                case "delete":
                    return new DeleteCommand().executeCommand(parsedCmd, userTasks);
                case "find":
                    return new FindCommand().executeCommand(parsedCmd, userTasks);
                default:
                    throw new UnknownCommandException();
            }
        } catch (UnknownCommandException e) {
            return e.getMessage();
        }
    }
    // GUI code portion

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return doCommand(input);
    }
}
