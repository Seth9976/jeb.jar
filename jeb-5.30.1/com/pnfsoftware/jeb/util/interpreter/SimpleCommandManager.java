package com.pnfsoftware.jeb.util.interpreter;

import com.pnfsoftware.jeb.util.format.Strings;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SimpleCommandManager implements ICommandManager {
   private ICommandManager parent;
   private String name;
   private Map children = new LinkedHashMap();
   private Map alts = new LinkedHashMap();
   private int options;
   private static final int NEW_TOKEN = 0;
   private static final int CURR_TOKEN = 1;
   private static final int QUOTES = 2;

   public SimpleCommandManager() {
      this(null, "");
   }

   public SimpleCommandManager(ICommandManager var1, String var2) {
      if (var1 == null && !var2.isEmpty()) {
         throw new IllegalArgumentException();
      } else {
         this.parent = var1;
         this.name = var2;
      }
   }

   @Override
   public ICommandManager getParent() {
      return this.parent;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public ICommandNode addChild(ICommandNode var1) {
      if (var1.getParent() == null) {
         throw new IllegalArgumentException();
      } else {
         String var2 = var1.getName();
         if (var2 != null && var2.length() != 0) {
            String[] var3 = var2.split("\\|");

            for (String var7 : var3) {
               if (Strings.hasBlank(var7)) {
                  throw new IllegalArgumentException("Invalid command name: " + var7);
               }

               if (Strings.isContainedIn(var7, "help", "use", "list", "exit")) {
                  throw new IllegalArgumentException(Strings.ff("'%s' is a reserved command name", var7));
               }

               if (this.children.containsKey(var7) || this.alts.containsKey(var7)) {
                  throw new IllegalArgumentException("Duplicate command name: " + var7);
               }
            }

            String var8 = var3[0];
            this.children.put(var8, var1);

            for (int var9 = 1; var9 < var3.length; var9++) {
               this.alts.put(var3[var9], var8);
            }

            return var1;
         } else {
            throw new IllegalArgumentException("Command name cannot be null or empty");
         }
      }
   }

   @Override
   public List getChildren() {
      return new ArrayList(this.children.values());
   }

   @Override
   public String getHelp() {
      StringBuilder var1 = new StringBuilder();

      for (String var3 : this.children.keySet()) {
         ICommandNode var4 = (ICommandNode)this.children.get(var3);
         this.appendHelp(var1, var4);
      }

      return var1.toString();
   }

   private void appendParam(StringBuilder var1, CommandParameter var2) {
      if (var2.isOptional()) {
         var1.append('[');
      }

      if (var2.hasPrefix()) {
         var1.append('-').append(var2.getPrefix());
         if (!var2.getName().isEmpty()) {
            var1.append(" ");
         }
      }

      var1.append(var2.getName());
      if (var2.isOptional()) {
         var1.append(']');
      }
   }

   private void appendHelp(StringBuilder var1, ICommandNode var2) {
      StringBuilder var3 = new StringBuilder(getFullName(var2));
      if (var2 instanceof ICommandHandler) {
         for (CommandParameter var5 : ((ICommandHandler)var2).getParameters()) {
            var3.append(' ');
            this.appendParam(var3, var5);
         }
      }

      if (var3.length() > 30 && this.hasOptionalParameters((ICommandHandler)var2)) {
         var3 = new StringBuilder(getFullName(var2));
         var3.append(" [OPTION]...");
         if (var2 instanceof ICommandHandler) {
            for (CommandParameter var7 : ((ICommandHandler)var2).getParameters()) {
               if (!var7.isOptional()) {
                  var3.append(' ');
                  this.appendParam(var3, var7);
               }
            }
         }
      }

      Strings.ff(var1, "%-30s : %s\n", var3, var2.getHelp());
   }

   private boolean hasOptionalParameters(ICommandHandler var1) {
      for (CommandParameter var3 : var1.getParameters()) {
         if (var3.isOptional()) {
            return true;
         }
      }

      return false;
   }

   private String getHelp(String var1) {
      ICommandNode var2 = (ICommandNode)this.children.get(var1);
      if (var2 == null) {
         String var3 = (String)this.alts.get(var1);
         if (var3 == null) {
            return null;
         }

         var2 = (ICommandNode)this.children.get(var3);
      }

      if (var2 == null) {
         return null;
      } else {
         StringBuilder var6 = new StringBuilder();
         this.appendHelp(var6, var2);
         if (var2 instanceof ICommandHandler) {
            for (CommandParameter var5 : ((ICommandHandler)var2).getParameters()) {
               this.appendParam(var6, var5);
               if (!Strings.isBlank(var5.getHelp())) {
                  var6.append(": ").append(var5.getHelp());
               }

               var6.append('\n');
            }

            var6.append(((ICommandHandler)var2).getHelpDetails()).append('\n');
         }

         return var6.toString();
      }
   }

   private static String getFullName(ICommandNode var0) {
      String var1 = var0.getName();

      for (ICommandManager var2 = var0.getParent(); var2 != null && var2.getParent() != null; var2 = var2.getParent()) {
         var1 = var2.getName() + " " + var1;
      }

      return var1.trim();
   }

   public ExecutionResult execute(String var1) {
      List var2;
      try {
         var2 = this.parseTokenString(var1);
      } catch (ParseException var4) {
         return ExecutionResult.error(var4.toString());
      }

      return this.execute(var2);
   }

   @Override
   public ExecutionResult execute(List var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var2 = ((InputToken)var1.get(0)).getValue();
         if (var2.equals("help")) {
            if (var1.size() == 1) {
               return ExecutionResult.success(this.getHelp());
            } else {
               String var6 = ((InputToken)var1.get(1)).getValue();
               String var9 = this.getHelp(var6);
               return var9 == null ? ExecutionResult.error(Strings.ff("No section with name %s", var6)) : ExecutionResult.success(var9);
            }
         } else {
            ICommandNode var3 = (ICommandNode)this.children.get(var2);
            if (var3 == null) {
               String var4 = (String)this.alts.get(var2);
               if (var4 == null) {
                  return null;
               }

               var3 = (ICommandNode)this.children.get(var4);
            }

            ExecutionResult var7 = this.preCheck();
            if (var7 != null && var7.isError()) {
               return var7;
            } else {
               var7 = var3.execute(var1.subList(1, var1.size()));
               if (var7 != null && var7.isError()) {
                  return var7;
               } else {
                  ExecutionResult var5 = this.postCheck();
                  if (var5 != null && var5.isError()) {
                     return var5;
                  } else {
                     return var7 != null ? var7 : ExecutionResult.GENERIC_SUCCESS;
                  }
               }
            }
         }
      } else {
         return null;
      }
   }

   protected ExecutionResult preCheck() {
      return null;
   }

   protected ExecutionResult postCheck() {
      return null;
   }

   public List parseTokenString(String var1) throws ParseException {
      ArrayList var2 = new ArrayList();
      byte var3 = 0;
      StringBuilder var4 = null;
      boolean var5 = false;
      int var6 = this.options;

      for (int var7 = 0; var7 < var1.length(); var7++) {
         if (var2.size() == 1 && !var5) {
            var5 = true;
            ICommandNode var8 = (ICommandNode)this.children.get(((InputToken)var2.get(0)).getValue());
            if (var8 != null) {
               var6 = var8.getOptions();
            }
         }

         char var9 = var1.charAt(var7);
         if (var3 == 0) {
            var4 = new StringBuilder();
         }

         if (Character.isWhitespace(var9)) {
            if (var3 != 0) {
               if (var3 == 1) {
                  var2.add(new InputToken(var4.toString()));
                  var3 = 0;
               } else if (var3 == 2) {
                  var4.append(var9);
               }
            }
         } else if (var9 == '"' && (var6 & 1) == 0) {
            if (var3 == 0) {
               var3 = 2;
            } else {
               if (var3 == 1) {
                  throw new ParseException("Unexpected double-quote", var7);
               }

               if (var3 == 2) {
                  var2.add(new InputToken(var4.toString(), true));
                  var3 = 0;
               }
            }
         } else {
            if (var9 == '\\') {
               if (var7 + 1 >= var1.length()) {
                  throw new ParseException("Unexpected EOL after backslash", var7);
               }

               var4.append('\\');
               var9 = var1.charAt(var7 + 1);
               var7++;
            }

            if (var3 == 0) {
               var3 = 1;
            }

            var4.append(var9);
         }
      }

      if (var4 != null && var4.length() != 0) {
         if (var3 == 1) {
            var2.add(new InputToken(var4.toString()));
         } else if (var3 == 2) {
            throw new ParseException("Token is incomplete, missing closing double-quote", var1.length());
         }
      }

      return var2;
   }

   @Override
   public int getOptions() {
      return this.options;
   }
}
