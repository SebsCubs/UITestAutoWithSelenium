Feature: User account interaction

  Scenario Outline: Invalid registration
    Given the registration form is open
    When the registration is attempted with a "<fault_type>" error
    Then the page reloads with an error is prompted
    Examples:
      | fault_type                |
      | 'Celular' is not filled   |
      | The passwords don't match |
      | Invalid mail format       |

  Scenario: Successful sign in:
    Given the login dialog is open
    When the username and password are entered
    Then the user is signed in and the name appears in the top right corner

  Scenario Outline: Unsuccessful sign in
    Given the login dialog is open
    When  the "<username>" and "<password>" are entered
    Then an error prompts stating the credentials are invalid
    Examples:
      | username             | password          |
      | fallatest@yandex.com | FaultyPass        |
      | faulty@mail.com      | FaultyPass        |

  Scenario: Successful sign-out
    Given an user account is signed in
    When the account is signed out
    Then the user is not signed any more


