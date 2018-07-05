@csvReportDataValidation
Feature: Applicants Data CSV report

Background:
    Given User is on Applicants report page
      
Scenario: Validate UI Data against CSV report
    Given User can read applicants data from table
	And cvs report is generated
	Then data in ui and csv report should match       