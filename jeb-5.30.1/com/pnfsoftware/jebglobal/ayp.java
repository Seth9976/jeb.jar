package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledRoutine;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.List;
import java.util.ListIterator;

public class ayp implements IUnmangledRoutine {
   private final String Uv;
   private final String oW;
   private final List gO;
   private final List nf;
   protected String q;
   protected String RF;
   protected String xK;
   protected String Dw;
   private String gP;

   public ayp(String var1) {
      this(var1, null, null, null, null);
   }

   public ayp(String var1, String var2, List var3, List var4, String var5) {
      this.Uv = var1;
      this.oW = var2;
      this.gO = var3;
      this.nf = var4;
      this.gP = var5;
   }

   @Override
   public String getName() {
      return this.Uv;
   }

   @Override
   public String getNameWithParameters(boolean var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append(this.Uv);
      if (this.gO != null && (var1 || !this.gO.isEmpty())) {
         this.q(var2);
      }

      return var2.toString();
   }

   @Override
   public String getSignature(boolean var1) {
      return this.getSignature(var1, true);
   }

   @Override
   public String getSignature(boolean var1, boolean var2) {
      if (this.xK != null) {
         return this.xK;
      } else if (this.RF != null && this.gO != null) {
         if (var1 && this.RF.equals("__thiscall")) {
            return null;
         } else {
            StringBuilder var3 = new StringBuilder();
            var3.append(this.RF);
            var3.append(" ");
            if (var1) {
               if (this.oW == null) {
                  return null;
               }

               var3.append(this.oW);
            } else {
               var3.append(this.oW != null ? this.oW : "void");
            }

            var3.append(" ");
            var3.append(this.Uv);
            var3.append("(");
            boolean var4 = this.gO.size() == 1;
            ListIterator var5 = this.gO.listIterator();

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
            this.xK = var3.toString();
            return this.xK;
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
      if (this.Dw != null) {
         return this.Dw;
      } else if (this.RF != null && this.gO != null) {
         if (var1 && this.RF.equals("__thiscall")) {
            return null;
         } else {
            StringBuilder var3 = new StringBuilder();
            var3.append("<");
            var3.append(this.RF);
            var3.append("> ");
            if (var1) {
               if (this.oW == null) {
                  return null;
               }

               var3.append(this.oW);
            } else {
               var3.append(this.oW != null ? this.oW : "void");
            }

            var3.append("(");
            boolean var4 = this.gO.size() == 1;
            ListIterator var5 = this.gO.listIterator();

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
            this.Dw = var3.toString();
            return this.Dw;
         }
      } else {
         return null;
      }
   }

   @Override
   public String getFull() {
      if (this.gP != null) {
         return this.gP;
      } else if (this.Uv == null) {
         return null;
      } else {
         StringBuilder var1 = new StringBuilder();
         if (this.q != null) {
            var1.append(this.q);
            var1.append(" ");
         }

         if (this.oW != null) {
            var1.append(this.oW);
            var1.append(" ");
         }

         if (this.RF != null) {
            var1.append(this.RF);
            var1.append(" ");
         }

         var1.append(this.Uv);
         this.q(var1);
         this.gP = var1.toString();
         return this.gP;
      }
   }

   private void q(StringBuilder var1) {
      if (this.gO != null) {
         var1.append("(");
         ListIterator var2 = this.gO.listIterator();

         while (var2.hasNext()) {
            var1.append((String)var2.next());
            if (var2.hasNext()) {
               var1.append(", ");
            }
         }

         var1.append(")");
         if (this.nf != null) {
            var1.append(" ");
            var1.append(Strings.join(" ", this.nf));
         }
      }
   }

   @Override
   public String getSimple() {
      return this.getName();
   }

   @Override
   public String getReturnType() {
      return this.oW;
   }

   @Override
   public List getParameterTypes() {
      return this.gO;
   }

   @Override
   public List getAttributes() {
      return this.nf;
   }

   @Override
   public String getCallingConvention() {
      return this.RF;
   }

   @Override
   public boolean isRawData() {
      return false;
   }
}
