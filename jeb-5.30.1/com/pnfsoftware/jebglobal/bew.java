package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.LinkedHashMap;
import java.util.Map;

public class bew {
   public bex q;
   public int RF;
   public boolean xK;
   public Map Dw = new LinkedHashMap();

   public bew(bex var1, int var2) {
      this.q = var1;
      this.RF = var2;
   }

   public String q() {
      return this.q.nf(this.RF);
   }

   public void q(String var1) {
      this.Dw.remove(var1);
   }

   public void q(String var1, Object var2) {
      Assert.a(var1 != null && var1.length() > 0);
      this.Dw.put(var1, var2);
   }

   public void q(String var1, Object var2, String... var3) {
      Assert.a(var1 != null && var1.length() > 0);
      this.Dw.put(var1, var2);
   }

   public boolean RF(String var1) {
      return this.Dw.get(var1) != null;
   }

   public Object xK(String var1) {
      return this.Dw.get(var1);
   }

   public Object q(String var1, Class var2) {
      return this.Dw.get(var1);
   }

   public int RF() {
      return ((Number)this.q("length", Number.class)).intValue();
   }

   public bew Dw(String var1) {
      Object var2 = this.Dw.get(var1);
      if (!(var2 instanceof Number)) {
         return null;
      } else {
         int var3 = ((Number)var2).intValue();
         return this.q.RF(var3);
      }
   }

   public String Uv(String var1) {
      bew var2 = this.Dw(var1);
      if (var2 == null) {
         return null;
      } else {
         Object var3 = var2.xK("data");
         if (var3 != null && !(var3 instanceof String)) {
            throw new RuntimeException("Expected a String!");
         } else {
            return (String)var3;
         }
      }
   }

   @Override
   public String toString() {
      return this.q(true);
   }

   public String q(boolean var1) {
      StringBuilder var2 = new StringBuilder();
      if (!var1) {
         Strings.ff(var2, "ClassId:%d", this.RF);
         if (this.xK) {
            Strings.ff(var2, " (BASE)");
         }

         for (String var4 : this.Dw.keySet()) {
            Object var5 = this.Dw.get(var4);
            var2.append('\n');
            Strings.ff(var2, "  %s:%s", var4, Formatter.toString(var5));
         }
      } else {
         Strings.ff(var2, "ClassId: %d(%s)", this.RF, this.q.nf(this.RF));
         if (this.xK) {
            Strings.ff(var2, " (BASE)");
         }

         for (String var14 : this.Dw.keySet()) {
            Object var15 = this.Dw.get(var14);
            String var6 = null;
            if (var15 instanceof Long) {
               long var7 = (Long)var15;
               if (var7 >= 0L && var7 < this.q.mI.size()) {
                  bew var9 = this.q.xK((int)var7);
                  if (var9 != null) {
                     String var10 = this.q.nf(var9.RF);
                     if (var10.contains("StringCid")) {
                        String var11 = (String)var9.q("data", String.class);
                        var6 = Strings.ff("'%s'", Formatter.escapeString(var11));
                     } else if (var10.equals("kClassCid")) {
                        bew var16 = var9.Dw("name");
                        if (var16 != null) {
                           String var12 = (String)var16.q("data", String.class);
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
