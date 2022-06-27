package plugin.utils;

import javax.swing.*;

public class ScrollPane extends JScrollPane {
    public ScrollPane(JPanel content) {
        super(content);
        setBorder(BorderFactory.createEmptyBorder());
        getVerticalScrollBar().setUnitIncrement(16);
        getHorizontalScrollBar().setUnitIncrement(16);

    }
}
