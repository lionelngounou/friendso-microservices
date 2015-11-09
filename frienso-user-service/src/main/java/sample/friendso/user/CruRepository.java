package sample.friendso.user;

import java.io.Serializable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * @author Lionel Stephane
 */
@NoRepositoryBean
public interface CruRepository<T extends Object, ID extends Serializable> extends Repository<T, ID> {

    public <S extends T> S save(S s);

    public <S extends T> Iterable<S> save(Iterable<S> itrbl);

    public T findOne(ID id);

    public boolean exists(ID id);

    public Iterable<T> findAll();

    public Iterable<T> findAll(Iterable<ID> itrbl);

    public long count();
}
