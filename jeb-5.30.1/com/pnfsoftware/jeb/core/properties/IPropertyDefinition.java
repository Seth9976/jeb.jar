package com.pnfsoftware.jeb.core.properties;

public interface IPropertyDefinition {
   int FLAG_INTERNAL = 1;
   int FLAG_NOT_SUITABLE_FOR_RUN_TIME_OPTIONS = 2;
   int FLAG_URLENCODED = 4;
   int FLAG_VISUAL_STANDOUT = 8;
   int FLAG_SIZE_TINY = 16;
   int FLAG_SIZE_SMALL = 32;
   int FLAG_SIZE_MEDIUM = 0;
   int FLAG_SIZE_LARGE = 64;
   int FLAG_SIZE_EXTRA = 128;

   IPropertyDefinitionManager getManager();

   String getName();

   IPropertyType getType();

   String getDescription();

   int getFlags();

   default boolean isInternal() {
      return (this.getFlags() & 1) != 0;
   }
}
