package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DBInit {

    public String readTeardown(){
        String sql = "";
        try {
            Path path = Paths.get("src/main/resources/teardown.sql");
            String filePath = String.valueOf(path);
            System.out.println(path.toAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while (true){
                String line = br.readLine();

                if(line == null) break;

                sql = sql + line;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sql;
    }
}
