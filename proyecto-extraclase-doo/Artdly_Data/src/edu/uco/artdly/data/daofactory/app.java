package edu.uco.artdly.data.daofactory;

public class app {
    public static void main(String[] args) {
        PostgreSQLDAOFactory pd = new PostgreSQLDAOFactory();
        pd.openConnection();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        pd.closeConection();
        System.out.println("Done");
    }
}
