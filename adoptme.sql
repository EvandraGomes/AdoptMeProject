-- MySQL dump 10.13  Distrib 5.7.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: adoptme
-- ------------------------------------------------------
-- Server version	5.7.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animal` (
  `ani_id` int(11) NOT NULL AUTO_INCREMENT,
  `ani_name` varchar(255) NOT NULL,
  `ani_birthdate` date NOT NULL,
  `ani_gender` char(1) DEFAULT NULL,
  `ani_description` text,
  `ani_breed` varchar(255) DEFAULT NULL,
  `ani_type` varchar(255) NOT NULL,
  PRIMARY KEY (`ani_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal`
--

LOCK TABLES `animal` WRITE;
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` VALUES (1,'Rex','2020-01-01','M','Cão amigável e cheio de energia','SRD','cao'),(2,'Luna','2021-02-15','F','Gata calma e independente','SRD','gato'),(3,'Max','2018-03-20','M','Cão protetor e leal','SRD','cao'),(4,'Bella','2019-04-10','F','Cão brincalhona e amorosa','SRD','cao'),(5,'Simba','2017-05-25','M','Gato curioso e destemido','SRD','gato'),(6,'Charlie','2019-06-12','M','Papagaio falante e esperto','Calopsita','passaro'),(7,'Lili','2020-07-08','F','Coelha carinhosa e fofinha','SRD','coelho'),(8,'Bento','2019-08-23','M','Gato tranquilo e observador','SRD','gato'),(9,'Dora','2018-11-05','F','Cão fiel e inteligente','SRD','cao'),(10,'Fred','2022-01-15','M','Piriquito colorido e alegre','SRD','passaro'),(11,'Mia','2021-03-18','F','Gata ágil e brincalhona','SRD','gata'),(12,'Pipoca','2022-04-21','M','Coelho saltitante e curioso','SRD','coelho'),(13,'Tina','2019-12-30','F','Cão dócil e protetora','SRD','cao'),(14,'Romeu','2018-02-14','M','Papagaio apaixonado e esperto','Calopsita','passaro'),(15,'Fofinha','2021-07-22','F','Coelha amigável e tranquila','SRD','coelho'),(16,'Oscar','2017-09-11','M','Cão corajoso e leal','SRD','cao'),(17,'Sasha','2020-10-31','F','Gata elegante e astuta','SRD','gato');
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donation`
--

DROP TABLE IF EXISTS `donation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donation` (
  `don_id` int(11) NOT NULL AUTO_INCREMENT,
  `don_typ_id` int(11) DEFAULT NULL,
  `don_amount` decimal(10,2) DEFAULT NULL,
  `don_payment_method` varchar(255) DEFAULT NULL,
  `don_date` date DEFAULT NULL,
  `don_usr_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`don_id`),
  KEY `don_usr_id` (`don_usr_id`),
  KEY `don_typ_id` (`don_typ_id`),
  CONSTRAINT `donation_ibfk_1` FOREIGN KEY (`don_usr_id`) REFERENCES `user` (`usr_id`),
  CONSTRAINT `donation_ibfk_2` FOREIGN KEY (`don_typ_id`) REFERENCES `donationtype` (`don_typ_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donation`
--

LOCK TABLES `donation` WRITE;
/*!40000 ALTER TABLE `donation` DISABLE KEYS */;
INSERT INTO `donation` VALUES (1,1,100.00,'Cartão de Crédito','2023-11-01',1),(2,2,50.00,'Pix','2023-10-15',2),(3,3,20.00,'Dinheiro','2023-09-10',3),(4,4,150.00,'Transferência','2023-08-05',4),(5,5,10.00,'Cartão de Débito','2023-07-20',5);
/*!40000 ALTER TABLE `donation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donationtype`
--

DROP TABLE IF EXISTS `donationtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donationtype` (
  `don_typ_id` int(11) NOT NULL AUTO_INCREMENT,
  `don_typ_name` varchar(255) NOT NULL,
  PRIMARY KEY (`don_typ_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donationtype`
--

LOCK TABLES `donationtype` WRITE;
/*!40000 ALTER TABLE `donationtype` DISABLE KEYS */;
INSERT INTO `donationtype` VALUES (1,'Financeira'),(2,'Material'),(3,'Alimentos'),(4,'Medicamentos'),(5,'Roupas'),(6,'Brinquedos'),(7,'Acessórios'),(8,'Veterinário'),(9,'Outros');
/*!40000 ALTER TABLE `donationtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite` (
  `fav_id` int(11) NOT NULL AUTO_INCREMENT,
  `fav_usr_id` int(11) DEFAULT NULL,
  `fav_ani_id` int(11) DEFAULT NULL,
  `fav_date` date DEFAULT NULL,
  PRIMARY KEY (`fav_id`),
  KEY `fav_usr_id` (`fav_usr_id`),
  KEY `fav_ani_id` (`fav_ani_id`),
  CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`fav_usr_id`) REFERENCES `user` (`usr_id`),
  CONSTRAINT `favorite_ibfk_2` FOREIGN KEY (`fav_ani_id`) REFERENCES `animal` (`ani_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

LOCK TABLES `favorite` WRITE;
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
INSERT INTO `favorite` VALUES (1,1,2,'2023-08-10'),(2,2,1,'2023-09-01'),(3,3,3,'2023-07-25'),(4,4,4,'2023-08-30'),(5,5,5,'2023-09-15');
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `sta_id` int(11) NOT NULL AUTO_INCREMENT,
  `sta_ani_id` int(11) DEFAULT NULL,
  `sta_first_visit` tinyint(1) DEFAULT NULL,
  `sta_adopter_evaluation` tinyint(1) DEFAULT NULL,
  `sta_documentation` tinyint(1) DEFAULT NULL,
  `sta_adaptation_period` tinyint(1) DEFAULT NULL,
  `sta_adoption_confirmed` tinyint(1) DEFAULT NULL,
  `sta_follow_up` tinyint(1) DEFAULT NULL,
  `sta_adopted` tinyint(1) DEFAULT NULL,
  `sta_update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sta_event_schedule` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sta_id`),
  KEY `sta_ani_id` (`sta_ani_id`),
  CONSTRAINT `status_ibfk_1` FOREIGN KEY (`sta_ani_id`) REFERENCES `animal` (`ani_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,1,1,1,1,0,0,0,0,'2023-10-27 23:00:00','2023-11-05 10:00:00'),(2,2,1,1,1,1,1,1,1,'2023-09-14 23:00:00','2023-11-06 14:30:00'),(3,3,0,1,0,0,0,0,0,'2023-08-04 23:00:00','2023-11-07 09:00:00'),(4,4,1,1,1,1,0,0,0,'2023-07-19 23:00:00','2023-11-08 15:45:00'),(5,5,1,1,1,0,0,0,0,'2023-06-14 23:00:00','2023-11-09 11:30:00');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `usr_id` int(11) NOT NULL AUTO_INCREMENT,
  `usr_name` varchar(255) NOT NULL,
  `usr_email` varchar(255) NOT NULL,
  `usr_password` varchar(255) NOT NULL,
  `usr_date_registered` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'João Silva','joao.silva@example.com','senha123','2024-11-21 01:19:15'),(2,'Maria Souza','maria.souza@example.com','senha456','2024-11-21 01:19:15'),(3,'Pedro Oliveira','pedro.oliveira@example.com','senha789','2024-11-21 01:19:15'),(4,'Ana Costa','ana.costa@example.com','senha101','2024-11-21 01:19:15'),(5,'Carlos Pereira','carlos.pereira@example.com','senha202','2024-11-21 01:19:15'),(6,'Juliana Almeida','juliana.almeida@example.com','senha303','2024-11-21 01:19:15'),(7,'Rafael Lima','rafael.lima@example.com','senha404','2024-11-21 01:19:15'),(8,'Fernanda Rocha','fernanda.rocha@example.com','senha505','2024-11-21 01:19:15'),(9,'Gustavo Martins','gustavo.martins@example.com','senha606','2024-11-21 01:19:15'),(10,'Patricia Santos','patricia.santos@example.com','senha707','2024-11-21 01:19:15'),(11,'Lucas Gomes','lucas.gomes@example.com','senha808','2024-11-21 01:19:15'),(12,'Juliana Silva','juliana.silva@example.com','senha909','2024-11-21 01:19:15'),(13,'Renato Ribeiro','renato.ribeiro@example.com','senha010','2024-11-21 01:19:15'),(14,'Carla Mendes','carla.mendes@example.com','senha111','2024-11-21 01:19:15'),(15,'Eduardo Almeida','eduardo.almeida@example.com','senha212','2024-11-21 01:19:15'),(16,'Roberta Martins','roberta.martins@example.com','senha313','2024-11-21 01:19:15'),(17,'Daniela Costa','daniela.costa@example.com','senha414','2024-11-21 01:19:15'),(18,'Fábio Oliveira','fabio.oliveira@example.com','senha515','2024-11-21 01:19:15'),(19,'Simone Pereira','simone.pereira@example.com','senha616','2024-11-21 01:19:15');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-24 14:53:56
