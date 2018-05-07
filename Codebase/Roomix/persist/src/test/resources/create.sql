DROP DATABASE IF EXISTS Roomix;

CREATE DATABASE Roomix;

USE Roomix;

CREATE TABLE Season (
	SeasonID INTEGER AUTO_INCREMENT,
	Description VARCHAR(150) NOT NULL,
	AdditionalCharge INTEGER NOT NULL,
	StartDate DATE NOT NULL,
	EndDate DATE NOT NULL,
	PRIMARY KEY (SeasonID)
);

CREATE TABLE RoomCategory(
	RoomCategoryID INTEGER AUTO_INCREMENT,
	CategoryDescription varchar(150) NOT NULL,
	PRIMARY KEY (RoomCategoryID)
);

CREATE TABLE Facility (
	FacilityID INTEGER AUTO_INCREMENT,
	Description VARCHAR(150) NOT NULL,
	AdditionalCharge DECIMAL(5) NOT NULL,
	PRIMARY KEY (FacilityID)
);

CREATE TABLE Room(
	RoomID INTEGER AUTO_INCREMENT,
	RoomCategory INTEGER NOT NULL,
	Status VARCHAR(20) NOT NULL,
	PRIMARY KEY (RoomID),
	FOREIGN KEY (RoomCategory) REFERENCES RoomCategory(RoomCategoryID)
);

CREATE TABLE RoomFacility(
	Room INTEGER NOT NULL,
	Facility INTEGER NOT NULL,
	Amount INTEGER NOT NULL,
	PRIMARY KEY (Room, Facility),
	FOREIGN KEY (Facility) REFERENCES Facility(FacilityID),
	FOREIGN KEY (Room) REFERENCES Room(RoomID)
);

CREATE TABLE RoomCategoryPrice(
	RoomCategoryPriceID INTEGER AUTO_INCREMENT,
	RoomCategory INTEGER NOT NULL,
	Season INTEGER NOT NULL,
	ListPrice DECIMAL(5) NOT NULL,
	AcquisitionPrice DECIMAL(5) NOT NULL,
	MinimumPrice DECIMAL(5) NOT NULL,
	DayPrice DECIMAL(5),
	PRIMARY KEY (RoomCategoryPriceID),
	FOREIGN KEY (RoomCategory) REFERENCES RoomCategory(RoomCategoryID),
	FOREIGN KEY (Season) REFERENCES Season(SeasonID)
);

CREATE TABLE Contact(
	ContactID INTEGER AUTO_INCREMENT,
	FirstName varchar(50) NOT NULL,
	LastName varchar(50) NOT NULL,
	CompanyName VARCHAR(50),
	PhoneNumber VARCHAR(50) NOT NULL,
	Street varchar(50)NOT NULL,
  HouseNumber varchar(50) NOT NULL,
	Place varchar(50)NOT NULL,
	Postcode varchar(50)NOT NULL,
	Country varchar(30)NOT NULL,
	Email varchar(50)NOT NULL,
	Active BOOLEAN NOT NULL DEFAULT TRUE,
	PRIMARY KEY (ContactID)
);

CREATE TABLE ContactNote(
	ContactNoteID INTEGER AUTO_INCREMENT,
	Contact INTEGER NOT NULL,
	NoteContent VARCHAR(500) NOT NULL,
	PRIMARY KEY (ContactNoteID),
	FOREIGN KEY (Contact) REFERENCES Contact(ContactID)
);

CREATE TABLE CreditCard(
	CreditCardID INTEGER AUTO_INCREMENT,
	CardNumber VARCHAR(50) NOT NULL,
	CardOwner VARCHAR(50),
	CardType VARCHAR(10) NOT NULL,
	ValidDate DATE NOT NULL,
	Contact INTEGER,
	PRIMARY KEY (CreditCardID),
	FOREIGN KEY (Contact) REFERENCES Contact(ContactID)
);

CREATE TABLE Person (
	PersonID INTEGER AUTO_INCREMENT,
	FirstName VARCHAR(50) NOT NULL,
	LastName VARCHAR(50) NOT NULL,
	IsVIP BOOLEAN NOT NULL,
	Archive BOOLEAN NOT NULL,
	Contact INTEGER,
	PRIMARY KEY (PersonID),
	FOREIGN KEY (Contact) REFERENCES Contact(ContactID)
);

