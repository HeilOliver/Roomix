
CREATE TABLE IF NOT EXISTS Roomix.PersonReservation( Reservation INTEGER NOT NULL, Person INTEGER NOT NULL, PRIMARY KEY (Reservation, Person), FOREIGN KEY (Reservation) REFERENCES Reservation(ReservationID), FOREIGN KEY (Person) REFERENCES Person(PersonID) );

CREATE TABLE IF NOT EXISTS Roomix.PersonRoomAssignment( Unit INTEGER NOT NULL, Person INTEGER NOT NULL, PRIMARY KEY (Unit, Person), FOREIGN KEY (Unit) REFERENCES ReservationUnit(ReservationUnitID), FOREIGN KEY (Person) REFERENCES Person(PersonID) );

DELETE FROM Roomix.PersonReservation;

INSERT INTO Roomix.Contact(ContactID, FirstName, LastName, CompanyName, PhoneNumber, Street, HouseNumber, Place, Postcode, Country, Email, Active) VALUES (null, 'Per', 'Carlsson', 'Phasellus At Augue Ltd', '05240 281698', '352 Felis Av.', 38, 'Swan Hills', '62589', 'United Kingdom (Great Britain)', 'et@malesuadaiderat.ca', true), (null, 'Erik', 'Carlsson', 'Sapien Cras Dolor LLC', '03074 955115', 'Ap #947-5641 Cras Av.', 32, 'Hoeke', '326387', 'Libya', 'nunc.risus.varius@dui.org', true), (null, 'Thomas', 'Bodin', 'Vestibulum Ut Eros Company', '08494 225247', 'Ap #643-6525 Nisi. Av.', 65, 'Salamanca', '01080', 'Canada', 'libero.est.congue@ligulatortordictum.ca', true), (null, 'Henrik', 'Eriksson', 'Magnis Dis Foundation', '02359 560026', 'P.O. Box 428, 9422 Et St.', 70, 'Parrano', '50010', 'Russian Federation', 'Maecenas.malesuada@consectetuereuismodest.net', true), (null, 'Eva', 'Svensson', 'Aliquam Ltd', '04400 916213', 'P.O. Box 319, 5295 Vulputate Av.', 17, 'Enns', '7893 LU', 'Uganda', 'Donec@vel.edu', true), (null, 'Anna', 'Ericsson', 'Nunc Est Industries', '05262 992406', '4588 A Street', 6, 'Houthalen', '03522', 'Jordan', 'luctus@nonummyut.org', true), (null, 'Elena', 'Persson', 'Mauris Ltd', '02827 278412', '8694 Aliquam, Av.', 39, 'Bouffioulx', '8950 EV', 'Kyrgyzstan', 'elit.pretium.et@acfacilisisfacilisis.org', true), (null, 'Eleonor', 'Bodin', 'Diam Proin LLC', '04872 801455', 'P.O. Box 127, 552 Dolor. Road', 72, 'Sedgewick', '900885', 'Laos', 'commodo@Vivamus.co.uk', true), (null, 'Eleonor', 'Staffansson', 'Natoque Inc.', '08350 994200', '8278 Condimentum. St.', 47, 'Whitchurch-Stouffville', '22521', 'Jordan', 'amet.orci@vulputate.com', true), (null, 'Erika', 'Karlsson', 'Suspendisse Dui Company', '05910 405806', '636-7882 Augue Avenue', 10, 'Saint-Prime', 'C78 5GU', 'Ukraine', 'lacus.Quisque@consectetueradipiscingelit.org', true);

INSERT INTO Roomix.RoomCategory(RoomCategoryID, CategoryDescription) VALUES (1,'Einzelbett'), (2,'Doppelbett'), (3,'Suite');

