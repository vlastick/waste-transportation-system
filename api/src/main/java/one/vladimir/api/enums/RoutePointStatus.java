package one.vladimir.api.enums;

/**
 * Possible statuses of RoutePoint
 */
public enum RoutePointStatus {
    /**
     * RoutePoint is in Route and waits the Vessel.
     * Point on RoutePoint is locked for other Vessels, in case it is not a Base.
     */
    AWAITING,

    /**
     * Vessel is currently on RoutePoint.
     * Point on RoutePoint is locked for other Vessels, in case it is not a Base.
     */
    IN_PROGRESS,

    /**
     * RoutePoint was canceled and not visited by vessel.
     * Point on RoutePoint is unlocked for other Vessels.
     */
    CANCELED,

    /**
     * RoutePoint was visited by Vessel
     * Point on RoutePoint becomes inactive, in case it is not a Base.
     */
    COMPLETED
}
