package currency;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;

public class CurrencyExchange extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CurrencyExchange frame = new CurrencyExchange();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CurrencyExchange() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel menu = new JPanel();
		menu.setBackground(Color.DARK_GRAY);
		contentPane.add(menu, BorderLayout.NORTH);
		menu.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel dashboard = new JPanel();
		contentPane.add(dashboard, BorderLayout.CENTER);
		dashboard.setLayout(new CardLayout(0, 0));
		
		JPanel appConverter = new JPanel();
		appConverter.setBackground(Color.LIGHT_GRAY);
		dashboard.add(appConverter, "name_714925199361600");
		appConverter.setLayout(new MigLayout("", "[grow][grow][grow]", "[][][grow][grow][][][]"));
		
		JTextPane textPane_2 = new JTextPane();
		appConverter.add(textPane_2, "cell 0 2,growx");
		
		JComboBox comboBox = new JComboBox();
		appConverter.add(comboBox, "cell 1 2,growx");
		
		JTextPane textPane = new JTextPane();
		appConverter.add(textPane, "cell 2 2,growx");
		
		JTextPane textPane_3 = new JTextPane();
		appConverter.add(textPane_3, "cell 0 3,growx");
		
		JComboBox comboBox_1 = new JComboBox();
		appConverter.add(comboBox_1, "cell 1 3,growx");
		
		JTextPane textPane_1 = new JTextPane();
		appConverter.add(textPane_1, "cell 2 3,growx");
		
		JButton btnNewButton = new JButton("Get data from API");
		appConverter.add(btnNewButton, "cell 0 6,alignx center");
		
		JButton btnNewButton_1 = new JButton("Load DB");
		appConverter.add(btnNewButton_1, "cell 1 6,alignx center");
		
		JButton btnNewButton_2 = new JButton("Delete DB");
		appConverter.add(btnNewButton_2, "cell 2 6,alignx center");
		
		JPanel appViewer = new JPanel();
		appViewer.setBackground(Color.GRAY);
		dashboard.add(appViewer, "name_714913922581402");
		appViewer.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][]"));
		
		JLabel lblCurrency = new JLabel("CURRENCY");
		lblCurrency.setForeground(Color.WHITE);
		lblCurrency.setFont(new Font("Tahoma", Font.BOLD, 12));
		appViewer.add(lblCurrency, "cell 0 0,alignx center");
		
		JLabel lblPurchase = new JLabel("PURCHASE");
		lblPurchase.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPurchase.setForeground(Color.WHITE);
		appViewer.add(lblPurchase, "cell 1 0,alignx center");
		
		JLabel lblSale = new JLabel("SALE");
		lblSale.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSale.setForeground(Color.WHITE);
		appViewer.add(lblSale, "cell 2 0,alignx center");
		
		JLabel label = new JLabel("1");
		appViewer.add(label, "cell 0 1,alignx center");
		
		JTextPane textPane_11 = new JTextPane();
		appViewer.add(textPane_11, "cell 1 1,grow");
		
		JTextPane textPane_12 = new JTextPane();
		appViewer.add(textPane_12, "cell 2 1,grow");
		
		JLabel label_1 = new JLabel("2");
		appViewer.add(label_1, "cell 0 2,alignx center");
		
		JTextPane textPane_14 = new JTextPane();
		appViewer.add(textPane_14, "cell 1 2,grow");
		
		JTextPane textPane_16 = new JTextPane();
		appViewer.add(textPane_16, "cell 2 2,grow");
		
		JLabel label_2 = new JLabel("3");
		appViewer.add(label_2, "cell 0 3,alignx center");
		
		JTextPane textPane_15 = new JTextPane();
		appViewer.add(textPane_15, "cell 1 3,grow");
		
		JTextPane textPane_17 = new JTextPane();
		appViewer.add(textPane_17, "cell 2 3,grow");
		
		JLabel label_3 = new JLabel("4");
		appViewer.add(label_3, "cell 0 4,alignx center");
		
		JTextPane textPane_13 = new JTextPane();
		appViewer.add(textPane_13, "cell 1 4,grow");
		
		JTextPane textPane_18 = new JTextPane();
		appViewer.add(textPane_18, "cell 2 4,grow");
		
		JLabel label_4 = new JLabel("5");
		appViewer.add(label_4, "cell 0 5,alignx center");
		
		JTextPane textPane_20 = new JTextPane();
		appViewer.add(textPane_20, "cell 1 5,grow");
		
		JTextPane textPane_21 = new JTextPane();
		appViewer.add(textPane_21, "cell 2 5,grow");
		
		JButton btnNewButton_3 = new JButton("Get data from API");
		appViewer.add(btnNewButton_3, "cell 0 6,alignx center");
		
		JButton btnLoadDataFrom = new JButton("Load data from DB");
		appViewer.add(btnLoadDataFrom, "cell 1 6,alignx center");
		
		JComboBox comboBox_2 = new JComboBox();
		appViewer.add(comboBox_2, "cell 2 6,growx");
		
		JButton btnConverter = new JButton("Convert currencies");
		btnConverter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dashboard.removeAll();
				dashboard.add(appConverter);
				dashboard.repaint();
				dashboard.revalidate();
			}
		});
		menu.add(btnConverter);
		
		JButton btnViewer = new JButton("Check courses");
		btnViewer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dashboard.removeAll();
				dashboard.add(appViewer);
				dashboard.repaint();
				dashboard.revalidate();
			}
		});
		menu.add(btnViewer);
		
	}

}
