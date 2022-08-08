# SuperFarm-QA
Platform integration tests as well as other QA automation

## 1. Quick start

---

This tutorial will help run a API tests for your environment.

Clone project and open project folder

```bash
mvn clean install
```

## Run tests:

There are three environments (env) where tests can be started:
#### develop
#### test
#### staging

Set env variable to specify on which environment tests should be executed

### Run some ui tests:

```bash
mvn clean test -Pui -DsuiteXmlFile=${testSuite} -Dhost=${HOST} -Drp.enable=false
```
where testSuite is a group of tests - available values: api, buy, listing

## 2. Run tests on Github

---
To start the tests manually, use the action defined in `.github/workflows` .

