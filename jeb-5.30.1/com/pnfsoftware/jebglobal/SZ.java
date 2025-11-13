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

public class SZ {
   private INativeCodeAnalyzer q;
   private hi RF;
   private boolean xK;
   private boolean Dw;

   public SZ(INativeCodeAnalyzer var1, FS var2, boolean var3, boolean var4) {
      this.q = var1;
      this.RF = new hi(var2);
      this.xK = var3;
      this.Dw = var4;
   }

   public fA q(long var1, int var3) {
      return this.q(var1, var3, false, false);
   }

   private fA q(long var1, int var3, boolean var4, boolean var5) {
      fA var6 = OC.q(this.q, var1, var3);
      if (!OC.gP(var6)) {
         return null;
      } else if (var6.gP() && !var5) {
         return null;
      } else {
         String var7 = var6.Dw().q();
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
                     return this.RF.q(var1, var1 + 4L, var3) > 0 ? null : var6;
               }
         }
      }
   }

   public boolean q(int var1, long var2) {
      if (var1 != 0) {
         return this.q(var1, var2, true);
      } else if ((var2 & 3L) != 0L) {
         return this.q(16, var2, true);
      } else {
         return this.q(32, var2, false) ? true : this.q(16, var2, true);
      }
   }

   private boolean q(int var1, long var2, boolean var4) {
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

      Pointer var6 = this.q(var2, var1, var5 * 4, var4);
      return var6 != null;
   }

   public Pointer q(long var1, int var3, int var4, boolean var5) {
      if ((var3 == 0 || var3 == 16) && (var1 & 1L) != 0L) {
         var1--;
         var3 = 16;
      }

      if (var3 != 0) {
         return this.q(var1, var1, var3, var1, var4, false, var5);
      } else if ((var1 & 3L) != 0L) {
         return this.q(var1, var1, 16, var1, var4, false, var5);
      } else {
         Pointer var6 = this.q(var1, var1, 32, var1, var4, false, false);
         return var6 != null ? var6 : this.q(var1, var1, 16, var1, var4, false, var5);
      }
   }

   public CodePointer q(long var1, long var3, int var5, long var6, int var8, boolean var9) {
      Pointer var10 = this.q(var1, var3, var5, var6, var8, var9, false);
      return var10 instanceof CodePointer ? (CodePointer)var10 : null;
   }

   public Pointer q(long var1, long var3, int var5, long var6, int var8, boolean var9, boolean var10) {
      Long var11 = var1;
      int var12 = var5;
      boolean var13 = false;
      LinkedHashMap var14 = new LinkedHashMap();

      while (var11 < var3) {
         fA var15 = this.q(var11, var3, var12, var13, false, this.xK && var11 == var1);
         if (var15 == null) {
            if (var1 >= var6) {
               return null;
            }

            var11 = var3;
            break;
         }

         var14.put(var11, var15);
         if (!this.q(var11, var15)) {
            return null;
         }

         if (var15.Dw().xK()) {
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
               fA var23 = this.RF(var11, var3, var12, var13, this.xK && var11 == var1, !this.xK || !var18.isEmpty() || !var19.isEmpty());
               if (var23 == null) {
                  var22 = true;
                  break;
               }

               if (var16) {
                  var20.put(var11, var23);
               } else {
                  var14.put(var11, var23);
               }

               if (!this.q(var11, var23)) {
                  var22 = true;
                  break;
               }

               if (var23.Dw().xK()) {
                  var13 = true;
               }

               var8 -= var23.getSize();
               if (OC.RF(var23)) {
                  if (var23.Uv().oW()) {
                     if (var9 || OC.nf(var23)) {
                        var11 = null;
                        continue;
                     }

                     if (this.RF.Uv(var11)) {
                        var11 = null;
                        continue;
                     }

                     if (var14.size() == 1) {
                        var17 = this.RF(var11, var12) != null;
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
                     if (var23.Uv().oW()) {
                        if (!((ICodePointer)var26.getTargets().get(0)).isUnknownAddress() && !BN.q(this.q, var28)) {
                           var11 = var28;
                           var12 = ((ICodePointer)var26.getTargets().get(0)).getMode();
                           var21 = true;
                           if (var23.q().getBranchType() == IInsnEmulator.BranchType.CALL) {
                              var30 = true;
                           }
                        } else {
                           var11 = null;
                        }
                     } else {
                        var11 = var24;
                        if (!((ICodePointer)var26.getTargets().get(0)).isUnknownAddress() && !BN.q(this.q, var28) && !var30) {
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

         if (!var9 && !var17 && !this.q(var14, var5, var1, var10)) {
            return null;
         } else if (var22) {
            return null;
         } else if (!BN.q(var14, this.xK, this.Dw)) {
            return var21 ? null : new Pointer(var1, var5, 1);
         } else {
            return new CodePointer(var1, var5);
         }
      }
   }

   private boolean q(Map var1, int var2, long var3, boolean var5) {
      for (adf var8 : Ij.q(this.q, var1, var2)) {
         if (var1.isEmpty()) {
            List var12 = var8.RF(var3);
            if (var12 != null) {
               if (var5) {
                  this.q(var12);
               }

               return false;
            }
         } else {
            for (Entry var10 : var1.entrySet()) {
               List var11 = var8.RF((Long)var10.getKey());
               if (var11 != null) {
                  if (var5) {
                     this.q(var11);
                  }

                  return false;
               }
            }
         }
      }

      return true;
   }

   private void q(List var1) {
      for (Pointer var3 : var1) {
         if (this.RF != null) {
            this.RF.q(var3.getAddress());
         }

         if (var3.getType() == 2) {
            this.q.enqueuePointerForAnalysis(var3);
         }
      }
   }

   public boolean q(long var1, fA var3) {
      SortedMap var4 = this.q.getModel().getItemsInRange(var1, true, var1 + var3.getSize(), true);

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

   public fA q(long var1, long var3, int var5, boolean var6, boolean var7, boolean var8) {
      fA var9 = this.q(var1, var5, var8, false);
      if (var9 == null) {
         return null;
      } else if (var5 != 0 && var9.getProcessorMode() != var5) {
         return null;
      } else if (var9.q().isPCUpdated()) {
         if (!BN.q(this.q, var1, var9)) {
            return null;
         } else {
            IFlowInformation var10 = var9.getBreakingFlow(var1);
            if (var10 != null && var10.isBroken()) {
               if ((var9.Uv().gO() || var7) && (var10.getTargets().size() <= 2 || OC.Uv(var9, true))) {
                  return var6 ? var9 : null;
               } else if (var9.getMnemonic().equals("CBZ") || var9.getMnemonic().equals("CBNZ")) {
                  return var9;
               } else {
                  return var5 != 64
                        && var9.Uv().oW()
                        && var10.getTargets().size() == 1
                        && ((ICodePointer)var10.getTargets().get(0)).getAddress() == var3
                        && var9.q().q()
                     ? var9
                     : null;
               }
            } else {
               return null;
            }
         }
      } else if (this.xK && !var6 && var9.Uv().gO()) {
         return null;
      } else {
         return this.Dw && this.q(var9, var5, var1) ? null : var9;
      }
   }

   private boolean q(fA var1, int var2, long var3) {
      return var1.Dw().q().equals("ADD")
         && var1.getCountOfOperands() == 3
         && GC.RF(var1.q(0), var2)
         && GC.RF(var1.q(1), var2)
         && var1.q(2).getOperandType() == 1
         && var1.q(2).getOperandValue(var3) > 0L
         && var1.q(2).getOperandValue(var3) < (var2 == 64 ? 64 : 256);
   }

   public fA RF(long var1, long var3, int var5, boolean var6, boolean var7, boolean var8) {
      fA var9 = this.q(var1, var5, var7, var8);
      if (var9 == null) {
         return null;
      } else if (var5 != 0 && var9.getProcessorMode() != var5) {
         return null;
      } else {
         if (var9.q().isPCUpdated()) {
            if (!BN.q(this.q, var1, var9)) {
               return null;
            }

            if (this.Dw && var9.q().getBranchType() == IInsnEmulator.BranchType.CALL) {
               fA var10 = this.q(var1 + var9.getSize(), var5);
               if (var10 != null && this.q(var10, var5, var1 + var9.getSize())) {
                  fA var11 = this.q(var1 + var9.getSize() + var10.getSize(), var5);
                  if (var11 == null || !OC.gO(var11)) {
                     return null;
                  }
               }
            }
         }

         return this.xK && !var6 && var9.Uv().gO() ? null : var9;
      }
   }

   public SZ.eo RF(long var1, int var3) {
      fA var4 = this.q(var1, var3);
      if (OC.Dw(var4)) {
         try {
            Long var5 = var4.q().q(var4, var1, null);
            if (var5 != null && var5 == (var1 + 4L & -4L)) {
               return var4.q().q(var3, var5) == var3 ? SZ.eo.q : SZ.eo.RF;
            }
         } catch (ProcessorException var6) {
         }
      }

      return null;
   }

   public static enum eo {
      q,
      RF;
   }
}
