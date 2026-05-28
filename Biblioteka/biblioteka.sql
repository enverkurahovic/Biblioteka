-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2026 at 12:47 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `biblioteka`
--

-- --------------------------------------------------------

--
-- Table structure for table `autor`
--

CREATE TABLE `autor` (
  `AutorID` int(11) NOT NULL,
  `Ime` varchar(50) DEFAULT NULL,
  `Prezime` varchar(50) DEFAULT NULL,
  `DatumRodjenja` date DEFAULT NULL,
  `Adresa` varchar(100) DEFAULT NULL,
  `Zvanje` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `citalac`
--

CREATE TABLE `citalac` (
  `CitalacID` int(11) NOT NULL,
  `MaticniBroj` varchar(13) DEFAULT NULL,
  `Ime` varchar(50) DEFAULT NULL,
  `Prezime` varchar(50) DEFAULT NULL,
  `Adresa` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `citalac`
--

INSERT INTO `citalac` (`CitalacID`, `MaticniBroj`, `Ime`, `Prezime`, `Adresa`) VALUES
(1, '050100668397', 'Dunja', 'Borovac', 'Beogradska 1'),
(2, '0306996735032', 'Sofija', 'Jovanovic', 'Nis'),
(3, '0612998731234', 'Marko', 'Petrovic', 'Pancevo'),
(4, '055160624332', 'Nemanja', 'Antic', 'Novi Sad'),
(5, '022144676792', 'Lazar', 'Savic', 'Subotica'),
(6, '2304996335052', 'Ana', 'Ilic', 'Novi Sad'),
(7, '4404906387752', 'Masa', 'Popovic', 'Pancevo');

-- --------------------------------------------------------

--
-- Table structure for table `izdali`
--

CREATE TABLE `izdali` (
  `IzdavacID` int(11) DEFAULT NULL,
  `KnjigaID` int(11) DEFAULT NULL,
  `Godina` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `izdavac`
--

CREATE TABLE `izdavac` (
  `IzdavacID` int(11) NOT NULL,
  `NazivIzdavaca` varchar(100) DEFAULT NULL,
  `Adresa` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `knjiga`
--

CREATE TABLE `knjiga` (
  `KnjigaID` int(11) NOT NULL,
  `UDK` varchar(50) DEFAULT NULL,
  `ISBN` varchar(50) DEFAULT NULL,
  `Naziv` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `knjiga`
--

INSERT INTO `knjiga` (`KnjigaID`, `UDK`, `ISBN`, `Naziv`) VALUES
(1, '123', '978-111', 'Java'),
(2, '456', '978-222', 'MySQL');

-- --------------------------------------------------------

--
-- Table structure for table `napisali`
--

CREATE TABLE `napisali` (
  `AutorID` int(11) DEFAULT NULL,
  `KnjigaID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `na_citanju`
--

CREATE TABLE `na_citanju` (
  `KnjigaID` int(11) DEFAULT NULL,
  `CitalacID` int(11) DEFAULT NULL,
  `DatumUzimanja` date DEFAULT NULL,
  `DatumVracanja` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `na_citanju`
--

INSERT INTO `na_citanju` (`KnjigaID`, `CitalacID`, `DatumUzimanja`, `DatumVracanja`) VALUES
(1, 1, '2016-01-01', '2016-01-05'),
(1, 1, '2017-01-01', '2017-01-06'),
(2, 1, '2018-01-01', '2018-01-08'),
(1, 2, '2020-01-01', '2020-01-09'),
(1, 1, '2019-02-10', '2019-02-20'),
(2, 1, '2020-03-11', '2020-03-19'),
(1, 1, '2021-04-15', NULL),
(1, 2, '2018-01-05', '2018-01-11'),
(2, 2, '2019-06-01', NULL),
(1, 2, '2020-09-10', '2020-09-18'),
(2, 3, '2017-02-02', '2017-02-15'),
(1, 3, '2018-07-12', NULL),
(2, 3, '2022-08-20', '2022-08-29'),
(1, 4, '2021-05-14', '2021-05-22'),
(2, 4, '2022-03-03', NULL),
(1, 4, '2023-11-01', '2023-11-15'),
(1, 1, '2021-06-01', NULL),
(2, 1, '2021-07-15', NULL),
(1, 2, '2020-05-10', NULL),
(2, 2, '2020-08-22', NULL),
(1, 3, '2018-03-11', NULL),
(2, 3, '2018-09-14', NULL),
(1, 7, '2022-02-10', '2022-02-20'),
(2, 7, '2023-05-01', NULL),
(3, 7, '2024-01-15', '2024-01-30');

-- --------------------------------------------------------

--
-- Table structure for table `primerak`
--

CREATE TABLE `primerak` (
  `KnjigaID` int(11) DEFAULT NULL,
  `PrimerakID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`AutorID`);

--
-- Indexes for table `citalac`
--
ALTER TABLE `citalac`
  ADD PRIMARY KEY (`CitalacID`);

--
-- Indexes for table `izdavac`
--
ALTER TABLE `izdavac`
  ADD PRIMARY KEY (`IzdavacID`);

--
-- Indexes for table `knjiga`
--
ALTER TABLE `knjiga`
  ADD PRIMARY KEY (`KnjigaID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `autor`
--
ALTER TABLE `autor`
  MODIFY `AutorID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `citalac`
--
ALTER TABLE `citalac`
  MODIFY `CitalacID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `izdavac`
--
ALTER TABLE `izdavac`
  MODIFY `IzdavacID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `knjiga`
--
ALTER TABLE `knjiga`
  MODIFY `KnjigaID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
