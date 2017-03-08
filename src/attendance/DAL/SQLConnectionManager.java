/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author Desmoswal
 */
public class SQLConnectionManager
{
    private SQLServerDataSource ds =
        new SQLServerDataSource();

    public SQLConnectionManager()
    {
        setupDataSource();
    }
    
    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }
    
    private void setupDataSource()
    {
        ds.setDatabaseName("CS2016B_16_Attendance");
        ds.setUser("CS2016B_19");
        ds.setPassword("CS2016B_19");
        //ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
    }
}
