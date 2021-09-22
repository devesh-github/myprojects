1. prepare test data - done
2. Read from a single file - done
3. Validation layer - done
4. policy interface - done
5. Rule implementation
6. Writing output
7. Code execution - done
8. Test Cases - done
9. Read Me - done

Instructions
--------------------

* **Prerequisites:**
    * Install IntelliJ, Maven plugin, and necessary libraries.

* Run the project

    * com/dev/trns/driver/TrnsAomalyDriver.java
    * Right click and run the main method
        * Project can be inhanced to take custom i/p from a source file

* Run the test cases

    * Go to test -> java
    * Right click and choose option to Run testcases with coverage

* Project description

    * driver -> consist of driver class and PolicyExecution interface which can attach or detach policies
    * constant -> consist of constant variable
    * services -> consist of policy interfaces and different policy implementations
    * utils -> consist of different utilities required for the project
    * Policies can be added or deleted using interface implementation, the policy to be chosen is configured in property file