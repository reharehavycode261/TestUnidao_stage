import pytest
from babel import Locale
from src.i18n import Internationalization

def test_initialization_default_language():
    """Test initialization with default language."""
    i18n = Internationalization()
    assert i18n.current_language == "en", "Default language should be 'en'"
    assert i18n.locale == Locale.parse("en"), "Locale should be initialized to 'en'"

def test_initialization_custom_language():
    """Test initialization with a custom language."""
    i18n = Internationalization("fr")
    assert i18n.current_language == "fr", "Language should be initialized to 'fr'"
    assert i18n.locale == Locale.parse("fr"), "Locale should be initialized to 'fr'"

def test_set_language_valid():
    """Test setting a valid language."""
    i18n = Internationalization()
    i18n.set_language("es")
    assert i18n.current_language == "es", "Language should be set to 'es'"
    assert i18n.locale == Locale.parse("es"), "Locale should be set to 'es'"

def test_set_language_invalid():
    """Test setting an invalid language."""
    i18n = Internationalization()
    i18n.set_language("invalid_lang")
    assert i18n.current_language == "en", "Language should remain 'en' after invalid input"
    assert i18n.locale == Locale.parse("en"), "Locale should remain 'en' after invalid input"

def test_translate_default_language():
    """Test translation with default language."""
    i18n = Internationalization()
    result = i18n.translate("hello")
    expected = "English: hello"
    assert result == expected, f"Translation should be '{expected}'"

def test_translate_custom_language():
    """Test translation after setting a custom language."""
    i18n = Internationalization()
    i18n.set_language("fr")
    result = i18n.translate("hello")
    expected = "français: hello"
    assert result == expected, f"Translation should be '{expected}'"

def test_translate_with_kwargs():
    """Test translation with additional keyword arguments."""
    i18n = Internationalization()
    result = i18n.translate("greeting", name="World")
    expected = "English: greeting"
    assert result == expected, f"Translation should be '{expected}'"

def test_set_language_exception_handling(mocker):
    """Test exception handling in set_language method."""
    i18n = Internationalization()
    mocker.patch('babel.Locale.parse', side_effect=Exception("Mocked exception"))
    i18n.set_language("es")
    assert i18n.current_language == "en", "Language should remain 'en' after exception"
    assert i18n.locale == Locale.parse("en"), "Locale should remain 'en' after exception"