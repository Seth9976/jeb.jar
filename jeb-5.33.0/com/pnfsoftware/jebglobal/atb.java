package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class atb implements IEVisitor {
   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      boolean var4 = false;
      if (var1 instanceof IEStatement) {
         var4 = true;
      } else if (var1 instanceof IEOperation var5) {
         switch (var5.getOperationType()) {
            case MUL2_S:
            case MUL2_U:
            case DIV2_S:
            case DIV2_U:
            case CAST:
            case CAST_S:
            case PAR:
            case CARRY:
            case POW:
            case FADD:
            case FSUB:
            case FMUL:
            case FDIV:
            case FEQ:
            case FNE:
            case FLT:
            case FGT:
            case FLE:
            case FGE:
            case FP2FP:
            case FP2INT:
            case INT2FP:
            case FP2UINT:
            case UINT2FP:
            case FUN:
            case ADD_SSAT:
            case ADD_USAT:
            case SUB_SSAT:
            case SUB_USAT:
            case FUNCTION:
               var4 = true;
         }
      }

      if (var4) {
         var3.interrupt(false);
      }
   }
}
