Feature: User account interaction
  Scenario Outline: Invalid registration
    Given The registration form is open
    When The registration is attempted with a "<fault_type>"
    Then The page reloads with an error is prompted
    Examples:
      |fault_type |Meaning                  |
      |1          |"Celular" is not filled  |
      |2          |The passwords don't match|
      |3          |Invalid mail format      |

  Scenario: Successful sign in:
    Given The login dialog is open
    When The username and password are entered
    Then The user is signed in and the name appears in the top right corner

  Scenario Outline: Unsuccessful sign in
    Given The login dialog is open
    When  The "<username>" and "<password>" is entered
    Then An error prompts stating the credentials are invalid
    Examples:
      |username            |password         |
      |faulty@mail.com     |FallabellaTest123|
      |fallatest@yandex.com|FaultyPass       |
      |faulty@mail.com     |FaultyPass       |

  Scenario: Successful sign-out
    Given An user account is signed in
    When The account is signed out
    Then The user is not signed any more


