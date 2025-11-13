package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Map.Entry;

public class ZM {
   private INativeCodeAnalyzer pC;
   private dA A;
   private boolean kS;
   private boolean wS;

   public ZM(INativeCodeAnalyzer var1, com.pnfsoftware.jeb.corei.parsers.arm.Av var2, boolean var3, boolean var4) {
      this.pC = var1;
      this.A = new dA(var2);
      this.kS = var3;
      this.wS = var4;
   }

   public com.pnfsoftware.jeb.corei.parsers.arm.rQ pC(long var1, int var3) {
      return this.pC(var1, var3, false, false);
   }

   private com.pnfsoftware.jeb.corei.parsers.arm.rQ pC(long var1, int var3, boolean var4, boolean var5) {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var6 = PU.pC(this.pC, var1, var3);
      if (!PU.gp(var6)) {
         return null;
      } else if (var6.ld() && !var5) {
         return null;
      } else {
         String var7 = var6.wS().pC();
         byte var8 = -1;
         switch (var7.hashCode()) {
            case 77487:
               if (var7.equals("NOP")) {
                  var8 = 0;
               }
            default:
               switch (var8) {
                  case 0:
                     if (var4) {
                        return null;
                     }

                     return var6;
                  default:
                     return this.A.pC(var1, var1 + 4L, var3) > 0 ? null : var6;
               }
         }
      }
   }

   public boolean pC(int var1, long var2) {
      if (var1 != 0) {
         return this.pC(var1, var2, true);
      } else if ((var2 & 3L) != 0L) {
         return this.pC(16, var2, true);
      } else {
         return this.pC(32, var2, false) ? true : this.pC(16, var2, true);
      }
   }

   private boolean pC(int var1, long var2, boolean var4) {
      byte var5 = 4;
      if (var1 != 64) {
         if ((var2 & 3L) != 0L) {
            var5 = 10;
         } else if (var1 != 16) {
            var1 = 32;
            var5 = 4;
         } else {
            var5 = 10;
         }
      }

      Pointer var6 = this.pC(var2, var1, var5 * 4, var4);
      return var6 != null;
   }

   public Pointer pC(long var1, int var3, int var4, boolean var5) {
      if ((var3 == 0 || var3 == 16) && (var1 & 1L) != 0L) {
         var1--;
         var3 = 16;
      }

      if (var3 != 0) {
         return this.pC(var1, var1, var3, var1, var4, false, var5);
      } else if ((var1 & 3L) != 0L) {
         return this.pC(var1, var1, 16, var1, var4, false, var5);
      } else {
         Pointer var6 = this.pC(var1, var1, 32, var1, var4, false, false);
         return var6 != null ? var6 : this.pC(var1, var1, 16, var1, var4, false, var5);
      }
   }

   public CodePointer pC(long var1, long var3, int var5, long var6, int var8, boolean var9) {
      Pointer var10 = this.pC(var1, var3, var5, var6, var8, var9, false);
      return var10 instanceof CodePointer ? (CodePointer)var10 : null;
   }

