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

public class Jc {
   private final INativeCodeAnalyzer q;
   private final FS RF;
   private final rl xK;
   private final hP Dw;
   private final SZ Uv;
   private final BinaryPatternVerifier oW;
   private final BinaryPatternVerifier gO;
   private final BinaryPatternVerifier nf;
   private final BinaryPatternVerifier gP;
   private final BinaryPatternVerifier za;
   private final List lm = Arrays.asList("ADR", "B", "BX", "CMP", "CBZ", "CBNZ", "IT", "LDR", "MOV", "SUB");

   public Jc(INativeCodeAnalyzer var1, FS var2) {
      this(var1, var2, new BinaryPatternVerifier());
   }

   public Jc(INativeCodeAnalyzer var1, FS var2, BinaryPatternVerifier var3) {
      this.q = var1;
      this.RF = var2;
      this.oW = var3;
      this.xK = new rl(var1, new BinaryPatternVerifier());
      this.Dw = new hP(var1);
      this.Uv = new SZ(var1, var2, true, true);
      this.gO = new BinaryPatternVerifier();
      this.nf = new BinaryPatternVerifier();
      this.gP = new BinaryPatternVerifier();
      this.za = new BinaryPatternVerifier();
      this.q(var3, new byte[]{-23, 45, 0, 0}, new byte[]{-1, -1, -96, 0}, 0);
      this.q(var3, new byte[]{-19, 45, 10, 0}, new byte[]{-1, -65, 14, 0}, 0);
      this.q(var3, new byte[]{-27, 45, -32, 4}, new byte[]{-1, -1, -1, -1}, 32);
      this.q(var3, new byte[]{-8, 77, -19, 4}, new byte[]{-1, -1, -1, -1}, 16);
      this.q(new byte[]{-30, -113, 0, 0}, new byte[]{-1, -1, -64, 0}, 32);
      this.q(new byte[]{-30, 79, 0, 0}, new byte[]{-1, -1, -64, 0}, 32);
      this.q(new byte[]{-30, -113, -64, 0}, new byte[]{-1, -1, -16, 0}, 32);
      this.q(new byte[]{-30, 79, -64, 0}, new byte[]{-1, -1, -16, 0}, 32);
      this.q(new byte[]{-30, 77, -48, 0}, new byte[]{-1, -1, -16, 0}, 32);
      this.q(new byte[]{-29, 80, 0, 0}, new byte[]{-1, -4, -16, 0}, 32);
      this.q(new byte[]{-29, 112, 0, 0}, new byte[]{-1, -4, -16, 0}, 32);
      this.q(new byte[]{-31, 80, 0, 0}, new byte[]{-1, -4, -16, 28}, 32);
      this.q(new byte[]{-29, 48, 0, 0}, new byte[]{-1, -4, -16, 0}, 32);
      this.q(new byte[]{-29, 16, 0, 0}, new byte[]{-1, -4, -16, 0}, 32);
      this.q(new byte[]{-27, 31, 0, 0}, new byte[]{-1, 127, -64, 0}, 32);
      this.q(new byte[]{-27, 31, -64, 0}, new byte[]{-1, 127, -16, 0}, 32);
      this.q(new byte[]{-28, 64, 0, 0}, new byte[]{-1, -16, -64, 0}, 32);
      this.nf
         .addPatterns(
            this.q(new byte[]{40, 0, 123, -32}, new byte[]{126, 64, 127, -32}, 4, 64),
            this.q(new byte[]{40, 0, 3, -2}, new byte[]{126, 64, 3, -1}, 4, 64),
            this.q(new byte[]{-43, 3, 36, 95}, new byte[]{-1, -1, -1, -1}, 4, 64),
            this.q(new byte[]{40, 0, 120, 29}, new byte[]{126, 64, 124, 31}, 4, 64)
         );
      this.gP.addPattern(this.q(new byte[]{40, 0, 3, -32}, new byte[]{126, 64, 3, -32}, 4, 64));
      this.za.addPatterns(this.q(new byte[]{81, 0, 3, -1}, new byte[]{95, 0, 3, -1}, 4, 64));
      this.za
         .addPatterns(
            this.q(new byte[]{-72, 64, 4, 0}, new byte[]{-65, -32, 4, 0}, 4, 64),
            this.q(new byte[]{-71, 64, 0, 0}, new byte[]{-65, -64, 0, 0}, 4, 64),
            this.q(new byte[]{-72, 0, 4, 0}, new byte[]{-65, -32, 4, 0}, 4, 64),
            this.q(new byte[]{-71, 0, 0, 0}, new byte[]{-65, -64, 0, 0}, 4, 64),
            this.q(new byte[]{-112, 0, 0, 0}, new byte[]{-97, 0, 0, 0}, 4, 64),
            this.q(new byte[]{113, 0, 0, 31}, new byte[]{127, 0, 2, 31}, 4, 64),
            this.q(new byte[]{107, 0, 0, 31}, new byte[]{127, 56, 2, 31}, 4, 64),
            this.q(new byte[]{52, 0, 0, 0}, new byte[]{126, 0, 0, 16}, 4, 64)
         );
   }

