package models.data.repository;

import play.Logger;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class GenericReadOnlyDao<T>
{
    private final Logger.ALogger logger = Logger.of(this.getClass());

    private EntityManagerProvider emp;

    protected Class<T> type;

    public GenericReadOnlyDao(EntityManagerProvider emp)
    {
        this.emp = emp;

        Type t = getClass().getGenericSuperclass();

        ParameterizedType pt = (ParameterizedType) t;

        type = (Class) pt.getActualTypeArguments()[0];
    }

    public final T find(Object id)
    {
        return (T) getEntityManager().find(type, id);
    }

    public List<T> list()
    {
        logger.info("Getting List of " + type.getSimpleName());

        String query = "Select e from " + type.getSimpleName() + " e";

        logger.info("List query " + query);

        return getEntityManager().createQuery(query).getResultList();
    }

    protected EntityManager getEntityManager()
    {
        return emp.getEntityManager();
    }
}
