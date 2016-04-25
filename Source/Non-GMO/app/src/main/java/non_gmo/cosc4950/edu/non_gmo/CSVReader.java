package non_gmo.cosc4950.edu.non_gmo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

*
 * Created by mike on 4/24/16.

public class CSVReader {

    InputStream inputStream;

    public CSVReader(InputStream is) {
        this.inputStream = is;
    }

    public List<String[]> read() {
        List<String[]> catList = new ArrayList<String[]>();
        BufferedReader bufferedReader = new BufferedReader(new InputStream(inputStream));

        try {
            String csvLine;
            while((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                catList.add(row);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file:" + ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }

        return catList;
    }
}
