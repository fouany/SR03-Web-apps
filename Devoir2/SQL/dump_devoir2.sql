-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  sam. 02 mai 2020 à 09:46
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `sr03`
--

-- --------------------------------------------------------

--
-- Structure de la table `messages`
--

DROP TABLE IF EXISTS `messages`;
CREATE TABLE IF NOT EXISTS `messages` (
  `id_msg` int(11) NOT NULL AUTO_INCREMENT,
  `id_user_to` int(11) NOT NULL,
  `id_user_from` int(11) NOT NULL,
  `sujet_msg` varchar(255) NOT NULL,
  `corps_msg` text NOT NULL,
  PRIMARY KEY (`id_msg`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `messages`
--

INSERT INTO `messages` (`id_msg`, `id_user_to`, `id_user_from`, `sujet_msg`, `corps_msg`) VALUES
(40, 1, 2, 'prêt', 'Je vous remercie pour votre message je men vais de ce pas le contacter.\r\nCordialement'),
(39, 5, 1, 'Asssurance', 'Bonjour Mr Dupont, je vous contacte pour parler de votre contrat dassurance, veuillez me recontacter au plus vite pour en fixer les modalités.\r\n\r\nCordialement,\r\nGillet Guillaume\r\nEmployé de Banque'),
(38, 2, 1, 'Prêt', 'Bonjour je vous demanderai de prendre contact avec mon collègue Mr Herkens pour discuter de votre prêt.\r\n\r\nCordialement,\r\nGillet Guillaume\r\nEmployé de Banque'),
(37, 6, 1, 'Collaboration prêt Madame ripert', 'Je técris ce mail pour fixer un rendez vous pour le prêt de Madame ripert.\r\n\r\nCordialement, \r\nGillet Guillaume\r\nEmployé de banque'),
(41, 6, 2, 'Prêt', 'Bonjour quand seriez vous disponible pour réaliser mon rendez vous de demande de prêt?'),
(42, 1, 6, 're: prêt', 'Ca marche je moccupe de la cliente.\r\n\r\nCordialement,\r\nHerkens Antoine\r\nEmployé de Banque');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `profil_user` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `numero_compte` int(12) NOT NULL,
  `solde_compte` int(11) NOT NULL,
  PRIMARY KEY (`id_user`)
) ;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id_user`, `login`, `mot_de_passe`, `profil_user`, `nom`, `prenom`, `numero_compte`, `solde_compte`) VALUES
(1, 'ggillet', 'GG1507', 'EMPLOYE', 'Gillet', 'Guillaume', 123456789, 1500),
(2, 'eripert', 'client', 'CLIENT', 'Ripert', 'Emma', 321654987, 279),
(5, 'DupontJ', 'Client', 'CLIENT', 'Dupont', 'Jean', 789456123, 658),
(6, 'aherkens', 'motdepasse', 'EMPLOYE', 'Herkens', 'Antoine', 987654321, 2500);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
