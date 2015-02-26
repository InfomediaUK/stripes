package net.infomediauk.xml.jaxb.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "title", "metaDescription", "metaKeywords", "body" })
public class HtmlPage
{
  private String title;
  private String metaDescription;
  private String metaKeywords;
  private String body;
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

  public String toString()
  {
    StringBuilder output = new StringBuilder();
    output.append("title=[");
    output.append(title);
    output.append("]\n");
    output.append("metaDescription=[");
    output.append(metaDescription);
    output.append("]\n");
    output.append("metaKeywords=[");
    output.append(metaKeywords);
    output.append("]\n");
    output.append("body=[");
    output.append(body);
    output.append("]\n");
    return output.toString();
  }
  
}