INSERT INTO Roomix.Season(Description, AdditionalCharge, StartDate, EndDate) VALUES('Vorseason', 0, PARSEDATETIME('01-01-2018', 'dd-mm-yyyy'),PARSEDATETIME('01-05-2022', 'dd-mm-yyyy')), ('Hauptseason', 4500, PARSEDATETIME('02-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('20-10-2020', 'dd-mm-yyyy'));

INSERT INTO Roomix.RoomCategoryPrice(RoomCategory, Season, ListPrice, AcquisitionPrice, MinimumPrice, DayPrice) VALUES(1, 1, 5000, 2000, 2500, 4500), (2, 1, 8000, 2000, 3500, 7500), (3, 1, 25000, 4000, 4500, 15000);

INSERT INTO Roomix.Article(ArticleID, ArticleDescription, ArticleType, Price) VALUES (10,'Cola - 1,5L', 'ARTICLE', 500), (11,'Fanta - 1,5L', 'ARTICLE', 500), (12,'Sprite - 1,5L', 'ARTICLE', 500), (13,'Massage 1h', 'SERVICE', 5000), (14,'Massage 30min', 'SERVICE', 3000), (15,'Vollpension', 'ARRANGEMENT', 10000);

INSERT INTO Roomix.PaymentType(PaymentTypeID, PaymentTypeDescription) VALUES (1, 'CASH'), (2, 'DEBIT'), (3, 'CREDIT');

INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (100, 1, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (101, 1, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (102, 1, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (103, 1, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (104, 1, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (105, 1, 'FREE_CLEAN');

INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (200, 2, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (201, 2, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (202, 2, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (203, 2, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (204, 2, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (205, 2, 'FREE_CLEAN');

INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (300, 3, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (301, 3, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (302, 3, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (303, 3, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (304, 3, 'FREE_CLEAN');
INSERT INTO Roomix.Room(RoomID, RoomCategory, Status) VALUES (305, 3, 'FREE_CLEAN');

INSERT INTO Roomix.ContractingParty(ContractingPartyID, ContractingPartyType, Contact) VALUES (200, 'INDIVIDUAL', 3), (300, 'INDIVIDUAL', 4), (400, 'INDIVIDUAL', 5);

INSERT INTO Roomix.ReservationOption (OptionID, DaysBeforeArrival, OptionDescription, OptionStatus, OptionPercentage) VALUES (10,10, 'Anzahlung, keine Überziehung!', 'PAYED', 20), (11,11, 'Anzahlung', 'PAYED', 20);

INSERT INTO Roomix.Reservation(ReservationID, ReservationOption, ContractingParty, PaymentType, ReservationStatus, ReservationComment) VALUES (9402204, NULL , 200, 1, 'NEW', NULL ), (6014321, NULL , 300, 2, 'NEW', NULL ), (6212124, NULL , 300, 2, 'NEW', 'Hat ein Meeting mit Herr Mustermann' ), (2121458, NULL , 400, 3, 'NEW', NULL ), (5415458, NULL , 200, 3, 'NEW', 'Kommt ggf nicht' );

INSERT INTO Roomix.ReservationUnit(ReservationUnitID, Reservation, RoomCategory, Cancellation, ArrivalTime, StartDate, EndDate, Price, Status) VALUES (100,9402204, 1, NULL , PARSEDATETIME('12:00:00', 'hh:mm:ss') , PARSEDATETIME('09-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('15-05-2018', 'dd-mm-yyyy'), 60000, 'NEW'), (101,6014321, 1, NULL , PARSEDATETIME('16:00:00', 'hh:mm:ss') , PARSEDATETIME('09-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('15-05-2018', 'dd-mm-yyyy'), 60000, 'NEW'), (102,6014321, 2, NULL , PARSEDATETIME('11:00:00', 'hh:mm:ss') , PARSEDATETIME('09-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('23-05-2018', 'dd-mm-yyyy'), 60000, 'NEW'), (103,6212124, 2, NULL , PARSEDATETIME('10:00:00', 'hh:mm:ss') , PARSEDATETIME('09-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('12-05-2018', 'dd-mm-yyyy'), 60000, 'NEW'), (104,2121458, 1, NULL , PARSEDATETIME('13:00:00', 'hh:mm:ss') , PARSEDATETIME('09-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('10-05-2018', 'dd-mm-yyyy'), 60000, 'NEW'), (105,2121458, 3, NULL , PARSEDATETIME('12:00:00', 'hh:mm:ss') , PARSEDATETIME('09-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('22-05-2018', 'dd-mm-yyyy'), 60000, 'NEW'), (106,5415458, 3, NULL , PARSEDATETIME('12:00:00', 'hh:mm:ss') , PARSEDATETIME('09-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('16-05-2018', 'dd-mm-yyyy'), 60000, 'NEW');

INSERT INTO Roomix.Person(PersonID, FirstName, LastName, IsVIP, Archive, Contact) VALUES (20,'Max', 'Mustermann', false , false , NULL ), (21,'Peter', 'Mayer', false , false , 4 ), (22,'Rolf', 'Rudolf', false , false , 3 ), (23,'Ashley', 'Fink', false , false , 2 ), (24,'Iva', 'Hase', false , false , NULL ), (25,'Eva', 'Mustermann', false , false , 1 );

INSERT INTO Roomix.PersonReservation (Reservation, Person) VALUES (9402204, 20), (9402204, 21), (6014321, 22), (6212124, 22), (2121458, 21), (5415458, 23), (5415458, 24), (5415458, 25);

INSERT INTO Roomix.RoomAssignment(ArrivalDate, DepartureDate, Room, ReservationUnit) VALUES (PARSEDATETIME('09-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('15-05-2018', 'dd-mm-yyyy'), 301, 100), (PARSEDATETIME('09-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('15-05-2018', 'dd-mm-yyyy'), 302, 101), (PARSEDATETIME('09-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('23-05-2018', 'dd-mm-yyyy'), 303, 102), (PARSEDATETIME('09-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('12-05-2018', 'dd-mm-yyyy'), 304, 103), (PARSEDATETIME('09-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('10-05-2018', 'dd-mm-yyyy'), 201, 104), (PARSEDATETIME('09-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('22-05-2018', 'dd-mm-yyyy'), 202, 105), (PARSEDATETIME('09-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('12-05-2018', 'dd-mm-yyyy'), 203, 106), (PARSEDATETIME('12-05-2018', 'dd-mm-yyyy'), PARSEDATETIME('16-05-2018', 'dd-mm-yyyy'), 101, 106);

INSERT INTO Roomix.InvoicePosition (Reservation, ReservationUnit, Invoice, RoomAssignment, Article, Comment, PaidFlag, Price, Count) VALUES (9402204, 100, null , null , 15, '', false , 40, 1), (6014321, 101, null , null , 15, '', false , 40, 1), (6212124, 103, null , null , 15, '', false , 40, 1), (5415458, 106, null , null , 15, '', false , 40, 1);
