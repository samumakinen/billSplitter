# Bill Splitter App
This application helps users when purchasing something for a group of people. One person can pay at a store and then use Bill Splitter to calculate the amount each participant owes them. Users can also save bills to history to keep track of them.
## Documentation

[Architecture description](documentation/architecture.md) TBA

[Manual](documentation/manual.md) TBA

[Software requirement specification (SRS)](documentation/srs.md)

[Testing documentation](documentation/testing.md) TBA

[Timesheet](documentation/timesheet.md)

## Command line actions
**All commans must be executed in the BillSplitter project folder!**
### General
Compile and run project:
```
mvn compile exec:java -Dexec.mainClass=billsplitter.Main
```
Generate a runnable .jar file:
```
mvn package
```
### Tests
Run tests:
```
mvn test
```
Generate and open jacoco test report:
```
mvn jacoco:report && open target/site/jacoco/index.html
```
