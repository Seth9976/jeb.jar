package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaClassFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Ser
public class blw implements IJavaClassFactory {
   @SerId(1)
   private IDexUnit q;
   @SerId(2)
   private IJavaTypeFactory RF;
   @SerId(3)
   private bmv xK;
   @SerId(4)
   private Map Dw;
   @SerId(5)
   private bma Uv;
   @SerId(6)
   private com.pnfsoftware.jeb.corei.parsers.dexdec.ej oW;

   @SerCustomInitPostGraph
   private void oW() {
      if (this.Dw instanceof HashMap) {
         this.Dw = new ConcurrentHashMap(this.Dw);
      }

      if (this.oW == null) {
         this.oW = (com.pnfsoftware.jeb.corei.parsers.dexdec.ej)DecompilerHelper.getDecompiler(this.q, false);
         if (this.oW != null) {
            this.Dw.values().forEach(var1 -> q(var1, this.oW));
         }
      }
   }

   static void q(bmj var0, com.pnfsoftware.jeb.corei.parsers.dexdec.ej var1) {
      var0.visitDepthPost(new blx(var1));
   }

   public blw(com.pnfsoftware.jeb.corei.parsers.dexdec.ej var1, IJavaTypeFactory var2, bmv var3) {
      this.oW = var1;
      this.q = var1.TX();
      this.RF = var2;
      this.xK = var3;
      this.Dw = new ConcurrentHashMap();
   }

   public void q(bma var1) {
      this.Uv = var1;
   }

   public bni q(String var1) {
      IDexClass var2 = this.q.getClass(var1);
      return var2 == null ? null : (bni)this.Dw.get(var2.getClassTypeIndex());
   }

   public bni RF(String var1) {
      IDexClass var2 = this.q.getClass(var1);
      return var2 == null ? null : this.RF(var2.getClassTypeIndex());
   }

   private bni RF(int var1) {
      if (var1 < 0) {
         throw new RuntimeException("Illegal type index: " + var1);
      } else {
         bni var2 = (bni)this.Dw.get(var1);
         if (var2 != null) {
            return var2;
         } else {
            synchronized (this) {
               var2 = (bni)this.Dw.get(var1);
               if (var2 != null) {
                  return var2;
               } else {
                  IDexType var4 = this.q.getType(var1);
                  if (var4 == null) {
                     throw new RuntimeException();
                  } else {
                     IJavaType var5 = this.RF.createType(var4.getSignature(false));
                     IDexClass var6 = var4.getImplementingClass();
                     if (var6 == null) {
                        var2 = new bni(var1, -1, this.RF, var5, true, null, null, 0);
                     } else {
                        String var7 = var6.getSupertypeSignature(false);
                        IJavaType var8 = this.RF.createType(var7);
                        ArrayList var9 = new ArrayList();

                        for (String var13 : var6.getInterfaceSignatures(false)) {
                           IJavaType var14 = this.RF.createType(var13);
                           var9.add(var14);
                        }

                        int var19 = q(var6.getGenericFlags());
                        if (var6.isStaticMemberClass()) {
                           var19 |= 1048584;
                        }

                        if (var6.isMemberClass()) {
                           var19 |= 1048576;
                        }

                        if (var6.isAnonymousClass()) {
                           var19 |= 3145728;
                        }

                        var2 = new bni(var1, var6.getIndex(), this.RF, var5, false, var8, var9, var19);

                        for (IDexField var23 : var6.getFields()) {
                           var2.Dw(var23.getSignature(false));
                        }

                        for (IDexMethod var24 : var6.getMethods()) {
                           var2.Uv(var24.getSignature(false));
                        }

                        for (IDexClass var25 : DexUtil.getMemberClasses(this.q, var6)) {
                           if (var25.isAnonymousClass()) {
                              var2.xK(var25.getSignature(false));
                           } else {
                              var2.RF(var25.getSignature(false));
                           }
                        }
                     }

                     var2.q(this.oW);
                     this.Dw.put(var1, var2);
                     return var2;
                  }
               }
            }
         }
      }
   }

   public static int q(int var0) {
      int var1 = 0;
      var1 |= q(var0, 1, 1);
      var1 |= q(var0, 2, 2);
      var1 |= q(var0, 4, 4);
      var1 |= q(var0, 8, 8);
      var1 |= q(var0, 16, 16);
      var1 |= q(var0, 32, 32);
      var1 |= q(var0, 64, 64);
      var1 |= q(var0, 64, 64);
      var1 |= q(var0, 128, 128);
      var1 |= q(var0, 128, 128);
      var1 |= q(var0, 256, 256);
      var1 |= q(var0, 512, 512);
      var1 |= q(var0, 1024, 1024);
      var1 |= q(var0, 2048, 2048);
      var1 |= q(var0, 4096, 4096);
      var1 |= q(var0, 8192, 8192);
      var1 |= q(var0, 16384, 16384);
      var1 |= q(var0, 65536, 65536);
      return var1 | q(var0, 131072, 131072);
   }

   private static int q(int var0, int var1, int var2) {
      return (var0 & var1) != 0 ? var2 : 0;
   }

   public void q() {
      this.Dw.clear();
   }

   public int RF() {
      return this.Dw.size();
   }

   public boolean xK(String var1) {
      bni var2 = this.q(var1);
      return var2 != null && this.q(var2);
   }

   public boolean q(bni var1) {
      int var2 = var1.Dw();
      bni var3 = (bni)this.Dw.remove(var2);
      return var3 != null;
   }

   public int xK() {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.Dw.entrySet()) {
         if ((((bni)var3.getValue()).getFlags() & 256) == 0) {
            var1.add((Integer)var3.getKey());
         }
      }

      var1.forEach(var1x -> this.Dw.remove(var1x));
      return var1.size();
   }

   public Collection Dw() {
      return Collections.unmodifiableCollection(this.Dw.values());
   }

   public int Uv() {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.Dw.entrySet()) {
         int var4 = (Integer)var3.getKey();
         bni var5 = (bni)var3.getValue();
         if (this.q.getType(var4).isInternal() && var5.isExternal()) {
            var1.add(var4);
         }
      }

      var1.forEach(var1x -> this.Dw.remove(var1x));
      return var1.size();
   }

   @Override
   public String toString() {
      return this.Dw.toString();
   }
}
