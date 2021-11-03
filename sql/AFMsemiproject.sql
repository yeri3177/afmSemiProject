CREATE TABLE tb_user (
	user_id VARCHAR2(20) NOT NULL,
	user_name VARCHAR2(20) NOT NULL,
	user_email VARCHAR2(300) NOT NULL,
	password VARCHAR2(300) NOT NULL,
	birthday DATE,
	phone CHAR(11) NOT NULL,
	user_enroll_date DATE DEFAULT sysdate NOT NULL,
	user_role CHAR(1) DEFAULT 'U' NOT NULL,
	user_expose CHAR(1) DEFAULT 'Y' NOT NULL,
    CONSTRAINTS pk_tb_user_id primary key(user_id),
    CONSTRAINTS ck_user_expose check(user_expose in ('Y','N')),
    CONSTRAINTS ck_user_role check(user_role in ('U','S','A'))
);
--drop table tb_user;
insert into tb_user
	values (
		'admin', '관리자','admin@admin.com','kvOffyqGmDjNUIXm8X/IIQm8+YzWKkfLw3njjegLvAITojzubkoT3myq4K3Yo5AnLW8Ig8J0Mgsf9g28/G3XUA==', sysdate,'01000000000',sysdate, 'A', default
	);
    
    insert into tb_user
    values (
        'honggd', '홍길동','honggd@naver.com','kvOffyqGmDjNUIXm8X/IIQm8+YzWKkfLw3njjegLvAITojzubkoT3myq4K3Yo5AnLW8Ig8J0Mgsf9g28/G3XUA==', sysdate,'01000001000',sysdate, 'A', default
    );
    
    insert into tb_user
    values (
        'aaaaa', '아아아','aaa@naver.com','kvOffyqGmDjNUIXm8X/IIQm8+YzWKkfLw3njjegLvAITojzubkoT3myq4K3Yo5AnLW8Ig8J0Mgsf9g28/G3XUA==', sysdate,'01000001010',sysdate, 'A', default
    );

delete from tb_user where user_id = 'admin';
select * from tb_user;
select * from user_delete;
delete from user_delete where delete_u_id = 'admin';

create sequence seq_product_no;
CREATE TABLE product (
	p_no NUMBER NOT NULL,
	p_user_id VARCHAR2(20) NOT NULL,
	p_reg_date DATE default sysdate NOT NULL,
	p_title VARCHAR2(400) NOT NULL,
	p_content VARCHAR2(4000),
	p_post CHAR(1) DEFAULT 'Y' NOT NULL, --
	p_price NUMBER NOT NULL,
	p_cnt NUMBER NOT NULL,
	p_category VARCHAR2(20) DEFAULT '곡류' NOT NULL,
	p_expose CHAR(1) DEFAULT 'Y' NOT NULL,
	p_report VARCHAR2(400)	,
	p_recommend NUMBER DEFAULT 0,
    constraints fk_p_user_id foreign key(p_user_id)REFERENCES tb_user (user_id),
    CONSTRAINTS pk_p_no PRIMARY KEY (p_no),
    CONSTRAINTS ck_p_expose check(p_expose in ('Y','N')),
    CONSTRAINTS ck_p_post check(p_post in ('Y','N')),
    CONSTRAINTS ck_p_category check(p_category in ('곡류','과실류','채소류'))
);
insert into product(
    SEQ_PRODUCT_NO.nextval, 'admin', default, '게시물5', '게시물5입니다.', default, 32000, 3, default, default, null, default
    
);

--drop table product;

--drop table attachment;
create sequence seq_attachment_no;
CREATE TABLE attachment (
	attach_no NUMBER NOT NULL,
	p_no NUMBER NOT NULL,
	original_filename VARCHAR2(255) NOT NULL,
	renamed_filename VARCHAR2(255) NOT NULL,
	reg_date DATE DEFAULT sysdate NOT NULL,
	img_flag CHAR(1) DEFAULT 'N' NOT NULL,
    CONSTRAINTS pk_attach_no PRIMARY KEY (attach_no),
    CONSTRAINTS fk_attach_p_no FOREIGN KEY(p_no)REFERENCES product(p_no),
    CONSTRAINTS ck_img_flag check(img_flag in ('Y','N'))
);

--drop table tb_order;
create sequence seq_tb_order_no;
CREATE TABLE tb_order (
	order_no NUMBER NOT NULL,
	user_id VARCHAR2(20) NOT NULL,
	order_date DATE DEFAULT sysdate NOT NULL,
	total_price NUMBER NOT NULL,
    CONSTRAINTS pk_order_no PRIMARY KEY (order_no),
    CONSTRAINTS fk_tb_order_user_id FOREIGN KEY(user_id) REFERENCES tb_user (user_id)
);

--drop table cart;
create sequence seq_cart_no;
CREATE TABLE cart (
	cart_no NUMBER NOT NULL,
	p_no NUMBER NOT NULL,
	user_id VARCHAR2(20) NOT NULL,
	cart_product_quantity NUMBER NOT NULL,
    CONSTRAINTS pk_cart_cart_no PRIMARY KEY (cart_no),
    CONSTRAINTS fk_cart_p_no FOREIGN KEY (p_no)REFERENCES product (p_no),
    CONSTRAINTS fk_cart_user_id FOREIGN KEY (user_id)REFERENCES tb_user (user_id)
);

--drop table order_detail;
create sequence seq_order_detail_no;
CREATE TABLE order_detail (
	order_detail_no NUMBER NOT NULL,
	p_no NUMBER NOT NULL,
	order_no NUMBER NOT NULL,
	p_cnt NUMBER,
	p_price NUMBER,
	order_status CHAR(1) DEFAULT 'N' NOT NULL,
    CONSTRAINTS pk_order_detail_no PRIMARY KEY (order_detail_no),
    CONSTRAINTS fk_order_detail_p_no FOREIGN KEY (p_no)REFERENCES product (p_no),
    CONSTRAINTS fk_order_detail_order_no FOREIGN KEY (order_no)REFERENCES tb_order (order_no),
    CONSTRAINTS ck_order_status check(order_status in ('Y','N'))
);

