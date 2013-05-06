package br.com.lellis.pricecheck.tool;

/**
 * Created with IntelliJ IDEA.
 * User: Y3WG
 * Date: 06/05/13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
public class PersistTool  {

    private final String PATH_FILE = "priceList";

//    public List<Item> load(){
//        List<Item> itens = new ArrayList<Item>();
//
//        try {
//            FileInputStream fis = openFileInput(PATH_FILE);
//            ObjectInputStream objectInputStream = new ObjectInputStream(fis);
//            itens = (List<Item>) objectInputStream.readObject();
//            fis.close();
//            objectInputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (StreamCorruptedException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//        return itens;
//    }
//
//    public void save(List<Item> itens){
//        try {
//            FileOutputStream fos =  openFileOutput(PATH_FILE, Context.MODE_PRIVATE);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
//
//            objectOutputStream.writeObject(itens);
//            fos.close();
//            objectOutputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//    }
}
