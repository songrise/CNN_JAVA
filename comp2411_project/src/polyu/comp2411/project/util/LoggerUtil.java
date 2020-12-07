package polyu.comp2411.project.util;

import polyu.comp2411.project.dao.LoggerDAO;
import polyu.comp2411.project.dao.impl.LoggerDAOImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerUtil {
    public static void addLog(String info) {
        long l = System.currentTimeMillis();
        Date d = new Date(l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LoggerDAO loggerDAO = new LoggerDAOImpl();
        loggerDAO.addLog(info + " at: " + simpleDateFormat.format(d));
        TransactionUtil.commit();
    }
}
