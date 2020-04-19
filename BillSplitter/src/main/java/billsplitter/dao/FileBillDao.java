
package billsplitter.dao;

import billsplitter.domain.Bill;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class FileBillDao implements BillDao {
    String billsFilePath;
    
    public FileBillDao() throws Exception {
        this.billsFilePath = "./src/main/resources/data/users.txt";
        
        InputStream inputStream = getClass().getResourceAsStream("/config.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String contents = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(contents);
        
            
//            Scanner reader = new Scanner(new File("./main/resources/config.txt"));
//            while (reader.hasNextLine()) {
//                String line = reader.nextLine();
//                String[] parts = line.split(";");
//                if (parts[0].equals("billsfilepath")) {
//                    this.billsFilePath = parts[1];
//                }
//        }
            
    }
    
    @Override
    public void create(Bill bill) throws Exception {
        
        try (FileWriter writer = new FileWriter(billsFilePath, true)) {
            writer.write(bill.getUsername() + ";" + bill.getTitle() + ";" + bill.getDescription() + ";" + bill.getPayers() + ";" + bill.getAmount() + ";" + bill.getResult());
            writer.write(System.lineSeparator());
        }
    }

    @Override
    public List<Bill> getAll() throws Exception {
        List<Bill> bills = new ArrayList<>();
        
        Scanner reader = new Scanner(new File(billsFilePath));
            
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] parts = line.split(";");
            bills.add(new Bill(parts[0], parts[1], parts[2], Integer.valueOf(parts[3]), Double.valueOf(parts[4]), Double.valueOf(parts[5])));
        }

        return bills;
    }

}
