package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.DeferredRequestsCollector;
import com.pnfsoftware.jeb.core.units.code.java.IJMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDecompilableElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.JOptimizerType;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class bhs implements IJMasterOptimizer {
   private static final ILogger pC = GlobalLog.getLogger(bhs.class);
   private boolean A = true;
   private IJavaDecompilableElement kS;
   private Watchdog wS;
   private DeferredRequestsCollector UT;
   private boolean E;
   private List sY = new ArrayList();
   private List ys = new ArrayList();
   private List ld = new ArrayList();

   public bhs(IJavaDecompilableElement var1, DeferredRequestsCollector var2, Watchdog var3, boolean var4) {
      this.kS = var1;
      this.UT = var2;
      this.wS = var3;
      if (var4) {
         this.pC();
      }
   }

   @Override
   public IJavaDecompilableElement getTarget() {
      return this.kS;
   }

   @Override
   public void setSafeMode(boolean var1) {
      this.E = var1;
   }

   @Override
   public boolean isSafeMode() {
      return this.E;
   }

   @Override
   public boolean add(IJOptimizer var1) {
      return this.pC(var1, true);
   }

   @Override
   public boolean remove(IJOptimizer var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (this.sY.contains(var1)) {
            if (this.ys.remove(var1)) {
               this.sY.remove(var1);
               return true;
            }

            if (this.ld.remove(var1)) {
               this.sY.remove(var1);
               return true;
            }
         }

         return false;
      }
   }

   public boolean pC(IJOptimizer var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (this.sY.contains(var1)) {
         return false;
      } else {
         if (var2) {
            this.ld.add(var1);
         } else {
            this.ys.add(var1);
         }

         this.sY.add(var1);
         return true;
      }
   }

   private void pC() {
      this.sY.add(new bkt());
      this.sY.add(new bmq());
      this.sY.add(new bms());
      this.sY.add(new bkr());
      this.sY.add(new bks());
      this.sY.add(new bkz());
      this.sY.add(new blx());
      this.sY.add(new bli());
      this.sY.add(new blj());
      this.sY.add(new bll());
      this.sY.add(new bla());
      this.sY.add(new blm());
      this.sY.add(new bln());
      this.sY.add(new blo());
      this.sY.add(new blk());
      this.sY.add(new blb());
      this.sY.add(new bmc());
      this.sY.add(new bmv());
      this.sY.add(new bku());
      this.sY.add(new bkv());
      this.sY.add(new bme());
      this.sY.add(new bna());
      this.sY.add(new bmx());
      this.sY.add(new bkx());
      this.sY.add(new bmf());
      this.sY.add(new bmp());
      this.sY.add(new bkw());
      this.sY.add(new bmy());
      this.sY.add(new bnb());
      this.sY.add(new bnc());
      this.sY.add(new bmh());
      this.sY.add(new bmd());
      this.sY.add(new blq());
      this.sY.add(new bky());
      this.sY.add(new bmz());
      this.sY.add(new bls());
      this.sY.add(new blr());
      this.sY.add(new blp());
      this.sY.add(new bmt());
      this.sY.add(new blz());
      this.sY.add(new blc());
      this.sY.add(new ble());
      this.sY.add(new bmi());
      this.sY.add(new bmj());
      this.sY.add(new bmw());
      this.sY.add(new bml());
      this.sY.add(new bnd());
      this.sY.add(new bmn());
      this.sY.add(new bma());
      this.sY.add(new bmb());
      this.sY.add(new bkq());
      this.sY.add(new blt());
      this.sY.add(new blv());
      this.sY.add(new blw());
      this.sY.add(new bly());
      this.sY.add(new bkp());
      this.sY.add(new bko());
      this.sY.add(new bmk());
      this.sY.add(new bmg());
      this.sY.add(new bmu());
      this.sY.add(new bmr());
      this.sY.add(new bnh());
      this.sY.add(new bnf());
      this.sY.add(new bng());
      this.sY.add(new bni());
   }

   @Override
   public int perform() {
      for (IJOptimizer var2 : this.sY) {
         if (var2 instanceof AbstractJOptimizer) {
            ((AbstractJOptimizer)var2).drcollector = this.UT;
         }
      }

      this.sY.sort(new bht(this));
      boolean var12 = this.kS instanceof IJavaMethod;
      int var13 = this.A ? 200 : 100;
      int var3 = 0;
      int var4 = 0;

      while (var3 <= var13) {
         int var5 = 0;
         Iterator var6 = this.sY.iterator();

         while (true) {
            label71:
            if (var6.hasNext()) {
               IJOptimizer var7 = (IJOptimizer)var6.next();
               if (var7 instanceof AbstractJOptimizer && !((AbstractJOptimizer)var7).isEnabled()
                  || var7.getType() == JOptimizerType.CUSTOM
                  || this.E && var7.getType() == JOptimizerType.UNSAFE) {
                  continue;
               }

               if (var12) {
                  Watchdog.verify(this.wS);
               }

               int var8;
               try {
                  var8 = var7.perform(this.kS);
               } catch (Exception var11) {
                  pC.catchingSilent(var11);
                  boolean var10 = this.ld.contains(var7);
                  if (var12) {
                     com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(
                        new RuntimeException("AST optimizer failed", var11), this.kS.getSignature(), -1, Maps.toMap("external-dexdec-ast-optimizer", var10)
                     );
                     pC.error(S.L("%s: an optimizer failed, the decompiled code could not be entirely optimized"), this.kS.getSignature());
                  }

                  if (var10) {
                     pC.catchingSilent(var11);
                  }
                  break label71;
               }

               var5 += var8;
               if (!this.A || var8 <= 0) {
                  continue;
               }
            }

            var4 += var5;
            var3++;
            if (var5 <= 0) {
               return var4;
            }
            break;
         }
      }

      return var4;
   }
}
