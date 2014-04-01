package models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 03.03.14
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class DataTablesRequest {

    @XmlElement(name = "sEcho")
    private String  sEcho;
    private int     iColumns;
    private String  sColumns;
    private int     iDisplayStart;
    private int     iDisplayLength;
    private String  mDataProp_0;
    private String  mDataProp_1;
    private String  mDataProp_2;
    private String  mDataProp_3;
    private int     iSortCol;
    private String  sSortDir_0;
    private int     iSortingCols;
    private Boolean bSortable_0;
    private Boolean bSortable_1;
    private Boolean bSortable_2;
    private Boolean bSortable_3;

    public DataTablesRequest() {
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public int getiColumns() {
        return iColumns;
    }

    public void setiColumns(int iColumns) {
        this.iColumns = iColumns;
    }

    public String getsColumns() {
        return sColumns;
    }

    public void setsColumns(String sColumns) {
        this.sColumns = sColumns;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public String getmDataProp_0() {
        return mDataProp_0;
    }

    public void setmDataProp_0(String mDataProp_0) {
        this.mDataProp_0 = mDataProp_0;
    }

    public String getmDataProp_1() {
        return mDataProp_1;
    }

    public void setmDataProp_1(String mDataProp_1) {
        this.mDataProp_1 = mDataProp_1;
    }

    public String getmDataProp_2() {
        return mDataProp_2;
    }

    public void setmDataProp_2(String mDataProp_2) {
        this.mDataProp_2 = mDataProp_2;
    }

    public String getmDataProp_3() {
        return mDataProp_3;
    }

    public void setmDataProp_3(String mDataProp_3) {
        this.mDataProp_3 = mDataProp_3;
    }

    public int getiSortCol() {
        return iSortCol;
    }

    public void setiSortCol(int iSortCol) {
        this.iSortCol = iSortCol;
    }

    public String getsSortDir_0() {
        return sSortDir_0;
    }

    public void setsSortDir_0(String sSortDir_0) {
        this.sSortDir_0 = sSortDir_0;
    }

    public int getiSortingCols() {
        return iSortingCols;
    }

    public void setiSortingCols(int iSortingCols) {
        this.iSortingCols = iSortingCols;
    }

    public Boolean getbSortable_0() {
        return bSortable_0;
    }

    public void setbSortable_0(Boolean bSortable_0) {
        this.bSortable_0 = bSortable_0;
    }

    public Boolean getbSortable_1() {
        return bSortable_1;
    }

    public void setbSortable_1(Boolean bSortable_1) {
        this.bSortable_1 = bSortable_1;
    }

    public Boolean getbSortable_2() {
        return bSortable_2;
    }

    public void setbSortable_2(Boolean bSortable_2) {
        this.bSortable_2 = bSortable_2;
    }

    public Boolean getbSortable_3() {
        return bSortable_3;
    }

    public void setbSortable_3(Boolean bSortable_3) {
        this.bSortable_3 = bSortable_3;
    }
}
