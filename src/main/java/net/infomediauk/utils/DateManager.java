package net.infomediauk.utils;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DateManager
{
  private String dayTitle;
  private String monthTitle;
  private String yearTitle;
  private List<DayNumber> dayNumberList;
  private List<Month> monthList;
  private List<Year> yearList;
  private Map<Integer, Month> monthMap = new HashMap<Integer, Month>();
  private Map<Integer, Year> yearMap = new HashMap<Integer, Year>();

  
  public DateManager()
  {
    this(Locale.ENGLISH, "--", "---------------", "----");
  }

  public DateManager(Locale locale)
  {
    this(locale, "--", "---------------", "----");
  }

  public DateManager(Locale locale, String dayTitle, String monthTitle, String yearTitle)
  {
    if (locale == null) 
    {
      locale = Locale.ENGLISH;
    }
    this.dayTitle = dayTitle;
    this.monthTitle = monthTitle;
    this.yearTitle = yearTitle;
    buildDayNUmberList();
    buildMonthList(locale);
    buildYearList();
  }

  public List<DayNumber> getDayNumberList()
  {
    return dayNumberList;
  }

  public void setDayNumberList(List<DayNumber> dayNumberList)
  {
    this.dayNumberList = dayNumberList;
  }

  public List<Month> getMonthList()
  {
    return monthList;
  }

  public void setMonthList(List<Month> monthList)
  {
    this.monthList = monthList;
  }

  public List<Year> getYearList()
  {
    return yearList;
  }

  public void setYearList(List<Year> yearList)
  {
    this.yearList = yearList;
  }

  public String getMonthName(Integer mm)
  {
    Month month = monthMap.get(mm);
    return month.getName();
  }
  
  public String getYearName(Integer yy)
  {
    Year year = yearMap.get(yy);
    return year.getName();
  }
  
  private void buildDayNUmberList()
  {
    dayNumberList = new ArrayList<DayNumber>(32);
    dayNumberList.add(new DayNumber(0, dayTitle));
    for (int i = 1; i < 31; i++)
    {
      dayNumberList.add(new DayNumber(i, new Integer(i).toString()));
    }
  }
  
  private void buildMonthList(Locale locale)
  {
    DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(Locale.ENGLISH);
    DateFormatSymbols localizedFormatSymbols = new DateFormatSymbols(locale);
    String[] monthNames = dateFormatSymbols.getMonths();
    String[] localizedMonthNames = localizedFormatSymbols.getMonths();
    monthList = new ArrayList<Month>(13);
    monthList.add(new Month(0, monthTitle, monthTitle));
    for (int i = 0; i < 12; i++) 
    {
      Month month = new Month(i + 1, monthNames[i], localizedMonthNames[i]);
      monthList.add(month);
      monthMap.put(month.getId(), month);
    }
  }

  private void buildYearList()
  {
    yearList = new ArrayList<Year>(5);
    yearList.add(new Year(0, yearTitle));
    Calendar calendar = Calendar.getInstance();
    int yyyy = 0;
    for (int i = 1; i < 5; i++)
    {
      yyyy = calendar.get(Calendar.YEAR);
      Year year = new Year(Integer.valueOf(i), String.valueOf(yyyy));
      yearList.add(year);
      yearMap.put(year.getId(), year);
      calendar.add(Calendar.YEAR, 1);
    }
  }
  
  
}
