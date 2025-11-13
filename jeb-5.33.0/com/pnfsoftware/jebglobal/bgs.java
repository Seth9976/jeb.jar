package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSMethod;
import com.pnfsoftware.jeb.core.units.code.android.IJLSType;
import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import com.pnfsoftware.jeb.util.collect.CacheMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class bgs implements IJLSTypeAdapter {
   private baj pC;
   private boolean A;
   private CacheMap kS;

   public bgs(baj var1) {
      this(var1, true);
   }

   public bgs(baj var1, boolean var2) {
      this.pC = var1;
      this.A = var2;
   }

   private bai pC(String var1) {
      try {
         if (this.kS != null) {
            bai var2 = (bai)this.kS.get(var1);
            if (var2 != null) {
               return var2;
            }
         }

         bai var4 = this.pC.A(bak.kS(var1));
         if (this.kS != null) {
            this.kS.put(var1, var4);
         }

         return var4;
      } catch (IOException var3) {
         return null;
      }
   }

   @Override
   public IJLSType getType(String var1) {
      bai var2 = this.pC(var1);
      return var2 == null ? null : new bgp(bak.wS(var2.ld()), var2.kS());
   }

   @Override
   public String getSupertype(String var1) {
      bai var2 = this.pC(var1);
      if (var2 == null) {
         return null;
      } else {
         String var3 = var2.gp();
         return this.A ? var3 : bak.wS(var3);
      }
   }

   @Override
   public List getInterfaces(String var1) {
      bai var2 = this.pC(var1);
      if (var2 == null) {
         return null;
      } else {
         List var3 = var2.oT();
         return this.A ? var3 : (List)var3.stream().map(var0 -> bak.wS(var0)).collect(Collectors.toList());
      }
   }

   @Override
   public List getParentTypes(String var1) {
      bai var2 = this.pC(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();
         if (this.A) {
            var3.add(var2.gp());
            var3.addAll(var2.oT());
         } else {
            var3.add(bak.wS(var2.gp()));
            var2.oT().forEach(var1x -> var3.add(bak.wS(var1x)));
         }

         return var3;
      }
   }

   @Override
   public List getMethods(String var1) {
      bai var2 = this.pC(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();

         for (ban var5 : var2.ys()) {
            bgo var6 = new bgo(var5.pC(var2), var5.A(var2), var5.pC());
            var3.add(var6);
         }

         return var3;
      }
   }

   @Override
   public IJLSMethod findMethod(String var1, String var2, String var3) {
      bai var4 = this.pC(var1);
      if (var4 == null) {
         return null;
      } else {
         for (ban var6 : var4.ys()) {
            if (var6.pC(var4).equals(var2) && var6.A(var4).equals(var3)) {
               return new bgo(var6.pC(var4), var6.A(var4), var6.pC());
            }
         }

         return null;
      }
   }

   @Override
   public List getFields(String var1) {
      bai var2 = this.pC(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();

         for (bal var5 : var2.sY()) {
            bgn var6 = new bgn(var5.pC(var2), var5.A(var2), var5.pC());
            var3.add(var6);
         }

         return var3;
      }
   }

   @Override
   public List getTypeAnnotations(String var1) {
      return null;
   }
}
