package ch.unisg.tapasstandards.formats;

/**
 * Used to expose a representation of the state of an auction through an interface. This class is
 * only meant as a starting point when defining a uniform HTTP API for the Auction House: feel free
 * to modify this class as you see fit!
 */
public abstract class AbstractAuctionJsonRepresentation {
    public static final String MEDIA_TYPE = "application/json";

    public String auctionId;
    public String auctionHouseUri;
    public String taskUri;
    public String taskType;
    public String deadline;
}
