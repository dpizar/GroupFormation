CREATE TABLE user
(userName varchar(64), version INT, password varchar(64), firstName varchar(64), lastName varchar(64), groupId int, userType int, 
primary key(userName), FOREIGN KEY (groupId) REFERENCES groupp(groupId));
insert into user(userName, version, password, firstName, lastName,groupId,userType) values('user1', 1, 'user1', 'Seho', 'Kim',0,0);
insert into user(userName, version, password, firstName, lastName,groupId,userType) values('user2', 1, 'user2', 'James', 'Bond',0,1);
insert into user(userName, version, password, firstName, lastName,groupId,userType) values('user3', 1, 'user3', 'Diego', 'Pizarro',0,1);
insert into user(userName, version, password, firstName, lastName,groupId,userType) values('user4', 1, 'user4', 'Fang', 'Yuan',1,1);


CREATE TABLE groupp(groupId INT NOT NULL AUTO_INCREMENT, version INT, groupName varchar(64), primary key(groupId));
insert into groupp values(1,1,"group1");


CREATE TABLE invite(inviteId INT NOT NULL AUTO_INCREMENT,version INT,userid varchar(64),groupid INT, PRIMARY KEY (inviteId),
FOREIGN KEY (groupid) REFERENCES groupp(groupId),FOREIGN KEY (userid) REFERENCES user(userName));

insert into invite(version, userid, groupid) values(1, "user2", 1);