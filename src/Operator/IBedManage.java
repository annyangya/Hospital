package Operator;

import entity.Bed;

public interface IBedManage {
    void BedAdd(Bed bed);
    void BedDelete(Bed bed);
    void BedUpdate(String Wno,String Bno, String attribute, String value);
    void BedQuery();
}
