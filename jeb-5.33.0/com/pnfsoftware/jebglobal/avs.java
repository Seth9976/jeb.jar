package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledRoutine;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.List;
import java.util.ListIterator;

public class avs implements IUnmangledRoutine {
   private final String UT;
   private final String E;
   private final List sY;
   private final List ys;
   protected String pC;
   protected String A;
   protected String kS;
   protected String wS;
   private String ld;

   public avs(String var1) {
      this(var1, null, null, null, null);
   }

   public avs(String var1, String var2, List var3, List var4, String var5) {
      this.UT = var1;
      this.E = var2;
      this.sY = var3;
      this.ys = var4;
      this.ld = var5;
   }

   @Override
   public String getName() {
      return this.UT;
   }

   @Override
   public String getNameWithParameters(boolean var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append(this.UT);
      if (this.sY != null && (var1 || !this.sY.isEmpty())) {
         this.pC(var2);
      }

      return var2.toString();
   }

   @Override
   public String getSignature(boolean var1) {
      return this.getSignature(var1, true);
   }

   @Override
   public String getSignature(boolean var1, boolean var2) {
      if (this.kS != null) {
         return this.kS;
      } else if (this.A != null && this.sY != null) {
         if (var1 && this.A.equals("__thiscall")) {
            return null;
         } else {
            StringBuilder var3 = new StringBuilder();
            var3.append(this.A);
            var3.append(" ");
            if (var1) {
               if (this.E == null) {
                  return null;
               }

               var3.append(this.E);
            } else {
               var3.append(this.E != null ? this.E : "void");
            }

            var3.append(" ");
            var3.append(this.UT);
            var3.append("(");
            boolean var4 = this.sY.size() == 1;
            ListIterator var5 = this.sY.listIterator();

            while (var5.hasNext()) {
               String var6 = (String)var5.next();
               if (var1) {
                  if (var6.startsWith("class ")) {
                     return null;
                  }

                  if (var6.contains(" &")) {
                     return null;
                  }

                  if (var6.contains("::")) {
                     return null;
                  }
               }

               var3.append(var6);
               if (var2 && var4) {
                  var3.append(" _");
               }

               if (var5.hasNext()) {
                  var3.append(",");
               }
            }

            var3.append(")");
            this.kS = var3.toString();
            return this.kS;
         }
      } else {
         return null;
      }
   }

   @Override
   public String getPrototype(boolean var1) {
      return this.getPrototype(var1, true);
   }

   @Override
   public String getPrototype(boolean var1, boolean var2) {
      if (this.wS != null) {
         return this.wS;
      } else if (this.A != null && this.sY != null) {
         if (var1 && this.A.equals("__thiscall")) {
            return null;
         } else {
            StringBuilder var3 = new StringBuilder();
            var3.append("<");
            var3.append(this.A);
            var3.append("> ");
            if (var1) {
               if (this.E == null) {
                  return null;
               }

               var3.append(this.E);
            } else {
               var3.append(this.E != null ? this.E : "void");
            }

            var3.append("(");
            boolean var4 = this.sY.size() == 1;
            ListIterator var5 = this.sY.listIterator();

            while (var5.hasNext()) {
               String var6 = (String)var5.next();
               if (var1) {
                  if (var6.startsWith("class ")) {
                     return null;
                  }

                  if (var6.contains(" &")) {
                     return null;
                  }

                  if (var6.contains("::")) {
                     return null;
                  }
               }

               var3.append(var6);
               if (var2 && var4) {
                  var3.append(" _");
               }

               if (var5.hasNext()) {
                  var3.append(",");
               }
            }

            var3.append(")");
            this.wS = var3.toString();
            return this.wS;
         }
      } else {
         return null;
      }
   }

   @Override
   public String getFull() {
      if (this.ld != null) {
         return this.ld;
      } else if (this.UT == null) {
         return null;
      } else {
         StringBuilder var1 = new StringBuilder();
         if (this.pC != null) {
            var1.append(this.pC);
            var1.append(" ");
         }

         if (this.E != null) {
            var1.append(this.E);
            var1.append(" ");
         }

         if (this.A != null) {
            var1.append(this.A);
            var1.append(" ");
         }

         var1.append(this.UT);
         this.pC(var1);
         this.ld = var1.toString();
         return this.ld;
      }
   }

   private void pC(StringBuilder var1) {
      if (this.sY != null) {
         var1.append("(");
         ListIterator var2 = this.sY.listIterator();

         while (var2.hasNext()) {
            var1.append((String)var2.next());
            if (var2.hasNext()) {
               var1.append(", ");
            }
         }

         var1.append(")");
         if (this.ys != null) {
            var1.append(" ");
            var1.append(Strings.join(" ", this.ys));
         }
      }
   }

   @Override
   public String getSimple() {
      return this.getName();
   }

   @Override
   public String getReturnType() {
      return this.E;
   }

   @Override
   public List getParameterTypes() {
      return this.sY;
   }

   @Override
   public List getAttributes() {
      return this.ys;
   }

   @Override
   public String getCallingConvention() {
      return this.A;
   }

   @Override
   public boolean isRawData() {
      return false;
   }
}
