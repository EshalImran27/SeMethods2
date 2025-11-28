**Contributing to World Population Report**

Thank you for your interest in contributing to the World Population Report project! This document provides guidelines for contributing to this repository.

**Table of Contents**

- How to Contribute**
- Development Setup
- Branch Naming Convention
- Code Standards
- Testing Requirements
- Pull Request Process

**How to Contribute**

We welcome contributions in the following areas:

*Bug Reports*

If you find a bug, please report it by creating an issue using our Bug Report template. Include:

- Clear description of the bug
- Steps to reproduce
- Expected vs actual behavior
- Your environment details

*Data Corrections*

If you notice incorrect, missing, or outdated population data:

- Create an issue using our Data Issue template
- Provide the correct data with a reliable source
- Specify which reports are affected

*Note:* For significant code changes or new features, please discuss with the team first before submitting a pull request.

**Development Setup**

*Prerequisites*

- *Java Version*: 17
- *Recommended IDE*: IntelliJ IDEA
- *Git:* For version control

*Setup Steps*

- Fork the repository
- Clone your fork to your local machine:

- bash   git clone https://github.com/EshalImran27/SeMethods2.git

- Open the project in IntelliJ IDEA
- Configure the database connection in the configuration file
- Build the project to ensure everything works

**Branch Naming Convention**

We use a folder-based branch naming system. Create your branch under the appropriate folder:

*Branch Structure*

 feature/ - For new features

- Example: feature/add-continent-filter
- Example: feature/export-to-csv


 bugfix/ - For bug fixes

- Example: bugfix/fix-sorting-error
- Example: bugfix/database-connection-timeout


test/ - For adding or updating tests

- Example: test/add-population-query-tests
- Example: test/improve-integration-tests


docs/ - For documentation updates

- Example: docs/update-readme
- Example: docs/add-setup-guide


data/ - For data corrections or updates

- Example: data/fix-india-population
- Example: data/update-city-data



**Creating a Branch**
- bashgit checkout -b feature/your-feature-name
- Code Standards
- Java Coding Style

*Follow standard Java naming conventions*
- Use camelCase for variables and methods
- Use PascalCase for class names
- Keep methods focused and concise

*Comments*
- All comments must follow Java format:
java/**
 * Retrieves all countries sorted by population in descending order.
 * 
 * @return ArrayList of Country objects sorted by population
 * @throws SQLException if database connection fails
 */
public ArrayList<Country> getAllCountriesByPopulation() throws SQLException {
    // Implementation here
}

// Single-line comments for brief explanations
int totalPopulation = 0; // Initialize population counter
Comment Guidelines

Use JavaDoc (/** */) for all public methods and classes
Use single-line comments (//) for inline explanations
Write clear, descriptive comments that explain "why", not just "what"
Keep comments up-to-date when code changes

**Testing Requirements**
*Unit Testing*
All new features MUST include unit tests before being merged.
*Testing Guidelines*

- Write unit tests for all new methods
- Tests should cover normal cases and edge cases
- Use descriptive test method names
- Ensure all tests pass before submitting a pull request

*Running Tests*
bash# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests YourTestClassName
Test Structure Example
java@Test
public void testGetAllCountriesByPopulation_ReturnsCorrectOrder() {
    // Arrange
    // Act
    // Assert
}
Pull Request Process
Before Submitting

✅ Ensure your code follows the coding standards
✅ Add appropriate comments in Java format
✅ Write and run unit tests for new features
✅ Verify all existing tests still pass
✅ Update documentation if needed
✅ Commit with clear, descriptive messages

**Submitting a Pull Request**

- Push your branch to your fork
- Go to the original repository on GitHub
- Click "New Pull Request"
- Select your branch
- Fill out the pull request template with:

**Description of changes**
- Related issue number (if applicable)
- Testing performed
- Screenshots (if relevant)


**Request review from team members**
Address any feedback from reviewers

*Pull Request Title Format*
Use clear, descriptive titles:

[Feature] Add continent filter to country reports
[Bugfix] Fix sorting error in city population query
[Test] Add unit tests for database queries
[Data] Correct population data for Tokyo

*Review Process*

At least one team member must review and approve
Address all comments and requested changes
Once approved, a team member will merge your PR

*Questions or Issues?*
If you have questions or need help:

Check existing issues and documentation
Create a new issue using the appropriate template
Contact the team members directly

Code of Conduct
Please note that this project is released with a Code of Conduct. By participating in this project you agree to abide by its terms.

Thank you for contributing to the World Population Report project! 🌍📊
