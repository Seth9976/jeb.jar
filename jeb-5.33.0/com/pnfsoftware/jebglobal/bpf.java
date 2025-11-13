package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.util.collect.CollectionOrder;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class bpf {
   private static final ILogger wS = GlobalLog.getLogger(bpf.class);
   private IDMethodContext UT;
   private MultiMap E;
   private Map sY;
   private Map ys;
   private Map ld;
   Map pC;
   bpd A;
   Set kS;

   public bpf(IDMethodContext var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.UT = var1;
      }
   }

   public bpg pC() {
      MultiMap var1 = new MultiMap(CollectionOrder.NATURAL);

      for (int var3 : this.E.keySet()) {
         List var4 = this.E.get(var3);
         if (var4 != null && !var4.isEmpty()) {
            for (bpa var6 : var4) {
               var6.A++;
               var6.kS++;
               if (var6.wS != -1) {
                  var6.wS++;
               }
            }

            var1.putMulti(var3 + 1, var4);
         }
      }

      TreeMap var7 = new TreeMap();

      for (int var10 : this.ys.keySet()) {
         bor var12 = (bor)this.ys.get(var10);
         var12.A++;
         if (var12.kS != -1) {
            var12.kS++;
         }

         var7.put(var10 + 1, var12);
      }

      TreeMap var9 = new TreeMap();

      for (int var13 : this.ld.keySet()) {
         bor var14 = (bor)this.ld.get(var13);
         var14.A++;
         if (var14.kS != -1) {
            var14.kS++;
         }

         var9.put(var13 + 1, var14);
      }

      this.E = null;
      this.sY = null;
      this.ys = null;
      this.ld = null;
      return new bpg(var1, var7, var9);
   }

   public boolean A() {
      if (this.pC != null) {
         throw new IllegalStateException();
      } else {
         CFG var1 = this.UT.getCfg();
         this.pC = pC(var1);
         this.A = new bpd(var1.size());
         this.kS = new HashSet();
         boz var2 = new boz(this.UT, true, false);
         var2.pC(this.pC, this.A, this.kS);

         bpa var3;
         while ((var3 = var2.UT()) != null) {
            Object[] var10000 = new Object[]{var3};
         }

         this.E = var2.pC;
         this.ys = new TreeMap();
         this.ld = new TreeMap();
         boq var4 = new boq(this.UT, false);
         var4.pC(this.pC, this.A, this.kS, this.E);

         bor var5;
         while ((var5 = var4.kS()) != null) {
            Object[] var6 = new Object[]{var5};
            if (var5.A() == bos.A) {
               this.ld.put(var5.A, var5);
            } else {
               this.ys.put(var5.A, var5);
            }
         }

         this.sY = var4.pC;
         return true;
      }
   }

   static Map pC(CFG var0) {
      HashMap var1 = new HashMap();
      int var2 = 0;

      for (BasicBlock var4 : var0) {
         long var5 = var4.getBase();
         var1.put(var5, var2);
         var2++;
      }

      return var1;
   }
}
