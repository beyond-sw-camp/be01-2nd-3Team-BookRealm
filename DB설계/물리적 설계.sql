CREATE TABLE `ORDER` (
	`buyId`	INT UNSIGNED	NOT NULL,
	`payment`	VARCHAR(50)	NULL,
	`destination`	VARCHAR(50)	NULL,
	`userId`	VARCHAR(50)	NOT NULL
);

CREATE TABLE `CART` (
	`cartId`	INT UNSIGNED	NOT NULL,
	`purchase`	INT UNSIGNED	NULL,
	`userId`	VARCHAR(50)	NOT NULL,
	`bookId`	INT UNSIGNED	NOT NULL
);

CREATE TABLE `BOOK` (
	`bookId`	INT UNSIGNED	NOT NULL,
	`category`	VARCHAR(50)	NULL,
	`writer`	VARCHAR(50)	NULL,
	`title`	INT UNSIGNED	NULL,
	`price`	INT UNSIGNED	NULL,
	`stock`	INT UNSIGNED	NULL,
	`sales`	INT UNSIGNED	NULL,
	`publishDate`	DATE	NULL,
	`publisher`	VARCHAR(50)	NULL
);

CREATE TABLE `FAVORITE` (
	`favorId`	INT UNSIGNED	NOT NULL,
	`userId`	VARCHAR(50)	NOT NULL,
	`bookId`	INT UNSIGNED	NOT NULL
);

CREATE TABLE `ORDER LIST` (
	`buyId`	INT UNSIGNED	NOT NULL,
	`bookId`	INT UNSIGNED	NOT NULL,
	`purchase`	INT UNSIGNED	NULL,
	`status`	VARCHAR(50)	NULL
);

CREATE TABLE `REVIEW` (
	`reviewId`	INT UNSIGNED	NOT NULL,
	`popular`	INT UNSIGNED	NULL,
	`contents`	TEXT	NULL,
	`reportDate`	DATETIME	NULL,
	`userId`	VARCHAR(50)	NOT NULL,
	`bookId`	INT UNSIGNED	NOT NULL
);

CREATE TABLE `USER` (
	`userId`	VARCHAR(50)	NOT NULL,
	`username`	VARCHAR(50)	NULL,
	`passwd`	VARCHAR(50)	NULL,
	`address`	VARCHAR(50)	NULL,
	`phone`	VARCHAR(50)	NULL,
	`suType`	VARCHAR(50)	NULL,
	`adminyn`	BOOLEAN	NULL
);

ALTER TABLE `ORDER` ADD CONSTRAINT `PK_ORDER` PRIMARY KEY (
	`buyId`
);

ALTER TABLE `CART` ADD CONSTRAINT `PK_CART` PRIMARY KEY (
	`cartId`
);

ALTER TABLE `BOOK` ADD CONSTRAINT `PK_BOOK` PRIMARY KEY (
	`bookId`
);

ALTER TABLE `FAVORITE` ADD CONSTRAINT `PK_FAVORITE` PRIMARY KEY (
	`favorId`
);

ALTER TABLE `ORDER LIST` ADD CONSTRAINT `PK_ORDER LIST` PRIMARY KEY (
	`buyId`,
	`bookId`
);

ALTER TABLE `REVIEW` ADD CONSTRAINT `PK_REVIEW` PRIMARY KEY (
	`reviewId`
);

ALTER TABLE `USER` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`userId`
);

ALTER TABLE `ORDER LIST` ADD CONSTRAINT `FK_ORDER_TO_ORDER LIST_1` FOREIGN KEY (
	`buyId`
)
REFERENCES `ORDER` (
	`buyId`
);

ALTER TABLE `ORDER LIST` ADD CONSTRAINT `FK_BOOK_TO_ORDER LIST_1` FOREIGN KEY (
	`bookId`
)
REFERENCES `BOOK` (
	`bookId`
);

