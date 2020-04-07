
package billsplitter.dao;

import billsplitter.domain.Bill;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class FileBillDao implements BillDao {
    String billsFilePath;
    
    public FileBillDao() throws Exception {
        this.billsFilePath = "./src/main/resources/data/users.txt";
        
        try (Scanner reader = new Scanner(new File("config.txt"))) {
            
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] parts = line.split(";");
            if (parts[0].equals("billsfilepath")) this.billsFilePath = parts[1];
        }
        
        } catch (Exception e) {
            System.out.println("FileUserDao.config()");
            System.out.println("Virhe: " + e.getMessage());
        }
    }
    
    
    @Override
    public void create(Bill bill) throws Exception {
        try {
            try (FileWriter writer = new FileWriter(billsFilePath, true)) {
                writer.write(bill.getUsername() + ";" + bill.getTitle() + ";" + bill.getDescription() + ";" + bill.getPayers() + ";" + bill.getAmount() + ";" + bill.getResult());
                writer.write(System.lineSeparator());
                writer.close();
            }
        } catch (IOException ex) {
            System.out.println("FileUserDao.create(" + bill + ")");
            System.out.println("Virhe: " + ex.getMessage());
        }
    }

    @Override
    public List<Bill> getAll() throws Exception {
        List<Bill> bills = new ArrayList<>();
        
        try (Scanner reader = new Scanner(new File(billsFilePath))) {
            
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(";");
                bills.add(new Bill(parts[0], parts[1], parts[2], Integer.valueOf(parts[3]), Double.valueOf(parts[4]), Double.valueOf(parts[5])));
            }
        } catch (Exception e) {
            System.out.println("FileUserDao.getAll()");
            System.out.println("Virhe: " + e.getMessage());
        }
        return bills;
    }

}
