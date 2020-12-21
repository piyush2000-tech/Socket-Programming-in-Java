//Program to Client Side//
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

class client implements ActionListener
{
	Frame fr;
	Button b1,b2;
	TextField t1,t2,t3,t4,t5;
	Label l1,l2,l3,l4,l5,l6;
	Socket sk;
	double a,b;

	public client()
	{
		fr = new Frame("Client");	
		fr.setSize(600,500);
		fr.setLayout(null);
		fr.setBackground(Color.pink);

		l1 = new Label("Enter PortName");
		l2 = new Label("Enter PortNumber");
		l3 = new Label("Enter Number");
		l4 = new Label("Enter Number");
		l5 = new Label("Answer");
		l6 = new Label();
		l1.setFont(new Font("verdana",Font.BOLD,20));
		l2.setFont(new Font("verdana",Font.BOLD,20));
		l3.setFont(new Font("verdana",Font.BOLD,20));
		l4.setFont(new Font("verdana",Font.BOLD,20));
		l5.setFont(new Font("verdana",Font.BOLD,20));
		l6.setFont(new Font("verdana",Font.BOLD,15));
		l1.setBounds(100,100,200,30);
		l2.setBounds(100,150,200,30);
		l3.setBounds(100,250,200,30);
		l4.setBounds(100,300,200,30);
		l5.setBounds(100,350,200,30);
		l6.setBounds(430,190,120,30);
		fr.add(l1);
		fr.add(l2);
		fr.add(l3);
		fr.add(l4);
		fr.add(l5);
		fr.add(l6);
	
		t1 = new TextField("localhost");
		t2 = new TextField();
		t3 = new TextField();
		t4 = new TextField();
		t5 = new TextField();
		t1.setFont(new Font("verdana",Font.BOLD,20));
		t2.setFont(new Font("verdana",Font.BOLD,20));
		t3.setFont(new Font("verdana",Font.BOLD,20));
		t4.setFont(new Font("verdana",Font.BOLD,20));
		t5.setFont(new Font("verdana",Font.BOLD,20));
		t1.setBounds(300,100,200,30);
		t2.setBounds(300,150,200,30);
		t3.setBounds(300,250,200,30);
		t4.setBounds(300,300,200,30);
		t5.setBounds(300,350,200,30);
		fr.add(t1);
		fr.add(t2);
		fr.add(t3);
		fr.add(t4);
		fr.add(t5);

		b1 = new Button("Connect");
		b2 = new Button("Send Data");
		b1.setBounds(300,190,120,30);
		b2.setBounds(300,390,120,30);
		b1.setFont(new Font("verdana",Font.BOLD,16));
		b2.setFont(new Font("verdana",Font.BOLD,16));
		b1.addActionListener(this);
		b2.addActionListener(this);
		fr.add(b1);
		fr.add(b2);

		fr.setVisible(true);
		fr.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				fr.dispose();
			}
		});
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			try
			{
				String pname = t1.getText();
				int pno = Integer.parseInt(t2.getText());
				sk = new Socket(pname,pno);
				l6.setText("connected");	
			}
			catch(Exception e)
			{
				l6.setText("not connected");	
			}
		}

		if(ae.getSource()==b2)
		{
			try
			{
				a = Double.parseDouble(t3.getText());
				b = Double.parseDouble(t4.getText());
				OutputStream os = sk.getOutputStream();
 				DataOutputStream dos = new DataOutputStream(os);
 				dos.writeDouble(a);
 				dos.writeDouble(b);

 				InputStream is = sk.getInputStream();
 				DataInputStream dis = new DataInputStream(is);
 				double res = dis.readDouble();
				t5.setText(""+res);
			}
			catch(Exception e)
			{
			}
		}
	}

	public static void main(String args[])
	{
		new client();
	} 
}