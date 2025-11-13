package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Map;

public class bqu {
   private static final Map pC = new IdentityHashMap();

   static {
      pC.put(bps.class, 100);
      pC.put(bqg.class, 90);
      pC.put(bpo.class, 60);
      pC.put(bpu.class, 50);
      pC.put(bqd.class, 40);
      pC.put(bpy.class, 30);
      pC.put(bpn.class, 26);
      pC.put(bpz.class, 25);
      pC.put(bpp.class, 20);
      pC.put(bqa.class, 10);
      pC.put(bqc.class, 5);
      pC.put(bpv.class, 0);
   }

   public static class Av implements Comparator {
      public int pC(IDExpression var1, IDExpression var2) {
         if (var1 != null && var2 != null) {
            int var3 = (Integer)bqu.pC.getOrDefault(var1.getClass(), 0);
            int var4 = (Integer)bqu.pC.getOrDefault(var2.getClass(), 0);
            if (var3 != var4 || var3 == 0) {
               return var3 - var4;
            } else if (var1 instanceof IDImm) {
               Assert.a(var2 instanceof IDImm);
               return Long.compare(((IDImm)var1).getRawValue(), ((IDImm)var2).getRawValue());
            } else if (var1 instanceof IDVar) {
               Assert.a(var2 instanceof IDVar);
               return Long.compare(((IDVar)var1).getId(), ((IDVar)var2).getId());
            } else if (var1 instanceof IDOperation) {
               Assert.a(var2 instanceof IDOperation);
               JavaOperatorType var5 = ((IDOperation)var1).getOperatorType();
               JavaOperatorType var6 = ((IDOperation)var2).getOperatorType();
               int var7 = var5.ordinal() - var6.ordinal();
               if (var7 != 0) {
                  return var7;
               } else {
                  IDExpression var8 = ((IDOperation)var1).getLeft();
                  IDExpression var9 = ((IDOperation)var2).getLeft();
                  var7 = this.pC(var8, var9);
                  if (var7 != 0) {
                     return var7;
                  } else {
                     var8 = ((IDOperation)var1).getRight();
                     var9 = ((IDOperation)var2).getRight();
                     if (var8 == null) {
                        return var9 == null ? 0 : -1;
                     } else if (var9 == null) {
                        return 1;
                     } else {
                        var7 = this.pC(var8, var9);
                        return var7 != 0 ? var7 : 0;
                     }
                  }
               }
            } else {
               return 0;
            }
         } else {
            return 0;
         }
      }
   }
}
