package dbclasses;

/**
 * Created by David on 8/28/2018.
 */

public final class Database {
    public static final String NAME = "students";
    public static final int VERSION =1;

    //Student Table
    public static final class Student{
        public static final String NAME = "student"; //Table Name

        //Following are column names
        public static final String SID = "sid";
        public static final String SURNAME = "surname";
        public static final String OTHERNAME = "othername";
        public static final String SEX = "sex";

        //Create Query For this Table
        public static final String CREATE_QUERY =
                "CREATE TABLE IF NOT EXISTS " +
            Student.NAME + " (" +
            Student.SID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Student.SURNAME + " TEXT, " +
            Student.OTHERNAME + " TEXT, " +
            Student.SEX + " TEXT " +
            ")";
    }
}
