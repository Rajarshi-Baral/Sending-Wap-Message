import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApp {
    /**
     * Main method to run the application.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter your text: ");
            String text = scanner.nextLine();

            System.out.print("How many times do you want to print: ");
            int size = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline

            // Set the clipboard with the user's text
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            // Allow time to switch to the desired window
            System.out.println("You have 5 seconds to focus the target window...");
            Thread.sleep(5000);

            Robot robot = new Robot();
            for (int i = 0; i < size; i++) {
                // Paste the text
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);

                // Press Enter
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                // Wait 2 seconds before repeating
                Thread.sleep(2000);
            }
        } catch (InputMismatchException e) {
            System.err.println("Invalid input: Please enter a valid number for the print count.");
        } catch (AWTException e) {
            System.err.println("Robot initialization error: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore the interrupted status
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
