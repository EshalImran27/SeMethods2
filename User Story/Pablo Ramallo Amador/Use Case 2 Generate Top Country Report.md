###### **Use Case: 2 Generate Top Country Report**

**--------------------------------------------------**

**CHARACTERISTIC INFORMATION**

**Goal in Context:** Generate a report of top x numbers, specify by the user, of the most populated countries on a specify continent, region or globally that contains code, name, continent, region, population and capital.

**Scope:** Population Information System

**Level:** Primary Task

**Preconditions:** Access to the system and the system contains a specify database

**Success End Condition:** The organization, WorldPop, user successfully receives the report.

**Failed End Condition:** The report fails to generate and display an error message.

**Primary Actor:** Population Data Analyst (Organisation User)

**Trigger:** User requests the “Top Country report”

**----------------------------------------**

**MAIN SUCCESS SCENARIO**

    1. User selects the “Top Country Report” option.

    2. System ask for the number of top populated country

    3. User input the number

    4. System shows for input (continent, region, or global filter).

    5. User specifies the desired filter.

    6. System retrieves top countries from the database matching the specified number of result and the filter selected

    7. System display the report.

**----------------------**

**EXTENSIONS**

    • 3a. If no number is input:

        ◦ 3a1. The system defaults to Top 5 countries.

    • 3b. If input is not a numeric:

        ◦ 3b1. System displays an error message.

    • 5a. If filter is not selected:

        ◦ 5a1. The report defaults to the global filter.

    • 6a If database is not provided or contain an error:

        ◦ 6a1. System displays an error message.

**--------------------**

**SUB-VARIATIONS**

3.The user may input

    • No number entered (defaults to 5)

    • Valid number

5\. The user may selected

    • Global (default)

    • Continent

    • Region

**----------------------**

**RELATED INFORMATION**

**Priority:** High

**Performance Target:** 10 seconds per report generation

**Frequency:** Frequent (daily/weekly use expected)

**Superordinate Use Case:** None

**Subordinate Use Cases:** None

**Channel to primary actor:** Interactive (App GUI)

**Secondary Actors:**  Database System

**Channel to Secondary Actors:** SQL Query

**----------------------------**

**OPEN ISSUES**

    • None

**---------------------------**

**SCHEDULE**

**Due Date:** Friday, 28 November 2025, 6:00 PM



