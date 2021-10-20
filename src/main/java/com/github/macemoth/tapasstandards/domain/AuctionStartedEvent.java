package com.github.macemoth.tapasstandards.domain;

import lombok.Getter;

/**
 * A domain event that models an auction has started.
 */
public class AuctionStartedEvent {
    @Getter
    private Auction auction;

    public AuctionStartedEvent(Auction auction) {
        this.auction = auction;
    }
}
