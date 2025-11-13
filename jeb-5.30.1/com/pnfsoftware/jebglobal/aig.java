package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodSimulator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CSimulationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CSimulationLogger;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IMasterOptimizerInstrumenter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerEntry;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class aig implements IMasterOptimizerInstrumenter {
   private static final StructuredLogger q = aeg.q(aig.class);
   private final CEnvironment RF;
   private final boolean xK;
   private final boolean Dw;
   private CSimulationLogger Uv;

   public aig(CEnvironment var1) {
      this(var1, true);
   }

   public aig(CEnvironment var1, boolean var2) {
      this(var1, var2, false);
   }

   public aig(CEnvironment var1, boolean var2, boolean var3) {
      this.RF = var1;
      this.xK = var2;
      this.Dw = var3;
   }

   public void q(ICMethod var1) {
      this.xK(var1);
   }

   private void xK(ICMethod var1) {
      this.Uv = this.Dw(var1);
   }

   private CSimulationLogger Dw(ICMethod var1) {
      CSimulationLogger var2 = null;

      try {
         this.RF.clearState();
         CMethodSimulator var3 = new CMethodSimulator(var1, this.RF, this.Dw);
         var2 = var3.simulate();
      } catch (CSimulationException var4) {
         if (!this.xK) {
            throw var4;
         }
      }

      return var2;
   }

   public void q(ICMethod var1, OptimizerEntry var2, int var3, long var4) {
      if (var3 != 0 && this.Uv != null) {
         this.RF(var1, var2);
      }
   }

   private void RF(ICMethod var1, OptimizerEntry var2) {
      CSimulationLogger var3 = this.Dw(var1);
      this.q(var3, var2);
   }

   private void q(CSimulationLogger var1, OptimizerEntry var2) {
      if (this.Uv != null) {
         if (var1 == null) {
            throw new JebRuntimeException("simulation failed, while init succeeded");
         } else if (!CMethodSimulator.areEquivalentSimulations(var1, this.Uv)) {
            Object[] var10000 = new Object[]{this.Uv.getFinalState()};
            var10000 = new Object[]{var1.getFinalState()};
            throw new JebRuntimeException(Strings.ff("simulation difference after %s", var2.getOptimizer().getPluginInformation().getName()));
         }
      } else {
         throw new JebRuntimeException("no known state for this method");
      }
   }

   public void q(ICMethod var1, OptimizerEntry var2) {
   }

   public void RF(ICMethod var1) {
   }
}
