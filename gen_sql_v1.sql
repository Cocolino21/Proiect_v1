-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema proiect_v1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema proiect_v1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `proiect_v1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `proiect_v1` ;

-- -----------------------------------------------------
-- Table `proiect_v1`.`centru`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`centru` (
  `id_centru` INT NOT NULL AUTO_INCREMENT,
  `nume_centru` VARCHAR(40) NULL DEFAULT NULL,
  `adresa` VARCHAR(50) NULL DEFAULT NULL,
  `program` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`id_centru`),
  UNIQUE INDEX `id_centru` (`id_centru` ASC) VISIBLE,
  UNIQUE INDEX `nume_centru` (`nume_centru` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 73
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`functie_dept`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`functie_dept` (
  `functie` VARCHAR(20) NOT NULL,
  `dept` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`functie`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`angajat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`angajat` (
  `id_angajat` INT NOT NULL AUTO_INCREMENT,
  `cnp` VARCHAR(16) NULL DEFAULT NULL,
  `nume` VARCHAR(20) NULL DEFAULT NULL,
  `prenume` VARCHAR(20) NULL DEFAULT NULL,
  `adresa` VARCHAR(50) NULL DEFAULT NULL,
  `nr_telefon` VARCHAR(15) NULL DEFAULT NULL,
  `email` VARCHAR(30) NULL DEFAULT NULL,
  `iban` VARCHAR(30) NULL DEFAULT NULL,
  `numar_contract` INT NULL DEFAULT NULL,
  `functie` VARCHAR(20) NULL DEFAULT NULL,
  `salariu_lunar` INT NULL DEFAULT NULL,
  `username` VARCHAR(20) NULL DEFAULT NULL,
  `parola` VARCHAR(33) NULL DEFAULT NULL,
  `id_centru` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_angajat`),
  UNIQUE INDEX `cnp` (`cnp` ASC) VISIBLE,
  UNIQUE INDEX `username` (`username` ASC) VISIBLE,
  INDEX `id_centru` (`id_centru` ASC) VISIBLE,
  INDEX `functie` (`functie` ASC) VISIBLE,
  CONSTRAINT `angajat_ibfk_1`
    FOREIGN KEY (`id_centru`)
    REFERENCES `proiect_v1`.`centru` (`id_centru`),
  CONSTRAINT `angajat_ibfk_2`
    FOREIGN KEY (`functie`)
    REFERENCES `proiect_v1`.`functie_dept` (`functie`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`asistentmedical`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`asistentmedical` (
  `id_asistent` INT NOT NULL,
  `tip` VARCHAR(30) NULL DEFAULT NULL,
  `grad` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id_asistent`),
  CONSTRAINT `asistentmedical_ibfk_1`
    FOREIGN KEY (`id_asistent`)
    REFERENCES `proiect_v1`.`angajat` (`id_angajat`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`pacient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`pacient` (
  `id_pacient` INT NOT NULL AUTO_INCREMENT,
  `nume` VARCHAR(20) NULL DEFAULT NULL,
  `prenume` VARCHAR(20) NULL DEFAULT NULL,
  `CNP` VARCHAR(16) NULL DEFAULT NULL,
  `nr_telefon` VARCHAR(15) NULL DEFAULT NULL,
  `email` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`id_pacient`),
  UNIQUE INDEX `CNP` (`CNP` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`medic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`medic` (
  `id_medic` INT NOT NULL,
  `cod_parafa` VARCHAR(30) NULL DEFAULT NULL,
  `titlu_stiintific` VARCHAR(30) NULL DEFAULT NULL,
  `post_didactic` VARCHAR(30) NULL DEFAULT NULL,
  `procent` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_medic`),
  CONSTRAINT `medic_ibfk_1`
    FOREIGN KEY (`id_medic`)
    REFERENCES `proiect_v1`.`angajat` (`id_angajat`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`receptioner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`receptioner` (
  `id_receptioner` INT NOT NULL,
  PRIMARY KEY (`id_receptioner`),
  CONSTRAINT `receptioner_ibfk_1`
    FOREIGN KEY (`id_receptioner`)
    REFERENCES `proiect_v1`.`angajat` (`id_angajat`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`programare`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`programare` (
  `id_programare` INT NOT NULL AUTO_INCREMENT,
  `ora` TIME NULL DEFAULT NULL,
  `data_programare` DATE NULL DEFAULT NULL,
  `id_medic` INT NULL DEFAULT NULL,
  `id_receptioner` INT NULL DEFAULT NULL,
  `id_pacient` INT NULL DEFAULT NULL,
  `finalizat` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id_programare`),
  INDEX `id_medic` (`id_medic` ASC) VISIBLE,
  INDEX `id_receptioner` (`id_receptioner` ASC) VISIBLE,
  INDEX `id_pacient` (`id_pacient` ASC) VISIBLE,
  CONSTRAINT `programare_ibfk_1`
    FOREIGN KEY (`id_medic`)
    REFERENCES `proiect_v1`.`medic` (`id_medic`),
  CONSTRAINT `programare_ibfk_2`
    FOREIGN KEY (`id_receptioner`)
    REFERENCES `proiect_v1`.`receptioner` (`id_receptioner`),
  CONSTRAINT `programare_ibfk_3`
    FOREIGN KEY (`id_pacient`)
    REFERENCES `proiect_v1`.`pacient` (`id_pacient`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`raport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`raport` (
  `id_raport` INT NOT NULL,
  `id_pacient` INT NULL DEFAULT NULL,
  `id_asistent` INT NULL DEFAULT NULL,
  `recomandari` VARCHAR(500) NULL DEFAULT NULL,
  `istoric_relev` VARCHAR(300) NULL DEFAULT NULL,
  `diagonistic` VARCHAR(300) NULL DEFAULT NULL,
  `data_completare` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id_raport`),
  INDEX `id_pacient` (`id_pacient` ASC) VISIBLE,
  INDEX `id_asistent` (`id_asistent` ASC) VISIBLE,
  CONSTRAINT `raport_ibfk_1`
    FOREIGN KEY (`id_pacient`)
    REFERENCES `proiect_v1`.`pacient` (`id_pacient`),
  CONSTRAINT `raport_ibfk_2`
    FOREIGN KEY (`id_asistent`)
    REFERENCES `proiect_v1`.`asistentmedical` (`id_asistent`),
  CONSTRAINT `raport_ibfk_3`
    FOREIGN KEY (`id_raport`)
    REFERENCES `proiect_v1`.`programare` (`id_programare`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`bonurifiscale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`bonurifiscale` (
  `id_bon` INT NOT NULL,
  `suma_platita` INT NULL DEFAULT NULL,
  `data_emitere` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id_bon`),
  CONSTRAINT `bonurifiscale_ibfk_1`
    FOREIGN KEY (`id_bon`)
    REFERENCES `proiect_v1`.`raport` (`id_raport`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`resursa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`resursa` (
  `id_resursa` INT NOT NULL AUTO_INCREMENT,
  `nume_resursa` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_resursa`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`centru_resursa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`centru_resursa` (
  `centru_id_centru` INT NOT NULL,
  `resursa_id_resursa` INT NOT NULL,
  `cantitate_disponibila` INT NULL DEFAULT NULL,
  PRIMARY KEY (`centru_id_centru`, `resursa_id_resursa`),
  INDEX `fk_centru_has_resursa_resursa1_idx` (`resursa_id_resursa` ASC) VISIBLE,
  INDEX `fk_centru_has_resursa_centru1_idx` (`centru_id_centru` ASC) VISIBLE,
  CONSTRAINT `fk_centru_has_resursa_centru1`
    FOREIGN KEY (`centru_id_centru`)
    REFERENCES `proiect_v1`.`centru` (`id_centru`),
  CONSTRAINT `fk_centru_has_resursa_resursa1`
    FOREIGN KEY (`resursa_id_resursa`)
    REFERENCES `proiect_v1`.`resursa` (`id_resursa`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`serviciumedical`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`serviciumedical` (
  `id_serviciu` INT NOT NULL,
  `nume_serviciu` VARCHAR(20) NULL DEFAULT NULL,
  `pret` INT NULL DEFAULT NULL,
  `durata_min` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_serviciu`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`centru_serviciu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`centru_serviciu` (
  `id_centru` INT NOT NULL,
  `id_serviciu` INT NOT NULL,
  PRIMARY KEY (`id_centru`, `id_serviciu`),
  INDEX `id_serviciu` (`id_serviciu` ASC) VISIBLE,
  CONSTRAINT `centru_serviciu_ibfk_1`
    FOREIGN KEY (`id_centru`)
    REFERENCES `proiect_v1`.`centru` (`id_centru`),
  CONSTRAINT `centru_serviciu_ibfk_2`
    FOREIGN KEY (`id_serviciu`)
    REFERENCES `proiect_v1`.`serviciumedical` (`id_serviciu`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`cerereconcediu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`cerereconcediu` (
  `id_angajat` INT NOT NULL,
  `data_inceput` DATE NOT NULL,
  `data_sfarsit` DATE NULL DEFAULT NULL,
  `motiv` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id_angajat`, `data_inceput`),
  CONSTRAINT `cerereconcediu_ibfk_1`
    FOREIGN KEY (`id_angajat`)
    REFERENCES `proiect_v1`.`angajat` (`id_angajat`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`cheltuialalunartotal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`cheltuialalunartotal` (
  `id_centru` INT NOT NULL,
  `suma` DECIMAL(10,2) NULL DEFAULT NULL,
  `an_luna_zi` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id_centru`),
  CONSTRAINT `cheltuialalunartotal_ibfk_1`
    FOREIGN KEY (`id_centru`)
    REFERENCES `proiect_v1`.`centru` (`id_centru`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`competenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`competenta` (
  `nume_competenta` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`nume_competenta`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`competenta_medic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`competenta_medic` (
  `nume_competenta` VARCHAR(30) NOT NULL,
  `id_medic` INT NOT NULL,
  PRIMARY KEY (`nume_competenta`, `id_medic`),
  INDEX `id_medic` (`id_medic` ASC) VISIBLE,
  CONSTRAINT `competenta_medic_ibfk_1`
    FOREIGN KEY (`nume_competenta`)
    REFERENCES `proiect_v1`.`competenta` (`nume_competenta`),
  CONSTRAINT `competenta_medic_ibfk_2`
    FOREIGN KEY (`id_medic`)
    REFERENCES `proiect_v1`.`medic` (`id_medic`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`concediu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`concediu` (
  `id_angajat` INT NOT NULL,
  `data_inceput` DATE NOT NULL,
  `data_sfarsit` DATE NULL DEFAULT NULL,
  `motiv` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id_angajat`, `data_inceput`),
  CONSTRAINT `concediu_ibfk_1`
    FOREIGN KEY (`id_angajat`)
    REFERENCES `proiect_v1`.`angajat` (`id_angajat`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`investigatie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`investigatie` (
  `id_investigatie` INT NOT NULL,
  `id_serviciu` INT NOT NULL,
  PRIMARY KEY (`id_investigatie`, `id_serviciu`),
  INDEX `id_serviciu` (`id_serviciu` ASC) VISIBLE,
  CONSTRAINT `investigatie_ibfk_1`
    FOREIGN KEY (`id_investigatie`)
    REFERENCES `proiect_v1`.`raport` (`id_raport`),
  CONSTRAINT `investigatie_ibfk_2`
    FOREIGN KEY (`id_serviciu`)
    REFERENCES `proiect_v1`.`serviciumedical` (`id_serviciu`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`orar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`orar` (
  `id_angajat` INT NOT NULL,
  `ziua_saptamanii` VARCHAR(20) NOT NULL,
  `moment_inceput` TIME NULL DEFAULT NULL,
  `moment_sfarsit` TIME NULL DEFAULT NULL,
  `id_centru` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_angajat`, `ziua_saptamanii`),
  INDEX `id_centru` (`id_centru` ASC) VISIBLE,
  CONSTRAINT `orar_ibfk_1`
    FOREIGN KEY (`id_angajat`)
    REFERENCES `proiect_v1`.`angajat` (`id_angajat`),
  CONSTRAINT `orar_ibfk_2`
    FOREIGN KEY (`id_centru`)
    REFERENCES `proiect_v1`.`centru` (`id_centru`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`raportanaliza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`raportanaliza` (
  `id_analiza` INT NOT NULL AUTO_INCREMENT,
  `id_pacient` INT NULL DEFAULT NULL,
  `rezultat` VARCHAR(50) NULL DEFAULT NULL,
  `id_asistent` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_analiza`),
  INDEX `id_asistent` (`id_asistent` ASC) VISIBLE,
  INDEX `id_pacient` (`id_pacient` ASC) VISIBLE,
  CONSTRAINT `raportanaliza_ibfk_1`
    FOREIGN KEY (`id_asistent`)
    REFERENCES `proiect_v1`.`asistentmedical` (`id_asistent`),
  CONSTRAINT `raportanaliza_ibfk_2`
    FOREIGN KEY (`id_pacient`)
    REFERENCES `proiect_v1`.`pacient` (`id_pacient`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`serviciu_competenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`serviciu_competenta` (
  `id_serviciu` INT NOT NULL,
  `nume_competenta` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id_serviciu`, `nume_competenta`),
  INDEX `nume_competenta` (`nume_competenta` ASC) VISIBLE,
  CONSTRAINT `serviciu_competenta_ibfk_1`
    FOREIGN KEY (`id_serviciu`)
    REFERENCES `proiect_v1`.`serviciumedical` (`id_serviciu`),
  CONSTRAINT `serviciu_competenta_ibfk_2`
    FOREIGN KEY (`nume_competenta`)
    REFERENCES `proiect_v1`.`competenta` (`nume_competenta`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`serviciu_programare`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`serviciu_programare` (
  `id_programare` INT NOT NULL,
  `id_serviciu` INT NOT NULL,
  PRIMARY KEY (`id_programare`, `id_serviciu`),
  INDEX `id_serviciu` (`id_serviciu` ASC) VISIBLE,
  CONSTRAINT `serviciu_programare_ibfk_1`
    FOREIGN KEY (`id_programare`)
    REFERENCES `proiect_v1`.`programare` (`id_programare`),
  CONSTRAINT `serviciu_programare_ibfk_2`
    FOREIGN KEY (`id_serviciu`)
    REFERENCES `proiect_v1`.`serviciumedical` (`id_serviciu`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`specializare`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`specializare` (
  `nume_specializare` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`nume_specializare`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`serviciu_specializare`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`serviciu_specializare` (
  `id_serviciu` INT NOT NULL,
  `nume_specializare` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id_serviciu`, `nume_specializare`),
  INDEX `nume_specializare` (`nume_specializare` ASC) VISIBLE,
  CONSTRAINT `serviciu_specializare_ibfk_1`
    FOREIGN KEY (`id_serviciu`)
    REFERENCES `proiect_v1`.`serviciumedical` (`id_serviciu`),
  CONSTRAINT `serviciu_specializare_ibfk_2`
    FOREIGN KEY (`nume_specializare`)
    REFERENCES `proiect_v1`.`specializare` (`nume_specializare`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`serviciumedical_resursa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`serviciumedical_resursa` (
  `serviciumedical_id_serviciu` INT NOT NULL,
  `resursa_id_resursa` INT NOT NULL,
  `cantitate_necesara` INT NOT NULL,
  PRIMARY KEY (`serviciumedical_id_serviciu`, `resursa_id_resursa`),
  INDEX `fk_serviciumedical_has_resursa_resursa1_idx` (`resursa_id_resursa` ASC) VISIBLE,
  INDEX `fk_serviciumedical_has_resursa_serviciumedical1_idx` (`serviciumedical_id_serviciu` ASC) VISIBLE,
  CONSTRAINT `fk_serviciumedical_has_resursa_resursa1`
    FOREIGN KEY (`resursa_id_resursa`)
    REFERENCES `proiect_v1`.`resursa` (`id_resursa`),
  CONSTRAINT `fk_serviciumedical_has_resursa_serviciumedical1`
    FOREIGN KEY (`serviciumedical_id_serviciu`)
    REFERENCES `proiect_v1`.`serviciumedical` (`id_serviciu`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`specializare_medic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`specializare_medic` (
  `nume_specializare` VARCHAR(30) NOT NULL,
  `id_medic` INT NOT NULL,
  `grad` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`nume_specializare`, `id_medic`),
  INDEX `id_medic` (`id_medic` ASC) VISIBLE,
  CONSTRAINT `specializare_medic_ibfk_1`
    FOREIGN KEY (`nume_specializare`)
    REFERENCES `proiect_v1`.`specializare` (`nume_specializare`),
  CONSTRAINT `specializare_medic_ibfk_2`
    FOREIGN KEY (`id_medic`)
    REFERENCES `proiect_v1`.`medic` (`id_medic`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `proiect_v1`.`venitlunartotal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proiect_v1`.`venitlunartotal` (
  `id_centru` INT NOT NULL,
  `suma` DECIMAL(10,2) NULL DEFAULT NULL,
  `an_luna_zi` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id_centru`),
  CONSTRAINT `venitlunartotal_ibfk_1`
    FOREIGN KEY (`id_centru`)
    REFERENCES `proiect_v1`.`centru` (`id_centru`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `proiect_v1` ;

-- -----------------------------------------------------
-- procedure GetAngajatiByCentruID
-- -----------------------------------------------------

DELIMITER $$
USE `proiect_v1`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAngajatiByCentruID`(
    IN p_id_centru INT,
    OUT p_employee_count INT
)
BEGIN
    IF p_id_centru = -1 THEN
        -- Return all employees from all centers
        SELECT *
        FROM proiect_v1.angajat;

        -- Get count of all employees from all centers
        SELECT COUNT(*) INTO p_employee_count
        FROM proiect_v1.angajat;
    ELSE
        -- Return employees of a specific center based on id_centru
        SELECT *
        FROM proiect_v1.angajat
        WHERE id_centru = p_id_centru;

        -- Get count of employees for a specific center
        SELECT COUNT(*) INTO p_employee_count
        FROM proiect_v1.angajat
        WHERE id_centru = p_id_centru;
    END IF;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure InsertAngajat
-- -----------------------------------------------------

DELIMITER $$
USE `proiect_v1`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertAngajat`(
    IN p_cnp VARCHAR(16),
    IN p_nume VARCHAR(20),
    IN p_prenume VARCHAR(20),
    IN p_adresa VARCHAR(50),
    IN p_nr_telefon VARCHAR(15),
    IN p_email VARCHAR(30),
    IN p_iban VARCHAR(30),
    IN p_numar_contract INT,
    IN p_functie VARCHAR(20),
    IN p_salariu_lunar INT,
    IN p_username VARCHAR(20),
    IN p_parola VARCHAR(33),
    IN p_id_centru INT
)
BEGIN
    INSERT INTO angajat (cnp, nume, prenume, adresa, nr_telefon, email, iban, numar_contract, functie, salariu_lunar, username, parola, id_centru)
    VALUES (p_cnp, p_nume, p_prenume, p_adresa, p_nr_telefon, p_email, p_iban, p_numar_contract, p_functie, p_salariu_lunar, p_username, p_parola, p_id_centru);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure SearchCentruByString
-- -----------------------------------------------------

DELIMITER $$
USE `proiect_v1`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SearchCentruByString`(IN search_string VARCHAR(100))
BEGIN
    SET @searchString = CONCAT('%', search_string, '%');
    SELECT *
    FROM proiect_v1.centru
    WHERE nume_centru LIKE @searchString;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure UpdateAngajatById
-- -----------------------------------------------------

DELIMITER $$
USE `proiect_v1`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateAngajatById`(
    IN p_id_angajat INT,
    IN p_cnp VARCHAR(16),
    IN p_nume VARCHAR(20),
    IN p_prenume VARCHAR(20),
    IN p_adresa VARCHAR(50),
    IN p_nr_telefon VARCHAR(15),
    IN p_email VARCHAR(30),
    IN p_iban VARCHAR(30),
    IN p_numar_contract INT,
    IN p_functie VARCHAR(20),
    IN p_salariu_lunar INT,
    IN p_username VARCHAR(20),
    IN p_parola VARCHAR(33),
    IN p_id_centru INT
)
BEGIN
    UPDATE angajat 
    SET cnp = p_cnp,
        nume = p_nume,
        prenume = p_prenume,
        adresa = p_adresa,
        nr_telefon = p_nr_telefon,
        email = p_email,
        iban = p_iban,
        numar_contract = p_numar_contract,
        functie = p_functie,
        salariu_lunar = p_salariu_lunar,
        username = p_username,
        parola = p_parola,
        id_centru = p_id_centru
    WHERE id_angajat = p_id_angajat;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure VerifyUser
-- -----------------------------------------------------

DELIMITER $$
USE `proiect_v1`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `VerifyUser`(
    IN p_username VARCHAR(20),
    IN p_password VARCHAR(33),
    IN p_centru VARCHAR(40),
    OUT p_result BOOLEAN
)
BEGIN
    DECLARE v_centru_id INT;
    DECLARE v_user_exists INT;
    DECLARE v_password_match INT;

    -- Get the ID of the centru based on the provided name
    SELECT id_centru INTO v_centru_id
    FROM proiect_v1.centru
    WHERE nume_centru = p_centru;

    -- Check if the centru exists
    IF v_centru_id IS NOT NULL THEN
        -- Check if the username exists in the specified centru
        SELECT COUNT(*) INTO v_user_exists
        FROM proiect_v1.angajat
        WHERE id_centru = v_centru_id AND username = p_username;

        -- If the username exists in the centru, check the password
        IF v_user_exists > 0 THEN
            SELECT COUNT(*) INTO v_password_match
            FROM proiect_v1.angajat
            WHERE id_centru = v_centru_id AND username = p_username AND parola = p_password;

            -- Set the result based on password match
            IF v_password_match > 0 THEN
                SET p_result = TRUE;
            ELSE
                SET p_result = FALSE;
            END IF;
        ELSE
            SET p_result = FALSE; -- Username does not exist in the centru
        END IF;
    ELSE
        SET p_result = FALSE; -- Centru does not exist
    END IF;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
