DROP TABLE IF EXISTS `CATEGORY`;

CREATE TABLE `CATEGORY` (
	`cateno`	INT	auto_increment primary key,
	`cname`	varchar(50)	NULL
);

DROP TABLE IF EXISTS `SERVICE`;

CREATE TABLE `SERVICE` (
	`servno`	INT	auto_increment primary key,
	`userno`	INT	NOT NULL,
	`cateno`	INT	NOT NULL,
	`servname`	varchar(100)	NULL,
	`price`	INT	NULL,
	`discription`	varchar(1000)	NULL,
	`saddr1` varchar(100),
    `saddr2` varchar(100),
    `saddr3` varchar(100),
    `saddr4` varchar(100),
    `saddr5` varchar(100),
    `saddr6` varchar(100),
    `imgurl` varchar(1000)
);

DROP TABLE IF EXISTS `REVIEW`;

CREATE TABLE `REVIEW` (
	`revno`	INT	auto_increment primary key,
	`userno`	INT	NOT NULL,
	`servno`	INT	NOT NULL,
	`title`	varchar(100)	NULL,
	`content`	varchar(1000)	NULL,
	`point`	INT	NULL,
	`cdate`	datetime	NULL
);


DROP TABLE IF EXISTS `PAY`;

CREATE TABLE `PAY` (
	`payno`	INT	auto_increment primary key,
	`servno`	INT	NOT NULL,
	`userno`	INT	NOT NULL
);

DROP TABLE IF EXISTS `QNA`;

CREATE TABLE `QNA` (
	`qnano`	INT	auto_increment primary key,
	`userno`	INT	NOT NULL,
	`qtitle`	varchar(100)	NULL,
	`qcontent`	varchar(1000)	NULL,
	`awriter`	varchar(20)	NULL,
	`acontent`	varchar(1000)	NULL,
	`qdate`	datetime	NULL,
	`adate`	datetime	NULL
);

DROP TABLE IF EXISTS `USER`;

create table user (
	userno int auto_increment,
	email varchar(100) not null,
	pw varchar(20) not null,
	name varchar(20) not null,
	tel varchar(20) not null,
	addr1 varchar(10),
	addr2 varchar(20),
	addr3 varchar(10),
	addr4 varchar(20),
	addr5 varchar(10),
	addr6 varchar(20),
	ispro boolean,
	primary key (userno)
);

DROP TABLE IF EXISTS `FOLLOW`;

CREATE TABLE `FOLLOW` (
	`folno`	INT	auto_increment primary key,
	`userno`	INT	NOT NULL,
	`prono`	INT	NOT NULL
);

DROP TABLE IF EXISTS `MSG`;

CREATE TABLE `MSG` (
	`msgno`	INT	auto_increment primary key,
	`writerno`	INT	NOT NULL,
	`reciverno`	INT	NOT NULL,
	`title`	varchar(100)	NULL,
	`content`	varchar(1000)	NULL,
	`senddate`	datetime	NULL
);

DROP TABLE IF EXISTS `BOARD`;

CREATE TABLE `BOARD` (
	`boardno`	INT auto_increment primary key,
	`userno`	INT	NOT NULL,
	`title`	varchar(100)	NULL,
	`content`	varchar(1000)	NULL,
	`bdate`	datetime	NULL
);

DROP TABLE IF EXISTS `COMMENT`;

CREATE TABLE `COMMENT` (
	`commno`	INT	auto_increment primary key,
	`boardno`	INT	NOT NULL,
	`userno`	INT	NOT NULL,
	`content`	varchar(1000)	NULL,
	`cdate`	datetime	NULL
);

DROP TABLE IF EXISTS `CITYCODE`;

CREATE TABLE `CITYCODE` (
	`citycode`	INT	NOT NULL,
	`cityname`	varchar(20)	NULL
);

DROP TABLE IF EXISTS `GUCODE`;

CREATE TABLE `GUCODE` (
	`gucode`	INT	NOT NULL,
	`guname`	varchar(20)	NULL
);