--drop table product_comment;
create sequence seq_product_comment_no;
CREATE TABLE product_comment (
	comment_no NUMBER NOT NULL,
	p_no NUMBER NOT NULL,
	user_id VARCHAR2(20) NOT NULL,
	comment_level number default 1 NOT NULL,
	comment_content VARCHAR2(2000) NOT NULL,
	comment_ref NUMBER,
	reg_date DATE DEFAULT sysdate NOT NULL,
    CONSTRAINTS pk_comment_no PRIMARY KEY (comment_no),
    CONSTRAINTS fk_product_comment_p_no FOREIGN KEY (p_no)REFERENCES product (p_no) on delete cascade,
    CONSTRAINTS fk_product_comment_user_id FOREIGN KEY (user_id)REFERENCES tb_user (user_id),
    CONSTRAINTS fk_product_comment_ref foreign key(comment_ref) references product_comment(comment_no) on delete cascade
);

--drop table product_report;
create sequence seq_product_report_no;
CREATE TABLE product_report (
	report_no NUMBER NOT NULL,
	p_no NUMBER NOT NULL,
	report_title VARCHAR2(100) NOT NULL,
	report_type VARCHAR2(100) NOT NULL,
	report_content VARCHAR2(100) NOT NULL,
	user_id VARCHAR2(20) NOT NULL,
	report_reg_date DATE DEFAULT sysdate NOT NULL,
	report_status CHAR(1) DEFAULT 'N' NOT NULL,
    CONSTRAINTS pk_product_report_no PRIMARY KEY (report_no),
    CONSTRAINTS fk_product_report_p_no FOREIGN KEY (p_no)REFERENCES product (p_no),
    CONSTRAINTS fk_product_report_user_id FOREIGN KEY (user_id)REFERENCES tb_user (user_id),
    CONSTRAINTS ck_report_status check(report_status in ('Y','N'))
);

select * from user_sequences;


--drop table csboard;
create sequence seq_csboard_no;
CREATE TABLE csboard (
	board_no NUMBER NOT NULL,
	user_id VARCHAR2(20) NOT NULL,
	board_title VARCHAR2(100) NOT NULL,
	board_content VARCHAR2(4000) NOT NULL,
	board_reg_date DATE DEFAULT sysdate NOT NULL,
	board_readcount NUMBER default 0 NOT NULL,
	board_status CHAR(1) DEFAULT 'N' NOT NULL,
	board_noticeYN CHAR(1) DEFAULT 'N' NOT NULL,
	board_password VARCHAR2(200) NOT NULL,
	board_lockYN CHAR(1) DEFAULT 'N' NOT NULL,
	board_family NUMBER default 0,
	board_orderby NUMBER default 0,
	board_step NUMBER default 0,
    CONSTRAINTS pk_csboard_board_no PRIMARY KEY (board_no),
    CONSTRAINTS FK_csboard_user_id FOREIGN KEY (user_id)REFERENCES tb_user (user_id),
    CONSTRAINTS ck_csboard_board_status check(board_status in ('Y','N')),
    CONSTRAINTS ck_csboard_board_noticeYN check(board_noticeYN in ('Y','N')),
    CONSTRAINTS ck_csboard_board_lockYN check(board_lockYN in ('Y','N'))
);

--drop table tb_account;
create sequence seq_tb_account_no;
CREATE TABLE tb_account (
    account_no NUMBER NOT NULL,
	user_id VARCHAR2(20) NOT NULL,
	account_number VARCHAR2(30) NOT NULL,
	bank_name VARCHAR2(30) NOT NULL,
    CONSTRAINTS pk_tb_account_no PRIMARY KEY (account_no),
    CONSTRAINTS fk_tb_account_user_id FOREIGN KEY (user_id)REFERENCES tb_user (user_id) on delete cascade
);
ALTER TABLE tb_account DROP FOREIGN KEY fk_tb_account_user_id;
ALTER TABLE tb_account ADD CONSTRAINTS fk_tb_account_user_id FOREIGN KEY (user_id)REFERENCES tb_user (user_id) on delete cascade;

--drop table address;
create sequence seq_tb_address_no;
CREATE TABLE address (
	adr_no NUMBER NOT NULL,
	user_id VARCHAR2(20) NOT NULL,
	adr_name VARCHAR2(100),
	adr_road VARCHAR2(300) NOT NULL,
	adr_detail VARCHAR2(200) NOT NULL,
    CONSTRAINT pk_address_no PRIMARY KEY (adr_no),
    CONSTRAINT fk_address_user_id FOREIGN KEY (user_id)REFERENCES tb_user (user_id) on delete cascade
);
ALTER TABLE address ADD CONSTRAINTS fk_address_user_id FOREIGN KEY (user_id)REFERENCES tb_user (user_id) on delete cascade;

--drop table product_delete;
CREATE TABLE product_delete (
    delete_p_no NUMBER NOT NULL,
	delete_user_id VARCHAR2(20) NOT NULL,
	delete_p_reg_date DATE NOT NULL,
	delete_p_title VARCHAR2(400) NOT NULL,
	delete_p_content VARCHAR2(4000),
	delete_p_post CHAR(1),
	delete_p_price NUMBER NOT NULL,
	delete_p_cnt NUMBER NOT NULL,
	delete_p_category VARCHAR2(20) NOT NULL,
	delete_p_expose CHAR(1) NOT NULL,
	delete_p_report VARCHAR2(400),
	delete_p_recommend NUMBER DEFAULT 0,
	delete_p_date DATE DEFAULT sysdate
);
create or replace trigger trg_product_delete
    after delete on product
    for each row
begin
    insert into product_delete(delete_p_no, delete_user_id, delete_p_reg_date, delete_p_title, delete_p_content, delete_p_post, delete_p_price, delete_p_cnt, delete_p_category, delete_p_expose, delete_p_report, delete_p_recommend, delete_p_date)
    values(:old.p_no, :old.p_user_id,:old.p_reg_date, :old.p_title,:old.p_content, :old.p_post, :old.p_price, :old.p_cnt, :old.p_category, :old.p_expose, :old.p_report, :old.p_recommend, default);
end;
/

--drop table user_delete;
CREATE TABLE user_delete (
    delete_u_id VARCHAR2(20) NOT NULL,
	delete_u_name VARCHAR2(20) NOT NULL,
	delete_u_email VARCHAR2(300) NOT NULL,
	delete_password VARCHAR2(300) NOT NULL,
	delete_birthday DATE,
	delete_phone CHAR(11) NOT NULL,
	delete_u_enroll_date DATE,
	user_role CHAR(1),
	user_expose CHAR(1),
	delete_u_date DATE DEFAULT sysdate
);
create or replace trigger trg_user_delete
    after delete on tb_user
    for each row
