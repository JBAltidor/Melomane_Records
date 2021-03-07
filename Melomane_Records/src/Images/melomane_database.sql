-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 08 juin 2019 à 07:26
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `melomane_database`
--

-- --------------------------------------------------------

--
-- Structure de la table `album`
--

DROP TABLE IF EXISTS `album`;
CREATE TABLE IF NOT EXISTS `album` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITRE` varchar(255) NOT NULL,
  `FORMAT` varchar(255) NOT NULL,
  `DATE_LANCEMENT` date NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `instrument`
--

DROP TABLE IF EXISTS `instrument`;
CREATE TABLE IF NOT EXISTS `instrument` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) NOT NULL,
  `DATE_ACQUISITION` date NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `musicien`
--

DROP TABLE IF EXISTS `musicien`;
CREATE TABLE IF NOT EXISTS `musicien` (
  `IDM` varchar(255) NOT NULL,
  `NOM` varchar(255) NOT NULL,
  `PRENOM` varchar(255) NOT NULL,
  `ADRESSE` varchar(255) NOT NULL,
  `TEL` varchar(255) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `musicien_instrument_nn`
--

DROP TABLE IF EXISTS `musicien_instrument_nn`;
CREATE TABLE IF NOT EXISTS `musicien_instrument_nn` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_MUSICEN` int(11) NOT NULL,
  `ID_INSTRUMENT` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_INSTRUMENT` (`ID_INSTRUMENT`),
  KEY `ID_MUSICEN` (`ID_MUSICEN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `musicien_music_nn`
--

DROP TABLE IF EXISTS `musicien_music_nn`;
CREATE TABLE IF NOT EXISTS `musicien_music_nn` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_MUSICEN` int(11) NOT NULL,
  `ID_MUSIQUE` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_MUSICEN` (`ID_MUSICEN`),
  KEY `ID_MUSIQUE` (`ID_MUSIQUE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `musique`
--

DROP TABLE IF EXISTS `musique`;
CREATE TABLE IF NOT EXISTS `musique` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITRE` int(11) NOT NULL,
  `DUREE` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `musicien_instrument_nn`
--
ALTER TABLE `musicien_instrument_nn`
  ADD CONSTRAINT `musicien_instrument_nn_ibfk_1` FOREIGN KEY (`ID_INSTRUMENT`) REFERENCES `instrument` (`ID`),
  ADD CONSTRAINT `musicien_instrument_nn_ibfk_2` FOREIGN KEY (`ID_MUSICEN`) REFERENCES `musicien` (`ID`);

--
-- Contraintes pour la table `musicien_music_nn`
--
ALTER TABLE `musicien_music_nn`
  ADD CONSTRAINT `musicien_music_nn_ibfk_1` FOREIGN KEY (`ID_MUSICEN`) REFERENCES `musicien` (`ID`),
  ADD CONSTRAINT `musicien_music_nn_ibfk_2` FOREIGN KEY (`ID_MUSIQUE`) REFERENCES `musique` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
