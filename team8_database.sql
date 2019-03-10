-- MySQL dump 10.11
--
-- Host: localhost    Database: team8
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `order_id` int(11) NOT NULL auto_increment,
  `item_id` int(11) default NULL,
  `qty` int(11) default NULL,
  `is_purchased` tinyint(1) default NULL,
  `c_id` int(11) default NULL,
  PRIMARY KEY  (`order_id`),
  KEY `item_id` (`item_id`),
  KEY `c_id` (`c_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `godown` (`item_id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `customer` (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,7,2,0,1),(2,5,1,1,2),(3,5,1,1,3),(4,9,1,1,3),(5,1,2,0,2),(6,7,3,0,2),(7,6,2,0,1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `c_id` int(11) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `e_mail` varchar(50) default NULL,
  `password` varchar(20) default NULL,
  PRIMARY KEY  (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Human','land_dweller@gmail.com','8eing_Human'),(2,'sd','sd@sd.sd','sd'),(3,'Raman','r@123','1234');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `godown`
--

DROP TABLE IF EXISTS `godown`;
CREATE TABLE `godown` (
  `item_id` int(11) NOT NULL auto_increment,
  `item_type` varchar(20) default NULL,
  `name` varchar(50) default NULL,
  `price` double default NULL,
  PRIMARY KEY  (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `godown`
--

LOCK TABLES `godown` WRITE;
/*!40000 ALTER TABLE `godown` DISABLE KEYS */;
INSERT INTO `godown` VALUES (1,'shoes','nike_Sports',1499.55),(2,'shoes','Adidas_comfort',2399.55),(3,'shoes','Sparks_sandle',999.4),(4,'books','Harry Potter and the cursed child',449.4),(5,'books','The Kite ',369.88),(6,'books','Lost Jewels ',699.88),(7,'books','Lord of the rings',599.88),(8,'electronics','Philips_PowerBank',1200),(9,'electronics','Redmi Note 5 Pro',17000),(10,'electronics','Hp pavilian Laptop',79000);
/*!40000 ALTER TABLE `godown` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-12 16:50:17
