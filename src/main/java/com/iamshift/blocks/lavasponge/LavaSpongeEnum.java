package com.iamshift.blocks.lavasponge;

import net.minecraft.util.IStringSerializable;

public class LavaSpongeEnum 
{
	public LavaSpongeEnum() {}

	public static enum Type implements IStringSerializable
	{
		DRY("dry", 0),
		WET("wet", 1);

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

