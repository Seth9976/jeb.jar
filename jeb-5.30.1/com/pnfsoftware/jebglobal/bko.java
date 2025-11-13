package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSMethod;
import com.pnfsoftware.jeb.core.units.code.android.IJLSType;
import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import com.pnfsoftware.jeb.util.collect.CacheMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class bko implements IJLSTypeAdapter {
   private bec q;
   private boolean RF;
   private CacheMap xK;

   public bko(bec var1) {
      this(var1, true);
   }

   public bko(bec var1, boolean var2) {
      this.q = var1;
      this.RF = var2;
   }

   public void q(boolean var1) {
      if (var1) {
         this.xK = new CacheMap(1000);
      } else {
         this.xK = null;
      }
   }

   public boolean q() {
      return this.xK != null;
   }

   public void RF() {
      if (this.xK != null) {
         this.xK.clear();
      }
   }

   private beb q(String var1) {
      try {
         if (this.xK != null) {
            beb var2 = (beb)this.xK.get(var1);
            if (var2 != null) {
               return var2;
            }
         }

         beb var4 = this.q.RF(bed.Dw(var1));
         if (this.xK != null) {
            this.xK.put(var1, var4);
         }

         return var4;
      } catch (IOException var3) {
         return null;
      }
   }

   @Override
   public IJLSType getType(String var1) {
      beb var2 = this.q(var1);
      return var2 == null ? null : new bkl(bed.Uv(var2.lm()), var2.xK());
   }

   @Override
   public String getSupertype(String var1) {
      beb var2 = this.q(var1);
      if (var2 == null) {
         return null;
      } else {
         String var3 = var2.zz();
         return this.RF ? var3 : bed.Uv(var3);
      }
   }

   @Override
   public List getInterfaces(String var1) {
      beb var2 = this.q(var1);
      if (var2 == null) {
         return null;
      } else {
         List var3 = var2.JY();
         return this.RF ? var3 : (List)var3.stream().map(var0 -> bed.Uv(var0)).collect(Collectors.toList());
      }
   }

   @Override
   public List getParentTypes(String var1) {
      beb var2 = this.q(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();
         if (this.RF) {
            var3.add(var2.zz());
            var3.addAll(var2.JY());
         } else {
            var3.add(bed.Uv(var2.zz()));
            var2.JY().forEach(var1x -> var3.add(bed.Uv(var1x)));
         }

         return var3;
      }
   }

   @Override
   public List getMethods(String var1) {
      beb var2 = this.q(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();

         for (beg var5 : var2.gP()) {
            bkk var6 = new bkk(var5.q(var2), var5.RF(var2), var5.q());
            var3.add(var6);
         }

         return var3;
      }
   }

   @Override
   public IJLSMethod findMethod(String var1, String var2, String var3) {
      beb var4 = this.q(var1);
      if (var4 == null) {
         return null;
      } else {
         for (beg var6 : var4.gP()) {
            if (var6.q(var4).equals(var2) && var6.RF(var4).equals(var3)) {
               return new bkk(var6.q(var4), var6.RF(var4), var6.q());
            }
         }

         return null;
      }
   }

   @Override
   public List getFields(String var1) {
      beb var2 = this.q(var1);
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = new ArrayList();

         for (bee var5 : var2.nf()) {
            bki var6 = new bki(var5.q(var2), var5.RF(var2), var5.q());
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
