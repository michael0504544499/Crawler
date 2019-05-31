package CrawlerComponents;

import javax.swing.JButton;

import Interfaces.Command;

@SuppressWarnings("serial")
public class BingButton extends JButton implements Command{

	@Override
	public void exec() {
	
		if (!this.isSelected()) {
			this.setContentAreaFilled(false);
			this.setSelected(true);
		} else {
			this.setSelected(false);
			this.setContentAreaFilled(true);

		}
		
	}

}
