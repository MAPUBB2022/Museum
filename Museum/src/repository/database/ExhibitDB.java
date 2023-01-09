package repository.database;
import classes.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExhibitDB {
    private static ExhibitDB single_instance = null;

    public static ExhibitDB getInstance() throws ClassNotFoundException {
        if (single_instance == null) {
            single_instance = new ExhibitDB();
        }
        return single_instance;
    }

    public Exhibit findById(String id) throws ClassNotFoundException{
        if(ArtifactDB.getInstance().findById(id) != null) {
            return ArtifactDB.getInstance().findById(id);
        }
        if(PaintingDB.getInstance().findById(id) != null) {
            return PaintingDB.getInstance().findById(id);
        }
        if(StatueDB.getInstance().findById(id) != null) {
            return StatueDB.getInstance().findById(id);
        }
        return null;
    }

    public boolean checkIfExists(String id) throws ClassNotFoundException {
        return this.findById(id) != null;
    }

    public void remove(String id) throws ClassNotFoundException {
        if(!ExhibitDB.getInstance().checkIfExists(id)) {
            System.out.println("Exhibit does not exist!");
            return;
        }
        Exhibit e = ExhibitDB.getInstance().findById(id);
        String blockId = this.findById(id).getLocation().getId();
        String exId = id;
        Block b = BlockDB.getInstance().findById(blockId);
//        Exhibit exToAdd = ExhibitDB.getInstance().findById(exId);
//        ExhibitDB.getInstance().changeLocation(blockId, exToAdd);

        boolean found = false;
        if(ArtifactDB.getInstance().findById(id) != null) {
            ArtifactDB.getInstance().remove(id);
            found = true;
        }
        if(PaintingDB.getInstance().findById(id) != null) {
            PaintingDB.getInstance().remove(id);
            found = true;

        }
        if(StatueDB.getInstance().findById(id) != null) {
            StatueDB.getInstance().remove(id);
            found = true;

        }
        List<Block> blocks = BlockDB.getInstance().getBlocks();
        for(Block b1:blocks) {
            b1.deleteExhibitNoSout(e);
        }
        List<Client> clients = ClientDB.getInstance().getClients();
        for(Client c:clients) {
            c.deleteExhibitNoSout(e);
        }
        if(!found) {
            System.out.println("The exhibit does not exist, please try again using an existing one!");
            return;
        }

        List<Artist> la1 = new ArrayList<>();
        for (int i = 0; i < b.getExhibits().size(); i++) {
            if (b.getExhibits().get(i) instanceof Painting) {
                if (!la1.contains(((Painting) b.getExhibits().get(i)).getPainter())) {
                    la1.add(((Painting) b.getExhibits().get(i)).getPainter());
                }
            } else if (b.getExhibits().get(i) instanceof Statue) {
                if (!la1.contains(((Statue) b.getExhibits().get(i)).getSculptor())) {
                    la1.add(((Statue) b.getExhibits().get(i)).getSculptor());
                }
            }
        }

        List<ArtMovement> lam1 = new ArrayList<>();
        for (int i = 0; i < b.getExhibits().size(); i++) {
            if (b.getExhibits().get(i) instanceof Painting) {
                if (!lam1.contains(((Painting) b.getExhibits().get(i)).getArtMovement())) {
                    lam1.add(((Painting) b.getExhibits().get(i)).getArtMovement());
                }
            } else if (b.getExhibits().get(i) instanceof Statue) {
                if (!lam1.contains(((Statue) b.getExhibits().get(i)).getArtMovement())) {
                    lam1.add(((Statue) b.getExhibits().get(i)).getArtMovement());
                }
            }
        }
        b.setArtists(la1);
        b.setMovements(lam1);

    }

    public void update(String id, Exhibit newEntity) throws ClassNotFoundException {
        if(ArtifactDB.getInstance().findById(id) != null) {
            ArtifactDB.getInstance().update(id, (Artifact) newEntity);
        }
        if(PaintingDB.getInstance().findById(id) != null) {
            PaintingDB.getInstance().update(id, (Painting) newEntity);
        }
        if(StatueDB.getInstance().findById(id) != null) {
            StatueDB.getInstance().update(id, (Statue) newEntity);
        }
        System.out.println("The exhibit you want to update does not exist!");
    }

    public void updateName(String id, String newName) throws ClassNotFoundException {
        boolean found = false;
        if(ArtifactDB.getInstance().findById(id) != null) {
            ArtifactDB.getInstance().updateName(id, newName);
            found = true;
        }
        if(PaintingDB.getInstance().findById(id) != null) {
            PaintingDB.getInstance().updateName(id, newName);
            found = true;
        }
        if(StatueDB.getInstance().findById(id) != null) {
            StatueDB.getInstance().updateName(id, newName);
            found = true;
        }
        if(!found) {
        System.out.println("The exhibit you want to update does not exist!");}
    }

    public void updateDateOfCreation(String id, Date newDateOfCreation) throws ClassNotFoundException {
        boolean found = false;
        if(ArtifactDB.getInstance().findById(id) != null) {
            ArtifactDB.getInstance().updateCreation(id, newDateOfCreation);
            found = true;
        }
        if(PaintingDB.getInstance().findById(id) != null) {
            PaintingDB.getInstance().updateCreation(id, newDateOfCreation);
            found = true;
        }
        if(StatueDB.getInstance().findById(id) != null) {
            StatueDB.getInstance().updateCreation(id, newDateOfCreation);
            found = true;
        }
        if(!found) {
            System.out.println("The exhibit you want to update does not exist!");}
    }

    public void updatePrice(String id, double newPrice) throws ClassNotFoundException  {
        boolean found = false;
        if(ArtifactDB.getInstance().findById(id) != null) {
            ArtifactDB.getInstance().updatePrice(id, newPrice);
            found = true;
        }
        if(PaintingDB.getInstance().findById(id) != null) {
            PaintingDB.getInstance().updatePrice(id, newPrice);
            found = true;
        }
        if(StatueDB.getInstance().findById(id) != null) {
            StatueDB.getInstance().updatePrice(id, newPrice);
            found = true;
        }
        if(!found) {
            System.out.println("The exhibit you want to update does not exist!");}    }

    public List<Exhibit> getAllExhibits() throws ClassNotFoundException  {
        List<Exhibit> allExhibits = new ArrayList<>();
        List<Artifact> allArtifacts = ArtifactDB.getInstance().getArtifacts();
        List<Painting> allPaintings= PaintingDB.getInstance().getPaintings();
        List<Statue> allStatues= StatueDB.getInstance().getStatues();
        allExhibits.addAll(allArtifacts);
        allExhibits.addAll(allPaintings);
        allExhibits.addAll(allStatues);
        return allExhibits;
    }

    public void deleteBlock(String exID) throws ClassNotFoundException {
        Exhibit exhibit = this.findById(exID);
        if (ArtifactDB.getInstance().findById(exhibit.getId()) != null) {
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("Update Artifact set Artifact.Location = null where Artifact.ID = ?");
                statement.setString(1, exhibit.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (StatueDB.getInstance().findById(exhibit.getId()) != null) {
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("Update Statue set Statue.Location = null where Statue.ID = ?");
                statement.setString(1, exhibit.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (PaintingDB.getInstance().findById(exhibit.getId()) != null) {
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("Update Painting set Painting.Location = null where Painting.ID = ?");
                statement.setString(1, exhibit.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void changeLocation(String blockID,Exhibit exhibit) throws ClassNotFoundException {
        if (ArtifactDB.getInstance().findById(exhibit.getId()) != null) {
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("Update Artifact set Artifact.Location = ? where Artifact.ID = ?");
                statement.setString(1, blockID);
                statement.setString(2, exhibit.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (StatueDB.getInstance().findById(exhibit.getId()) != null) {
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("Update Statue set Statue.Location = ? where Statue.ID = ?");
                statement.setString(1, blockID);
                statement.setString(2, exhibit.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (PaintingDB.getInstance().findById(exhibit.getId()) != null) {
            Connection connection = null;
            try {
                connection = OurConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement statement = connection.prepareStatement("Update Painting set Painting.Location = ? where Painting.ID = ?");
                statement.setString(1, blockID);
                statement.setString(2, exhibit.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    }
