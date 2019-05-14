package one.vladimir.api;

import one.vladimir.api.enums.RoutePointStatus;
import one.vladimir.api.pojo.Route;
import one.vladimir.api.pojo.RouteFilter;
import java.util.List;

public interface RouteService {


    /**
     * Finds all Routes that are fitting the RouteFilter.
     * If any field in RouteFilter is null, corresponding condition will not be applied.
     * In case all RouteFilter fields are null, will return all Routes.
     * @param routeFilter RouteFilter object, that contains information about needed query conditions.
     * @return List of Routes, that fit incoming RouteFilter. Could be empty.
     */
    List<Route> getRoutesByFilter(RouteFilter routeFilter);


    /**
     * Adds RoutePoints to current Route of Vessel based on relevant information about Dumps and Bases.
     * If there is no current Route, creates it.
     * If current Route already contains needed amount of active RoutePoints, does nothing and returns current Route.
     * @param vesselID id of Vessel, that needs to add new RoutePoints
     * @return Route object, that contains relevant information.
     */
    Route buildRoute(Integer vesselID);


    /**
     * Updates status of RoutePoint.
     * Requirements:
     *  current Route of Vessel should contain given RoutePoint;
     *  current Status of RoutePoint should be IN_PROGRESS or AWAITING;
     *  status param should be CANCELED or COMPLETED.
     * @param routePointId id of RoutePoint, that wil be updated.
     * @param vesselId id of Vessel, that want to update the RoutePoint.
     * @param status wanted status. Could be only CANCELED or COMPLETED.
     * @return information message.
     */
    String updateRoutePointStatus(Integer routePointId, Integer vesselId, RoutePointStatus status);


    /**
     * Adds Base to current Route as Last RoutePoint.
     * All other RoutePoints in current Route should have status COMPLETED or CANCELED.
     * @param vesselId id of vessel, that want to close Route
     * @return Route object, that contains relevant information.
     */
    Route finishRoute(Integer vesselId);

    String addRoute(Route route);

    String updateRoute(Route route);

    Route getRoute(Integer id);
}
