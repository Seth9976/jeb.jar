package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import java.util.Arrays;
import java.util.List;

public class HL {
   private static final byte[] pC = new byte[]{-42, 31, 2, 32};
   private static final byte[] A = new byte[]{71, 120};
   private INativeCodeAnalyzer kS;

   public HL(INativeCodeAnalyzer var1) {
      this.kS = var1;
   }

   public boolean pC(BasicBlock var1) {
      return var1.size() == 1
         && ((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(0)).getProcessorMode() == 16
         && ArrayUtil.compareBytes(((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(0)).kS(), 0, A, 0, 2) == 0;
   }

   public CodePointer pC(INativeMethodItem var1, boolean var2) {
      BasicBlock var3 = var1.getData().getCFG().getEntryBlock();
      return this.pC(var3, false, var2);
   }

   public CodePointer A(INativeMethodItem var1, boolean var2) {
      BasicBlock var3 = var1.getData().getCFG().getEntryBlock();
      return this.pC(var3, true, var2);
   }

   public CodePointer pC(BasicBlock var1, boolean var2, boolean var3) {
      boolean var4 = var2;
      int var5 = ((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(0)).getProcessorMode();
      if (var5 == 64) {
         return this.kS(var1);
      } else {
         if (this.pC(var1) && var1.alloutsize() <= 1) {
            List var6 = var1.getAllOutputBlocks();
            if (var6.size() == 1) {
               var1 = (BasicBlock)var6.get(0);
               var4 = true;
            } else if (var6.size() == 0) {
               return var2 ? new CodePointer(var1.getBase() + 4L, 32) : null;
            }
         }

         return this.A(var1, var4, var3);
      }
   }

   public CodePointer pC(BasicBlock var1, boolean var2) {
      return this.A(var1, var2, false);
   }

   public CodePointer A(BasicBlock var1, boolean var2, boolean var3) {
      long var4 = this.A(var1);
      if (var4 != -1L) {
         return new CodePointer(var4, ((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(0)).getProcessorMode());
      } else {
         if (var2) {
            ICodePointer var6 = A(var1, var3);
            if (var6 != null) {
               return new CodePointer(var6);
            }
         }

         return null;
      }
   }

   public long A(BasicBlock var1) {
      long var2 = UT(var1);
      if (var2 != -1L) {
         return var2;
      } else {
         var2 = this.E(var1);
         if (var2 != -1L) {
            return var2;
         } else {
            var2 = this.sY(var1);
            return var2 != -1L ? var2 : -1L;
         }
      }
   }

   private static long UT(BasicBlock var0) {
      if (var0.size() < 3) {
         return -1L;
      } else {
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var1 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var0.get(0);
         byte[] var2 = var1.kS();
         if (var2.length == 4 && var2[0] == -30 && var2[1] == -113 && (byte)(var2[2] & 240) == -64) {
            int var3 = Gq.A(var2[2] & 15, var2[3] & 255) + (int)var0.getFirstAddress() + 8;
            var1 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var0.get(1);
            var2 = var1.kS();
            if (var2.length == 4 && var2[0] == -30 && var2[1] == -116 && (byte)(var2[2] & 240) == -64) {
               int var4 = Gq.A(var2[2] & 15, var2[3] & 255);
               var1 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var0.get(2);
               var2 = var1.kS();
               if (var2.length == 4 && var2[0] == -27 && var2[1] == -68 && (byte)(var2[2] & 240) == -16) {
                  int var5 = (var2[2] & 15) << 8 | var2[3] & 255;
                  int var6 = var4 + var3 + var5;
                  return var6;
               } else {
                  return -1L;
               }
            } else {
               return -1L;
            }
         } else {
            return -1L;
         }
      }
   }

   private long E(BasicBlock var1) {
      if (var1.size() != 2) {
         return -1L;
      } else {
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var2 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(0);
         byte[] var3 = var2.kS();
         if (var3.length == 4 && var3[0] == -27 && var3[1] == -97 && var3[2] == -64 && var3[3] == 0) {
            com.pnfsoftware.jeb.corei.parsers.arm.rQ var4 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(1);
            byte[] var5 = var4.kS();
            if (var5.length == 4 && var5[0] == -32 && var5[1] == -116 && var5[2] == -16 && var5[3] == 15) {
               int var6;
               try {
                  var6 = this.kS.getMemory().readInt(var1.getEndAddress());
               } catch (MemoryException var8) {
                  return -1L;
               }

               if (var6 != -1) {
                  int var7 = var6 + (int)var1.getEndAddress() + 4;
                  return var7 & 4294967295L;
               } else {
                  return -1L;
               }
            } else {
               return -1L;
            }
         } else {
            return -1L;
         }
      }
   }

   private long sY(BasicBlock var1) {
      if (var1.size() != 3) {
         return -1L;
      } else {
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var2 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(0);
         byte[] var3 = var2.kS();
         if (var3.length == 4 && var3[0] == -27 && var3[1] == -97 && var3[2] == -64 && var3[3] == 4) {
            com.pnfsoftware.jeb.corei.parsers.arm.rQ var4 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(1);
            byte[] var5 = var4.kS();
            if (var5.length == 4 && var5[0] == -32 && var5[1] == -113 && var5[2] == -64 && var5[3] == 12) {
               com.pnfsoftware.jeb.corei.parsers.arm.rQ var6 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(2);
               byte[] var7 = var6.kS();
               if (var7.length == 4 && var7[0] == -31 && var7[1] == 47 && var7[2] == -1 && var7[3] == 28) {
                  int var8;
                  try {
                     var8 = this.kS.getMemory().readInt(var1.getEndAddress()) & -2;
                  } catch (MemoryException var10) {
                     return -1L;
                  }

                  if (var8 != -1) {
                     int var9 = var8 + (int)var1.getEndAddress();
                     return var9 & 4294967295L;
                  } else {
                     return -1L;
                  }
               } else {
                  return -1L;
               }
            } else {
               return -1L;
            }
         } else {
            return -1L;
         }
      }
   }

   private static ICodePointer A(BasicBlock var0, boolean var1) {
      long var3 = var0.getFirstAddress();
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var2;
      if (!var1) {
         if (var0.size() != 1) {
            return null;
         }

         var2 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var0.get(0);
      } else {
         if (var0.size() > 5) {
            return null;
         }

         var2 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var0.getLast();
         var3 = var0.getLastAddress();
      }

      if (var2.wS().pC().equalsIgnoreCase("B") && var2.UT().E()) {
         IFlowInformation var5 = var2.getBreakingFlow(var3);
         if (var5.isBroken() && !var5.getTargets().isEmpty()) {
            return (ICodePointer)var5.getTargets().get(0);
         }
      }

      return null;
   }

   public CodePointer kS(BasicBlock var1) {
      CodePointer var2 = this.wS(var1);
      return var2 != null ? var2 : ys(var1);
   }

   public CodePointer wS(BasicBlock var1) {
      if (var1.size() < 4) {
         return null;
      } else {
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var2 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(0);
         byte[] var3 = var2.kS();
         if ((var3[0] & 159) == 144 && (var3[3] & 31) == 16) {
            long var4 = var1.getFirstAddress() + (((var3[1] & 255) << 13 | (var3[2] & 255) << 5 | (var3[3] & 224) >>> 3 | (var3[0] & 96) >>> 5) << 12) & -4096L;
            var2 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(1);
            var3 = var2.kS();
            if (var3[0] == -7 && (var3[1] & 192) == 64 && (var3[2] & 3) == 2 && var3[3] == 17) {
               int var6 = ((var3[1] & 63) << 6 | (var3[2] & 252) >>> 2) * 8;
               var2 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(2);
               var3 = var2.kS();
               if (var3[0] == -111 && (var3[1] & 192) == 0 && (var3[2] & 3) == 2 && var3[3] == 16) {
                  if (var6 != ((var3[1] & 63) << 6 | (var3[2] & 252) >>> 2)) {
                     return null;
                  } else {
                     var2 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(3);
                     var3 = var2.kS();
                     return !Arrays.equals(var3, pC) ? null : new CodePointer(var4 + var6, 64);
                  }
               } else {
                  return null;
               }
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   private static CodePointer ys(BasicBlock var0) {
      if (var0.size() < 4) {
         return null;
      } else {
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var1 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var0.get(0);
         byte[] var2 = var1.kS();
         if ((var2[0] & 159) != 144) {
            return null;
         } else {
            int var3 = var2[3] & 31;
            long var4 = var0.getFirstAddress() + (((var2[1] & 255) << 13 | (var2[2] & 255) << 5 | (var2[3] & 224) >>> 3 | (var2[0] & 96) >>> 5) << 12) & -4096L;
            var1 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var0.get(1);
            var2 = var1.kS();
            int var6 = var3 >>> 3;
            int var7 = (var3 << 5 | var3) & 0xFF;
            if (var2[0] == -111 && (var2[1] & 192) == 0 && (var2[2] & 3) == var6 && var2[3] == var7) {
               int var8 = (var2[1] & 63) << 6 | (var2[2] & 252) >>> 2;
               var6 = var3 >>> 3;
               var7 = var3 << 5 & 224;
               var1 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var0.get(2);
               var2 = var1.kS();
               if (var2[0] == -7 && (var2[1] & 192) == 64 && (var2[2] & 3) == var6 && (var2[3] & 224) == var7) {
                  int var9 = ((var2[1] & 63) << 6 | (var2[2] & 252) >>> 2) * 8;
                  int var10 = var2[3] & 31;
                  var1 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var0.get(3);
                  var2 = var1.kS();
                  var6 = var10 >>> 3;
                  var7 = var10 << 5 & 224;
                  return var2[0] == -42 && var2[1] == 31 && var2[2] == var6 && var2[3] == var7 ? new CodePointer(var4 + var8 + var9, 64) : null;
               } else {
                  return null;
               }
            } else {
               return null;
            }
         }
      }
   }
}
