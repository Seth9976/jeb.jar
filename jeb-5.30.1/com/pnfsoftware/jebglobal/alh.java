package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UntranslatedIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroup;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroupElt;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;

public abstract class alh {
   public void q(IEGeneric var1) {
      if (var1 instanceof IEStatement) {
         this.q((IEStatement)var1);
      } else if (var1 instanceof IECompose) {
         this.q((IECompose)((alo)var1));
      } else if (var1 instanceof IECond) {
         this.q((IECond)((alp)var1));
      } else if (var1 instanceof IEImm) {
         this.q((IEImm)var1);
      } else if (var1 instanceof IEMem) {
         this.q((IEMem)var1);
      } else if (var1 instanceof amf) {
         this.q((IEOperation)((amf)var1));
      } else if (var1 instanceof IERange) {
         this.q((IERange)var1);
      } else if (var1 instanceof IESlice) {
         this.q((IESlice)var1);
      } else if (var1 instanceof IEVar) {
         this.q((IEVar)var1);
      } else if (var1 instanceof IEGroup) {
         this.q((IEGroup)var1);
      } else {
         if (!(var1 instanceof IEGroupElt)) {
            throw new UntranslatedIntermediateExpressionException("No translation available for %s", var1);
         }

         this.q((IEGroupElt)((alt)var1));
      }
   }

   public void q(IEStatement var1) {
      throw new UntranslatedIntermediateExpressionException("No translation available for EStatement");
   }

   public void q(IECompose var1) {
      throw new UntranslatedIntermediateExpressionException("No translation available for ECompose");
   }

   public void q(IECond var1) {
      throw new UntranslatedIntermediateExpressionException("No translation available for ECond");
   }

   public void q(IEImm var1) {
      throw new UntranslatedIntermediateExpressionException("No translation available for EImm");
   }

   public void q(IEMem var1) {
      throw new UntranslatedIntermediateExpressionException("No translation available for EMem");
   }

   public void q(IEOperation var1) {
      throw new UntranslatedIntermediateExpressionException("No translation available for EOperation");
   }

   public void q(IERange var1) {
      throw new UntranslatedIntermediateExpressionException("No translation available for ERange");
   }

   public void q(IESlice var1) {
      throw new UntranslatedIntermediateExpressionException("No translation available for ESlice");
   }

   public void q(IEVar var1) {
      throw new UntranslatedIntermediateExpressionException("No translation available for EVar");
   }

   public void q(IEGroup var1) {
      throw new UntranslatedIntermediateExpressionException("No translation available for EGroup");
   }

   public void q(IEGroupElt var1) {
      throw new UntranslatedIntermediateExpressionException("No translation available for EGroupElt");
   }
}
