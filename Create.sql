create table Artist (
	ID varchar(100) primary key not null,
	Name varchar(100),
	BirthDate date, 
	DeathDate date,
)

create table ArtMovement (
	ID varchar(100) primary key not null,
	Name varchar(100),
	StartDate date, 
	EndDate date,
)

create table ArtistMovements (
	ArtistID varchar(100) foreign key references Artist(ID) not null,
	ArtMovementID varchar(100) foreign key references ArtMovement(ID) not null,
	CONSTRAINT PK_ArtistAndArtMovement PRIMARY KEY (ArtistID,ArtMovementID)
)

create table Museum (
	Name varchar(100) primary key,
)

create table Block (
	Name varchar(100),
	Museum varchar(100) foreign key references Museum(Name),
	ID varchar(100) primary key not null,
)

create table Painting (
	PainterID varchar(100) foreign key references Artist(ID),
	ArtMovementID varchar(100) foreign key references ArtMovement(ID),
	Location varchar(100) foreign key references Block(ID),
	Price float,
	Creation date,
	Name varchar(100),
	ID varchar(100) primary key not null,
)

create table Statue (
	SculptorID varchar(100) foreign key references Artist(ID),
	ArtMovementID varchar(100) foreign key references ArtMovement(ID),
	Location varchar(100) foreign key references Block(ID),
	Price float,
	Creation date,
	Name varchar(100),
	ID varchar(100) primary key not null,
)

create table Artifact (
	Origin varchar(100),
	Location varchar(100) foreign key references Block(ID),
	Price float,
	Creation date,
	Name varchar(100),
	ID varchar(100) primary key not null,
)

create table Client (
	ID varchar(100) primary key not null,
	Name varchar(100),
)

create table Exhibit (
	ID varchar(100) primary key not null,
	Location varchar(100) foreign key references Block(ID),
)

create table ClientFavorites (
	ClientID varchar(100) foreign key references Client(ID) not null,
	ExhibitID varchar(100) foreign key references Exhibit(ID) not null,
	CONSTRAINT PK_ClientAndExhibit PRIMARY KEY (ClientID,ExhibitID)
)

create table Ticket (
	Price float,
	ID varchar(100) primary key,
	ClientID varchar(100) foreign key references Client(ID) not null,
)

create table Permits (
	TicketID varchar(100) foreign key references Ticket(ID) not null,
	BlockID varchar(100) foreign key references Block(ID) not null,
	CONSTRAINT PK_ClientAndBlock PRIMARY KEY (TicketID,BlockID)
)

create table Counters (
	CounterTicket bigint,
	CounterArtist bigint,
	CounterArtMovement bigint,
	CounterBlock bigint,
	CounterClient bigint,
	CounterExhibit bigint,
)

create table MuseumBlocks(
	BlockId varchar(100)  foreign key references Block(ID),
	MuseumId varchar(100) foreign key references Museum(Name),
	constraint BlockIdMuseumID PRIMARY KEY CLUSTERED (BlockID,MuseumId)
)

create table ClientMuseum (
	ClientID varchar(100)  foreign key references Client(ID),
	MuseumId varchar(100) foreign key references Museum(Name),
	constraint ClientIdMuseumID PRIMARY KEY CLUSTERED (ClientID,MuseumId)
)