   private void q(byte[] var1, byte[] var2, int var3) {
      this.q(this.gO, var1, var2, var3);
   }

   private void q(BinaryPatternVerifier var1, byte[] var2, byte[] var3, int var4) {
      if (var4 == 32) {
         var1.addPattern(this.q(var2, var3, 4, 32));
      } else if (var4 == 16) {
         var1.addPattern(this.q(var2, var3, 2, 16));
      } else if (var4 == 0) {
         if (((vh)this.q.getProcessor()).q().isLittle()) {
            var1.addPatterns(this.q(var2, var3, 4, 32), this.q(var2, var3, 2, 16));
         } else {
            var1.addPattern(this.q(var2, var3, 4, 0));
         }
      }
   }

   private BinaryPattern q(byte[] var1, byte[] var2, int var3, int var4) {
      return cd.q(((vh)this.q.getProcessor()).q(), var1, var2, var3, var4);
   }

   public CodePointer q(long var1, int var3) {
      return this.q(var1, var3, Jc.eo.q);
   }

   public CodePointer q(long var1, int var3, Jc.eo var4) {
      if (var3 == 64 || this.q.getProcessor().getDefaultMode() == 64) {
         return var1 % 4L == 0L ? this.q(var1, 64, true, false, var4) : null;
      } else if (var1 % 4L == 0L) {
         if (var3 == 0) {
            CodePointer var5 = this.q(var1, 32, true, false, var4);
            return var5 != null ? var5 : this.q(var1, true, var4);
         } else {
            return var3 == 16 ? this.q(var1, true, var4) : this.q(var1, 32, true, false, var4);
         }
      } else {
         if (var1 % 2L != 0L && this.q.getProcessor().getDefaultMode() != 64) {
            var1--;
         }

         return var1 % 2L == 0L ? this.q(var1, true, var4) : null;
      }
   }

   public CodePointer q(long var1) {
      if (this.q.getProcessor().getDefaultMode() == 64) {
         return var1 % 4L == 0L ? this.q(var1, 64, true, true, Jc.eo.xK) : null;
      } else {
         return var1 % 4L == 0L ? this.q(var1, 32, true, true, Jc.eo.xK) : null;
      }
   }

   public boolean q(long var1, int var3, boolean var4) {
      BinaryPatternVerifier var5;
      if (var3 == 64) {
         var5 = var4 ? this.za : this.nf;
      } else {
         var5 = var4 ? this.gO : this.oW;
      }

      return CodeAnalyzerUtil.checkBinaryPattern(this.q, var5, var1, var1 + 4L, 0, var3) != null;
   }

   private CodePointer q(long var1, int var3, boolean var4, boolean var5, Jc.eo var6) {
      if (var3 == 0) {
         throw new IllegalArgumentException();
      } else {
         BinaryPatternVerifier var7;
         if (var3 == 64) {
            var7 = var5 ? this.za : this.nf;
         } else {
            var7 = var5 ? this.gO : this.oW;
         }

         INativeContinuousItem var8 = this.q.getModel().getNextItem(var1);
         if (var7 != null) {
            int var9 = var4 ? 4 + (var3 == 64 ? 1 : 0) : 1;
            long var10 = var1;

            for (int var12 = 0; var12 < var9; var12++) {
               if (var8 != null
                  && var10 >= var8.getMemoryAddress()
                  && (var5 || !(var8 instanceof INativeInstructionItem) || ((INativeInstructionItem)var8).getInstruction().getProcessorMode() != var3)) {
                  return null;
               }

               IBinaryPattern var13 = CodeAnalyzerUtil.checkBinaryPattern(this.q, var7, var10, var10 + 4L, 0, var3);
               if (var13 == null || var6 == Jc.eo.q && !this.RF(var10, var13.getProcessorMode())) {
                  if (var3 == 16) {
                     return null;
                  }

                  if (var3 == 64) {
                     var13 = CodeAnalyzerUtil.checkBinaryPattern(this.q, this.gP, var10, var10 + 4L, 0, var3);
                     if (var13 != null) {
                        var9++;
                     }
                  }

                  var10 += 4L;
               } else {
                  if (this.q.getProcessor().getMode() == 64 || var13.getProcessorMode() != 16) {
                     if (var6 == Jc.eo.RF) {
                        return this.xK(var1, var10, var3);
                     }

                     return this.RF(var1, var10, var3);
                  }

                  var10 += 4L;
               }
            }
         }

         return null;
      }
   }

