package ru.job4j.design.srp.reports;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import ru.job4j.design.srp.reports.model.Report;
import ru.job4j.design.srp.reports.textformatters.ToHTML;
import ru.job4j.design.srp.reports.textformatters.ToJSON;
import ru.job4j.design.srp.reports.textformatters.ToXML;
import ru.job4j.design.srp.reports.textgenerators.TextGeneratorBUH001;
import ru.job4j.design.srp.reports.textgenerators.TextGeneratorHR001;
import ru.job4j.design.srp.reports.textgenerators.TextGeneratorIT001;
import ru.job4j.design.srp.reports.textgenerators.TextGeneratorStandard;

import java.util.Calendar;

public class ReportEngineTest {

    private final Calendar calendarHired1 = Calendar.getInstance();
    private final Calendar calendarFired1 = Calendar.getInstance();
    private final Calendar calendarHired2 = Calendar.getInstance();
    private final Calendar calendarFired2 = Calendar.getInstance();
    private final Calendar calendarHired3 = Calendar.getInstance();
    private final Calendar calendarFired3 = Calendar.getInstance();

    @Before
    public void setUp() {
        calendarHired1.set(2009, Calendar.MARCH, 3, 0, 0, 0);
        calendarFired1.set(2010, Calendar.DECEMBER, 10, 0, 0, 0);
        calendarHired2.set(2019, Calendar.FEBRUARY, 14, 0, 0, 0);
        calendarFired2.clear();
        calendarHired3.set(2004, Calendar.JANUARY, 23, 0, 0, 0);
        calendarFired3.clear();
    }

    @Test
    public void whenStandard() {
        MemStore store = new MemStore();
        store.add(new Employee("1. Ivan", calendarHired1, calendarFired1, 1045));
        store.add(new Employee("2. Petr", calendarHired2, calendarFired2, 5498));
        Report engine = new ReportEngine(store);
        String expect = "Name; Hired; Fired; Salary;" + System.lineSeparator() +
                "1. Ivan;03/03/2009;10/12/2010;1045.0;" + System.lineSeparator() +
                "2. Petr;14/02/2019;<>;5498.0;" + System.lineSeparator();
        assertThat(engine.generate(em -> true, new TextGeneratorStandard()), is(expect));
    }

    @Test
    public void whenHR001() {
        MemStore store = new MemStore();
        store.add(new Employee("1. Ivan", calendarHired1, calendarFired1, 1045));
        store.add(new Employee("2. Petr", calendarHired2, calendarFired2, 5498));
        store.add(new Employee("3. Alf", calendarHired3, calendarFired3, 4855));
        Report engine = new ReportEngine(store);
        String expect = "Name; Salary;" + System.lineSeparator() +
                "2. Petr;5498.0;" + System.lineSeparator() +
                "3. Alf;4855.0;" + System.lineSeparator() +
                "1. Ivan;1045.0;" + System.lineSeparator();
        assertThat(engine.generate(em -> true, new TextGeneratorHR001()), is(expect));
    }

    @Test
    public void whenIT001() {
        MemStore store = new MemStore();
        store.add(new Employee("1. Ivan", calendarHired1, calendarFired1, 1045));
        store.add(new Employee("2. Petr", calendarHired2, calendarFired2, 5498));
        store.add(new Employee("3. Alf", calendarHired3, calendarFired3, 4855));
        Report engine = new ReportEngine(store);
        String expect = "<!DOCTYPE html>" + System.lineSeparator() +
                "<html lang=\"ru\">" + System.lineSeparator() +
                "<head>Name; Hired; Fired; Salary;</head>" + System.lineSeparator() +
                "<body>" + System.lineSeparator() +
                "<P>1. Ivan;03/03/2009;10/12/2010;1045.0;</P>" + System.lineSeparator() +
                "<P>2. Petr;14/02/2019;<>;5498.0;</P>" + System.lineSeparator() +
                "<P>3. Alf;23/01/2004;<>;4855.0;</P>" + System.lineSeparator() +
                "</body>" + System.lineSeparator() +
                "</html>" + System.lineSeparator();
        assertThat(engine.generate(em -> true, new TextGeneratorIT001()), is(expect));
    }

    @Test
    public void whenBUH001() {
        MemStore store = new MemStore();
        store.add(new Employee("1. Ivan", calendarHired1, calendarFired1, 10045));
        store.add(new Employee("2. Petr", calendarHired2, calendarFired2, 54498));
        store.add(new Employee("3. Alf", calendarHired3, calendarFired3, 48555));
        Report engine = new ReportEngine(store);
        String expect = "Name; Salary USD;" + System.lineSeparator() +
                "1. Ivan;134,74;" + System.lineSeparator() +
                "2. Petr;731,04;" + System.lineSeparator() +
                "3. Alf;651,32;" + System.lineSeparator();
        assertThat(engine.generate(em -> true, new TextGeneratorBUH001()), is(expect));
    }

