package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CallGraphVertex;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledRoutine;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class bai extends baf {
   private static final ILogger q = GlobalLog.getLogger(bai.class);
   private List RF;
   private List xK;
   private final bai.CU Dw;
   private final aae Uv;
   private final boolean oW;
   private final boolean gO;
   private ayl nf = new ayl();
   private final String gP = "extern";
   private final String za = "ptr_";
   private final String lm = "→";
   private final String zz = "__imp_";

   public bai(aae var1, bai.CU var2, boolean var3, boolean var4) {
      Assert.a(var1 != null && var2 != null);
      this.Uv = var1;
      this.Dw = var2;
      this.RF = new ArrayList();
      this.oW = var3;
      this.gO = var4;
   }

   public void q(List var1) {
      this.xK = var1;
   }

   @Override
   public void q(INativeMethodItem var1) {
      List var2 = this.Uv.za().getCallGraphManager().getGlobalCallGraph().getCallees(var1, this.gO);
      if (!var2.isEmpty()) {
         HashMap var3 = new HashMap();
         Iterator var4 = var2.iterator();

         while (true) {
            CallGraphVertex var5;
            axp var20;
            while (true) {
               String var6;
               HashSet var7;
               boolean var8;
               boolean var9;
               boolean var10;
               while (true) {
                  if (!var4.hasNext()) {
                     return;
                  }

                  var5 = (CallGraphVertex)var4.next();
                  var6 = null;
                  var7 = new HashSet();
                  var8 = false;
                  var9 = false;
                  var10 = false;
                  if (!var5.isInternal()) {
                     if (var5.isResolved()) {
                        var6 = ((axp)var5.getRoutine()).mI();
                        if (var6 == null) {
                           var6 = var5.getRoutine().getName(true);
                        }
                     } else if (this.Uv.za().getItemAt(var5.getDereferencedAddress()) != null) {
                        var6 = this.Uv.za().getItemAt(var5.getDereferencedAddress()).getName(true);
                     }
                     break;
                  }

                  var20 = this.Uv.za().oW(var5.getInternalAddress().getAddress());
                  if (var20 != null) {
                     if (var20.equals(var1)) {
                        continue;
                     }

                     if (var20.wF() != null) {
                        var6 = var20.wF().q().getTargetName();
                        List var21 = var20.wF().RF();
                        if (var21 != null) {
                           for (INativeSignature var14 : var21) {
                              var7.add(var14.getTargetName());
                           }
                        }

                        var10 = true;
                     } else {
                        var6 = var20.mI();
                        if (var6 == null) {
                           var6 = var20.getName(true);
                        }
                     }

                     var8 = true;
                     if (var20.oW().oW() != null) {
                        var9 = true;
                     }
                     break;
                  }

                  INativeContinuousItem var12 = this.Uv.za().getItemAt(var5.getInternalAddress().getAddress());
                  if (var12 instanceof axu && ((axu)var12).cC() != null) {
                     var6 = ((axp)((axu)var12).cC()).mI();
                     if (var6 == null) {
                        var6 = ((axu)var12).cC().getName(true);
                     }
                     break;
                  }

                  if (var12 != null) {
                     var6 = var12.getName(true);
                  }
                  break;
               }

               if (var6 != null && (!this.oW || !var8 || var9) && this.RF(var6)) {
                  INativeFeature var19 = (INativeFeature)var3.get(var5);
                  int var22 = 1;
                  if (var19 != null) {
                     if (var19 instanceof bah) {
                        var22 = ((bah)var19).xK() + 1;
                     } else {
                        if (!(var19 instanceof bag)) {
                           throw new RuntimeException();
                        }

                        for (INativeFeature var25 : ((bag)var19).q()) {
                           if (!(var25 instanceof bah)) {
                              throw new RuntimeException();
                           }

                           var22 = ((bah)var25).xK() + 1;
                        }
                     }

                     var3.remove(var5);
                     this.RF.remove(var19);
                  }

                  bai.eo var24 = null;
                  if (!var10) {
                     if (this.Dw == bai.CU.xK) {
                        var24 = this.Dw(var6);
                     } else if (this.Dw == bai.CU.RF) {
                        var24 = this.Uv(var6);
                     } else {
                        var24 = this.xK(var6);
                     }
                  }

                  if (var24 != null) {
                     if (!this.q(var24.q)) {
                        if (var24.RF == null) {
                           var20 = new bah(var24.q, var8, var22);
                           if (var24.xK) {
                              ((bah)var20).q(true);
                           }
                        } else {
                           bah var27 = new bah(var24.q, var8, var22);
                           bah var28 = new bah(var24.RF, var8, var22);
                           if (var24.xK) {
                              var27.q(true);
                              var28.q(true);
                           }

                           HashSet var29 = new HashSet();
                           var29.add(var27);
                           var29.add(var28);
                           var20 = new bag(var29);
                        }
                        break;
                     }
                  } else if (!this.q(var6)) {
                     if (var7.isEmpty()) {
                        var20 = new bah(var6, var8, var22);
                        break;
                     }

                     HashSet var26 = new HashSet();
                     bah var15 = new bah(var6, var8, var22);
                     var26.add(var15);

                     for (String var17 : var7) {
                        bah var18 = new bah(var17, var8, var22);
                        var26.add(var18);
                     }

                     if (var26.size() > 1) {
                        var20 = new bag(var26);
                     } else {
                        var20 = new bah(var6, var8, var22);
                     }
                     break;
                  }
               }
            }

            var3.put(var5, var20);
            this.RF.add(var20);
         }
      }
   }

   private bai.eo xK(String var1) {
      bai.eo var2 = new bai.eo();

      while (var1.startsWith("→")) {
         var1 = var1.substring("→".length());
      }

      var2.q = var1;
      return var2;
   }

   private bai.eo Dw(String var1) {
      bai.eo var2 = new bai.eo();
      if (var1.startsWith("→") || var1.startsWith("ptr_")) {
         int var3 = var1.indexOf(".dll!");
         if (var3 != -1) {
            var1 = var1.substring(var3 + 5);
         }
      }

      var2.q = var1;
      return var2;
   }

   private bai.eo Uv(String var1) {
      bai.eo var2 = new bai.eo();
      if (var1.startsWith("extern")) {
         var1 = var1.substring(6);
      }

      if (var1.startsWith("__imp_")) {
         var1 = var1.substring(6);
         var2.xK = true;
      }

      IUnmangledData var3 = this.nf.unmangle(var1);
      if (var3 instanceof IUnmangledRoutine) {
         var2.RF = ((IUnmangledRoutine)var3).getName();
      }

      var2.q = var1;
      return var2;
   }

   @Override
   public List q() {
      return this.RF;
   }

   @Override
   public void RF() {
      this.RF.clear();
   }

   public boolean q(String var1) {
      return this.xK != null && this.xK.contains(var1);
   }

   public boolean RF(String var1) {
      return var1 != null && !this.Uv.za().LK().q(var1);
   }

   public static enum CU {
      q,
      RF,
      xK;
   }

   private static class eo {
      String q;
      String RF;
      boolean xK = false;
   }
}
