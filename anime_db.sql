-- MySQL dump 10.13  Distrib 8.4.10, for macos15 (arm64)
--
-- Host: localhost    Database: anime_db
-- ------------------------------------------------------
-- Server version	8.4.10

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `anime`
--

DROP TABLE IF EXISTS `anime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anime` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `anime_status` enum('COMPLETED','ONGOING','UPCOMING') DEFAULT NULL,
  `episodes` int NOT NULL,
  `studio` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `watch_status` enum('COMPLETED','DROPPED','PLANNING','WATCHING') DEFAULT NULL,
  `watched_episodes` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anime`
--

LOCK TABLES `anime` WRITE;
/*!40000 ALTER TABLE `anime` DISABLE KEYS */;
INSERT INTO `anime` VALUES (1,'COMPLETED',37,'Madhouse','Death Note','COMPLETED',37),(2,'COMPLETED',220,'Pierrot','Naruto','COMPLETED',220),(3,'COMPLETED',500,'Pierrot','Naruto Shippuden','COMPLETED',500),(4,'ONGOING',63,'Ufotable','Demon Slayer','WATCHING',63),(5,'ONGOING',47,'MAPPA','Jujutsu Kaisen','WATCHING',47),(6,'COMPLETED',12,'A-1 Pictures','Erased','COMPLETED',12),(7,'ONGOING',1172,'Toei Animation','One Piece','WATCHING',1143),(8,'COMPLETED',50,'Liden Films','Tokyo Revengers','COMPLETED',50),(9,'COMPLETED',12,'P.A. Works','Another','COMPLETED',12),(10,'COMPLETED',12,'Madhouse','No Game No Life','COMPLETED',12),(11,'COMPLETED',23,'CloverWorks','The Promised Neverland','COMPLETED',23),(12,'COMPLETED',48,'Pierrot','Tokyo Ghoul','COMPLETED',48),(13,'COMPLETED',24,'LARX Entertainment','Kengan Ashura','COMPLETED',24),(14,'COMPLETED',85,'Production I.G','Haikyuu','COMPLETED',85),(15,'COMPLETED',75,'Production I.G','Kuroko no Basket','COMPLETED',75),(16,'COMPLETED',13,'CloverWorks','Horimiya','COMPLETED',13),(17,'COMPLETED',13,'MAPPA','The God of High School','COMPLETED',13),(18,'COMPLETED',26,'Asread','Future Diary','COMPLETED',26),(19,'COMPLETED',12,'Zero-G','High-Rise Invasion','COMPLETED',12),(20,'ONGOING',38,'Lerche','Classroom of the Elite','WATCHING',38),(21,'COMPLETED',47,'Lerche','Assassination Classroom','COMPLETED',47),(22,'COMPLETED',148,'Madhouse','Hunter x Hunter','COMPLETED',148),(23,'COMPLETED',94,'MAPPA','Attack on Titan','COMPLETED',94),(25,'ONGOING',38,'8bit','Blue Lock','COMPLETED',38),(26,'ONGOING',12,'MAPPA','Chainsaw Man','COMPLETED',12),(27,'COMPLETED',12,'Okuruto Noboru','Tomodachi Game','COMPLETED',12),(28,'COMPLETED',24,'Production I.G','Ao Ashi','COMPLETED',24),(29,'ONGOING',24,'Madhouse / J.C.Staff','One Punch Man','COMPLETED',24),(30,'COMPLETED',24,'MAPPA','Kakegurui','COMPLETED',24),(31,'COMPLETED',61,'Bones','Bungo Stray Dogs','COMPLETED',61),(32,'COMPLETED',22,'A-1 Pictures','Your Lie in April','COMPLETED',22),(33,'COMPLETED',50,'Sunrise','Code Geass','COMPLETED',50),(34,'COMPLETED',64,'Bones','Fullmetal Alchemist: Brotherhood','COMPLETED',64),(35,'ONGOING',13,'MAPPA','Hell\'s Paradise','COMPLETED',13),(36,'ONGOING',170,'Pierrot','Black Clover','COMPLETED',170),(37,'ONGOING',24,'Doga Kobo','Oshi no Ko','COMPLETED',24),(38,'ONGOING',406,'Pierrot','Bleach','COMPLETED',406),(39,'ONGOING',37,'Wit Studio / CloverWorks','Spy x Family','COMPLETED',37);
/*!40000 ALTER TABLE `anime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anime_genres`
--

DROP TABLE IF EXISTS `anime_genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anime_genres` (
  `anime_id` bigint NOT NULL,
  `genres` varchar(255) DEFAULT NULL,
  UNIQUE KEY `UK2s74uua6hhcih0y1eqjohm2ml` (`anime_id`,`genres`),
  CONSTRAINT `FKorrk6ybv1u14qh4bd1tndm125` FOREIGN KEY (`anime_id`) REFERENCES `anime` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anime_genres`
--

LOCK TABLES `anime_genres` WRITE;
/*!40000 ALTER TABLE `anime_genres` DISABLE KEYS */;
INSERT INTO `anime_genres` VALUES (1,'Mystery'),(1,'Psychological'),(1,'Thriller'),(2,'Action'),(2,'Adventure'),(3,'Action'),(3,'Adventure'),(4,'Action'),(4,'Fantasy'),(5,'Action'),(5,'Supernatural'),(6,'Mystery'),(6,'Thriller'),(7,'Action'),(7,'Adventure'),(8,'Action'),(8,'Drama'),(9,'Horror'),(9,'Mystery'),(10,'Comedy'),(10,'Fantasy'),(11,'Fantasy'),(11,'Mystery'),(12,'Action'),(12,'Horror'),(13,'Action'),(13,'Sports'),(14,'Sports'),(15,'Sports'),(16,'Comedy'),(16,'Romance'),(17,'Action'),(17,'Fantasy'),(18,'Action'),(18,'Psychological'),(19,'Action'),(19,'Horror'),(20,'Drama'),(20,'Psychological'),(21,'Action'),(21,'Comedy'),(22,'Action'),(22,'Adventure'),(23,'Action'),(23,'Drama'),(25,'Sports'),(26,'Action'),(26,'Dark Fantasy'),(26,'Supernatural'),(27,'Psychological'),(27,'Thriller'),(28,'Sports'),(29,'Action'),(29,'Comedy'),(29,'Superhero'),(30,'Drama'),(30,'Psychological'),(31,'Action'),(31,'Mystery'),(31,'Supernatural'),(32,'Drama'),(32,'Music'),(32,'Romance'),(33,'Action'),(33,'Drama'),(33,'Mecha'),(34,'Action'),(34,'Adventure'),(34,'Fantasy'),(35,'Action'),(35,'Dark Fantasy'),(35,'Supernatural'),(36,'Action'),(36,'Adventure'),(36,'Fantasy'),(37,'Drama'),(37,'Mystery'),(37,'Supernatural'),(38,'Action'),(38,'Adventure'),(38,'Supernatural'),(39,'Action'),(39,'Comedy'),(39,'Slice of Life');
/*!40000 ALTER TABLE `anime_genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','USER') NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'suhas@gmail.com','$2a$10$U0a7vqZxn48cDR//hG7bq.Q6qdUHO/HWEwvnQYP0zsvkeOljdKCAm','USER','suhas'),(2,'pirateking2910@gmail.com','$2a$10$fYJ9OEHVExLTw.Hm.IFv3usNHruN8zWZ76U3eAdaLyxo/2ZHL6/cG','ADMIN','pirateking');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-08-07 23:06:41
