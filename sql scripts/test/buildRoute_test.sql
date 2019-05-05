insert into user_entity(user_id, email, login, password, role, active) values (1, "admin@mail.ru", "admin", "123456", "ADMIN", 1);
insert into user_entity(user_id, email, login, password, role, active) values (2, "admin@mail.ru", "tourist", "123456", "TOURIST", 1);
insert into user_entity(user_id, email, login, password, role, active) values (3, "admin@mail.ru", "captain", "123456", "CREWMAN", 1);

insert into point_group_entity(group_id, koef, left_longitude, top_latitude, right_longitude, bottom_latitude) values  (1, 1, 0, 0, 100, 100);
insert into point_group_entity(group_id, koef, left_longitude, top_latitude, right_longitude, bottom_latitude) values (2, 1, 100, 100, 200, 200);

insert into point_entity(point_id, is_active, latitude, longitude, creator_id, group_id, updater_id) values (1, 1, 10, 50, 1, 1, 1);
insert into point_entity(point_id, is_active, latitude, longitude, creator_id, group_id, updater_id) values (2, 1, 20, 50, 1, 1, 1);
insert into point_entity(point_id, is_active, latitude, longitude, creator_id, group_id, updater_id) values (3, 0, 37, 50, 1, 1, 1);
insert into point_entity(point_id, is_active, latitude, longitude, creator_id, group_id, updater_id) values (4, 1, 46, 50, 1, 1, 1);
insert into point_entity(point_id, is_active, latitude, longitude, creator_id, group_id, updater_id) values (5, 1, 56, 50, 1, 1, 1);
insert into point_entity(point_id, is_active, latitude, longitude, creator_id, group_id, updater_id) values (6, 1, 66, 50, 1, 1, 1);
insert into point_entity(point_id, is_active, latitude, longitude, creator_id, group_id, updater_id) values (7, 1, 76, 50, 1, 1, 1);


insert into dump_entity(dump_id, size, type, status, point_id) values (1, 10, "ORGANIC", "UNREMOVED", 1);
insert into dump_entity(dump_id, size, type, status, point_id) values (2, 20, "MIXED", "UNREMOVED", 2);
insert into dump_entity(dump_id, size, type, status, point_id) values (3, 11, "ORGANIC", "UNREMOVED", 3);
insert into dump_entity(dump_id, size, type, status, point_id) values (4, 11, "ORGANIC", "UNREMOVED", 4);
insert into dump_entity(dump_id, size, type, status, point_id) values (5, 50, "ORGANIC", "UNREMOVED", 5);
insert into dump_entity(dump_id, size, type, status, point_id) values (6, 60, "ORGANIC", "UNREMOVED", 6);
insert into dump_entity(dump_id, size, type, status, point_id) values (7, 70, "ORGANIC", "UNREMOVED", 7);

insert into vessel_entity(vessel_id, capacity, current_load, latitude, longitude, name) values (1, 100, 20, 9, 50, "Vessel1");
insert into vessel_entity(vessel_id, capacity, current_load, latitude, longitude, name) values (2, 100, 10, 49, 50, "Vessel2");
insert into vessel_entity(vessel_id, capacity, current_load, latitude, longitude, name) values (3, 100, 10, 89, 50, "Vessel3");
insert into vessel_entity(vessel_id, capacity, current_load, latitude, longitude, name) values (4, 100, 10, 90, 50, "Strange Vessel");

insert into route_entity(route_id, status, vessel_id) values (1, "IN_PROGRESS", 1);
insert into route_entity(route_id, status, vessel_id) values (2, "IN_PROGRESS", 2);
insert into route_entity(route_id, status, vessel_id) values (3, "IN_PROGRESS", 4);
insert into route_entity(route_id, status, vessel_id) values (4, "IN_PROGRESS", 4);

insert into route_point_entity(route_point_id, number, status, point_id, route_id) values (1, 1, "AWAITING", 1, 1);
insert into route_point_entity(route_point_id, number, status, point_id, route_id) values (2, 1, "COMPLETED", 3, 2);
insert into route_point_entity(route_point_id, number, status, point_id, route_id) values (3, 2, "AWAITING", 4, 2);



