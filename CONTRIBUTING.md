# Contributing Guidelines
## Welcome!!
Thanks for taking the time to look at our project. It’s great to see you here.
Following these guidelines will help you to make meaningful contributions to this open source project.

## What we’re looking for
We’re not looking for outside contributions yet, but thanks for your interest!

## How to set up your environment
My Study is created using java, [javaFX](https://openjfx.io/openjfx-docs/) and [scenebuilder](https://gluonhq.com/products/scene-builder/) . So you’ll need the three of those installed and set up on the IDE of your choice (we recommend IntelliJ or eclipse). If you intend to contribute code, also set up [SonarLint](https://www.sonarlint.org/) on your IDE for line-by-line code checking to help you write clean code.

## How to contribute
Start by forking the main repository into your personal GitHub account. If you are creating a new feature (see below on the procedure on doing this), then wait for approval before assigning yourself to the issue and starting work on it. Otherwise, pick out an issue that no one is currently working on, assign yourself to it, and get started! 
### Code guidelines
You should write clean code, using SonarLints help, and with appropriate commenting. Each method should have a header comment outlining its purpose, and you should comment on any strange code. Your design should match existing features, most importantly using the same side navigation bar and colour scheme. The layout should be responsive to all common desktop screens. Try to contain your changes to a few files wherever possible, and do not delete other contributors code unless absolutely necessary. 
### Commits
The contents and style of your commits is mostly up to you, although we ask that you begin the title of the commit with a relevant label, such as feat/ or bug/.
### Creating a pull request
Only create a pull request when your feature is complete and fully tested. Each pull request should correspond to a single issue. If you changed any features from how they were described in the issue, please comment on these changes in the body of the pull request. The title of the pull request should contain a short description of the changes, and the number of the issue that you were working on. You should also assign this issue to the pull request, so that your pull request will be accessible from the issues page. SonarCloud will automatically run on your pull request, and you should wait for this analysis to complete and fix any issues that it reveals. 
At least one person must approve your pull request before it can be merged. You can assign someone, or multiple people, to the pull request manually, or you can wait for someone to review it. The guidelines for reviewers are detailed below. 

## How to suggest a new feature
Create a GitHub issue with a full description of the feature, its functionality, and a mock-up of its design. You can also include some implementation ideas, e.g. an API to use for it, but this is not required. Label the issue accordingly, most likely either feature, for completely new functionality, or enhancement, for improving an existing feature. Ensure that the feature fits within the theme of the project (i.e. is related to student productivity) and does not exist within the repo or as an existing issue. 

## How to run tests
Testing is done in two ways: automated CI testing with SonarCloud, and manual testing. SonarCloud is integrated into the GitHub repo and will appear when you create a pull request. Once you make a pull request, fix any issues that SonarCloud declares. While writing your code, you should ensure that you fix any issues that SonarLint finds, and follow clean code principles. Functionality testing is done manually, by running the code and testing each feature with both expected and unexpected values. If you find a bug or anything unexpected, make sure to file a bug report. 

## How to file a bug report
Create a GitHub issue by following this template. Make sure you check the existing list of issues in case someone else already found the bug. 
Title: Quick summary of the bug
Label: bug
Body: 
-	Context: describe the exact issue and why it is relevant
-	Location: what feature/s this bug effects
-	Process: step-by-step guide to recreate the issue
-	Expected result: what you would’ve expected/wanted to happen
-	Current result: the outcome of the bug, including screenshots if relevant
-	Possible fix: If you have an idea of how to fix it, write it down here!

## How to review a pull request
At least one person needs to review and approve a pull request before it can be merged. When reviewing a pr, there are a few things to do:
1.	Check that the pr is passing all SonarCloud tests. This will be indicated immediately below the pull request.
2.	Check out the branch and manually test the code. This should include testing the main functionality of the feature and also some unexpected actions to ensure that the code can appropriately handle exceptions. 
3.	Review the code and ensure that it is clean. This includes having appropriate comments, meaningful variable names, and having short code modules. 
If the code does not fulfil any of the above guidelines, then the pr should not be merged. Lay out your findings, and try to keep your comments specific and actionable. 
If the code passes all of the above reviews and you are happy with it, then go ahead and merge the pull request. 

## Code of conduct
Check out our [code of conduct](CODE_OF_CONDUCT)

