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

public class bti {
   private static final ILogger Dw = GlobalLog.getLogger(bti.class);
   private IDMethodContext Uv;
   private MultiMap oW;
   private Map gO;
   private Map nf;
   private Map gP;
   Map q;
   btf RF;
   Set xK;

   public bti(IDMethodContext var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Uv = var1;
      }
   }

   public MultiMap q() {
      return this.oW;
   }

   public Map RF() {
      return this.gO;
   }

   public btj xK() {
      MultiMap var1 = new MultiMap(CollectionOrder.NATURAL);

      for (int var3 : this.oW.keySet()) {
         List var4 = this.oW.get(var3);
         if (var4 != null && !var4.isEmpty()) {
            for (btc var6 : var4) {
               var6.RF++;
               var6.xK++;
               if (var6.Dw != -1) {
                  var6.Dw++;
               }
            }

            var1.putMulti(var3 + 1, var4);
         }
      }

      TreeMap var7 = new TreeMap();

      for (int var10 : this.nf.keySet()) {
         bss var12 = (bss)this.nf.get(var10);
         var12.RF++;
         if (var12.xK != -1) {
            var12.xK++;
         }

         var7.put(var10 + 1, var12);
      }

      TreeMap var9 = new TreeMap();

      for (int var13 : this.gP.keySet()) {
         bss var14 = (bss)this.gP.get(var13);
         var14.RF++;
         if (var14.xK != -1) {
            var14.xK++;
         }

         var9.put(var13 + 1, var14);
      }

      this.oW = null;
      this.gO = null;
      this.nf = null;
      this.gP = null;
      return new btj(var1, var7, var9);
   }

   public boolean Dw() {
      if (this.q != null) {
         throw new IllegalStateException();
      } else {
         CFG var1 = this.Uv.getCfg();
         this.q = q(var1);
         this.RF = new btf(var1.size());
         this.xK = new HashSet();
         btb var2 = new btb(this.Uv, true, false);
         var2.q(this.q, this.RF, this.xK);

         btc var3;
         while ((var3 = var2.nf()) != null) {
            Object[] var10000 = new Object[]{var3};
         }

         this.oW = var2.q;
         this.nf = new TreeMap();
         this.gP = new TreeMap();
         bsr var4 = new bsr(this.Uv, false);
         var4.q(this.q, this.RF, this.xK, this.oW);

         bss var5;
         while ((var5 = var4.Uv()) != null) {
            Object[] var6 = new Object[]{var5};
            if (var5.Dw() == bst.RF) {
               this.gP.put(var5.RF, var5);
            } else {
               this.nf.put(var5.RF, var5);
            }
         }

         this.gO = var4.q;
         return true;
      }
   }

   static Map q(CFG var0) {
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
