CREATE TABLE board_cafe(
num NUMBER PRIMARY KEY,
writer VARCHAR2(100) NOT NULL,
title VARCHAR2(100) NOT NULL,
content CLOB,
viewCount NUMBER,
regdate DATE);

CREATE SEQUENCE board_cafe_seq;

CREATE TABLE board_cafe_comment(
num NUMBER PRIMARY KEY, -- 덧글의 글번호
writer VARCHAR2(100),
content VARCHAR2(500),
target_id VARCHAR2(100), -- 덧글의 대상이 되는 아이디
ref_group NUMBER, -- 덧글 그룹
comment_group NUMBER, -- 덧글 내에서의 그룹
regdate DATE
);