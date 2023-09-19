delete from card;
delete from ticket;
commit;
insert into ticket(consumerid, countnum) values ('1111','4');
insert into ticket(consumerid, countnum) values ('2222','5');

SELECT * from card;
SELECT * from ticket;