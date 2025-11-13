package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Dx {
   private static final ILogger pC = GlobalLog.getLogger(Dx.class);
   private final Ro.Sv A = new Ro.Sv();
   private final Ei kS;
   private final Ro.Av wS;
   private final DM UT;
   private final IVirtualMemory E;
   private final uj sY;
   private final Sp ys;
   private final IEGlobalContext ld;

   public Dx(IVirtualMemory var1, uj var2, Sp var3, IEGlobalContext var4) {
      this.kS = new Ei(var4);
      this.wS = new Ro.Av();
      this.UT = new DM(var4, var3, this.A, this.wS);
      this.E = var1;
      this.sY = var2;
      this.ys = var3;
      this.ld = var4;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   protected CW pC(long var1, List var3, Map var4, os var5) {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var6 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var3.get(var3.size() - 1);
      DM.K var7 = new DM.K();
      var7.pC(var1, var3);
      DM.Av var8 = this.UT.pC();
      Ro.K var9 = new Ro.K();
      var9.pC(this.ys.getProgramCounter(), null);
      var9.pC = this.ys.E();
      ok var10 = ok.pC(var1, var6, var9, this.sY, this.ys);
      switch (var10) {
         case pC:
            return null;
         case kS:
            var7.pC();
         case A:
         default:
            this.UT.pC(var6);
            boolean var11 = false;
            boolean var12 = false;

            for (; var7.kS >= -1; var7.pC()) {
               if (var7.kS == -1) {
                  DM.Ws var13 = this.UT.pC(var7, var4, var5, var9.pC(), var8);
                  if (!var13.pC) {
                     break;
                  }
               } else {
                  com.pnfsoftware.jeb.corei.parsers.arm.rQ var17 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var7.A.get(var7.kS);
                  String var14 = var17.wS().pC();
                  if (var17.getProcessorMode() != 64 && var9.pC.equals(this.ys.E()) && (var7.kS < var3.size() - 1 || var7.A != var3)) {
                     var9.pC = EUtil.imm(var6.getProcessorMode() == 16 ? 1L : 0L, 1);
                  }

                  if (this.A.wS() && this.pC(var9) || this.pC(var9.pC()) > 30 || !var9.kS() || Td.pC(this.ld, var9).size() == 0) {
                     break;
                  }

                  if (var17.A() != null && var17.A().length != 0 && (!var17.UT().sY() || var17 == var6 || !this.pC(var9, var17, var14))) {
                     if (var8.pC(var17, var9)) {
                        DM.Sv var15 = var8.pC();
                        if (var15 == DM.Sv.A) {
                           break;
                        }

                        if (var15 == DM.Sv.kS) {
                           continue;
                        }
                     }

                     if (!var12 && var17 != var6) {
                        this.A.pC(var9, this.ld, this.ys);
                     }

                     boolean var20 = this.A.pC(var17, var14, var7.pC, this.ys, this.wS, var7.A, var7.kS);
                     if (!this.A.pC(var17, var7.pC, var9, this.ys, this.ld, this.E, this.wS, var7.A, var7.kS)) {
                        break;
                     }

                     if (this.A.UT() != null || !this.pC(var9, var17, var14, var7, var3)) {
                        if (!var12 && !var11 && this.A.kS() && this.A.oT.isEmpty() && (this.A.UT() == null || !Td.pC(this.ld, this.ys, this.A.UT(), var9))) {
                           ig var16 = new ig(this.A, this.wS, this.UT, this.ys, this.ld);
                           var11 = var16.pC(var7.A(), var4, var5.pC(), var9, var8.A());
                        }

                        if (!var12 && this.A.UT() != null) {
                           this.ys.pC(var9, this.A.UT());
                           if (Td.pC(this.ld, var9).contains(this.ys.sY())) {
                              var12 = true;
                           }
                        }

                        if (var20 && !this.ys.pC(var17, var7.pC, var9)) {
                           break;
                        }
                     }
                  }
               }
            }

            if (!var9.kS()) {
               return null;
            } else {
               if (var9.pC.equals(this.ys.E())) {
                  var9.pC = EUtil.imm(var6.getProcessorMode() == 16 ? 1L : 0L, 1);
               }

               if (this.A.UT() != null) {
                  this.ys.A(var9, this.A.UT());
               }

               CW var18 = new CW();
               boolean var19 = this.kS.pC(this.ys.A(var9.pC()), var9.pC);
               var18.pC(this.kS);
               if (var19) {
                  if (this.A.wS()) {
                     var19 = this.wS.A() != null && Td.pC(this.ys, this.A.UT(), this.kS.kS);
                  } else {
                     var19 = this.wS.A() != null && this.kS.kS.equals(this.ys.sY());
                  }
               }

               var18.pC(var19, this.E, this.A, var1);
               return var18;
            }
      }
   }

   private int pC(IEGeneric var1) {
      int var2 = 0;
      ArrayList var3 = new ArrayList();
      var1.collectSubExpressions(var3);
      if (var3.size() == 0) {
         return 1;
      } else {
         for (IEGeneric var5 : var3) {
            var2 += this.pC(var5);
         }

         return var2;
      }
   }

   private boolean pC(Ro.K var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var2, String var3) {
      if (this.pC(var2, var1)) {
         if (var3.equals("MOV") && var2.A()[1].getOperandType() == 1 && this.A.UT() == null && this.pC(var1, var2)) {
            if (this.wS.pC != null && this.wS.pC.equals(var2.A()[0])) {
               this.A.fI.add(var2.A()[1].getOperandValue());
            } else if (this.pC(var1, var2)) {
               this.wS.pC = var2.A()[0];
               this.A.fI.add(var2.A()[1].getOperandValue());
            }

            return true;
         }

         if (Strings.isContainedIn(var3, "ADD", "SUB", "RSB", "LSL")) {
            return var2.UT().A() != 1;
         }
      }

      return PU.pC(var2.UT().A());
   }

   private boolean pC(Ro.K var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var2, String var3, DM.K var4, List var5) {
      if (!this.A.pC()) {
         return false;
      } else {
         int var6 = var2.getCountOfOperands() - 1;
         if (var2.A()[var6].getOperandType() != 1) {
            return false;
         } else {
            if (var3.equals("MOV")) {
               if (this.pC(var1, var2)) {
                  this.pC(var1, var2.A()[0]);
                  this.A.oT.add(var2.A()[1].getOperandValue());
                  if (var4.A == var5) {
                     this.A.ld = true;
                  }

                  if (this.wS.pC != null && !this.A.UT().equals(this.wS.pC)) {
                     this.A.fI.clear();
                     this.wS.pC = null;
                  }

                  return true;
               }
            } else if (var3.equals("LSR") && Td.pC(this.ld, this.ys, var2.A()[0], var1)) {
               IEGeneric var7 = this.ys.kS(var2, var2.A()[var6 - 1], -1L);
               List var8 = EUtil.getParents(var1.pC(), var7);
               int var9 = 0;
               int var10 = var7.getBitsize();

               for (IEGeneric var12 : var8) {
                  if (var12 instanceof IESlice) {
                     var9 = Math.max(var9, var12.asSlice().getBitStart());
                     var10 = Math.min(var10, var12.asSlice().getBitEnd());
                  }
               }

               long var13 = -1L >>> var9 << var9;
               var13 = var13 << 64 - var10 >>> 64 - var10;
               var13 >>>= (int)var2.pC(var6).getOperandValue();
               if (var13 <= 0L) {
                  return false;
               }

               if (var13 < 256L) {
                  this.pC(var1, var2.A()[0], var2.A()[var6 - 1]);
                  this.A.A = var7;
                  this.A.UT = (int)var13;
                  this.A.UT++;
               }
            }

            return false;
         }
      }
   }

   private boolean pC(Ro.K var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var2) {
      if (!Td.pC(this.ld, this.ys, var2.A()[0], var1)) {
         return false;
      } else {
         IEGeneric var3 = this.ys.kS(var2, var2.A()[0], -1L);
         boolean var4 = var1.pC().visitDepthPre(new OV(this, var3));
         return !var4;
      }
   }

   private boolean pC(Ro.K var1) {
      List var2 = Td.pC(this.ld, var1);
      return var2.size() != 1 ? false : ((IEVar)var2.get(0)).equals(this.ys.sY()) || Td.pC(this.ys, this.A.UT(), (IEVar)var2.get(0));
   }

   private boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Ro.K var2) {
      return Td.pC(this.ys, var1, var2);
   }

   private void pC(Ro.K var1, Yg var2) {
      this.pC(var1, var2, var2);
   }

   private void pC(Ro.K var1, Yg var2, Yg var3) {
      this.A.pC = new Ro.bO(var3, -1);
      this.ys.pC(var1, var2);
   }
}
