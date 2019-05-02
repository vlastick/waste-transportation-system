insert into user_entity(user_id, email, login, password, role) values (1, "admin@mail.ru", "admin", "123456", "ADMIN");

insert into point_group_entity(group_id, koef, left_longitude, top_latitude, right_longitude, bottom_latitude) values  (1, 1, 0, 0, 100, 100);
insert into point_group_entity(group_id, koef, left_longitude, top_latitude, right_longitude, bottom_latitude) values (2, 1, 100, 100, 200, 200);

insert into point_entity(point_id, is_active, latitude, longitude, creator_id, group_id, updater_id) values (1, 1, 50, 50, 1, 1, 1);
insert into point_entity(point_id, is_active, latitude, longitude, creator_id, group_id, updater_id) values (2, 1, 77, 50, 1, 1, 1);
insert into point_entity(point_id, is_active, latitude, longitude, creator_id, group_id, updater_id) values (3, 1, 66, 50, 1, 1, 1);
insert into point_entity(point_id, is_active, latitude, longitude, creator_id, group_id, updater_id) values (4, 1, 44, 50, 1, 1, 1);

insert into dump_entity(dump_id, size, type, point_id) values (1, 10, "ORGANIC", 1);
insert into dump_entity(dump_id, size, type, point_id) values (2, 20, "MIXED", 2);
insert into dump_entity(dump_id, size, type, point_id) values (3, 15, "ORGANIC", 3);
insert into dump_entity(dump_id, size, type, point_id) values (4, 5, "ORGANIC", 4);

insert into vessel_entity(vessel_id, capacity, current_load, latitude, longitude, name) values (1, 100, 0, 10, 10, "Vessel1");
insert into vessel_entity(vessel_id, capacity, current_load, latitude, longitude, name) values (2, 100, 0, 199, 199, "Vessel2");

