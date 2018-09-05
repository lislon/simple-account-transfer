Simple Account Transfer
=======================

Demo project to demonstrate simple API without using `Spring` framework on `JDK8`.

API features: 
 - List/Adding of existing accounts
 - Currency transferring between them

The project was build with
 - Jersey (REST Framework)
 - hk2 (Dependency Injection)
 - junit/mockito (unit testing)
 - google thread weaver (unit testing - multi-threading)
 - jersey-test-framework-provider-inmemory (integration testing)
 - swagger (automatic documentation generation)

Building
========

```batch
gradle build
```

Running
=======
```batch
gradle run
```

The app will run on [http://localhost:8080/](http://localhost:8080/docs)

Trying it out
=============

The API has autogenerated swagger documentation.
When program is running, navigate to [http://localhost:8080/docs](http://localhost:8080/docs),
then select API method and click `Try it out` buttons.

API endpoints
=============

### /api/accounts/{id}
---
##### ***GET***
**Summary:** Show details for single account by its id

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path | Account identifier | Yes | integer |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | Account details with identifier |
| 404 | Account cannot be found |

### /api/accounts
---
##### ***GET***
**Summary:** Lists all accounts in system

**Description:** Show all account with their balances

**Responses**

| Code | Description |
| ---- | ----------- |
| default | default response |

##### ***POST***
**Summary:** Adds a new account to system

**Responses**

| Code | Description |
| ---- | ----------- |
| 201 | Account details with assigned identifier |

### /api/transactions
---
##### ***POST***
**Summary:** Transfer fund from one account to another

**Description:** Withdraw specified amount of money from first account and deposit it to second account

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | Transaction details with status |
| 404 | Sender/Receiver account not exists |
| 409 | Insufficient funds |
