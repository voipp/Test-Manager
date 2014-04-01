package models;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 12.03.14
 * Time: 13:01
 * To change this template use File | Settings | File Templates.
 */

import java.util.List;

/**
 * simple data wrapper для оборачивания ответов.
 * Имеет все параметры, заявленные в протоколе обмена DataTables <--> Server
 *
 * @param <T>
 */
public class SimpleWrapper<T> {

    Integer iTotalRecords;
    Integer iTotalDisplayRecords;
    String  sEcho;
    private T data;

    public SimpleWrapper(T data) {
        this.data = data;
    }

    public SimpleWrapper() {
    }

    public Integer getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public Integer getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(Integer iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        if (data instanceof List) {
            iTotalDisplayRecords = ((List) data).size();
        } else {
            iTotalDisplayRecords = 1;
        }
        iTotalRecords = iTotalDisplayRecords;
    }

}
