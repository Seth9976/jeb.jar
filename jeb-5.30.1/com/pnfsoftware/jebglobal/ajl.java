package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICContinue;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICInstanceField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICVisitor;
import java.util.List;

class ajl implements ICVisitor {
   ajl(List var1, List var2) {
      this.q = var1;
      this.RF = var2;
   }

   public void q(ICElement var1, ICElement var2, IVisitResults var3) {
      if (var1 instanceof ICGoto || var1 instanceof ICContinue || var1 instanceof ICLabel || var1 instanceof ICJumpFar || var1 instanceof ICBreak) {
         var3.interrupt(false);
      } else if (var1 instanceof ICDecl) {
         var3.interrupt(false);
      } else if (var1 instanceof ICIdentifier) {
         this.q.add((ICIdentifier)var1);
      } else if (var1 instanceof ICInstanceField) {
         this.q.add((ICInstanceField)var1);
      } else if (var1 instanceof ICOperation
         && (((ICOperation)var1).getOperatorType() == COperatorType.REF || ((ICOperation)var1).getOperatorType() == COperatorType.SIZEOF)) {
         this.RF.addAll(var1.getSubElements());
      }
   }
}