   public Pointer pC(long var1, long var3, int var5, long var6, int var8, boolean var9, boolean var10) {
      Long var11 = var1;
      int var12 = var5;
      boolean var13 = false;
      LinkedHashMap var14 = new LinkedHashMap();

      while (var11 < var3) {
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var15 = this.pC(var11, var3, var12, var13, false, this.kS && var11 == var1);
         if (var15 == null) {
            if (var1 >= var6) {
               return null;
            }

            var11 = var3;
            break;
         }

         var14.put(var11, var15);
         if (!this.pC(var11, var15)) {
            return null;
         }

         if (var15.wS().kS()) {
            var13 = true;
         }

         var11 = var11 + var15.getSize();
      }

      if (var11 != var3) {
         return null;
      } else {
         boolean var30 = false;
         boolean var16 = false;
         boolean var17 = false;
         ArrayList var18 = new ArrayList();
         ArrayList var19 = new ArrayList();
         LinkedHashMap var20 = new LinkedHashMap();
         boolean var21 = false;
         boolean var22 = false;

         do {
            if (var11 == null) {
               if (var18.isEmpty()) {
                  break;
               }

               var11 = (Long)((Couple)var18.get(0)).getFirst();
               var12 = (Integer)((Couple)var18.get(0)).getSecond();
               var30 = false;
               var18.remove(0);
               var16 = true;
            }

            if (!var14.containsKey(var11) && !var20.containsKey(var11)) {
               com.pnfsoftware.jeb.corei.parsers.arm.rQ var23 = this.A(
                  var11, var3, var12, var13, this.kS && var11 == var1, !this.kS || !var18.isEmpty() || !var19.isEmpty()
               );
               if (var23 == null) {
                  var22 = true;
                  break;
               }

               if (var16) {
                  var20.put(var11, var23);
               } else {
                  var14.put(var11, var23);
               }

               if (!this.pC(var11, var23)) {
                  var22 = true;
                  break;
               }

               if (var23.wS().kS()) {
                  var13 = true;
               }

               var8 -= var23.getSize();
               if (PU.A(var23)) {
                  if (var23.UT().E()) {
                     if (var9 || PU.ld(var23)) {
                        var11 = null;
                        continue;
                     }

                     if (this.A.kS(var11)) {
                        var11 = null;
                        continue;
                     }

                     if (var14.size() == 1) {
                        var17 = this.A(var11, var12) != null;
                     }
                  }

                  long var24 = var11 + var23.getSize();
                  IFlowInformation var26 = var23.getBreakingFlow(var11);
                  ArrayList var27 = var18;
                  if (!var26.isBroken() || var26.getTargets().size() <= 0) {
                     var26 = var23.getRoutineCall(var11);
                     var27 = var19;
                  }

                  if (var26.isBroken() && var26.getTargets().size() > 0) {
                     long var28 = ((ICodePointer)var26.getTargets().get(0)).getAddress();
                     if (var23.UT().E()) {
                        if (!((ICodePointer)var26.getTargets().get(0)).isUnknownAddress() && !aK.pC(this.pC, var28)) {
                           var11 = var28;
                           var12 = ((ICodePointer)var26.getTargets().get(0)).getMode();
                           var21 = true;
                           if (var23.pC().getBranchType() == IInsnEmulator.BranchType.CALL) {
                              var30 = true;
                           }
                        } else {
                           var11 = null;
                        }
                     } else {
                        var11 = var24;
                        if (!((ICodePointer)var26.getTargets().get(0)).isUnknownAddress() && !aK.pC(this.pC, var28) && !var30) {
                           var27.add(new Couple(((ICodePointer)var26.getTargets().get(0)).getAddress(), ((ICodePointer)var26.getTargets().get(0)).getMode()));
                        }
                     }
                  } else {
                     var11 = null;
                  }

                  if (var8 <= 0) {
                     break;
                  }
               } else {
                  if (var8 <= 0) {
                     break;
                  }

                  var11 = var11 + var23.getSize();
               }
            } else {
               var11 = null;
            }
         } while (var8 > 0);

         if (!var9 && !var17 && !this.pC(var14, var5, var1, var10)) {
            return null;
         } else if (var22) {
            return null;
         } else if (!aK.pC(var14, this.kS, this.wS)) {
            return var21 ? null : new Pointer(var1, var5, 1);
         } else {
            return new CodePointer(var1, var5);
         }
      }
   }

   private boolean pC(Map var1, int var2, long var3, boolean var5) {
      for (abn var8 : Vg.pC(this.pC, var1, var2)) {
         if (var1.isEmpty()) {
            List var12 = var8.A(var3);
            if (var12 != null) {
               if (var5) {
                  this.pC(var12);
               }

               return false;
            }
         } else {
            for (Entry var10 : var1.entrySet()) {
               List var11 = var8.A((Long)var10.getKey());
               if (var11 != null) {
                  if (var5) {
                     this.pC(var11);
                  }

                  return false;
               }
            }
         }
      }

      return true;
   }

   private void pC(List var1) {
      for (Pointer var3 : var1) {
         if (this.A != null) {
            this.A.pC(var3.getAddress());
         }

         if (var3.getType() == 2) {
            this.pC.enqueuePointerForAnalysis(var3);
         }
      }
   }

