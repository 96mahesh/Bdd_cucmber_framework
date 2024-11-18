Feature: Testing  Twitter Application Login page
Background: 
Given user need to on Tiwtter Login Page
@SMOKE
Scenario: verify login button functionality by email as a blank
When  user should able to enter username "7842358565"
When  user should able to enter password "swapna" 
Then  user click on login button
And close browser