begin
    insert into user_delete(delete_u_id, delete_u_name, delete_u_email, delete_password, delete_birthday, delete_phone, delete_u_enroll_date, user_role, user_expose, delete_u_date)
    values(:old.user_id, :old.user_name, :old.user_email, :old.password, :old.birthday, :old.phone, :old.user_enroll_date, :old.user_role, :old.user_expose, default);
end;
/

--drop table cs_delete;
CREATE TABLE cs_delete (
    delete_board_no NUMBER,
    delete_user_id VARCHAR2(20),
	delete_board_title VARCHAR2(100) NOT NULL,
	delete_board_content VARCHAR2(4000) NOT NULL,
	delete_board_reg_date DATE,
	delete_board_readcount NUMBER,
	delete_board_status CHAR(1) NOT NULL,
	delete_board_noticeYN CHAR(1),
	delete_board_password VARCHAR2(200) NOT NULL,
	delete_board_lockYN CHAR(1),
	delete_board_family NUMBER NOT NULL,
	delete_board_orderby NUMBER NOT NULL,
	delete_board_step NUMBER NOT NULL,
	delete_b_date DATE DEFAULT sysdate
);
create or replace trigger trg_cs_delete
    after delete on csboard
    for each row
begin
    insert into cs_delete(delete_board_no, delete_user_id, delete_board_title, delete_board_content, delete_board_reg_date, delete_board_readcount, delete_board_status, delete_board_noticeYN, delete_board_password, delete_board_lockYN, delete_board_family, delete_board_orderby, delete_board_step, delete_b_date)
    values(:old.board_no, :old.user_id, :old.board_title, :old.board_content, :old.board_reg_date, :old.board_readcount, :old.board_status, :old.board_noticeYN, :old.board_password, :old.board_lockYN, :old.board_family, :old.board_orderby, :old.board_step, default);
end;
/

-- 박종서
comment on column csboard.board_no is '고객센터번호';
comment on column csboard.board_title is '고객센터제목';
comment on column csboard.user_id is '고객센터작성자 아이디';
comment on column csboard.board_content is '고객센터내용';
comment on column csboard.board_reg_date is '고객센터작성일시';
comment on column csboard.board_readcount is '조회수';

insert into csboard (board_no, user_id, board_title, board_content, board_reg_date, board_readcount, board_status, board_noticeYN, board_password, board_lockYN, board_family, board_orderby, board_step) 
    values (seq_csboard_no.nextval, 'honggd', '안녕하세요, 고객센터입니다 - 1', '반갑습니다', to_date('18/02/10','RR/MM/DD'), 0,'N', 'N', '1234','Y', 0, 0, 0);
insert into csboard (board_no, user_id, board_title, board_content, board_reg_date, board_readcount, board_status, board_noticeYN, board_password, board_lockYN, board_family, board_orderby, board_step) 
    values (seq_csboard_no.nextval, 'honggd', '안녕하세요, 고객센터입니다 - 2', '반갑습니다', to_date('18/02/11','RR/MM/DD'), 0,'N', 'N', '1234','Y', 0, 0, 0);
insert into csboard (board_no, user_id, board_title, board_content, board_reg_date, board_readcount, board_status, board_noticeYN, board_password, board_lockYN, board_family, board_orderby, board_step) 
    values (seq_csboard_no.nextval, 'honggd', '안녕하세요, 고객센터입니다 - 3', '반갑습니다', to_date('18/02/12','RR/MM/DD'), 0,'N', 'N', '1234','Y', 0, 0, 0);
insert into csboard (board_no, user_id, board_title, board_content, board_reg_date, board_readcount, board_status, board_noticeYN, board_password, board_lockYN, board_family, board_orderby, board_step) 
    values (seq_csboard_no.nextval, 'honggd', '안녕하세요, 고객센터입니다 - 4', '반갑습니다', to_date('18/02/13','RR/MM/DD'), 0,'N', 'N', '1234','Y', 0, 0, 0);    
insert into csboard (board_no, user_id, board_title, board_content, board_reg_date, board_readcount, board_status, board_noticeYN, board_password, board_lockYN, board_family, board_orderby, board_step) 
    values (seq_csboard_no.nextval, 'honggd', '안녕하세요, 고객센터입니다 - 5', '반갑습니다', to_date('18/02/14','RR/MM/DD'), 0,'N', 'N', '1234','Y', 0, 0, 0);
insert into csboard (board_no, user_id, board_title, board_content, board_reg_date, board_readcount, board_status, board_noticeYN, board_password, board_lockYN, board_family, board_orderby, board_step) 
    values (seq_csboard_no.nextval, 'honggd', '안녕하세요, 고객센터입니다 - 6', '반갑습니다', to_date('18/02/15','RR/MM/DD'), 0,'N', 'N', '1234','Y', 0, 0, 0);
insert into csboard (board_no, user_id, board_title, board_content, board_reg_date, board_readcount, board_status, board_noticeYN, board_password, board_lockYN, board_family, board_orderby, board_step) 
    values (seq_csboard_no.nextval, 'honggd', '안녕하세요, 고객센터입니다 - 7', '반갑습니다', to_date('18/02/16','RR/MM/DD'), 0,'N', 'N', '1234','Y', 0, 0, 0);
insert into csboard (board_no, user_id, board_title, board_content, board_reg_date, board_readcount, board_status, board_noticeYN, board_password, board_lockYN, board_family, board_orderby, board_step) 
    values (seq_csboard_no.nextval, 'honggd', '안녕하세요, 고객센터입니다 - 8', '반갑습니다', to_date('18/02/17','RR/MM/DD'), 0,'N', 'N', '1234','Y', 0, 0, 0);
insert into csboard (board_no, user_id, board_title, board_content, board_reg_date, board_readcount, board_status, board_noticeYN, board_password, board_lockYN, board_family, board_orderby, board_step) 
    values (seq_csboard_no.nextval, 'honggd', '안녕하세요, 고객센터입니다 - 9', '반갑습니다', to_date('18/02/18','RR/MM/DD'), 0,'N', 'N', '1234','Y', 0, 0, 0);
insert into csboard (board_no, user_id, board_title, board_content, board_reg_date, board_readcount, board_status, board_noticeYN, board_password, board_lockYN, board_family, board_orderby, board_step) 
    values (seq_csboard_no.nextval, 'honggd', '안녕하세요, 고객센터입니다 - 10', '반갑습니다', to_date('18/02/19','RR/MM/DD'), 0,'N', 'N', '1234','Y', 0, 0, 0);
