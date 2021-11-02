# Tapas Engineering Task Force 👀

This documents defines common Tapas APIs.

## Interaction Scheme

⬅️➡️ denotes a JSON Object and its direction of sending.

### Auction

| AuctionHouse (offering)        |                                      | AuctionHouse (bidding)    |
| ------------------------------ | ------------------------------------ | ------------------------- |
|                                | Auction ➡️ (over AuctionStartedEvent via Message Queue*) | HTTP POST @ `/auction`    |
| HTTP POST @ `/bid/<AuctionId>` | ⬅️ Bid                                |                           |
|                                | Task ➡️                              | HTTP POST @ `/taskwinner` |

\* The message queue is offered either by MQTT or PubSubHub.

### Task Execution

| TaskList (offering)               |                                          | Bidding party (undefined endpoint)|
| --------------------------------- | ---------------------------------------- | ---------------------- |
| HTTP PATCH @ `/tasks/<task-UUID>` | ⬅️ TaskPatch (State.ASSIGNED)             |                        |
|                                   |                                          | (Executes task)        |
| HTTP PATCH @ `/tasks/`<task-UUID> | ⬅️ TaskPatch (State.EXECUTED, OutputData) |                        |

## Uniform HTTP API

### Auction House

**URL**: `tapas-auction-house.<publicIP>.nio.io`

**Port**: 8085

#### HTTP POST `/auction`

**Media-Type** `application/auction+json`

**Body**:

```json
{
  "auctionId": "1",
  "auctionHouseUri": "https://127.0.0.1:8085/",
  "taskUri":"http://example.org/tasks/cef2fa9d-367b-4e7f-bf06-3b1fea35f354",
  "taskType":"COMPUTATION",
  "deadline":"2021-12-24 12:00:00"
}

```
- Note that `deadline` is produced by using Java's default `toString()` method of the `java.sql.Timestamp` class
- The `taskUri` should not only contain the server, but also the `/tasks/<task-UUID>`, s.t. the original Task can be fetched

**Response Codes**

_No codes defined as messages are sent by queue_

*No response body*

#### HTTP POST `/bid/<auctionId>` 

**Media-Type** `application/bid+json`

**Body**:

```json
{
  "auctionId":"1",
  "bidderName":"Group1",
  "bidderAuctionHouseUri":"http://example.com/",
  "bidderTaskListUri":"http://example.com/tasks/"
}
```

**Response Codes**

- `204` (No content) Bid was received and accepted
- `404` (Not found) There never was an auction with that `<auctionID>`
- `410` (Gone) The Auction that was requested has already expired 

*No response body*

#### HTTP POST `/taskwinner` 

**Media-Type** `application/task+json`

**Body** (Identical to Task):

```json
{
  "taskId":"cef2fa9d-367b-4e7f-bf06-3b1fea35f354",
  "taskName":"task1",
  "taskType":"COMPUTATION",
  "taskStatus":"ASSIGNED",
  "originalTaskUri":"http://example.org/tasks/cef2fa9d-367b-4e7f-bf06-3b1fea35f354",
  "serviceProvider":"tapas-group1",
  "inputData":"1+1",
  "outputData":"2"
}
```

**Response Codes**

- `202` (Accepted) The won task is accepted and can be done
- `406` (Not Acceptable) The winning Auction House cannot execute the won task anymore

*No response body*
    
#### HTTP POST `/tasks/<taskId>` 

**Media-Type** `application/json-patch+json`

**Body** (same as Task):
_See `tapas-tasks` documentation_

**Response Codes**

- `200` (Accepted) The patch was applied to the Task
- `404` (Not Found) The Task requested to patch wasn't found
- `406` (Not acceptable) An invalid patch request was made

**Response Body**
For debugging purposes, the patched Task is returned.
    
## Task Types (with in- and outputs)

### `BIGROBOT`

(Cherrybot or PretendABot)
    
**Input**: _None_
    
**Output**: _None_

### `SMALLROBOT`

(Leubot)
    
**Input**: _None_
    
**Output**: _None_
    
### `COMPUTATION`

**Input**: `<OPERAND> <OPERATOR> <OPERAND>`
    
**Output**: Integer result of computation
    
Valid Operands: All integers
    
Valid Operators: `+`, `-`, `*`
    
### `RANDOMTEXT`

**Input**: _None_
    
**Output**: Randomly generated sentence
