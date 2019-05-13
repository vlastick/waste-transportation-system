package one.vladimir.api;

import one.vladimir.api.pojo.*;

import java.util.List;

public interface PointService {


    /**
     * Inserts Dump associated with User into database.
     * Doesn't insert User.
     * @param dump Dump object that contains information about dump. Field id is not mandatory, it is
     *             generated automatically.
     * @param creator User object that contains information about user. Only id field is mandatory.
     * @return information message
     */
    String addDump(Dump dump, User creator);


    /**
     * Inserts Base associated with User into database.
     * Doesn't insert User.
     * @param base Base object that contains information about dump. Field id is not mandatory, it is
     *             generated automatically.
     * @param creator User object that contains information about user. Only id field is mandatory.
     * @return information message
     */
    String addBase(Base base, User creator);


    /**
     * Finds all Dumps that are fitting the DumpFilter.
     * If any field in DumpFilter is null, corresponding condition will not be applied.
     * In case all DumpFilter fields are null, will return all Dumps
     * @param dumpFilter DumpFilter object, that contains information about needed query conditions.
     * @return List of Dumps, that fit incoming DumpFilter. Could be empty.
     */
    List <Dump> getDumpsByFilter(DumpFilter dumpFilter);


    /**
     * Finds all Bases that are fitting the BaseFilter.
     * If any field in BaseFilter is null, corresponding condition will not be applied.
     * In case all BaseFilter fields are null, will return all Bases.
     * @param baseFilter BaseFilter object, that contains information about needed query conditions.
     * @return List of Bases, that fit incoming BaseFilter. Could be empty.
     */
    List <Base> getBasesByFilter(BaseFilter baseFilter);


    /**
     * Updates Dump and associate it with User
     * @param dump Dump object that contains info about Dump.
     * @param updater User object that contains information about user. Only id field is mandatory.
     * @return information message
     */
    String updateDump(Dump dump, User updater);


    /**
     * Updates Base and associate it with User
     * @param base Base Objects that contains info about Base.
     * @param updater User object that contains information about user. Only id field is mandatory.
     * @return information message
     */
    String updateBase(Base base, User updater);

    Dump getDump(Integer id);

    Base getBase(Integer id);

    String testGeo(String request);

    String addGroup(Group group);

    String updateGroup(Group group);

    Group getGroup(Integer id);

}
