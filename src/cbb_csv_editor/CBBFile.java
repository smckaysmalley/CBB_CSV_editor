/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbb_csv_editor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author McKay
 */
public class CBBFile {
    
    private String fullFilepath;
    private String filename;
    private String filepath;
    

    public CBBFile(File file) {
        this.fullFilepath = file.getAbsolutePath();
        this.filename = file.getName();
        this.filepath = file.getParent();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public void cleanFile()
    {
        try {
            BufferedReader in = new BufferedReader(new FileReader(this.fullFilepath));
            BufferedWriter out = new BufferedWriter(new FileWriter(editedFilepath()));
            String line = in.readLine();
            String cleaned;
            line = in.readLine();
            cleaned = cleanLine(line);
            
            out.write(cleaned+",Won\n");
            line = in.readLine();
            
            while (line != null)
            {
                if (!line.startsWith(",,Overall,Overall,Overall,Overall,Overall,Overall,") &&
                    !line.startsWith("Rk,School,G,W,L,W-L%,"))
                {
                    cleaned = cleanLine(line);
                    out.write(cleaned+",0\n");
                }
                
                line = in.readLine();
            }
            
            out.close();
            in.close();
            
        } catch (IOException ex) {
            Logger.getLogger(CBBFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String cleanLine(String line)
    {
        return line.replace(",,", ",");
    }
    
    public String editedFilepath()
    {
        String[] tokens = this.filename.split("\\.(?=[^\\.]+$)");
        return this.filepath+"\\"+tokens[0]+"_edited.csv";
    }
}
