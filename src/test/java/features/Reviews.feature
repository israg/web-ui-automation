Feature: Reviews

  Background:

  Scenario:
    Given Check page url contains "https://www.hepsiburada.com/"
    And Click "search"
    When Fill "searchArea" field with "iPhone"
    And Click "searchButton"
    And Scroll to "firstProduct" element
    And Click "firstProduct"
    And Scroll page to down on 1 tab
    And Scroll to "reviewsTab" element
    And Click "reviewsTab"
    And Scroll page to down on 1 tab
    And Click "firstThumbsUp"
    Then Check "Teşekkür Ederiz." text existence on page