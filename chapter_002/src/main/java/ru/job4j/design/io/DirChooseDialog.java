package ru.job4j.design.io;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;

public class DirChooseDialog {

    public static File chooseDir() {
        File selectedFolder = null;
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File(".")); //
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedFolder = fc.getSelectedFile();
        }
        return selectedFolder;
    }
}
