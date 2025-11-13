package com.pnfsoftware.jeb.util.interpreter;

public class CommandParameter {
   private String prefix;
   private String pname;
   private String help = "";
   private boolean optional;
   private boolean allowMultipleTokens;
   private IAutocompleteListProvider autocompleteProvider;

   public CommandParameter(String var1) {
      this(var1, false);
   }

   public CommandParameter(String var1, boolean var2) {
      this.pname = var1;
      this.optional = var2;
   }

   public CommandParameter(String var1, String var2, boolean var3) {
      this.pname = var1;
      this.help = var2;
      this.optional = var3;
   }

   public CommandParameter(String var1, String var2, String var3, boolean var4) {
      this.prefix = var1;
      this.pname = var2;
      this.help = var3;
      this.optional = var4;
   }

   public CommandParameter(String var1, String var2, boolean var3, boolean var4) {
      this.pname = var1;
      this.help = var2;
      this.optional = var3;
      this.allowMultipleTokens = var4;
   }

   public CommandParameter(String var1, String var2, boolean var3, boolean var4, IAutocompleteListProvider var5) {
      this.pname = var1;
      this.help = var2;
      this.optional = var3;
      this.allowMultipleTokens = var4;
      this.autocompleteProvider = var5;
   }

   public boolean hasPrefix() {
      return this.prefix != null;
   }

   public String getPrefix() {
      return this.prefix;
   }

   public String getName() {
      return this.pname;
   }

   public String getHelp() {
      return this.help;
   }

   public boolean isOptional() {
      return this.optional;
   }

   public IAutocompleteListProvider getAutocompleteProvider() {
      return this.autocompleteProvider;
   }

   public boolean allowMultipleTokens() {
      return this.allowMultipleTokens;
   }
}
