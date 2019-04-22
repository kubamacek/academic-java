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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.json.*;
import org.jdesktop.beansbinding.Property;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class CurrencyExchange extends JFrame {

	private String url = "http://api.nbp.pl/api/exchangerates/tables/a/?format=json";
	private JPanel contentPane;
	private JTextField txtConvertFrom;
	private JTextField txtConvertTo;
	private JTextField txtPLN;
	private JTextField txtLoadData_converter;
	private JTextField txtLoadData_viewer;
	private JTextField txtDeleteData_viewer;
	private JTextPane value_PLN;
	private JTextPane value_Converted;
	private String[] currencies = { "USD", "EUR", "GBP", "CHF", "AUD"};
	public Double USDcourse;
	public Double EURcourse;
	public Double GBPcourse;
	public Double CHFcourse;
	public Double AUDcourse;

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
		DatabaseHandler db = new DatabaseHandler();
		db.connect();
		db.createNewTableIfNotExist("currency");
		db.disconnect();
		HttpHandler handler = new HttpHandler(url);
		try {
			handler.load();
		} catch (IOException err) {
			// get currency from db
			err.printStackTrace();
		}
		
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

		value_Converted = new JTextPane();
		appConverter.add(value_Converted, "cell 2 0,growx,aligny bottom");

		value_PLN = new JTextPane();
		appConverter.add(value_PLN, "cell 2 1,growx,aligny top");
		
		txtConvertFrom = new JTextField();
		txtConvertFrom.setEditable(false);
		txtConvertFrom.setText("Convert from:");
		appConverter.add(txtConvertFrom, "cell 0 0,growx,aligny bottom");
		txtConvertFrom.setColumns(10);
		
		JComboBox selectCurrency = new JComboBox(currencies);
		selectCurrency.setSelectedIndex(4);
		selectCurrency.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double amount = Double.parseDouble(value_Converted.getText());
				String currency = selectCurrency.getSelectedItem().toString();
				if (currency.equals("USD")) {
					value_PLN.setText(String.valueOf(amount*USDcourse));
				}
				else if (currency.equals("EUR")) {
					value_PLN.setText(String.valueOf(amount*EURcourse));
				}
				else if (currency.equals("GBP")) {
					value_PLN.setText(String.valueOf(amount*GBPcourse));
				}
				else if (currency.equals("CHF")) {
					value_PLN.setText(String.valueOf(amount*CHFcourse));
				}
				else if (currency.equals("AUD")) {
					value_PLN.setText(String.valueOf(amount*AUDcourse));
				}
			}
		});
		appConverter.add(selectCurrency, "flowy,cell 1 0,growx,aligny bottom");
		
		txtConvertTo = new JTextField();
		txtConvertTo.setEditable(false);
		txtConvertTo.setText("Convert to:\r\n");
		appConverter.add(txtConvertTo, "cell 0 1,growx,aligny top");
		txtConvertTo.setColumns(10);
		
		txtPLN = new JTextField();
		txtPLN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPLN.setEditable(false);
		txtPLN.setHorizontalAlignment(SwingConstants.LEFT);
		txtPLN.setText("PLN");
		appConverter.add(txtPLN, "cell 1 1,growx,aligny top");
		txtPLN.setColumns(10);
		
		txtLoadData_converter = new JTextField();
		txtLoadData_converter.setEditable(false);
		txtLoadData_converter.setText("Load data from date...");
		appConverter.add(txtLoadData_converter, "cell 2 2,growx");
		txtLoadData_converter.setColumns(10);
		
		db.connect();
		ArrayList<String> dates = db.selectAll("currency", "date");
		db.disconnect();
		JComboBox selectToLoad_converter = new JComboBox(dates.toArray());
		selectToLoad_converter.setSelectedIndex(-1);
		selectToLoad_converter.setToolTipText("Load from DB...");
		appConverter.add(selectToLoad_converter, "cell 2 3,growx");
		selectToLoad_converter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// set currencies from this date
			}
		});
		
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
		purchaseUSD.setEditable(false);
		appViewer.add(purchaseUSD, "cell 1 1,grow");
		
		JTextPane saleUSD = new JTextPane();
		saleUSD.setEditable(false);
		appViewer.add(saleUSD, "cell 2 1,grow");
		
		JLabel lblEur = new JLabel("EUR");
		lblEur.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEur.setForeground(UIManager.getColor("Button.disabledShadow"));
		appViewer.add(lblEur, "cell 0 2,alignx center");
		
		JTextPane purchaseEUR = new JTextPane();
		purchaseEUR.setEditable(false);
		appViewer.add(purchaseEUR, "cell 1 2,grow");
		
		JTextPane saleEUR = new JTextPane();
		saleEUR.setEditable(false);
		appViewer.add(saleEUR, "cell 2 2,grow");
		
		JLabel lblGbp = new JLabel("GBP");
		lblGbp.setForeground(UIManager.getColor("Button.disabledShadow"));
		lblGbp.setFont(new Font("Tahoma", Font.BOLD, 12));
		appViewer.add(lblGbp, "cell 0 3,alignx center");
		
		JTextPane purchaseGBP = new JTextPane();
		purchaseGBP.setEditable(false);
		appViewer.add(purchaseGBP, "cell 1 3,grow");
		
		JTextPane saleGBP = new JTextPane();
		saleGBP.setEditable(false);
		appViewer.add(saleGBP, "cell 2 3,grow");
		
		JLabel lblChf = new JLabel("CHF");
		lblChf.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChf.setForeground(UIManager.getColor("Button.disabledShadow"));
		appViewer.add(lblChf, "cell 0 4,alignx center");
		
		JTextPane purchaseCHF = new JTextPane();
		purchaseCHF.setEditable(false);
		appViewer.add(purchaseCHF, "cell 1 4,grow");
		
		JTextPane saleCHF = new JTextPane();
		saleCHF.setEditable(false);
		appViewer.add(saleCHF, "cell 2 4,grow");
		
		JLabel lblAud = new JLabel("AUD\r\n");
		lblAud.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAud.setForeground(UIManager.getColor("Button.disabledShadow"));
		appViewer.add(lblAud, "cell 0 5,alignx center");
		
		JTextPane purchaseAUD = new JTextPane();
		purchaseAUD.setEditable(false);
		appViewer.add(purchaseAUD, "cell 1 5,grow");
		
		JTextPane saleAUD = new JTextPane();
		saleAUD.setEditable(false);
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
		
		JSONObject data = handler.getJsonObject();
		JsonParser parser = new JsonParser(data);
		String date = parser.getValue("effectiveDate");
		HashMap<String, Double> ratesMap = parser.getRatesConverter();
		System.out.println(ratesMap);
		USDcourse = ratesMap.get("USD");
		EURcourse = ratesMap.get("EUR");
		GBPcourse = ratesMap.get("GBP");
		CHFcourse = ratesMap.get("CHF");
		AUDcourse = ratesMap.get("AUD");
		
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
