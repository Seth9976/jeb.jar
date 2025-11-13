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

public class amh {
   private static final StructuredLogger pC = aco.pC(amh.class);
   private IERoutineContext A;
   private int kS;
   private INativeDecompilerContext wS;
   private INativeContext UT;
   private IVirtualMemory E;
   private ICodeObjectUnit sY;

   public amh(IERoutineContext var1, int var2) {
      this.A = var1;
      this.kS = var2;
      this.UT = var1.getNativeContext();
      this.E = this.UT.getMemory();
      if (this.UT instanceof INativeCodeUnit) {
         this.sY = ((INativeCodeUnit)this.UT).getCodeObjectContainer();
      }
   }

   public amh(IERoutineContext var1) {
      this(var1, 3);
   }

   public void pC(INativeDecompilerContext var1) {
      this.wS = var1;
   }

   public void pC(int var1) {
      this.kS = var1;
   }

   public int pC() {
      return this.kS;
   }

   public int A() {
      if (this.kS == 0) {
         return 0;
      } else {
         amh.Av var1 = new amh.Av();

         for (IEStatement var3 : this.A.getCfg().instructions()) {
            var1.kS = true;
            if (var3 instanceof IEAssign) {
               if (((IEAssign)var3).getLeftOperand() instanceof IEMem) {
                  var1.kS = false;
                  IEMem var4 = (IEMem)((IEAssign)var3).getLeftOperand();
                  var4.getReference().visitDepthPost(var1, var4);
                  var1.kS = true;
                  IEGeneric var5 = ((IEAssign)var3).getRightOperand();
                  var5.visitDepthPost(var1, var3);
               } else {
                  var1.kS = false;
                  IEGeneric var6 = ((IEAssign)var3).getLeftOperand();
                  var6.visitDepthPost(var1, var3);
                  var1.kS = true;
                  IEGeneric var7 = ((IEAssign)var3).getRightOperand();
                  var7.visitDepthPost(var1, var3);
               }
            } else {
               var3.visitDepthPost(var1);
            }
         }

         return var1.A;
      }
   }

   private Long pC(long var1, int var3) {
      return !A(var3) ? null : VirtualMemoryUtil.readAsUnsignedLongSafe(this.E, this.UT.getProcessor().getEndianness(), var1, var3 / 8);
   }

   protected Long pC(IEMem var1) {
      if (this.wS != null && !(Boolean)this.wS.getExtensionsManager().isMemoryResolutionAllowed(this.wS, this.A, var1).getResult()) {
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

   protected static boolean A(int var0) {
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

   protected int pC(long var1) {
      if ((this.kS & 12) != 0) {
         return 1;
      } else if (this.sY == null && (this.kS & 8) != 0) {
         return 1;
      } else if (this.sY != null && (this.kS & 4) != 0) {
         return 1;
      } else if (this.sY == null) {
         return 0;
      } else {
         if ((this.kS & 1) != 0) {
            ISegmentInformation var3 = CodeObjectUnitUtil.findSectionByRelativeAddress(this.sY, var1 - ((INativeCodeUnit)this.UT).getVirtualImageBase());
            if (var3 != null && (var3.getFlags() & 1) == 0) {
               return 1;
            }

            var3 = CodeObjectUnitUtil.findSegmentByRelativeAddress(this.sY, var1 - ((INativeCodeUnit)this.UT).getVirtualImageBase());
            if (var3 != null && (var3.getFlags() & 3) == 2) {
               return 1;
            }
         }

         if ((this.kS & 2) != 0 && this.sY instanceof IELFUnit) {
            ISegmentInformation var5 = CodeObjectUnitUtil.findSectionByRelativeAddress(this.sY, var1 - ((INativeCodeUnit)this.UT).getVirtualImageBase());
            if (var5 != null && Strings.equals(var5.getName(), ".got")) {
               return 2;
            }
         }

         return 0;
      }
   }

   protected boolean pC(long var1, long var3, int var5, int var6) {
      switch (var6) {
         case 2:
            if (var3 == 0L) {
               break;
            }
         case 1:
            if ((this.kS & 256) != 0 && this.UT.getNativeItemAt(var1) == null) {
               return false;
            }

            if ((this.kS & 512) == 0 || var5 == this.E.getSpaceBits() && this.UT.getNativeItemAt(var3) != null) {
               return true;
            }

            return false;
      }

      return false;
   }

   public IEImm pC(long var1, int var3, Long var4) {
      if (!A(var3)) {
         return null;
      } else {
         int var5 = this.pC(var1);
         if (var5 == 0) {
            return null;
         } else {
            Long var6 = this.pC(var1, var3);
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

               return !this.pC(var1, var6, var3, var5) ? null : ajr.pC(var6, var3);
            }
         }
      }
   }

   private class Av implements IEVisitor {
      private int A = 0;
      private boolean kS = true;

      public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
         Long var4 = null;
         Long var5 = null;
         if (var1 instanceof IEMem var6) {
            var4 = amh.this.pC(var6);
         } else if (this.kS && var1 instanceof IEOperation var10) {
            boolean var7 = true;
            if (var10.getOperationType() == OperationType.SUB) {
               var7 = false;
            } else if (var10.getOperationType() != OperationType.ADD) {
               return;
            }

            if (var10.getOperand1() instanceof IEMem && var10.getOperand2() instanceof IEImm) {
               IEMem var8 = (IEMem)var10.getOperand1();
               var4 = amh.this.pC(var8);
               if (var4 != null) {
                  var5 = ((IEImm)var10.getOperand2()).getValueAsLong();
                  var5 = var7 ? var5 : -var5;
               }
            }
         }

         if (var4 != null) {
            int var11 = var1.getBitsize();
            IEImm var12 = amh.this.pC(var4, var11, var5);
            if (var12 != null) {
               if (var1.getType() != null) {
                  var12 = var12.duplicateWithType(var1.getType());
               }

               Object[] var10000 = new Object[]{var2};
               if (var2.replaceSubExpression(var1, var12)) {
                  var10000 = new Object[]{var1, var12};
                  this.A++;
               }
            }
         }
      }
   }
}
