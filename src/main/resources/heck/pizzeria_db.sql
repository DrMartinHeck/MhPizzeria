-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 28. Sep 2022 um 16:03
-- Server-Version: 10.4.24-MariaDB
-- PHP-Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `pizzeria_db`
--
CREATE DATABASE IF NOT EXISTS `pizzeria_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `pizzeria_db`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bills`
--

CREATE TABLE `bills` (
  `pk_id` int(11) NOT NULL,
  `customer` int(11) NOT NULL,
  `sum` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `bills`
--

INSERT INTO `bills` (`pk_id`, `customer`, `sum`) VALUES
(1, 1, 22),
(2, 2, 14);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `customers`
--

CREATE TABLE `customers` (
  `pk_id` int(11) NOT NULL,
  `name` text NOT NULL,
  `table_number` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `customers`
--

INSERT INTO `customers` (`pk_id`, `name`, `table_number`) VALUES
(1, 'Max Mustermann', '2'),
(2, 'Maxine Musterfrau', '7 - hinten rechts');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `order_items`
--

CREATE TABLE `order_items` (
  `pk_id` int(11) NOT NULL,
  `pizza` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `position_price` float NOT NULL,
  `bill` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `order_items`
--

INSERT INTO `order_items` (`pk_id`, `pizza`, `count`, `position_price`, `bill`) VALUES
(1, 1, 1, 5, 1),
(2, 3, 1, 10, 1),
(3, 4, 1, 7, 1),
(4, 4, 2, 14, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pizzas`
--

CREATE TABLE `pizzas` (
  `pk_id` int(11) NOT NULL,
  `name` text NOT NULL,
  `price` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `pizzas`
--

INSERT INTO `pizzas` (`pk_id`, `name`, `price`) VALUES
(1, 'Margherita', '5.00'),
(2, 'Romana', '8.00'),
(3, 'Elvis', '10.00'),
(4, 'Vegetaria', '7.00');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`pk_id`),
  ADD KEY `bills_to_customers` (`customer`);

--
-- Indizes für die Tabelle `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`pk_id`);

--
-- Indizes für die Tabelle `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`pk_id`),
  ADD KEY `order_items_to_pizzas` (`pizza`),
  ADD KEY `order_items_to_bills` (`bill`);

--
-- Indizes für die Tabelle `pizzas`
--
ALTER TABLE `pizzas`
  ADD PRIMARY KEY (`pk_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `bills`
--
ALTER TABLE `bills`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `customers`
--
ALTER TABLE `customers`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `order_items`
--
ALTER TABLE `order_items`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `pizzas`
--
ALTER TABLE `pizzas`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `bills_to_customers` FOREIGN KEY (`customer`) REFERENCES `customers` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `order_items_to_bills` FOREIGN KEY (`bill`) REFERENCES `bills` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `order_items_to_pizzas` FOREIGN KEY (`pizza`) REFERENCES `pizzas` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
