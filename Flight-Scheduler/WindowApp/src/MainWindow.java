import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class MainWindow {

	protected Shell shell;
	private Text username_textfield;
	private Text password_textfield;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Flight Scheduler");
		
		username_textfield = new Text(shell, SWT.BORDER);
		username_textfield.setBounds(222, 102, 64, 19);
		
		password_textfield = new Text(shell, SWT.BORDER);
		password_textfield.setBounds(222, 139, 64, 19);
		
		Label lblUsername = new Label(shell, SWT.NONE);
		lblUsername.setBounds(140, 107, 59, 14);
		lblUsername.setText("Username");
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setBounds(140, 144, 59, 14);
		lblPassword.setText("Password");
		
		Button btnLogin = new Button(shell, SWT.NONE);
		btnLogin.setBounds(174, 195, 96, 27);
		btnLogin.setText("Login");

	}
}
