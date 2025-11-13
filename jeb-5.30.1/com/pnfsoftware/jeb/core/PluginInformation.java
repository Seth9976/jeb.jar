package com.pnfsoftware.jeb.core;

public class PluginInformation implements IPluginInformation {
   protected String name;
   protected String description;
   protected String author;
   protected Version version;
   protected Version minCoreVersion;
   protected Version maxCoreVersion;

   public PluginInformation(String var1, String var2, String var3, Version var4, Version var5, Version var6) {
      this.name = var1;
      this.description = var2;
      this.author = var3;
      this.version = var4;
      this.minCoreVersion = var5;
      this.maxCoreVersion = var6;
   }

   public PluginInformation(String var1, String var2, String var3, Version var4, Version var5) {
      this.name = var1;
      this.description = var2;
      this.author = var3;
      this.version = var4;
      this.minCoreVersion = var5;
   }

   public PluginInformation(String var1, String var2, String var3, Version var4) {
      this(var1, var2, var3, var4, null, null);
   }

   protected PluginInformation() {
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public String getDescription() {
      return this.description;
   }

   @Override
   public String getAuthor() {
      return this.author;
   }

   @Override
   public Version getVersion() {
      return this.version;
   }

   @Override
   public Version getMinimumCoreVersion() {
      return this.minCoreVersion;
   }

   @Override
   public Version getMaximumCoreVersion() {
      return this.maxCoreVersion;
   }

   @Override
   public String toString() {
      return this.name == null ? "<anonymous-plugin>" : this.name;
   }
}
