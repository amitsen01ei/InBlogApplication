CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(256) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `role` int(11) NOT NULL,
  `active` int(1) NOT NULL DEFAULT 0,
  `created_on` datetime DEFAULT NULL,
  `time_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `Uk_email` (`email`),
  UNIQUE KEY `Uk_username` (`username`)
)Engine=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `blog_request` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL,
  `subtitle` varchar(500) DEFAULT NULL,
  `body` text NOT NULL,
  `approved_by` varchar(256) DEFAULT NULL,
  `submitted_by` varchar(100) NOT NULL,
  `approval_status` int(11) NOT NULL,
  `is_deleted` int(1) NOT NULL DEFAULT 0,
  `created_on` datetime DEFAULT NULL,
  `time_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`post_id`),
  UNIQUE KEY `Uk_submitted_by` (`submitted_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;