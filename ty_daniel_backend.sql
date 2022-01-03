CREATE DATABASE ty_daniel_db;
GO

USE ty_daniel_db;
--DROP DATABASE ty_daniel_db;

CREATE TABLE DBUser (
	Username VARCHAR(64),
	Email VARCHAR(64),
	FName VARCHAR(64),
	LastName VARCHAR(64),
	PRIMARY KEY (Username)
);

CREATE TABLE VideoCard (
	GPUName VARCHAR(64),
	Chipset VARCHAR(64),
	CoreClock VARCHAR(64),
	GPULength Decimal(5, 1),
	Price DECIMAL(10, 2),
	PRIMARY KEY (GPUName),
);

CREATE TABLE Memory (
	MemoryName VARCHAR(64),
	Speed CHAR(9),
	Modules VARCHAR(64),
	Price DECIMAL(10, 2),
	PRIMARY KEY (MemoryName),
);

CREATE TABLE Storage (
	StorageName VARCHAR(64),
	Capacity VARCHAR(64),
	StorageType VARCHAR(64),
	FormFactor VARCHAR(64), 
	Interface VARCHAR(64), 
	Price DECIMAL(10, 2),
	PRIMARY KEY (StorageName),
);

CREATE TABLE Monitor (
	MonitorName VARCHAR(64),
	ScreenSize VARCHAR(64),
	Resolution VARCHAR(64),
	RefreshRate VARCHAR(64),
	Price DECIMAL(10, 2),
	PRIMARY KEY (MonitorName),
);

CREATE TABLE CPU (
	CPUName VARCHAR(64),
	Socket VARCHAR(64),
	CoreCount VARCHAR(64),
	CoreClock VARCHAR(64),
	TDP int,
	Price DECIMAL(10, 2),
	PRIMARY KEY (CPUName),
);

CREATE TABLE CPUCooler (
	CoolerName VARCHAR(64),
	RPM VARCHAR(64),
	RadiatorSize VARCHAR(64),
	Price DECIMAL(10, 2),
	PRIMARY KEY (CoolerName),
);

CREATE TABLE PowerSupply (
	PSUName VARCHAR(64),
	FormFactor VARCHAR(64),
	Efficiency VARCHAR(64),
	Wattage int,
	Price DECIMAL(10, 2),
	PRIMARY KEY (PSUName),
);

CREATE TABLE Motherboard (
	MoboName VARCHAR(64),
	Socket VARCHAR(64),
	FormFactor VARCHAR(64),
	MemSlots int,
	Price DECIMAL(10, 2),
	PRIMARY KEY (MoboName),
);

CREATE TABLE PCCase (
	CaseName VARCHAR(64),
	CaseType VARCHAR(64),
	ExtBays int,
	IntBays int, 
	Price DECIMAL(10, 2),
	PRIMARY KEY (CaseName),
);

CREATE TABLE OS (
	OSName VARCHAR(64),
	OSType VARCHAR(64),
	OSMode VARCHAR(64),
	Price DECIMAL(10, 2),
	PRIMARY KEY (OSName),
);

CREATE TABLE Mouse (
	MouseName VARCHAR(64),
	Connection VARCHAR(64),
	MaxDPI int,
	Price DECIMAL(10, 2),
	PRIMARY KEY (MouseName),
);

CREATE TABLE Keyboard (
	KeyboardName VARCHAR(64),
	Connection VARCHAR(64),
	SwitchType VARCHAR(64), 
	Price DECIMAL(10, 2),
	PRIMARY KEY (KeyboardName),
);

