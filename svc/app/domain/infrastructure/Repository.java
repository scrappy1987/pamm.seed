package domain.infrastructure;

import domain.infrastructure.businessobjects.BusinessObject;

import java.util.List;

public interface Repository<T extends BusinessObject>
{
    public  T set(T businessObject);

    public  T get(Long businessObjectIdentifier);

    public List<T> getAll();

    public void remove(T businessObject);
}
