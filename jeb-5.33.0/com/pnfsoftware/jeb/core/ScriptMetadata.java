package com.pnfsoftware.jeb.core;

public class ScriptMetadata {
   String type;
   String description;
   String author;
   Version version;
   String shortcut;
   boolean deprecated;
   boolean nolist;

   public String getType() {
      return this.type;
   }

   public String getDescription() {
      return this.description;
   }

   public String getAuthor() {
      return this.author;
   }

   public Version getVersion() {
      return this.version;
   }

   public String getShortcut() {
      return this.shortcut;
   }

   public boolean isDeprecated() {
      return this.deprecated;
   }

   public boolean isNolist() {
      return this.nolist;
   }
}
