package data;
import java.io.*;

//Works with User data

public class Code {
    File f = new File("saves");

    //Check if Directory exists. If not, create it. (used before createAccount)

    public void verify() {

        if (!(f.exists() && f.isDirectory())) {
            File theDir = new File("saves");
            theDir.mkdirs();
        }
    }


    //Create new .bin file and add Object inside.

    public void createAccount(String name) {
        User newV = new User();
        newV.name = name;
        newV.progress = 0;
        String filename = "saves/"+name+".bin";
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
            os.writeObject(newV);
            os.close();
        } catch (IOException e) {
        }
    }

    //1. Find all files in directory 'saves'
    //2. Open every file in directory 'saves', get Object (newV), read newV.name
    //3. If newV.name = String name (name user wants to give to new account) flag = false else flag stays true.

    public boolean singularity(String name) {
        boolean flag = true;
        File[] listOfFiles = f.listFiles();
        ObjectInputStream as;
        User check;
        for (File a : listOfFiles) {
            if (a.isFile()) {
                try {
                    as = new ObjectInputStream(new FileInputStream("saves/"+a.getName()));
                    check = (User) as.readObject();
                    if (check.name.equals(name)) flag = false;
                    as.close();
                } catch (IOException | ClassNotFoundException e) { }

            }
        }
        return flag;
    }

}
