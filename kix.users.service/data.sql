-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: kix_user_service
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `zip_code` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,NULL,NULL,NULL,0),(2,NULL,NULL,NULL,0),(3,NULL,NULL,NULL,0),(4,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1),(2);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_products`
--

DROP TABLE IF EXISTS `cart_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_products` (
  `cart_cart_id` bigint NOT NULL,
  `products` varchar(255) DEFAULT NULL,
  KEY `FKewu4olnfvfyd6wfdix1s4gdtr` (`cart_cart_id`),
  CONSTRAINT `FKewu4olnfvfyd6wfdix1s4gdtr` FOREIGN KEY (`cart_cart_id`) REFERENCES `cart` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_products`
--

LOCK TABLES `cart_products` WRITE;
/*!40000 ALTER TABLE `cart_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `cart_cart_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FKddefmvbrws3hvl5t0hnnsv8ox` (`address_id`),
  KEY `FKak34gd6gbtvduhkfsmkh9nqy4` (`cart_cart_id`),
  CONSTRAINT `FKak34gd6gbtvduhkfsmkh9nqy4` FOREIGN KEY (`cart_cart_id`) REFERENCES `cart` (`cart_id`),
  CONSTRAINT `FKddefmvbrws3hvl5t0hnnsv8ox` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@admin.com','Casitas','Armando','$2a$10$kXILqRX54f03MjhnLfh/MuLCWZUHKEh1ecS1NJlw6T00viWlbhBf6',0,3,1),(2,'sergio@santos.com','Santos','Sergio','$2a$10$muZRgtDnkOxBryoeNqc2K.MnZyNBsR0d7r7PF23ibOjo8S8ra7t6y',1,4,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_payments`
--

DROP TABLE IF EXISTS `user_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_payments` (
  `user_id` bigint NOT NULL,
  `payments` bigint DEFAULT NULL,
  KEY `FKcertxr5jqjh208h6busad5lqc` (`user_id`),
  CONSTRAINT `FKcertxr5jqjh208h6busad5lqc` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_payments`
--

LOCK TABLES `user_payments` WRITE;
/*!40000 ALTER TABLE `user_payments` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wish_list`
--

DROP TABLE IF EXISTS `wish_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wish_list` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8462y9kc76hpxuom1ui7dvp7k` (`user_id`),
  CONSTRAINT `FK8462y9kc76hpxuom1ui7dvp7k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wish_list`
--

LOCK TABLES `wish_list` WRITE;
/*!40000 ALTER TABLE `wish_list` DISABLE KEYS */;
INSERT INTO `wish_list` VALUES (5,'2020-07-24','Lorem ipsum',2);
/*!40000 ALTER TABLE `wish_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wish_list_products`
--

DROP TABLE IF EXISTS `wish_list_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wish_list_products` (
  `wish_list_id` bigint NOT NULL,
  `products` varchar(255) DEFAULT NULL,
  KEY `FKemcyikw15sfq3dcg689e29it1` (`wish_list_id`),
  CONSTRAINT `FKemcyikw15sfq3dcg689e29it1` FOREIGN KEY (`wish_list_id`) REFERENCES `wish_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wish_list_products`
--

LOCK TABLES `wish_list_products` WRITE;
/*!40000 ALTER TABLE `wish_list_products` DISABLE KEYS */;
INSERT INTO `wish_list_products` VALUES (5,'5f1b28ec7c4d182bb582ec70'),(5,'5f1b29217c4d182bb582ec71'),(5,'5f1b28c47c4d182bb582ec6f'),(5,'5f1b29db7c4d182bb582ec75');
/*!40000 ALTER TABLE `wish_list_products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-24 23:38:01
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: kix_image_service
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (146),(146);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `image_id` bigint NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `gallery_gallery_id` bigint DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `FK6i17lqqdbyem8lktysj28mdg1` (`gallery_gallery_id`),
  CONSTRAINT `FK6i17lqqdbyem8lktysj28mdg1` FOREIGN KEY (`gallery_gallery_id`) REFERENCES `image_gallery` (`gallery_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (42,'https://storage.googleapis.com/kix-products-storage/424416dbda08b22e938d0fe74667f6ab8c8212f5da92a252008e17fe8b535b11.jpg',43),(44,'https://storage.googleapis.com/kix-products-storage/992ea08fe5e86c5dee47fff3ae6229b00a5bf6cc9f506d549de9dce750d80485.jpg',43),(45,'https://storage.googleapis.com/kix-products-storage/f58bde7147d127e730ed1b36d0bcb125d1951db08aa1b34286652e70a65f9c13.jpg',43),(46,'https://storage.googleapis.com/kix-products-storage/3ab78d28608867ebecebc0a1c71f82c9c3e668fdacbd47bb5a97d75833c6f75f.jpg',43),(47,'https://storage.googleapis.com/kix-products-storage/da668cf68df6789e6f2cd1c516c513abc647b61b2f711400eb096138367713a7.jpg',48),(49,'https://storage.googleapis.com/kix-products-storage/fefb69fbb3190d512ff623163ff9c003433515fe083160e270d2ae0d3be307fd.jpg',48),(50,'https://storage.googleapis.com/kix-products-storage/2344cf81b9c174113b47b6879db2f02a0d836271da324e6c306ca2c3a0873243.jpg',48),(51,'https://storage.googleapis.com/kix-products-storage/bfd6ab63de79aa8c93bfa442dd117fe935245f73124c0fb06aeffa6648441b0a.jpg',48),(52,'https://storage.googleapis.com/kix-products-storage/aa112cca40ff34b86b1167c4a717752afc7255d8eec5c0409ffe553ff617c394.jpg',48),(53,'https://storage.googleapis.com/kix-products-storage/237ecefa2ca7e2fef6c4a57cc6326f3cc6a0be5bdb4c7878bd2257c3a7337c40.jpg',54),(55,'https://storage.googleapis.com/kix-products-storage/f3791a1ef611ef9e8df97cb948eefdca366c960b147360816198fd78219f02f5.jpg',54),(56,'https://storage.googleapis.com/kix-products-storage/104b78afd29c7e782de92bad86c4ac859361d2d6b55609745259b05a147ab794.jpg',54),(57,'https://storage.googleapis.com/kix-products-storage/c16417a892297f61b9159dd00ade72e6b4c40a1af379cc2788ddd919e6533caa.jpg',54),(58,'https://storage.googleapis.com/kix-products-storage/7eb3d55a570d31ddb5d6b485bf029bd8d78dc0fd0521a57f33ace7559539960b.jpg',54),(59,'https://storage.googleapis.com/kix-products-storage/1413d8825f06d1f5a5295e3f08c3ad67c7ed9c3a49a5af324c4b12a48bb941a6.jpg',54),(60,'https://storage.googleapis.com/kix-products-storage/77eec6c0b9209ed82c3362532c49d8b8237022b1f6ed09db1865ea28b349f105.jpg',61),(62,'https://storage.googleapis.com/kix-products-storage/ba6b5eea60a255f152c4f08805688f9271eb17c141ab38dea7a72c8c908d74da.jpg',61),(63,'https://storage.googleapis.com/kix-products-storage/ae177239d42d7fb0d4cf03c3dfb16e4d4a6d47f7323313eb92422bd39627c0b2.jpg',61),(64,'https://storage.googleapis.com/kix-products-storage/94bfce40a88cf6fdcee8a9f180285aa351c3baae3e7ea3dd702d468f828c8193.jpg',61),(65,'https://storage.googleapis.com/kix-products-storage/3df9beb6bd1568a32fa3003147f4f26197a0d81898962ba8bd5961aa6f4eedc6.jpg',61),(66,'https://storage.googleapis.com/kix-products-storage/453b24f519b9bc99c5dd13ce50414d06480b033ad5b54b0f31d44b97dfdd919b.jpg',67),(68,'https://storage.googleapis.com/kix-products-storage/bf8f7837c6e4209fb2d0736622e055a5bd101779758ae80f81d06c88f22dd09e.jpg',67),(69,'https://storage.googleapis.com/kix-products-storage/259b135101c4b4750fb30e21aff02581efaa606853f05ad729e1103c52c32abd.jpg',67),(70,'https://storage.googleapis.com/kix-products-storage/5b240e47e0746fc1c6d0f51512130165a376690975b88b78ebcecf4981e4899e.jpg',67),(71,'https://storage.googleapis.com/kix-products-storage/40c26e6c75ddcdcef8f890a325a98b3b35b3fa55a1662030fca4a147d3d1b4b0.jpg',72),(73,'https://storage.googleapis.com/kix-products-storage/71215dc64d84159d2054c1b4b3d7418dc21901d54dd4687c223067d09035bdde.jpg',72),(74,'https://storage.googleapis.com/kix-products-storage/6ae925eb54d2c470c5be4d6c934b8e409d87ff5c1790746dd6563a5af68700bf.jpg',72),(75,'https://storage.googleapis.com/kix-products-storage/a5bd1a2e4d328b643f6e5b39d040300ec07d1a8b4f845aa51b0a07e59df6434a.jpg',72),(76,'https://storage.googleapis.com/kix-products-storage/61620cf429af284e2d98cfce5ba900de9e520e69e737fc399278bb57c0bfaff6.jpg',72),(77,'https://storage.googleapis.com/kix-products-storage/b89ef81c0482b44068e12dbdba4477e419ff41d9286a905a049da4d41a82c276.jpg',78),(79,'https://storage.googleapis.com/kix-products-storage/ac419b503505f4f802d5035f381b6ae3be402626df61584a39667113ef68fd41.jpg',78),(80,'https://storage.googleapis.com/kix-products-storage/c006545bfffb3149f092a87f19f1a315f7fee6d67cdb590fc64713159bbfa0fc.jpg',78),(81,'https://storage.googleapis.com/kix-products-storage/7b33c4a5c497f033a0e01c49abe693c75dc92af25c0350bef482ede93e0e57c2.jpg',78),(82,'https://storage.googleapis.com/kix-products-storage/3fbe4ecdb662347040913974936d329e2739599321ec07738dd587e4b63035c5.jpg',78),(83,'https://storage.googleapis.com/kix-products-storage/2c3307155c06fb34301cac253f092e6cb2cf240b935c76de07a1710fc9e36d52.jpg',84),(85,'https://storage.googleapis.com/kix-products-storage/b5dddf768d24635c112e33b8e58a1edd102e600d390d93b2d432ecb6b0f45c7e.jpg',84),(86,'https://storage.googleapis.com/kix-products-storage/221c774e5625c98c7548c418549e92370d6368f056d08d042736fbc1e5fcef4b.jpg',84),(87,'https://storage.googleapis.com/kix-products-storage/828b7b12eef2e2c2981a30dbb9a3b7b2664ee27f8eb31cf939b203efd82cfdb9.jpg',84),(88,'https://storage.googleapis.com/kix-products-storage/50876448c902bcf50936e296df5ed9e620e9c8aa080d3f8b07cb9564d311259b.jpg',89),(90,'https://storage.googleapis.com/kix-products-storage/cf69e3a9583813762b4ee90fdd33227f1c7505eb049b80918610610d2ce7fcf4.jpg',89),(91,'https://storage.googleapis.com/kix-products-storage/972805c2ccf0dbd9ab415e17381dc29dac22f0bd840c748ec84ace294303cc76.jpg',89),(92,'https://storage.googleapis.com/kix-products-storage/dab806adb23786617437b66101a190cada7d1f532fb323641ece43fb84bcd67b.jpg',89),(93,'https://storage.googleapis.com/kix-products-storage/a339fd14f6c455fb0e14398b979b5d3942a1f1e6260e48f8a90b63746439eb74.jpg',89),(94,'https://storage.googleapis.com/kix-products-storage/8485e9e93ec628ff8085abb288d3c9a81bb1338f9933f9fe0fa2e0f4dc9c673c.jpg',95),(96,'https://storage.googleapis.com/kix-products-storage/3292a4831f5da7e2e6c1f5b2140604089f68b0e4acfab4c09c22d69d74b51f97.jpg',95),(97,'https://storage.googleapis.com/kix-products-storage/6ff3e53b750d6a64496ac8d6c9939700ecadac127255e0b06780a3289dd462b6.jpg',95),(98,'https://storage.googleapis.com/kix-products-storage/3f95b6fbf791ec606544536204914fee77cece07c349f721409dafc3f07f825e.jpg',95),(99,'https://storage.googleapis.com/kix-products-storage/d0baab048d5b682486e27f7be41398c76cb1b33eb494be611b17ac87559b67ce.jpg',95),(100,'https://storage.googleapis.com/kix-products-storage/b14866b5f39fdcd067103b20e295e778bbc37a8ca480ba0211ebda9c4bd52ff7.jpg',101),(102,'https://storage.googleapis.com/kix-products-storage/c64788339f5dedb91aaf8bfa9e943754e54a6e48b289695ff515c78fec5be268.jpg',101),(103,'https://storage.googleapis.com/kix-products-storage/519bb55e841e9f9eaaa2ceba352095cf8e8115697d1c7e127f48c1ea9bc74478.jpg',101),(104,'https://storage.googleapis.com/kix-products-storage/cd8b6a13840f874542b89dda7a67c6ba1e74241af630314466f7c51742c287f6.jpg',101),(105,'https://storage.googleapis.com/kix-products-storage/68e94e378dea02e0a9a80a68fec0f75c40092f33184fe3d4d2c07ea013a3117d.jpg',101),(106,'https://storage.googleapis.com/kix-products-storage/8aac83ad6a2f504c717c3fc4b95458c1cacb941c96657dca993bf880f870ca0b.jpg',107),(108,'https://storage.googleapis.com/kix-products-storage/d96095e533d680f07ada0e39e27c9277823bd8d7597fc0ea8d3d9a19f9776c66.jpg',107),(109,'https://storage.googleapis.com/kix-products-storage/c0b69b49f8fd78501820bcc171d3fb3efb2d49d36cc44bf391defc3c879cef00.jpg',107),(110,'https://storage.googleapis.com/kix-products-storage/e065b9ec34cf7a05f8fab0f5c54e111f9ca77ac252390cfaab255ab9d2cdf36f.jpg',107),(111,'https://storage.googleapis.com/kix-products-storage/e0fd42724a2001f54dc9ab721dafb8566b71b6b77ca7359e7b3fa4346450a18e.jpg',107),(112,'https://storage.googleapis.com/kix-products-storage/11274cac0b8ab7315f6797c1b4de7bb4d1c6364441adf83fa5c85f505ce816b4.jpg',113),(114,'https://storage.googleapis.com/kix-products-storage/736c539337d1b89e04cf9381ef9cd363190a39793bb548c8155cf14924890874.jpg',113),(115,'https://storage.googleapis.com/kix-products-storage/e6a435fc2ca99f3adfdff4c34783d4b35e79289a02e44aa5c5a63a2ef43fcfa8.jpg',113),(116,'https://storage.googleapis.com/kix-products-storage/85a16cc3b2f679b00b969ea837765b201bffcf14647dc0971106e2ffd42a45e2.jpg',113),(117,'https://storage.googleapis.com/kix-products-storage/19d8c936f87c0f7911182d6ce6b2534b64a2abe45ba8d5f369c665b30f06b184.jpg',118),(119,'https://storage.googleapis.com/kix-products-storage/565f248ba862ab3bf1a03d592c53a59feb0bce5af85c8067dc4ba9b7c8a59b0e.jpg',118),(120,'https://storage.googleapis.com/kix-products-storage/155d680358e9d77d5c99ea6b58611d542260ae452a6b4fcbde6d1ebe377b0c00.jpg',118),(121,'https://storage.googleapis.com/kix-products-storage/1f6410e250d6cbd15f86d3376aa5cf07aef4473628dffd162647d07954b29acf.jpg',118),(122,'https://storage.googleapis.com/kix-products-storage/2429b98bc559282d9212bd724ed14215b4d2f8cc677d318593f242c3c4186e09.jpg',123),(124,'https://storage.googleapis.com/kix-products-storage/8b81da05542d94db24d7e942ad4e0e913ef2d04e56f9b9b989cf4306ce96b70b.jpg',123),(125,'https://storage.googleapis.com/kix-products-storage/5ac0c751a2ae83b84cd5ffcf0ef6d638c41e6266d2484b45fbdb2183f4adf554.jpg',123),(126,'https://storage.googleapis.com/kix-products-storage/cd25774437530c451428e7ffdc0b52a9f1fa3412a54ceeee80ec2a929b8b3e9b.jpg',123),(144,'https://storage.googleapis.com/kix-products-storage/6383a6b0cd8f5757eeea5ea05edde4566ac86971e8d0904697d0f4dbf5fe1c38.jpg',145);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image_gallery`
--

DROP TABLE IF EXISTS `image_gallery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_gallery` (
  `gallery_id` bigint NOT NULL,
  PRIMARY KEY (`gallery_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_gallery`
--

LOCK TABLES `image_gallery` WRITE;
/*!40000 ALTER TABLE `image_gallery` DISABLE KEYS */;
INSERT INTO `image_gallery` VALUES (43),(48),(54),(61),(67),(72),(78),(84),(89),(95),(101),(107),(113),(118),(123),(145);
/*!40000 ALTER TABLE `image_gallery` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-24 23:38:01
