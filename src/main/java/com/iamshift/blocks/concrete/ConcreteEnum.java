package com.iamshift.blocks.concrete;

import com.iamshift.blocks.lavasponge.LavaSpongeEnum.Type;

import net.minecraft.util.IStringSerializable;

public class ConcreteEnum 
{
	public ConcreteEnum() {}
	
	public static enum Type implements IStringSerializable
	{
		WHITE("white", 0), 
	    ORANGE("orange", 1), 
	    MAGENTA("magenta", 2), 
	    LIGHT_BLUE("light_blue", 3), 
	    YELLOW("yellow", 4), 
	    LIME("lime", 5), 
	    PINK("pink", 6), 
	    GRAY("gray", 7), 
	    LIGHT_GRAY("light_gray", 8), 
	    CYAN("cyan", 9), 
	    PURPLE("purple", 10), 
	    BLUE("blue", 11), 
	    BROWN("brown", 12), 
	    GREEN("green", 13), 
	    RED("red", 14), 
	    BLACK("black", 15);
	    
	    private int ID;
	    private String name;
	    private static final Type[] META_LOOKUP;
	    
	    private Type(String name, int ID)
	    {
	      this.ID = ID;
	      this.name = name;
	    }
	    
	    public String getName()
		{
			return name;
		}

		public int getID()
		{
			return ID;
		}

		public String toString()
		{
			return getName();
		}

		static
		{
			META_LOOKUP = new Type[values().length];

			for (Type type : values()) {
				META_LOOKUP[type.getID()] = type;
			}
		}
	}
}