insert into csboard (board_no, user_id, board_title, board_content, board_reg_date, board_readcount, board_status, board_noticeYN, board_password, board_lockYN, board_family, board_orderby, board_step) 
    values (seq_csboard_no.nextval, 'honggd', '안녕하세요, 고객센터입니다 - 11', '반갑습니다', to_date('18/02/20','RR/MM/DD'), 0,'N', 'N', '1234','Y', 0, 0, 0);


--정예리
--아이디, 이름, 이메일, 비밀번호, 생일, 핸드폰번호11자리, 가입일, 회원권한, 공개여부)
--1
insert into tb_user 
values(
    'yeri3177', 
    '정예리', 
    'yeri3177@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '930325',
    '01012345678',
    sysdate,
    'A',
    'Y'
);

--2
insert into tb_user 
values(
    'jenny99', 
    '김제니', 
    'bp_123@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '960505',
    '01035345678',
    sysdate,
    'U',
    'Y'
);

--3
insert into tb_user 
values(
    'qwerty123', 
    '김철수', 
    'kim_33@naver.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '880624',
    '01045891222',
    sysdate,
    'U',
    'Y'
);

--4
insert into tb_user 
values(
    'ho123', 
    '김윤호', 
    'yoonho88@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '970802',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--5
insert into tb_user 
values(
    'rose32', 
    '김로제', 
    'rose32@naver.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '930325',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--6
insert into tb_user 
values(
    'monika66', 
    '모니카', 
    'monika42@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '840523',
    '01012345678',
    sysdate,
    'A',
    'Y'
);

--7
insert into tb_user 
values(
    'noze123', 
    '김노제', 
    'nozelove@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '970328',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--8
insert into tb_user 
values(
    'yoona123', 
    '임윤아', 
    'gg99@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '901025',
    '01012345678',
    sysdate,
    'A',
    'Y'
);

--9
insert into tb_user 
values(
    'jessica_sa', 
    '제시카', 
    'jessi123@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '891002',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--10
insert into tb_user 
values(
    'karina11', 
    '카리나', 
    'aespa@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '030303',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--11
insert into tb_user 
values(
    'hey123', 
    '김가비', 
    'gabee123@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '990325',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--12
insert into tb_user 
values(
    'momo123', 
    '김모모', 
    'momo123@naver.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '961231',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--13
insert into tb_user 
values(
    'nayeon', 
    '임나연', 
    'twice123@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '970512',
    '01012345678',
    sysdate,
    'A',
    'Y'
);

--14
insert into tb_user 
values(
    'sana_twice', 
    '김사나', 
    'sana33@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '910421',
    '01012345678',
    sysdate,
    'S',
    'Y'
);

--15
insert into tb_user 
values(
    'mina_111', 
    '권미나', 
    'mmm_123@daum.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '730602',
    '01012345678',
    sysdate,
    'S',
    'Y'
);

insert into tb_user 
values(
    'mina_111', 
    '권미나', 
    'mmm_123@daum.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '730602',
    '01012345678',
    sysdate,
    'S',
    'Y'
);

insert into tb_user 
values(
    'leesu_11', 
    '이수근', 
    'leesu_11@daum.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '730602',
    '01012345000',
    sysdate,
    'S',
    'Y'
);

--탈퇴회원 
delete from tb_user where user_id='rose32';
delete from tb_user where user_id='jessica_sa';
delete from tb_user where user_id='mina_111';


--임광수
insert into tb_user 
values(
	'kimgunmo',
	'김건모',
	'kimgunmo@naver.com',
	'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
	'710423',
	'01048403281',
	sysdate,
	'U',
	'Y'
);
insert into address
values (
	 seq_tb_address_no.nextval,
	 'kimgunmo',
	 '회사',
	 '경기도 화성시 삼성전자로 1(반월동)', 
	 '삼성전자 화성 연구소 102'
);

insert into tb_user 
values(
	'yanghak1',
	'양학선',
	'yanghak1@naver.com',
	'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
	'890123',
	'01048203281',
	sysdate,
	'U',
	'Y'
);
insert into address
values (
	 seq_tb_address_no.nextval,
	 'yanghak1',
	 '우리집',
	 '인천광역시 남동구 선수촌공원로 85', 
	 '302동 1002호'
);

insert into tb_user 
values(
	'kimduhan00',
	'김두한',
	'yanghak1@naver.com',
	'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
	'890123',
	'01048203281',
	sysdate,
	'U',
	'Y'
);
insert into address
values (
	 seq_tb_address_no.nextval,
	 'kimduhan00',
	 '회사',
	 '서울특별시 동대문구 회기로 166(회기동)', 
	 'kb빌딩'
);

insert into tb_user 
values(
	'조상구',
	'sanggu222',
	'sanggu222@naver.com',
	'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
	'951223',
	'01048113281',
	sysdate,
	'U',
	'Y'
);
insert into address
values (
	 seq_tb_address_no.nextval,
	 'sanggu222',
	 '집',
	 '경기도 화성시 경기대로 990(병점동, 화성 병점 에스케이 뷰)', 
	 '101동 1001호'
);

insert into tb_user 
values(
	'박준규',
	'parkjun3322',
	'parkjun3322@naver.com',
	'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
	'971223',
	'01022113281',
	sysdate,
	'U',
	'Y'
);
insert into address
values (
	 seq_tb_address_no.nextval,
	 'parkjun3322',
	 '집',
	 '경기도 수원시 권선구 구운로27번길 23(구운동)', 
	 '302번지'
);

insert into tb_user 
values(
	'구마적',
	'kuma33',
	'kuma33@naver.com',
	'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
	'810413',
	'01011113281',
	sysdate,
	'U',
	'Y'
);
insert into address
values (
	 seq_tb_address_no.nextval,
	 'kuma33',
	 '병원',
	 '경기도 의정부시 흥선로 142(의정부동)', 
	 '경기도립의정부병원 3층 프론트'
);

insert into tb_user 
values(
	'신마적',
	'sinma223',
	'sinma223@naver.com',
	'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
	'770513',
	'01099113281',
	sysdate,
	'S',
	'Y'
);
insert into address
values (
	 seq_tb_address_no.nextval,
	 'sinma223',
	 '회사',
	 '경기도 남양주시 경춘로 520(다산동)', 
	 '경기도구리남양주교육지원청 1층'
);
insert into tb_account
values (
	seq_tb_account_no.nextval,
	'sinma223', 
	'1002154173086', 
	'우리은행'
);


insert into tb_user 
values(
	'정은찬',
	'jungsilver22300',
	'jungsilver22300@naver.com',
	'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
	'780513',
	'01088113281',
	sysdate,
	'S',
	'Y'
);
insert into address
values (
	 seq_tb_address_no.nextval,
	 'jungsilver22300',
	 '집',
	 '경기도 파주시 탄현면 얼음실로 40', 
	 '107동 1404호'
);
insert into tb_account
values (
	seq_tb_account_no.nextval,
	'jungsilver22300', 
	'1004133123386', 
	'우리은행'
);

insert into tb_user 
values(
	'이창훈',
	'changhun300',
	'changhun300@naver.com',
	'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
	'790513',
	'01077113281',
	sysdate,
	'S',
	'Y'
);
insert into address
values (
	 seq_tb_address_no.nextval,
	 'changhun300',
	 '집',
	 '경기도 의왕시 부곡중앙북1길 9(삼동)', 
	 '2층 상가'
);
insert into tb_account
values (
	seq_tb_account_no.nextval,
	'changhun300', 
	'1001113123295', 
	'우리은행'
);

insert into tb_user 
values(
	'조여정',
	'jojo210',
	'jojo210@naver.com',
	'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
	'800513',
	'01083113281',
	sysdate,
	'S',
	'Y'
);
insert into address
values (
	 seq_tb_address_no.nextval,
	 'jojo210',
	 '집',
	 '경기도 성남시 분당구 판교공원로1길 14(판교동)', 
	 '1층 113호'
);
insert into tb_account
values (
	seq_tb_account_no.nextval,
	'jojo210', 
	'1009122124496', 
	'우리은행'
);

select * from tb_user;
delete from tb_user where user_id = '박준규';

--강구진
insert into cart values(seq_cart_no.nextval,9,'honggd',1);
insert into cart values(seq_cart_no.nextval,8,'aaaaa',3);
insert into cart values(seq_cart_no.nextval,10,'honggd',2);

--박종서
--아이디, 이름, 이메일, 비밀번호, 생일, 핸드폰번호11자리, 가입일, 회원권한, 공개여부

insert into tb_user 
values(
    'njh1005', 
    '나준호', 
    'njh1005@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '891005',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--njh1005 배송지 
insert into address
values (seq_tb_address_no.nextval, 'njh1005', '우리집', '서울 노원구 동일로241길 53', '두산아파트 205동 708호');


insert into tb_user 
values(
    'hoi1220', 
    '황둘리', 
    'hoi1220@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '500808',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--hoi1220 배송지 
insert into address
values (seq_tb_address_no.nextval, 'hoi1220', '우리집', '경북 경주시 불국로 385 불국사', '사랑채');


insert into tb_user 
values(
    'eomjunho92', 
    '엄준호', 
    'eomjunho92@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '920808',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--eomjunho92 배송지 
insert into address
values (seq_tb_address_no.nextval, 'eomjunho92', '회사', '경기 구리시 경춘로 153 한양대학교구리병원', '응급실');


insert into tb_user 
values(
    'feel_good19', 
    '엄은진', 
    'feel_good19@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '910924',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--feel_good19 배송지 
insert into address
values (seq_tb_address_no.nextval, 'feel_good19', '회사', '경기 수원시 영통구 월드컵로 164', '아주대학교의료원');

insert into tb_user 
values(
    'jimin0524', 
    '김지민', 
    'jimin0524@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '920524',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--jimin0524 배송지 
insert into address
values (seq_tb_address_no.nextval, 'jimin0524', '우리집', '서울 강남구 논현로136길 10', '505호');


insert into tb_user 
values(
    'minv0526', 
    '김민석', 
    'minv0526@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '9000526',
    '01012345678',
    sysdate,
    'S',
    'Y'
);

--minv0526 배송지 
insert into address
values (seq_tb_address_no.nextval, 'minv0526', '우리집', '서울 강동구 천호대로185길 36', '101호');

--minv0526 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'admin', '110111686868', '신한은행');



insert into tb_user 
values(
    'junomong99', 
    '오준호', 
    'junomong99@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '9000324',
    '01012345678',
    sysdate,
    'S',
    'Y'
);

--junomong99 배송지 
insert into address
values (seq_tb_address_no.nextval, 'junomong99', '우리집', '경기 의정부시 송양로 46', '705동 809호');

--junomong99 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'junomong99', '1002111686868', '우리은행');


insert into tb_user 
values(
    'simpact05', 
    '심광진', 
    'simpact05@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '890524',
    '01012345678',
    sysdate,
    'S',
    'Y'
);

--simpact05 배송지 
insert into address
values (seq_tb_address_no.nextval, 'simpact05', '우리집', '경기 남양주시 다산중앙로146번길 7', '1007동 608호');

--simpact05 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'simpact05', '30012347890123', '국민은행');


insert into tb_user 
values(
    'ameba66', 
    '아메바', 
    'ameba66@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '990909',
    '01012345678',
    sysdate,
    'S',
    'Y'
);

--ameba66 배송지 
insert into address
values (seq_tb_address_no.nextval, 'ameba66', '우리집', '충북 청주시 상당구 중고개로337번길23', '금천동현대아파트 305동 506호');

--ameba66 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'ameba66', '1002905786354', '우리은행');


insert into tb_user 
values(
    'yeochi11', 
    '곽여치', 
    'yeochi11@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '750619',
    '01012345678',
    sysdate,
    'S',
    'Y'
);

--yeochi11 배송지 
insert into address
values (seq_tb_address_no.nextval, 'yeochi11', '우리집', '부산 해운대구 달맞이길 30', '1001동 1007호');

--yeochi11 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'yeochi11', '1657890123423', '시티은행');


-- 강구진
insert into tb_user
	values (
		'one1', '김하나', 'one1@gmail.com' ,'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==', '910101' ,'01010000000',sysdate, 'U', default
	);
    insert into address
    values (seq_tb_address_no.nextval, 'one1', '회사', '경기 성남시 분당구 불정로 6', '네이버 그린팩토리');
    
    insert into tb_user
	values (
		'two2', '김두울', 'two2@gmail.com' ,'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==', '920202' ,'01001000000',sysdate, 'U', default
	);
    insert into address
    values (seq_tb_address_no.nextval, 'two2', '회사', '서울 강남구 테헤란로 152', '강남 파이낸스 센터 22층 구글코리아');
    
    insert into tb_user
	values (
		'three3', '김세엣', 'three3@gmail.com' ,'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',  '930303' ,'01000000010',sysdate, 'U', default
	);
    insert into address
    values (seq_tb_address_no.nextval, 'three3', '회사', '경기 성남시 분당구 판교로 256번길 7', '넥슨코리아');
    
    insert into tb_user
	values (
		'four4', '김네엣', 'four4@gmail.com' ,'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==', '940404' ,'01000000001',sysdate, 'U', default
	);
    insert into address
    values (seq_tb_address_no.nextval, 'four4', '회사', '경기 성남시 분당구 판교역로 152', '14층 카카오게임즈');
    
    insert into tb_user
	values (
		'five5', '김다섯', 'five5@gmail.com' ,'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',  '950505' ,'01000000100',sysdate, 'U', default
	);
    insert into address
    values (seq_tb_address_no.nextval, 'five5', '회사', '경기 성남시 분당구 대왕판교로644번길 12', '엔씨소프트');
    
    insert into tb_user
	values (
		'six6', '김여섯', 'six6@gmail.com' ,'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',  '960606' ,'01000001000',sysdate, 'U', default
	);
    insert into address
    values (seq_tb_address_no.nextval, 'six6', '우리집', '서울 송파구 한가람로 478', '씨티극동아파트 101동 101호');
    
    insert into tb_user
	values (
		'seven7', '김일곱', 'seven7@gmail.com' ,'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',  '970707' ,'01011000000',sysdate, 'U', default
	);
    insert into address
    values (seq_tb_address_no.nextval, 'seven7', '회사', '서울 강남구 논현로76길 24', '엠티리버컨설팅');
    
    insert into tb_user
	values (
		'eight8', '김여덟', 'eight8@gmail.com' ,'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',  '980808' ,'01000110000',sysdate, 'U', default
	);
    insert into address
    values (seq_tb_address_no.nextval, 'eight8', '회사', '서울 강남구 테헤란로14길 6', '남도빌딩 2층 kh정보교육원');

    insert into tb_user
	values (
		'nine9', '김아홉', 'nine9@gmail.com' ,'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',  '990909' ,'01000000011',sysdate, 'U', default
	);
    insert into address
    values (seq_tb_address_no.nextval, 'nine9', '회사', '서울 강남구 테헤란로14길 6', '남도빌딩 3층 kh정보교육원');
    
    insert into tb_user
	values (
		'ten10', '김여얼', 'ten10@gmail.com' ,'1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',  '901010' ,'01000001100',sysdate, 'S', default
	);
    insert into address
    values (seq_tb_address_no.nextval, 'ten10', '회사', '제주특별자치도 제주시 첨단로', '242 카카오본사');
    insert into tb_account
    values (seq_tb_account_no.nextval, 'ten10', '3333101010101', '카카오뱅크');

-- 박수빈
insert into tb_user
	values (
		'lmh', '이민형', 'lmh123@naver.com', '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==', '990802' ,'01019990802',sysdate, 'U', default
	);
--lmh배송지 
    insert into address
    values (seq_tb_address_no.nextval, 'lmh', '우리집', '경기도 남양주시 덕소로33', '주공아파트 301동 202호');




insert into tb_user
	values (
		'ldh', '이동혁', 'ldh123@naver.com', '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==', '990606' ,'01020000606',sysdate, 'U', default
	);
--ldh배송지 
    insert into address
    values (seq_tb_address_no.nextval, 'ldh', '회사', '서울특별시 중구 을지로7가 2-1', '살림터 1층');



insert into tb_user
	values (
		'psb', '박수빈', 'psb123@naver.com', '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==', '980118' ,'01091208109',sysdate, 'U', default
	);
--psb배송지 
    insert into address
    values (seq_tb_address_no.nextval, 'psb', '우리집', '경기도 남양주시 와부읍 덕소로33', '301동 103호');


insert into tb_user
	values (
		'kty', '김태연', 'kty123@naver.com', '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==', '920321' ,'01001526398',sysdate, 'U', default
	);
--kty배송지 
    insert into address
    values (seq_tb_address_no.nextval, 'kty', '회사', '서울특별시 강남구 삼성로 648', '1104호');


insert into tb_user
	values (
		'jsm', '전새미', 'jsm123@naver.com', '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==', '981119' ,'01015975642',sysdate, 'U', default
	);
--jsm배송지 
    insert into address
    values (seq_tb_address_no.nextval, 'jsm', '우리집', '서울 강남구 도산대로53길 15', '101호');



insert into tb_user 
values(
    'kdy', 
    '김도용', 
    'kdy123@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '960220',
    '01001270127',
    sysdate,
    'S',
    'Y'
);

--kdy 배송지 
insert into address
values (seq_tb_address_no.nextval, 'kdy', '서울특별시 동대문구 21', '201동 303호');

--kdy 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'kdy', '67270201428284', '국민은행');




insert into tb_user 
values(
    'lsm', 
    '이수만', 
    'lsm123@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '590209',
    '01012215663',
    sysdate,
    'S',
    'Y'
);

--lsm 배송지 
insert into address
values (seq_tb_address_no.nextval, 'lsm', '서울특별시 성동구 왕십리로 83-21 에스엠엔터테인먼트', '1층');

--lsm 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'lsm', '323256159845', '우리은행');





insert into tb_user 
values(
    'jyp', 
    '박진영', 
    'jyp123@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '660823',
    '01058974412',
    sysdate,
    'S',
    'Y'
);

--jyp 배송지 
insert into address
values (seq_tb_address_no.nextval, 'jyp', '서울 강동구 강동대로 205 JYP Center', '1층');

--jyp 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'jyp', '10156963222', '신한은행');


insert into tb_user 
values(
    'psh', 
    '방시혁', 
    'psh123@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '660821',
    '01012574890',
    sysdate,
    'S',
    'Y'
);

-psh 배송지 
insert into address
values (seq_tb_address_no.nextval, 'psh', '서울 서대문구 신촌역로 22-5', '103호');

--psh 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'psh', '67259845851', '국민은행');



insert into tb_user 
values(
    'lmj', 
    '이미주', 
    'lmj123@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '950623',
    '01025236985',
    sysdate,
    'S',
    'Y'
);

-lmj 배송지 
insert into address
values (seq_tb_address_no.nextval, 'lmj', '서울 마포구 월드컵북로23길 14', '2층');

--lmj 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'lmj', '121202358955', '기업은행');

------------------------------
--박수빈
------------------------------
insert into tb_user
	values (
		'lmh', '이민형', 'lmh123@naver.com', '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==', '990802' ,'01019990802',sysdate, 'U', default
	);
--lmh배송지 
    insert into address
    values (seq_tb_address_no.nextval, 'lmh', '우리집', '경기도 남양주시 덕소로33', '주공아파트 301동 202호');




insert into tb_user
	values (
		'ldh', '이동혁', 'ldh123@naver.com', '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==', '990606' ,'01020000606',sysdate, 'U', default
	);
--ldh배송지 
    insert into address
    values (seq_tb_address_no.nextval, 'ldh', '회사', '서울특별시 중구 을지로7가 2-1', '살림터 1층');



insert into tb_user
	values (
		'psb', '박수빈', 'psb123@naver.com', '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==', '980118' ,'01091208109',sysdate, 'U', default
	);
--psb배송지 
    insert into address
    values (seq_tb_address_no.nextval, 'psb', '우리집', '경기도 남양주시 와부읍 덕소로33', '301동 103호');


insert into tb_user
	values (
		'kty', '김태연', 'kty123@naver.com', '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==', '920321' ,'01001526398',sysdate, 'U', default
	);
--kty배송지 
    insert into address
    values (seq_tb_address_no.nextval, 'kty', '회사', '서울특별시 강남구 삼성로 648', '1104호');


insert into tb_user
	values (
		'jsm', '전새미', 'jsm123@naver.com', '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==', '981119' ,'01015975642',sysdate, 'U', default
	);
--jsm배송지 
    insert into address
    values (seq_tb_address_no.nextval, 'jsm', '우리집', '서울 강남구 도산대로53길 15', '101호');



insert into tb_user 
values(
    'kdy', 
    '김도용', 
    'kdy123@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '960220',
    '01001270127',
    sysdate,
    'S',
    'Y'
);

--kdy 배송지 
insert into address
values (seq_tb_address_no.nextval, 'kdy','우리집', '서울특별시 동대문구 21', '201동 303호');

--kdy 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'kdy', '67270201428284', '국민은행');




insert into tb_user 
values(
    'lsm', 
    '이수만', 
    'lsm123@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '590209',
    '01012215663',
    sysdate,
    'S',
    'Y'
);

--lsm 배송지 
insert into address
values (seq_tb_address_no.nextval, 'lsm','회사', '서울특별시 성동구 왕십리로 83-21 에스엠엔터테인먼트', '1층');

--lsm 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'lsm', '323256159845', '우리은행');





