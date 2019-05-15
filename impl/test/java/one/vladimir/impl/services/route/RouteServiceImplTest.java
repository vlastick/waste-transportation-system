package one.vladimir.impl.services.route;

import one.vladimir.api.DatabaseService;
import one.vladimir.api.PointService;
import one.vladimir.api.RouteService;
import one.vladimir.api.TransportService;
import one.vladimir.api.enums.RoutePointStatus;
import one.vladimir.api.pojo.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceImplTest {

    private RouteService service;

    @Mock
    private TransportService transportService;

    @Mock
    private PointService pointService;

    @Mock
    private DatabaseService db;

    @Before
    public void setUp() {
        List<RoutePoint> routePoints = new ArrayList<>();
        RoutePoint rp = new RoutePoint();
        rp.setStatus(RoutePointStatus.AWAITING);

        Vessel vessel = new Vessel();
        vessel.setLatitude(10.0);
        vessel.setLongitude(10.0);
        vessel.setCapacity(100);
        vessel.setCurrentLoad(0);

        List<Dump> availableDumps = new ArrayList<>();
        List<Dump> availableDumps2 = new ArrayList<>();
        Dump dump = new Dump();
        dump.setLatitude(50.0);
        dump.setLongitude(50.0);
        dump.setSize(10);
        availableDumps.add(dump);

        when(db.addRoute(any(Route.class), any(Vessel.class)))
                .thenReturn(1);

        when(transportService.getVessel(any(Integer.class)))
                .thenReturn(vessel);

        when(db.getGroupByCoordinates(any(Double.class), any(Double.class)))
                .thenReturn(new Group());

        when(pointService.getDumpsByFilter(any(DumpFilter.class)))
                .thenReturn(availableDumps);

        when(db.addRoutePoint(any(RoutePoint.class), any(Route.class)))
                .thenReturn(11);

        service = mock(RouteService.class);
        service = new RouteServiceImpl();
        ReflectionTestUtils.setField(service, "db", db);
        ReflectionTestUtils.setField(service, "transportService", transportService);
        ReflectionTestUtils.setField(service, "pointService", pointService);

        when(service.getRoutesByFilter(any(RouteFilter.class)))
                .thenReturn(new ArrayList<>());
    }


    @Test
    public void buildRoute() {
        Route currRoute = service.buildRoute(1);
        assertEquals(1, currRoute.getRoutePoints().size());
    }


//    @Test
//    public void routePointWithPointIdAlreadyExists() {
//        assertEquals(false, service.routePointWithPointIdAlreadyExists(1));
//    }
}