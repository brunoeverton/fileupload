create database fileupload;
use fileupload;

CREATE TABLE file_data (
	id bigint UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	bytes LONGBLOB NOT NULL
);

CREATE TABLE user (
	id bigint UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	email VARCHAR(200) NOT NULL,
    CONSTRAINT UC_Email UNIQUE (email)
);

CREATE TABLE file_info (
	id bigint UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(200) NOT NULL,
    status tinyint NOT NULL,
    upload_start bigint NOT NULL,
    upload_finish bigint NOT NULL,
    blocks_amount INT(6) NOT NULL,
    last_block_received INT(6) NOT NULL,
    file_data_id bigint UNSIGNED, 
    user_id bigint UNSIGNED NOT NULL,    
    CONSTRAINT UC_FileName_User UNIQUE (name,user_id),
    CONSTRAINT FK_FileData FOREIGN KEY (file_data_id) REFERENCES file_data(id),
    CONSTRAINT FK_User FOREIGN KEY (user_id) REFERENCES user(id)
);   