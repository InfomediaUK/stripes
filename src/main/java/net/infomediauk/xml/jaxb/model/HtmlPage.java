package net.infomediauk.xml.jaxb.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "title", "metaDescription", "metaKeywords", "body", "relatedDisciplineId" })
public class HtmlPage
{
  private String title;
  private String metaDescription;
  private String metaKeywords;
  private String body;
  private Integer relatedDisciplineId;
  /**
   * @return the title
   */
  public String getTitle()
  {
    return title;
  }
  /**
   * @param title the title to set
   */
  @XmlElement
  public void setTitle(String title)
  {
    this.title = title;
  }
  /**
   * @return the metaDescription
   */
  public String getMetaDescription()
  {
    return metaDescription;
  }
  /**
   * @param metaDescription the metaDescription to set
   */
  @XmlElement
  public void setMetaDescription(String metaDescription)
  {
    this.metaDescription = metaDescription;
  }
  /**
   * @return the metaKeywords
   */
  public String getMetaKeywords()
  {
    return metaKeywords;
  }
  /**
   * @param metaKeywords the metaKeywords to set
   */
  @XmlElement
  public void setMetaKeywords(String metaKeywords)
  {
    this.metaKeywords = metaKeywords;
  }
  /**
   * @return the body
   */
  public String getBody()
  {
    return body;
  }
  /**
   * @param body the body to set
   */
  public void setBody(String body)
  {
    this.body = body;
  }

  public Integer getRelatedDisciplineId()
  {
    return relatedDisciplineId;
  }
  
  public void setRelatedDisciplineId(Integer relatedDisciplineId)
  {
    this.relatedDisciplineId = relatedDisciplineId;
  }
  @Override
  public String toString()
  {
    return "HtmlPage [title=" + title + ", metaDescription=" + metaDescription + ", metaKeywords=" + metaKeywords + ", body=" + body + ", relatedDisciplineId=" + relatedDisciplineId + "]";
  }
  
}
