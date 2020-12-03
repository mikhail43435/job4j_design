package ru.job4j.design.io.exam;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FindFileArgTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenEmpty() {
        String[] args = "".split(" ");
        FindFileArg fineFile = new FindFileArg(args);
        assertThat(fineFile.isValid(), is(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyIsMissingD() {
        String[] args = "-n=*.zip -f -o=c:\\gresults.txt".split(" ");
        FindFileArg fineFile = new FindFileArg(args);
        assertThat(fineFile.isValid(), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyIsMissingN() {
        String[] args = "-d=c:\\projects -f -o=c:\\gresults.txt".split(" ");
        FindFileArg fineFile = new FindFileArg(args);
        assertThat(fineFile.isValid(), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyIsMissingO() {
        String[] args = "-d=c:\\projects -n=*.zip -f ".split(" ");
        FindFileArg fineFile = new FindFileArg(args);
        assertThat(fineFile.isValid(), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTooMuchSecondKeysMF() {
        String[] args = "-d=c:\\projects -n=*.zip -m -f -o=c:\\gresults.txt".split(" ");
        FindFileArg fineFile = new FindFileArg(args);
        assertThat(fineFile.isValid(), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTooMuchSecondKeysMR() {
        String[] args = "-d=c:\\projects -n=*.zip -m -r -o=c:\\gresults.txt".split(" ");
        FindFileArg fineFile = new FindFileArg(args);
        assertThat(fineFile.isValid(), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTooMuchSecondKeysFR() {
        String[] args = "-d=c:\\projects -n=*.zip -f -r -o=c:\\gresults.txt".split(" ");
        FindFileArg fineFile = new FindFileArg(args);
        assertThat(fineFile.isValid(), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTooMuchSecondKeysMFR() {
        String[] args = "-d=c:\\projects -n=*.zip -f -r -m -o=c:\\gresults.txt".split(" ");
        FindFileArg fineFile = new FindFileArg(args);
        assertThat(fineFile.isValid(), is(true));
    }

/*  // не работает
    @Test(expected = FileNotFoundException.class)
    public void whenWrongOutputPath() throws IOException {
        String[] args = "-d=c:\\projects -n=*.zip -m -o=qd:\\gresults.txt".split(" ");
        FindFile fineFile = new FindFile(args);
       //assertThat(fineFile(args), is(true));
    }*/
}