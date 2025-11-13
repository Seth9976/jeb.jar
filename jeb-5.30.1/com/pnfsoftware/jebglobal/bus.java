package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.IrregularFlowData;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class bus {
   private static final ILogger xK = GlobalLog.getLogger(bus.class);
   private static final boolean Dw = false;
   public static final int q = 8;
   public static final int RF = 10000;
   private IDMethodContext Uv;
   private CFG oW;
   private int gO;
   private List nf;
   private LinkedHashSet gP;

   private static void q(String var0, Object... var1) {
   }

   private static void q(CFG var0, String var1, Object... var2) {
   }

   private static void q(Throwable var0) {
   }

   private static void q(Throwable var0, String var1, Object... var2) {
   }

   public bus(IDMethodContext var1) {
      this(var1, -1);
   }

   public bus(IDMethodContext var1, int var2) {
      this.Uv = var1;
      this.oW = var1.getCfg();
      this.gO = var2;
   }

   public boolean q(int var1) {
      if (this.nf != null) {
         throw new IllegalStateException();
      } else {
         this.nf = new ArrayList();
         this.gP = new LinkedHashSet();
         boolean var2 = false;
         if (this.gO >= 0) {
            int var3 = 0;

            for (BasicBlock var5 : this.oW) {
               var3 += var5.irroutsize();
               if (var3 >= this.gO) {
                  var2 = true;
                  break;
               }
            }
         }

         Set var27 = new bwr(this.Uv).q(var1);
         Object[] var10000 = new Object[]{DUtil.formatVarIds(var27)};
         if (var27.size() > 8) {
            return false;
         } else {
            ArrayDeque var28 = new ArrayDeque();
            bus.eo var29 = new bus.eo(this.oW.get(0));
            this.nf.add(var29);
            this.gP.add(var29);
            var28.add(var29);
            int var6 = 0;

            while (!var28.isEmpty()) {
               if (++var6 > 10000) {
                  var10000 = new Object[0];
                  com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(
                     new RuntimeException("Unrolling: exceeded max rounds count"), this.Uv.getMethodSignature(), -1, null, true
                  );
                  return false;
               }

               if ((var6 & 4095) == 0) {
                  Watchdog.verify(this.Uv.getWatchdog());
               }

               bus.eo var7 = (bus.eo)var28.remove();
               var10000 = new Object[]{var6, var7, var28.size()};
               BasicBlock var8 = var7.q;
               Map var9 = var7.q();
               long var12 = -1L;
               IDInstruction var14 = (IDInstruction)var8.getLast();

               for (int var15 = 0; var15 < var8.size(); var15++) {
                  IDInstruction var16 = (IDInstruction)var8.get(var15);
                  Integer var17 = null;
                  if (var16.isAssignToVar()) {
                     var17 = var16.getAssignDestination().asVar().getId();
                  }

                  boolean var18 = q(var16, var9.keySet());
                  if (var18 && var17 != null) {
                     IDImm var19 = null;

                     try {
                        var19 = var16.getAssignSource().evaluate(this.Uv.getGlobalContext(), var9);
                     } catch (DexDecEvaluationException var26) {
                     }

                     if (var19 == null) {
                        var10000 = new Object[0];
                        var10000 = new Object[]{var16.getOffset(), var16};
                        return false;
                     }

                     var9.put(var17, var19);
                  }

                  if (var17 != null && !var18) {
                     if (var17 != var1 && !var9.containsKey(var17) && !var27.contains(var17)) {
                        var9.remove(var17);
                     } else {
                        IDImm var54 = null;

                        try {
                           var54 = var16.getAssignSource().evaluate(this.Uv.getGlobalContext(), var9);
                        } catch (DexDecEvaluationException var25) {
                        }

                        if (var54 == null) {
                           var10000 = new Object[]{var17};
                           var10000 = new Object[]{var16.getOffset(), var16};
                           return false;
                        }

                        var9.put(var17, var54);
                     }
                  }

                  if (var16 == var14 && var16.getBreakingFlow().isBroken()) {
                     Integer var55 = null;

                     try {
                        var55 = var16.evaluate(var9);
                     } catch (DexDecEvaluationException var24) {
                     }

                     if (var55 != null) {
                        var12 = var55.intValue();
                     }
                  }
               }

               var7.Dw = var12;

               for (IDInstruction var42 : var8) {
                  var7.xK.add(var42.duplicateWithOffsetAndSize(-1L, 1));
               }

               boolean var40 = false;
               switch (var14.getOpcode()) {
                  case IR_JUMP:
                  case IR_THROW:
                  case IR_RETURN:
                     break;
                  case IR_JCOND:
                  case IR_SWITCH:
                     var40 = true;
                  default:
                     int var43 = (int)var8.getEndAddress();
                     var7.xK.add(this.Uv.createJump(var43).withOffset(-1L));
               }

               HashMap var44 = new HashMap();
               if (var12 != -1L) {
                  if (var40) {
                     var7.xK.remove(var7.xK.size() - 2);
                     var40 = false;
                  }

                  IDInstruction var47 = (IDInstruction)var7.xK.get(var7.xK.size() - 1);
                  var47.setBranchTarget((int)var12);
                  BasicBlock var50 = this.oW.getBlockAt(var12);
                  bus.eo var57 = new bus.eo(var50, this.q(var50, var9));
                  int var61;
                  if (this.gP.contains(var57)) {
                     var61 = this.nf.indexOf(var57);
                     Assert.a(var61 >= 0);
                  } else {
                     var61 = this.nf.size();
                     this.nf.add(var57);
                     this.gP.add(var57);
                     var28.add(var57);
                  }

                  var44.put((int)var12, -var61);
               } else {
                  for (BasicBlock var49 : var8.getOutputBlocks()) {
                     bus.eo var56 = new bus.eo(var49, this.q(var49, var9));
                     int var20;
                     if (this.gP.contains(var56)) {
                        var20 = this.nf.indexOf(var56);
                        Assert.a(var20 >= 0);
                     } else {
                        var20 = this.nf.size();
                        this.nf.add(var56);
                        this.gP.add(var56);
                        var28.add(var56);
                     }

                     var44.put((int)var49.getBase(), -var20);
                  }
               }

               int var48 = var7.xK.size();
               ((IDInstruction)var7.xK.get(var48 - 1)).updateTargets(var44);
               if (var40) {
                  ((IDInstruction)var7.xK.get(var48 - 2)).updateTargets(var44);
               }

               var7.Uv = var40;
               if (!var2 && var8.canThrow()) {
                  for (BasicBlock var58 : var8.getIrregularOutputBlocks()) {
                     bus.eo var62 = new bus.eo(var58, this.q(var58, var9));
                     int var21;
                     if (this.gP.contains(var62)) {
                        var21 = this.nf.indexOf(var62);
                        Assert.a(var21 >= 0);
                     } else {
                        var21 = this.nf.size();
                        this.nf.add(var62);
                        this.gP.add(var62);
                        var28.add(var62);
                     }

                     var44.put((int)var58.getBase(), -var21);
                     var7.oW.add(-var21);
                  }

                  for (IDExceptionHandler var63 : this.Uv.getExceptionData().getBlockHandlers((int)var8.getBase())) {
                     biv var64 = new biv(var63.getTypeIndex(), (Integer)var44.get(var63.getAddress()));
                     var7.gO.add(var64);
                  }

                  Assert.a(var7.gO.size() == var7.oW.size());
               }
            }

            var10000 = new Object[]{this.nf.size(), var6};
            var10000 = new Object[]{this.oW.size()};
            HashMap var30 = new HashMap();
            ArrayList var31 = new ArrayList();
            int var32 = 0;
            int var10 = 0;

            for (bus.eo var34 : this.nf) {
               var30.put(-var10, var32);
               var32 += var34.xK.size();
               var31.addAll(var34.xK);
               var10++;
            }

            int var33 = 0;

            for (IDInstruction var13 : var31) {
               var13.setOffset(var33);
               var13.updateTargets(var30);
               var33++;
            }

            ArrayList var36 = new ArrayList();
            btn var37 = new btn();

            for (bus.eo var41 : this.nf) {
               Assert.a(var41.gO.size() == var41.oW.size());
               if (!var41.oW.isEmpty()) {
                  long var45 = ((IDInstruction)var41.xK.get(0)).getOffset();
                  int var53 = var41.xK.size() - (var41.Uv ? 1 : 0);
                  long var60 = ((IDInstruction)var41.xK.get(var53 - 1)).getOffset();

                  for (int var22 : var41.oW) {
                     int var23 = (Integer)var30.get(var22);
                     var36.add(new IrregularFlowData(var45, var60, var23));
                  }

                  for (biv var67 : var41.gO) {
                     int var68 = (Integer)var30.get(var67.getAddress());
                     var37.q((int)var45, var67.RF(), var68);
                  }
               }
            }

            this.oW = new CFG(var31, var36);
            DUtil.removeGaps(this.oW);
            this.Uv.replace(this.oW, var37);
            DUtil.cleanGraph(this.Uv);
            return true;
         }
      }
   }

   private Map q(BasicBlock var1, Map var2) {
      HashMap var3 = new HashMap(var2);
      IDFA var4 = this.oW.doDataFlowAnalysis();
      Iterator var5 = var3.keySet().iterator();

      while (var5.hasNext()) {
         int var6 = (Integer)var5.next();
         if (!var4.isAlive(var1, 0, var6)) {
            var5.remove();
         }
      }

      return var3;
   }

   private static boolean q(IDInstruction var0, Set var1) {
      return !var0.visitInstruction(new but(var1), true);
   }

   static class eo {
      BasicBlock q;
      Map RF;
      List xK = new ArrayList();
      long Dw = -1L;
      boolean Uv;
      List oW = new ArrayList();
      List gO = new ArrayList();

      eo(BasicBlock var1) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         } else {
            this.q = var1;
            this.RF = new HashMap();
         }
      }

      eo(BasicBlock var1, Map var2) {
         if (var1 != null && var2 != null) {
            this.q = var1;
            this.RF = var2;
         } else {
            throw new IllegalArgumentException();
         }
      }

      Map q() {
         return new HashMap(this.RF);
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
         return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            bus.eo var2 = (bus.eo)var1;
            if (this.q == null) {
               if (var2.q != null) {
                  return false;
               }
            } else if (!this.q.equals(var2.q)) {
               return false;
            }

            if (this.RF == null) {
               if (var2.RF != null) {
                  return false;
               }
            } else if (!this.RF.equals(var2.RF)) {
               return false;
            }

            return true;
         }
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "0x%04X", this.q.getBase());
         if (this.Dw != -1L) {
            Strings.ff(var1, " ->0x%04X", this.Dw);
         }

         if (!this.RF.isEmpty()) {
            var1.append(" [");
            int var2 = 0;

            for (int var4 : this.RF.keySet()) {
               if (var2 > 0) {
                  var1.append(", ");
               }

               Strings.ff(var1, "%s:%s", DUtil.formatVarId(var4), this.RF.get(var4));
               var2++;
            }

            var1.append("]");
         }

         return var1.toString();
      }
   }
}