   public CodePointer q(long var1, boolean var3, Jc.eo var4) {
      byte var5 = 16;
      if (this.RF(var1)) {
         return null;
      } else {
         INativeContinuousItem var6 = this.q.getModel().getPreviousItem(var1);
         if (var6 == null || var6.getMemoryAddressEnd() <= var1 - 2L) {
            fA var7 = OC.q(this.q, var1 - 2L, var5);
            if (OC.gP(var7) && var7.getSize() == 4) {
               return null;
            }
         }

         INativeContinuousItem var24 = this.q.getModel().getNextItem(var1);
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

            IBinaryPattern var16 = CodeAnalyzerUtil.checkBinaryPattern(this.q, this.oW, var9, var9 + 4L, 0, var5);
            if (var16 != null && var16.getProcessorMode() != 32) {
               if (var4 == Jc.eo.RF) {
                  return this.Uv(var1, var9, var5);
               }

               if (var4 != Jc.eo.q || this.RF(var9, var16.getProcessorMode())) {
                  return this.Dw(var1, var9, var5);
               }
            }

            fA var17 = this.Uv.q(var9, var9 + 4L, var5, var11, var12 > 0, true);
            if (var17 == null) {
               var17 = this.Uv.q(var9, var5);
               if (var17 == null || !OC.nf(var17) || var14.isEmpty()) {
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
               if (var17.Dw().xK()) {
                  var11 = true;
               }

               if (var17.getCode().length == 2 && (var4 != Jc.eo.q && (var17.xK()[0] & 254) == 180 || var4 == Jc.eo.q && (var17.xK()[0] & 255) == 181)) {
                  for (String var19 : var13) {
                     if (!this.lm.contains(var19)) {
                        return null;
                     }
                  }

                  return this.Dw(var1, var9, var5);
               }

               var13.add(var17.Dw().q());
               if (var12 > 0) {
                  var12--;
               }

               if (var17.Dw().q().equals("IT")) {
                  var12 = var17.getMnemonic().length() - 1;
               }

               if (var17.q().isPCUpdated() && !var17.Uv().oW()) {
                  IFlowInformation var26 = var17.getBreakingFlow(var9);
                  if (var26.isBrokenKnown()) {
                     var14.add(((ICodePointer)var26.getTargets().get(0)).getAddress());
                  }
               }

               if (OC.Dw(var17, false)) {
                  try {
                     var9 = var17.q().q(var17, var9, null);
                  } catch (ProcessorException var23) {
                     return null;
                  }
               } else {
                  var9 += var17.getSize();
                  if (this.RF.Uv(var9) || this.q.getModel().isRoutineHeader(var9)) {
                     return null;
                  }
               }
            }
         }

         return null;
      }
   }

   public boolean RF(long var1) {
      long var3 = var1 & -4L;

      for (int var5 = 0; var5 < 4; var5++) {
         fA var6 = this.Uv.RF(var3 + 4 * var5, var3 + 4 * var5, 32, true, false, true);
         if (var6 == null) {
            return false;
         }

         if (!CharSequences.isBlank(var6.Dw().Dw())) {
            return false;
         }

         if (var6.q().isPCUpdated()) {
            break;
         }
      }

      return true;
   }

   private CodePointer RF(long var1, long var3, int var5) {
      return this.Uv.q(var1, var3, var5, var1, 32, true);
   }

   private CodePointer xK(long var1, long var3, int var5) {
      return this.Uv.q(var1, var3, var5, var1, 0, true);
   }

   private CodePointer Dw(long var1, long var3, int var5) {
      return this.Uv.q(var1, var3, var5, var3, 128, false);
   }

   private CodePointer Uv(long var1, long var3, int var5) {
      return this.Uv.q(var1, var3, var5, var3, 0, false);
   }

   private boolean RF(long var1, int var3) {
      fA var4 = this.Uv.q(var1, var3);
      if (var4 == null) {
         return false;
      } else {
         if (var4.Dw().q().equals("PUSH")) {
            eL var5 = (eL)var4.q(0);
            boolean var6 = false;
            boolean var7 = false;
            boolean var8 = false;

            for (CW var12 : var5.oW()) {
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

   public boolean xK(long var1) {
      return this.xK.q(var1) > 0;
   }

   public int q(long var1, long var3, int var5) {
      return this.xK.q(var1, var3, var5);
   }

   public boolean q(INativeMethodItem var1) {
      return this.RF(var1) || this.xK(var1);
   }

   public boolean RF(INativeMethodItem var1) {
      return !v.xK(var1.getName()) || this.RF().q(var1, false) != null;
   }

   public boolean xK(INativeMethodItem var1) {
      return this.q(var1.getMemoryAddress(), var1.getData().getCFG().getInstruction(var1.getMemoryAddress()).getProcessorMode(), Jc.eo.RF) != null;
   }

   public rl q() {
      return this.xK;
   }

   public hP RF() {
      return this.Dw;
   }

   public static enum eo {
      q,
      RF,
      xK;
   }
}
