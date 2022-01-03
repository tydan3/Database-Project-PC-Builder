import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
	// String constants for Change Form options
	private static final String SQL = "SQL";
	private static final String CREATE_USER = "Create User";
	private static final String DELETE_USER = "Delete User";
	private static final String CREATE_PC_BUILD = "Create PC Build";
	private static final String ADD_BUILD_VIDEO_CARD = "Add GPU to Build";
	private static final String ADD_BUILD_MEMORY = "Add Memory to Build";
	private static final String ADD_BUILD_STORAGE = "Add Storage to Build";
	private static final String ADD_BUILD_MONITOR = "Add Monitor to Build";
	private static final String DELETE_PC_BUILD = "Delete PC Build";
	// String constants for View Report options
	private static final String VIEW_COMPONENTS = "Components";
	private static final String VIEW_COMPONENTS_RANGE = "Components (Price Range)";
	private static final String VIEW_PC_BUILD = "PC Build(s)";
	private static final String VIEW_PC_BUILD_RANGE = "PC Build(s) (Price Range)";
	private static final String VIEW_COMPAT_MOBO = "Compatible Motherboards";
	private static final String VIEW_COMPAT_CPU = "Compatible CPUs";
	


	
	/**
	 * default serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	private SQLDatabaseConnection myConnection;

	
    /**
     * constructor.
     */
    public GUI(SQLDatabaseConnection theConnection) {
        super("PC Builder");
        this.myConnection = theConnection;
    }
    
    
    /**
     * set-up.
     */
    public void start() {
        setSize(870, 600);

        
        // View Report label
        final JLabel reportLabel = new JLabel("View Report");
        reportLabel.setBounds(20, 10, 100, 30);
        reportLabel.setText("View Report:");
        reportLabel.setForeground(Color.BLUE);
        add(reportLabel);
        
        // Change Form label
        final JLabel formLabel = new JLabel("Change Form");
        formLabel.setBounds(440, 10, 100, 30);
        formLabel.setText("Change Form:");
        formLabel.setForeground(Color.BLUE);
        add(formLabel);
        
        // Output label
        final JLabel outputLabel = new JLabel("Output");
        outputLabel.setBounds(20, 280, 100, 30);
        outputLabel.setText("Output:");
        outputLabel.setForeground(Color.BLUE);
        add(outputLabel);
        
        
        // Report text area
        final JTextArea reportArea = new JTextArea("");
        JScrollPane reportScroll = new JScrollPane(reportArea);
        reportScroll.setBounds(20, 40, 400, 200);
        add(reportScroll);
        
        // Form text area
        final JTextArea formArea = new JTextArea("");
        JScrollPane formScroll = new JScrollPane(formArea);
        formScroll.setBounds(440, 40, 400, 200);
        add(formScroll);


        // Output Text Area
        final JTextArea outputArea = new JTextArea("");
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBounds(20, 310, 820, 220);
        add(outputScroll);
        
        // View Report options combo box
        String[] reportOptionStrings = {SQL, VIEW_COMPONENTS,
        		VIEW_COMPONENTS_RANGE, VIEW_PC_BUILD, VIEW_PC_BUILD_RANGE,
        		VIEW_COMPAT_MOBO, VIEW_COMPAT_CPU};
        final JComboBox<String> reportOptions = 
        		new JComboBox<String>(reportOptionStrings);
        // title in box when nothing selected
        reportOptions.setRenderer(new MyComboBoxRenderer(
        		"View Report Options"));
        reportOptions.setSelectedIndex(-1); //By default, select nothing
        reportOptions.setBounds(20, 240, 160, 20);
        reportOptions.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		switch ((String)reportOptions.getSelectedItem()) {
	        		case SQL:
	        			reportArea.setText("");
	        			break;
	        		case VIEW_COMPONENTS:
	        			reportArea.setText("ComponentCategory");
	        			break;
	        		case VIEW_COMPONENTS_RANGE:
	        			reportArea.setText("ComponentCategory,\nMinPrice,"
	        					+ "\nMaxPrice");
	        			break;
	        		case VIEW_PC_BUILD:
	        			reportArea.setText("'Username'");
	        			break;
	        		case VIEW_PC_BUILD_RANGE:
	        			reportArea.setText("MinPrice,\nMaxPrice");
	        			break;
	        		case VIEW_COMPAT_MOBO:
	        			reportArea.setText("'CPUName'");
	        			break;
	        		case VIEW_COMPAT_CPU:
	        			reportArea.setText("'MoboName'");
	        			break;
        		}
        	}
        });
        add(reportOptions);
        
        // Change Form options combo box
        String[] formOptionStrings = {SQL, CREATE_USER, DELETE_USER,
        		CREATE_PC_BUILD, ADD_BUILD_VIDEO_CARD, ADD_BUILD_MEMORY, 
        		ADD_BUILD_STORAGE, ADD_BUILD_MONITOR, DELETE_PC_BUILD};
        final JComboBox<String> formOptions = 
        		new JComboBox<String>(formOptionStrings);
        // title in box when nothing selected
        formOptions.setRenderer(new MyComboBoxRenderer("Change Form Options"));
        formOptions.setSelectedIndex(-1); //By default, select nothing
        formOptions.setBounds(440, 240, 160, 20);
        formOptions.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		// set form templates based on selected option
        		switch ((String)formOptions.getSelectedItem()) {
	        		case SQL:
	        			formArea.setText("");
	        			break;
	        		case CREATE_USER:
	        			formArea.setText("'Username',\n'Email',\n'Fname',\n"
	        					+ "'LastName'");
	        			break;
	        		case DELETE_USER:
	        			formArea.setText("'Username'");
	        			break;
	        		case CREATE_PC_BUILD:
	        			formArea.setText("'Username',\n'BuildName',\n'CPUName',"
	        					+ "\n'CoolerName',\n'PSUName',\n'MoboName',"
	        					+ "\n'CaseName',\n'OSName',\n'MouseName',"
	        					+ "\n'KeyboardName'");
	        			break;
	        			//TODO
	        		case ADD_BUILD_VIDEO_CARD:
	        			formArea.setText("'Username',\n'BuildName',\nGPU#,"
	        					+ "\n'GPUName'");
	        			break;
	        		case ADD_BUILD_MEMORY:
	        			formArea.setText("'Username',\n'BuildName',\nMemory#,"
	        					+ "\n'MemoryName'");
	        			break;
	        		case ADD_BUILD_STORAGE:
	        			formArea.setText("'Username',\n'BuildName',\nStorage#,"
	        					+ "\n'StorageName'");
	        			break;
	        		case ADD_BUILD_MONITOR:
	        			formArea.setText("'Username',\n'BuildName',\nMonitor#,"
	        					+ "\n'MonitorName'");
	        			break;
	        		case DELETE_PC_BUILD:
	        			formArea.setText("'Username',\n'BuildName'");
	        			break;
	        	}
        	}
        });
        add(formOptions);
        
        
        // Execute View Report button
        final JButton reportButton = new JButton("Request View Report");
        reportButton.setBounds(259, 240, 160, 20);
        reportButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
            	String reportStatement = "";
        		switch ((String)reportOptions.getSelectedItem()) {
	        		case SQL:
	        			reportStatement = reportArea.getText();
	        			break;
	        		case VIEW_COMPONENTS:
	        			reportStatement = viewComponents(reportArea.getText());
	        			break;
	        		case VIEW_COMPONENTS_RANGE:
	        			reportStatement = viewComponentsRange(reportArea.getText());
	        			break;
	        		case VIEW_PC_BUILD:
	        			reportStatement = viewBuild(reportArea.getText());
	        			break;
	        		case VIEW_PC_BUILD_RANGE:
	        			reportStatement = viewBuildRange(reportArea.getText());
	        			break;
	        		case VIEW_COMPAT_MOBO:
	        			reportStatement = viewCompatMobo(reportArea.getText());
	        			break;
	        		case VIEW_COMPAT_CPU:
	        			reportStatement = viewCompatCPU(reportArea.getText());
	        			break;
        		}
        		String outputResult = 
        				myConnection.executeReport(reportStatement);
        		outputArea.setText(outputResult);
        	}
        });
        add(reportButton);
        
        // Execute Change Form button
        final JButton formButton = new JButton("Request Change Form");
        formButton.setBounds(679, 240, 160, 20);
        formButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
            	String formStatement = "";
        		switch ((String)formOptions.getSelectedItem()) {
	        		case SQL:
	        			formStatement = formArea.getText();
	        			break;
	        		case CREATE_USER:
	        			formStatement = createUser(formArea.getText());
	        			break;
	        		case DELETE_USER:
	        			formStatement = deleteUser(formArea.getText());
	        			break;
	        		case CREATE_PC_BUILD:
	        			formStatement = createBuild(formArea.getText());
	        			break;
	        		case ADD_BUILD_VIDEO_CARD:
	        			formStatement = addVideoCard(formArea.getText());
	        			break;
	        		case ADD_BUILD_MEMORY:
	        			formStatement = addMemory(formArea.getText());
	        			break;
	        		case ADD_BUILD_STORAGE:
	        			formStatement = addStorage(formArea.getText());
	        			break;
	        		case ADD_BUILD_MONITOR:
	        			formStatement = addMonitor(formArea.getText());
	        			break;
	        		case DELETE_PC_BUILD:
	        			formStatement = deleteBuild(formArea.getText());
	        			break;
        		}
        		String outputResult = myConnection.executeForm(formStatement);
        		outputArea.setText(outputResult);        	
        	}
        });
        add(formButton);
        
        // Clear button
        final JButton clearButton = new JButton("Clear");
        clearButton.setBounds(759, 530, 80, 20);
        clearButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		// clear output
        		outputArea.setText("");
        	}
        });
        add(clearButton);
                
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    // Change Form helper methods
    
    /**
     * Creates SQL statement String for creating a user.
     * @param theUserInfo 'Username','Email','Fname','LastName'
     * @return SQL statement String
     */
    private static String createUser(String theUserInfo) {
    	return "INSERT INTO DBUser VALUES(" + theUserInfo + ");";
    }
    
    /**
     * Creates SQL statement String for deleting a user.
     * @param theUsername 'Username'
     * @return SQL statement String
     */
    private static String deleteUser(String theUsername) {
    	return "DELETE FROM DBUser WHERE Username = " + theUsername + ";";
    }
    
    /**
     * Creates SQL statement String for creating a pc build.
     * @param theBuildInfo 'Username','BuildName','CPUName','CoolerName',
     * 'PSUName','MoboName','CaseName','OSName','MouseName','KeyboardName'
     * @return SQL statement String
     */
    private static String createBuild(String theBuildInfo) {
    	return "INSERT INTO PCBuild VALUES(" + theBuildInfo + ");";
    }
    
    //TODO
    /**
     * Creates SQL statement String for adding a gpu to a build.
     * @param theVideoCardInfo 'Username','BuildName', GPU#,'GPUName'
     * @return SQL statement String
     */
    private static String addVideoCard(String theVideoCardInfo) {
    	return "INSERT INTO BuildGPU VALUES(" + theVideoCardInfo + ");";
    }
    
    /**
     * Creates SQL statement String for adding memory to a build.
     * @param theMemoryInfo 'Username','BuildName',Memory#,'MemoryName'
     * @return SQL statement String
     */
    private static String addMemory(String theMemoryInfo) {
    	return "INSERT INTO BuildMemory VALUES(" + theMemoryInfo + ");";
    }
    
    /**
     * Creates SQL statement String for adding storage to a build.
     * @param theStorageInfo 'Username','BuildName',Storage#,'StorageName'
     * @return SQL statement String
     */
    private static String addStorage(String theStorageInfo) {
    	return "INSERT INTO BuildStorage VALUES(" + theStorageInfo + ");";
    }
    
    /**
     * Creates SQL statement String for adding a monitor to a build.
     * @param theMonitorInfo 'Username','BuildName',Monitor#,'MonitorName'
     * @return SQL statement String
     */
    private static String addMonitor(String theMonitorInfo) {
    	return "INSERT INTO BuildMonitor VALUES(" + theMonitorInfo + ");";
    }
    
    /**
     * Creates SQL statement String for deleting a pc build.
     * @param theBuildInfo 'Username','BuildName'
     * @return SQL statement String
     */
    private static String deleteBuild(String theBuildInfo) {
    	String[] infoValues = theBuildInfo.split(",");
    	return "DELETE FROM PCBuild WHERE Username = " + infoValues[0] + 
    			" and BuildName = " + infoValues[1] + ";";
    }
    
    // View Report helper methods
    
    /**
     * Creates SQL statement String for viewing components of a given category.
     * @param theCategory ComponentCategory
     * @return SQL statement String
     */
    private static String viewComponents(String theCategory) {
    	return "SELECT *\n"
    			+ "FROM "+ theCategory +";";
    }
    
    /**
     * Creates SQL statement String for viewing components of a given category and price range.
     * @param theCategory ComponentCategory
     * @return SQL statement String
     */
    private static String viewComponentsRange(String theCompInfo) {
    	String[] infoValues = theCompInfo.split(",");
    	return "SELECT *\n"
    			+ "FROM " + infoValues[0] + "\n"
    			+ "WHERE "+ infoValues[0] + ".Price >= " + infoValues[1]
    			+ " and " + infoValues[0] +".Price <= "+ infoValues[2] + ";";
    }
    
    /**
     * Creates SQL statement String for viewing a user's pc builds.
     * @param theBuildInfo Username
     * @return SQL statement String
     */
    private static String viewBuild(String theBuildInfo) {
    	return "SELECT DISTINCT FullBuild.Username,\n"
    			+ "	   FullBuild.BuildName,\n"
    			+ "	   FullBuild.CPUName,\n"
    			+ "	   FullBuild.CoolerName,\n"
    			+ "	   FullBuild.PSUName,\n"
    			+ "	   FullBuild.MoboName,\n"
    			+ "	   FullBuild.CaseName,\n"
    			+ "	   FullBuild.OSName,\n"
    			+ "	   FullBuild.MouseName,\n"
    			+ "	   FullBuild.KeyboardName,\n"
    			+ "	   BuildGPUs.BuildGpus,\n"
    			+ "	   BuildMem.BuildMemory,\n"
    			+ "	   BuildSt.BuildStorage,\n"
    			+ "	   BuildMon.BuildMonitors,\n"
    			+ "	   (CPU.Price + Cool.Price + PSU.Price + Mobo.Price + PCase.Price + OS.Price\n"
    			+ "	   + M.Price + Kb.Price + GPUBuildPrice.GPUsTotalPrice \n"
    			+ "	   + MemBuildPrice.MemoryTotalPrice + StBuildPrice.StorageTotalPrice \n"
    			+ "	   + MonBuildPrice.MonitorsTotalPrice) AS TotalPrice\n"
    			+ "FROM (\n"
    			+ "	SELECT PC.Username,\n"
    			+ "		   PC.BuildName,\n"
    			+ "		   CPUName,\n"
    			+ "		   CoolerName,\n"
    			+ "		   PSUName,\n"
    			+ "		   MoboName,\n"
    			+ "		   CaseName,\n"
    			+ "		   OSName,\n"
    			+ "		   MouseName,\n"
    			+ "		   KeyboardName,\n"
    			+ "		   GPU.GPUName,\n"
    			+ "		   Mem.MemoryName,\n"
    			+ "		   St.StorageName,\n"
    			+ "		   Mon.MonitorName\n"
    			+ "	FROM PCBuild AS PC\n"
    			+ "	JOIN BuildGpu AS GPU\n"
    			+ "	ON PC.Username = GPU.Username and PC.BuildName = GPU.BuildName\n"
    			+ "	JOIN BuildMemory AS Mem\n"
    			+ "	ON PC.Username = Mem.Username and PC.BuildName = Mem.BuildName\n"
    			+ "	JOIN BuildStorage AS St\n"
    			+ "	ON PC.Username = St.Username and PC.BuildName = St.BuildName\n"
    			+ "	JOIN BuildMonitor AS Mon\n"
    			+ "	ON PC.Username = Mon.Username and PC.BuildName = Mon.BuildName\n"
    			+ "	WHERE PC.Username = " + theBuildInfo + ") AS FullBuild\n"
    			+ "JOIN CPU\n"
    			+ "ON FullBuild.CPUName = CPU.CPUName\n"
    			+ "JOIN CPUCooler AS Cool\n"
    			+ "ON FullBuild.CoolerName = Cool.CoolerName\n"
    			+ "JOIN PowerSupply AS PSU\n"
    			+ "ON FullBuild.PSUName = PSU.PSUName\n"
    			+ "JOIN Motherboard AS Mobo\n"
    			+ "ON FullBuild.MoboName = Mobo.MoboName\n"
    			+ "JOIN PCCase AS PCase\n"
    			+ "ON FullBuild.CaseName = PCase.CaseName\n"
    			+ "JOIN OS\n"
    			+ "ON FullBuild.OSName = OS.OSName\n"
    			+ "JOIN Mouse AS M\n"
    			+ "ON FullBuild.MouseName = M.MouseName\n"
    			+ "JOIN Keyboard AS Kb\n"
    			+ "ON FullBuild.KeyboardName = Kb.KeyboardName\n"
    			+ "\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, STRING_AGG(GPUName, ', ') AS BuildGpus\n"
    			+ "	FROM BuildGpu\n"
    			+ "	GROUP BY UserName, BuildName) AS BuildGPUs\n"
    			+ "ON FullBuild.Username = BuildGPUs.Username and FullBuild.BuildName = BuildGPUs.BuildName\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, SUM(Price) AS GPUsTotalPrice\n"
    			+ "	FROM BuildGpu\n"
    			+ "	JOIN VideoCard\n"
    			+ "	ON BuildGpu.GPUName = VideoCard.GPUName\n"
    			+ "	GROUP BY UserName, BuildName) AS GPUBuildPrice\n"
    			+ "ON FullBuild.Username = GPUBuildPrice.Username and FullBuild.BuildName = GPUBuildPrice.BuildName\n"
    			+ "\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, STRING_AGG(MemoryName, ', ') AS BuildMemory\n"
    			+ "	FROM BuildMemory\n"
    			+ "	GROUP BY UserName, BuildName) AS BuildMem\n"
    			+ "ON FullBuild.Username = BuildMem.Username and FullBuild.BuildName = BuildMem.BuildName\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, SUM(Price) AS MemoryTotalPrice\n"
    			+ "	FROM BuildMemory\n"
    			+ "	JOIN Memory\n"
    			+ "	ON BuildMemory.MemoryName = Memory.MemoryName\n"
    			+ "	GROUP BY UserName, BuildName) AS MemBuildPrice\n"
    			+ "ON FullBuild.Username = MemBuildPrice.Username and FullBuild.BuildName = MemBuildPrice.BuildName\n"
    			+ "\n"
    			+ "\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, STRING_AGG(StorageName, ', ') AS BuildStorage\n"
    			+ "	FROM BuildStorage\n"
    			+ "	GROUP BY UserName, BuildName) AS BuildSt\n"
    			+ "ON FullBuild.Username = BuildSt.Username and FullBuild.BuildName = BuildSt.BuildName\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, SUM(Price) AS StorageTotalPrice\n"
    			+ "	FROM BuildStorage\n"
    			+ "	JOIN Storage\n"
    			+ "	ON BuildStorage.StorageName = Storage.StorageName\n"
    			+ "	GROUP BY UserName, BuildName) AS StBuildPrice\n"
    			+ "ON FullBuild.Username = StBuildPrice.Username and FullBuild.BuildName = StBuildPrice.BuildName\n"
    			+ "\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, STRING_AGG(MonitorName, ', ') AS BuildMonitors\n"
    			+ "	FROM BuildMonitor\n"
    			+ "	GROUP BY UserName, BuildName) AS BuildMon\n"
    			+ "ON FullBuild.Username = BuildMon.Username and FullBuild.BuildName = BuildMon.BuildName\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, SUM(Price) AS MonitorsTotalPrice\n"
    			+ "	FROM BuildMonitor\n"
    			+ "	JOIN Monitor\n"
    			+ "	ON BuildMonitor.MonitorName = Monitor.MonitorName\n"
    			+ "	GROUP BY UserName, BuildName) AS MonBuildPrice\n"
    			+ "ON FullBuild.Username = MonBuildPrice.Username and FullBuild.BuildName = MonBuildPrice.BuildName";
    }
    
    /**
     * Creates SQL statement String for viewing a pc build in a price range.
     * @param theRangeInfo min, max
     * @return SQL statement String
     */
    private static String viewBuildRange(String theRangeInfo) {
    	String[] infoValues = theRangeInfo.split(",");
    	return "SELECT DISTINCT FullBuild.Username,\n"
    			+ "	   FullBuild.BuildName,\n"
    			+ "	   FullBuild.CPUName,\n"
    			+ "	   FullBuild.CoolerName,\n"
    			+ "	   FullBuild.PSUName,\n"
    			+ "	   FullBuild.MoboName,\n"
    			+ "	   FullBuild.CaseName,\n"
    			+ "	   FullBuild.OSName,\n"
    			+ "	   FullBuild.MouseName,\n"
    			+ "	   FullBuild.KeyboardName,\n"
    			+ "	   BuildGPUs.BuildGpus,\n"
    			+ "	   BuildMem.BuildMemory,\n"
    			+ "	   BuildSt.BuildStorage,\n"
    			+ "	   BuildMon.BuildMonitors,\n"
    			+ "	   (CPU.Price + Cool.Price + PSU.Price + Mobo.Price + PCase.Price + OS.Price\n"
    			+ "	   + M.Price + Kb.Price + GPUBuildPrice.GPUsTotalPrice \n"
    			+ "	   + MemBuildPrice.MemoryTotalPrice + StBuildPrice.StorageTotalPrice \n"
    			+ "	   + MonBuildPrice.MonitorsTotalPrice) AS TotalPrice\n"
    			+ "FROM (\n"
    			+ "	SELECT PC.Username,\n"
    			+ "		   PC.BuildName,\n"
    			+ "		   CPUName,\n"
    			+ "		   CoolerName,\n"
    			+ "		   PSUName,\n"
    			+ "		   MoboName,\n"
    			+ "		   CaseName,\n"
    			+ "		   OSName,\n"
    			+ "		   MouseName,\n"
    			+ "		   KeyboardName,\n"
    			+ "		   GPU.GPUName,\n"
    			+ "		   Mem.MemoryName,\n"
    			+ "		   St.StorageName,\n"
    			+ "		   Mon.MonitorName\n"
    			+ "	FROM PCBuild AS PC\n"
    			+ "	JOIN BuildGpu AS GPU\n"
    			+ "	ON PC.Username = GPU.Username and PC.BuildName = GPU.BuildName\n"
    			+ "	JOIN BuildMemory AS Mem\n"
    			+ "	ON PC.Username = Mem.Username and PC.BuildName = Mem.BuildName\n"
    			+ "	JOIN BuildStorage AS St\n"
    			+ "	ON PC.Username = St.Username and PC.BuildName = St.BuildName\n"
    			+ "	JOIN BuildMonitor AS Mon\n"
    			+ "	ON PC.Username = Mon.Username and PC.BuildName = Mon.BuildName) AS FullBuild\n"
    			+ "JOIN CPU\n"
    			+ "ON FullBuild.CPUName = CPU.CPUName\n"
    			+ "JOIN CPUCooler AS Cool\n"
    			+ "ON FullBuild.CoolerName = Cool.CoolerName\n"
    			+ "JOIN PowerSupply AS PSU\n"
    			+ "ON FullBuild.PSUName = PSU.PSUName\n"
    			+ "JOIN Motherboard AS Mobo\n"
    			+ "ON FullBuild.MoboName = Mobo.MoboName\n"
    			+ "JOIN PCCase AS PCase\n"
    			+ "ON FullBuild.CaseName = PCase.CaseName\n"
    			+ "JOIN OS\n"
    			+ "ON FullBuild.OSName = OS.OSName\n"
    			+ "JOIN Mouse AS M\n"
    			+ "ON FullBuild.MouseName = M.MouseName\n"
    			+ "JOIN Keyboard AS Kb\n"
    			+ "ON FullBuild.KeyboardName = Kb.KeyboardName\n"
    			+ "\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, STRING_AGG(GPUName, ', ') AS BuildGpus\n"
    			+ "	FROM BuildGpu\n"
    			+ "	GROUP BY UserName, BuildName) AS BuildGPUs\n"
    			+ "ON FullBuild.Username = BuildGPUs.Username and FullBuild.BuildName = BuildGPUs.BuildName\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, SUM(Price) AS GPUsTotalPrice\n"
    			+ "	FROM BuildGpu\n"
    			+ "	JOIN VideoCard\n"
    			+ "	ON BuildGpu.GPUName = VideoCard.GPUName\n"
    			+ "	GROUP BY UserName, BuildName) AS GPUBuildPrice\n"
    			+ "ON FullBuild.Username = GPUBuildPrice.Username and FullBuild.BuildName = GPUBuildPrice.BuildName\n"
    			+ "\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, STRING_AGG(MemoryName, ', ') AS BuildMemory\n"
    			+ "	FROM BuildMemory\n"
    			+ "	GROUP BY UserName, BuildName) AS BuildMem\n"
    			+ "ON FullBuild.Username = BuildMem.Username and FullBuild.BuildName = BuildMem.BuildName\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, SUM(Price) AS MemoryTotalPrice\n"
    			+ "	FROM BuildMemory\n"
    			+ "	JOIN Memory\n"
    			+ "	ON BuildMemory.MemoryName = Memory.MemoryName\n"
    			+ "	GROUP BY UserName, BuildName) AS MemBuildPrice\n"
    			+ "ON FullBuild.Username = MemBuildPrice.Username and FullBuild.BuildName = MemBuildPrice.BuildName\n"
    			+ "\n"
    			+ "\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, STRING_AGG(StorageName, ', ') AS BuildStorage\n"
    			+ "	FROM BuildStorage\n"
    			+ "	GROUP BY UserName, BuildName) AS BuildSt\n"
    			+ "ON FullBuild.Username = BuildSt.Username and FullBuild.BuildName = BuildSt.BuildName\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, SUM(Price) AS StorageTotalPrice\n"
    			+ "	FROM BuildStorage\n"
    			+ "	JOIN Storage\n"
    			+ "	ON BuildStorage.StorageName = Storage.StorageName\n"
    			+ "	GROUP BY UserName, BuildName) AS StBuildPrice\n"
    			+ "ON FullBuild.Username = StBuildPrice.Username and FullBuild.BuildName = StBuildPrice.BuildName\n"
    			+ "\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, STRING_AGG(MonitorName, ', ') AS BuildMonitors\n"
    			+ "	FROM BuildMonitor\n"
    			+ "	GROUP BY UserName, BuildName) AS BuildMon\n"
    			+ "ON FullBuild.Username = BuildMon.Username and FullBuild.BuildName = BuildMon.BuildName\n"
    			+ "JOIN (\n"
    			+ "	SELECT UserName, BuildName, SUM(Price) AS MonitorsTotalPrice\n"
    			+ "	FROM BuildMonitor\n"
    			+ "	JOIN Monitor\n"
    			+ "	ON BuildMonitor.MonitorName = Monitor.MonitorName\n"
    			+ "	GROUP BY UserName, BuildName) AS MonBuildPrice\n"
    			+ "ON FullBuild.Username = MonBuildPrice.Username and FullBuild.BuildName = MonBuildPrice.BuildName\n"
    			+ "\n"
    			+ "WHERE (CPU.Price + Cool.Price + PSU.Price + Mobo.Price + PCase.Price + OS.Price\n"
    			+ "	   + M.Price + Kb.Price + GPUBuildPrice.GPUsTotalPrice \n"
    			+ "	   + MemBuildPrice.MemoryTotalPrice + StBuildPrice.StorageTotalPrice \n"
    			+ "	   + MonBuildPrice.MonitorsTotalPrice) >= " + infoValues[0] + "\n"
    			+ "	   and (CPU.Price + Cool.Price + PSU.Price + Mobo.Price + PCase.Price + OS.Price\n"
    			+ "	   + M.Price + Kb.Price + GPUBuildPrice.GPUsTotalPrice \n"
    			+ "	   + MemBuildPrice.MemoryTotalPrice + StBuildPrice.StorageTotalPrice \n"
    			+ "	   + MonBuildPrice.MonitorsTotalPrice) <= "+ infoValues[1] + ";";
    }
    
    /**
     * Creates SQL statement String for viewing compatible motherboards, given a CPU.
     * @param theCPUInfo 'CPUName'
     * @return SQL statement String
     */
    private static String viewCompatMobo(String theCPUInfo) {
    	return "SELECT Mobo.*\n"
    			+ "FROM CPU\n"
    			+ "JOIN Motherboard AS Mobo\n"
    			+ "ON CPU.Socket = Mobo.Socket\n"
    			+ "WHERE CPU.CPUName = " + theCPUInfo + ""
    			+ "ORDER BY Mobo.Price ASC;";
    }
    
    /**
     * Creates SQL statement String for viewing compatible CPUs, given a motherboard.
     * @param theMoboInfo 'MoboName'
     * @return SQL statement String
     */
    private static String viewCompatCPU(String theMoboInfo) {
    	return "SELECT CPU.*\n"
    			+ "FROM Motherboard AS Mobo\n"
    			+ "JOIN CPU\n"
    			+ "ON Mobo.Socket = CPU.Socket\n"
    			+ "WHERE Mobo.MoboName = " + theMoboInfo + ""
    			+ "ORDER BY CPU.Price ASC;";
    }
    
    
    
    
    
    /**
     * Used to set title of combo box when nothing is selected.
     * @author tydan
     *
     */
    class MyComboBoxRenderer extends JLabel implements ListCellRenderer<Object> {
    	// default serial version id
		private static final long serialVersionUID = 1L;
		private String _title;

        public MyComboBoxRenderer(String title) {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, 
        		Object value, int index, boolean isSelected,
        		boolean hasFocus) {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
}
