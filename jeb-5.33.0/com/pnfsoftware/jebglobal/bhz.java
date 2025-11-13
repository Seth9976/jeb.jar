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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Ser
public class bhz implements IJavaClassFactory {
   @SerId(1)
   private IDexUnit pC;
   @SerId(2)
   private IJavaTypeFactory A;
   @SerId(3)
   private biy kS;
   @SerId(4)
   private Map wS;
   @SerId(5)
   private bid UT;
   @SerId(6)
   private com.pnfsoftware.jeb.corei.parsers.dexdec.Ws E;

   @SerCustomInitPostGraph
   private void kS() {
      if (this.wS instanceof HashMap) {
         this.wS = new ConcurrentHashMap(this.wS);
      }

      if (this.E == null) {
         this.E = (com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)DecompilerHelper.getDecompiler(this.pC, false);
         if (this.E != null) {
            this.wS.values().forEach(var1 -> pC(var1, this.E));
         }
      }
   }

   static void pC(bim var0, com.pnfsoftware.jeb.corei.parsers.dexdec.Ws var1) {
      var0.visitDepthPost(new bia(var1));
   }

   public bhz(com.pnfsoftware.jeb.corei.parsers.dexdec.Ws var1, IJavaTypeFactory var2, biy var3) {
      this.E = var1;
      this.pC = var1.ld();
      this.A = var2;
      this.kS = var3;
      this.wS = new ConcurrentHashMap();
   }

   public void pC(bid var1) {
      this.UT = var1;
   }

   public bjl pC(String var1) {
      IDexClass var2 = this.pC.getClass(var1);
      return var2 == null ? null : (bjl)this.wS.get(var2.getClassTypeIndex());
   }

   public bjl A(String var1) {
      IDexClass var2 = this.pC.getClass(var1);
      return var2 == null ? null : this.A(var2.getClassTypeIndex());
   }

   private bjl A(int var1) {
      if (var1 < 0) {
         throw new RuntimeException("Illegal type index: " + var1);
      } else {
         bjl var2 = (bjl)this.wS.get(var1);
         if (var2 != null) {
            return var2;
         } else {
            synchronized (this) {
               var2 = (bjl)this.wS.get(var1);
               if (var2 != null) {
                  return var2;
               } else {
                  IDexType var4 = this.pC.getType(var1);
                  if (var4 == null) {
                     throw new RuntimeException();
                  } else {
                     IJavaType var5 = this.A.createType(var4.getSignature(false));
                     IDexClass var6 = var4.getImplementingClass();
                     if (var6 == null) {
                        var2 = new bjl(var1, -1, this.A, var5, true, null, null, 0);
                     } else {
                        String var7 = var6.getSupertypeSignature(false);
                        IJavaType var8 = this.A.createType(var7);
                        ArrayList var9 = new ArrayList();

                        for (String var13 : var6.getInterfaceSignatures(false)) {
                           IJavaType var14 = this.A.createType(var13);
                           var9.add(var14);
                        }

                        int var19 = pC(var6.getGenericFlags());
                        if (var6.isStaticMemberClass()) {
                           var19 |= 1048584;
                        }

                        if (var6.isMemberClass()) {
                           var19 |= 1048576;
                        }

                        if (var6.isAnonymousClass()) {
                           var19 |= 3145728;
                        }

                        var2 = new bjl(var1, var6.getIndex(), this.A, var5, false, var8, var9, var19);

                        for (IDexField var23 : var6.getFields()) {
                           var2.wS(var23.getSignature(false));
                        }

                        for (IDexMethod var24 : var6.getMethods()) {
                           var2.UT(var24.getSignature(false));
                        }

                        for (IDexClass var25 : DexUtil.getMemberClasses(this.pC, var6)) {
                           if (var25.isAnonymousClass()) {
                              var2.kS(var25.getSignature(false));
                           } else {
                              var2.A(var25.getSignature(false));
                           }
                        }
                     }

                     var2.pC(this.E);
                     this.wS.put(var1, var2);
                     return var2;
                  }
               }
            }
         }
      }
   }

   public static int pC(int var0) {
      int var1 = 0;
      var1 |= pC(var0, 1, 1);
      var1 |= pC(var0, 2, 2);
      var1 |= pC(var0, 4, 4);
      var1 |= pC(var0, 8, 8);
      var1 |= pC(var0, 16, 16);
      var1 |= pC(var0, 32, 32);
      var1 |= pC(var0, 64, 64);
      var1 |= pC(var0, 64, 64);
      var1 |= pC(var0, 128, 128);
      var1 |= pC(var0, 128, 128);
      var1 |= pC(var0, 256, 256);
      var1 |= pC(var0, 512, 512);
      var1 |= pC(var0, 1024, 1024);
      var1 |= pC(var0, 2048, 2048);
      var1 |= pC(var0, 4096, 4096);
      var1 |= pC(var0, 8192, 8192);
      var1 |= pC(var0, 16384, 16384);
      var1 |= pC(var0, 65536, 65536);
      return var1 | pC(var0, 131072, 131072);
   }

   private static int pC(int var0, int var1, int var2) {
      return (var0 & var1) != 0 ? var2 : 0;
   }

   public boolean kS(String var1) {
      bjl var2 = this.pC(var1);
      return var2 != null && this.pC(var2);
   }

   public boolean pC(bjl var1) {
      int var2 = var1.kS();
      bjl var3 = (bjl)this.wS.remove(var2);
      return var3 != null;
   }

   public int pC() {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.wS.entrySet()) {
         if ((((bjl)var3.getValue()).getFlags() & 256) == 0) {
            var1.add((Integer)var3.getKey());
         }
      }

      var1.forEach(var1x -> this.wS.remove(var1x));
      return var1.size();
   }

   public int A() {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.wS.entrySet()) {
         int var4 = (Integer)var3.getKey();
         bjl var5 = (bjl)var3.getValue();
         if (this.pC.getType(var4).isInternal() && var5.isExternal()) {
            var1.add(var4);
         }
      }

      var1.forEach(var1x -> this.wS.remove(var1x));
      return var1.size();
   }

   @Override
   public String toString() {
      return this.wS.toString();
   }
}
