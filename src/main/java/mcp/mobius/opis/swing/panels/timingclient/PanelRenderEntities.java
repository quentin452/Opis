package mcp.mobius.opis.swing.panels.timingclient;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import mcp.mobius.opis.api.IMessageHandler;
import mcp.mobius.opis.api.ITabPanel;
import mcp.mobius.opis.data.holders.clienttypes.DataEntityRender;
import mcp.mobius.opis.data.holders.newtypes.CachedString;
import mcp.mobius.opis.data.holders.newtypes.DataTiming;
import mcp.mobius.opis.network.PacketBase;
import mcp.mobius.opis.network.enums.Message;
import mcp.mobius.opis.swing.SelectedTab;
import mcp.mobius.opis.swing.actions.ActionRunOpisClient;
import mcp.mobius.opis.swing.widgets.JTableStats;

import net.miginfocom.swing.MigLayout;

public class PanelRenderEntities extends JPanel implements ITabPanel, IMessageHandler {

    private JTableStats table;
    private JButton btnRunRender;
    private JLabel lblTotal;

    /**
     * Create the panel.
     */
    public PanelRenderEntities() {
        setLayout(new MigLayout("", "[grow][grow][]", "[][grow][]"));

        btnRunRender = new JButton("Run Render");
        add(btnRunRender, "cell 2 0");
        btnRunRender.addActionListener(new ActionRunOpisClient());

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, "cell 0 1 3 1,grow");

        table = new JTableStats(
                new String[] { "Name", "Coordinates", "Timing" },
                new Class[] { CachedString.class, Object.class, DataTiming.class });
        scrollPane.setViewportView(table);

        lblTotal = new JLabel("Total : 0 µs");
        add(lblTotal, "cell 0 2 3 1,alignx center");
    }

    public void setTable(ArrayList<DataEntityRender> data) {

        DefaultTableModel model = table.getModel();
        int row = this.updateData(table, model, DataEntityRender.class);

        for (DataEntityRender o : data) {

            model.addRow(
                    new Object[] { o.name, String.format("[ %4d %4d %4d ]", o.pos.x, o.pos.y, o.pos.z), o.update });
        }

        this.dataUpdated(table, model, row);
    }

    public JTableStats getTable() {
        return table;
    }

    public <U> int updateData(JTable table, DefaultTableModel model, Class<U> datatype) {
        int row = table.getSelectedRow();

        if (model.getRowCount() > 0) for (int i = model.getRowCount() - 1; i >= 0; i--) model.removeRow(i);

        return row;
    }

    public void dataUpdated(JTable table, DefaultTableModel model, int row) {
        model.fireTableDataChanged();

        try {
            table.setRowSelectionInterval(row, row);
        } catch (IllegalArgumentException e) {

        }
    }

    public JButton getBtnRunRender() {
        return btnRunRender;
    }

    public JLabel getLblTotal() {
        return lblTotal;
    }

    @Override
    public boolean handleMessage(Message msg, PacketBase rawdata) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public SelectedTab getSelectedTab() {
        return SelectedTab.RENDERENTITIES;
    }

    @Override
    public boolean refreshOnString() {
        return false;
    }

    @Override
    public boolean refresh() {
        return false;
    }
}
