package configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Project: fake-mario
 * Author: Michele
 * File: BaseClass
 * Creation: 20/03/2022
 */
public abstract class BaseClass {

//    public static final Logger LOG = Logger.getLogger("[BaseClass]");
    public static void info(String msg){
        System.out.println("[" + new SimpleDateFormat().format(new Date()) + "] " + msg);
    }
}
