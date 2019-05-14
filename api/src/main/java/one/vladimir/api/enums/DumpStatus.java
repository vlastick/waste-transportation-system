package one.vladimir.api.enums;

/**
 * Possible statuses of Dump
 */
public enum DumpStatus {
    /**
     * Dump was added to system.
     * Information about it is not proven
     */
    UNCONFIRMED,

    /**
     * Information about Dump is proven.
     * Dump is not removed yet.
     */
    UNREMOVED,

    /**
     * Dump was removed
     */
    REMOVED
}
