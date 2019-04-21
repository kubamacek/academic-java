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
import javax.swing.SwingConstants;

public class CurrencyExchange extends JFrame {

	private JPanel contentPane;
	private JTextField txtConvertFrom;
	private JTextField txtConvertTo;
	private JTextField txtPLN;
	private JTextField txtLoadData_converter;
	private JTextField txtDeleteData_converter;
	private JTextField txtLoadData_viewer;
	private JTextField txtDeleteData_viewer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CurrencyExchange frame = new CurrencyExchange();
					frame.setVisible(true);
					frame.setTitle("CurrencyExchange");
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
		appConverter.setLayout(new MigLayout("", "[125px,grow][125px,grow][125px,grow]", "[grow][grow][][]"));
		
		txtConvertFrom = new JTextField();
		txtConvertFrom.setEditable(false);
		txtConvertFrom.setText("Convert from:");
		appConverter.add(txtConvertFrom, "cell 0 0,growx,aligny bottom");
		txtConvertFrom.setColumns(10);
		
		txtPLN = new JTextField();
		txtPLN.setEditable(false);
		txtPLN.setHorizontalAlignment(SwingConstants.CENTER);
		txtPLN.setText("PLN");
		appConverter.add(txtPLN, "cell 1 0,growx,aligny bottom");
		txtPLN.setColumns(10);
		
		JTextPane value_PLN = new JTextPane();
		appConverter.add(value_PLN, "cell 2 0,growx,aligny bottom");
		
		txtConvertTo = new JTextField();
		txtConvertTo.setEditable(false);
		txtConvertTo.setText("Convert to:\r\n");
		appConverter.add(txtConvertTo, "cell 0 1,growx,aligny top");
		txtConvertTo.setColumns(10);
		
		JComboBox selectCurrency = new JComboBox();
		appConverter.add(selectCurrency, "cell 1 1,growx,aligny top");
		
		JTextPane value_Converted = new JTextPane();
		appConverter.add(value_Converted, "cell 2 1,growx,aligny top");
		
		txtLoadData_converter = new JTextField();
		txtLoadData_converter.setEditable(false);
		txtLoadData_converter.setText("Load data from DB...");
		appConverter.add(txtLoadData_converter, "cell 1 2,growx");
		txtLoadData_converter.setColumns(10);
		
		txtDeleteData_converter = new JTextField();
		txtDeleteData_converter.setEditable(false);
		txtDeleteData_converter.setText("Delete data from DB...");
		txtDeleteData_converter.setColumns(10);
		appConverter.add(txtDeleteData_converter, "cell 2 2,growx");
		
		JButton btnGetFromAPI_converter = new JButton("Get data from API");
		appConverter.add(btnGetFromAPI_converter, "cell 0 3,growx");
		
		JComboBox selectToLoad_converter = new JComboBox();
		selectToLoad_converter.setToolTipText("Load from DB...");
		appConverter.add(selectToLoad_converter, "cell 1 3,growx");
		
		JComboBox selectToDelete_converter = new JComboBox();
		appConverter.add(selectToDelete_converter, "cell 2 3,growx");
		
		JPanel appViewer = new JPanel();
		appViewer.setBackground(Color.GRAY);
		dashboard.add(appViewer, "name_714913922581402");
		appViewer.setLayout(new MigLayout("", "[125px,grow][125px,grow][125px,grow]", "[grow][grow][grow][grow][grow][grow][][]"));
		
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
		
		JLabel lblUsd = new JLabel("USD");
		lblUsd.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsd.setForeground(UIManager.getColor("CheckBox.highlight"));
		appViewer.add(lblUsd, "cell 0 1,alignx center");
		
		JTextPane purchaseUSD = new JTextPane();
		appViewer.add(purchaseUSD, "cell 1 1,grow");
		
		JTextPane saleUSD = new JTextPane();
		appViewer.add(saleUSD, "cell 2 1,grow");
		
		JLabel lblEur = new JLabel("EUR");
		lblEur.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEur.setForeground(UIManager.getColor("Button.disabledShadow"));
		appViewer.add(lblEur, "cell 0 2,alignx center");
		
		JTextPane purchaseEUR = new JTextPane();
		appViewer.add(purchaseEUR, "cell 1 2,grow");
		
		JTextPane saleEUR = new JTextPane();
		appViewer.add(saleEUR, "cell 2 2,grow");
		
		JLabel lblGbp = new JLabel("GBP");
		lblGbp.setForeground(UIManager.getColor("Button.disabledShadow"));
		lblGbp.setFont(new Font("Tahoma", Font.BOLD, 12));
		appViewer.add(lblGbp, "cell 0 3,alignx center");
		
		JTextPane purchaseGBP = new JTextPane();
		appViewer.add(purchaseGBP, "cell 1 3,grow");
		
		JTextPane saleGBP = new JTextPane();
		appViewer.add(saleGBP, "cell 2 3,grow");
		
		JLabel lblChf = new JLabel("CHF");
		lblChf.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChf.setForeground(UIManager.getColor("Button.disabledShadow"));
		appViewer.add(lblChf, "cell 0 4,alignx center");
		
		JTextPane purchaseCHF = new JTextPane();
		appViewer.add(purchaseCHF, "cell 1 4,grow");
		
		JTextPane saleCHF = new JTextPane();
		appViewer.add(saleCHF, "cell 2 4,grow");
		
		JLabel lblAud = new JLabel("AUD\r\n");
		lblAud.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAud.setForeground(UIManager.getColor("Button.disabledShadow"));
		appViewer.add(lblAud, "cell 0 5,alignx center");
		
		JTextPane purchaseAUD = new JTextPane();
		appViewer.add(purchaseAUD, "cell 1 5,grow");
		
		JTextPane saleAUD = new JTextPane();
		appViewer.add(saleAUD, "cell 2 5,grow");
		
		txtLoadData_viewer = new JTextField();
		txtLoadData_viewer.setEditable(false);
		txtLoadData_viewer.setText("Load data from DB...");
		appViewer.add(txtLoadData_viewer, "cell 1 6,growx");
		txtLoadData_viewer.setColumns(10);
		
		txtDeleteData_viewer = new JTextField();
		txtDeleteData_viewer.setEditable(false);
		txtDeleteData_viewer.setText("Delete data from DB...");
		appViewer.add(txtDeleteData_viewer, "cell 2 6,growx");
		txtDeleteData_viewer.setColumns(10);
		
		JButton btnGetFromAPI_viewer = new JButton("Get data from API");
		appViewer.add(btnGetFromAPI_viewer, "cell 0 7,growx");
		
		JComboBox selectToLoad_viewer = new JComboBox();
		appViewer.add(selectToLoad_viewer, "cell 1 7,growx");
		
		JComboBox selectToDelete_viewer = new JComboBox();
		appViewer.add(selectToDelete_viewer, "cell 2 7,growx");
		
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
