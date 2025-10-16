###### **Use Case: 1 Generate Country Report**

**--------------------------------------------------**

**CHARACTERISTIC INFORMATION**

**Goal in Context:** Generate a report of all countries of the specify continent, region or globally order by population (Largest by Smallest) that contains code, name, continent, region, population and capital.

**Scope:** Population Information System

**Level:** Primary Task

**Preconditions:** Access to the system and the system contains a specify database

**Success End Condition:** The organization, WorldPop, user successfully receives the report.

**Failed End Condition:** The report fails to generate and display an error message.

**Primary Actor:** Population Data Analyst (Organisation User)

**Trigger:** User requests the “Country report”

**----------------------------------------**

**MAIN SUCCESS SCENARIO**

    1. User selects the “Country Report” option.

    2. System shows for input (continent, region, or global filter).

    3. User specifies the desired filter.

    4. System retrieves all countries from the database matching the specified filter by population (largest to smallest)

    5. System display the report.

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





###### **Use Case: 3 Generate City Report**

**--------------------------------------------------**

**CHARACTERISTIC INFORMATION**

**Goal in Context:** Generate a report of all cities of the specify continent, region, country, district or globally order by population (Largest by Smallest) that contains name, country, district, population.

**Scope:** Population Information System

**Level:** Primary Task

**Preconditions:** Access to the system and the system contains a specify database

**Success End Condition:** The organization, WorldPop, user successfully receives the report.

**Failed End Condition:** The report fails to generate and display an error message.

**Primary Actor:** Population Data Analyst (Organisation User)

**Trigger:** User requests the “City report”

**----------------------------------------**

**MAIN SUCCESS SCENARIO**

    1. User selects the “City Report” option.

    2. System shows for input (continent, region, country, district or global filter).

    3. User specifies the desired filter.

    4. System retrieves all cities from the database matching the specified filter by population (largest to smallest)

    5. System display the report.

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





###### **Use Case: 4 Generate Top City Report**

**--------------------------------------------------**

**CHARACTERISTIC INFORMATION**

**Goal in Context:** Generate a report of top x numbers, specify by the user, of the most populated cities on a specify continent, region, country, district or globally that contains name, country, district, population

**Scope:** Population Information System

**Level:** Primary Task

**Preconditions:** Access to the system and the system contains a specify database

Success End Condition: The organization, WorldPop, user successfully receives the report.

Failed End Condition: The report fails to generate and display an error message.

**Primary Actor:** Population Data Analyst (Organisation User)

**Trigger:** User requests the “Top City report”

**----------------------------------------**

**MAIN SUCCESS SCENARIO**

    1. User selects the “Top City Report” option.

    2. System ask for the number of top populated cities

    3. User input the number

    4. System prompts for input (continent, region, country, district or global filter).

    5. User specifies the desired filter.

    6. System retrieves top cities from the database matching the specified number of result and the filter selected

    7. System display the report.

**----------------------**

**EXTENSIONS**

    • 3a. If no number is input:

        ◦ 3a1. The system defaults to Top 5 cities.

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

    • Country

    • District

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





###### **Use Case: 5 Generate Capital Report**

**--------------------------------------------------**

**CHARACTERISTIC INFORMATION**

**Goal in Context:** Generate a report of all capitals of the specify continent, region or globally order by population (Largest by Smallest) that contains name, country and population

**Scope:** Population Information System

**Level:** Primary Task

**Preconditions:** Access to the system and the system contains a specify database

**Success End Condition:** The organization, WorldPop, user successfully receives the report.

**Failed End Condition:** The report fails to generate and display an error message.

**Primary Actor:** Population Data Analyst (Organisation User)

**Trigger:** User requests the “Capital report”

**----------------------------------------**

**MAIN SUCCESS SCENARIO**

    1. User selects the “Capital Report” option.

    2. System shows for input (continent, region or global filter).

    3. User specifies the desired filter.

    4. System retrieves all capitals from the database matching the specified filter by population (largest to smallest)

    5. System display the report.

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





###### **Use Case: 6 Generate Top Capital Report**

**--------------------------------------------------**

**CHARACTERISTIC INFORMATION**

**Goal in Context:** Generate a report of top x numbers, specify by the user, of the most populated capitals on a specify continent, region or globally that contains name, country and population

**Scope:** Population Information System

**Level:** Primary Task

**Preconditions:** Access to the system and the system contains a specify database

**Success End Condition:** The organization, WorldPop, user successfully receives the report.

**Failed End Condition:** The report fails to generate and display an error message.

**Primary Actor:** Population Data Analyst (Organisation User)

**Trigger:** User requests the “Top Capital report”

**----------------------------------------**

**MAIN SUCCESS SCENARIO**

    1. User selects the “Top Capital Report” option.

    2. System ask for the number of top populated capitals

    3. User input the number

    4. System prompts for input (continent, region or global filter).

    5. User specifies the desired filter.

    6. System retrieves top capitals from the database matching the specified number of result and the filter selected

    7. System display the report.

**----------------------**

**EXTENSIONS**

    • 3a. If no number is input:

        ◦ 3a1. The system defaults to Top 5 capitals.

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





###### **Use Case: 7 City Distribution Reports**

**--------------------------------------------------**

**CHARACTERISTIC INFORMATION**

**Goal in Context:** Generate a report of the population of the specify continent, region or country that contains name of the specify continent, region or country, population on % that lives in cities and population on % that doesn’t and the total population.

**Scope:** Population Information System

**Level:** Primary Task

**Preconditions:** Access to the system and the system contains a specify database

**Success End Condition:** The organization, WorldPop, user successfully receives the report.

**Failed End Condition:** The report fails to generate and display an error message.

**Primary Actor:** Population Data Analyst (Organisation User)

**Trigger:** User requests the “City Distribution Reports”

**----------------------------------------**

**MAIN SUCCESS SCENARIO**

    1. User selects the “City Distribution Reports” option.

    2. System shows for input (continent, region or country filter).

    3. User specifies the desired filter.

    4. System retrieves all population from the database matching the specified filter on % that lives in cities and population on % that doesn’t and the total population

    5. System display the report.

**----------------------**

**EXTENSIONS**

    • 3a. If no filter is selected:

        ◦ 3a1. The report defaults to the country filter.

    • 4a. If the database is unavailable or contains an error:

        ◦ 4a1. System displays an error message.

**--------------------**

**SUB-VARIATIONS**

3\. The user may select one of the following:

    • Country (default)

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





###### **Use Case: 9 Language Report**

**--------------------------------------------------**

**CHARACTERISTIC INFORMATION**

**Goal in Context:** Display the information of the total population by the languages Chinese, English, Hindi, Spanish and Arabic on % of the world population on order of the greatest to smallest number spoke.

**Scope:** Population Information System

**Level:** Primary Task

**Preconditions:** Access to the system and the system contains a specify database.

**Success End Condition:** The organization user successfully receives the information.

**Failed End Condition:** The report fails to generate and display an error message.

**Primary Actor:** Population Data Analyst (Organisation User)

**Trigger:** User requests the “Language Report”

**----------------------------------------**

**MAIN SUCCESS SCENARIO**

    1. User selects the “Language Report” option.

    2. System retrieves the % population that speak each language

    3. System display the information.

**----------------------**

**EXTENSIONS**

    • 3a. If the database is unavailable or contains an error:

        ◦ 3a1. System displays an error message.

**--------------------**

**SUB-VARIATIONS**

    • None

**----------------------**

**RELATED INFORMATION**

**Priority:** Low

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