CREATE TABLE ContractingParty(
	ContractingPartyID INTEGER AUTO_INCREMENT,
	ContractingPartyType varchar(50) NOT NULL,
	Contact INTEGER NOT NULL,
	PRIMARY KEY (ContractingPartyID),
	FOREIGN KEY (Contact) REFERENCES Contact(ContactID)
);

CREATE TABLE CancellationCondition(
	CancellationConditionID INTEGER AUTO_INCREMENT,
	CancellationFee DECIMAL(5) NOT NULL,
	DaysBeforeArrival INTEGER NOT NULL,
	PRIMARY KEY (CancellationConditionID)
);

CREATE TABLE Article(
	ArticleID INTEGER AUTO_INCREMENT,
	ArticleDescription VARCHAR(50),
	ArticleType VARCHAR(30),
  Price DECIMAL(5),
	PRIMARY KEY (ArticleID)
);

CREATE TABLE Cancellation(
	CancellationID INTEGER AUTO_INCREMENT,
	CancellationCondition INTEGER NOT NULL,
	CancellationDate TIMESTAMP NOT NULL DEFAULT NOW(),
	Description varchar(150),
	PRIMARY KEY (CancellationID),
	FOREIGN KEY (CancellationCondition) REFERENCES CancellationCondition(CancellationConditionID)
);

CREATE TABLE PartnerAgreement(
	AgreementID INTEGER AUTO_INCREMENT,
	ContractingParty INTEGER NOT NULL,
	CancellationCondition INTEGER NOT NULL,
	StartDate DATE NOT NULL,
	ExpiringDate DATE NOT NULL,
    RoomCategory INTEGER,
    CountRoomCategory INTEGER,
	Discount INTEGER NOT NULL DEFAULT 0,
	PRIMARY KEY (AgreementID),
	FOREIGN KEY (ContractingParty) REFERENCES ContractingParty(ContractingPartyID),
	FOREIGN KEY (CancellationCondition) REFERENCES CancellationCondition(CancellationConditionID),
    FOREIGN KEY (RoomCategory) REFERENCES RoomCategory(RoomCategoryID)
);

CREATE TABLE PaymentType(
	PaymentTypeID INTEGER AUTO_INCREMENT,
	PaymentTypeDescription VARCHAR(150),
	PRIMARY KEY (PaymentTypeID)
);

CREATE TABLE ReservationOption(
	OptionID INTEGER AUTO_INCREMENT,
	DaysBeforeArrival INTEGER NOT NULL,
	OptionDescription varchar(150) NOT NULL,
	OptionStatus VARCHAR(15) NOT NULL,
	OptionPercentage INTEGER NOT NULL,
	PRIMARY KEY (OptionID)
);

CREATE TABLE Reservation(
	ReservationID INTEGER AUTO_INCREMENT,
	ReservationOption INTEGER,
	ContractingParty INTEGER NOT NULL,
	PaymentType INTEGER NOT NULL,
	ReservationStatus VARCHAR(15) NOT NULL,
	ReservationComment VARCHAR(1024),
	PRIMARY KEY (ReservationID),
	FOREIGN KEY (ContractingParty) REFERENCES ContractingParty(ContractingPartyID),
	FOREIGN KEY (PaymentType) REFERENCES PaymentType(PaymentTypeID),
	FOREIGN KEY (ReservationOption) REFERENCES ReservationOption(OptionID)
);

CREATE TABLE PersonReservation(
	Reservation INTEGER NOT NULL,
	Person INTEGER NOT NULL,
	PRIMARY KEY (Reservation, Person),
	FOREIGN KEY (Reservation) REFERENCES Reservation(ReservationID),
	FOREIGN KEY (Person) REFERENCES Person(PersonID)
);

CREATE TABLE TourGroup (
    TourGroupID INTEGER AUTO_INCREMENT,
	TourGroupName VARCHAR(50) NOT NULL,
	TourGroupLeader INTEGER NOT NULL,
	PRIMARY KEY (TourGroupID),
	FOREIGN KEY (TourGroupLeader) REFERENCES Person(PersonID)
);

