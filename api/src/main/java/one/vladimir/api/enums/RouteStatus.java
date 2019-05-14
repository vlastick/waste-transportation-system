package one.vladimir.api.enums;

/**
 * possible statuses of Route
 */
public enum RouteStatus {
    /**
     * Route is currently managed by Vessel.
     * Every Vessel at one moment could have only one Route, that has status IN_PROGRESS
     */
    IN_PROGRESS,

    /**
     * Route was canceled by Vessel.
     */
    CANCELLED,

    /**
     * Route is completed by Vessel.
     */
    COMPLETED
}
