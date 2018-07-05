Feature: Etsy product search

  Scenario: Etsy search for an item
    Given User in on Etsy homepage
    When User searches for "baby spoon"
    Then Search results should be displayed