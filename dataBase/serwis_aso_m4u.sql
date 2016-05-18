-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Czas wygenerowania: 16 Maj 2016, 19:35
-- Wersja serwera: 5.5.47-0ubuntu0.14.04.1
-- Wersja PHP: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `serwis_aso_m4u`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `czesc`
--

CREATE TABLE IF NOT EXISTS `czesc` (
  `ID_Czesci` int(10) NOT NULL AUTO_INCREMENT,
  `Nr_Czesci` text,
  `Marka` text,
  `Typ_Czesci` text NOT NULL,
  `Koszt` int(10) NOT NULL,
  `ID_Naprawy` int(10) NOT NULL,
  `ID_P_Naprawy` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID_Czesci`),
  UNIQUE KEY `ID_Czesci` (`ID_Czesci`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kalendarz_wizyt`
--

CREATE TABLE IF NOT EXISTS `kalendarz_wizyt` (
  `ID_Wizyty` int(10) NOT NULL AUTO_INCREMENT,
  `Data_Wizyty` date DEFAULT NULL,
  `Status_Wizyty` int(3) NOT NULL DEFAULT '1',
  `ID_Serwis` int(10) NOT NULL,
  `ID_P_Naprawy` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID_Wizyty`),
  UNIQUE KEY `ID_Wizyty` (`ID_Wizyty`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Zrzut danych tabeli `kalendarz_wizyt`
--

INSERT INTO `kalendarz_wizyt` (`ID_Wizyty`, `Data_Wizyty`, `Status_Wizyty`, `ID_Serwis`, `ID_P_Naprawy`) VALUES
(1, '2016-04-20', 0, 1, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kalendarz_wizyt_samochod`
--

CREATE TABLE IF NOT EXISTS `kalendarz_wizyt_samochod` (
  `VIN` varchar(30) NOT NULL,
  `ID_Wizyty` int(10) NOT NULL,
  PRIMARY KEY (`VIN`,`ID_Wizyty`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `kalendarz_wizyt_samochod`
--

INSERT INTO `kalendarz_wizyt_samochod` (`VIN`, `ID_Wizyty`) VALUES
('ZFA19900000014240', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient`
--

CREATE TABLE IF NOT EXISTS `klient` (
  `PESEL` varchar(15) NOT NULL,
  `Nazwisko` text NOT NULL,
  `Imie` text NOT NULL,
  `Miejscowosc` text NOT NULL,
  `Kod_Pocztowy` int(6) NOT NULL,
  `Nr_Telefonu` int(13) NOT NULL,
  `Email` text,
  `Haslo` text NOT NULL,
  `Status_Klienta` int(3) NOT NULL DEFAULT '1',
  PRIMARY KEY (`PESEL`),
  UNIQUE KEY `PESEL` (`PESEL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `klient`
--

INSERT INTO `klient` (`PESEL`, `Nazwisko`, `Imie`, `Miejscowosc`, `Kod_Pocztowy`, `Nr_Telefonu`, `Email`, `Haslo`, `Status_Klienta`) VALUES
('123456789', 'Sikora', 'wojtek', 'krakow', 34231, 1235467, 'a@a.pl', 'haslo123', 1),
('94010606732', 'Pindel', 'Dawid', 'Klwz', -96, 8888888, 'pindlu94@gmail.com', 'haslo123', 1),
('94050603947', 'bbb', 'aaa', 'krakow', -319, 603884777, 'asd@gmail.com', 'abc', 1),
('94111904153', 'Wójtowicz', 'Micha?', 'Limanowa', 34600, 505999000, 'wojtowicz.michal@outlook.com', 'haslo123', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient_serwis`
--

CREATE TABLE IF NOT EXISTS `klient_serwis` (
  `PESEL` varchar(15) NOT NULL,
  `ID_Serwis` int(10) NOT NULL,
  PRIMARY KEY (`PESEL`,`ID_Serwis`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `klient_serwis`
--

INSERT INTO `klient_serwis` (`PESEL`, `ID_Serwis`) VALUES
('94111904153', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `naprawa`
--

CREATE TABLE IF NOT EXISTS `naprawa` (
  `ID_Naprawy` int(10) NOT NULL AUTO_INCREMENT,
  `Data_Naprawy` date NOT NULL,
  `Typ_Naprawy` text NOT NULL,
  `Przebieg` int(10) NOT NULL,
  `Koszt` int(10) NOT NULL,
  `VIN` varchar(30) NOT NULL,
  `ID_Serwis` int(10) NOT NULL,
  PRIMARY KEY (`ID_Naprawy`),
  UNIQUE KEY `ID_Naprawy` (`ID_Naprawy`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `planowane_naprawy`
--

CREATE TABLE IF NOT EXISTS `planowane_naprawy` (
  `ID_P_Naprawy` int(10) NOT NULL AUTO_INCREMENT,
  `Data_Naprawy` date DEFAULT NULL,
  `Przebieg` int(10) DEFAULT NULL,
  `Koszt` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID_P_Naprawy`),
  UNIQUE KEY `ID_P_Naprawy` (`ID_P_Naprawy`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Zrzut danych tabeli `planowane_naprawy`
--

INSERT INTO `planowane_naprawy` (`ID_P_Naprawy`, `Data_Naprawy`, `Przebieg`, `Koszt`) VALUES
(1, '2016-02-05', 30000, 113);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `planowane_naprawy_samochod`
--

CREATE TABLE IF NOT EXISTS `planowane_naprawy_samochod` (
  `VIN` varchar(30) NOT NULL,
  `ID_P_Naprawy` int(10) NOT NULL,
  PRIMARY KEY (`VIN`,`ID_P_Naprawy`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `planowane_naprawy_samochod`
--

INSERT INTO `planowane_naprawy_samochod` (`VIN`, `ID_P_Naprawy`) VALUES
('ZFA19900000014240', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownik`
--

CREATE TABLE IF NOT EXISTS `pracownik` (
  `PESEL` varchar(15) NOT NULL,
  `Nazwisko` text NOT NULL,
  `Imie` text NOT NULL,
  `Miejscowosc` text NOT NULL,
  `Kod_Pocztowy` int(6) NOT NULL,
  `Nr_Telefonu` int(13) NOT NULL,
  `Email` text,
  `Haslo` text NOT NULL,
  `Stanowisko` text NOT NULL,
  `Status_Pracownika3030` int(3) NOT NULL DEFAULT '1',
  PRIMARY KEY (`PESEL`),
  UNIQUE KEY `PESEL` (`PESEL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `pracownik`
--

INSERT INTO `pracownik` (`PESEL`, `Nazwisko`, `Imie`, `Miejscowosc`, `Kod_Pocztowy`, `Nr_Telefonu`, `Email`, `Haslo`, `Stanowisko`, `Status_Pracownika3030`) VALUES
('94010606732', 'Pindel', 'Dawid', 'klwz', 34130, 666888999, 'pindlu94@gmail.com', 'haslo123', 'szef', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `samochod`
--

CREATE TABLE IF NOT EXISTS `samochod` (
  `VIN` varchar(30) NOT NULL,
  `Marka` text NOT NULL,
  `Model` text NOT NULL,
  `Rok_Produkcji` date NOT NULL,
  `Paliwo` text NOT NULL,
  `Pojemnosc` int(6) NOT NULL,
  `Moc_Silnika` int(6) NOT NULL,
  `Kraj_Pochodzenia` text,
  `Przebieg` int(10) DEFAULT NULL,
  `PESEL` varchar(15) NOT NULL,
  PRIMARY KEY (`VIN`),
  UNIQUE KEY `VIN` (`VIN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `samochod`
--

INSERT INTO `samochod` (`VIN`, `Marka`, `Model`, `Rok_Produkcji`, `Paliwo`, `Pojemnosc`, `Moc_Silnika`, `Kraj_Pochodzenia`, `Przebieg`, `PESEL`) VALUES
('12345das121', 'opel', 'astra', '2016-02-05', 'benzyna', 3000, 120, 'polska', 100000, '123456789'),
('wwwwssss', 'vw', 'golf', '2016-02-05', 'd', 1900, 130, 'niemcy', 265258, '94010606732'),
('ZFA123542', 'VW', 'GOLF', '2016-02-05', 'DIESEL', 1900, 130, 'NIEMCY', 100, '94111904153'),
('ZFA19900000014240', 'Fiat', 'Grande Punto', '2005-11-08', 'Diesel', 1910, 131, 'Francja', 176810, '94111904153');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `serwis`
--

CREATE TABLE IF NOT EXISTS `serwis` (
  `ID_Serwis` int(10) NOT NULL AUTO_INCREMENT,
  `Nazwa` text NOT NULL,
  `Miejscowosc` text NOT NULL,
  `Kod_Pocztowy` int(6) NOT NULL,
  `Nr_Telefonu` int(13) NOT NULL,
  `Email` text NOT NULL,
  `Status_Serwisu` int(3) NOT NULL DEFAULT '1',
  `ID_Wizyty` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID_Serwis`),
  UNIQUE KEY `ID_Serwis` (`ID_Serwis`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Zrzut danych tabeli `serwis`
--

INSERT INTO `serwis` (`ID_Serwis`, `Nazwa`, `Miejscowosc`, `Kod_Pocztowy`, `Nr_Telefonu`, `Email`, `Status_Serwisu`, `ID_Wizyty`) VALUES
(1, 'Serwis Politechnika', 'Kraków', 31100, 100200300, 'serwis@pk.pl', 1, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `serwis_pracownik`
--

CREATE TABLE IF NOT EXISTS `serwis_pracownik` (
  `PESEL` varchar(15) NOT NULL,
  `ID_Serwis` int(10) NOT NULL,
  PRIMARY KEY (`PESEL`,`ID_Serwis`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
