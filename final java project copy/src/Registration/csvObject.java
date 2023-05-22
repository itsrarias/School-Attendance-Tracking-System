package Registration;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// polymorphism
class csvObject {
        public void DoInsertion(String[] data ) {
            String filePath = "src/com/proj/teacher_list.csv";
            System.out.println("inserting this data " + data);
            File file = new File(filePath);
            try {
                // create FileWriter object with file as parameter
                FileWriter outputfile = new FileWriter(file, true);

                // create CSVWriter object filewriter object as parameter

                CSVWriter writer = new CSVWriter(outputfile);


                // add data to csv
//                String[] data1 = { Student_name_field, Student_class_field};
                writer.writeNext(data);

                // closing writer connection
                writer.close();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // returning csvObject for teacher_list.csv file
        }
}
class studentList extends csvObject {
    @Override
    public void DoInsertion(String[] data ) {
        String filePath = "src/com/proj/student_list.csv";
        System.out.println("inserting this data " + data);
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file, true);

            // create CSVWriter object filewriter object as parameter

            CSVWriter writer = new CSVWriter(outputfile);


            // add data to csv
//                String[] data1 = { Student_name_field, Student_class_field};
            writer.writeNext(data);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // returning csvObject for teacher_list.csv file
    }
}