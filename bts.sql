-- MySQL dump 10.13  Distrib 5.5.35, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: BTS
-- ------------------------------------------------------
-- Server version	5.5.35-0ubuntu0.12.04.2

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
-- Table structure for table `assign_project`
--

DROP TABLE IF EXISTS `assign_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assign_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) DEFAULT NULL,
  `d_id` int(11) DEFAULT NULL,
  `t_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assign_project`
--

LOCK TABLES `assign_project` WRITE;
/*!40000 ALTER TABLE `assign_project` DISABLE KEYS */;
INSERT INTO `assign_project` VALUES (1,3,17,22);
/*!40000 ALTER TABLE `assign_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bug`
--

DROP TABLE IF EXISTS `bug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bug` (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) DEFAULT NULL,
  `priority` varchar(10) DEFAULT NULL,
  `summary` varchar(30) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `d_id` int(11) DEFAULT NULL,
  `status` varchar(10) DEFAULT 'Open',
  `img` blob,
  PRIMARY KEY (`b_id`),
  KEY `d_id` (`d_id`),
  KEY `p_id` (`p_id`),
  CONSTRAINT `bug_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `project` (`p_id`),
  CONSTRAINT `bug_ibfk_2` FOREIGN KEY (`d_id`) REFERENCES `user` (`u_id`),
  CONSTRAINT `bug_ibfk_3` FOREIGN KEY (`d_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE,
  CONSTRAINT `bug_ibfk_4` FOREIGN KEY (`p_id`) REFERENCES `project` (`p_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bug`
--

LOCK TABLES `bug` WRITE;
/*!40000 ALTER TABLE `bug` DISABLE KEYS */;
INSERT INTO `bug` VALUES (17,3,'Normal','abc','abc',20,'Closed',NULL),(21,3,'High','aa','aa',18,'Open',NULL),(23,3,'Normal','aa','aa',18,'Open',NULL);
/*!40000 ALTER TABLE `bug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dummy`
--

DROP TABLE IF EXISTS `dummy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dummy` (
  `name` varchar(10) DEFAULT NULL,
  `unm` varchar(10) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dummy`
--

LOCK TABLES `dummy` WRITE;
/*!40000 ALTER TABLE `dummy` DISABLE KEYS */;
INSERT INTO `dummy` VALUES ('a','a',1),('b','b',2),('k','k',3);
/*!40000 ALTER TABLE `dummy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `stdate` date DEFAULT NULL,
  `edate` date DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (3,'b','2014-03-20','2014-04-02'),(4,'c','2014-04-02','2014-04-23'),(6,'BTS','2014-03-21','2014-04-10'),(8,'ccc','2014-04-11','2014-05-03'),(9,'aaa','2014-04-15','2014-04-24');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `unm` varchar(10) NOT NULL,
  `pwd` varchar(15) NOT NULL,
  `desig` varchar(20) DEFAULT NULL,
  `email` varchar(25) NOT NULL,
  `pm` varchar(10) DEFAULT NULL,
  `dept` varchar(10) NOT NULL,
  `sec_que` varchar(20) DEFAULT NULL,
  `sec_ans` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (17,'Riddhi Mehta','riddhi','r','Developer','riddhi@gmail.com','a','production',NULL,NULL),(18,'Monica','monica','monica','Developer','monica@yahoo.in','aa','aa',NULL,NULL),(20,'bb','bb','bb','Developer','bb@b.com','bb','bb',NULL,NULL),(21,'Anuj','anuj','abc','Developer','anuj@gmail.com','abc','abc',NULL,NULL),(22,'Pooja','pooja','pooja','Tester','pooja@yahoo.com','aa','aa','pets name','prince'),(23,'Heta','heta','heta','Project Manager','heta@gmail.com','','aa',NULL,NULL);
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

-- Dump completed on 2014-04-18 18:16:59
