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

public class axl extends axi {
   private static final ILogger pC = GlobalLog.getLogger(axl.class);
   private List A;
   private List kS;
   private final axl.Sv wS;
   private final a UT;
   private final boolean E;
   private final boolean sY;
   private avp ys = new avp();
   private final String ld = "extern";
   private final String gp = "ptr_";
   private final String oT = "→";
   private final String fI = "__imp_";

   public axl(a var1, axl.Sv var2, boolean var3, boolean var4) {
      Assert.a(var1 != null && var2 != null);
      this.UT = var1;
      this.wS = var2;
      this.A = new ArrayList();
      this.E = var3;
      this.sY = var4;
   }

   public void pC(List var1) {
      this.kS = var1;
   }

   @Override
   public void pC(INativeMethodItem var1) {
      List var2 = this.UT.ys().getCallGraphManager().getGlobalCallGraph().getCallees(var1, this.sY);
      if (!var2.isEmpty()) {
         HashMap var3 = new HashMap();
         Iterator var4 = var2.iterator();

         while (true) {
            CallGraphVertex var5;
            auu var20;
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
                        var6 = ((auu)var5.getRoutine()).Er();
                        if (var6 == null) {
                           var6 = var5.getRoutine().getName(true);
                        }
                     } else if (this.UT.ys().getItemAt(var5.getDereferencedAddress()) != null) {
                        var6 = this.UT.ys().getItemAt(var5.getDereferencedAddress()).getName(true);
                     }
                     break;
                  }

                  var20 = this.UT.ys().E(var5.getInternalAddress().getAddress());
                  if (var20 != null) {
                     if (var20.equals(var1)) {
                        continue;
                     }

                     if (var20.z() != null) {
                        var6 = var20.z().pC().getTargetName();
                        List var21 = var20.z().A();
                        if (var21 != null) {
                           for (INativeSignature var14 : var21) {
                              var7.add(var14.getTargetName());
                           }
                        }

                        var10 = true;
                     } else {
                        var6 = var20.Er();
                        if (var6 == null) {
                           var6 = var20.getName(true);
                        }
                     }

                     var8 = true;
                     if (var20.E().E() != null) {
                        var9 = true;
                     }
                     break;
                  }

                  INativeContinuousItem var12 = this.UT.ys().getItemAt(var5.getInternalAddress().getAddress());
                  if (var12 instanceof auz && ((auz)var12).UO() != null) {
                     var6 = ((auu)((auz)var12).UO()).Er();
                     if (var6 == null) {
                        var6 = ((auz)var12).UO().getName(true);
                     }
                     break;
                  }

                  if (var12 != null) {
                     var6 = var12.getName(true);
                  }
                  break;
               }

               if (var6 != null && (!this.E || !var8 || var9) && this.A(var6)) {
                  INativeFeature var19 = (INativeFeature)var3.get(var5);
                  int var22 = 1;
                  if (var19 != null) {
                     if (var19 instanceof axk) {
                        var22 = ((axk)var19).kS() + 1;
                     } else {
                        if (!(var19 instanceof axj)) {
                           throw new RuntimeException();
                        }

                        for (INativeFeature var25 : ((axj)var19).pC()) {
                           if (!(var25 instanceof axk)) {
                              throw new RuntimeException();
                           }

                           var22 = ((axk)var25).kS() + 1;
                        }
                     }

                     var3.remove(var5);
                     this.A.remove(var19);
                  }

                  axl.Av var24 = null;
                  if (!var10) {
                     if (this.wS == axl.Sv.kS) {
                        var24 = this.wS(var6);
                     } else if (this.wS == axl.Sv.A) {
                        var24 = this.UT(var6);
                     } else {
                        var24 = this.kS(var6);
                     }
                  }

                  if (var24 != null) {
                     if (!this.pC(var24.pC)) {
                        if (var24.A == null) {
                           var20 = new axk(var24.pC, var8, var22);
                           if (var24.kS) {
                              ((axk)var20).pC(true);
                           }
                        } else {
                           axk var27 = new axk(var24.pC, var8, var22);
                           axk var28 = new axk(var24.A, var8, var22);
                           if (var24.kS) {
                              var27.pC(true);
                              var28.pC(true);
                           }

                           HashSet var29 = new HashSet();
                           var29.add(var27);
                           var29.add(var28);
                           var20 = new axj(var29);
                        }
                        break;
                     }
                  } else if (!this.pC(var6)) {
                     if (var7.isEmpty()) {
                        var20 = new axk(var6, var8, var22);
                        break;
                     }

                     HashSet var26 = new HashSet();
                     axk var15 = new axk(var6, var8, var22);
                     var26.add(var15);

                     for (String var17 : var7) {
                        axk var18 = new axk(var17, var8, var22);
                        var26.add(var18);
                     }

                     if (var26.size() > 1) {
                        var20 = new axj(var26);
                     } else {
                        var20 = new axk(var6, var8, var22);
                     }
                     break;
                  }
               }
            }

            var3.put(var5, var20);
            this.A.add(var20);
         }
      }
   }

   private axl.Av kS(String var1) {
      axl.Av var2 = new axl.Av();

      while (var1.startsWith("→")) {
         var1 = var1.substring("→".length());
      }

      var2.pC = var1;
      return var2;
   }

   private axl.Av wS(String var1) {
      axl.Av var2 = new axl.Av();
      if (var1.startsWith("→") || var1.startsWith("ptr_")) {
         int var3 = var1.indexOf(".dll!");
         if (var3 != -1) {
            var1 = var1.substring(var3 + 5);
         }
      }

      var2.pC = var1;
      return var2;
   }

   private axl.Av UT(String var1) {
      axl.Av var2 = new axl.Av();
      if (var1.startsWith("extern")) {
         var1 = var1.substring(6);
      }

      if (var1.startsWith("__imp_")) {
         var1 = var1.substring(6);
         var2.kS = true;
      }

      IUnmangledData var3 = this.ys.unmangle(var1);
      if (var3 instanceof IUnmangledRoutine) {
         var2.A = ((IUnmangledRoutine)var3).getName();
      }

      var2.pC = var1;
      return var2;
   }

   @Override
   public List pC() {
      return this.A;
   }

   @Override
   public void A() {
      this.A.clear();
   }

   public boolean pC(String var1) {
      return this.kS != null && this.kS.contains(var1);
   }

   public boolean A(String var1) {
      return var1 != null && !this.UT.ys().oT().pC(var1);
   }

   private static class Av {
      String pC;
      String A;
      boolean kS = false;
   }

   public static enum Sv {
      pC,
      A,
      kS;
   }
}