insert into tb_user 
values(
    'jyp', 
    '박진영', 
    'jyp123@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '660823',
    '01058974412',
    sysdate,
    'S',
    'Y'
);

--jyp 배송지 
insert into address
values (seq_tb_address_no.nextval, 'jyp', '회사', '서울 강동구 강동대로 205 JYP Center', '1층');

--jyp 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'jyp', '10156963222', '신한은행');


insert into tb_user 
values(
    'psh', 
    '방시혁', 
    'psh123@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '660821',
    '01012574890',
    sysdate,
    'S',
    'Y'
);

-psh 배송지 
insert into address
values (seq_tb_address_no.nextval, 'psh', '회사', '서울 서대문구 신촌역로 22-5', '103호');

--psh 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'psh', '67259845851', '국민은행');



insert into tb_user 
values(
    'lmj', 
    '이미주', 
    'lmj123@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '950623',
    '01025236985',
    sysdate,
    'S',
    'Y'
);

-lmj 배송지 
insert into address
values (seq_tb_address_no.nextval, 'lmj', '회사', '서울 마포구 월드컵북로23길 14', '2층');

--lmj 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'lmj', '121202358955', '기업은행');

------------------------------
--박종서
------------------------------
--아이디, 이름, 이메일, 비밀번호, 생일, 핸드폰번호11자리, 가입일, 회원권한, 공개여부

