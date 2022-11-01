# classes.Museum
Group: 722
Oprisor Raul-Alexandru & Moldovan Andrei

## Let us learn about all the classes:

### classes.Person
= a person who creates paintings or statues as a profession or hobby.
Special Methods: 
*	presentSelf (A short description about themselves)
*	isFamous (Displays if a person is famous or not)

### classes.Client
= the client is the boss.
Every fine client has a list of favorites in terms of exhibits.
What date do we collect about the client? Name, ID, Number of Visits, Favorites
Special Methods: 
*	steal (A low chance to successfully steal an artifact and a high chance to get busted by police) Is it worth it?
*   presentSelf (A short description about themselves)
*	isFamous (Displays if a person is famous or not)

### classes.Artist
= a person who creates paintings or statues as a profession or hobby.
An artist can have 1 or many exhibits (Paintings/Statues). 
He can also take part in none,one or a lot of art movements. Art is art!
Every artist has: Name, ID, Art Movements he took part in, Exhibits he created, Birth Date, Death Date
Special Methods:
*	presentSelf (A short description about themselves)
*	isFamous (Displays if a person is famous or not)

### classes.ArtMovement
= tendency or style in art with a specific common philosophy or goal, followed by a group of artists during a specific period of time.
Many artists can take part in an art Movement.
Every art movement has: Name, ID, Artists that took part in the creation , Start Date, End Date
Special Methods:
*	getRandomArtist

### classes.Block
= the part of the building where something can be found.
In a certain block you can find many nice things. So we need to know what exhibits are displayed. Who the artists of the beautiful exhibits are. And also the art movements special to this block!
Every block has: Name, ID, Artists, Exhibits, Movements

### classes.Museum
= the class to rule them all
Attributes: name, blocks(know all about the exhibits, clients
Special Methods:
*	getAllExhibits (returns a list with all exhibits from all the blocks in a museum) 

### classes.Exhibit
= publicly display in an art gallery or museum as a painting, statue and artifact in a certain block location.
Every exhibit is special. So we need to know its Name, ID, location classes.Block in the museum and the date of creation
Special Methods: 
*	getInformation (returns a String with all the information about an exhibit)


### classes.Painting
= Every cool painting has a well known artist.
What extra do we need? Painter and Art Movement
### classes.Statue 
= Every crisp statue has a well known artist.
What extra do we need?
Sculptor and Art Movement
### classes.Artifact 
= are old and dusty. No one knows who is their creator. 
What extra do we need? Its origin!

### Special Methods using the interface:
* getTotalVisits of a museum
* Display a random Artist from an art movement

![Diagram](https://github.com/MAPUBB2022/Museum/blob/main/Museum/Diagram.png)


