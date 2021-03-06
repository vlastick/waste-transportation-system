insert into user_entity(user_id, email, login, password, role, active) values (1, "admin@mail.ru", "manager", "1", "ADMIN", 1);
insert into user_entity(user_id, email, login, password, role, active) values (2, "admin@mail.ru", "tourist", "1", "TOURIST", 1);
insert into user_entity(user_id, email, login, password, role, active) values (3, "admin@mail.ru", "captain", "1", "CREWMAN", 1);
insert into user_entity(user_id, email, login, password, role, active) values (4, "admin@mail.ru", "captain2", "1", "CREWMAN", 1);
insert into user_entity(user_id, email, login, password, role, active) values (5, "admin@mail.ru", "tourist2", "1", "TOURIST", 1);

insert into point_group_entity(group_id, koef, left_longitude, top_latitude, right_longitude, bottom_latitude) values  (1, 1, 29.285654415485855, 61.77028870692254, 33.5321013662014, 59.67278387738081);

-- insert into point_entity(point_id, is_active, latitude, longitude, creator_id, group_id, updater_id) values (1, 1, 60.9333435997638, 32.684439513123465, 1, 1, 1);
insert into point_entity(point_id, is_active, latitude, longitude, creator_id, group_id, updater_id) values (1, 1, 61.36563880588071, 31.540352350180665, 1, 1, 1);
insert into point_entity(point_id, is_active, latitude, longitude, creator_id, group_id, updater_id) values (2, 1, 60.973940033771136, 30.3107552914428, 1, 1, 1);

insert into base_entity(base_id, point_id) values (1, 1);
insert into base_entity(base_id, point_id) values (2, 2);
-- insert into base_entity(base_id, point_id) values (3, 3);

insert into vessel_entity(vessel_id, capacity, current_load, latitude, longitude, name) values (1, 100, 0, 61.12635995135459, 30.63482229260782, "Vessel1");
insert into vessel_entity(vessel_id, capacity, current_load, latitude, longitude, name) values (2, 50, 0, 60.402015335909155, 32.00700133170193, "Vessel2");

insert into crewman_entity(crewman_id, user_id, vessel_id) values (1, 3, 1);
insert into crewman_entity(crewman_id, user_id, vessel_id) values (2, 4, 2);
