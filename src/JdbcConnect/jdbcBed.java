package JdbcConnect;


import Operator.IBedManage;
import entity.Bed;

import java.sql.SQLException;
import java.util.ArrayList;

public class jdbcBed implements IBedManage{

    Bed bedEntity;
    private ArrayList<Bed> bed=new ArrayList<Bed>();

    public ArrayList<Bed> getBed() {
        return bed;
    }

    public jdbcBed(Bed bed1){
        bedEntity=bed1;
    }
    @Override
    public void BedAdd(Bed bed) {
        String sql="insert into Bed values('"+bed.getWno()+"',"+"'"+bed.getBno()+"',"+"'"+bed.getBinform()+"',"+"'"+bed.getBoffice()+"')";
        try {
            jdbcUtils.jdbcExecuteUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void BedDelete(Bed bed) {
        String sql="delete Bed where Wno='"+bed.getWno()+"' and Bno='"+bed.getBno()+"'";
        try {
            jdbcUtils.jdbcExecuteUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void BedUpdate(String Wno,String Bno, String attribute, String value) {
        String sql="update Bed set "+attribute+"='"+value+"' where Bno="+"'"+Bno+"'and Wno='"+Wno+"'";
        try {
            jdbcUtils.jdbcExecuteUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void BedQuery() {
        String sql="select * from Bed";
        try {
            jdbcUtils.jdbcExecuteQuery(sql);
            while(jdbcUtils.resultSet.next()){
                Bed bed1=new Bed();
                bed1.setWno(jdbcUtils.resultSet.getString(1).toString().trim());
                bed1.setBno(jdbcUtils.resultSet.getString(2).toString().trim());
                bed1.setBinform(jdbcUtils.resultSet.getString(3).toString().trim());
                bed1.setBoffice(jdbcUtils.resultSet.getString(4).toString().trim());
                bed.add(bed1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
