import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SelectFile implements ActionListener {

    private final JFileChooser fileChooser = new JFileChooser();
    private final JFrame textInput = new JFrame("Input File");
    private final JPanel textPanel = new JPanel();

    private boolean submitted = false;
    private boolean setUpDone = false;
    private boolean closed = false;
    
    private String text = "";

    public void setUpForInput() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);

        textInput.setAlwaysOnTop(true);
        textInput.setResizable(true);
        textInput.setSize(700, 400);
        textInput.setLocationRelativeTo(null);
        textInput.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        textInput.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                closed = true;
            }
        });

        fileChooser.addActionListener(this);

        textPanel.add(fileChooser);
        textInput.add(textPanel);

        textInput.setVisible(true);

        setUpDone = true;
        closed = false;
    }

    public String getText() {
        return text;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public boolean isSetUpDone() {
        return setUpDone;
    }

    public boolean isClosed() {
        return closed;
    }

    public void reset() {
        setUpDone = false;
        submitted = false;
        closed = false;
        text = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        if (cmd.equals(JFileChooser.APPROVE_SELECTION)) {
            submitted = true;
            text = fileChooser.getSelectedFile().getAbsolutePath();
            closed = false;
            textInput.dispose();
        }

        else if (cmd.equals(JFileChooser.CANCEL_SELECTION)) {
            submitted = false;
            closed = true;
            textInput.dispose();
        }
    }
}
