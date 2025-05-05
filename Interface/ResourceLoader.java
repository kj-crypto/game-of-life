package Interface;

import javax.swing.ImageIcon;

public final class ResourceLoader {
	
	
	
	public final static ImageIcon load(String path)
	{
		ImageIcon icon = new  ImageIcon(ClassLoader.getSystemResource(path));
		System.out.println("load "+ClassLoader.getSystemResource(path)+ " image "+icon);
		
		return icon;
	}

}
