INSERT INTO test1.tournament
(address, `date`, name, status)
VALUES('г.Минск, ул.Рабкоровская 17/2', '2021-04-24 00:00:00', 'Открытый Чемпионат ОО БФТ – 2021', 1);

INSERT INTO test1.description
(full_description, short_description)
VALUES('Дорогие друзья! 24  апреля 2021 года в концертном зале спортивно-культурного центра учреждения образования "Белорусский Государственный Университет Культуры и Искусств" (ул. Рабкоровская 17/2) состоятся Открытые Республиканские соревнования по спортивным бальным танцам - "Открытый чемпионат Белорусской федерации танца 2021"! Уютный концертный зал, танцевальный паркет, профессиональное звуковое и световое оборудование и оформление. Регистрация открыта на сайте www.eform.by!!! Ждем Вас на нашем турнире!!! До встречи на паркете!!!' ,'VII Открытый чемпионат Белорусской федерации танца 2021');

UPDATE test1.tournament
SET description_id=1
WHERE id=1;


INSERT INTO test1.tournament
(address, `date`, name, status)
VALUES('г. Минск, ул. Ташкентская, 19 «Чижовка- Арена»', '2021-05-22 00:00:00', 'Открытое первенство СОО «Белорусский республиканский танцевальный союз» по танцевальному искусству', 1);

INSERT INTO test1.description
(full_description, short_description)
VALUES('ЧЕМПИОНАТ И ПЕРВЕНСТВО РЕСПУБЛИКИ БЕЛАРУСЬ ОТКРЫТЫЙ ЧЕМПИОНАТ Г. МИНСКА ПО СПОРТИВНЫМ БАЛЬНЫМ ТАНЦАМ Министерство спорта и туризма Республики Беларусь Главное управление спорта и туризма Мингорисполкома СОО «Белорусский республиканский танцевальный союз» ОО «Городская федерация спортивных танцев»', 'ЧЕМПИОНАТ И ПЕРВЕНСТВО РЕСПУБЛИКИ БЕЛАРУСЬ ОТКРЫТЫЙ ЧЕМПИОНАТ Г. МИНСКА ПО СПОРТИВНЫМ БАЛЬНЫМ ТАНЦАМ');

UPDATE test1.tournament
SET description_id=2
WHERE id=2;

