package repository;

public interface ICrudRepository<ID, E> {
    void add(E entity) throws ClassNotFoundException;

    void remove(ID id) throws ClassNotFoundException;

    void update(ID id, E newEntity) throws ClassNotFoundException;

    void updateName(ID id, ID newId);

    E findById(ID id);

    boolean checkIfExists(ID id); // Using an unique identifier (mostly Id)
}
