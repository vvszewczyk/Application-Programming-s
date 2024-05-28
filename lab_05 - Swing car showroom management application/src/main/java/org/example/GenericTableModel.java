package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class GenericTableModel<T> extends AbstractTableModel
{
    private List<T> dataList = new ArrayList<>();
    private String[] columnNames;
    private DataAdapter<T> dataAdapter;

    public interface DataAdapter<T>
    {
        String[] getColumnNames();
        Object getValueAt(T item, int columnIndex);
    }

    public GenericTableModel(DataAdapter<T> dataAdapter)
    {
        this.dataAdapter = dataAdapter;
        this.columnNames = dataAdapter.getColumnNames();
    }

    public void setDataList(List<T> dataList)
    {
        this.dataList = dataList;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount()
    {
        return dataList.size();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        T item = dataList.get(rowIndex);
        return dataAdapter.getValueAt(item, columnIndex);
    }


    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
}
