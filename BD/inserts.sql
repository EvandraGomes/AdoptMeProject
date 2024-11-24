## Tabela `User`
```sql
INSERT INTO User (usr_name, usr_email, usr_password, usr_image) VALUES
('João Silva', 'joao.silva@example.com', 'senha123', 'https://example.com/images/joao.jpg'),
('Maria Souza', 'maria.souza@example.com', 'senha456', 'https://example.com/images/maria.jpg'),
('Pedro Oliveira', 'pedro.oliveira@example.com', 'senha789', 'https://example.com/images/pedro.jpg'),
('Ana Costa', 'ana.costa@example.com', 'senha101', 'https://example.com/images/ana.jpg'),
('Carlos Pereira', 'carlos.pereira@example.com', 'senha202', 'https://example.com/images/carlos.jpg'),
('Luciana Martins', 'luciana.martins@example.com', 'senha303', 'https://example.com/images/luciana.jpg'),
('Marcos Silva', 'marcos.silva@example.com', 'senha404', 'https://example.com/images/marcos.jpg'),
('Fernanda Almeida', 'fernanda.almeida@example.com', 'senha505', 'https://example.com/images/fernanda.jpg'),
('Roberta Dias', 'roberta.dias@example.com', 'senha606', 'https://example.com/images/roberta.jpg'),
('Ricardo Costa', 'ricardo.costa@example.com', 'senha707', 'https://example.com/images/ricardo.jpg'),
('Júlia Oliveira', 'julia.oliveira@example.com', 'senha808', 'https://example.com/images/julia.jpg'),
('Felipe Santos', 'felipe.santos@example.com', 'senha909', 'https://example.com/images/felipe.jpg'),
('Patrícia Rodrigues', 'patricia.rodrigues@example.com', 'senha010', 'https://example.com/images/patricia.jpg'),
('Renato Souza', 'renato.souza@example.com', 'senha111', 'https://example.com/images/renato.jpg'),
('Carla Lima', 'carla.lima@example.com', 'senha212', 'https://example.com/images/carla.jpg');

# SQL Insert Statement for Animal Table

```sql
INSERT INTO Animal (ani_name, ani_birthdate, ani_gender, ani_description, ani_breed, ani_type, ani_image) VALUES
    ('Rex', '2020-01-01', 'M', 'Cão amigável e cheio de energia', 'SRD', 'cao', 'https://example.com/images/rex.jpg'),
    ('Luna', '2021-02-15', 'F', 'Gata calma e independente', 'SRD', 'gato', 'https://example.com/images/luna.jpg'),
    ('Max', '2018-03-20', 'M', 'Cão protetor e leal', 'SRD', 'cao', 'https://example.com/images/max.jpg'),
    ('Bella', '2019-04-10', 'F', 'Cão brincalhona e amorosa', 'SRD', 'cao', 'https://example.com/images/bella.jpg'),
    ('Simba', '2017-05-25', 'M', 'Gato curioso e destemido', 'SRD', 'gato', 'https://example.com/images/simba.jpg'),
    ('Toby', '2022-06-30', 'M', 'Cão tranquilo e fiel', 'SRD', 'cao', 'https://example.com/images/toby.jpg'),
    ('Maya', '2019-11-20', 'F', 'Gata energética e carinhosa', 'Siamês', 'gato', 'https://example.com/images/maya.jpg'),
    ('Coco', '2020-12-05', 'M', 'Cão alegre e sociável', 'SRD', 'cao', 'https://example.com/images/coco.jpg'),
    ('Pipoca', '2021-01-10', 'F', 'Gata doce e brincalhona', 'SRD', 'gato', 'https://example.com/images/pipoca.jpg'),
    ('Chico', '2018-07-15', 'M', 'Cão protetor e brincalhão', 'SRD', 'cao', 'https://example.com/images/chico.jpg'),
    ('Rosa', '2021-04-25', 'F', 'Gata tranquila e afetuosa', 'Persa', 'gato', 'https://example.com/images/rosa.jpg'),
    ('Zé', '2016-08-05', 'M', 'Cão carinhoso e leal', 'SRD', 'cao', 'https://example.com/images/ze.jpg'),
    ('Lili', '2019-03-10', 'F', 'Gata divertida e sociável', 'SRD', 'gato', 'https://example.com/images/lili.jpg'),
    ('Nina', '2020-11-15', 'F', 'Cão muito ativo e brincalhão', 'SRD', 'cao', 'https://example.com/images/nina.jpg'),
    ('Freddy', '2018-05-22', 'M', 'Gato independente e esperto', 'Bengal', 'gato', 'https://example.com/images/freddy.jpg'),
    ('Marley', '2021-09-30', 'M', 'Cão amigável e protetor', 'SRD', 'cao', 'https://example.com/images/marley.jpg'),
    ('Olivia', '2022-10-17', 'F', 'Gata muito carinhosa e tranquila', 'Maine Coon', 'gato', 'https://example.com/images/olivia.jpg'),
    ('Fiona', '2021-12-03', 'F', 'Cão adorável e sociável', 'SRD', 'cao', 'https://example.com/images/fiona.jpg'),
    ('Rocky', '2019-02-20', 'M', 'Cão brincalhão e protetor', 'SRD', 'cao', 'https://example.com/images/rocky.jpg');

