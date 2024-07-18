import pyautogui
import pyperclip
import time

def main():
    try:
        text = input("Enter your text: ")
        size = int(input("How many times do you want to print: "))

        # Set the clipboard with the user's text
        pyperclip.copy(text)

        # Allow time to switch to the desired window
        print("You have 0.2 seconds to focus the target window...")
        time.sleep(0.08)

        for _ in range(size):
            # Paste the text
            pyautogui.hotkey('ctrl', 'v')

            # Press Enter
            pyautogui.press('enter')

            # Wait 2 seconds before repeating
            time.sleep(0.01)

    except ValueError:
        print("Invalid input: Please enter a valid number for the print count.")
    except Exception as e:
        print(f"Unexpected error: {e}")

if __name__ == "__main__":
    main()
