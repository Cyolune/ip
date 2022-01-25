import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parser represents a command parser that parses the user commands to Sana
 *
 * @author Jan
 * @version 1.0
 */
public class Parser {

    /**
     * Parses the command given to Sana
     *
     * @param userCommand   the command from the user
     * @return              a string of arguments from parsing the command
     */
    public String[] parseCommand(String userCommand) {
        try {
            if (userCommand.equals("bye")) {
                return new String[]{"bye"};

            } else if (userCommand.equals("list")) {
                return new String[]{"list"};

            } else if (userCommand.startsWith("mark ")) {
                String[] substringArr = userCommand.split(" ", 2);
                if (substringArr.length == 1) {
                    throw new IncompleteCommandException();
                }
                return new String[]{"mark", substringArr[1]};
            } else if (userCommand.startsWith("unmark ")) {
                String[] substringArr = userCommand.split(" ", 2);
                if (substringArr.length == 1) {
                    throw new IncompleteCommandException();
                }
                return new String[]{"unmark", substringArr[1]};
            } else if (userCommand.startsWith("todo ")) {
                String taskName = userCommand.replaceFirst("todo ", "");
                return new String[]{"todo", taskName};

            } else if (userCommand.startsWith("event ")) {
                String temp = userCommand.replaceFirst("event ", "");
                if (!temp.contains(" /at ")) {
                    throw new IncompleteCommandException();
                }
                String[] subStrings = temp.split(" /at ", 2);
                if (subStrings.length == 1) {
                    throw new IncompleteCommandException();
                }
                return new String[]{"event", subStrings[0], subStrings[1]};


            } else if (userCommand.startsWith("deadline ")) {
                String temp = userCommand.replaceFirst("deadline ", "");
                if (!temp.contains(" /by ")) {
                    throw new IncompleteCommandException();
                }
                String[] subStrings = temp.split(" /by ", 2);
                if (subStrings.length == 1) {
                    throw new IncompleteCommandException();
                }
                return new String[]{"deadline", subStrings[0], subStrings[1]};

            } else if (userCommand.startsWith("delete ")) {
                String[] substringArr = userCommand.split(" ", 2);
                if (substringArr.length == 1) {
                    throw new IncompleteCommandException();
                }
                return new String[]{"delete", substringArr[1]};

            } else {
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        } catch (IncompleteCommandException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Where's my number!");
        } catch (DateTimeParseException e) {
            System.out.println("Give your date in YYYY-MM-DD format!");
        }
        return new String[] {};
    }
}
