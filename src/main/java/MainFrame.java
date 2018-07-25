import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{

    private JPanel mainP;
    private Button save;
    private Label label = new Label("counter");
    private TextField in;
    private TextField out;
    private Button plus;

    private JPanel errorP;
    private Label error;
    private int count = 0;

    private JPanel subP;

    private Label hexLabel;
    private TextField hexField;

    private Label bitLabel;
    private TextField bitField;

    public MainFrame() {
        setTitle("AWT tuto");
        setSize(800,600);
        setLayout(new GridLayout(3,1));

        mainP = new JPanel();
        mainP.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(mainP);

        save = new Button("save");
//        add(save);
        save.addActionListener(new Exit());

        mainP.add(label);

        in = new TextField("0", 10);
        mainP.add(in);

        out = new TextField(count + "", 10);
        out.setEditable(false);
        mainP.add(out);

        errorP = new JPanel();
        add(errorP);
        error = new Label("");
        errorP.add(error);

        plus = new Button("Count");
        mainP.add(plus);



        subP = new JPanel();
        subP.setLayout(new FlowLayout());

        hexField = new TextField();
        hexLabel = new Label("16進数");
        subP.add(hexLabel);
        subP.add(hexField);

        bitField = new TextField();
        bitLabel = new Label("2進数");
        subP.add(bitLabel);
        subP.add(bitField);

//        Scrollbar bar = new Scrollbar(Scrollbar.HORIZONTAL);
//        bar.setMinimum(0);
//        bar.setMaximum(256);
//        subP.add(bar);
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 256, 0);
        slider.setName("OK");
        slider.setMajorTickSpacing(64);
        slider.setMinorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            int val = (int) source.getValue();
            count = val;
            out.setText(val + "");
            recalculate();
            this.validate();
//            pack();
            if (!source.getValueIsAdjusting()) {
            }
        });
        subP.add(slider);

        add(subP);

        plus.addActionListener((ActionEvent e) -> {
            int num = 0;
            try {
                num = Integer.parseInt(in.getText());
                String bit = Integer.toBinaryString(num);
                String hex = Integer.toHexString(num);
                hexField.setText(hex);
                bitField.setText(bit);
                error.setForeground(Color.BLACK);
                error.setText("Your input was added !!!");
                this.validate();
            } catch (NumberFormatException e1) {
                error.setText("input number must be integer!!!");
                error.setForeground(Color.RED);
                this.validate();
            }
            count += num;
            out.setText(count + "");
        });

//        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void recalculate() {
        String bit = Integer.toBinaryString(count);
        String hex = Integer.toHexString(count);
        bitField.setText(bit);
        hexField.setText(hex);
    }

}

class Exit implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}

