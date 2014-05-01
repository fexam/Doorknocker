CREATE TABLE IF NOT EXISTS `dorms` (
  `dorm_id` int(11) NOT NULL,
  `dorm_name` varchar(50) NOT NULL,
  UNIQUE KEY `dorm_id` (`dorm_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `members` (
  `username` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL DEFAULT '',
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `rooms` (
  `id` int(11) NOT NULL,
  `room_number` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `dorm_id` int(11) NOT NULL,
  `floor_num` int(11) NOT NULL,
  `wing` varchar(5) NOT NULL,
  `date` varchar(10) NOT NULL DEFAULT '',
  `notes` varchar(100) NOT NULL DEFAULT '',
  `state1` int(11) NOT NULL DEFAULT '0',
  `state2` int(11) NOT NULL DEFAULT '0',
  `state3` int(11) NOT NULL DEFAULT '0',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;