DROP TABLE IF EXISTS `DONGCODE`;

CREATE TABLE `DONGCODE` (
	`dongcode`	INT	NOT NULL,
	`dongname`	varchar(20)	NULL
);

ALTER TABLE `SERVICE` ADD CONSTRAINT `FK_USER_TO_SERVICE_1` FOREIGN KEY (
	`userno`
)
REFERENCES `USER` (
	`userno`
);

ALTER TABLE `SERVICE` ADD CONSTRAINT `FK_CATEGORY_TO_SERVICE_1` FOREIGN KEY (
	`cateno`
)
REFERENCES `CATEGORY` (
	`cateno`
);

ALTER TABLE `REVIEW` ADD CONSTRAINT `FK_USER_TO_REVIEW_1` FOREIGN KEY (
	`userno`
)
REFERENCES `USER` (
	`userno`
);

ALTER TABLE `REVIEW` ADD CONSTRAINT `FK_SERVICE_TO_REVIEW_1` FOREIGN KEY (
	`servno`
)
REFERENCES `SERVICE` (
	`servno`
);

ALTER TABLE `PAY` ADD CONSTRAINT `FK_SERVICE_TO_PAY_1` FOREIGN KEY (
	`servno`
)
REFERENCES `SERVICE` (
	`servno`
);

ALTER TABLE `PAY` ADD CONSTRAINT `FK_USER_TO_PAY_1` FOREIGN KEY (
	`userno`
)
REFERENCES `USER` (
	`userno`
);

ALTER TABLE `QNA` ADD CONSTRAINT `FK_USER_TO_QNA_1` FOREIGN KEY (
	`userno`
)
REFERENCES `USER` (
	`userno`
);

ALTER TABLE `FOLLOW` ADD CONSTRAINT `FK_USER_TO_FOLLOW_1` FOREIGN KEY (
	`userno`
)
REFERENCES `USER` (
	`userno`
);

ALTER TABLE `FOLLOW` ADD CONSTRAINT `FK_USER_TO_FOLLOW_2` FOREIGN KEY (
	`prono`
)
REFERENCES `USER` (
	`userno`
);

ALTER TABLE `MSG` ADD CONSTRAINT `FK_USER_TO_MSG_1` FOREIGN KEY (
	`writerno`
)
REFERENCES `USER` (
	`userno`
);

ALTER TABLE `MSG` ADD CONSTRAINT `FK_USER_TO_MSG_2` FOREIGN KEY (
	`reciverno`
)
REFERENCES `USER` (
	`userno`
);

ALTER TABLE `BOARD` ADD CONSTRAINT `FK_USER_TO_BOARD_1` FOREIGN KEY (
	`userno`
)
REFERENCES `USER` (
	`userno`
);

ALTER TABLE `COMMENT` ADD CONSTRAINT `FK_BOARD_TO_COMMENT_1` FOREIGN KEY (
	`boardno`
)
REFERENCES `BOARD` (
	`boardno`
);

ALTER TABLE `COMMENT` ADD CONSTRAINT `FK_USER_TO_COMMENT_1` FOREIGN KEY (
	`userno`
)
REFERENCES `USER` (
	`userno`
);


insert into category (cname) values ('교육');
insert into category (cname) values ('개인PT');
insert into category (cname) values ('음악');
insert into category (cname) values ('미술');
insert into category (cname) values ('홈/리빙');
insert into category (cname) values ('건강');
insert into category (cname) values ('미용');
insert into category (cname) values ('스포츠');
insert into category (cname) values ('management');
insert into category (cname) values ('IT개발');
insert into category (cname) values ('사진');
insert into category (cname) values ('웨딩');
insert into category (cname) values ('소송');
insert into category (cname) values ('대여');
insert into category (cname) values ('금융');

