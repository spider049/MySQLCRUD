package net.simplifiedcoding.mysqlcrud;

/**
 * Created by Belal on 10/24/2015.
 */
public class Config {

    //Address of our scripts of the CRUD
    public static final String HOST_DOMAIN = "http://spider049.16mb.com";       // for chance WWW Host
    public static final String FOLDER_WWW = "/android/CRUD/";                   // for chance WWW Host
            // PHP_file_name
    public static final String PHP_ADDEMP = "addEmp.php";                       // for chance WWW Host
    public static final String PHP_GETALLEMP = "getAllEmp.php";                 // for chance WWW Host
    public static final String PHP_GETEMP = "getEmp.php?id=";                   // for chance WWW Host
    public static final String PHP_UPDATEEMP = "updateEmp.php";                 // for chance WWW Host
    public static final String PHP_DELETEEMP = "deleteEmp.php?id=";             // for chance WWW Host

    public static final String PHP_ADD100 = "addEmp100.php";     // add 100 record

    public static final String URL_ADD = HOST_DOMAIN + FOLDER_WWW + PHP_ADDEMP;
    public static final String URL_GET_ALL = HOST_DOMAIN + FOLDER_WWW + PHP_GETALLEMP;
    public static final String URL_GET_EMP = HOST_DOMAIN + FOLDER_WWW + PHP_GETEMP;
    public static final String URL_UPDATE_EMP = HOST_DOMAIN + FOLDER_WWW + PHP_UPDATEEMP;
    public static final String URL_DELETE_EMP = HOST_DOMAIN + FOLDER_WWW + PHP_DELETEEMP;

    public static final String URL_ADD100 =HOST_DOMAIN + PHP_ADD100;                    // add 100 record

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_DESG = "desg";
    public static final String KEY_EMP_SAL = "salary";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_DESG = "desg";
    public static final String TAG_SAL = "salary";

    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";
}
