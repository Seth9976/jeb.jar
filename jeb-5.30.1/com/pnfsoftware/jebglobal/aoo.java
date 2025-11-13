package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class aoo {
   private static final StructuredLogger zz = aeg.q(aoo.class);
   public static final int q = 0;
   public static final int RF = 1;
   public static final int xK = 2;
   public static final int Dw = 3;
   public static final int Uv = 4;
   public static final int oW = 8;
   public static final int gO = 12;
   public static final int nf = 65280;
   public static final int gP = 256;
   public static final int za = 512;
   public static final int lm = 3;
   private IERoutineContext JY;
   private int HF;
   private INativeDecompilerContext LK;
   private INativeContext io;
   private IVirtualMemory qa;
   private ICodeObjectUnit Hk;

   public aoo(IERoutineContext var1, int var2) {
      this.JY = var1;
      this.HF = var2;
      this.io = var1.getNativeContext();
      this.qa = this.io.getMemory();
      if (this.io instanceof INativeCodeUnit) {
         this.Hk = ((INativeCodeUnit)this.io).getCodeObjectContainer();
      }
   }

   public aoo(IERoutineContext var1) {
      this(var1, 3);
   }

   public void q(INativeDecompilerContext var1) {
      this.LK = var1;
   }

   public INativeDecompilerContext q() {
      return this.LK;
   }

   public void q(int var1) {
      this.HF = var1;
   }

   public int RF() {
      return this.HF;
   }

   public int xK() {
      if (this.HF == 0) {
         return 0;
      } else {
         aoo.eo var1 = new aoo.eo();

         for (IEStatement var3 : this.JY.getCfg().instructions()) {
            var1.xK = true;
            if (var3 instanceof IEAssign) {
               if (((IEAssign)var3).getLeftOperand() instanceof IEMem) {
                  var1.xK = false;
                  IEMem var4 = (IEMem)((IEAssign)var3).getLeftOperand();
                  var4.getReference().visitDepthPost(var1, var4);
                  var1.xK = true;
                  IEGeneric var5 = ((IEAssign)var3).getRightOperand();
                  var5.visitDepthPost(var1, var3);
               } else {
                  var1.xK = false;
                  IEGeneric var6 = ((IEAssign)var3).getLeftOperand();
                  var6.visitDepthPost(var1, var3);
                  var1.xK = true;
                  IEGeneric var7 = ((IEAssign)var3).getRightOperand();
                  var7.visitDepthPost(var1, var3);
               }
            } else {
               var3.visitDepthPost(var1);
            }
         }

         return var1.RF;
      }
   }

   private Long q(long var1, int var3) {
      return !RF(var3) ? null : VirtualMemoryUtil.readAsUnsignedLongSafe(this.qa, this.io.getProcessor().getEndianness(), var1, var3 / 8);
   }

   protected Long q(IEMem var1) {
      if (this.LK != null && !(Boolean)this.LK.getExtensionsManager().isMemoryResolutionAllowed(this.LK, this.JY, var1).getResult()) {
         return null;
      } else {
         IEGeneric var2 = var1.getReference();
         if (var2 instanceof IEImm var3) {
            if (var3.canReadAsAddress()) {
               return var3.getValueAsAddress();
            }
         } else if (var2 instanceof IEVar var4 && var4.isGlobalReference() && var4.getAddress() != null) {
            return var4.getAddress();
         }

         return null;
      }
   }

   protected static boolean RF(int var0) {
      switch (var0) {
         case 8:
         case 16:
         case 32:
         case 64:
            return true;
         default:
            return false;
      }
   }

   protected int q(long var1) {
      if ((this.HF & 12) != 0) {
         return 1;
      } else if (this.Hk == null && (this.HF & 8) != 0) {
         return 1;
      } else if (this.Hk != null && (this.HF & 4) != 0) {
         return 1;
      } else if (this.Hk == null) {
         return 0;
      } else {
         if ((this.HF & 1) != 0) {
            ISegmentInformation var3 = CodeObjectUnitUtil.findSectionByRelativeAddress(this.Hk, var1 - ((INativeCodeUnit)this.io).getVirtualImageBase());
            if (var3 != null && (var3.getFlags() & 1) == 0) {
               return 1;
            }

            var3 = CodeObjectUnitUtil.findSegmentByRelativeAddress(this.Hk, var1 - ((INativeCodeUnit)this.io).getVirtualImageBase());
            if (var3 != null && (var3.getFlags() & 3) == 2) {
               return 1;
            }
         }

         if ((this.HF & 2) != 0 && this.Hk instanceof IELFUnit) {
            ISegmentInformation var5 = CodeObjectUnitUtil.findSectionByRelativeAddress(this.Hk, var1 - ((INativeCodeUnit)this.io).getVirtualImageBase());
            if (var5 != null && Strings.equals(var5.getName(), ".got")) {
               return 2;
            }
         }

         return 0;
      }
   }

   protected boolean q(long var1, long var3, int var5, int var6) {
      switch (var6) {
         case 2:
            if (var3 == 0L) {
               break;
            }
         case 1:
            if ((this.HF & 256) != 0 && this.io.getNativeItemAt(var1) == null) {
               return false;
            }

            if ((this.HF & 512) == 0 || var5 == this.qa.getSpaceBits() && this.io.getNativeItemAt(var3) != null) {
               return true;
            }

            return false;
      }

      return false;
   }

   public IEImm q(long var1, int var3, Long var4) {
      if (!RF(var3)) {
         return null;
      } else {
         int var5 = this.q(var1);
         if (var5 == 0) {
            return null;
         } else {
            Long var6 = this.q(var1, var3);
            if (var6 == null) {
               return null;
            } else {
               if (var4 != null) {
                  if (var3 == 32 && (var6 & -2147483648L) != 0L) {
                     var6 = (long)var6.intValue();
                  } else if (var3 == 16 && (var6 & 32768L) != 0L) {
                     var6 = (long)var6.shortValue();
                  }

                  var6 = var6 + var4;
                  if (var6 < 0L) {
                     return null;
                  }
               }

               return !this.q(var1, var6, var3, var5) ? null : alu.q(var6, var3);
            }
         }
      }
   }

   private class eo implements IEVisitor {
      private int RF = 0;
      private boolean xK = true;

      public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
         Long var4 = null;
         Long var5 = null;
         if (var1 instanceof IEMem var6) {
            var4 = aoo.this.q(var6);
         } else if (this.xK && var1 instanceof IEOperation var10) {
            boolean var7 = true;
            if (var10.getOperationType() == OperationType.SUB) {
               var7 = false;
            } else if (var10.getOperationType() != OperationType.ADD) {
               return;
            }

            if (var10.getOperand1() instanceof IEMem && var10.getOperand2() instanceof IEImm) {
               IEMem var8 = (IEMem)var10.getOperand1();
               var4 = aoo.this.q(var8);
               if (var4 != null) {
                  var5 = ((IEImm)var10.getOperand2()).getValueAsLong();
                  var5 = var7 ? var5 : -var5;
               }
            }
         }

         if (var4 != null) {
            int var11 = var1.getBitsize();
            IEImm var12 = aoo.this.q(var4, var11, var5);
            if (var12 != null) {
               if (var1.getType() != null) {
                  var12 = var12.duplicateWithType(var1.getType());
               }

               Object[] var10000 = new Object[]{var2};
               if (var2.replaceSubExpression(var1, var12)) {
                  var10000 = new Object[]{var1, var12};
                  this.RF++;
               }
            }
         }
      }
   }
}