    @Test
    public void whenBUH001andJSON() {
        MemStore store = new MemStore();
        store.add(new Employee("1. Ivan", calendarHired1, calendarFired1, 10045));
        store.add(new Employee("2. Petr", calendarHired2, calendarFired2, 54498));
        store.add(new Employee("3. Alf", calendarHired3, calendarFired3, 48555));
        Report engine = new ReportEngine(store);
        String expected = "{\"Employee 3\":{\"Salary USD\":\"651,32\",\"Name\":\"" +
                "3. Alf\"},\"Employee 2\":" +
                "{\"Salary USD\":\"731,04\",\"Name\":\"2. Petr\"},\"Report type\":" +
                "\"Employee report\",\"Employee 1\":" +
                "{\"Salary USD\":\"134,74\",\"Name\":\"1. Ivan\"}}";
        String result = engine.generate(em -> true, new TextGeneratorBUH001());
        result = engine.format(result, new ToJSON());
        assertThat(result, is(expected));
    }

    @Test
    public void whenStandardAndXML() {
        MemStore store = new MemStore();
        store.add(new Employee("1. Ivan", calendarHired1, calendarFired1, 10045));
        store.add(new Employee("2. Petr", calendarHired2, calendarFired2, 54498));
        store.add(new Employee("3. Alf", calendarHired3, calendarFired3, 48555));
        Report engine = new ReportEngine(store);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + System.lineSeparator() +
                "<report>" + System.lineSeparator() +
                "    <report_type>Employee report</report_type>" + System.lineSeparator() +
                "    <report_body>" + System.lineSeparator() +
                "        <employee_0>" + System.lineSeparator() +
                "            <Name>1. Ivan</Name>" + System.lineSeparator() +
                "            <Hired>03/03/2009</Hired>" + System.lineSeparator() +
                "            <Fired>10/12/2010</Fired>" + System.lineSeparator() +
                "            <Salary>10045.0</Salary>" + System.lineSeparator() +
                "        </employee_0>" + System.lineSeparator() +
                "        <employee_1>" + System.lineSeparator() +
                "            <Name>3. Alf</Name>" + System.lineSeparator() +
                "            <Hired>23/01/2004</Hired>" + System.lineSeparator() +
                "            <Fired></Fired>" + System.lineSeparator() +
                "            <Salary>48555.0</Salary>" + System.lineSeparator() +
                "        </employee_1>" + System.lineSeparator() +
                "        <employee_2>" + System.lineSeparator() +
                "            <Name>2. Petr</Name>" + System.lineSeparator() +
                "            <Hired>14/02/2019</Hired>" + System.lineSeparator() +
                "            <Fired></Fired>" + System.lineSeparator() +
                "            <Salary>54498.0</Salary>" + System.lineSeparator() +
                "        </employee_2>" + System.lineSeparator() +
                "    </report_body>" + System.lineSeparator() +
                "</report>";
        String result = engine.generate(em -> true, new TextGeneratorStandard());
        result = engine.format(result, new ToXML());
        assertEquals(result, expected);
    }

    @Test
    public void whenStandardAndHTML() {
        MemStore store = new MemStore();
        store.add(new Employee("1. Ivan", calendarHired1, calendarFired1, 10045));
        store.add(new Employee("2. Petr", calendarHired2, calendarFired2, 54498));
        store.add(new Employee("3. Alf", calendarHired3, calendarFired3, 48555));
        Report engine = new ReportEngine(store);
        String expected = "<!DOCTYPE html>" + System.lineSeparator() +
                "<html lang=\"ru\">" + System.lineSeparator() +
                "<head>" + System.lineSeparator() +
                "Name; Hired; Fired; Salary;" + System.lineSeparator() +
                "</head>" + System.lineSeparator() +
                "<body>" + System.lineSeparator() +
                "<P>1. Ivan;03/03/2009;10/12/2010;10045.0;</P>" + System.lineSeparator() +
                "<P>3. Alf;23/01/2004;<>;48555.0;</P>" + System.lineSeparator() +
                "<P>2. Petr;14/02/2019;<>;54498.0;</P>" + System.lineSeparator() +
                "</body>" + System.lineSeparator() +
                "</html>";
        String result = engine.generate(em -> true, new TextGeneratorStandard());
        result = engine.format(result, new ToHTML());
        assertEquals(result, expected);
    }
}