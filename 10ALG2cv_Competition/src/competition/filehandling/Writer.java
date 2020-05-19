/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competition.filehandling;

import competition.app.Runner;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Václav Kurel
 */
public abstract class Writer {

    public abstract void saveResults(String resultFilePath, List<Runner> runners) throws IOException;
}