insert into tb_user 
values(
    'njh1005', 
    '나준호', 
    'njh1005@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '891005',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--njh1005 배송지 
insert into address
values (seq_tb_address_no.nextval, 'njh1005', '우리집', '서울 노원구 동일로241길 53', '두산아파트 205동 708호');


insert into tb_user 
values(
    'hoi1220', 
    '황둘리', 
    'hoi1220@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '500808',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--hoi1220 배송지 
insert into address
values (seq_tb_address_no.nextval, 'hoi1220', '우리집', '경북 경주시 불국로 385 불국사', '사랑채');


insert into tb_user 
values(
    'eomjunho92', 
    '엄준호', 
    'eomjunho92@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '920808',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--eomjunho92 배송지 
insert into address
values (seq_tb_address_no.nextval, 'eomjunho92', '회사', '경기 구리시 경춘로 153 한양대학교구리병원', '응급실');


insert into tb_user 
values(
    'feel_good19', 
    '엄은진', 
    'feel_good19@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '910924',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--feel_good19 배송지 
insert into address
values (seq_tb_address_no.nextval, 'feel_good19', '회사', '경기 수원시 영통구 월드컵로 164', '아주대학교의료원');

insert into tb_user 
values(
    'jimin0524', 
    '김지민', 
    'jimin0524@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '920524',
    '01012345678',
    sysdate,
    'U',
    'Y'
);

--jimin0524 배송지 
insert into address
values (seq_tb_address_no.nextval, 'jimin0524', '우리집', '서울 강남구 논현로136길 10', '505호');


insert into tb_user 
values(
    'minv0526', 
    '김민석', 
    'minv0526@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '900526',
    '01012345678',
    sysdate,
    'S',
    'Y'
);

--minv0526 배송지 
insert into address
values (seq_tb_address_no.nextval, 'minv0526', '우리집', '서울 강동구 천호대로185길 36', '101호');

--minv0526 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'minv0526', '110111686868', '신한은행');



insert into tb_user 
values(
    'junomong99', 
    '오준호', 
    'junomong99@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '9000324',
    '01012345678',
    sysdate,
    'S',
    'Y'
);

--junomong99 배송지 
insert into address
values (seq_tb_address_no.nextval, 'junomong99', '우리집', '경기 의정부시 송양로 46', '705동 809호');

--junomong99 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'junomong99', '1002111686868', '우리은행');


insert into tb_user 
values(
    'simpact05', 
    '심광진', 
    'simpact05@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '890524',
    '01012345678',
    sysdate,
    'S',
    'Y'
);

--simpact05 배송지 
insert into address
values (seq_tb_address_no.nextval, 'simpact05', '우리집', '경기 남양주시 다산중앙로146번길 7', '1007동 608호');

--simpact05 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'simpact05', '30012347890123', '국민은행');


insert into tb_user 
values(
    'ameba66', 
    '아메바', 
    'ameba66@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '990909',
    '01012345678',
    sysdate,
    'S',
    'Y'
);

--ameba66 배송지 
insert into address
values (seq_tb_address_no.nextval, 'ameba66', '우리집', '충북 청주시 상당구 중고개로337번길23', '금천동현대아파트 305동 506호');

--ameba66 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'ameba66', '1002905786354', '우리은행');


