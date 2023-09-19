delete from party;
drop sequence party_idx_seq;
create sequence party_idx_seq;


select count(*) from party;
select * from party order by idx desc;
commit;

delete from MEMBER;
 
SELECT * FROM member;
commit;