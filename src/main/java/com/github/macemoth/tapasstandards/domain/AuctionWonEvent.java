package com.github.macemoth.tapasstandards.domain;

import lombok.Getter;

/**
 * A domain event that models an auction was won.
 */
public class AuctionWonEvent {
    // The winning bid
    @Getter
    private Bid winningBid;

    public AuctionWonEvent(Bid winningBid) {
        this.winningBid = winningBid;
    }
}
