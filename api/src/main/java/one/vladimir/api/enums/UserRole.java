package one.vladimir.api.enums;

/**
 * possible roles of any user in the system
 */
public enum UserRole {
    /**
     * User could add new Dumps to the system and get information about created Dumps
     */
    TOURIST,

    /**
     * Could get information about any Route and Point in system
     */
    ADMIN,

    /**
     * User could manage Vessel coordinates and data connected with current Route
     * User could add new Dumps to the system and get information about created Dumps
     */
    CREWMAN
}
