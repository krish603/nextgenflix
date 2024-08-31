-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 31, 2024 at 11:39 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nextgenflix`
--

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('admin', 'admin'),
(' \r\n', '');

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

CREATE TABLE `movies` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `image_path` varchar(255) NOT NULL,
  `rating` decimal(3,2) NOT NULL DEFAULT 9.50,
  `is_in_watchlist` tinyint(1) NOT NULL DEFAULT 0,
  `video_path` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`id`, `title`, `description`, `image_path`, `rating`, `is_in_watchlist`, `video_path`) VALUES
(1, 'Inception', 'A mind-bending thriller by Christopher Nolan.', 'assets/img/Posters/Movies/Inception.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(2, 'The Dark Knight', 'Batman faces the Joker in Gotham City.', 'assets/img/Posters/Movies/TheDarkKnight.jpg', 8.90, 1, 'assets/video/NEXTGENFLIX.mp4'),
(3, 'Interstellar', 'A journey to save humanity beyond the stars.', 'assets/img/Posters/Movies/Interstellar.jpg', 9.70, 0, 'assets/video/NEXTGENFLIX.mp4'),
(4, 'The Matrix', 'A hacker discovers a shocking truth about reality.', 'assets/img/Posters/Movies/TheMatrix.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(5, 'Gladiator', 'A betrayed Roman general seeks revenge.', 'assets/img/Posters/Movies/Gladiator.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(6, 'The Shawshank Redemption', 'A banker is sentenced to life in Shawshank.', 'assets/img/Posters/Movies/TheShawshankRedemption.jpg', 5.00, 1, 'assets/video/NEXTGENFLIX.mp4'),
(7, 'Pulp Fiction', 'Stories of crime in Los Angeles.', 'assets/img/Posters/Movies/PulpFiction.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(8, 'Fight Club', 'A man forms an underground fight club.', 'assets/img/Posters/Movies/FightClub.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(9, 'Forrest Gump', 'A man with a low IQ recounts his life.', 'assets/img/Posters/Movies/ForrestGump.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(10, 'The Godfather', 'The story of the Corleone mafia family.', 'assets/img/Posters/Movies/TheGodfather.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(11, 'The Lord of the Rings', 'A hobbit begins a journey to destroy a powerful ring.', 'assets/img/Posters/Movies/TheLordoftheRings.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(12, 'Star Wars: A New Hope', 'A young man becomes a hero in a galactic war.', 'assets/img/Posters/Movies/StarWarsANewHope.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(13, 'Jurassic Park', 'A theme park with cloned dinosaurs faces disaster.', 'assets/img/Posters/Movies/JurassicPark.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(14, 'The Avengers', 'Superheroes team up to save the world.', 'assets/img/Posters/Movies/TheAvengers.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(15, 'Titanic', 'A love story on the ill-fated Titanic.', 'assets/img/Posters/Movies/Titanic.jpg', 9.90, 0, 'assets/video/NEXTGENFLIX.mp4'),
(16, 'Braveheart', 'A Scottish warrior leads a rebellion.', 'assets/img/Posters/Movies/Braveheart.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(17, 'The Lion King', 'A lion cub learns about life and leadership.', 'assets/img/Posters/Movies/TheLionKing.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(18, 'Avatar', 'A marine on an alien planet gets involved in a conflict.', 'assets/img/Posters/Movies/Avatar.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(19, 'Saving Private Ryan', 'A mission to rescue a soldier during WWII.', 'assets/img/Posters/Movies/SavingPrivateRyan.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(20, 'The Silence of the Lambs', 'A young FBI agent seeks help from a cannibalistic killer.', 'assets/img/Posters/Movies/TheSilenceoftheLambs.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4');

-- --------------------------------------------------------

--
-- Table structure for table `tvshows`
--

CREATE TABLE `tvshows` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `image_path` varchar(255) NOT NULL,
  `rating` decimal(3,2) NOT NULL DEFAULT 9.50,
  `is_in_watchlist` tinyint(1) NOT NULL DEFAULT 0,
  `video_path` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tvshows`
--

INSERT INTO `tvshows` (`id`, `title`, `description`, `image_path`, `rating`, `is_in_watchlist`, `video_path`) VALUES
(1, 'Breaking Bad', 'A high school teacher turns to making meth.', 'assets/img/Posters/TVShows/BreakingBad.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(2, 'Game of Thrones', 'Noble families fight for control of the Iron Throne.', 'assets/img/Posters/TVShows/GameofThrones.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(3, 'Stranger Things', 'Kids uncover supernatural mysteries in their town.', 'assets/img/Posters/TVShows/StrangerThings.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(4, 'The Office', 'A mockumentary about office workers at Dunder Mifflin.', 'assets/img/Posters/TVShows/TheOffice.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(5, 'Friends', 'Six friends navigate life and relationships in NYC.', 'assets/img/Posters/TVShows/Friends.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(6, 'The Mandalorian', 'A bounty hunter embarks on missions across the galaxy.', 'assets/img/Posters/TVShows/TheMandalorian.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(7, 'Sherlock', 'A modern adaptation of Sherlock Holmes.', 'assets/img/Posters/TVShows/Sherlock.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(8, 'The Crown', 'The reign of Queen Elizabeth II and the events that shaped the second half of the twentieth century.', 'assets/img/Posters/TVShows/TheCrown.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(9, 'The Witcher', 'A monster hunter struggles to find his place in a world where people often prove more wicked than beasts.', 'assets/img/Posters/TVShows/TheWitcher.jpg', 7.80, 1, 'assets/video/NEXTGENFLIX.mp4'),
(10, 'Westworld', 'A futuristic theme park where visitors interact with robots.', 'assets/img/Posters/TVShows/Westworld.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(11, 'The Simpsons', 'A satirical depiction of American life.', 'assets/img/Posters/TVShows/TheSimpsons.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(12, 'The Boys', 'A group of vigilantes set out to take down corrupt superheroes.', 'assets/img/Posters/TVShows/TheBoys.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(13, 'House of Cards', 'A ruthless politician climbs the ranks of power.', 'assets/img/Posters/TVShows/HouseofCards.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(14, 'Black Mirror', 'A collection of dystopian tales exploring modern society.', 'assets/img/Posters/TVShows/BlackMirror.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(15, 'The Walking Dead', 'Survivors navigate a post-apocalyptic world overrun by zombies.', 'assets/img/Posters/TVShows/TheWalkingDead.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(16, 'Narcos', 'The rise and fall of the infamous drug kingpin Pablo Escobar.', 'assets/img/Posters/TVShows/Narcos.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(17, 'Lost', 'Survivors of a plane crash struggle to stay alive on a mysterious island.', 'assets/img/Posters/TVShows/Lost.jpg', 9.50, 1, 'assets/video/NEXTGENFLIX.mp4'),
(18, 'The Handmaid\'s Tale', 'In a dystopian society, fertile women are forced into child-bearing slavery.', 'assets/img/Posters/TVShows/TheHandmaidsTale.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(19, 'Peaky Blinders', 'A gangster family epic set in 1900s England.', 'assets/img/Posters/TVShows/PeakyBlinders.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4'),
(20, 'The Queen\'s Gambit', 'A young chess prodigy rises to prominence.', 'assets/img/Posters/TVShows/TheQueensGambit.jpg', 9.50, 0, 'assets/video/NEXTGENFLIX.mp4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tvshows`
--
ALTER TABLE `tvshows`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `tvshows`
--
ALTER TABLE `tvshows`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