insert into tb_user 
values(
    'yeochi11', 
    '곽여치', 
    'yeochi11@gmail.com', 
    '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==',
    '750619',
    '01012345678',
    sysdate,
    'S',
    'Y'
);

--yeochi11 배송지 
insert into address
values (seq_tb_address_no.nextval, 'yeochi11', '우리집', '부산 해운대구 달맞이길 30', '1001동 1007호');

--yeochi11 계좌 tb_account
insert into tb_account
values (seq_tb_account_no.nextval, 'yeochi11', '1657890123423', '시티은행');

select * from tb_user where user_role = 'S'; -- 17

------------------------------------------
-- 정예리
------------------------------------------
--회원권한 변경
update tb_user set user_role = 'U' where user_id = 'sana_twice';
update tb_user set user_role = 'U' where user_id = 'nayeon';
update tb_user set user_role = 'U' where user_id = 'momo123';
update tb_user set user_role = 'U' where user_id = 'hey123';
update tb_user set user_role = 'U' where user_id = 'karina11';
update tb_user set user_role = 'U' where user_id = 'yoona123';
update tb_user set user_role = 'U' where user_id = 'noze123';
update tb_user set user_role = 'U' where user_id = 'monika66';
update tb_user set user_role = 'S' where user_id = 'ho123';
update tb_user set user_role = 'S' where user_id = 'qwerty123';
update tb_user set user_role = 'U' where user_id = 'aaaaa';

