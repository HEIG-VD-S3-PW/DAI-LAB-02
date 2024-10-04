# Workflow 
**Here is a workflow to follow when working on a project.**

### 1. Create an issue and assign it
Add an explicit title and a short description of the issue. If the issue is a bug, provide a detailed description of the feature to add, to improve or the bug to fix.

Example : 

- Title : Fix the login page
- Title : Add an authentication feature
- Title : Improve the user interface
- Title : Update the documentation

### 2. Create a branch and work on the issue
Before creating a branch, make sure you are on the main branch and that it is up to date. (```git checkout main``` and ```git pull```)

Use the command `git checkout -b <branch_name>` to create a new branch and switch to it.

- If it is a bug fixe, use the following naming convention: `bug/fix-bug-name`.
- If it is a feature to add, use the following naming convention: `feature/add-feature-name`.
- If it is an improvement, use the following naming convention: `improvement/improve-feature-name`.
- If it is a documentation/configuration update, use the following naming convention: `configuration/update-documentation`.

### 3. Commit changes 
Use the command `git add <filenames> (or .)` and `git commit -m "message"` to commit changes.

### 4. Push changes
Use the command `git push origin <branch_name>` to push changes to the remote repository.

**The branch name is the one created in step 2.**

### 5. Create a pull request
Create a pull request to merge the changes into the main branch.

**Specify the issue number in the pull request title and description.**

### 6. Review the pull request
Review the pull request and provide feedback.

### 7. Merge the pull request
Merge the pull request into the main branch.


## Concrete example

### 1. Create an issue and assign it
- Title : Fix the login page
- Description : The login page is not displayed correctly on mobile devices.

### 2. Create a branch and work on the issue
```
git checkout main
git pull
git checkout -b bug/fix-login-page
```

### 3. Commit changes
```
git add .
git commit -m "Fix the login page on mobile devices"
```

### 4. Push changes
```
git push origin bug/fix-login-page
```

### 5. Create a pull request
- Title : Fix #NUMBER_OF_THE_ISSUE - Fix the login page
- Description : The login page is not displayed correctly on mobile devices. This pull request fixes the issue #NUMBER_OF_THE_ISSUE.

### 6. Review the pull request
- Check the changes made
- Check the code quality

### 7. Merge the pull request
- Merge the pull request into the main branch
- Delete the branch
- Close the issue if it is completely added/fixed/improved
