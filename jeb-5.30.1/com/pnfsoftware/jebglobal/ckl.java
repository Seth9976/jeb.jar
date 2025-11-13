package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPattern;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPatternVerifier;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBinaryPattern;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.InstructionUtil;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISymbolInformation;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;
import java.util.List;

@Ser
public class ckl extends AbstractAnalyzerExtension {
   private static final byte[] q = new byte[]{-113, -103, -128, 16};
   private static final byte[] RF = new byte[]{3, -32, 120, 37};
   private static final byte[] xK = new byte[]{3, -32, 120, 33};
   private static final byte[] Dw = new byte[]{3, 32, -8, 9};
   private static final byte[] Uv = new byte[]{3, 32, 0, 8};
   @SerId(1)
   private BinaryPatternVerifier oW;

   public Long q(BasicBlock var1) {
      if (var1.size() < 4) {
         return null;
      } else {
         cki var2 = (cki)var1.get(0);
         byte[] var3 = var2.xK();
         if (!Arrays.equals(var3, q)) {
            return null;
         } else {
            var2 = (cki)var1.get(1);
            var3 = var2.xK();
            if (!Arrays.equals(var3, RF) && !Arrays.equals(var3, xK)) {
               return null;
            } else {
               var2 = (cki)var1.get(2);
               var3 = var2.xK();
               if (!Arrays.equals(var3, Dw)) {
                  if (var1.size() < 5) {
                     return null;
                  }

                  if (var3.length != 4 || var3[0] != 36 || var3[1] != 24) {
                     return null;
                  }

                  var2 = (cki)var1.get(3);
                  var3 = var2.xK();
                  if (!Arrays.equals(var3, Dw)) {
                     return null;
                  }

                  var2 = (cki)var1.get(4);
                  if (!var2.getMnemonic().startsWith("NOP")) {
                     return null;
                  }
               } else {
                  var2 = (cki)var1.get(3);
                  var3 = var2.xK();
                  if (var3.length != 4 || var3[0] != 36 || var3[1] != 24) {
                     return null;
                  }
               }

               return null;
            }
         }
      }
   }

