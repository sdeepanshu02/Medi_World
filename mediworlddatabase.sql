-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2016 at 04:32 PM
-- Server version: 5.7.9
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mediworlddatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor_details`
--

DROP TABLE IF EXISTS `doctor_details`;
CREATE TABLE IF NOT EXISTS `doctor_details` (
  `_id` varchar(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Contact_No` varchar(20) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Dob` varchar(20) NOT NULL,
  `Educational_Qualification` varchar(50) NOT NULL,
  `Name_Of_Workplace` varchar(255) NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `patient_details`
--

DROP TABLE IF EXISTS `patient_details`;
CREATE TABLE IF NOT EXISTS `patient_details` (
  `_id` varchar(8) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Contact_No` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Dob` varchar(20) NOT NULL,
  `BloodGroup` varchar(20) NOT NULL,
  `Height` varchar(10) NOT NULL,
  `Weight` varchar(10) NOT NULL,
  `Metabolic_Disorders` varchar(255) NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_details`
--

INSERT INTO `patient_details` (`_id`, `Name`, `Password`, `Contact_No`, `Email`, `Gender`, `Dob`, `BloodGroup`, `Height`, `Weight`, `Metabolic_Disorders`) VALUES
('U15CO001', 'A', '1', '1', 'a@', 'B', '1', 'A(+ve)', '1', '1', 'no'),
('U15CO002', 'B', '2', '2', 'b@', 'B', '2', 'O(+ve)', '2', '2', 'yes'),
('U15CO003', 'C', '3', '3', 'c@', 'B', '3', 'O(+ve)', '3', '3', 'no');

-- --------------------------------------------------------

--
-- Table structure for table `patient_medical_history`
--

DROP TABLE IF EXISTS `patient_medical_history`;
CREATE TABLE IF NOT EXISTS `patient_medical_history` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `Adm_No` varchar(8) NOT NULL,
  `Symptoms` varchar(1000) NOT NULL,
  `Medical_Test` varchar(1000) NOT NULL,
  `Medicines_Prescribed` varchar(1000) NOT NULL,
  `Date_Time_Show` text NOT NULL,
  `Date_Time_Store` text NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_medical_history`
--

INSERT INTO `patient_medical_history` (`_id`, `Adm_No`, `Symptoms`, `Medical_Test`, `Medicines_Prescribed`, `Date_Time_Show`, `Date_Time_Store`) VALUES
(1, 'U15CO001', 'Pain1', 'Test1', 'Medi1', '001', '001'),
(2, 'U15CO001', 'Pain2', 'Test2', 'Medi2', '002', '002'),
(3, 'U15CO002', 'Pain1', 'Test1', 'Medi1', '003', '003'),
(4, 'U15CO001', 'Pain3', 'Test3', 'Medi3', '004', '004'),
(5, 'U15CO002', 'pain2', 'Test2', 'Medi2', '005', '005');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
