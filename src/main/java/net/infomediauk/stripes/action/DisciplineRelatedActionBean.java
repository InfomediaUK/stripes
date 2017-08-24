package net.infomediauk.stripes.action;

import net.infomediauk.dao.impl.XmlDisciplineDao;
import net.infomediauk.dao.impl.XmlJobDao;
import net.infomediauk.model.Discipline;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;

public class DisciplineRelatedActionBean extends StaticPageActionBean 
{
  @DefaultHandler
  @Override
  public Resolution view() throws Exception
  {
    Resolution resolution = super.view();
    // Now HtmlPage is loaded, get its related Discipline if one exists...
    Integer disciplineId = getHtmlPage().getRelatedDisciplineId();
    if (disciplineId != null)
    {
      // Has related Discipline, now load any jobs for that Discipline.
      Discipline discipline = XmlDisciplineDao.getInstance().select(disciplineId);
      if (discipline != null)
      {
        setJobList(XmlJobDao.getInstance().selectForDiscipline(discipline.getId()));
      }
    }
    return resolution;
  }

}
