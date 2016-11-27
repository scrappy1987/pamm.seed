package domain.services.project.businessobjects;

import domain.infrastructure.businessobjects.BusinessObject;

public class Project extends BusinessObject
{
    private String title;

    private String summary;

    private String info;

    private String status;

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }
}
