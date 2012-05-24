package game.level;

import java.io.File;
import java.util.ArrayList;

import game.Game;

public class LevelList {

	private static ArrayList<LevelInformation> levels;

	public static void createLevelList() {
		levels = new ArrayList<LevelInformation>();
		levels.add(new LevelInformation("Mojam", "/levels/level1.tmx",true));
		levels.add(new LevelInformation("AsymeTrical", "/levels/AsymeTrical.tmx",true));
		levels.add(new LevelInformation("BlackHole", "/levels/BlackHole.tmx",true));
		levels.add(new LevelInformation("CataBOMB", "/levels/CataBOMB.tmx",true));
		levels.add(new LevelInformation("RailRoads", "/levels/RailRoads.tmx",true));
		levels.add(new LevelInformation("Siege", "/levels/Siege.tmx",true));
		levels.add(new LevelInformation("TheMaze", "/levels/TheMaze.tmx",true));
		levels.add(new LevelInformation("Dev TMX", "/levels/DEV.tmx",true));
		
		File levels = getBaseDir();
		if(!levels.exists()) levels.mkdirs();
		System.out.println("Looking for levels: "+levels.getPath());
		loadDir(levels);
	}
	
	public static File getBaseDir(){
		return new File(Game.getTempDir(), "levels");
	}
	
	public static void loadDir(File file){
		File[] children = file.listFiles();
	    if (children != null) {
	        for (File child : children) {
	            if(child.isDirectory()){
	            	loadDir(child);
	            	continue;
	            }
	            String fileName = child.getName();
	            String fname="";
	            String ext="";
	            int mid= fileName.lastIndexOf(".");
	            fname=fileName.substring(0,mid);
	            ext=fileName.substring(mid+1);
	            if(ext.toLowerCase().equals("tmx")){
	            	System.out.println("  Found level: "+fname+" . "+ext);
	        		levels.add(new LevelInformation("+ "+fname, child.getPath(),false));
	            }
	        }
	    }
	}

	public static ArrayList<LevelInformation> getLevels() {
		if (levels == null) {
			createLevelList();
		}
		return levels;
	}
	
	public static void resetLevels(){
		levels = null;
	}
}
