package plugin.utils;

import org.protege.editor.core.platform.OSUtils;

import javax.swing.*;
import java.awt.*;

public class TitleLabel extends JLabel {
    private static final Font OS_X_FONT = new Font("Helvetica Neue", Font.BOLD, 14);
    private static final Font DEFAULT_FONT = new Font("Dialog", Font.BOLD, 14);

    public TitleLabel(String title) { 
        super(title);
        setFont(OSUtils.isOSX() ? OS_X_FONT : DEFAULT_FONT);
        setFont(DEFAULT_FONT);
    }
}
