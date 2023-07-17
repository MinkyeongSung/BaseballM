drop database  baseball_m;
create database baseball_m;
use baseball_m;

CREATE TABLE IF NOT EXISTS `stadium` (
                                         `idx` int primary key auto_increment,
                                         `name` varchar(100) not null,
                                         `created_at` timestamp not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `team` (
                                      `idx` int primary key auto_increment,
                                      `stadium_idx` int not NULL,
                                      `name` varchar(100) not NULL,
                                      created_at timestamp not null,
                                      KEY `stadiumidx` (`stadium_idx`),
                                      CONSTRAINT `stadiumidx` FOREIGN KEY (`stadium_idx`) REFERENCES `stadium` (`idx`)  ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `player` (
                                        `idx` INT primary key AUTO_INCREMENT,
                                        `team_idx` INT ,
                                        `name` VARCHAR(100)  not NULL,
                                        `position` VARCHAR(100) not NULL ,
                                        `created_at` TIMESTAMP not NULL ,
                                        KEY `teamidx` (`team_idx`),
                                        CONSTRAINT `teamidx` FOREIGN KEY (`team_idx`) REFERENCES `team` (`idx`) ON UPDATE CASCADE,
                                        CONSTRAINT `position_unique` UNIQUE (`team_idx`, `position`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `out_player` (
                                            `idx` INT primary key AUTO_INCREMENT,
                                            `player_id` INT not null,
                                            `reason` VARCHAR(255) default null,
                                            `created_at` TIMESTAMP default null,
                                            KEY `playeridx` (`player_id`),
                                            CONSTRAINT `playeridx` FOREIGN KEY (`player_id`) REFERENCES `player` (`idx`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `stadium` (`name`, `created_at`) VALUES
                                                 ('사직구장', now()),
                                                 ('잠실야구장', now()),
                                                 ('기아야구장', now());


INSERT INTO `team` (`stadium_idx`, `name`, `created_at`) VALUES
                                                             (1, '롯데', now()),
                                                             (2, 'LG', now()),
                                                             (3, 'SK', now());



INSERT INTO `player` (`team_idx`, `name`, `position`, `created_at`) VALUES
                                                                        (1, '김원중', '1루수', now()),
                                                                        (1, '최준용', '2루수', now()),
                                                                        (1, '잭렉스', '3루수', now()),
                                                                        (1, '윤동희', '포수', now()),
                                                                        (1, '김진욱', '유격수', now()),
                                                                        (1, '한동희', '투수', now()),
                                                                        (1, '이정훈', '중견수', now()),
                                                                        (1, '손성빈', '좌익수', now()),
                                                                        (1, '니코구드럼', '우익수', now()),
                                                                        (2, '케이시 켈리', '1루수', now()),
                                                                        (2, '이재원', '2루수', now()),
                                                                        (2, '홍창기', '3루수', now()),
                                                                        (2, '오스틴 딘', '포수', now()),
                                                                        (2, '김현수', '투수', now()),
                                                                        (2, '오지환', '유격수', now()),
                                                                        (2, '정우영', '중견수', now()),
                                                                        (2, '박동원', '좌익수', now()),
                                                                        (2, '이정용', '우익수', now()),
                                                                        (3, '김광현', '1루수', now()),
                                                                        (3, '추신수', '2루수', now()),
                                                                        (3, '기예르모 에레디아', '3루수', now()),
                                                                        (3, '최정', '포수', now()),
                                                                        (3, '김건용', '투수', now()),
                                                                        (3, '최상민', '유격수', now()),
                                                                        (3, '최지훈', '중견수', now()),
                                                                        (3, '이재원', '우익수', now()),
                                                                        (3, '최주환', '좌익수', now());
