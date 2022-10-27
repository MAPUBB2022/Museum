package repository;

public interface ICrudRepository<ID, E> {
    void add(E entity);

    void remove(ID id);

    void update(ID id, E newEntity);

    void updateName(ID id, ID newId);

    E findById(ID id);

    boolean checkIfExists(ID id); // Using an unique identifier (mostly Id)
}
