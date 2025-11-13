package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.SwitchInformation;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ctk {
   protected static final ILogger q = GlobalLog.getLogger(ctk.class);
   protected final ctk.eo RF;
   protected final INativeCodeAnalyzer xK;
   protected long Dw;
   protected final boolean Uv;
   protected boolean oW;
   protected boolean gO;
   protected boolean nf;
   protected Map gP = new HashMap();

   public ctk(INativeCodeAnalyzer var1, ctk.eo var2) {
      this.xK = var1;
      this.Dw = ((aae)var1).Dw().io();
      this.Uv = var1.getProcessor().getType().is64Bit();
      this.RF = var2;
   }

   public boolean q(long var1, ctc var3, IBasicBlockSkeleton var4) {
      if (var3.getMnemonic().equals("jmp")) {
         cqj var5 = var3.Dw()[0];
         if (var5.RF()) {
            ctd var6 = (ctd)var5;
            if (var6.getMemoryScale() == (this.Uv ? 8 : 4) && var6.getMemoryIndexRegister() != -1L && var6.getMemoryDisplacement() != 0L) {
               this.gP.put(var1, new Couple(var6.getMemoryDisplacement(), var6.getMemoryIndexRegister()));
               return true;
            }
         }
      }

      return false;
   }

   public SwitchInformation q(long var1, IBasicBlockSkeleton var3, List var4) {
      Couple var5 = (Couple)this.gP.get(var1);
      if (var5 == null) {
         return null;
      } else {
         long var6 = (Long)var5.getFirst();
         long var8 = (Long)var5.getSecond();
         int var10 = this.Uv && !this.oW && !this.gO ? 8 : 4;
         MemoryRanges var11 = ((aae)this.xK).q();
         if (var11 == null) {
            return null;
         } else if (!this.xK.getAnalysisRanges().contains(var6)) {
            return null;
         } else {
            Couple var12 = this.q(var3, var8);
            boolean var13 = var12 != null;
            long var14 = var13 ? (Long)var12.getFirst() : -1L;
            long var16 = var13 ? (Long)var12.getSecond() : -1L;
            long var18 = -1L;
            long var20 = -1L;
            long var22 = this.q(var3, var4, var13 ? var16 : var8);
            long var24 = this.RF(var3, var4, var13 ? var16 : var8);
            if (var13) {
               var20 = var22;
               var14 += var24;
            } else {
               var18 = var22;
               var6 += var24 * var10;
            }

            if (this.RF == ctk.eo.xK) {
               if (var13 && var20 <= 0L) {
                  Couple var26 = this.q(var14, 1, var6 > var14 ? var6 : -1L, false, false);
                  if (var26 == null) {
                     return null;
                  }

                  var20 = (Long)var26.getSecond();
               }

               if (!var13 && var18 <= 0L) {
                  Couple var37 = this.q(var6, var10, var14 > var6 ? var14 : -1L, true, false);
                  if (var37 == null || (Long)var37.getSecond() == 1L) {
                     var37 = this.q(var6, var10, var14 > var6 ? var14 : -1L, true, true);
                     if (var37 == null) {
                        return null;
                     }
                  }

                  var6 = (Long)var37.getFirst();
                  var18 = (Long)var37.getSecond();
               }
            }

            SwitchInformation var38 = new SwitchInformation();
            if (var13) {
               if (var20 <= 0L) {
                  return null;
               }

               SwitchInformation.JumpTableInformation var27 = new SwitchInformation.JumpTableInformation(var14, 1, true);
               long var28 = 0L;
               long var30 = var14;

               for (int var32 = 0; var32 < var20; var32++) {
                  Long var33;
                  try {
                     var33 = this.xK.getMemory().readByte(var30) & 255L;
                  } catch (MemoryException var36) {
                     return null;
                  }

                  if (var33 > var28) {
                     var28 = var33;
                  }

                  SwitchInformation.SwitchCaseInformation var34 = new SwitchInformation.SwitchCaseInformation();
                  var34.setJumpTableEntryAddress(var30);
                  var34.setJumpTableEntrySize(1);
                  var38.addCase(var34);
                  var30++;
               }

               var27.setEndAddress(var30);
               var38.addJumpTable(var27);
               var18 = var28 + 1L;
            }

            if (var18 <= 0L) {
               return null;
            } else {
               SwitchInformation.JumpTableInformation var39 = new SwitchInformation.JumpTableInformation(var6, var10);
               long var40 = var6;

               for (int var41 = 0; var41 < var18; var41++) {
                  Long var31;
                  try {
                     if (this.nf) {
                        var31 = (long)this.xK.getMemory().readInt(var40);
                     } else {
                        var31 = this.xK.getMemory().readInt(var40) & 4294967295L;
                     }

                     if (this.oW) {
                        var31 = var31 + this.Dw & MathUtil.makeMask(this.xK.getProcessor().getMode());
                     } else if (this.gO) {
                        var31 = var31 + var6 & MathUtil.makeMask(this.xK.getProcessor().getMode());
                     }
                  } catch (MemoryException var35) {
                     return null;
                  }

                  if (!var11.contains(var31)) {
                     return null;
                  }

                  SwitchInformation.SwitchCaseInformation var42 = new SwitchInformation.SwitchCaseInformation();
                  var42.setCaseHandler(this.xK.getProcessor().createEntryPoint(var31, this.xK.getProcessor().getDefaultMode()));
                  var42.setJumpTableEntryAddress(var40);
                  var42.setJumpTableEntrySize(var10);
                  var38.addCase(var42);
                  var40 += var10;
               }

               var39.setEndAddress(var40);
               var38.addJumpTable(var39);
               return var38;
            }
         }
      }
   }

   private Couple q(long var1, int var3, long var4, boolean var6, boolean var7) {
      Assert.a(var3 == 1 || var3 == 4 || var3 == 8);
      long var8 = 0L;
      long var10 = 0L;
      long var12 = ((aae)this.xK).Dw().io();
      if (this.xK.getContainer() != null) {
         ISegmentInformation var14 = CodeObjectUnitUtil.findSectionByRelativeAddress(this.xK.getContainer(), var1 - var12);
         if (var14 != null) {
            var8 = var14.getOffsetInMemory() + var12;
            var10 = var8 + var14.getSizeInMemory();
         }

         ISegmentInformation var15 = CodeObjectUnitUtil.findSegmentByRelativeAddress(this.xK.getContainer(), var1 - var12);
         if (var15 != null) {
            var8 = var15.getOffsetInMemory() + var12;
            var10 = var8 + var15.getSizeInMemory();
         }
      }

      if (var8 == 0L && var10 == 0L && ((aae)this.xK).q() != null) {
         var8 = ((aae)this.xK).q().getLocalBegin(var1);
         var10 = ((aae)this.xK).q().getLocalEnd(var1);
      }

      if (var8 == 0L && var10 == 0L) {
         return null;
      } else {
         long var25 = var1;
         long var16 = var1;
         int var18 = 0;
         ArrayList var19 = new ArrayList();

         try {
            while (true) {
               long var20;

               var20 = switch (var3) {
                  case 1 -> this.xK.getMemory().readByte(var25) & 255L;
                  case 4 -> {
                     yield this.xK.getMemory().readInt(var25) & 4294967295L;
                     if (this.oW) {
                        yield var20 + this.Dw & MathUtil.makeMask(var3 * 8);
                     } else if (this.gO) {
                        yield var20 + var1 & MathUtil.makeMask(var3 * 8);
                     }
                  }
                  case 8 -> this.xK.getMemory().readLong(var25);
                  default -> throw new JebRuntimeException("invalid entry size");
               };

               boolean var22 = true;
               if (var3 != 4 && var3 != 8) {
                  if (var3 == 1 && (byte)var20 < 0) {
                     var22 = false;
                  }
               } else if (var20 < var8 || var20 > var10) {
                  var22 = false;
               } else if (!var19.isEmpty() && Math.abs((Long)var19.get(0) - var20) > 65536L) {
                  var22 = false;
               }

               if (var22) {
                  ICodePointer var23 = (ICodePointer)this.xK.getAnalyzerExtensionsManager().getPrologueLooking(var25, var25 + 8L).getResult();
                  if (var23 != null && var23.getAddress() == var25) {
                     var22 = false;
                  }
               }

               if (!var22 && var19.isEmpty() && var6) {
                  if (var18 >= 4) {
                     break;
                  }

                  var18++;
                  var25 = var7 ? var25 - var3 : var25 + var3;
               } else {
                  if (!var22) {
                     break;
                  }

                  if (var18 != 0 && var19.isEmpty() || var7) {
                     var16 = var25;
                  }

                  var19.add(var20);
                  var25 = var7 ? var25 - var3 : var25 + var3;
                  if (this.xK.getModel().getItemAt(var25) != null
                     || !this.xK.getModel().getReferenceManager().getReferencesTo(var25).isEmpty()
                     || var19.contains(var25)
                     || var4 != -1L && var25 >= var4) {
                     break;
                  }
               }
            }
         } catch (MemoryException var24) {
            return null;
         }

         return !var19.isEmpty() ? new Couple(var16, var19.size() & 4294967295L) : null;
      }
   }

   public abstract long q(IBasicBlockSkeleton var1, List var2, long var3);

   public long RF(IBasicBlockSkeleton var1, List var2, long var3) {
      return 0L;
   }

   public abstract Couple q(IBasicBlockSkeleton var1, long var2);

   @Ser
   public static enum eo {
      q,
      RF,
      xK;
   }
}
