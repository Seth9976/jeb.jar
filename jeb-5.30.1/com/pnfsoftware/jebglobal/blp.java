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

public class blp implements IJMasterOptimizer {
   private static final ILogger q = GlobalLog.getLogger(blp.class);
   private boolean RF = true;
   private IJavaDecompilableElement xK;
   private Watchdog Dw;
   private DeferredRequestsCollector Uv;
   private boolean oW;
   private List gO = new ArrayList();
   private List nf = new ArrayList();
   private List gP = new ArrayList();

   public blp(IJavaDecompilableElement var1, DeferredRequestsCollector var2, Watchdog var3, boolean var4) {
      this.xK = var1;
      this.Uv = var2;
      this.Dw = var3;
      if (var4) {
         this.q();
      }
   }

   @Override
   public IJavaDecompilableElement getTarget() {
      return this.xK;
   }

   @Override
   public void setSafeMode(boolean var1) {
      this.oW = var1;
   }

   @Override
   public boolean isSafeMode() {
      return this.oW;
   }

   @Override
   public boolean add(IJOptimizer var1) {
      return this.q(var1, true);
   }

   @Override
   public boolean remove(IJOptimizer var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (this.gO.contains(var1)) {
            if (this.nf.remove(var1)) {
               this.gO.remove(var1);
               return true;
            }

            if (this.gP.remove(var1)) {
               this.gO.remove(var1);
               return true;
            }
         }

         return false;
      }
   }

   public boolean q(IJOptimizer var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (this.gO.contains(var1)) {
         return false;
      } else {
         if (var2) {
            this.gP.add(var1);
         } else {
            this.nf.add(var1);
         }

         this.gO.add(var1);
         return true;
      }
   }

   private void q() {
      this.gO.add(new boq());
      this.gO.add(new bqp());
      this.gO.add(new bqs());
      this.gO.add(new boo());
      this.gO.add(new bop());
      this.gO.add(new bow());
      this.gO.add(new bpw());
      this.gO.add(new bph());
      this.gO.add(new bpi());
      this.gO.add(new bpk());
      this.gO.add(new box());
      this.gO.add(new bpl());
      this.gO.add(new bpm());
      this.gO.add(new bpn());
      this.gO.add(new bpj());
      this.gO.add(new boy());
      this.gO.add(new bqb());
      this.gO.add(new bqv());
      this.gO.add(new bor());
      this.gO.add(new bos());
      this.gO.add(new bqd());
      this.gO.add(new brb());
      this.gO.add(new bqx());
      this.gO.add(new bou());
      this.gO.add(new bqe());
      this.gO.add(new bqo());
      this.gO.add(new bot());
      this.gO.add(new bqy());
      this.gO.add(new brc());
      this.gO.add(new brd());
      this.gO.add(new bqg());
      this.gO.add(new bqc());
      this.gO.add(new bpp());
      this.gO.add(new bov());
      this.gO.add(new bqz());
      this.gO.add(new bpr());
      this.gO.add(new bpq());
      this.gO.add(new bpo());
      this.gO.add(new bqt());
      this.gO.add(new bpy());
      this.gO.add(new bpa());
      this.gO.add(new bpd());
      this.gO.add(new bqh());
      this.gO.add(new bqi());
      this.gO.add(new bqw());
      this.gO.add(new bqk());
      this.gO.add(new bre());
      this.gO.add(new bqm());
      this.gO.add(new bpz());
      this.gO.add(new bqa());
      this.gO.add(new bon());
      this.gO.add(new bps());
      this.gO.add(new bpu());
      this.gO.add(new bpv());
      this.gO.add(new bpx());
      this.gO.add(new bom());
      this.gO.add(new bol());
      this.gO.add(new bqj());
      this.gO.add(new bqf());
      this.gO.add(new bqu());
      this.gO.add(new bqq());
      this.gO.add(new bri());
      this.gO.add(new brg());
      this.gO.add(new brh());
      this.gO.add(new brj());
   }

   public IJOptimizer q(Class var1) {
      for (IJOptimizer var3 : this.gO) {
         if (var1.isInstance(var3)) {
            return var3;
         }
      }

      return null;
   }

   public boolean q(Class var1, boolean var2) {
      AbstractJOptimizer var3 = (AbstractJOptimizer)this.q(var1);
      if (var3 == null) {
         return false;
      } else {
         var3.setEnabled(var2);
         return true;
      }
   }

   public boolean RF(Class var1) {
      return this.q(var1, true);
   }

   public boolean xK(Class var1) {
      return this.q(var1, false);
   }

   @Override
   public int perform() {
      for (IJOptimizer var2 : this.gO) {
         if (var2 instanceof AbstractJOptimizer) {
            ((AbstractJOptimizer)var2).drcollector = this.Uv;
         }
      }

      this.gO.sort(new blq(this));
      boolean var12 = this.xK instanceof IJavaMethod;
      int var13 = this.RF ? 200 : 100;
      int var3 = 0;
      int var4 = 0;

      while (var3 <= var13) {
         int var5 = 0;
         Iterator var6 = this.gO.iterator();

         while (true) {
            label71:
            if (var6.hasNext()) {
               IJOptimizer var7 = (IJOptimizer)var6.next();
               if (var7 instanceof AbstractJOptimizer && !((AbstractJOptimizer)var7).isEnabled()
                  || var7.getType() == JOptimizerType.CUSTOM
                  || this.oW && var7.getType() == JOptimizerType.UNSAFE) {
                  continue;
               }

               if (var12) {
                  Watchdog.verify(this.Dw);
               }

               int var8;
               try {
                  var8 = var7.perform(this.xK);
               } catch (Exception var11) {
                  q.catchingSilent(var11);
                  boolean var10 = this.gP.contains(var7);
                  if (var12) {
                     com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(
                        new RuntimeException("AST optimizer failed", var11), this.xK.getSignature(), -1, Maps.toMap("external-dexdec-ast-optimizer", var10)
                     );
                     q.error(S.L("%s: an optimizer failed, the decompiled code could not be entirely optimized"), this.xK.getSignature());
                  }

                  if (var10) {
                     q.catchingSilent(var11);
                  }
                  break label71;
               }

               var5 += var8;
               if (!this.RF || var8 <= 0) {
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
