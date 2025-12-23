import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CalcLogs {

    Path path;

    CalcLogs(String fpath) {
        this.path = Paths.get(fpath);
    }

    public void write(String entry) throws IOException {
        try (BufferedWriter wr = Files
                .newBufferedWriter(
                    this.path,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
                )
        )
        {
           wr.write(entry);
           wr.newLine();
        }
    }

    public void read(String mode) throws IOException {
        try (BufferedReader rd = Files
                .newBufferedReader(
                    this.path,
                    StandardCharsets.UTF_8
                )
        )
        {
            if (mode.equals("all")) { rd.readAllLines(); }
            else if (mode.equals("line")) { rd.readLine(); }
            else {
                throw new IllegalArgumentException("choose between: 'all' and 'line'");
            }
        }
    }

    public void wipe() throws IOException {
        try (BufferedWriter wr = Files
                .newBufferedWriter(
                        this.path,
                        StandardCharsets.UTF_8
                )
        )
        {
            wr.write("");
        }
    }
}