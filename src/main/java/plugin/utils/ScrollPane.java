package plugin.utils;

import javax.swing.*;

public class ScrollPane extends JScrollPane {
    public ScrollPane(JPanel content) {
        super(content);
        setBorder(BorderFactory.createEmptyBorder());
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getVerticalScrollBar().setUnitIncrement(16);
    }
}
