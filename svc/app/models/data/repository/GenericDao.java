package models.data.repository;

import play.Logger;

public abstract class GenericDao<T> extends GenericReadOnlyDao<T>
{
    private final Logger.ALogger logger = Logger.of(this.getClass());

    public GenericDao(EntityManagerProvider emp)
    {
        super(emp);
    }

    public final T create(final T t)
    {
        try
        {
            logger.info("About to persist the entity " + t);

            getEntityManager().persist(t);
        }
        catch (Throwable e)
        {
            logger.info("Exception creating entity " + e.getMessage(), e);

            throw new RuntimeException(e);
        }

        return t;
    }

    public final void delete(final Object id)
    {
        getEntityManager().remove(getEntityManager().getReference(type, id));
    }

    public final T update(final T t)
    {
        T mergedEntity = null;

        try
        {
            mergedEntity = getEntityManager().merge(t);
        }
        catch (Throwable e)
        {
            throw new RuntimeException(e);
        }
        return mergedEntity;
    }
}
