insert into Artist (ID, Name, BirthDate, DeathDate) values ('A1000','Leonardo Da Vinci', '1452-04-15', '1519-06-2')
insert into Artist (ID, Name, BirthDate, DeathDate) values ('A1001','Michelangelo Buonarroti', '1475-03-06', '1563-02-18')

insert into ArtMovement (ID, Name, StartDate, EndDate) values ('V1000','Renaissance','1300-01-01', '1699-12-31')

insert into Client (ID, Name) values ('C1000', 'Oprisor Raul')
insert into Client (ID, Name) values ('C1001', 'Joita Razvan')
insert into Client (ID, Name) values ('C1002', 'Moldovan Andrei')

insert into Museum(Name) values ('Antipa')

insert into Block (ID, Name) values ('B1000', 'Art Gallery1' )
insert into MuseumBlocks (BlockId,MuseumId) values ('B1000','Antipa')
insert into Block (ID, Name) values ('B1001', 'Art Gallery2' )
insert into MuseumBlocks (BlockId,MuseumId) values ('B1001','Antipa')
insert into Block (ID, Name) values ('B1002', 'Art Gallery3' )
insert into MuseumBlocks (BlockId,MuseumId) values ('B1002','Antipa')

insert into Ticket(Price, ID, ClientID) values (2.5, 'T1000','C1000')
insert into Permits(TicketID,BlockID) values ('T1000','B1000')
insert into Ticket(Price, ID, ClientID) values (5, 'T1001','C1000')
insert into Permits(TicketID,BlockID) values ('T1001','B1000')
insert into Permits(TicketID,BlockID) values ('T1001','B1002')
insert into Ticket(Price, ID, ClientID) values (5, 'T1002','C1001')
insert into Permits(TicketID,BlockID) values ('T1002','B1001')
insert into Permits(TicketID,BlockID) values ('T1002','B1002')

insert into Ticket(Price, ID, ClientID) values (0, 'T1000','C1000')
insert into Ticket(Price, ID, ClientID) values (0, 'T1000','C1000')

insert into Painting (ID, PainterID, ArtMovementID, Location, Price, Creation, Name) values ('E1000', 'A1000', 'V1000', 'B1000', 10, '1503-11-24', 'Mona Lisa')
insert into Painting (ID, PainterID, ArtMovementID, Location, Price, Creation, Name) values ('E1001', 'A1001', 'V1000', 'B1000', 0.5, '1510-03-28', 'Creation of Adam')
insert into Statue (ID, SculptorID, ArtMovementID, Location, Price, Creation, Name) values ('E1002', 'A1000', 'V1000', 'B1001', 19, '1510-02-23', 'Equestrian')
insert into Artifact (ID, Location, Price, Creation, Name, Origin) values ('E1003', 'B1001', 14, '12-06-12', 'Rod Of Ascellus', 'Egypt')


