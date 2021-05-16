CREATE TABLE `tournament_user` (
  `tournament_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  KEY `FKt9fakr8pp9ve87l75yyri38p4` (`tournament_id`),
  KEY `FKthcugq4lunxqo32qm5eewfrur` (`user_id`),
  CONSTRAINT `FKt9fakr8pp9ve87l75yyri38p4` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`),
  CONSTRAINT `FKthcugq4lunxqo32qm5eewfrur` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `user_order` (
  `user_id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  KEY `FKjyad9h2pmdy2u7r2vyc48bukm` (`user_id`),
  KEY `FKrtt5rijo65gon4wgqj4pv44hj` (`order_id`),
  CONSTRAINT `FKjyad9h2pmdy2u7r2vyc48bukm` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrtt5rijo65gon4wgqj4pv44hj` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
);

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKhjx9nk20h4mo745tdqj8t8n9d` (`user_id`),
  KEY `FKka3w3atry4amefp94rblb52n7` (`role_id`),
  CONSTRAINT `FKhjx9nk20h4mo745tdqj8t8n9d` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKka3w3atry4amefp94rblb52n7` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
);

CREATE TABLE `user_schedule` (
  `user_id` bigint(20) NOT NULL,
  `schedule_id` bigint(20) NOT NULL,
  KEY `FKtowfjgxnv3o7ehpaupjp2tgg0` (`user_id`),
  KEY `FKn569yd8jxgjtl2kbf4wlntlts` (`schedule_id`),
  CONSTRAINT `FKn569yd8jxgjtl2kbf4wlntlts` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`)  ON DELETE CASCADE,
  CONSTRAINT `FKtowfjgxnv3o7ehpaupjp2tgg0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);