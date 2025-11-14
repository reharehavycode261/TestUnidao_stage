class DarkMode:
    def __init__(self):
        self.is_dark_mode = False

    def toggle_dark_mode(self):
        self.is_dark_mode = not self.is_dark_mode
        mode = "Dark" if self.is_dark_mode else "Light"
        print(f"Mode changed to {mode} mode.")

# Example usage
dark_mode_setting = DarkMode()
dark_mode_setting.toggle_dark_mode()  # Output: Mode changed to Dark mode.
dark_mode_setting.toggle_dark_mode()  # Output: Mode changed to Light mode.