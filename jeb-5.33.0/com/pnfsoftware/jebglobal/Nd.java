package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPattern;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPatternVerifier;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeAnalyzerUtil;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBinaryPattern;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.format.CharSequences;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Nd {
   private final INativeCodeAnalyzer pC;
   private final com.pnfsoftware.jeb.corei.parsers.arm.Av A;
   private final Cx kS;
   private final HL wS;
   private final ZM UT;
   private final BinaryPatternVerifier E;
   private final BinaryPatternVerifier sY;
   private final BinaryPatternVerifier ys;
   private final BinaryPatternVerifier ld;
   private final BinaryPatternVerifier gp;
   private final List oT = Arrays.asList("ADR", "B", "BX", "CMP", "CBZ", "CBNZ", "IT", "LDR", "MOV", "SUB");

   public Nd(INativeCodeAnalyzer var1, com.pnfsoftware.jeb.corei.parsers.arm.Av var2, BinaryPatternVerifier var3) {
      this.pC = var1;
      this.A = var2;
      this.E = var3;
      this.kS = new Cx(var1, new BinaryPatternVerifier());
      this.wS = new HL(var1);
      this.UT = new ZM(var1, var2, true, true);
      this.sY = new BinaryPatternVerifier();
      this.ys = new BinaryPatternVerifier();
      this.ld = new BinaryPatternVerifier();
      this.gp = new BinaryPatternVerifier();
      this.pC(var3, new byte[]{-23, 45, 0, 0}, new byte[]{-1, -1, -96, 0}, 0);
      this.pC(var3, new byte[]{-19, 45, 10, 0}, new byte[]{-1, -65, 14, 0}, 0);
      this.pC(var3, new byte[]{-27, 45, -32, 4}, new byte[]{-1, -1, -1, -1}, 32);
      this.pC(var3, new byte[]{-8, 77, -19, 4}, new byte[]{-1, -1, -1, -1}, 16);
      this.pC(new byte[]{-30, -113, 0, 0}, new byte[]{-1, -1, -64, 0}, 32);
      this.pC(new byte[]{-30, 79, 0, 0}, new byte[]{-1, -1, -64, 0}, 32);
      this.pC(new byte[]{-30, -113, -64, 0}, new byte[]{-1, -1, -16, 0}, 32);
      this.pC(new byte[]{-30, 79, -64, 0}, new byte[]{-1, -1, -16, 0}, 32);
      this.pC(new byte[]{-30, 77, -48, 0}, new byte[]{-1, -1, -16, 0}, 32);
      this.pC(new byte[]{-29, 80, 0, 0}, new byte[]{-1, -4, -16, 0}, 32);
      this.pC(new byte[]{-29, 112, 0, 0}, new byte[]{-1, -4, -16, 0}, 32);
      this.pC(new byte[]{-31, 80, 0, 0}, new byte[]{-1, -4, -16, 28}, 32);
      this.pC(new byte[]{-29, 48, 0, 0}, new byte[]{-1, -4, -16, 0}, 32);
      this.pC(new byte[]{-29, 16, 0, 0}, new byte[]{-1, -4, -16, 0}, 32);
      this.pC(new byte[]{-27, 31, 0, 0}, new byte[]{-1, 127, -64, 0}, 32);
      this.pC(new byte[]{-27, 31, -64, 0}, new byte[]{-1, 127, -16, 0}, 32);
      this.pC(new byte[]{-28, 64, 0, 0}, new byte[]{-1, -16, -64, 0}, 32);
      this.ys
         .addPatterns(
            this.pC(new byte[]{40, 0, 123, -32}, new byte[]{126, 64, 127, -32}, 4, 64),
            this.pC(new byte[]{40, 0, 3, -2}, new byte[]{126, 64, 3, -1}, 4, 64),
            this.pC(new byte[]{-43, 3, 36, 95}, new byte[]{-1, -1, -1, -1}, 4, 64),
            this.pC(new byte[]{-43, 3, 35, 31}, new byte[]{-1, -1, -1, -97}, 4, 64),
            this.pC(new byte[]{40, 0, 120, 29}, new byte[]{126, 64, 124, 31}, 4, 64)
         );
      this.ld.addPattern(this.pC(new byte[]{40, 0, 3, -32}, new byte[]{126, 64, 3, -32}, 4, 64));
      this.gp.addPatterns(this.pC(new byte[]{81, 0, 3, -1}, new byte[]{95, 0, 3, -1}, 4, 64));
      this.gp
         .addPatterns(
            this.pC(new byte[]{-72, 64, 4, 0}, new byte[]{-65, -32, 4, 0}, 4, 64),
            this.pC(new byte[]{-71, 64, 0, 0}, new byte[]{-65, -64, 0, 0}, 4, 64),
            this.pC(new byte[]{-72, 0, 4, 0}, new byte[]{-65, -32, 4, 0}, 4, 64),
            this.pC(new byte[]{-71, 0, 0, 0}, new byte[]{-65, -64, 0, 0}, 4, 64),
            this.pC(new byte[]{-112, 0, 0, 0}, new byte[]{-97, 0, 0, 0}, 4, 64),
            this.pC(new byte[]{113, 0, 0, 31}, new byte[]{127, 0, 2, 31}, 4, 64),
            this.pC(new byte[]{107, 0, 0, 31}, new byte[]{127, 56, 2, 31}, 4, 64),
            this.pC(new byte[]{52, 0, 0, 0}, new byte[]{126, 0, 0, 16}, 4, 64)
         );
   }

   private void pC(byte[] var1, byte[] var2, int var3) {
      this.pC(this.sY, var1, var2, var3);
   }

   private void pC(BinaryPatternVerifier var1, byte[] var2, byte[] var3, int var4) {
      if (var4 == 32) {
         var1.addPattern(this.pC(var2, var3, 4, 32));
      } else if (var4 == 16) {
         var1.addPattern(this.pC(var2, var3, 2, 16));
      } else if (var4 == 0) {
         if (((com.pnfsoftware.jeb.corei.parsers.arm.cq)this.pC.getProcessor()).pC().isLittle()) {
            var1.addPatterns(this.pC(var2, var3, 4, 32), this.pC(var2, var3, 2, 16));
         } else {
            var1.addPattern(this.pC(var2, var3, 4, 0));
         }
      }
   }

   private BinaryPattern pC(byte[] var1, byte[] var2, int var3, int var4) {
      return Iw.pC(((com.pnfsoftware.jeb.corei.parsers.arm.cq)this.pC.getProcessor()).pC(), var1, var2, var3, var4);
   }

   public CodePointer pC(long var1, int var3) {
      return this.pC(var1, var3, Nd.Av.pC);
   }

   public CodePointer pC(long var1, int var3, Nd.Av var4) {
      if (var3 == 64 || this.pC.getProcessor().getDefaultMode() == 64) {
         return var1 % 4L == 0L ? this.pC(var1, 64, true, false, var4) : null;
      } else if (var1 % 4L == 0L) {
         if (var3 == 0) {
            CodePointer var5 = this.pC(var1, 32, true, false, var4);
            return var5 != null ? var5 : this.pC(var1, true, var4);
         } else {
            return var3 == 16 ? this.pC(var1, true, var4) : this.pC(var1, 32, true, false, var4);
         }
      } else {
         if (var1 % 2L != 0L && this.pC.getProcessor().getDefaultMode() != 64) {
            var1--;
         }

         return var1 % 2L == 0L ? this.pC(var1, true, var4) : null;
      }
   }

   public CodePointer pC(long var1) {
      if (this.pC.getProcessor().getDefaultMode() == 64) {
         return var1 % 4L == 0L ? this.pC(var1, 64, true, true, Nd.Av.kS) : null;
      } else {
         return var1 % 4L == 0L ? this.pC(var1, 32, true, true, Nd.Av.kS) : null;
      }
   }

   public boolean pC(long var1, int var3, boolean var4) {
      BinaryPatternVerifier var5;
      if (var3 == 64) {
         var5 = var4 ? this.gp : this.ys;
      } else {
         var5 = var4 ? this.sY : this.E;
      }

      return CodeAnalyzerUtil.checkBinaryPattern(this.pC, var5, var1, var1 + 4L, 0, var3) != null;
   }

   private CodePointer pC(long var1, int var3, boolean var4, boolean var5, Nd.Av var6) {
      if (var3 == 0) {
         throw new IllegalArgumentException();
      } else {
         BinaryPatternVerifier var7;
         if (var3 == 64) {
            var7 = var5 ? this.gp : this.ys;
         } else {
            var7 = var5 ? this.sY : this.E;
         }

         INativeContinuousItem var8 = this.pC.getModel().getNextItem(var1);
         if (var7 != null) {
            int var9 = var4 ? 4 + (var3 == 64 ? 1 : 0) : 1;
            long var10 = var1;

            for (int var12 = 0; var12 < var9; var12++) {
               if (var8 != null
                  && var10 >= var8.getMemoryAddress()
                  && (var5 || !(var8 instanceof INativeInstructionItem) || ((INativeInstructionItem)var8).getInstruction().getProcessorMode() != var3)) {
                  return null;
               }

               IBinaryPattern var13 = CodeAnalyzerUtil.checkBinaryPattern(this.pC, var7, var10, var10 + 4L, 0, var3);
               if (var13 == null || var6 == Nd.Av.pC && !this.A(var10, var13.getProcessorMode())) {
                  if (var3 == 16) {
                     return null;
                  }

                  if (var3 == 64) {
                     var13 = CodeAnalyzerUtil.checkBinaryPattern(this.pC, this.ld, var10, var10 + 4L, 0, var3);
                     if (var13 != null) {
                        var9++;
                     }
                  }

                  var10 += 4L;
               } else {
                  if (this.pC.getProcessor().getMode() == 64 || var13.getProcessorMode() != 16) {
                     if (var6 == Nd.Av.A) {
                        return this.kS(var1, var10, var3);
                     }

                     return this.A(var1, var10, var3);
                  }

                  var10 += 4L;
               }
            }
         }

         return null;
      }
   }

   public CodePointer pC(long var1, boolean var3, Nd.Av var4) {
      byte var5 = 16;
      if (this.A(var1)) {
         return null;
      } else {
         INativeContinuousItem var6 = this.pC.getModel().getPreviousItem(var1);
         if (var6 == null || var6.getMemoryAddressEnd() <= var1 - 2L) {
            com.pnfsoftware.jeb.corei.parsers.arm.rQ var7 = PU.pC(this.pC, var1 - 2L, var5);
            if (PU.gp(var7) && var7.getSize() == 4) {
               return null;
            }
         }

         INativeContinuousItem var24 = this.pC.getModel().getNextItem(var1);
         int var8 = var3 ? 10 : 1;
         long var9 = var1;
         boolean var11 = false;
         int var12 = 0;
         HashSet var13 = new HashSet();
         ArrayList var14 = new ArrayList();

         for (int var15 = 0; var15 < var8; var15++) {
            if (var24 != null
               && var9 >= var24.getMemoryAddress()
               && (!(var24 instanceof INativeInstructionItem) || ((INativeInstructionItem)var24).getInstruction().getProcessorMode() != var5)) {
               return null;
            }

            IBinaryPattern var16 = CodeAnalyzerUtil.checkBinaryPattern(this.pC, this.E, var9, var9 + 4L, 0, var5);
            if (var16 != null && var16.getProcessorMode() != 32) {
               if (var4 == Nd.Av.A) {
                  return this.UT(var1, var9, var5);
               }

               if (var4 != Nd.Av.pC || this.A(var9, var16.getProcessorMode())) {
                  return this.wS(var1, var9, var5);
               }
            }

            com.pnfsoftware.jeb.corei.parsers.arm.rQ var17 = this.UT.pC(var9, var9 + 4L, var5, var11, var12 > 0, true);
            if (var17 == null) {
               var17 = this.UT.pC(var9, var5);
               if (var17 == null || !PU.ld(var17) || var14.isEmpty()) {
                  return null;
               }

               long var18 = var9 + var17.getSize();
               boolean var20 = false;

               for (Long var22 : var14) {
                  if (var22 >= var18 && var22 <= var18 + 4L) {
                     var20 = true;
                     var9 = var22;
                  }
               }

               if (!var20) {
                  return null;
               }
            } else {
               if (var17.wS().kS()) {
                  var11 = true;
               }

               if (var17.getCode().length == 2 && (var4 != Nd.Av.pC && (var17.kS()[0] & 254) == 180 || var4 == Nd.Av.pC && (var17.kS()[0] & 255) == 181)) {
                  for (String var19 : var13) {
                     if (!this.oT.contains(var19)) {
                        return null;
                     }
                  }

                  return this.wS(var1, var9, var5);
               }

               var13.add(var17.wS().pC());
               if (var12 > 0) {
                  var12--;
               }

               if (var17.wS().pC().equals("IT")) {
                  var12 = var17.getMnemonic().length() - 1;
               }

               if (var17.pC().isPCUpdated() && !var17.UT().E()) {
                  IFlowInformation var26 = var17.getBreakingFlow(var9);
                  if (var26.isBrokenKnown()) {
                     var14.add(((ICodePointer)var26.getTargets().get(0)).getAddress());
                  }
               }

               if (PU.wS(var17, false)) {
                  try {
                     var9 = var17.pC().pC(var17, var9, null);
                  } catch (ProcessorException var23) {
                     return null;
                  }
               } else {
                  var9 += var17.getSize();
                  if (this.A.wS(var9) || this.pC.getModel().isRoutineHeader(var9)) {
                     return null;
                  }
               }
            }
         }

         return null;
      }
   }

   public boolean A(long var1) {
      long var3 = var1 & -4L;

      for (int var5 = 0; var5 < 4; var5++) {
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var6 = this.UT.A(var3 + 4 * var5, var3 + 4 * var5, 32, true, false, true);
         if (var6 == null) {
            return false;
         }

         if (!CharSequences.isBlank(var6.wS().wS())) {
            return false;
         }

         if (var6.pC().isPCUpdated()) {
            break;
         }
      }

      return true;
   }

   private CodePointer A(long var1, long var3, int var5) {
      return this.UT.pC(var1, var3, var5, var1, 32, true);
   }

   private CodePointer kS(long var1, long var3, int var5) {
      return this.UT.pC(var1, var3, var5, var1, 0, true);
   }

   private CodePointer wS(long var1, long var3, int var5) {
      return this.UT.pC(var1, var3, var5, var3, 128, false);
   }

   private CodePointer UT(long var1, long var3, int var5) {
      return this.UT.pC(var1, var3, var5, var3, 0, false);
   }

   private boolean A(long var1, int var3) {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var4 = this.UT.pC(var1, var3);
      if (var4 == null) {
         return false;
      } else {
         if (var4.wS().pC().equals("PUSH")) {
            Uw var5 = (Uw)var4.pC(0);
            boolean var6 = false;
            boolean var7 = false;
            boolean var8 = false;

            for (Yg var12 : var5.E()) {
               int var13 = RegisterUtil.getRegisterIndex(var12.getOperandValue(var1));
               if (var13 < 4) {
                  var6 = true;
               } else if (var13 < 13) {
                  var7 = true;
               } else if (var13 == 14) {
                  var8 = true;
               }
            }

            if (var6 && !var7) {
               return false;
            }

            if (var8 && !var6 && !var7) {
               return false;
            }
         }

         return true;
      }
   }

   public int pC(long var1, long var3, int var5) {
      return this.kS.pC(var1, var3, var5);
   }

   public boolean pC(INativeMethodItem var1) {
      return this.A(var1) || this.kS(var1);
   }

   public boolean A(INativeMethodItem var1) {
      return !ph.kS(var1.getName()) || this.pC().pC(var1, false) != null;
   }

   public boolean kS(INativeMethodItem var1) {
      return this.pC(var1.getMemoryAddress(), var1.getData().getCFG().getInstruction(var1.getMemoryAddress()).getProcessorMode(), Nd.Av.A) != null;
   }

   public HL pC() {
      return this.wS;
   }

   public static enum Av {
      pC,
      A,
      kS;
   }
}
