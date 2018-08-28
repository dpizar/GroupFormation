package org.soenea.assignments.domain.command;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

/**
 * Servlet implementation class CSVProcessor
 */
@WebServlet("/assignments/CSVProcessor")
public class CSVProcessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String INSERT = "INSERT INTO user (userName,version,password,firstName,lastName,groupId,userType) VALUES (?,?,?,?,?,?,?);";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CSVProcessor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String contentType = request.getContentType();
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
			DataInputStream in = new DataInputStream(request.getInputStream());
			int formDataLength = request.getContentLength();
			byte dataBytes[] = new byte[formDataLength];
			int byteRead = 0;
			int totalBytesRead = 0;

			while (totalBytesRead < formDataLength) {
				byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
				totalBytesRead += byteRead;
			}
			String file = new String(dataBytes);
			String saveFile = file.substring(file.indexOf("filename=\"") + 10);
			//System.out.println(saveFile);
			saveFile = saveFile.substring(saveFile.lastIndexOf("\\")+ 1,saveFile.indexOf("\""));
			//System.out.println("saveFile" + saveFile);
			saveFile = file.substring(file.indexOf("filename=\"") + 10);
			saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
			saveFile = saveFile.substring(saveFile.lastIndexOf("\\")+ 1,saveFile.indexOf("\""));
			int lastIndex = contentType.lastIndexOf("=");
			String boundary = contentType.substring(lastIndex + 1,contentType.length());
			int pos;

			pos = file.indexOf("filename=\"");
			pos = file.indexOf("\n", pos) + 1;
			pos = file.indexOf("\n", pos) + 1;
			pos = file.indexOf("\n", pos) + 1;
			int boundaryLocation = file.indexOf(boundary, pos) - 4;
			int startPos = ((file.substring(0, pos)).getBytes()).length;
			int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;

			FileOutputStream fileOut = new FileOutputStream(saveFile);
			fileOut.write(dataBytes, startPos, (endPos - startPos));

			String line = null;
			String userName = "";
			String password = "";
			String firstName = "";
			String lastName = "";
			String groupId = "";
			String userType = "";

			try{
				int c=0;
				BufferedReader input = new BufferedReader(new FileReader(saveFile));
				while (( line = input.readLine()) != null){
					Hashtable<Integer,String> tmp=new Hashtable<Integer,String>();
					StringTokenizer tokenizer = new StringTokenizer(line, ",");
					//System.out.println(c);
					int cc=0;
					while(tokenizer.hasMoreTokens()&&c!=0) { 
						String result = tokenizer.nextToken();
						String part = result.replaceAll("[\"]","");
						//System.out.println(part);
						tmp.put(cc, part);
						cc++;
					}

					if(cc!=0){
						userName = tmp.get(0);
						password = tmp.get(1);
						firstName = tmp.get(2);
						lastName = tmp.get(3);
						groupId = tmp.get(4);
						int groupIdInt = Integer.parseInt(groupId);
						userType = tmp.get(5);
						int userTypeInt = Integer.parseInt(userType);

						/*System.out.println(userName);
						System.out.println(password);
						System.out.println(firstName);
						System.out.println(lastName);
						System.out.println(groupIdInt);
						System.out.println(userTypeInt);*/						

						PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
						ps.setString(1, userName);
						ps.setInt(2, 1);
						ps.setString(3, password);
						ps.setString(4, firstName);
						ps.setString(5, lastName);
						ps.setInt(6, groupIdInt);
						ps.setInt(7, userTypeInt);
						ps.executeUpdate();
						ps.close();
					}
					c++;
				}
			}
			catch(Exception e){}
		}
		request.getRequestDispatcher("/WEB-INF/jsp/processCSV.jsp").forward(request, response);
	}
}
