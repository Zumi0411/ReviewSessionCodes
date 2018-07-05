Feature: Etsy product search

  Scenario: Etsy search for an item
    Given User in on Etsy homepage
    When User searches for "golden spoon"
    Then Search results should be displayed