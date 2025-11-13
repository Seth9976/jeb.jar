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

public class KV {
   private static final ILogger q = GlobalLog.getLogger(KV.class);
   private static final boolean RF = false;
   private static final int xK = 30;
   private final Ia.CU Dw = new Ia.CU();
   private final tx Uv;
   private final Ia.eo oW;
   private final gP gO;
   private final IVirtualMemory nf;
   private final AL gP;
   private final bR za;
   private final IEGlobalContext lm;

   public KV(IVirtualMemory var1, AL var2, bR var3, IEGlobalContext var4) {
      this.Uv = new tx(var4);
      this.oW = new Ia.eo();
      this.gO = new gP(var4, var3, this.Dw, this.oW);
      this.nf = var1;
      this.gP = var2;
      this.za = var3;
      this.lm = var4;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   protected Vl q(long var1, List var3, Map var4, LI var5) {
      fA var6 = (fA)var3.get(var3.size() - 1);
      gP.nI var7 = new gP.nI();
      var7.q(var1, var3);
      gP.eo var8 = this.gO.q();
      Ia.nI var9 = new Ia.nI();
      var9.q(this.za.getProgramCounter(), null);
      var9.q = this.za.oW();
      tK var10 = tK.q(var1, var6, var9, this.gP, this.za);
      switch (var10) {
         case q:
            return null;
         case xK:
            var7.q();
         case RF:
         default:
            this.gO.q(var6);
            boolean var11 = false;
            boolean var12 = false;

            for (; var7.xK >= -1; var7.q()) {
               if (var7.xK == -1) {
                  gP.ej var13 = this.gO.q(var7, var4, var5, var9.q(), var8);
                  if (!var13.q) {
                     break;
                  }
               } else {
                  fA var17 = (fA)var7.RF.get(var7.xK);
                  String var14 = var17.Dw().q();
                  if (var17.getProcessorMode() != 64 && var9.q.equals(this.za.oW()) && (var7.xK < var3.size() - 1 || var7.RF != var3)) {
                     var9.q = EUtil.imm(var6.getProcessorMode() == 16 ? 1L : 0L, 1);
                  }

                  if (this.Dw.Uv() && this.q(var9) || this.q(var9.q()) > 30 || !var9.xK() || tB.q(this.lm, var9).size() == 0) {
                     break;
                  }

                  if (var17.RF() != null && var17.RF().length != 0 && (!var17.Uv().gO() || var17 == var6 || !this.q(var9, var17, var14))) {
                     if (var8.q(var17, var9)) {
                        gP.CU var15 = var8.q();
                        if (var15 == gP.CU.RF) {
                           break;
                        }

                        if (var15 == gP.CU.xK) {
                           continue;
                        }
                     }

                     if (!var12 && var17 != var6) {
                        this.Dw.q(var9, this.lm, this.za);
                     }

                     boolean var20 = this.Dw.q(var17, var14, var7.q, this.za, this.oW, var7.RF, var7.xK);
                     if (!this.Dw.q(var17, var7.q, var9, this.za, this.lm, this.nf, this.oW, var7.RF, var7.xK)) {
                        break;
                     }

                     if (this.Dw.oW() != null || !this.q(var9, var17, var14, var7, var3)) {
                        if (!var12 && !var11 && this.Dw.Dw() && this.Dw.lm.isEmpty() && (this.Dw.oW() == null || !tB.q(this.lm, this.za, this.Dw.oW(), var9))) {
                           pe var16 = new pe(this.Dw, this.oW, this.gO, this.za, this.lm);
                           var11 = var16.q(var7.RF(), var4, var5.q(), var9, var8.RF());
                        }

                        if (!var12 && this.Dw.oW() != null) {
                           this.za.q(var9, this.Dw.oW());
                           if (tB.q(this.lm, var9).contains(this.za.gO())) {
                              var12 = true;
                           }
                        }

                        if (var20 && !this.za.q(var17, var7.q, var9)) {
                           break;
                        }
                     }
                  }
               }
            }

            if (!var9.xK()) {
               return null;
            } else {
               if (var9.q.equals(this.za.oW())) {
                  var9.q = EUtil.imm(var6.getProcessorMode() == 16 ? 1L : 0L, 1);
               }

               if (this.Dw.oW() != null) {
                  this.za.RF(var9, this.Dw.oW());
               }

               Vl var18 = new Vl();
               boolean var19 = this.Uv.q(this.za.RF(var9.q()), var9.q);
               var18.q(this.Uv);
               if (var19) {
                  if (this.Dw.Uv()) {
                     var19 = this.oW.RF() != null && tB.q(this.za, this.Dw.oW(), this.Uv.xK);
                  } else {
                     var19 = this.oW.RF() != null && this.Uv.xK.equals(this.za.gO());
                  }
               }

               var18.q(var19, this.nf, this.Dw, var1);
               return var18;
            }
      }
   }

   private int q(IEGeneric var1) {
      int var2 = 0;
      ArrayList var3 = new ArrayList();
      var1.collectSubExpressions(var3);
      if (var3.size() == 0) {
         return 1;
      } else {
         for (IEGeneric var5 : var3) {
            var2 += this.q(var5);
         }

         return var2;
      }
   }

   private boolean q(Ia.nI var1, fA var2, String var3) {
      if (this.q(var2, var1)) {
         if (var3.equals("MOV") && var2.RF()[1].getOperandType() == 1 && this.Dw.oW() == null && this.q(var1, var2)) {
            if (this.oW.q != null && this.oW.q.equals(var2.RF()[0])) {
               this.Dw.zz.add(var2.RF()[1].getOperandValue());
            } else if (this.q(var1, var2)) {
               this.oW.q = var2.RF()[0];
               this.Dw.zz.add(var2.RF()[1].getOperandValue());
            }

            return true;
         }

         if (Strings.isContainedIn(var3, "ADD", "SUB", "RSB", "LSL")) {
            return var2.Uv().RF() != 1;
         }
      }

      return OC.q(var2.Uv().RF());
   }

   private boolean q(Ia.nI var1, fA var2, String var3, gP.nI var4, List var5) {
      if (!this.Dw.q()) {
         return false;
      } else {
         int var6 = var2.getCountOfOperands() - 1;
         if (var2.RF()[var6].getOperandType() != 1) {
            return false;
         } else {
            if (var3.equals("MOV")) {
               if (this.q(var1, var2)) {
                  this.q(var1, var2.RF()[0]);
                  this.Dw.lm.add(var2.RF()[1].getOperandValue());
                  if (var4.RF == var5) {
                     this.Dw.gP = true;
                  }

                  if (this.oW.q != null && !this.Dw.oW().equals(this.oW.q)) {
                     this.Dw.zz.clear();
                     this.oW.q = null;
                  }

                  return true;
               }
            } else if (var3.equals("LSR") && tB.q(this.lm, this.za, var2.RF()[0], var1)) {
               IEGeneric var7 = this.za.xK(var2, var2.RF()[var6 - 1], -1L);
               List var8 = EUtil.getParents(var1.q(), var7);
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
               var13 >>>= (int)var2.q(var6).getOperandValue();
               if (var13 <= 0L) {
                  return false;
               }

               if (var13 < 256L) {
                  this.q(var1, var2.RF()[0], var2.RF()[var6 - 1]);
                  this.Dw.RF = var7;
                  this.Dw.Uv = (int)var13;
                  this.Dw.Uv++;
               }
            }

            return false;
         }
      }
   }

   private boolean q(Ia.nI var1, fA var2) {
      if (!tB.q(this.lm, this.za, var2.RF()[0], var1)) {
         return false;
      } else {
         IEGeneric var3 = this.za.xK(var2, var2.RF()[0], -1L);
         boolean var4 = var1.q().visitDepthPre(new nC(this, var3));
         return !var4;
      }
   }

   private boolean q(Ia.nI var1) {
      List var2 = tB.q(this.lm, var1);
      return var2.size() != 1 ? false : ((IEVar)var2.get(0)).equals(this.za.gO()) || tB.q(this.za, this.Dw.oW(), (IEVar)var2.get(0));
   }

   private boolean q(fA var1, Ia.nI var2) {
      return tB.q(this.za, var1, var2);
   }

   private void q(fA var1, IEGeneric var2) {
   }

   private void q(Ia.nI var1, CW var2) {
      this.q(var1, var2, var2);
   }

   private void q(Ia.nI var1, CW var2, CW var3) {
      this.Dw.q = new Ia.oM(var3, -1);
      this.za.q(var1, var2);
   }
}
