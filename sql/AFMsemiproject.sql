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
    CONSTRAINTS fk_tb_account_user_id FOREIGN KEY (user_id)REFERENCES tb_user (user_id)
);

--drop table address;
create sequence seq_tb_address_no;
CREATE TABLE address (
	adr_no NUMBER NOT NULL,
	user_id VARCHAR2(20) NOT NULL,
	adr_name VARCHAR2(100),
	adr_road VARCHAR2(300) NOT NULL,
	adr_detail VARCHAR2(200) NOT NULL,
    CONSTRAINT pk_address_no PRIMARY KEY (adr_no),
    CONSTRAINT fk_address_user_id FOREIGN KEY (user_id)REFERENCES tb_user (user_id)
);

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

comment on column csboard.board_no is '고객센터번호';
comment on column csboard.board_title is '고객센터제목';
comment on column csboard.user_id is '고객센터작성자 아이디';
comment on column csboard.board_content is '고객센터내용';
comment on column csboard.board_reg_date is '고객센터작성일시';
comment on column csboard.board_readcount is '조회수';

comment on column csboard_comment.cb_no is '게시판 댓글 번호';
comment on column csboard_comment.cb_level is '게시판 댓글 레벨';
comment on column csboard_comment.user_id is '게시판 댓글 작성자';
comment on column csboard_comment.cb_content is '게시판 댓글';
comment on column csboard_comment.cb_board_no is '참조 원글 번호';
comment on column csboard_comment.cb_comment_ref is '게시판댓글 참조번호';
comment on column csboard_comment.reg_date is '게시판댓글 작성일';

alter table tb_order add(adr_no number);

drop sequence seq_csboard_comment_no;
create sequence seq_csboard_comment_no;
create sequence seq_csboard_comment_cb_no;
create table csboard_comment (
    CB_NO number, 
    CB_LEVEL number default 1, 
    USER_ID varchar2(20),
    CB_CONTENT varchar2(2000),
    CB_BOARD_NO number, 
    CB_COMMENT_REF number,
    REG_DATE date default sysdate,
    constraint pk_csboard_comment_cb_no primary key(cb_no),
    constraint fk_csboard_comment_user_id foreign key(user_id) references tb_user(user_id),
    constraint fk_csboard_comment_cb_board_no foreign key(cb_board_no) references csboard(board_no) on delete cascade,
    constraint fk_csboard_comment_ref foreign key(cb_comment_ref) references csboard_comment(cb_no) on delete cascade
);

