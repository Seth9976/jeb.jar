package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.AbstractGraphReorganizer;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;
import java.util.Map;

public class brb extends AbstractGraphReorganizer {
   private static final ILogger pC = GlobalLog.getLogger(brb.class);
   private IDMethodContext A;

   public brb(IDMethodContext var1) {
      super(var1.getCfg());
      this.A = var1;
   }

   @Override
   public CFG reorder(int[] var1) {
      CFG var2 = super.reorder(var1);
      if (var2 == null) {
         return null;
      } else {
         this.pC();
         return var2;
      }
   }

   @Override
   public void shift(int var1) {
      if (var1 == 0) {
         throw new IllegalArgumentException();
      } else {
         super.shift(var1);
         this.pC();
      }
   }

   public void pC() {
      if (this.getReorganizedCfg() != null && this.getConversionMap() != null) {
         HashMap var1 = new HashMap();
         this.getConversionMap().forEach((var1x, var2) -> var1.put(var1x.intValue(), var2.intValue()));
         this.A.replaceCFG(this.getReorganizedCfg(), var1);
      } else {
         throw new IllegalStateException();
      }
   }

   protected IDInstruction pC(IDInstruction var1, long var2) {
      var1.setOffset(var2);
      return var1;
   }

   protected boolean pC(IDInstruction var1) {
      return var1.isOpcode(DOpcodeType.IR_JCOND, DOpcodeType.IR_SWITCH);
   }

   protected IDInstruction pC(long var1, long var3) {
      IDInstruction var5 = this.A.createJump((int)var3);
      var5.setOffset(var1);
      return var5;
   }

   protected IDInstruction pC(long var1, int var3) {
      IDInstruction var4 = this.A.createNop();
      var4.setOffset(var1);
      var4.setSize(var3);
      return var4;
   }

   protected void A(IDInstruction var1, long var2) {
      var1.setBranchTarget((int)var2);
   }

   protected boolean A(IDInstruction var1) {
      return var1.isOpcode(DOpcodeType.IR_JUMP, DOpcodeType.IR_JCOND, DOpcodeType.IR_SWITCH);
   }

   protected void pC(IDInstruction var1, Map var2) {
      if (var1.isJump() || var1.isJcond()) {
         long var8 = var1.getBranchTarget();
         var1.setBranchTarget(((Long)var2.get(var8)).intValue());
      } else if (var1.isSwitch()) {
         IDSwitchData var3 = var1.getSwitchData();

         for (IDTarget var5 : var3.getTargets(false)) {
            long var6 = (Long)var2.get((long)var5.getOffset());
            var5.setOffset((int)var6);
         }
      }
   }
}
