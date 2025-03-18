package com.dipfile.spreadsheet.uility;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class DeleteFile {
    private DeleteFile(){}
    public static void tempFiles(){
        try {
            if(!FilePath.tempDotSummaryName.isEmpty()){
                System.out.println("FilePath.tempDotSummaryName = "+FilePath.tempDotSummaryName);
                deletePath(FilePath.tempsqlFileName);
                FilePath.tempsqlFileName = "";

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deletePath(String fileName) throws IOException{
        boolean isIt =
                Files.deleteIfExists(Paths.get((FilePath.HOME + FilePath.UPLOAD_DIR + fileName).trim()));
    }
}
