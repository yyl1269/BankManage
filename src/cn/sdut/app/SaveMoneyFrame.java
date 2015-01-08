package cn.sdut.app;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.sdut.biz.AccountBiz;

/*�����˻����ڣ����������ͳ�ʼ���*/
@SuppressWarnings("serial")
public class SaveMoneyFrame extends JFrame implements ActionListener {

	JLabel lb_accountId;
	JTextField tf_accountId;
	JLabel lb_amount;
	JTextField tf_amount;
	JButton bt_OK;
	JButton bt_reset;
	JScrollPane sp_message;
	JTextArea ta_message;

	public SaveMoneyFrame(String title) {
		super(title);
		init();
	}

	private void init() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setBounds(300, 300, 300, 300);
		setLayout(new FlowLayout());
		lb_accountId = new JLabel("�������û��˺�:");
		tf_accountId = new JTextField(15);
		lb_amount = new JLabel("�����������:");
		tf_amount = new JTextField(15);
		bt_OK = new JButton("ȷ��");
		bt_reset = new JButton("����");
		ta_message = new JTextArea(5, 25);
		ta_message.setLineWrap(true);
		sp_message = new JScrollPane(ta_message);

		add(lb_accountId);
		add(tf_accountId);
		add(lb_amount);
		add(tf_amount);
		add(bt_OK);
		add(bt_reset);
		add(sp_message);
		setVisible(true);

		bt_OK.addActionListener(this);
		bt_reset.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "ȷ��") {
			String str_accountId = tf_accountId.getText();
			String amount = tf_amount.getText();
			int accountId = Integer.parseInt(str_accountId);

			float saveAmount = 0.0f;

			try {
				saveAmount = Float.parseFloat(amount);
				AccountBiz accountBiz = new AccountBiz();
				if (saveAmount < 0) {
					ta_message.setText("�Բ���������Ľ��Ϊ������");
				} else {
					String strReturn = accountBiz.saveMoney(accountId,
							saveAmount);
					ta_message.setText(strReturn);
				}
			} catch (NumberFormatException e1) {
				ta_message.setText("�Բ���������Ľ�����ֵ��");
			}

		} else {
			if (e.getActionCommand() == "����") {
				tf_accountId.setText("");
				tf_amount.setText("");
				ta_message.setText("");
			}
		}

	}

}