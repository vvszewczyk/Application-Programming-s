-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 25, 2024 at 02:27 PM
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
-- Database: `database`
--

-- --------------------------------------------------------

--
-- Table structure for table `carshowroom`
--

CREATE TABLE `carshowroom` (
  `id` int(11) NOT NULL,
  `showroom` varchar(255) DEFAULT NULL,
  `maxVehicle` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `carshowroom`
--

INSERT INTO `carshowroom` (`id`, `showroom`, `maxVehicle`) VALUES
(1, 'Salon Kraków', 10),
(2, 'Salon Warszawa', 15),
(3, 'Salon Wrocław', 20),
(4, 'Favorites', 100);

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE `rating` (
  `id` int(11) NOT NULL,
  `rating` int(1) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `vehicle_id` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rating`
--

INSERT INTO `rating` (`id`, `rating`, `comment`, `vehicle_id`, `date`) VALUES
(1, 2, '312132132', 26, '2024-05-22 11:22:15'),
(2, 5, '2', 26, '2024-05-22 11:22:25'),
(3, 5, '', 19, '2024-05-22 11:22:47'),
(4, 4, '', 19, '2024-05-22 11:22:52'),
(5, 3, '', 19, '2024-05-22 11:22:58'),
(6, 5, '', 19, '2024-05-22 11:23:03'),
(7, 2, '', 19, '2024-05-22 11:50:38'),
(8, 3, '', 24, '2024-05-22 11:54:22'),
(9, 2, '', 24, '2024-05-22 11:54:29'),
(10, 5, '', 27, '2024-05-22 12:01:50'),
(11, 4, '', 21, '2024-05-22 12:05:45'),
(12, 1, '', 21, '2024-05-22 12:05:55'),
(13, 3, '', 21, '2024-05-22 12:13:50'),
(14, 3, '', 19, '2024-05-22 12:17:44'),
(15, 3, '', 20, '2024-05-22 21:06:51'),
(16, 5, 'bingus\n', 20, '2024-05-22 21:10:10'),
(17, 2, '', 22, '2024-05-22 21:10:23'),
(18, 1, '', 41, '2024-05-22 21:10:32'),
(19, 3, '', 38, '2024-05-22 21:10:37'),
(20, 4, '', 39, '2024-05-22 21:10:44'),
(21, 4, '', 32, '2024-05-22 21:10:48'),
(22, 4, '', 28, '2024-05-22 21:10:54'),
(23, 4, '', 42, '2024-05-22 21:10:59'),
(24, 5, '', 40, '2024-05-22 21:11:04'),
(25, 5, '', 33, '2024-05-22 21:11:11'),
(26, 5, '', 23, '2024-05-22 21:11:16'),
(27, 1, '', 25, '2024-05-22 21:11:23'),
(28, 5, '', 43, '2024-05-22 21:12:36'),
(29, 5, '', 44, '2024-05-22 21:12:41'),
(30, 5, 'jezdzi ale na lawecie\n', 45, '2024-05-22 21:12:55');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
  `id` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `itemCondition` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `yearOfProduction` int(11) DEFAULT NULL,
  `mileage` double DEFAULT NULL,
  `engineCapacity` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `carShowRoomID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`id`, `brand`, `model`, `itemCondition`, `price`, `yearOfProduction`, `mileage`, `engineCapacity`, `quantity`, `carShowRoomID`) VALUES
(19, 'Toyota', 'Corolla', 'NEW', 50000, 2020, 100, 1.8, 1, 1),
(20, 'Ford', 'Focus', 'USED', 30000, 2018, 120, 2, 1, 1),
(21, 'Volkswagen', 'Golf', 'NEW', 45000, 2021, 110, 1.5, 1, 1),
(22, 'Fiat', '500', 'DAMAGED', 10000, 2015, 90, 1.2, 1, 1),
(23, 'Renault', 'Clio', 'NEW', 40000, 2021, 80, 1.5, 1, 1),
(24, 'Chevrolet', 'Cruze', 'USED', 15000, 2016, 140, 1.8, 1, 1),
(25, 'Mazda', '3', 'NEW', 55000, 2022, 120, 2, 1, 1),
(26, 'Kia', 'Rio', 'DAMAGED', 7000, 2013, 75, 1.4, 1, 1),
(27, 'Hyundai', 'i30', 'USED', 22000, 2017, 100, 1.6, 1, 1),
(28, 'Audi', 'A4', 'NEW', 60000, 2021, 150, 2, 1, 2),
(29, 'BMW', '3 Series', 'USED', 55000, 2019, 140, 2, 1, 2),
(30, 'Peugeot', '308', 'DAMAGED', 20000, 2016, 110, 1.6, 1, 2),
(31, 'Nissan', 'Qashqai', 'NEW', 50000, 2020, 117, 1.3, 1, 2),
(32, 'Volvo', 'XC60', 'NEW', 250000, 2022, 190, 2, 1, 2),
(33, 'Opel', 'Astra', 'USED', 32000, 2018, 110, 1.6, 1, 2),
(34, 'Toyota', 'Yaris', 'DAMAGED', 5000, 2012, 90, 1.33, 1, 2),
(35, 'Ford', 'Mustang', 'NEW', 85000, 2021, 300, 5, 1, 2),
(36, 'Peugeot', '207', 'USED', 11000, 2015, 95, 1.4, 1, 2),
(37, 'Lexus', 'IS', 'USED', 65000, 2018, 130, 2.5, 1, 3),
(38, 'Honda', 'Civic', 'NEW', 35000, 2020, 120, 1.5, 1, 3),
(39, 'Skoda', 'Octavia', 'DAMAGED', 15000, 2017, 105, 1.4, 1, 3),
(40, 'Alfa Romeo', 'Giulia', 'NEW', 100000, 2021, 200, 2.9, 1, 3),
(41, 'Tesla', 'Model 3', 'NEW', 160000, 2022, 0, 0, 1, 3),
(42, 'Toyota', 'Corolla', 'NEW', 50000, 2020, 100, 1.8, 1, 4),
(43, 'Ford', 'Focus', 'USED', 30000, 2018, 120, 2, 1, 4),
(44, 'BMW', '3 Series', 'USED', 55000, 2019, 140, 2, 1, 4),
(45, 'Alfa Romeo', 'Giulia', 'NEW', 100000, 2021, 200, 2.9, 1, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carshowroom`
--
ALTER TABLE `carshowroom`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`id`),
  ADD KEY `vehicle_id` (`vehicle_id`);

--
-- Indexes for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `carShowRoomID` (`carShowRoomID`),
  ADD KEY `idx_brand` (`brand`),
  ADD KEY `idx_model` (`model`),
  ADD KEY `idx_yearOfProduction` (`yearOfProduction`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `carshowroom`
--
ALTER TABLE `carshowroom`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `rating`
--
ALTER TABLE `rating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `rating_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`carShowRoomID`) REFERENCES `carshowroom` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
