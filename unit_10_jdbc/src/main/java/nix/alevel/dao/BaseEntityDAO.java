package nix.alevel.dao;

import java.util.List;

public interface BaseEntityDAO <Entity> {
    List<Entity> read();
    void update(int id, Entity entity);

    /*
    ---For future develop---

    void create(Entity e);
    void delete(int id);
    Entity read(int id);
    */
}