--배송지추가
insert into address values (seq_tb_address_no.nextval, 'yeri3177', '우리집', '서울 서초구 신반포로 270', '반포자이아파트 130동 1103호');
insert into address values (seq_tb_address_no.nextval, 'aaaaa', '우리집', '경기 시흥시 은계남로 12', '시흥은계한양수자인 1311-132');
insert into address values (seq_tb_address_no.nextval, 'jenny99', '우리집', '경기 시흥시 삼미시장2길 40', '신천동 736 삼미아파트 103');
insert into address values (seq_tb_address_no.nextval, 'qwerty123', '우리집', '경기 시흥시 은행로 107', '은행대우푸르지오3차아파트 105동 1602호');
insert into address values (seq_tb_address_no.nextval, 'ho123', '집주소', '경기 시흥시 은행로 233', '늘푸른벽산 302동 1301호');
insert into address values (seq_tb_address_no.nextval, 'monika66', '회사', '경기 부천시 지봉로 43', '가톨릭대학교 인문동 1101호');
insert into address values (seq_tb_address_no.nextval, 'noze123', '회사', '경기 부천시 신흥로56번길 25', '부천대학교 2311호');
insert into address values (seq_tb_address_no.nextval, 'yoona123', '회사', '인천 서구 청라한울로 96', '인천서부일반산업단지 5동 13');
insert into address values (seq_tb_address_no.nextval, 'karina11', '우리집', '경기 부천시 석천로 216', '은하마을대우동부아파트 302호');
insert into address values (seq_tb_address_no.nextval, 'hey123', '우리집', '경기 광명시 광명로 877', '광명한진타운아파트 103동 908호');
insert into address values (seq_tb_address_no.nextval, 'momo123', '회사', '서울 구로구 천왕로 91', '천왕이펜하우스1단지아파트 1033동 2411호');
insert into address values (seq_tb_address_no.nextval, 'nayeon', '우리집', '경기 시흥시 배곧3로 27-7', '배곧신도시SK뷰아파트 710동 2313호');
insert into address values (seq_tb_address_no.nextval, 'sana_twice', '우리집', '경기 시흥시 배곧4로 81-29', '배곧센텀베이2차 800동 101호');
insert into address values (seq_tb_address_no.nextval, 'leesu_11', '우리집', '경기 시흥시 정왕신길로49번길 26', '시화미주아파트 211호');
insert into address values (seq_tb_address_no.nextval, 'mina_111', '우리집', '경기 시흥시 장곡로53번길 10', '숲속마을1단지 506호');
insert into address values (seq_tb_address_no.nextval, 'admin', '회사', '서울 강남구 테헤란로14길 6', '남도빌딩 2층');
insert into address values (seq_tb_address_no.nextval, 'honggd', '회사', '서울 강남구 강남대로118길 12', '부경빌딩 103호');

--계좌추가
insert into tb_account values (seq_tb_account_no.nextval, 'mina_111', '110352058123', '신한은행');
insert into tb_account values (seq_tb_account_no.nextval, 'leesu_11', '330352058572', '카카오뱅크');
insert into tb_account values (seq_tb_account_no.nextval, 'ho123', '130852558333', 'NH농협은행');
insert into tb_account values (seq_tb_account_no.nextval, 'qwerty123', '110352258824', '신한은행');

-- 122번 게시글에 대한 샘플 댓글 생성
insert into csboard_comment
values(
    seq_csboard_comment_cb_no.nextval,
    default,
    'jisoo123',
    '글 잘 읽었습니다',
    122,
    null,
    default
);

insert into csboard_comment
values(
    seq_csboard_comment_cb_no.nextval,
    default,
    'admin',
    '좋은 글 감사합니다.',
    122,
    null,
    default
);

insert into csboard_comment
values(
    seq_csboard_comment_cb_no.nextval,
    default,
    'han123',
    '이번달 베스트 게시글에 선정되셨습니다. 축하드려요~',
    122,
    null,
    default
);

-- 122번 게시글의 샘플 대댓글
-- 18번 댓글
insert into csboard_comment
values(
    seq_csboard_comment_cb_no.nextval,
    2, -- cb_level
    'aaaaa',
    '대댓글입니다..',
    122,
    18, -- cb_no
    default
);

-- 19번 댓글
insert into csboard_comment
values(
    seq_csboard_comment_cb_no.nextval,
    2, -- cb_level
    'nayeon',
    '좋은 글 감사합니다.',
    122,
    19,
    default
);
select * from csboard_comment order by cb_no;
    

