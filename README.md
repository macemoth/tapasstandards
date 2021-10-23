# Tapas Engineering Task Force 👀

This documents defines common Tapas APIs.

## Installation

To add this library to your project, insert following to your `pom.xml`:

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml
    <dependency>
        <groupId>com.github.macemoth</groupId>
        <artifactId>tapasstandards</artifactId>
        <version>v1.0</version>
    </dependency>
```

## Interaction Scheme

⬅️➡️ denotes a JSON Object and its direction of sending.

### Auction

| AuctionHouse (offering)        |                                      | AuctionHouse (bidding)    |
| ------------------------------ | ------------------------------------ | ------------------------- |
|                                | Auction ➡️ (over AuctionStartedEvent) | HTTP POST @ `/auction`    |
| HTTP POST @ `/bid/<AuctionId>` | ⬅️ Bid                                |                           |
|                                | Auction (over AuctionWonEvent) ➡️     | HTTP POST @ `/taskwinner` |

### Task Execution

| TaskList (offering)               |                                          | AuctionHouse (bidding) |
| --------------------------------- | ---------------------------------------- | ---------------------- |
| HTTP GET @ `/tasks/<task-UUID>`   | Task ➡️                                   |                        |
| HTTP PATCH @ `/tasks/<task-UUID>` | ⬅️ TaskPatch (State.ASSIGNED)             |                        |
|                                   |                                          | (Executes task)        |
| HTTP PATCH @ `/tasks/`<task-UUID> | ⬅️ TaskPatch (State.EXECUTED, OutputData) |                        |

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

#### HTTP POST `/bid/<auctionId>` 

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