delete from dongcode where num != 15469 and dong = '선산읍';
delete from dongcode where num != 15488 and dong = '고아읍';
delete from dongcode where num != 15507 and dong = '무을면';
delete from dongcode where num != 15518 and dong = '옥성면';
delete from dongcode where num != 15528 and dong = '도개면';
delete from dongcode where num != 15538 and dong = '해평면';
delete from dongcode where num != 15554 and dong = '산동면';
delete from dongcode where num != 15565 and dong = '장천면';

delete from dongcode where num != 14586 and dong = '구룡포읍';
delete from dongcode where num != 14597 and dong = '연일읍';
delete from dongcode where num != 14611 and dong = '오천읍';
delete from dongcode where num != 14623 and dong = '대송면';
delete from dongcode where num != 14633 and dong = '동해면';
delete from dongcode where num != 14648 and dong = '장기면';
delete from dongcode where num != 14672 and dong = '호미곶면';
delete from dongcode where num != 14702 and dong = '흥해읍';
delete from dongcode where num != 14733 and dong = '신광면';
delete from dongcode where num != 14747 and dong = '청하면';
delete from dongcode where num != 14766 and dong = '송라면';
delete from dongcode where num != 14776 and dong = '기계면';
delete from dongcode where num != 14813 and dong = '기북면';
delete from dongcode where num != 14866 and dong = '감포읍';
delete from dongcode where num != 14876 and dong = '안강읍';
delete from dongcode where num != 14892 and dong = '건천읍';
delete from dongcode where num != 14904 and dong = '외동읍';
delete from dongcode where num != 14922 and dong = '양북면';
delete from dongcode where num != 14938 and dong = '양남면';
delete from dongcode where num != 14954 and dong = '내남면';
delete from dongcode where city = '경상북도' and num != 14968 and dong = '산내면';
delete from dongcode where city = '경상북도' and num != 14978 and dong = '서면';
delete from dongcode where city = '경상북도' and num != 14987 and dong = '현곡면';
delete from dongcode where city = '경상북도' and num != 14998 and dong = '강동면';
delete from dongcode where city = '경상북도' and num != 15010 and dong = '천북면';
delete from dongcode where city = '경상북도' and num != 15052 and dong = '농소면';
delete from dongcode where city = '경상북도' and num != 15060 and dong = '남면';
delete from dongcode where city = '경상북도' and num != 15070 and dong = '개령면';
delete from dongcode where city = '경상북도' and num != 15070 and dong = '감문면';
delete from dongcode where city = '경상북도' and num != 15095 and dong = '어모면';
delete from dongcode where city = '경상북도' and num != 15108 and dong = '봉산면';
delete from dongcode where city = '경상북도' and num != 15117 and dong = '대항면';
delete from dongcode where city = '경상북도' and num != 15125 and dong = '감천면';
delete from dongcode where city = '경상북도' and num != 15131 and dong = '조마면';
delete from dongcode where city = '경상북도' and num != 15139 and dong = '구성면';
delete from dongcode where city = '경상북도' and num != 15157 and dong = '지례면';
delete from dongcode where city = '경상북도' and num != 15168 and dong = '부항면';
delete from dongcode where city = '경상북도' and num != 15182 and dong = '대덕면';
delete from dongcode where city = '경상북도' and num != 15195 and dong = '증산면';
delete from dongcode where city = '경상북도' and num != 15269 and dong = '와룡면';
delete from dongcode where city = '경상북도' and num != 15288 and dong = '북후면';
delete from dongcode where city = '경상북도' and num != 15301 and dong = '서후면';
delete from dongcode where city = '경상북도' and num != 15313 and dong = '풍천면';
delete from dongcode where city = '경상북도' and num != 15327 and dong = '일직면';
delete from dongcode where city = '경상북도' and num != 15341 and dong = '남후면';
delete from dongcode where city = '경상북도' and num != 15351 and dong = '남선면';
delete from dongcode where city = '경상북도' and num != 15360 and dong = '임하면';
delete from dongcode where city = '경상북도' and num != 15369 and dong = '길안면';
delete from dongcode where city = '경상북도' and num != 15383 and dong = '임동면';
delete from dongcode where city = '경상북도' and num != 15396 and dong = '예안면';
delete from dongcode where city = '경상북도' and num != 15413 and dong = '도산면';
delete from dongcode where city = '경상북도' and num != 15427 and dong = '녹전면';
delete from dongcode where city = '경상북도' and num != 15249 and dong = '풍산읍';
delete from dongcode where city = '경상북도' and num != 15604 and dong = '이산면';
delete from dongcode where city = '경상북도' and num != 15614 and dong = '평은면';
delete from dongcode where city = '경상북도' and num != 15622 and dong = '문수면';
delete from dongcode where city = '경상북도' and num != 15632 and dong = '장수면';
delete from dongcode where city = '경상북도' and num != 15641 and dong = '안정면';
delete from dongcode where city = '경상북도' and num != 15656 and dong = '봉현면';
delete from dongcode where city = '경상북도' and num != 15664 and dong = '순흥면';
delete from dongcode where city = '경상북도' and num != 15673 and dong = '단산면';
delete from dongcode where city = '경상북도' and num != 15682 and dong = '부석면';
delete from dongcode where city = '경상북도' and num != 15589 and dong = '풍기읍';
delete from dongcode where city = '경상북도' and num != 15724 and dong = '금호읍';
delete from dongcode where city = '경상북도' and num != 15746 and dong = '청통면';
delete from dongcode where city = '경상북도' and num != 15762 and dong = '신녕면';
delete from dongcode where city = '경상북도' and num != 15774 and dong = '화산면';
delete from dongcode where city = '경상북도' and num != 15790 and dong = '화북면';
delete from dongcode where city = '경상북도' and num != 15804 and dong = '화남면';
delete from dongcode where city = '경상북도' and num != 15818 and dong = '자양면';
delete from dongcode where city = '경상북도' and num != 15828 and dong = '임고면';
delete from dongcode where city = '경상북도' and num != 15843 and dong = '고경면';
delete from dongcode where city = '경상북도' and num != 15874 and dong = '북안면';
delete from dongcode where city = '경상북도' and num != 15899 and dong = '대창면';
delete from dongcode where city = '경상북도' and num != 15951 and dong = '함창읍';
delete from dongcode where city = '경상북도' and num != 15967 and dong = '중동면';
delete from dongcode where city = '경상북도' and num != 15975 and dong = '사벌국면';
delete from dongcode where city = '경상북도' and num != 15991 and dong = '낙동면';
delete from dongcode where city = '경상북도' and num != 16009 and dong = '청리면';
delete from dongcode where city = '경상북도' and num != 16022 and dong = '공성면';
delete from dongcode where city = '경상북도' and num != 16042 and dong = '외남면';
delete from dongcode where city = '경상북도' and num != 16051 and dong = '내서면';
delete from dongcode where city = '경상북도' and num != 16061 and dong = '모동면';
delete from dongcode where city = '경상북도' and num != 16072 and dong = '모서면';
delete from dongcode where city = '경상북도' and num != 16085 and dong = '화동면';
delete from dongcode where city = '경상북도' and num != 16095 and dong = '화서면';
delete from dongcode where city = '경상북도' and num != 16114 and dong = '외서면';
delete from dongcode where city = '경상북도' and num != 16127 and dong = '은척면';
delete from dongcode where city = '경상북도' and num != 16138 and dong = '공검면';
delete from dongcode where city = '경상북도' and num != 16150 and dong = '이안면';
delete from dongcode where city = '경상북도' and num != 16181 and dong = '문경읍';
delete from dongcode where city = '경상북도' and num != 16200 and dong = '가은읍';
delete from dongcode where city = '경상북도' and num != 16215 and dong = '영순면';
delete from dongcode where city = '경상북도' and num != 16227 and dong = '산양면';
delete from dongcode where city = '경상북도' and num != 16244 and dong = '호계면';
delete from dongcode where city = '경상북도' and num != 16256 and dong = '산북면';
delete from dongcode where city = '경상북도' and num != 16278 and dong = '동로면';
delete from dongcode where city = '경상북도' and num != 16288 and dong = '마성면';
delete from dongcode where city = '경상북도' and num != 16297 and dong = '농암면';
delete from dongcode where city = '경상북도' and num != 16338 and dong = '하양읍';
delete from dongcode where city = '경상북도' and num != 16355 and dong = '진량읍';
delete from dongcode where city = '경상북도' and num != 16381 and dong = '압량읍';
delete from dongcode where city = '경상북도' and num != 16398 and dong = '와촌면';
delete from dongcode where city = '경상북도' and num != 16413 and dong = '자인면';
delete from dongcode where city = '경상북도' and num != 16430 and dong = '용성면';
delete from dongcode where city = '경상북도' and num != 16451 and dong = '남산면';
delete from dongcode where city = '경상북도' and num != 16470 and dong = '남천면';
delete from dongcode where city = '경상북도' and num != 16502 and dong = '소보면';
delete from dongcode where city = '경상북도' and num != 16517 and dong = '효령면';
delete from dongcode where city = '경상북도' and num != 16533 and dong = '부계면';
delete from dongcode where city = '경상북도' and num != 16542 and dong = '우보면';
delete from dongcode where city = '경상북도' and num != 16552 and dong = '의흥면';
delete from dongcode where city = '경상북도' and num != 16564 and dong = '산성면';
delete from dongcode where city = '경상북도' and num != 16572 and dong = '고로면';
delete from dongcode where city = '경상북도' and num != 16484 and dong = '군위읍';
delete from dongcode where city = '경상북도' and num != 16585 and dong = '의성읍';
delete from dongcode where city = '경상북도' and num != 16599 and dong = '단촌면';
delete from dongcode where city = '경상북도' and num != 16609 and dong = '점곡면';
delete from dongcode where city = '경상북도' and num != 16618 and dong = '옥산면';
delete from dongcode where city = '경상북도' and num != 16629 and dong = '사곡면';
delete from dongcode where city = '경상북도' and num != 16640 and dong = '춘산면';
delete from dongcode where city = '경상북도' and num != 16649 and dong = '가음면';
delete from dongcode where city = '경상북도' and num != 16657 and dong = '금성면';
delete from dongcode where city = '경상북도' and num != 16673 and dong = '봉양면';
delete from dongcode where city = '경상북도' and num != 16687 and dong = '비안면';
delete from dongcode where city = '경상북도' and num != 16702 and dong = '구천면';
delete from dongcode where city = '경상북도' and num != 16713 and dong = '단밀면';
delete from dongcode where city = '경상북도' and num != 16722 and dong = '단북면';
delete from dongcode where city = '경상북도' and num != 16730 and dong = '안계면';
delete from dongcode where city = '경상북도' and num != 16740 and dong = '다인면';
delete from dongcode where city = '경상북도' and num != 16757 and dong = '신평면';
delete from dongcode where city = '경상북도' and num != 16764 and dong = '안평면';
delete from dongcode where city = '경상북도' and num != 16778 and dong = '안사면';
delete from dongcode where city = '경상북도' and num != 16786 and dong = '청송읍';
delete from dongcode where city = '경상북도' and num != 16796 and dong = '주왕산면';
delete from dongcode where city = '경상북도' and num != 16807 and dong = '부남면';
delete from dongcode where city = '경상북도' and num != 16817 and dong = '현동면';
delete from dongcode where city = '경상북도' and num != 16825 and dong = '현서면';
delete from dongcode where city = '경상북도' and num != 16839 and dong = '안덕면';
delete from dongcode where city = '경상북도' and num != 16852 and dong = '파천면';
delete from dongcode where city = '경상북도' and num != 16864 and dong = '진보면';
delete from dongcode where city = '경상북도' and num != 16897 and dong = '입암면';
delete from dongcode where city = '경상북도' and num != 16911 and dong = '청기면';
delete from dongcode where city = '경상북도' and num != 16925 and dong = '일월면';
delete from dongcode where city = '경상북도' and num != 16937 and dong = '수비면';
delete from dongcode where city = '경상북도' and num != 16947 and dong = '석보면';
delete from dongcode where city = '경상북도' and num != 16881 and dong = '영양읍';
delete from dongcode where city = '경상북도' and num != 16962 and dong = '영덕읍';
delete from dongcode where city = '경상북도' and num != 16980 and dong = '강구면';
delete from dongcode where city = '경상북도' and num != 16991 and dong = '남정면';
delete from dongcode where city = '경상북도' and num != 17007 and dong = '달산면';
delete from dongcode where city = '경상북도' and num != 17019 and dong = '지품면';
delete from dongcode where city = '경상북도' and num != 17039 and dong = '축산면';
delete from dongcode where city = '경상북도' and num != 17050 and dong = '영해면';
delete from dongcode where city = '경상북도' and num != 17060 and dong = '병곡면';
delete from dongcode where city = '경상북도' and num != 17075 and dong = '창수면';
delete from dongcode where city = '경상북도' and num != 17130 and dong = '각남면';
delete from dongcode where city = '경상북도' and num != 17141 and dong = '풍각면';
delete from dongcode where city = '경상북도' and num != 17154 and dong = '각북면';
delete from dongcode where city = '경상북도' and num != 17163 and dong = '이서면';
delete from dongcode where city = '경상북도' and num != 17181 and dong = '운문면';
delete from dongcode where city = '경상북도' and num != 17194 and dong = '금천면';
delete from dongcode where city = '경상북도' and num != 17205 and dong = '매전면';
delete from dongcode where city = '경상북도' and num != 17090 and dong = '화양읍';
delete from dongcode where city = '경상북도' and num != 17108 and dong = '청도읍';
delete from dongcode where city = '경상북도' and num != 17227 and dong = '대가야읍';
delete from dongcode where city = '경상북도' and num != 17241 and dong = '덕곡면';
delete from dongcode where city = '경상북도' and num != 17252 and dong = '운수면';
delete from dongcode where city = '경상북도' and num != 17262 and dong = '성산면';
delete from dongcode where city = '경상북도' and num != 17277 and dong = '다산면';
delete from dongcode where city = '경상북도' and num != 17288 and dong = '개진면';
delete from dongcode where city = '경상북도' and num != 17300 and dong = '우곡면';
delete from dongcode where city = '경상북도' and num != 17314 and dong = '쌍림면';
delete from dongcode where city = '경상북도' and num != 17332 and dong = '성주읍';
delete from dongcode where city = '경상북도' and num != 17343 and dong = '선남면';
delete from dongcode where city = '경상북도' and num != 17359 and dong = '용암면';
delete from dongcode where city = '경상북도' and num != 17377 and dong = '수륜면';
delete from dongcode where city = '경상북도' and num != 17392 and dong = '가천면';
delete from dongcode where city = '경상북도' and num != 17402 and dong = '금수면';
delete from dongcode where city = '경상북도' and num != 17410 and dong = '대가면';
delete from dongcode where city = '경상북도' and num != 17420 and dong = '벽진면';
delete from dongcode where city = '경상북도' and num != 17430 and dong = '초전면';
delete from dongcode where city = '경상북도' and num != 17430 and dong = '월항면';
delete from dongcode where city = '경상북도' and num != 17454 and dong = '왜관읍';
delete from dongcode where city = '경상북도' and num != 17464 and dong = '북삼읍';
delete from dongcode where city = '경상북도' and num != 17471 and dong = '석적읍';
delete from dongcode where city = '경상북도' and num != 17480 and dong = '지천면';
delete from dongcode where city = '경상북도' and num != 17496 and dong = '동명면';
delete from dongcode where city = '경상북도' and num != 17506 and dong = '가산면';
delete from dongcode where city = '경상북도' and num != 17519 and dong = '약목면';
delete from dongcode where city = '경상북도' and num != 17526 and dong = '기산면';
delete from dongcode where city = '경상북도' and num != 17536 and dong = '예천읍';
delete from dongcode where city = '경상북도' and num != 17555 and dong = '용문면';
delete from dongcode where city = '경상북도' and num != 17590 and dong = '보문면';
delete from dongcode where city = '경상북도' and num != 17604 and dong = '호명면';
delete from dongcode where city = '경상북도' and num != 17621 and dong = '유천면';
delete from dongcode where city = '경상북도' and num != 17642 and dong = '용궁면';
delete from dongcode where city = '경상북도' and num != 17654 and dong = '개포면';
delete from dongcode where city = '경상북도' and num != 17667 and dong = '지보면';
delete from dongcode where city = '경상북도' and num != 17683 and dong = '풍양면';
delete from dongcode where city = '경상북도' and num != 17699 and dong = '효자면';
delete from dongcode where city = '경상북도' and num != 17710 and dong = '은풍면';
delete from dongcode where city = '경상북도' and num != 17733 and dong = '물야면';
delete from dongcode where city = '경상북도' and num != 17742 and dong = '봉성면';
delete from dongcode where city = '경상북도' and num != 17750 and dong = '법전면';
delete from dongcode where city = '경상북도' and num != 17758 and dong = '춘양면';
delete from dongcode where city = '경상북도' and num != 17768 and dong = '소천면';
delete from dongcode where city = '경상북도' and num != 17776 and dong = '재산면';
delete from dongcode where city = '경상북도' and num != 17782 and dong = '명호면';
delete from dongcode where city = '경상북도' and num != 17791 and dong = '상운면';
delete from dongcode where city = '경상북도' and num != 17800 and dong = '석포면';
delete from dongcode where city = '경상북도' and num != 17722 and dong = '봉화읍';
delete from dongcode where city = '경상북도' and num != 17805 and dong = '울진읍';
delete from dongcode where city = '경상북도' and num != 17816 and dong = '평해읍';
delete from dongcode where city = '경상북도' and num != 17824 and dong = '북면';
delete from dongcode where city = '경상북도' and num != 17838 and dong = '근남면';
delete from dongcode where city = '경상북도' and num != 17846 and dong = '기성면';
delete from dongcode where city = '경상북도' and num != 17859 and dong = '온정면';
delete from dongcode where city = '경상북도' and num != 17869 and dong = '죽변면';
delete from dongcode where city = '경상북도' and num != 17874 and dong = '후포면';
delete from dongcode where city = '경상북도' and num != 17878 and dong = '금강송면';
delete from dongcode where city = '경상북도' and num != 17886 and dong = '매화면';
delete from dongcode where city = '경상북도' and num != 17896 and dong = '울릉읍';
delete from dongcode where city = '대구광역시' and num != 1006 and dong = '가창면';
delete from dongcode where city = '대구광역시' and num != 1019 and dong = '하빈면';
delete from dongcode where city = '대구광역시' and num != 1029 and dong = '구지면';
delete from dongcode where city = '대구광역시' and num != 941 and dong = '화원읍';
delete from dongcode where city = '대구광역시' and num != 948 and dong = '논공읍';
delete from dongcode where city = '대구광역시' and num != 958 and dong = '다사읍';
delete from dongcode where city = '대구광역시' and num != 970 and dong = '유가읍';
delete from dongcode where city = '대구광역시' and num != 984 and dong = '옥포읍';
delete from dongcode where city = '대구광역시' and num != 994 and dong = '현풍읍';


create table profile(
    pno int auto_increment primary key,
    userno int not null,
    imgurl varchar(300),
    foreign key (userno) references user (userno)
);

select service.servno, service.userno, service.cateno, service.servname, 
service.price, service.discription,  user.name, service.seraddr, category.cname, profile.imgurl
from service, user, category, profile
where service.userno = user.userno and service.cateno = category.cateno and service.userno = profile.userno;