   public Long RF(BasicBlock var1) {
      if (var1.size() < 3) {
         return null;
      } else {
         cki var2 = (cki)var1.get(0);
         byte[] var3 = var2.xK();
         if (var3.length == 4 && var3[0] == 60 && var3[1] == 25 && var3[2] == 0) {
            var2 = (cki)var1.get(1);
            var3 = var2.xK();
            if (var3.length == 4 && (var3[0] & 252) == 8) {
               long var4 = (EndianUtil.bigEndianBytesToInt(var3) & 67108863L) << 2 | var1.getAddressOfInstruction(1) & 4026531840L;
               var2 = (cki)var1.get(2);
               var3 = var2.xK();
               return var3.length == 4 && var3[0] == 39 && var3[1] == 57 ? var4 : null;
            } else if (var1.size() < 4) {
               return null;
            } else if (var3.length == 4 && var3[0] == 39 && var3[1] == 57) {
               var2 = (cki)var1.get(2);
               var3 = var2.xK();
               if (var3.length == 4 && (var3[0] & 252) == 8) {
                  EndianUtil.bigEndianBytesToInt(var3);
                  var1.getAddressOfInstruction(2);
                  var2 = (cki)var1.get(3);
                  return !var2.getMnemonic().startsWith("NOP") ? null : null;
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

   public Long xK(BasicBlock var1) {
      if (var1.size() < 4) {
         return null;
      } else {
         cki var2 = (cki)var1.get(0);
         byte[] var3 = var2.xK();
         if (var3.length == 4 && var3[0] == 60 && var3[1] == 15) {
            byte[] var4 = new byte[]{var3[2], var3[3], 0, 0};
            var2 = (cki)var1.get(1);
            var3 = var2.xK();
            if (var3.length == 4 && var3[0] == -115 && var3[1] == -7) {
               var4[2] = var3[2];
               var4[3] = var3[3];
               int var5 = EndianUtil.bigEndianBytesToInt(var4);
               var2 = (cki)var1.get(2);
               var3 = var2.xK();
               if (!Arrays.equals(var3, Uv)) {
                  if (var1.size() < 5) {
                     return null;
                  }

                  if (!Arrays.equals(var3, new byte[]{37, -8, var4[2], var4[3]})) {
                     return null;
                  }

                  var2 = (cki)var1.get(3);
                  var3 = var2.xK();
                  if (!Arrays.equals(var3, Uv)) {
                     return null;
                  }

                  var2 = (cki)var1.get(4);
                  var3 = var2.xK();
                  if (!var2.getMnemonic().startsWith("NOP") && (var3.length != 4 || var3[0] != 60 || var3[1] != 15)) {
                     return null;
                  }
               } else {
                  var2 = (cki)var1.get(3);
                  var3 = var2.xK();
                  if (!Arrays.equals(var3, new byte[]{37, -8, var4[2], var4[3]})) {
                     return null;
                  }
               }

               return var5 & 4294967295L;
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   @Override
   public ChainedOperationResult getTrampolineTarget(CFG var1) {
      if (var1.size() != 1) {
         return ChainedOperationResult.continue_();
      } else {
         BasicBlock var2 = var1.get(0);
         Long var3 = this.q(var2);
         if (var3 != null) {
            return new ChainedOperationResult(new Pointer(var3, 4, 5));
         } else {
            var3 = this.RF(var2);
            if (var3 != null) {
               return new ChainedOperationResult(new CodePointer(var3));
            } else {
               var3 = this.xK(var2);
               return var3 != null ? new ChainedOperationResult(new Pointer(var3, 4, 5)) : ChainedOperationResult.continue_();
            }
         }
      }
   }

   @Override
   protected void initializeProloguePatterns(BinaryPatternVerifier var1) {
      var1.addPatterns(
         this.q(new byte[]{39, -67, -128, 0, 60, 28, 0, 0}, new byte[]{-1, -1, -128, 0, -1, -1, 0, 0}),
         this.q(new byte[]{60, 28, 0, 0, 39, -100, 0, 0}, new byte[]{-1, -1, 0, 0, -1, -1, 0, 0}),
         this.q(new byte[]{60, 28, 0, 0, 0, 0, 0, 0, 39, -100, 0, 0}, new byte[]{-1, -1, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0}),
         this.q(new byte[]{60, 28, 0, 0, 39, -67, -128, 0}, new byte[]{-1, -1, 0, 0, -1, -1, -128, 0}),
         this.q(new byte[]{60, 28, 0, 0, 0, 0, 0, 0, 39, -67, -128, 0}, new byte[]{-1, -1, 0, 0, 0, 0, 0, 0, -1, -1, -128, 0}),
         this.q(new byte[]{39, -67, -128, 0}, new byte[]{-1, -1, -128, 0}),
         this.q(new byte[]{60, 25, 0, 0, 39, 57, 0, 0}, new byte[]{-1, -1, -1, 0, -1, -1, 0, 0})
      );
      this.oW = new BinaryPatternVerifier();
      this.oW
         .addPatterns(
            this.q(new byte[]{60, 28, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 39, -100, 0, 0}, new byte[]{-1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0}),
            this.q(
               new byte[]{60, 28, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 39, -100, 0, 0},
               new byte[]{-1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0}
            )
         );
   }

   @Override
   public ChainedOperationResult getPrologueLooking(long var1, long var3) {
      byte[] var5 = new byte[4];
      if (!VirtualMemoryUtil.readSafe(this.gca.getMemory(), var1, var5)) {
         return ChainedOperationResult.ignore();
      } else {
         if (this.q(var5)) {
            var1 += 4L;
            if (var1 >= var3) {
               return ChainedOperationResult.continue_();
            }
         }

         return this.q(var1, var3);
      }
   }

   protected ChainedOperationResult q(long var1, long var3) {
      ChainedOperationResult var5 = super.getPrologueLooking(var1, var3);
      if (var5.getResult() != null) {
         return var5;
      } else {
         if (var3 - var1 >= this.oW.getLongestSize()) {
            byte[] var6 = new byte[this.oW.getLongestSize()];
            if (!VirtualMemoryUtil.readSafe(this.gca.getMemory(), var1, var6)) {
               return ChainedOperationResult.ignore();
            }

            IBinaryPattern var7 = this.oW.verify(this.gca, var1, var6, 0, var6.length);
            if (var7 != null) {
               return new ChainedOperationResult(new CodePointer(var1, var7.getProcessorMode()));
            }
         }

         return ChainedOperationResult.continue_();
      }
   }

   private boolean q(byte[] var1) {
      int var2 = EndianUtil.bytesToInt(var1, 0, this.gca.getProcessor().getEndianness().toByteOrder());
      int var3 = var2 & -67108864;
      switch (var3) {
         case 0:
            return (var2 & 62) == 8;
         case 67108864:
            int var4 = var2 & 1835008;
            return var4 == 0 || var4 == 16;
         case 134217728:
         case 201326592:
         case 268435456:
         case 335544320:
         case 402653184:
         case 469762048:
            return true;
         case 1342177280:
         case 1409286144:
         case 1476395008:
         case 1543503872:
            return true;
         default:
            return false;
      }
   }

   public BinaryPatternVerifier q() {
      BinaryPatternVerifier var1 = new BinaryPatternVerifier();
      this.initializeProloguePatterns(var1);
      return var1;
   }

   private BinaryPattern q(byte[] var1, byte[] var2) {
      if (this.gca.getProcessor().getEndianness().isLittle()) {
         EndianUtil.swapByGroup(var1, 4);
         EndianUtil.swapByGroup(var2, 4);
      }

      return new BinaryPattern(var1, var2);
   }

   @Override
   protected void initializePaddingPatterns(BinaryPatternVerifier var1) {
      var1.addPatterns(this.q(new byte[]{0, 0, 0, 0}, new byte[]{-1, -1, -1, -1}), this.q(new byte[]{0, 32, 8, 37}, new byte[]{-1, -1, -1, -1}));
   }

   public ChainedOperationResult q(long var1, cki var3, List var4) {
      boolean var5 = false;
      clv[] var6 = var3.q();

      for (clv var10 : var6) {
         if (var10 != null && InstructionUtil.isAddressOperand(var10)) {
            byte var12 = 0;
            String var13 = var3.getMnemonic();
            int var11;
            switch (var13) {
               case "ADDIUPC":
               case "LAPC":
                  var11 = var10.getOperandBitsize() / 8;
                  break;
               case "LWPC":
               case "LWUPC":
                  var11 = 4;
                  var12 = 2;
                  break;
               case "LDPC":
                  var11 = 8;
                  var12 = 2;
                  break;
               default:
                  continue;
            }

            var4.add(new Pointer(var10.getOperandValue(var1), var11, var12));
            var5 = true;
         }
      }

      return var5 ? ChainedOperationResult.TRUE_STOP : ChainedOperationResult.TRUE_CONTINUE;
   }

   public ChainedOperationResult q(long var1, cki var3) {
      if (var3.RF().getBranchType() != IInsnEmulator.BranchType.CALL) {
         return ChainedOperationResult.FALSE_STOP;
      } else {
         try {
            long var4 = var1 + 4L;
            if (var3.RF().q()) {
               var4 += 4L;
            }

            int var6 = 0;
            Endianness var7 = this.gca.getProcessor().getEndianness();
            IVirtualMemory var8 = this.gca.getMemory();

            do {
               int var9 = var8.readInt(var4, var7);
               if (var9 >>> 16 == 15388) {
                  return ChainedOperationResult.stop(!this.q(var4 + 4L, var7, var8, 3));
               }

               if ((var9 & -32768) == 666730496) {
                  return new ChainedOperationResult(var3.RF().getBranchType() == IInsnEmulator.BranchType.CALL);
               }

               if (!this.q(var9)) {
                  return ChainedOperationResult.FALSE_STOP;
               }

               var6++;
               var4 += 4L;
            } while (var6 < 4);

            return ChainedOperationResult.TRUE_STOP;
         } catch (MemoryException var10) {
            return ChainedOperationResult.FALSE_IGNORE;
         }
      }
   }

   private boolean q(long var1, Endianness var3, IVirtualMemory var4, int var5) throws MemoryException {
      int var6 = var4.readInt(var1, var3);
      if (var6 == 60416033) {
         return false;
      } else if (var6 == 60809249) {
         return true;
      } else {
         return var5 <= 1 ? false : this.q(var1 + 4L, var3, var4, var5 - 1);
      }
   }

   private boolean q(int var1) {
      return var1 == 0 ? true : (var1 & -65075205) == 33 && var1 >>> 21 == (var1 >>> 11 & 31);
   }

   public ChainedOperationResult q(long var1, cki var3, IBasicBlockSkeleton var4) {
      if (var3.getMnemonic().equalsIgnoreCase("jr")) {
         clv var5 = var3.q()[0];
         if (var5.getOperandType() == 0 && var5.getOperandBitsize() == 32) {
            long var6 = var5.getOperandValue();
            if (var6 == 2L || var6 == 3L || var6 >= 8L && var6 <= 15L) {
               return ChainedOperationResult.TRUE_STOP;
            }
         }
      }

      return ChainedOperationResult.FALSE_STOP;
   }

   @Override
   public ChainedOperationResult preprocessImage(int var1) {
      INativeCodeUnit var2 = this.getUnit();
      if (!(var2.getParent() instanceof ICodeObjectUnit)) {
         return ChainedOperationResult.FALSE_CONTINUE;
      } else {
         ICodeObjectUnit var3 = (ICodeObjectUnit)var2.getParent();

         for (ISymbolInformation var5 : var3.getSymbols(10, 0)) {
            String var6 = var5.getName();
            if (var6 != null) {
               var6 = var6.toLowerCase();
               if (var6.contains("gp")) {
                  long var7 = var2.getVirtualImageBase() + var5.getSymbolRelativeAddress();
                  IReferenceType var9 = var2.getTypeManager().getVoidReference();
                  var2.setDataAt(var7, var9, var6);
                  return ChainedOperationResult.TRUE_CONTINUE;
               }
            }
         }

         return ChainedOperationResult.FALSE_CONTINUE;
      }
   }
}
