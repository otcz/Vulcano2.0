package com.company;

import com.luciad.io.TLcdIOUtil;
import com.luciad.util.logging.ILcdLogger;
import com.luciad.util.logging.TLcdLoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * This ILcdAction shows a JTextArea in an application pane that contains the
 * contents of a readme file.
 */
public class ShowReadMeUtil {

  private static final ILcdLogger LOGGER = TLcdLoggerFactory.getLogger(ShowReadMeUtil.class);

  private static final String NO_README_STRING = "Readme file name is not set.\nNo help available.";
  private final JEditorPane fEditorPane = new JEditorPane("text/html", NO_README_STRING);
  private final JScrollPane fScrollPane = new JScrollPane(fEditorPane);

  public ShowReadMeUtil() {
    fEditorPane.setEditable(false);
    fScrollPane.setPreferredSize(new Dimension(500, 200));
    fScrollPane.setMinimumSize(new Dimension(10, 10));

  }

  public void setReadMeFileName(String aReadMeFileName) {
    try {
      TLcdIOUtil io_util = new TLcdIOUtil();
      io_util.setSourceName(aReadMeFileName);
      fEditorPane.setPage(io_util.getURL());
    } catch (IOException e) {
      LOGGER.error(aReadMeFileName + " does not exist or cannot be read.", e);
      fEditorPane.setText(
          aReadMeFileName + " does not exist or cannot be read."
                         );
    }
    //scroll to top
    fEditorPane.setCaretPosition(0);
  }

  public Component getContent() {

    return fScrollPane;
  }
}