   public boolean pC(long var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var3) {
      SortedMap var4 = this.pC.getModel().getItemsInRange(var1, true, var1 + var3.getSize(), true);

      for (INativeContinuousItem var6 : var4.values()) {
         if (var6 instanceof INativeInstructionItem) {
            if (var6.getMemoryAddress() != var1) {
               return false;
            }

            if (((INativeInstructionItem)var6).getInstruction().getProcessorMode() != var3.getProcessorMode()) {
               return false;
            }
         } else if (var6.getName() != null) {
            return false;
         }
      }

      return true;
   }

   public com.pnfsoftware.jeb.corei.parsers.arm.rQ pC(long var1, long var3, int var5, boolean var6, boolean var7, boolean var8) {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var9 = this.pC(var1, var5, var8, false);
      if (var9 == null) {
         return null;
      } else if (var5 != 0 && var9.getProcessorMode() != var5) {
         return null;
      } else if (var9.pC().isPCUpdated()) {
         if (!aK.pC(this.pC, var1, var9)) {
            return null;
         } else {
            IFlowInformation var10 = var9.getBreakingFlow(var1);
            if (var10 != null && var10.isBroken()) {
               if ((var9.UT().sY() || var7) && (var10.getTargets().size() <= 2 || PU.UT(var9, true))) {
                  return var6 ? var9 : null;
               } else if (var9.getMnemonic().equals("CBZ") || var9.getMnemonic().equals("CBNZ")) {
                  return var9;
               } else {
                  return var5 != 64
                        && var9.UT().E()
                        && var10.getTargets().size() == 1
                        && ((ICodePointer)var10.getTargets().get(0)).getAddress() == var3
                        && var9.pC().pC()
                     ? var9
                     : null;
               }
            } else {
               return null;
            }
         }
      } else if (this.kS && !var6 && var9.UT().sY()) {
         return null;
      } else {
         return this.wS && this.pC(var9, var5, var1) ? null : var9;
      }
   }

   private boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, int var2, long var3) {
      return var1.wS().pC().equals("ADD")
         && var1.getCountOfOperands() == 3
         && LC.A(var1.pC(0), var2)
         && LC.A(var1.pC(1), var2)
         && var1.pC(2).getOperandType() == 1
         && var1.pC(2).getOperandValue(var3) > 0L
         && var1.pC(2).getOperandValue(var3) < (var2 == 64 ? 64 : 256);
   }

   public com.pnfsoftware.jeb.corei.parsers.arm.rQ A(long var1, long var3, int var5, boolean var6, boolean var7, boolean var8) {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var9 = this.pC(var1, var5, var7, var8);
      if (var9 == null) {
         return null;
      } else if (var5 != 0 && var9.getProcessorMode() != var5) {
         return null;
      } else {
         if (var9.pC().isPCUpdated()) {
            if (!aK.pC(this.pC, var1, var9)) {
               return null;
            }

            if (this.wS && var9.pC().getBranchType() == IInsnEmulator.BranchType.CALL) {
               com.pnfsoftware.jeb.corei.parsers.arm.rQ var10 = this.pC(var1 + var9.getSize(), var5);
               if (var10 != null && this.pC(var10, var5, var1 + var9.getSize())) {
                  com.pnfsoftware.jeb.corei.parsers.arm.rQ var11 = this.pC(var1 + var9.getSize() + var10.getSize(), var5);
                  if (var11 == null || !PU.sY(var11)) {
                     return null;
                  }
               }
            }
         }

         return this.kS && !var6 && var9.UT().sY() ? null : var9;
      }
   }

   public ZM.Av A(long var1, int var3) {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var4 = this.pC(var1, var3);
      if (PU.wS(var4)) {
         try {
            Long var5 = var4.pC().pC(var4, var1, null);
            if (var5 != null && var5 == (var1 + 4L & -4L)) {
               return var4.pC().pC(var3, var5) == var3 ? ZM.Av.pC : ZM.Av.A;
            }
         } catch (ProcessorException var6) {
         }
      }

      return null;
   }

   public static enum Av {
      pC,
      A;
   }
}
