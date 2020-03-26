
package billsplitter.dao;

import billsplitter.domain.Bill;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class FileBillDao {
    
        public void create(Bill bill) throws IOException {

            File templatePath = new File("./src/main/resources/html/template.html");
            String template = FileUtils.readFileToString(templatePath, "UTF-8");
            
            template = template.replace("$title", bill.getTitle());
            template = template.replace("$creator", bill.getCreator());
            template = template.replace("$description", bill.getDescription());
            template = template.replace("$payers", String.valueOf(bill.getPayers()));
            template = template.replace("$amount", String.valueOf(bill.getAmount()));
            template = template.replace("$result", String.valueOf(bill.getResult()));
            
            String filename = String.valueOf(bill.getUserId()) + String.valueOf(bill.getId());
            File newBill = new File("./src/data/bills/$filename.html");
            FileUtils.writeStringToFile(newBill, template, "UTF-8");
    }
    
}