# SQL Insert Statement for Status Table

```sql
INSERT INTO Status (sta_ani_id, sta_first_visit, sta_adopter_evaluation, sta_documentation, sta_adaptation_period, sta_adoption_confirmed, sta_follow_up, sta_adopted, sta_update_date, sta_event_schedule) VALUES
    (1, TRUE, TRUE, TRUE, FALSE, FALSE, FALSE, FALSE, '2023-10-28', '2023-11-05 10:00:00'),
    (2, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, '2023-09-15', '2023-11-06 14:30:00'),
    (3, FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, FALSE, '2023-08-05', '2023-11-07 09:00:00');


# SQL Insert Statement for Donation Table

```sql
INSERT INTO Donation (don_amount, don_payment_method, don_date, don_usr_id, don_typ_name) VALUES
    (100.00, 'Cartão de Crédito', '2023-11-01', 1, 'Financeira'),
    (NULL, 'MBWay', '2023-10-15', 2, 'Material'),
    (NULL, 'Dinheiro', '2023-09-10', 3, 'Alimentos'),
    (NULL, 'Transferência', '2023-08-05', 4, 'Medicamentos'),
    (200.00, 'MBWay', '2023-11-02', 5, 'Financeira'),
    (NULL, 'Cartão de Crédito', '2023-11-03', 6, 'Material'),
    (NULL, 'Dinheiro', '2023-10-10', 7, 'Alimentos'),
    (NULL, 'Transferência', '2023-09-05', 8, 'Medicamentos');

# SQL Insert Statement for Favorite Table

```sql
INSERT INTO Favorite (fav_usr_id, fav_ani_id, fav_date, fav_usr_name, fav_ani_name) VALUES
    (1, 2, '2023-08-10', 'João Silva', 'Luna'),
    (2, 1, '2023-09-01', 'Maria Souza', 'Rex'),
    (3, 3, '2023-07-25', 'Pedro Oliveira', 'Max'),
    (4, 5, '2023-08-15', 'Ana Costa', 'Simba'),
    (5, 6, '2023-09-10', 'Carlos Pereira', 'Toby'),
    (6, 7, '2023-09-15', 'Luciana Martins', 'Maya'),
    (7, 8, '2023-10-05', 'Marcos Silva', 'Coco'),
    (8, 9, '2023-10-15', 'Fernanda Almeida', 'Pipoca'),
    (9, 10, '2023-11-05', 'Roberta Dias', 'Chico'),
    (10, 11, '2023-11-01', 'Ricardo Costa', 'Rosa');

# SQL Insert Statement for Loc Table

```sql
INSERT INTO Loc (loc_name, loc_address, loc_postal_code) VALUES
    ('Ponto de Doação A', 'Avenida da Liberdade, Lisboa', '1250-000'),
    ('Ponto de Doação B', 'Rua da Misericórdia, Lisboa', '1200-270'),
    ('Ponto de Doação C', 'Rua do Conde Redondo, Lisboa', '1000-163'),
    ('Ponto de Doação D', 'Rua de São Bento, Lisboa', '1200-819'),
    ('Ponto de Doação E', 'Praça do Comércio, Lisboa', '1100-148'),
    ('Ponto de Doação F', 'Avenida da República, Lisboa', '1050-161'),
    ('Ponto de Doação G', 'Rua das Janelas Verdes, Lisboa', '1200-691'),
    ('Ponto de Doação H', 'Avenida Magalhães Lima, Lisboa', '1000-165'),
    ('Ponto de Doação I', 'Rua Almirante Reis, Lisboa', '1000-004'),
    ('Ponto de Doação J', 'Avenida 24 de Julho, Lisboa', '1200-870'),
    ('Ponto de Doação K', 'Rua do Sol a Santa Catarina, Lisboa', '1200-440'),
    ('Ponto de Doação L', 'Rua do Duque de Loulé, Lisboa', '1050-098'),
    ('Ponto de Doação M', 'Rua da Alegria, Lisboa', '1200-329'),
    ('Ponto de Doação N', 'Rua da Liberdade, Lisboa', '1250-062'),
    ('Ponto de Doação O', 'Avenida João XXI, Lisboa', '1000-160'),
    ('Ponto de Doação P', 'Praça dos Restauradores, Lisboa', '1250-001'),
    ('Ponto de Doação Q', 'Rua da Rosa, Lisboa', '1200-348'),
    ('Ponto de Doação R', 'Rua da Bica do Sapato, Lisboa', '1200-048'),
    ('Ponto de Doação S', 'Rua do Bairro Alto, Lisboa', '1200-004'),
    ('Ponto de Doação T', 'Rua da Palma, Lisboa', '1100-393'),
    ('Ponto de Doação U', 'Avenida Dom Carlos I, Lisboa', '1200-141'),
    ('Ponto de Doação V', 'Rua Nova da Trindade, Lisboa', '1200-302'),
    ('Ponto de Doação W', 'Rua da Prata, Lisboa', '1100-420'),
    ('Ponto de Doação X', 'Rua do Paraíso, Lisboa', '1150-385');

