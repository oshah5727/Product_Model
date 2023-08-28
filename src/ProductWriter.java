import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {
        ArrayList<String> productRecords = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        boolean done = false;

        String productRec = "";
        String ID = "";
        String Name = "";
        String Description = "";
        double Cost = 0;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]");
            Name = SafeInput.getNonZeroLenString(in, "Enter the product name");
            Description = SafeInput.getNonZeroLenString(in, "Enter the product description");
            Cost = SafeInput.getRangedDouble(in, "Enter the product cost", -1000.0,9999.0);

            productRec = ID + ", " + Name + ", " + Description + ", " + Cost;
            productRecords.add(productRec);

            done = SafeInput.getYNConfirm(in, "Are you done entering all the data elements for each product?");
        }

        while(!done);

        for(String p: productRecords)
            System.out.println(p);

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String rec: productRecords)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}