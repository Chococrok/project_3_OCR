INSERT INTO 
	site (name, lat, long, how_to_find, description)
    VALUES (
    	'Les Gorges de la Save Lespugue',
        43.235676435162915,
        0.6639862060546875,
        'Prendre à gauche sur Saouil/D9. Continuer de suivre D9, Prendre à droite sur D9G. Votre destination se trouvera sur la gauche.',
        'Les gorges de la Save sont un défilé creusé par la Save à travers un pli calcaire du piémont pyrénéen. Elles sont situées près des communes françaises de Montmaurin et de Lespugue, dans la région Occitanie.'
    );
    
INSERT INTO 
	site (name, lat, long, how_to_find, description)
    VALUES (
    	'Saint-Pe-d''Ardet',
        42.99158453566007,
        0.6758522987365723,
        'Prendre à droite sur Avenue Saint-Jean/D8G. Prendre à gauche sur Rue Saint-Roch/D9. Continuer de suivre D9Prendre à droite sur Hameau de Bruncan/D9 (panneaux vers Genos/St Béat/Luchon/Boucou). Continuer de suivre D9',
        'Saint-Pé-d''Ardet est une commune française située dans le département de la Haute-Garonne en région Occitanie. Ses habitants sont appelés les Saint-Péens.'
    );

INSERT INTO 
    comment (site_id, content, time_stamp) 
    VALUES(1,
    'j''ai adoré ce spot. Les voies sont longues mais les prises sont plutôt bonne. Parfait pour un débutant.',
    '1999-01-08 04:05:06');
INSERT INTO 
    comment (site_id, content, time_stamp) 
    VALUES(1, 'A ne pas manquer !',
        '2015-01-08 05:05:06');

INSERT INTO secteur (site_id, name, description) VALUES (1, 'Dunes', 'Un peu sabloneux');
INSERT INTO secteur (site_id, name, description) VALUES (1, 'Doom', 'Un secteur très difficile');
INSERT INTO secteur (site_id, name, description) VALUES (1, 'Desert', 'Peu de grimppeur sont capable d''afronter les voies de ce secteur, d''où son nom.');

INSERT INTO voie (secteur_id, name, length, point_number, cotation, description) VALUES (1, 'Jabba the Hutt', 60, 6, '6b+', 'Proinde concepta rabie saeviore, quam desperatio incendebat et fames, amplificatis viribus ardore incohibili in excidium urbium matris Seleuciae efferebantur, quam comes tuebatur Castricius tresque legiones bellicis sudoribus induratae.');
INSERT INTO voie (secteur_id, name, length, point_number, cotation, description) VALUES (1, 'Théorème', 80, 6, '5a', 'Proinde concepta rabie saeviore, quam desperatio incendebat et fames, amplificatis viribus ardore incohibili in excidium urbium matris Seleuciae efferebantur, quam comes tuebatur Castricius tresque legiones bellicis sudoribus induratae.');
INSERT INTO voie (secteur_id, name, length, point_number, cotation, description) VALUES (1, 'Belle nuit', 120, 6, '8a', 'Proinde concepta rabie saeviore, quam desperatio incendebat et fames, amplificatis viribus ardore incohibili in excidium urbium matris Seleuciae efferebantur, quam comes tuebatur Castricius tresque legiones bellicis sudoribus induratae.');
INSERT INTO voie (secteur_id, name, length, point_number, cotation, description) VALUES (1, 'Liane folle', 90, 6, '7b', 'Proinde concepta rabie saeviore, quam desperatio incendebat et fames, amplificatis viribus ardore incohibili in excidium urbium matris Seleuciae efferebantur, quam comes tuebatur Castricius tresque legiones bellicis sudoribus induratae.');

   