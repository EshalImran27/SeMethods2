###### **Use Case: 8 Total Population Reports**

**--------------------------------------------------**

**CHARACTERISTIC INFORMATION**

**Goal in Context:** Display the information of the total population of continent, region, country, district, city or globally.

**Scope:** Population Information System

**Level:** Primary Task

**Preconditions:** Access to the system and the system contains a specify database.

**Success End Condition:** The organization user successfully receives the information.

**Failed End Condition:** The report fails to generate and display an error message.

**Primary Actor:** Population Data Analyst (Organisation User)

**Trigger:** User requests the “Total Population Reports”

**----------------------------------------**

**MAIN SUCCESS SCENARIO**

    1. User selects the “Total Population Reports” option.

    2. System shows for input (continent, region, country, district, city or global filter).

    3. User specifies the desired filter.

    4. System retrieves total population from the database matching the specified filter

    5. System display the information.

**----------------------**

**EXTENSIONS**

    • 3a. If no filter is selected:

        ◦ 3a1. The report defaults to the global filter.

    • 4a. If the database is unavailable or contains an error:

        ◦ 4a1. System displays an error message.

**--------------------**

**SUB-VARIATIONS**

3\. The user may select one of the following:

    • Global (default)

    • Continent

    • Region

    • Country

    • District

    • City

**----------------------**

**RELATED INFORMATION**

**Priority:** Medium

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



