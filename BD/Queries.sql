# ➤ QUERIES.SQL

##Consultas para o Usuário (User):

**Chamar usuários registrados**

SELECT usr_id, usr_name, usr_email, usr_image, usr_date_registered
FROM User;

**Chamar usuário e respectivas doações:**

SELECT u.usr_name, u.usr_email, d.don_amount, d.don_payment_method, d.don_date, d.don_typ_name
FROM User u
JOIN Donation d ON u.usr_id = d.don_usr_id
ORDER BY d.don_date DESC;


**Chamar todos os animais que o usuário favoritou:**

SELECT u.usr_name, a.ani_name, f.fav_date
FROM User u
JOIN Favorite f ON u.usr_id = f.fav_usr_id
JOIN Animal a ON f.fav_ani_id = a.ani_id
WHERE u.usr_id = ?;

**Chamar o status de adoção de um animal (relacionado ao usuário)**

SELECT u.usr_name, a.ani_name, s.sta_first_visit, s.sta_adopter_evaluation, s.sta_adoption_confirmed, s.sta_adapted
FROM User u
JOIN UAS uas ON u.usr_id = uas.uas_usr_id
JOIN Animal a ON uas.uas_ani_id = a.ani_id
JOIN Status s ON uas.uas_sta_id = s.sta_id
WHERE u.usr_id = ? AND a.ani_id = ?;

**Verificar as visitas feitas por um usuário aos animais:**

SELECT u.usr_name, a.ani_name, v.visit_date, v.visit_time
FROM User u
JOIN Visitas v ON u.usr_id = v.visit_usr_id
JOIN Animal a ON v.visit_ani_id = a.ani_id
WHERE u.usr_id = ?;


##Consultas para o Animal:

**Chamar todos os animais:**

SELECT ani_id, ani_name, ani_type, ani_breed, ani_gender, ani_birthdate, ani_description, ani_image
FROM Animal;

**Chamar o status de um animal:**

SELECT a.ani_name, s.sta_first_visit, s.sta_adopter_evaluation, s.sta_adoption_confirmed, s.sta_follow_up
FROM Animal a
JOIN Status s ON a.ani_id = s.sta_ani_id
WHERE a.ani_id = ?;

**Chamar todas as doações relacionadas a um animal (Loc_Doa):**

SELECT a.ani_name, l.loc_name, l.loc_address, l.loc_postal_code, d.don_amount, d.don_payment_method, d.don_date
FROM Animal a
JOIN Status s ON a.ani_id = s.sta_ani_id
JOIN Loc_Doa ld ON s.sta_ani_id = ld.loc_id
JOIN Loc l ON ld.loc_id = l.loc_id
JOIN Donation d ON ld.don_id = d.don_id
WHERE a.ani_id = ?;

##Consultas para Doação (Donation);

**Chamar todas as doações feitas em um ponto de doação:**

SELECT l.loc_name, l.loc_address, d.don_amount, d.don_payment_method, d.don_date, u.usr_name
FROM Loc l
JOIN Loc_Doa ld ON l.loc_id = ld.loc_id
JOIN Donation d ON ld.don_id = d.don_id
JOIN User u ON d.don_usr_id = u.usr_id
WHERE l.loc_id = ?;

## Consultas para Ponto de Doação (Loc)

**Chamar todos os pontos de doação:**

SELECT loc_id, loc_name, loc_address, loc_postal_code
FROM Loc;

**Chamar doações em um ponto específico:**

SELECT d.don_amount, d.don_payment_method, d.don_date, u.usr_name
FROM Loc l
JOIN Loc_Doa ld ON l.loc_id = ld.loc_id
JOIN Donation d ON ld.don_id = d.don_id
JOIN User u ON d.don_usr_id = u.usr_id
WHERE l.loc_id = ?;

##Consultas para Favoritos (Favorite)

**Chamar todos os animais favoritos de um usuário:**

SELECT f.fav_date, a.ani_name, a.ani_image
FROM Favorite f
JOIN Animal a ON f.fav_ani_id = a.ani_id
WHERE f.fav_usr_id = ?;

##Consultas para Visitas

**Verificar visitas agendadas:**

SELECT v.visit_date, v.visit_time, a.ani_name
FROM Visitas v
JOIN Animal a ON v.visit_ani_id = a.ani_id
WHERE v.visit_usr_id = ?;

**Adicionar uma nova visita (para um usuário e animal específico): (talvez fosse melhor no populate)**

INSERT INTO Visitas (visit_usr_id, visit_ani_id, visit_date, visit_time)
VALUES (?, ?, ?, ?);

##Consultas para Postagens (Post)

**Chamar todas as postagens feitas por um usuário:**

SELECT p.post_content, p.post_image_url, p.post_date, p.post_time
FROM Post p
WHERE p.post_usr_id = ?;

##Consultas para Comentários (Comment)

**Chamar todos os comentários em uma postagem:**

SELECT c.comment_content, c.comment_date, c.comment_time, u.usr_name
FROM Comment c
JOIN User u ON c.comment_usr_id = u.usr_id
WHERE c.comment_post_id = ?;

**Adicionar um comentário em uma postagem:**

INSERT INTO Comment (comment_post_id, comment_usr_id, comment_content, comment_date, comment_time)
VALUES (?, ?, ?, ?, ?);

##Consultas para Notificações (Notifications)

**Chamar notificações para um usuário:**

SELECT n.notif_type, n.notif_description, n.notif_date, n.notif_time
FROM Notifications n
WHERE n.notif_usr_id = ?;