CREATE TABLE PCBuild (
	Username VARCHAR(64),
	BuildName VARCHAR(64),
	CPUName VARCHAR(64),
	CoolerName VARCHAR(64),
	PSUName VARCHAR(64),
	MoboName VARCHAR(64),
	CaseName VARCHAR(64),
	OSName VARCHAR(64),
	MouseName VARCHAR(64),
	KeyboardName VARCHAR(64),
	FOREIGN KEY (Username) REFERENCES DBUser(Username) ON DELETE CASCADE,
	FOREIGN KEY (CPUName) REFERENCES CPU(CPUName),
	FOREIGN KEY (CoolerName) REFERENCES CPUCooler(CoolerName),
	FOREIGN KEY (PSUName) REFERENCES PowerSupply(PSUName),
	FOREIGN KEY (MoboName) REFERENCES Motherboard(MoboName),
	FOREIGN KEY (CaseName) REFERENCES PCCase(CaseName),
	FOREIGN KEY (OSName) REFERENCES OS(OSName),
	FOREIGN KEY (MouseName) REFERENCES Mouse(MouseName),
	FOREIGN KEY (KeyboardName) REFERENCES Keyboard(KeyboardName),
	PRIMARY KEY (Username, BuildName)
);

CREATE TABLE BuildGpu (
	Username VARCHAR(64),
	BuildName VARCHAR(64),
	GPU# int,
	GPUName	VARCHAR(64),
	FOREIGN KEY (Username, BuildName) REFERENCES PCBuild(Username, BuildName) ON DELETE CASCADE,
	FOREIGN KEY (GPUName) REFERENCES VideoCard(GPUName),
	PRIMARY KEY (Username, BuildName, GPU#)
);

CREATE TABLE BuildMemory (
	Username VARCHAR(64),
	BuildName VARCHAR(64),
	Memory# int,
	MemoryName	VARCHAR(64),
	FOREIGN KEY (Username, BuildName) REFERENCES PCBuild(Username, BuildName) ON DELETE CASCADE,
	FOREIGN KEY (MemoryName) REFERENCES Memory(MemoryName),
	PRIMARY KEY (Username, BuildName, Memory#)
);

CREATE TABLE BuildStorage (
	Username VARCHAR(64),
	BuildName VARCHAR(64),
	Storage# int,
	StorageName	VARCHAR(64),
	FOREIGN KEY (Username, BuildName) REFERENCES PCBuild(Username, BuildName) ON DELETE CASCADE,
	FOREIGN KEY (StorageName) REFERENCES Storage(StorageName),
	PRIMARY KEY (Username, BuildName, Storage#)
);

CREATE TABLE BuildMonitor (
	Username VARCHAR(64),
	BuildName VARCHAR(64),
	Monitor# int,
	MonitorName	VARCHAR(64),
	FOREIGN KEY (Username, BuildName) REFERENCES PCBuild(Username, BuildName) ON DELETE CASCADE,
	FOREIGN KEY (MonitorName) REFERENCES Monitor(MonitorName),
	PRIMARY KEY (Username, BuildName, Monitor#)
);

INSERT INTO DBUser VALUES('tyd3', 'tyd3@uw.edu', 'Daniel', 'Ty');
INSERT INTO DBUser VALUES('test', 'test@test.com', 'Test', 'Tester');

INSERT INTO VideoCard VALUES('Asus Phoenix', 'GeForce GTX 1050 Ti', '1290 MHz', 192, 349.99);
INSERT INTO VideoCard VALUES('EVGA XC BLACK GAMING', 'GeForce GTX 1660', '1530 MHz', 189.89, 879.99);
INSERT INTO VideoCard VALUES('PowerColor Red Devil', 'Radeon RX 570', '1168 MHz', 310, 556.48);

INSERT INTO Memory VALUES('Corsair Vengeance LPX 16GB', 'DDR4-3200', '2 x 8GB', 91.99);
INSERT INTO Memory VALUES('Crucial CT4G4DFS824A 4 GB', 'DDR4-2400', '1 x 4GB', 24.99);
INSERT INTO Memory VALUES('G.Skill Trident Z RGB 16 GB', 'DDR4-3600', '2 x 8GB', 104.99);

INSERT INTO Storage VALUES('Samsung 860 Evo', '1 TB', 'SSD', '2.5"', 'SATA 6 Gb/s', 109.99);
INSERT INTO Storage VALUES('Seagate Barracuda Compute', '2 TB', '7200RPM', '3.5"', 'SATA 6 Gb/s', 55.49);
INSERT INTO Storage VALUES('Samsung 980 Pro', '1 TB', 'SSD', 'M.2-2280', 'M.2 (M)', 209.99);

INSERT INTO Monitor VALUES('MSI Optix G241', '23.8"', '1920 x 1080', '144 Hz', 189.99);
INSERT INTO Monitor VALUES('AOC C24G1', '24.0"', '1920 x 1080', '144 Hz', 180.99);
INSERT INTO Monitor VALUES('Asus VG248QG', '24.0"', '1920 x 1080', '165 Hz', 190.99);

INSERT INTO CPU VALUES('AMD Ryzen 5 3600', 'AM4','6', '4.2 GHz', 65, 219.98);
INSERT INTO CPU VALUES('AMD Ryzen 3 3200G', 'AM4','4', '3.6 GHz', 65, 178.95);
INSERT INTO CPU VALUES('Intel Core i7-10700K', 'LGA1200','8', '5.1 GHz', 125, 317.95);

INSERT INTO CPUCooler VALUES('NZXT Kraken X53', '500 - 2000 RPM', 240, 129.99);
INSERT INTO CPUCooler VALUES('Cooler Master MASTERLIQUID ML240L RGB V2', '650 - 1800 RPM', 240, 77.56);
INSERT INTO CPUCooler VALUES('Corsair iCUE H100i ELITE CAPELLIX', '	2400 RPM', 240, 147.12);

INSERT INTO PowerSupply VALUES('Antec High Current Gamer Gold', 'ATX', '80+ Gold', 650, 102.88);
INSERT INTO PowerSupply VALUES('Corsair RM (2019)', 'ATX', '80+ Gold', 750, 116.82);
INSERT INTO PowerSupply VALUES('EVGA SuperNOVA GA', 'ATX', '80+ Gold', 650, 79.00);

INSERT INTO Motherboard VALUES('Asus ROG Strix X570-I Gaming', 'AM4', 'Mini ITX', 2, 249.99);
INSERT INTO Motherboard VALUES('Asus ROG STRIX Z490-G GAMING (WI-FI)', 'LGA1200', 'Micro ATX', 4, 389.99);
INSERT INTO Motherboard VALUES('MSI A320M-A PRO MAX', 'AM4', 'Micro ATX', 2, 86.31);

INSERT INTO PCCase VALUES('Cooler Master MasterBox Q300L', 'MicroATX Mini Tower', 0, 1, 48.49);
INSERT INTO PCCase VALUES('NZXT H510', 'ATX Mid Tower', 0, 2, 66.98);
INSERT INTO PCCase VALUES('Corsair 4000D Airflow', 'ATX Mid Tower', 0, 2, 84.99);

INSERT INTO OS VALUES('Microsoft Windows 10 Home (64-bit)', 'Windows', '64-bit', 99.99);
INSERT INTO OS VALUES('Microsoft Windows 10 Pro (64-bit)', 'Windows', '64-bit', 139.88);
INSERT INTO OS VALUES('Microsoft Windows 7 Ultimate SP1 (32-bit)', 'Windows', '32-bit', 169.99);

INSERT INTO Mouse VALUES('Logitech G502 HERO', 'Wired', 25600, 46.99);
INSERT INTO Mouse VALUES('Razer Viper Ultimate', 'Wireless', 20000, 119.02);
INSERT INTO Mouse VALUES('Corsair HARPOON RGB PRO', 'Wired', 12000, 28.43);

INSERT INTO Keyboard VALUES('Corsair K68 RGB', 'Wired', 'Cherry MX Red', 116.00);
INSERT INTO Keyboard VALUES('Redragon K552', 'Wired', 'Outemu Blue', 31.99);
INSERT INTO Keyboard VALUES('SteelSeries Apex Pro TKL', 'Wired', 'SteelSeries QX2 OmniPoint', 179.99);

INSERT INTO PCBuild VALUES('tyd3', 'BigBuild','AMD Ryzen 5 3600', 
	'NZXT Kraken X53', 'Antec High Current Gamer Gold', 
	'Asus ROG Strix X570-I Gaming','Cooler Master MasterBox Q300L',
	'Microsoft Windows 10 Home (64-bit)', 'Logitech G502 HERO',
	'Corsair K68 RGB');
INSERT INTO PCBuild VALUES('tyd3', 'SmallBuild','AMD Ryzen 5 3600', 
	'NZXT Kraken X53', 'Antec High Current Gamer Gold', 
	'Asus ROG Strix X570-I Gaming','Cooler Master MasterBox Q300L',
	'Microsoft Windows 10 Home (64-bit)', 'Logitech G502 HERO', 
	'Corsair K68 RGB');
INSERT INTO PCBuild VALUES('test', 'TestBuild','Intel Core i7-10700K', 
	'Corsair iCUE H100i ELITE CAPELLIX', 'Antec High Current Gamer Gold',
	'Asus ROG STRIX Z490-G GAMING (WI-FI)', 'Corsair 4000D Airflow',
	'Microsoft Windows 10 Pro (64-bit)', 'Razer Viper Ultimate', 
	'SteelSeries Apex Pro TKL');

INSERT INTO BuildGpu VALUES('tyd3', 'BigBuild', 1, 'Asus Phoenix');
INSERT INTO BuildGpu VALUES('tyd3', 'BigBuild', 2, 'EVGA XC BLACK GAMING');
INSERT INTO BuildGpu VALUES('tyd3', 'SmallBuild', 1, 'Asus Phoenix');
INSERT INTO BuildGpu VALUES('test', 'TestBuild', 1, 'PowerColor Red Devil');

INSERT INTO BuildMemory VALUES('tyd3', 'BigBuild', 1, 'Corsair Vengeance LPX 16GB');
INSERT INTO BuildMemory VALUES('tyd3', 'BigBuild', 2, 'Corsair Vengeance LPX 16GB');
INSERT INTO BuildMemory VALUES('tyd3', 'SmallBuild', 1, 'Corsair Vengeance LPX 16GB');
INSERT INTO BuildMemory VALUES('test', 'TestBuild', 1, 'G.Skill Trident Z RGB 16 GB');


INSERT INTO BuildStorage VALUES('tyd3', 'BigBuild', 1, 'Samsung 860 Evo');
INSERT INTO BuildStorage VALUES('tyd3', 'BigBuild', 2, 'Samsung 980 Pro');
INSERT INTO BuildStorage VALUES('tyd3', 'BigBuild', 3, 'Seagate Barracuda Compute');
INSERT INTO BuildStorage VALUES('tyd3', 'SmallBuild', 1, 'Samsung 860 Evo');
INSERT INTO BuildStorage VALUES('test', 'TestBuild', 1, 'Samsung 980 Pro');


INSERT INTO BuildMonitor VALUES('tyd3', 'BigBuild', 1, 'MSI Optix G241');
INSERT INTO BuildMonitor VALUES('tyd3', 'BigBuild', 2, 'MSI Optix G241');
INSERT INTO BuildMonitor VALUES('tyd3', 'BigBuild', 3, 'Asus VG248QG');
INSERT INTO BuildMonitor VALUES('tyd3', 'SmallBuild', 1, 'MSI Optix G241');
INSERT INTO BuildMonitor VALUES('test', 'TestBuild', 1, 'Asus VG248QG');


--SELECT * FROM DBUser;
--SELECT * FROM VideoCard;
--SELECT * FROM Memory;
--SELECT * FROM Storage;
--SELECT * FROM Monitor;
--SELECT * FROM CPU;
--SELECT * FROM CPUCooler;
--SELECT * FROM PowerSupply;
--SELECT * FROM Motherboard;
--SELECT * FROM PCCase;
--SELECT * FROM OS;
--SELECT * FROM Mouse;
--SELECT * FROM Keyboard;
--SELECT * FROM PCBuild;
--SELECT * FROM BuildGpu;
--SELECT * FROM BuildMemory;
--SELECT * FROM BuildStorage;
--SELECT * FROM BuildMonitor;