# TABELA User

```sql**
CREATE TABLE User (
    usr_id INT AUTO_INCREMENT PRIMARY KEY,          
    usr_name VARCHAR(255) NOT NULL,                  
    usr_email VARCHAR(255) NOT NULL,                
    usr_password VARCHAR(255) NOT NULL,             
    usr_date_registered TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  
    usr_image VARCHAR(255) -- Adicionando a coluna de imagem
);**
# TABELA Animal

```sql**
CREATE TABLE Animal (
    ani_id INT AUTO_INCREMENT PRIMARY KEY,          
    ani_name VARCHAR(255) NOT NULL,                  
    ani_birthdate DATE NOT NULL,                    
    ani_gender CHAR(1) CHECK (ani_gender IN ('M', 'F')), 
    ani_description TEXT,                           
    ani_breed VARCHAR(255),                         
    ani_type VARCHAR(255) NOT NULL,
    ani_image VARCHAR(255) -- Adicionando a coluna de imagem
);**
# TABELA Status

```sql**
CREATE TABLE Status (
    sta_id INT AUTO_INCREMENT PRIMARY KEY,          
    sta_ani_id INT,                                 
    sta_first_visit BOOLEAN,                        
    sta_adopter_evaluation BOOLEAN,                 
    sta_documentation BOOLEAN,                      
    sta_adaptation_period BOOLEAN,                  
    sta_adoption_confirmed BOOLEAN,                 
    sta_follow_up BOOLEAN,                          
    sta_adopted BOOLEAN,                            
    sta_update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    sta_event_schedule TIMESTAMP NULL,              
    FOREIGN KEY (sta_ani_id) REFERENCES Animal(ani_id)
);**
# TABELA Donation

```sql**
CREATE TABLE Donation (
    don_id INT AUTO_INCREMENT PRIMARY KEY,
    don_amount DECIMAL(10, 2),
    don_payment_method VARCHAR(50) NOT NULL,
    don_date DATE NOT NULL,
    don_usr_id INT NOT NULL,
    don_typ_name VARCHAR(50) NOT NULL,
    FOREIGN KEY (don_usr_id) REFERENCES User(usr_id)
);
# TABELA Favorite

```sql**
CREATE TABLE Favorite (
    fav_id INT AUTO_INCREMENT PRIMARY KEY,
    fav_usr_id INT,
    fav_ani_id INT,
    fav_date DATE,
    fav_usr_name VARCHAR(255),  -- Adicionando o nome do usuário
    fav_ani_name VARCHAR(255),  -- Adicionando o nome do animal
    FOREIGN KEY (fav_usr_id) REFERENCES User(usr_id),
    FOREIGN KEY (fav_ani_id) REFERENCES Animal(ani_id)
);**
# TABELA Loc

```sql**
CREATE TABLE Loc (
    loc_id INT AUTO_INCREMENT PRIMARY KEY,
    loc_name VARCHAR(255) NOT NULL,
    loc_address VARCHAR(255) NOT NULL,
    loc_postal_code VARCHAR(10) NOT NULL
);**
# TABELA Loc_Doa

```sql**
CREATE TABLE Loc_Doa (
    loc_id INT,
    don_id INT,
    usr_id INT,
    PRIMARY KEY (loc_id, don_id, usr_id),
    FOREIGN KEY (loc_id) REFERENCES Loc(loc_id),
    FOREIGN KEY (don_id) REFERENCES Donation(don_id),
    FOREIGN KEY (usr_id) REFERENCES User(usr_id)
);**
# TABELA UA

```sql**
CREATE TABLE UA (
    ua_id INT AUTO_INCREMENT PRIMARY KEY,
    ua_usr_id INT,
    ua_ani_id INT,
    ua_interaction_type ENUM('Favorited', 'Visited', 'Adopted', 'Hour'),
    FOREIGN KEY (ua_usr_id) REFERENCES User(usr_id),
    FOREIGN KEY (ua_ani_id) REFERENCES Animal(ani_id)
);**
# TABELA UAS

```sql**
CREATE TABLE UAS (
    uas_id INT AUTO_INCREMENT PRIMARY KEY,
    uas_usr_id INT,
    uas_ani_id INT,
    uas_sta_id INT,
    uas_stage ENUM('First Visit', 'Adoption Evaluation', 'Documentation', 'Adaptation Period', 'Adoption Confirmed', 'Follow Up'),
    FOREIGN KEY (uas_usr_id) REFERENCES User(usr_id),
    FOREIGN KEY (uas_ani_id) REFERENCES Animal(ani_id),
    FOREIGN KEY (uas_sta_id) REFERENCES Status(sta_id)
);**
# TABELA Visitas

```sql**
CREATE TABLE Visitas (
    visit_id INT AUTO_INCREMENT PRIMARY KEY,
    visit_usr_id INT,
    visit_ani_id INT,
    visit_date DATE,
    visit_time TIME,
    visit_status VARCHAR(255) DEFAULT 'Visita realizada',
    FOREIGN KEY (visit_usr_id) REFERENCES User(usr_id),
    FOREIGN KEY (visit_ani_id) REFERENCES Animal(ani_id)
);**
# TABELA Post

```sql**
CREATE TABLE Post (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    post_usr_id INT,
    post_content TEXT,
    post_image_url VARCHAR(255),
    post_date DATE,
    post_time TIME,
    post_views INT DEFAULT 0,
    FOREIGN KEY (post_usr_id) REFERENCES User(usr_id)
);**
# TABELA Comment

```sql**
CREATE TABLE Comment (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    comment_post_id INT,
    comment_usr_id INT,
    comment_content TEXT,
    comment_date DATE,
    comment_time TIME,
    comment_reply_to INT DEFAULT NULL,  -- Para respostas aos comentários
    FOREIGN KEY (comment_post_id) REFERENCES Post(post_id),
    FOREIGN KEY (comment_usr_id) REFERENCES User(usr_id),
    FOREIGN KEY (comment_reply_to) REFERENCES Comment(comment_id)
);**
# TABELA Notifications

```sql**
CREATE TABLE Notifications (
    notif_id INT AUTO_INCREMENT PRIMARY KEY,
    notif_usr_id INT,
    notif_type ENUM('Visit Reminder', 'Adoption Status Update'),
    notif_description TEXT,
    notif_date DATE,
    notif_time TIME,
    FOREIGN KEY (notif_usr_id) REFERENCES User(usr_id)
);**