# SQL Insert Statement for Loc_Doa Table

```sql
INSERT INTO Loc_Doa (loc_id, don_id, usr_id) VALUES
    (1, 1, 3), -- Doação feita pelo usuário 3 no ponto 1
    (2, 2, 4), -- Doação feita pelo usuário 4 no ponto 2
    (3, 3, 5), -- Doação feita pelo usuário 5 no ponto 3
    (4, 4, 6), -- Doação feita pelo usuário 6 no ponto 4
    (5, 5, 7), -- Doação feita pelo usuário 7 no ponto 5
    (6, 6, 8); -- Doação feita pelo usuário 8 no ponto 6

# SQL Insert Statement for UA Table

```sql
INSERT INTO UA (ua_usr_id, ua_ani_id, ua_interaction_type) VALUES
    (1, 2, 'Favorited'),
    (2, 1, 'Visited');

# SQL Insert Statement for UAS Table

```sql
INSERT INTO UAS (uas_usr_id, uas_ani_id, uas_sta_id, uas_stage) VALUES
    (1, 2, 1, 'First Visit'),
    (2, 1, 2, 'Adoption Evaluation');

# SQL Insert Statement for Visitas Table

```sql
INSERT INTO Visitas (visit_usr_id, visit_ani_id, visit_date, visit_time) VALUES
    (1, 2, '2023-11-10', '10:00:00'),
    (2, 1, '2023-11-11', '11:30:00'),
    (3, 3, '2023-11-12', '14:00:00');

# SQL Insert Statement for Post Table

```sql
INSERT INTO Post (post_usr_id, post_content, post_image_url, post_date, post_time) VALUES
    (1, 'Visitamos o Rex! Ele é incrível!', 'https://example.com/images/rex-post.jpg', '2023-11-01', '12:00:00'),
    (2, 'A Luna é super fofa, adoramos nossa visita!', 'https://example.com/images/luna-post.jpg', '2023-11-02', '13:30:00'),
    (3, 'Max é super protetor, muito amoroso!', 'https://example.com/images/max-post.jpg', '2023-11-03', '15:00:00');

# SQL Insert Statement for Comment Table

```sql
INSERT INTO Comment (comment_post_id, comment_usr_id, comment_content, comment_date, comment_time) VALUES
    (1, 2, 'Concordo! O Rex é um amor de cão.', '2023-11-01', '12:30:00'),
    (2, 1, 'A Luna é muito carinhosa!', '2023-11-02', '14:00:00'),
    (3, 4, 'O Max é maravilhoso! Adoramos nossa visita.', '2023-11-03', '15:30:00');

# SQL Insert Statement for Notifications Table

```sql
INSERT INTO Notifications (notif_usr_id, notif_type, notif_description, notif_date, notif_time) VALUES
    (1, 'Visit Reminder', 'Lembrete: Sua visita a Luna está agendada para amanhã.', '2023-11-05', '09:00:00'),
    (2, 'Follow-up Reminder', 'Lembrete: O Rex está aguardando seu retorno para a visita de avaliação.', '2023-11-06', '10:30:00');
