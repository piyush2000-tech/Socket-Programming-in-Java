//Program to server side//
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class server implements ActionListener
{
	Frame fr;
	Button b1,b2,b3,b4;
	TextField t1,t2,t3,t4;
	Label l1,l2,l3,l4,l5,l6;
	ServerSocket server;
	Socket client;
	double a,b,c;

	public server()
	{
		fr = new Frame("Server");	
		fr.setSize(600,500);
		fr.setLayout(null);
		fr.setBackground(Color.cyan);

		l1 = new Label("Enter PortName");
		l2 = new Label("Number Received");
		l3 = new Label("Number Received");
		l4 = new Label("Answer");
		l5 = new Label();
		l1.setFont(new Font("verdana",Font.BOLD,20));
		l2.setFont(new Font("verdana",Font.BOLD,20));
		l3.setFont(new Font("verdana",Font.BOLD,20));
		l4.setFont(new Font("verdana",Font.BOLD,20));
		l5.setFont(new Font("verdana",Font.BOLD,16));
		l1.setBounds(100,100,200,30);
		l2.setBounds(100,200,200,30);
		l3.setBounds(100,250,200,30);
		l4.setBounds(100,300,200,30);
		l5.setBounds(430,140,120,30);
		fr.add(l1);
		fr.add(l2);
		fr.add(l3);
		fr.add(l4);
		fr.add(l5);
		
		t1 = new TextField();
		t2 = new TextField();
		t3 = new TextField();
		t4 = new TextField();
		t1.setFont(new Font("verdana",Font.BOLD,20));
		t2.setFont(new Font("verdana",Font.BOLD,20));
		t3.setFont(new Font("verdana",Font.BOLD,20));
		t4.setFont(new Font("verdana",Font.BOLD,20));
		t1.setBounds(300,100,250,30);
		t2.setBounds(300,200,250,30);
		t3.setBounds(300,250,250,30);
		t4.setBounds(300,300,250,30);
		fr.add(t1);
		fr.add(t2);
		fr.add(t3);
		fr.add(t4);
		
		b1 = new Button("Start");
		b2 = new Button("Received");
		b3 = new Button("Addition");
		b4 = new Button("Send Response");
		b1.setBounds(300,140,120,30);
		b2.setBounds(300,340,120,30);
		b3.setBounds(430,340,120,30);
		b4.setBounds(300,400,150,30);
		b1.setFont(new Font("verdana",Font.BOLD,16));
		b2.setFont(new Font("verdana",Font.BOLD,16));
		b3.setFont(new Font("verdana",Font.BOLD,16));
		b4.setFont(new Font("verdana",Font.BOLD,16));
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		fr.add(b1);
		fr.add(b2);
		fr.add(b3);
		fr.add(b4);

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
				int pno = Integer.parseInt(t1.getText());
				server = new ServerSocket(pno);
				l5.setText("started");
			}
			catch(Exception e)
			{
				l5.setText("not started");
			}
		}

		if(ae.getSource()==b2)
		{
			try
			{
				client = server.accept();
				InputStream is = client.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				a = dis.readDouble();
				b = dis.readDouble();
				t2.setText(""+a);
				t3.setText(""+b);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(fr,"Client is not available");
			}
		}

		if(ae.getSource()==b3)
		{
			c = a + b;
			t4.setText(""+c);
		}

		if(ae.getSource()==b4)
		{
			try
			{
				OutputStream os = client.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				dos.writeDouble(c);
				client.close();
			}
			catch(Exception e)
			{
			}
		}
	}

	public static void main(String args[])
	{
		new server();
	} 
}