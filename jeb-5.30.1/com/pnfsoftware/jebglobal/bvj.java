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

public class bvj extends AbstractGraphReorganizer {
   private static final ILogger q = GlobalLog.getLogger(bvj.class);
   private IDMethodContext RF;

   public bvj(IDMethodContext var1) {
      super(var1.getCfg());
      this.RF = var1;
   }

   @Override
   public CFG reorder(int[] var1) {
      CFG var2 = super.reorder(var1);
      if (var2 == null) {
         return null;
      } else {
         this.q();
         return var2;
      }
   }

   @Override
   public void shift(int var1) {
      if (var1 == 0) {
         throw new IllegalArgumentException();
      } else {
         super.shift(var1);
         this.q();
      }
   }

   public void q() {
      if (this.getReorganizedCfg() != null && this.getConversionMap() != null) {
         HashMap var1 = new HashMap();
         this.getConversionMap().forEach((var1x, var2) -> var1.put(var1x.intValue(), var2.intValue()));
         this.RF.replaceCFG(this.getReorganizedCfg(), var1);
      } else {
         throw new IllegalStateException();
      }
   }

   protected IDInstruction q(IDInstruction var1, long var2) {
      var1.setOffset(var2);
      return var1;
   }

   protected boolean q(IDInstruction var1) {
      return var1.isOpcode(DOpcodeType.IR_JCOND, DOpcodeType.IR_SWITCH);
   }

   protected IDInstruction q(long var1, long var3) {
      IDInstruction var5 = this.RF.createJump((int)var3);
      var5.setOffset(var1);
      return var5;
   }

   protected IDInstruction q(long var1, int var3) {
      IDInstruction var4 = this.RF.createNop();
      var4.setOffset(var1);
      var4.setSize(var3);
      return var4;
   }

   protected void RF(IDInstruction var1, long var2) {
      var1.setBranchTarget((int)var2);
   }

   protected boolean RF(IDInstruction var1) {
      return var1.isOpcode(DOpcodeType.IR_JUMP, DOpcodeType.IR_JCOND, DOpcodeType.IR_SWITCH);
   }

   protected void q(IDInstruction var1, Map var2) {
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
