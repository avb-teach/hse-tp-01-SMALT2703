import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String input_directory = args[0];
        String output_directory = args[1];
        int max_depth = 1;

        System.out.println(args.length);

        if (args.length == 3){
            max_depth = Integer.parseInt(args[2]);
        }

        final int mx = max_depth;

        try {
            Files.walk(Paths.get(input_directory)).forEach(path -> {

                File file = path.toFile();
                String pwd = path.toString().replace(input_directory, "");
                String[] pwdParts = pwd.split("/");

                if (!file.isFile()){
                    String dirPath = output_directory + "/" + String.join("/", java.util.Arrays.copyOfRange(pwdParts, Math.max(0, pwdParts.length - mx + 1), pwdParts.length));
                    new File(dirPath).mkdirs();
                }

                if (file.isFile()) {
                    try {
                        String destPath = output_directory + "/" + String.join("/", java.util.Arrays.copyOfRange(pwdParts, Math.max(0, pwdParts.length - mx), pwdParts.length - 1));
                        Files.copy(path, Paths.get(destPath, file.getName()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}