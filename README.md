# Tapas Engineering Task Force 👀

This documents defines common Tapas APIs.

## Interaction Scheme

⬅️➡️ denotes a JSON Object and its direction of sending.

### Auction

| AuctionHouse (offering)        |                                      | AuctionHouse (bidding)    |
| ------------------------------ | ------------------------------------ | ------------------------- |
|                                | Auction ➡️ (over AuctionStartedEvent) | HTTP POST @ `/auction`    |
| HTTP POST @ `/bid/<AuctionId>` | ⬅️ Bid                                |                           |
|                                | Auction (over AuctionWonEvent) ➡️     | HTTP POST @ `/taskwinner` |

### Task Execution

| TaskList (offering)            |                                          | AuctionHouse (bidding) |
| ------------------------------ | ---------------------------------------- | ---------------------- |
| HTTP POST @ `/tasks/<TaskID>`  | Task ➡️ (response)                        |                        |
| HTTP PATCH @ `/tasks/<TaskID>` | ⬅️ TaskPatch (State.ASSIGNED)             |                        |
|                                |                                          | (Executes task)        |
| HTTP PATCH @ `/tasks/<TaskID>` | ⬅️ TaskPatch (State.EXECUTED, OutputData) |                        |

## Uniform HTTP API

### Auction House

**URL**: `tapas-auction-house.<publicIP>.nio.io`

**Port**: 8085

#### HTTP POST `/auction`

**Media-Type** `application-json`

**Body**:

```json
{
  "auctionId":"",
  "auctionHouseUri":"",
  "taskUri":"",
  "taskType":"",
  "deadline" 42
}
```

**Response Codes**

- `200` All good in the hood
- ...

*No response body*

#### HTTP POST `/bid/<TaskID>` 

**Media-Type** `application-json`

**Body**:

```json
{
  "auctionId":"",
  "bidderName":"",
  "bidderAuctionHouseUri":"",
  "bidderTaskListUri":"",
}
```

**Response Codes**

- `200` All good in the hood
- ...

*No response body*

#### HTTP POST `/taskwinner` 

**Media-Type** `application-json`

**Body** (same as Bid):

```json
{
  "auctionId":"",
  "bidderName":"",
  "bidderAuctionHouseUri":"",
  "bidderTaskListUri":"",
}
```

**Response Codes**

- `200` All good in the hood
- ...

*No response body*