import pytest
from src.dark_mode import DarkMode

def test_initial_mode_is_light():
    """Test that the initial mode is Light."""
    dark_mode = DarkMode()
    assert dark_mode.is_dark_mode == False, "Initial mode should be Light (is_dark_mode should be False)"

def test_toggle_dark_mode_once():
    """Test toggling dark mode once changes mode to Dark."""
    dark_mode = DarkMode()
    dark_mode.toggle_dark_mode()
    assert dark_mode.is_dark_mode == True, "After one toggle, mode should be Dark (is_dark_mode should be True)"

def test_toggle_dark_mode_twice():
    """Test toggling dark mode twice changes mode back to Light."""
    dark_mode = DarkMode()
    dark_mode.toggle_dark_mode()
    dark_mode.toggle_dark_mode()
    assert dark_mode.is_dark_mode == False, "After two toggles, mode should be Light (is_dark_mode should be False)"

def test_toggle_dark_mode_multiple_times():
    """Test toggling dark mode multiple times."""
    dark_mode = DarkMode()
    for _ in range(5):
        dark_mode.toggle_dark_mode()
    assert dark_mode.is_dark_mode == True, "After odd number of toggles, mode should be Dark (is_dark_mode should be True)"
    dark_mode.toggle_dark_mode()
    assert dark_mode.is_dark_mode == False, "After even number of toggles, mode should be Light (is_dark_mode should be False)"