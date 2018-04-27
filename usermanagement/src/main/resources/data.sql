insert into user (id, username, password, group_fk) values ('9999999', 'Admin', 'YWRtaW4=', '9999999');

insert into usergroup (id, groupname, maycreateusers, maycreategroups) values ('9999999', 'Administrators', 1, 1);
insert into usergroup (id, groupname, maycreateusers, maycreategroups) values ('9999998', 'Default', 0, 0);