CREATE TABLE TourGroupMember (
	TourGroupID INTEGER NOT NULL,
	TourGroupMember INTEGER NOT NULL,
	PRIMARY KEY (TourGroupID, TourGroupMember),
	FOREIGN KEY (TourGroupID) REFERENCES TourGroup(TourGroupID),
	FOREIGN KEY (TourGroupMember) REFERENCES Person(PersonID)
);

CREATE TABLE Invoice(
	InvoiceID INTEGER AUTO_INCREMENT,
	FirstName varchar(50) NOT NULL,
	LastName varchar(50) NOT NULL,
	CompanyName VARCHAR(50),
	Street varchar(50)NOT NULL,
    HouseNumber varchar(50) NOT NULL,
	Place varchar(50)NOT NULL,
	Postcode varchar(50)NOT NULL,
	Country varchar(30)NOT NULL,
	Contact INTEGER NOT NULL,
	DeterminationDate TIMESTAMP NOT NULL DEFAULT NOW(),
	Status VARCHAR(15) NOT NULL,
	PRIMARY KEY (InvoiceID),
	FOREIGN KEY (Contact) REFERENCES Contact(ContactID)
);

CREATE TABLE Payment(
	PaymentID INTEGER AUTO_INCREMENT,
	Invoice INTEGER NOT NULL,
	PaymentType INTEGER NOT NULL,
	TotalPrice DECIMAL(5) NOT NULL,
	DueDate DATE NOT NULL,
	PaidDate DATE,
	PRIMARY KEY (PaymentID),
	FOREIGN KEY (Invoice) REFERENCES Invoice(InvoiceID),
	FOREIGN KEY (PaymentType) REFERENCES PaymentType(PaymentTypeID)
);

CREATE TABLE ReservationUnit(
	ReservationUnitID INTEGER AUTO_INCREMENT,
	Reservation INTEGER NOT NULL,
	RoomCategory INTEGER NOT NULL,
	Cancellation INTEGER,
	ArrivalTime TIME,
	StartDate DATE NOT NULL,
	EndDate DATE NOT NULL,
	PRIMARY KEY (ReservationUnitID),
	FOREIGN KEY (Reservation) REFERENCES Reservation(ReservationID),
	FOREIGN KEY (RoomCategory) REFERENCES RoomCategory(RoomCategoryID),
	FOREIGN KEY (Cancellation) REFERENCES Cancellation(CancellationID)
);

CREATE TABLE RoomAssignment(
	RoomAssignmentID INTEGER AUTO_INCREMENT,
	ArrivalDate DATE NOT NULL,
	DepartureDate DATE NOT NULL,
	Room INTEGER NOT NULL,
	ReservationUnit INTEGER NOT NULL,
	PRIMARY KEY (RoomAssignmentID),
	FOREIGN KEY (Room) REFERENCES Room(RoomID),
	FOREIGN KEY (ReservationUnit) REFERENCES ReservationUnit(ReservationUnitID)
);

CREATE TABLE PersonRoomAssignment(
    RoomAssignment INTEGER NOT NULL,
	Person INTEGER NOT NULL,
	PRIMARY KEY (RoomAssignment, Person),
	FOREIGN KEY (RoomAssignment) REFERENCES RoomAssignment(RoomAssignmentID),
	FOREIGN KEY (Person) REFERENCES Person(PersonID)
);

CREATE TABLE InvoicePosition (
	InvoicePositionID INTEGER AUTO_INCREMENT,
	Reservation INTEGER NOT NULL,
	ReservationUnit INTEGER NOT NULL,
	Invoice INTEGER,
	RoomAssignment INTEGER,
	Article INTEGER,
  	Comment VARCHAR(200),
	PaidFlag INTEGER,
	Price DECIMAL(5) NOT NULL ,
	Count INTEGER NOT NULL DEFAULT 1,
	PRIMARY KEY (InvoicePositionID),
	FOREIGN KEY (Reservation) REFERENCES Reservation(ReservationID),
	FOREIGN KEY (ReservationUnit) REFERENCES ReservationUnit(ReservationUnitID),
	FOREIGN KEY (Invoice) REFERENCES Invoice(InvoiceID),
  	FOREIGN KEY (RoomAssignment) REFERENCES RoomAssignment(RoomAssignmentID),
  	FOREIGN KEY (Article) REFERENCES Article(ArticleID)
);


