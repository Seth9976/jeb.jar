package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.LinkedHashMap;
import java.util.Map;

public class bbc {
   public bbd pC;
   public int A;
   public boolean kS;
   public Map wS = new LinkedHashMap();

   public bbc(bbd var1, int var2) {
      this.pC = var1;
      this.A = var2;
   }

   public String pC() {
      return this.pC.sY(this.A);
   }

   public void pC(String var1) {
      this.wS.remove(var1);
   }

   public void pC(String var1, Object var2) {
      Assert.a(var1 != null && var1.length() > 0);
      this.wS.put(var1, var2);
   }

   public void pC(String var1, Object var2, String... var3) {
      Assert.a(var1 != null && var1.length() > 0);
      this.wS.put(var1, var2);
   }

   public boolean A(String var1) {
      return this.wS.get(var1) != null;
   }

   public Object kS(String var1) {
      return this.wS.get(var1);
   }

   public Object pC(String var1, Class var2) {
      return this.wS.get(var1);
   }

   public int A() {
      return ((Number)this.pC("length", Number.class)).intValue();
   }

   public bbc wS(String var1) {
      Object var2 = this.wS.get(var1);
      if (!(var2 instanceof Number)) {
         return null;
      } else {
         int var3 = ((Number)var2).intValue();
         return this.pC.A(var3);
      }
   }

   public String UT(String var1) {
      bbc var2 = this.wS(var1);
      if (var2 == null) {
         return null;
      } else {
         Object var3 = var2.kS("data");
         if (var3 != null && !(var3 instanceof String)) {
            throw new RuntimeException("Expected a String!");
         } else {
            return (String)var3;
         }
      }
   }

   @Override
   public String toString() {
      return this.pC(true);
   }

   public String pC(boolean var1) {
      StringBuilder var2 = new StringBuilder();
      if (!var1) {
         Strings.ff(var2, "ClassId:%d", this.A);
         if (this.kS) {
            Strings.ff(var2, " (BASE)");
         }

         for (String var4 : this.wS.keySet()) {
            Object var5 = this.wS.get(var4);
            var2.append('\n');
            Strings.ff(var2, "  %s:%s", var4, Formatter.toString(var5));
         }
      } else {
         Strings.ff(var2, "ClassId: %d(%s)", this.A, this.pC.sY(this.A));
         if (this.kS) {
            Strings.ff(var2, " (BASE)");
         }

         for (String var14 : this.wS.keySet()) {
            Object var15 = this.wS.get(var14);
            String var6 = null;
            if (var15 instanceof Long) {
               long var7 = (Long)var15;
               if (var7 >= 0L && var7 < this.pC.LM.size()) {
                  bbc var9 = this.pC.kS((int)var7);
                  if (var9 != null) {
                     String var10 = this.pC.sY(var9.A);
                     if (var10.contains("StringCid")) {
                        String var11 = (String)var9.pC("data", String.class);
                        var6 = Strings.ff("'%s'", Formatter.escapeString(var11));
                     } else if (var10.equals("kClassCid")) {
                        bbc var16 = var9.wS("name");
                        if (var16 != null) {
                           String var12 = (String)var16.pC("data", String.class);
                           var6 = Strings.ff("class:%s", var12);
                        }
                     } else {
                        var6 = var10 + "?";
                     }
                  }
               }
            }

            var2.append('\n');
            Strings.ff(var2, "  %s:%s", var14, Formatter.toString(var15));
            if (var6 != null) {
               Strings.ff(var2, " => %s", var6);
            }
         }
      }

      return var2.toString();
   }
}
