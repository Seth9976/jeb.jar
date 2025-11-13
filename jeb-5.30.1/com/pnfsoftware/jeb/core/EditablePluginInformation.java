package com.pnfsoftware.jeb.core;

public class EditablePluginInformation extends PluginInformation {
   public EditablePluginInformation() {
   }

   public EditablePluginInformation(String var1) {
      this.name = var1;
   }

   public void setName(String var1) {
      this.name = var1;
   }

   public void setDescription(String var1) {
      this.description = var1;
   }

   public void setAuthor(String var1) {
      this.author = var1;
   }

   public void setVersion(Version var1) {
      this.version = var1;
   }
}
