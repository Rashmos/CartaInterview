# Distributions

## Architecture Overview

The main objects in this repository are
 - VC Firm
 - Investors
 - InvestorHoldings - Position information of an investor
 
 InvestorHoldings was created separate from Firm and Investor so that they have the necessary
 information for making calculations. Otherwise if investor was part of company or vice versa,
 sensitive data can be accessed which should not be allowed. Ex: Company A should not be able to see 
 other companies one of its investor has invested in.
 
 The classes are created in a way that allow flexibility for an investor to invest in multiple companies
 
 Effort has been put into designing code in a way that investor does not allow of unauthorized access to 
 objects that expose more information than the object should be able to.
 
 ## Rules Engine
 
 There exist a bag of rules who's only job is to apply the rule.
 The Rules Engine orders the rules and executes them.
 
 - The purpose of this was to enable multiple companies to reuse the same rules(in the same order or not).
 - Also, a new company can model their distribution strategy from an existing company by re-using the rules engine.
 - Goal was to maximize code reuse.
 
 
 ## Tests
 
 - Due to time constraints, implemented tests that verify the calculations are being made correctly.
 - If I had more time - I would have written unit tests for each class involving Business Logic - especially
 for the Rule classes which contain core logic of how the rule is being applied.
   
 
## Other Considerations - If I had more time 

Also wanted to write a Driver Class that helps setup. It would be able to convert the `Ownership` section
of the problem into appropriate objects and their associations.

A main program to try running different rule engines for different vc firms and spitting out outputs.
# Test
