package com.pnfsoftware.jeb.util.interpreter;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCommandHandler implements ICommandHandler {
   protected ICommandManager parent;
   protected String name;
   protected List params = new ArrayList();
   protected String help = "";
   protected String helpDetails = "";
   private int options = 0;

   public AbstractCommandHandler(ICommandManager var1, String var2) {
      this(var1, var2, "");
   }

   public AbstractCommandHandler(ICommandManager var1, String var2, String var3) {
      this.parent = var1;
      this.name = var2;
      this.help = var3;
      this.helpDetails = "";
   }

   public AbstractCommandHandler(ICommandManager var1, String var2, String[] var3, String var4, String var5) {
      this(var1, var2, var4);
      this.helpDetails = var5;

      for (String var9 : var3) {
         this.addParameter(new CommandParameter(var9, "", false));
      }
   }

   public AbstractCommandHandler(ICommandManager var1, String var2, CommandParameter[] var3, String var4, String var5) {
      this(var1, var2, var4);
      this.helpDetails = var5;

      for (CommandParameter var9 : var3) {
         this.params.add(var9);
      }
   }

   public InputToken[] parseInputToken(List var1) {
      return this.parseInputToken(var1, true);
   }

   public InputToken[] parseInputToken(List var1, boolean var2) {
      InputToken[] var3 = new InputToken[this.params.size()];
      ArrayList var4 = new ArrayList();
      boolean var5 = true;
      boolean var6 = ((CommandParameter)this.params.get(this.params.size() - 1)).allowMultipleTokens();
      int var7 = -1;
      int var8 = 0;

      while (var8 < var1.size()) {
         InputToken var9 = (InputToken)var1.get(var8);
         boolean var10 = !var9.needsUnespaping() && var9.getValue().startsWith("-");
         String var11 = var9.getValue();
         if (var10) {
            if (!var5) {
               throw new IllegalArgumentException(Strings.ff("Unexpected prefixed parameter %s", var9.getValue()));
            }

            var11 = var11.substring(1);
         } else {
            var5 = false;
         }

         int var12 = 0;

         while (true) {
            label114: {
               if (var12 < this.params.size()) {
                  CommandParameter var13 = (CommandParameter)this.params.get(var12);
                  if (var10) {
                     if (!var13.hasPrefix()) {
                        throw new IllegalArgumentException(Strings.ff("Unexpected parameter %s", var11));
                     }

                     if (!var11.contains(var13.getPrefix())) {
                        break label114;
                     }

                     if (var3[var12] != null) {
                        throw new IllegalArgumentException(Strings.ff("Duplicated parameter -%s", var13.getPrefix()));
                     }

                     if (Strings.isBlank(var13.getName())) {
                        var3[var12] = var9;
                        var11 = var11.replace(var13.getPrefix(), "");
                     } else {
                        if (!var11.endsWith(var13.getPrefix())) {
                           throw new IllegalArgumentException(Strings.ff("Expected token after %s ", var13.getPrefix()));
                        }

                        var11 = var11.substring(0, var11.length() - var13.getPrefix().length());
                        if (++var8 >= var1.size()) {
                           throw new IllegalArgumentException(Strings.ff("Expected token after -%s", var13.getPrefix()));
                        }

                        var3[var12] = (InputToken)var1.get(var8);
                     }

                     if (!var11.isEmpty()) {
                        break label114;
                     }
                  } else {
                     if (var7 == -1 && var13.hasPrefix()) {
                        break label114;
                     }

                     if (var7 == -1) {
                        var7 = var12;
                     }

                     if (var7 < this.params.size()) {
                        var3[var7] = var9;
                        var7++;
                     } else {
                        if (!var6) {
                           throw new IllegalArgumentException(Strings.ff("Extra parameter %s", var11));
                        }

                        var4.add(var9);
                     }
                  }
               }

               var8++;
               break;
            }

            var12++;
         }
      }

      if (var2) {
         for (int var14 = 0; var14 < this.params.size(); var14++) {
            CommandParameter var16 = (CommandParameter)this.params.get(var14);
            if (!var16.isOptional() && var3[var14] == null) {
               throw new IllegalArgumentException(Strings.ff("Missing parameter %s", var16.getName()));
            }
         }
      }

      if (var6 && !var4.isEmpty()) {
         for (int var15 = 0; var15 < this.params.size(); var15++) {
            var4.add(var15, var3[var15]);
         }

         var3 = (InputToken[])var4.toArray(new InputToken[var4.size()]);
      }

      return var3;
   }

   @Override
   public ICommandManager getParent() {
      return this.parent;
   }

   @Override
   public List getChildren() {
      return Collections.emptyList();
   }

   @Override
   public String getName() {
      return this.name;
   }

   protected void setName(String var1) {
      this.name = var1;
   }

   @Override
   public String getHelp() {
      return this.help;
   }

   protected void setHelp(String var1) {
      this.help = var1;
   }

   @Override
   public String getHelpDetails() {
      return this.helpDetails;
   }

   @Override
   public List getParameters() {
      return this.params;
   }

   public int getParameterIndex(String var1, String var2) {
      for (int var3 = 0; var3 < this.params.size(); var3++) {
         CommandParameter var4 = (CommandParameter)this.params.get(var3);
         if (Strings.equals(var2, var4.getPrefix()) && Strings.equals(var1, var4.getName())) {
            return var3;
         }
      }

      return -1;
   }

   public AbstractCommandHandler addParameter(String var1) {
      return this.addParameter(new CommandParameter(var1, false));
   }

   public AbstractCommandHandler addParameter(CommandParameter var1) {
      if (var1.getName() != null && !var1.getName().isEmpty()) {
         for (CommandParameter var3 : this.params) {
            if (var1.getName().equalsIgnoreCase(var3.getName())) {
               throw new IllegalArgumentException("Duplicate parameter");
            }

            if (!var1.isOptional() && !var1.hasPrefix() && var3.isOptional()) {
               throw new IllegalArgumentException("Mandatory parameters must come first");
            }

            if (var1.hasPrefix() && !var3.hasPrefix()) {
               throw new IllegalArgumentException("Prefixed parameters must comme first");
            }
         }

         this.params.add(var1);
         return this;
      } else {
         throw new IllegalArgumentException("Parameter name is empty or null");
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%s", this.getName());
   }

   public static InputToken getToken(List var0, int var1) {
      return var0 != null && var1 >= 0 && var1 < var0.size() ? (InputToken)var0.get(var1) : null;
   }

   public static String getParameter(List var0, int var1) {
      InputToken var2 = getToken(var0, var1);
      return var2 == null ? null : var2.getValue();
   }

   public static String getParameterSafe(List var0, int var1) {
      return Strings.safe(getParameter(var0, var1));
   }

   @Override
   public int getOptions() {
      return this.options;
   }

   public AbstractCommandHandler setOptions(int var1) {
      this.options = var1;
      return this;
   }
}
