-- '--' 주석
-- 시퀀스 초기화 방법
-- 모든 데이터를 제거하고 시퀀스를 다시 만든다.
delete from MVCBOARD;
drop sequence MVCBOARD_idx_seq;
-- 자동으로 1씩 증가하는 시퀀스 만들기 (테이블이름_필드이름_seq)
create sequence MVCBOARD_idx_seq;

insert into MVCBOARD (idx, name, subject, content, gup, lev, seq ) 
		values (MVCBOARD_idx_seq.nextval, '홍길동', '1등', '1등 입니다.', MVCBOARD_idx_seq.currval, 0 ,0 );
insert into MVCBOARD (idx, name, subject, content, gup, lev, seq ) 
		values (MVCBOARD_idx_seq.nextval, '임꺽정', '2등', '2등 입니다.', MVCBOARD_idx_seq.currval, 0 ,0 );
insert into MVCBOARD (idx, name, subject, content, gup, lev, seq ) 
		values (MVCBOARD_idx_seq.nextval, '장길산', '3등', '3등 입니다.', MVCBOARD_idx_seq.currval, 0 ,0 );
insert into MVCBOARD (idx, name, subject, content, gup, lev, seq ) 
		values (MVCBOARD_idx_seq.nextval, '일지매', '4등', '4등 입니다.', MVCBOARD_idx_seq.currval, 0 ,0 );


SELECT count(*) FROM MVCBOARD;
SELECT * FROM MVCBOARD order by gup desc, seq;

commit;