INSERT INTO Roomix.Contact(ContactID, FirstName, LastName, CompanyName, PhoneNumber, Street, HouseNumber, Place, Postcode, Country, Email)
VALUES (null, "Per", "Carlsson", "Phasellus At Augue Ltd", "05240 281698", "352 Felis Av.", 38, "Swan Hills", "62589",
              "United Kingdom (Great Britain)", "et@malesuadaiderat.ca"),
  (null, "Erik", "Carlsson", "Sapien Cras Dolor LLC", "03074 955115", "Ap #947-5641 Cras Av.", 32, "Hoeke", "326387",
         "Libya", "nunc.risus.varius@dui.org"),
  (null, "Thomas", "Bodin", "Vestibulum Ut Eros Company", "08494 225247", "Ap #643-6525 Nisi. Av.", 65, "Salamanca",
         "01080", "Canada", "libero.est.congue@ligulatortordictum.ca"),
  (null, "Henrik", "Eriksson", "Magnis Dis Foundation", "02359 560026", "P.O. Box 428, 9422 Et St.", 70, "Parrano",
         "50010", "Russian Federation", "Maecenas.malesuada@consectetuereuismodest.net"),
  (null, "Eva", "Svensson", "Aliquam Ltd", "04400 916213", "P.O. Box 319, 5295 Vulputate Av.", 17, "Enns", "7893 LU",
         "Uganda", "Donec@vel.edu"),
  (null, "Anna", "Ericsson", "Nunc Est Industries", "05262 992406", "4588 A Street", 6, "Houthalen", "03522",
         "Jordan", "luctus@nonummyut.org"),
  (null, "Elena", "Persson", "Mauris Ltd", "02827 278412", "8694 Aliquam, Av.", 39, "Bouffioulx", "8950 EV",
         "Kyrgyzstan", "elit.pretium.et@acfacilisisfacilisis.org"),
  (null, "Eleonor", "Bodin", "Diam Proin LLC", "04872 801455", "P.O. Box 127, 552 Dolor. Road", 72, "Sedgewick",
         "900885", "Laos", "commodo@Vivamus.co.uk"),
  (null, "Eleonor", "Staffansson", "Natoque Inc.", "08350 994200", "8278 Condimentum. St.", 47,
         "Whitchurch-Stouffville", "22521", "Jordan", "amet.orci@vulputate.com"),
  (null, "Erika", "Karlsson", "Suspendisse Dui Company", "05910 405806", "636-7882 Augue Avenue", 10, "Saint-Prime",
         "C78 5GU", "Ukraine", "lacus.Quisque@consectetueradipiscingelit.org");

INSERT INTO RoomCategory(CategoryDescription)
    VALUE ("Einzelbett"),
          ("Doppelbett"),
          ("Suite");

INSERT INTO Season(Description, AdditionalCharge, StartDate, EndDate)
    VALUE ("Vorseason", 0, STR_TO_DATE('01-01-2018', '%d-%m-%Y'),STR_TO_DATE('01-05-2018', '%d-%m-%Y')),
  ("Hauptseason", 4500, STR_TO_DATE('02-05-2018', '%d-%m-%Y'), STR_TO_DATE('20-10-2018', '%d-%m-%Y'));

INSERT INTO RoomCategoryPrice(RoomCategory, Season, ListPrice, AcquisitionPrice, MinimumPrice, DayPrice)
    VALUE (1, 1, 5000, 2000, 2500, 4500),
  (2, 1, 8000, 2000, 3500, 7500),
  (3, 1, 25000, 4000, 4500, 15000);

INSERT INTO Article(ArticleDescription, ArticleType, Price) VALUE
  ("Cola - 1,5L", "ARTICLE", 500),
  ("Fanta - 1,5L", "ARTICLE", 500),
  ("Sprite - 1,5L", "ARTICLE", 500),
  ("Massage 1h", "SERVICE", 5000),
  ("Massage 30min", "SERVICE", 3000),
  ("Vollpension", "ARRANGEMENT", 10000);

INSERT INTO PaymentType(PaymentTypeDescription) VALUE
  ("CASH"),
  ("DEBIT"),
  ("CREDIT")
